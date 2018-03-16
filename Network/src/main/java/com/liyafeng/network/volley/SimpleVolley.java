package com.liyafeng.network.volley;

import android.os.Handler;
import android.os.Looper;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * Created by liyafeng on 29/10/2017.
 *
 *
 * volley属于一个轻量级的缓存框架，然后。他首先把请求加入缓存优先队列，然后cache线程去查有没有缓存这个请求，
 * 如果命中的话。那么他直接返回到主线程。
 * 如果没有命中，然后他会加入网络请求队列。
 * 然后网络请求的线程用httpurlconnection去请求网络获取response之后。解析响应，然后封装成响应对象。
 * 然后再分发给主线程
 *
 */

public class SimpleVolley {

    public static RequestQueue newRequestQueue() {
        NetWork.UrlNetWork netWork = new NetWork.UrlNetWork();
        Cache.DiskBasedCache cache = new Cache.DiskBasedCache();
        RequestQueue requestQueue = new RequestQueue(netWork, cache);

        return requestQueue;
    }


    static class RequestQueue {
        NetWork netWork;
        private Cache cache;
        CacheDispatcher cacheDispatcher;
        NetWorkDispatcher[] netWorkDispatchers = new NetWorkDispatcher[4];
        PriorityBlockingQueue<Request<?>> mCacheQueue = new PriorityBlockingQueue<>();
        PriorityBlockingQueue<Request<?>> mNetworkQueue = new PriorityBlockingQueue<>();


        public RequestQueue(NetWork netWork, Cache cache) {
            this.netWork = netWork;
            this.cache = cache;
        }

        public void start() {
            cacheDispatcher = new CacheDispatcher(mCacheQueue, cache, mNetworkQueue);
            cacheDispatcher.start();

            for (int i = 0; i < netWorkDispatchers.length; i++) {
                NetWorkDispatcher netWorkDispatcher = new NetWorkDispatcher(mNetworkQueue, netWork);
                netWorkDispatchers[i] = netWorkDispatcher;
                netWorkDispatcher.start();
            }

        }

        public void add(Request request) {
            mCacheQueue.add(request);
        }


    }

    static class CacheDispatcher extends Thread {

        private PriorityBlockingQueue<Request<?>> mCacheQueue;
        private Cache cache;
        private PriorityBlockingQueue<Request<?>> mNetworkQueue;


        public CacheDispatcher(PriorityBlockingQueue<Request<?>> mCacheQueue, Cache cache, PriorityBlockingQueue<Request<?>> mNetworkQueue) {
            this.mCacheQueue = mCacheQueue;
            this.cache = cache;
            this.mNetworkQueue = mNetworkQueue;
        }

        @Override
        public void run() {
            cache.initialize();
            while (true) {
                try {
                    Request<?> request = mCacheQueue.take();

                    NetWorkResponse response = cache.get(request.getUrl());

                    if (response == null) {//miss
                        mNetworkQueue.add(request);
                        continue;
                    }
                    //hit
                    request.parseNetWorkResponse(response);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }

        }
    }

    static class NetWorkDispatcher extends Thread {
        private PriorityBlockingQueue<Request<?>> mNetworkQueue;
        private NetWork netWork;

        public NetWorkDispatcher(PriorityBlockingQueue<Request<?>> mNetworkQueue, NetWork netWork) {
            this.mNetworkQueue = mNetworkQueue;
            this.netWork = netWork;
        }

        @Override
        public void run() {

            while (true) {
                try {
                    Request<?> take = mNetworkQueue.take();

                    Response response = netWork.performRequest(take);


                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class ResponseDelivery{

        private final Handler handler;

        public ResponseDelivery() {
            handler = new Handler(Looper.getMainLooper());
        }

        public void postResponse(Request<?> request,Response<?> response){
            handler.post(new DeliveryRunnable(request,response));
        }

        class DeliveryRunnable implements Runnable{

            private final Request request;
            private final Response response;

            public DeliveryRunnable(Request request, Response response) {

                this.request = request;
                this.response = response;
            }

            @SuppressWarnings("unchecked")
            @Override
            public void run() {
                request.onResponse(response.result);
            }
        }
    }

    static interface NetWork {
        Response performRequest(Request request);

        static class UrlNetWork implements NetWork {
            private PoolingByteArrayOutputStream.ByteArrayPool byteArrayPool = new PoolingByteArrayOutputStream.ByteArrayPool();

            @Override
            public Response performRequest(Request request) {
                String urlStr = request.getUrl();
                HttpURLConnection urlConnection = null;
                InputStream inputStream = null;
                PoolingByteArrayOutputStream outputStream = null;
                try {
                    URL url = new URL(urlStr);
                    urlConnection = (HttpURLConnection) url.openConnection();
                    urlConnection.setConnectTimeout(10000);
                    urlConnection.setDoInput(true);
                    int responseCode = urlConnection.getResponseCode();
                    if (responseCode == 200) {
                        inputStream = urlConnection.getInputStream();
                        outputStream = new PoolingByteArrayOutputStream(byteArrayPool);

                        byte[] bytes = new byte[1024];
                        int length;
                        while ((length = inputStream.read(bytes)) != -1) {
                            outputStream.write(bytes, 0, length);
                        }

                        NetWorkResponse workResponse = new NetWorkResponse();
                        workResponse.data = outputStream.toByteArray();

                        Response response = request.parseNetWorkResponse(workResponse);

                        return response;
                    } else {
                        request.onErrorResponse("resCode = " + responseCode);
                    }
                } catch (IOException e) {
                    request.onErrorResponse(e.getMessage());
                    e.printStackTrace();
                } finally {
                    try {
                        inputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        outputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                return null;
            }
        }
    }

    interface Cache {

        void initialize();

        NetWorkResponse get(String request);

        /**
         * 5M
         */
        static class DiskBasedCache implements Cache {

            @Override
            public void initialize() {
                //读到内存中
            }

            @Override
            public NetWorkResponse get(String request) {
                return null;
            }
        }

        class CacheEntry {
            Response response;
        }

    }


    /**
     * 收取response 的byte[] 复用池
     */
    static class PoolingByteArrayOutputStream extends ByteArrayOutputStream {
        private ByteArrayPool byteArrayPool;

        public PoolingByteArrayOutputStream(ByteArrayPool byteArrayPool) {

            this.byteArrayPool = byteArrayPool;
        }

        /**
         * 这里如果用系统的容量拓展，很浪费
         * 因为它总是新建一个两倍大小的数组，然后copy过去，
         * 这里我们自己写的复用池，可以省去创建的耗时操作
         *
         * @param length
         */
        public void expand(int length) {
            if (count + length <= buf.length) {
                return;
            }
            byte[] newBuf = byteArrayPool.getBuf((count + length) * 2);
            System.arraycopy(buf, 0, newBuf, 0, count);
            byteArrayPool.returnBuf(buf);
            buf = newBuf;

        }

        public void write(byte[] data, int offset, int length) {
            expand(length);
            super.write(data, offset, length);
        }


        /**
         * 完成了一个lrucache 的byte[]库
         */
        static class ByteArrayPool {

            List<byte[]> arrayList = new ArrayList<>(64);

            //这个是实现超出上限移出最久未使用的byte[]对象
            List<byte[]> lastUseList = new LinkedList<>();

            int sizeLimit = 4096;//缓存总大小不能超过
            int currentSize;
            private Comparator<byte[]> comparator = new Comparator<byte[]>() {
                @Override
                public int compare(byte[] o1, byte[] o2) {
                    return o1.length - o2.length;//从小到大排序
                }
            };

            /**
             * @param len 获取长度为len的byte数组
             * @return
             */
            public byte[] getBuf(int len) {

                for (int j = 0; j < arrayList.size(); j++) {
                    if (arrayList.get(j).length >= len) {
                        byte[] remove = arrayList.remove(j);
                        currentSize -= remove.length;
                        lastUseList.remove(remove);
                        return remove;
                    }
                }
                return new byte[len];
            }

            public void returnBuf(byte[] buf) {
                lastUseList.add(buf);

                int pos = Collections.binarySearch(arrayList, buf, comparator);
                if (pos < 0) {
                    pos = -pos - 1;
                }
                arrayList.add(pos, buf);
                currentSize += buf.length;
                trim();
            }

            /**
             * 修剪 多出的内存
             */
            private void trim() {
                if (currentSize > sizeLimit) {
                    byte[] remove = lastUseList.remove(0);
                    arrayList.remove(remove);
                    currentSize -= remove.length;
                }
            }
        }

    }

    static abstract class Request<T> {

        private String url;

        public String getUrl() {
            return url;
        }

        public Request(String url) {
            this.url = url;
        }

        public abstract void onResponse(T response);

        public abstract void onErrorResponse(String error);

        public abstract Response<T> parseNetWorkResponse(NetWorkResponse response);

        static abstract class StringRequest extends Request<String> {

            public StringRequest(String url) {
                super(url);
            }

            public Response<String> parseNetWorkResponse(NetWorkResponse response) {
                String s = new String(response.data);

                return new Response<>(s);
            }
        }


    }

    static class NetWorkResponse {

        byte[] data;

    }


    static class Response<T>{
        T result;

        public Response(T result) {
            this.result = result;
        }
    }
}
