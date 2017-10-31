package com.liyafeng.network;

import android.os.Bundle;
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

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * HttpURLConnection
 * httpclient
 * Volley
 * Okhttp
 * Retrofit
 */
public class MainActivity extends AppCompatActivity {

    public static final String TAG = "test";
    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestVolley();
            }
        });
//        request();

    }

    private void request() {
        try {
            URL url = new URL("");
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            urlConnection.setConnectTimeout(10000);
            int responseCode = urlConnection.getResponseCode();
            if (responseCode == 200) {
                InputStream inputStream = urlConnection.getInputStream();
//                inputStream.read()
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    @Override
    protected void onStop() {
        super.onStop();
        requestQueue.cancelAll("tag1");
    }
}
