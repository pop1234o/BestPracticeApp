package com.liyafeng.network.download;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class FileDownLoader {

    private final ExecutorService executorService;
    private HashMap<String, Task> hashMap;

    private static final String DOWNLOAD_INFO = "download_info";
    private SharedPreferences preferences;

    private FileDownLoader() {
//        executorService = Executors.newCachedThreadPool();
        executorService = new ThreadPoolExecutor(0, Integer.MAX_VALUE,
                60L, TimeUnit.SECONDS,
                new SynchronousQueue<Runnable>());
        hashMap = new HashMap();
    }

    private static class Holder {
        private static FileDownLoader downLoader = new FileDownLoader();
    }

    public static FileDownLoader getInstance() {
        return Holder.downLoader;
    }


    public void start(Context app, final String downUrl, final String path, IDownloadListener listener) {

        if (hashMap.containsKey(downUrl)) {
            Log.i("test", "已经开始下载");
            return;
        }
        if (preferences == null) {
            preferences = app.getSharedPreferences(DOWNLOAD_INFO, Context.MODE_PRIVATE);

        }
        Task task = new Task();
        task.etag = getETag(downUrl);
        task.downLoadUrl = downUrl;
        task.storagePath = path;
        task.listener = listener;
        hashMap.put(downUrl, task);

        executorService.execute(new DownRunnable(task));
    }


    private String getETag(String downUrl) {

        String string = preferences.getString(downUrl + "etag", "");
        return string;
    }

    private void putETag(String downUrl, String etag) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(downUrl + "etag", etag);
        editor.commit();
    }


    private void clearEtag(String downLoadUrl) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove(downLoadUrl + "etag");
        editor.commit();
    }

    private static String getTempName(String path) {
        return path + ".temp";
    }

    public static class Task {
        public boolean isCancel;
        public String downLoadUrl;
        public String storagePath;
        public IDownloadListener listener;
        public String etag;
    }

    public interface IDownloadListener {
        void progress(int soFarBytes, int totalBytes);

        void completed();

        void error(Exception e);

        void start();
    }

    public void stopDownLoad(String url) {
        Task task = hashMap.get(url);
        if (task != null) {
            task.isCancel = true;
        }
    }

    public void stopAll() {
        Iterator<Map.Entry<String, Task>> iterator = hashMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Task> entry = iterator.next();
            Task task = entry.getValue();
            task.isCancel = true;
        }
        hashMap.clear();
    }

    class DownRunnable implements Runnable {

        Task task;

        public DownRunnable(Task task) {
            this.task = task;
        }

        @Override
        public void run() {

//				BufferedOutputStream outputStream = null;
            RandomAccessFile accessFile = null;
            InputStream inputStream = null;
            HttpURLConnection connection = null;
            try {
                String downUrl = task.downLoadUrl;
                String path = task.storagePath;
                URL url = new URL(downUrl);
                connection = (HttpURLConnection) url.openConnection();
                connection.setConnectTimeout(10000);

                File downLoadFile = new File(path);

                //如果要下载的这个文件已经存在，则删除，重新下载
                if (downLoadFile.exists()) {
                    downLoadFile.delete();
                }

                String tempName = getTempName(path);
                //
                File tempFile = new File(tempName);
                if (tempFile.exists()) {
                } else {
                    File parentFile = tempFile.getParentFile();
                    if (!parentFile.exists()) {
                        parentFile.mkdirs();
                    }
                    tempFile.createNewFile();
                }
                accessFile = new RandomAccessFile(tempFile, "rw");
                int currentLength = (int) accessFile.length();

                Log.i("test", "开始下载 当前文件长度" + currentLength + "  etag" + task.etag);

                connection.setRequestProperty("Range", String.format("bytes=%d-", currentLength));
                if (!TextUtils.isEmpty(task.etag)) {
                    connection.setRequestProperty("If-Match", task.etag);
                }
                if (task.listener != null) {
                    task.listener.start();
                }
                int responseCode = connection.getResponseCode();
                if (responseCode == 200 || responseCode == 206) {


                    if (responseCode == 206) {
                        accessFile.seek(currentLength);
                    }
//						outputStream = new BufferedOutputStream(new FileOutputStream(file));
                    int contentLength = connection.getContentLength();
                    String etag = connection.getHeaderField("Etag");

                    if (!TextUtils.isEmpty(etag) && !etag.equals(task.etag)) {
                        task.etag = etag;
                        putETag(task.downLoadUrl, etag);
                    }
                    Log.i("test", "文件长度" + contentLength / 1024 / 1024 + "   etag" + etag);

                    int totalSize = contentLength + currentLength;

                    int sofar = currentLength;
                    inputStream = connection.getInputStream();
                    byte[] bytes = new byte[4096];
                    int lenght = 0;
                    while ((lenght = inputStream.read(bytes)) != -1) {
//						Log.i("test", "写入" + lenght);
                        accessFile.write(bytes, 0, lenght);
                        sofar += lenght;
                        if (task.isCancel) {
                            return;
                        }
                        if (task.listener != null) {
                            task.listener.progress(sofar, totalSize);
                        }
                    }

                    tempFile.renameTo(downLoadFile);
                    if (task.listener != null) {
                        task.listener.completed();
                        clearEtag(task.downLoadUrl);
                    }
                    Log.i("test", "获取成功");
                } else {

                    String msg = "获取失败" + responseCode;
                    Log.i("test", msg);
                    if (task.listener != null) {
                        task.listener.error(new Exception(msg));
                    }
                }
            } catch (IOException e) {
                Log.i("test", "失败" + e.getMessage());
                if (task.listener != null) {
                    task.listener.error(e);
                }
                e.printStackTrace();
            } finally {
                if (connection != null) {

                    connection.disconnect();
                }
                if (accessFile != null) {
                    try {
                        accessFile.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                hashMap.remove(task.downLoadUrl);
            }
        }
    }

}
