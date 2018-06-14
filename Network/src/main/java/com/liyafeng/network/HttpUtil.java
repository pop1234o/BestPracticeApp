package com.liyafeng.network;

import android.os.Handler;
import android.os.Looper;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HttpUtil {


    private volatile static HttpUtil instance;
    private final Handler handler;
    private Retrofit retrofit;
    private BaoGuanService baoGuanService;
    private ExecutorService executorService;


    private HttpUtil() {
        initRetrofit();
        executorService = Executors.newCachedThreadPool();
        handler = new Handler(Looper.getMainLooper());
    }

    private void initRetrofit() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(20_000, TimeUnit.MILLISECONDS)
                .addNetworkInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request();


//                        if (BaoGuanApplication.isDebug) {
//
//                            LogUtil.i("test", "请求===" + request.toString());
//                        }
                        Response response = chain.proceed(request);


//                        Response build1 = new Response.Builder()
//                                .request(chain.request())
//                                .protocol(Protocol.HTTP_1_1)
//                                .code(response.code())
//                                .message(response.message())
//                                .body(response.body())
//                                .receivedResponseAtMillis(System.currentTimeMillis())
//                                .build();

//                        if (BaoGuanApplication.isDebug) {
//                            ResponseBody body = response.body();
//                            String string = body.string();
//                            LogUtil.i("test", "响应===" + string);
//                            Response response_new = response.newBuilder().body(ResponseBody.create(body.contentType(), string)).build();
//                            return response_new;
//                        }
                        return response;
                    }
                })
                .build();

        Gson gson = new GsonBuilder()
//                .serializeNulls()
//                .registerTypeAdapter(ResponseListEntity.class, new ResponseListEntityDeserializer())
                .create();

        retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("http://dev-view.jiaoyinhudong.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        baoGuanService = retrofit.create(BaoGuanService.class);


    }

    private static class Holder {
        private static HttpUtil holder = new HttpUtil();
    }

    public static HttpUtil getInstance() {
        return Holder.holder;
    }

    public static BaoGuanService getService() {
        return getInstance().baoGuanService;
    }


    public void getHttp(final String urlstr, final OnHttpCallback onHttpCallback) {
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                try {
                    URL url = new URL(urlstr);
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setConnectTimeout(10000);

                    final int responseCode = connection.getResponseCode();
                    if (responseCode == 200) {
                        InputStream inputStream = connection.getInputStream();

                        byte[] bytes = new byte[1024];
                        int length = 0;

                        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                        while ((length = inputStream.read(bytes)) != -1) {
                            outputStream.write(bytes, 0, length);
                        }

                        final String s = outputStream.toString();
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                onHttpCallback.onSuccess(s);

                            }
                        });

                    } else {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                onHttpCallback.onError("获取token失败：" + responseCode);
                            }
                        });
                    }
                } catch (final IOException e) {
                    e.printStackTrace();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            onHttpCallback.onError(e.getMessage());
                        }
                    });
                } finally {

                    if (connection != null) {
                        connection.disconnect();
                    }
                }
            }
        });

    }


    public interface OnHttpCallback {
        void onSuccess(String response);

        void onError(String error);
    }
}
