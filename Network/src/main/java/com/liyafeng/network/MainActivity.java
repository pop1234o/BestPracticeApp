package com.liyafeng.network;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * HttpURLConnection
 * httpclient
 * SimpleVolley
 * Okhttp
 * Retrofit
 */
public class MainActivity extends AppCompatActivity {

    public static final String TAG = "test";
    private RequestQueue requestQueue;
    private OkHttpClient okHttpClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestOkHttpGet();
            }
        });


    }

    private void requestHttpUrlConnection() {
        new Thread(new Runnable() {
            @Override
            public void run() {

                InputStream inputStream = null;
                try {
                    URL url = new URL("http://www.google.com");
                    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

                    urlConnection.setConnectTimeout(1000);
                    int responseCode = urlConnection.getResponseCode();
                    if (responseCode == 200) {
                        inputStream = urlConnection.getInputStream();

                        ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
                        byte[] bytes = new byte[1024];

                        int length;
                        while ((length = inputStream.read(bytes)) != -1) {
                            arrayOutputStream.write(bytes, 0, length);
                        }

                        String s = new String(arrayOutputStream.toByteArray());

                        Log.i(TAG, "requestHttpUrlConnection: " + s);

                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (inputStream != null) {

                            inputStream.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

    }

    /**
     * （主线程）创建request ,加入请求队列
     * <p>
     * -》（子线程cache）CacheDispatcher 获取到这个request，查看有无缓存
     * ——》有缓存，read response and parse ，返回response对象
     * --》无缓存，加入NetWorkDispatcher
     * -》（子线程network）NetWorkDispatcher调用HttpURLConnection请求
     * -》返回响应，解析（jsonobject/Bitmap/string），写入缓存（如果设置了）
     * -》创建response对象，返回到主线程
     * =======================
     * 当然我们可以对这里的请求进行封装，用单例
     * 配置cache大小
     * 配置httpclient对象
     */
    private void requestVolley() {
        //一般只创建一个RequestQueue，里面对应了一个缓存线程和多个网络请求线程
        requestQueue = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest("http://www.liyafeng.com", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.i(TAG, "onResponse: " + response);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i(TAG, "onResponse: " + error.getMessage());
            }
        });

        stringRequest.setTag("tag1");

        requestQueue.add(stringRequest);
    }

    /**
     * 自己配置请求client
     */
    private void requestVolley2() {
        RequestQueue mRequestQueue;

// Instantiate the cache
        Cache cache = new DiskBasedCache(getCacheDir(), 1024 * 1024); // 1MB cap

// Set up the network to use HttpURLConnection as the HTTP client.
        Network network = new BasicNetwork(new HurlStack());

// Instantiate the RequestQueue with the cache and network.
        mRequestQueue = new RequestQueue(cache, network);

// Start the queue
        mRequestQueue.start();

        String url = "http://www.example.com";


// Formulate the request and handle the response.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Do something with the response
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle error
                    }
                });

// Add the request to the RequestQueue.
        mRequestQueue.add(stringRequest);

    }


    /**
     * ==============================
     * 先用Builder模式来构建Request对象，然后将Request封装成一个Call（RealCall）对象
     * 调用RealCall.execute。里面使用OkHttpClient.Dispatcher.execute(call)
     * 里面将call加入Dispatcher中ArrayDeque中，然后再 RealCall.getResponseWithInterceptorChain()
     * 中创建各种拦截器，（
     * BridgeInterceptor 添加请求头，比如User-Agent，Connection，Host
     * CacheInterceptor请求头缓存设置，
     * ConnectInterceptor获取http连接，判断是1.0还是1.1，
     * 获取到HttpCodec对象，真正建立起了Socket连接
     * CallServerInterceptor 将请求行，请求头写入Socket,然后发送到服务端，接收Socket响应
     * 这里用的是BufferedSource 是okio，NIO，然后获取到http响应，解析响应行，响应头，响应体
     * 用ResponseBuilder来创建一个Response，然后返回
     * ）
     * 以上是一个同步的过程，异步的过程类似，就是创建一个AsyncCall然后用线程池执行
     * 返回Response后调用回调方法回调都是在子线程。
     * =================================
     * 他的巧妙的结构在于 ,链式的调用结构
     * Response r = chain.proceed(request)；
     * <p>
     * Interceptor=>
     * Response intercept(Chain chain){
     * //处理Request
     * Response r = chain.proceed(chain.request());
     * //处理Response
     * return r;
     * }
     * <p>
     * Chain=>中方法
     * Response proceed(Request request){
     * //这里主要遍历取出拦截器
     * Response r = interceptors.get(index++).intercept(new Chain());
     * return r;
     * }
     * <p>
     * <p>
     * ==============================
     * 使用builder模式创建Request,OkHttpClient
     * 使用工厂模式创建 EventListener
     * 策略模式，设置拦截器
     */
    private void requestOkHttpGet() {
        new Thread(new Runnable() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void run() {
                if (okHttpClient == null) {
                    okHttpClient = new OkHttpClient();
                }
                okhttp3.Request.Builder builder = new okhttp3.Request.Builder();
                okhttp3.Request request = builder.url("http://www.google.com").build();
                //必须在这定义 //  try-with-resources
                okhttp3.Call call = okHttpClient.newCall(request);
                try (okhttp3.Response response = call.execute()) {//  try-with-resources
                    //回调形式
//                    okHttpClient.newCall(request).enqueue(new Callback() {
//                        @Override
//                        public void onFailure(okhttp3.Call call, IOException e) {
//
//                        }
//
//                        @Override
//                        public void onResponse(okhttp3.Call call, okhttp3.Response response) throws IOException {
//
//                        }
//                    });
                    String s = response.body().string();//注意这里是string()不是toString();
                    Log.i(TAG, "requestOkHttpGet: " + s);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();


//        new okhttp3.Response.Builder()
//                .request(chain.request())
//                .protocol(Protocol.HTTP_1_1)
//                .code(504)
//                .message("Unsatisfiable Request (only-if-cached)")
//                .body(Util.EMPTY_RESPONSE)
//                .sentRequestAtMillis(-1L)
//                .receivedResponseAtMillis(System.currentTimeMillis())
//                .build();
    }


    private void requestOkHttpPost() {
        final MediaType JSON = MediaType.parse("application/json;charset=utf-8");
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (okHttpClient == null) {
                    okHttpClient = new OkHttpClient();
                }
                okhttp3.Request.Builder builder = new okhttp3.Request.Builder();
                RequestBody requestBody = RequestBody.create(JSON, "{\"key\":\"value\"}");
                okhttp3.Request request = builder.url("http://www.google.com").post(requestBody).build();
                okhttp3.Response response = null;
                try {
                    response = okHttpClient.newCall(request).execute();
                    String s = response.body().string();//注意这里是string()不是toString();
                    Log.i(TAG, "requestOkHttpPost: " + s);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    /**
     * 缓存
     */
    private void requestOkHttp() {
        new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                okhttp3.Request request = chain.request();
//                chain.request().newBuilder().addHeader().build()
                okhttp3.Response response = chain.proceed(request);
                return response;
            }
        }).cache(new okhttp3.Cache(getCacheDir(), 5 * 1024 * 1024)).build();

        okhttp3.Request request_forceNocache = new okhttp3.Request.Builder().cacheControl(new CacheControl.Builder().noCache().build()).url("").build();
        okhttp3.Request request_forceCache = new okhttp3.Request.Builder().cacheControl(new CacheControl.Builder().maxAge(0, TimeUnit.SECONDS).build()).url("").build();

    }


    /**
     * ======转换器============
     * 默认情况，retrofit只能反序列化http响应到okhttp的ResponseBody中
     * 而且请求的@Body注解只能接收okhttp的RequestBody，来序列化成http请求
     * <p>
     * =============结合rxjava=============
     * https://github.com/square/retrofit/tree/master/retrofit-adapters/rxjava2
     * <p>
     * implementation 'com.squareup.retrofit2:adapter-rxjava2:latest.version'
     * <p>
     * RxJava2CallAdapterFactory.createAsync()
     * RxJava2CallAdapterFactory.create()
     * 区别在于 createAsync使用call.enqueue方法来使用okhttp内部的线程池
     * 而create使用的是rxjava自己的线程池（我们通过代码指定的线程）
     * <p>
     * <p>
     * ==============okhttp回调线程问题=========
     * 默认的ok都是子线程回调 enqueue代表的是可以在主线程调用，但是回调还是在子线程
     * <p>
     * 加入retrofit，用默认的ExecutorCallAdapterFactory enqueue是在主线程回调
     * 而如果使用RxJava2CallAdapterFactory，就又会在子线程回调
     */
    private void requestRetrofit() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://www.baidu.com").addConverterFactory(GsonConverterFactory.create()).build();
        RequestService requestService = retrofit.create(RequestService.class);
        //这里response的body就直接转换为User对象
        final Call<RequestService.User> call = requestService.getUser("myname", "mypassword");


        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    RequestService.User body = call.execute().body();
                    Log.i(TAG, "requestRetrofit: " + body.toString());
                } catch (IOException e) {
                    Log.i(TAG, "requestRetrofit: " + e.getMessage());
                    e.printStackTrace();
                }
            }
        }).start();


        Call<ResponseBody> bodyCall = retrofit.create(RequestService1.class).getUser("", "");
        bodyCall.enqueue(new retrofit2.Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                //retrofit2.Response就是原始的http响应
                // 这里就是讲http的响应体默认转换为ResponseBody
                ResponseBody body = response.body();

                try {
                    //将body内容转换为string
                    String string = body.string();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });

    }

    public interface RequestService {

        @Headers({"Cache-Control:max-age=64000"})
        @GET("user/{name}/repo?age=18")
        Call<User> getUser(@Path("name") String username, @Query("password") String password);

        class User {

        }
    }

    public interface RequestService1 {

        @GET("user/{name}/repo?age=18")
        Call<ResponseBody> getUser(@Path("name") String username, @Query("password") String password);


    }


    @Override
    protected void onStop() {
        super.onStop();
        if (requestQueue != null) {
            requestQueue.cancelAll("tag1");
        }
    }
}
