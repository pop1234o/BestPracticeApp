
import android.util.Base64;


import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Random;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class FileUploadUtil {


    private static final String CRLF = "\r\n";


    /**
     *
     *
     *
     ** http格式：
     *
     *  POST http://test2.aibrandy.com/api-manager/upload/uploadFile http/1.1
     *  Content-Type: multipart/form-data; boundary=be5b4fe6-a570-49e4-9380-4515cdf87483
     *  【换行】
     *  --be5b4fe6-a570-49e4-9380-4515cdf87483
     *  Content-Disposition: form-data; name="file"; filename="melo.log"  //这个name其实就是key，如果是文件上传就加个filename
     *  Content-Type: multipart/form-data
     *  Content-Length: 564561 [这个可以不要]
     *  【换行】
     *  【文件内容】
     *  --be5b4fe6-a570-49e4-9380-4515cdf87483
     *  Content-Disposition: form-data; name="key"
     *  【换行】
     *  value
     *  --be5b4fe6-a570-49e4-9380-4515cdf87483--
     *
     *
     *
     *
     *
     */

    public static void upLoadFile(final File file, final UploadCallback callback) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String urlStr = EHttp.getInstance().getBaseUrl() + "api-manager/upload/uploadFile";
                FileInputStream fileInput = null;
                BufferedInputStream inputStream = null;
                DataOutputStream requestStream = null;
                HttpURLConnection urlConnection = null;
                try {
                    URL url = new URL(urlStr);
                    urlConnection = (HttpURLConnection) url.openConnection();


                    // 创建边界
                    Random random = new Random();
                    byte[] randomBytes = new byte[16];
                    random.nextBytes(randomBytes);
                    String boundary = Base64.encodeToString(randomBytes, Base64.NO_WRAP);

                    //设置属性
                    urlConnection.setDoOutput(true);
                    urlConnection.setDoInput(true);
                    urlConnection.setUseCaches(false);
                    urlConnection.setRequestMethod("POST");


                    // 设置请求头
                    urlConnection.setRequestProperty("Connection", "Keep-Alive");
                    urlConnection.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);
                    urlConnection.setRequestProperty("Cache-Control", "no-cache");
                    urlConnection.setRequestProperty("token", SPUtil.getToken());

                    //设置请求体
                    requestStream = new DataOutputStream(urlConnection.getOutputStream());
                    String nikeName = "file";
                    requestStream.writeBytes("--" + boundary + CRLF);
                    requestStream.writeBytes("Content-Disposition: form-data; name=\"" + nikeName + "\"; filename=\"" + file.getName() + "\"" + CRLF);
                    requestStream.writeBytes("Content-Type: multipart/form-data" + CRLF);

                    requestStream.writeBytes(CRLF);

                    fileInput = new FileInputStream(file);
                    byte[] bytes = new byte[1024];
                    int length = 0;
                    while ((length = fileInput.read(bytes)) != -1) {
                        requestStream.write(bytes, 0, length);
                        LogUtil.i("test", "上传中" + length);
                    }
                    requestStream.flush();
                    requestStream.writeBytes(CRLF);
                    requestStream.flush();
                    requestStream.writeBytes("--" + boundary + "--" + CRLF);

                    requestStream.flush();
                    fileInput.close();

                    int responseCode = urlConnection.getResponseCode();
                    if (responseCode == 200) {//读取响应
                        inputStream = new BufferedInputStream(urlConnection.getInputStream());
                        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                        while ((length = inputStream.read(bytes)) != -1) {
                            outputStream.write(bytes, 0, length);
                        }
                        String result = outputStream.toString();
                        LogUtil.i("test", "res:" + result);
                        callback.onSuccess();
                    } else {//失败
                        callback.onFail("响应失败" + responseCode);
                    }

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                    callback.onFail(e.getMessage());
                } catch (IOException e) {
                    e.printStackTrace();
                    callback.onFail(e.getMessage());
                } catch (Exception e) {
                    e.printStackTrace();
                    callback.onFail(e.getMessage());
                } finally {
                    if (fileInput != null) {
                        try {
                            fileInput.close();
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

                    if (requestStream != null) {
                        try {
                            requestStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (urlConnection != null) {
                        urlConnection.disconnect();
                    }
                }
            }
        }).start();

    }


    public interface UploadCallback {
        void onSuccess();

        void onFail(String msg);

        void onProgress(float percent);// 0-1的数

    }


    public static void upLoad(File file, final UploadCallback callback) {
        if (file.exists()) {
            RequestBody body = RequestBody.create(MediaType.parse("multipart/form-data"), file);
//
//            MultipartBody.Builder builder = new MultipartBody.Builder()
//                    .setType(MultipartBody.FORM);
//            builder.addFormDataPart("file", file.getName(), body);//imgfile 后台接收图片流的参数名
//
//            MultipartBody multipartBody = builder.build();
//

            MultipartBody.Part imageBodyPart = MultipartBody.Part.createFormData("file", file.getName(), body);
            DataRepository.getInstance().upLoad(imageBodyPart, new DataCallback<UpLoadFileEntity>() {
                @Override
                public void onFailure(String failMsg) {
                    callback.onFail(failMsg);
                }

                @Override
                public void onSuccess(UpLoadFileEntity result) {
                    callback.onSuccess();
                }
    });


            /*
            *    @Multipart
            *     @POST("api-manager/upload/uploadFile")
            *   Observable<HttpResult<UpLoadFileEntity>> upLoad(@Part MultipartBody.Part body);
            *
            * */
        }
    }
}
