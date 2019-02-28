package com.liyafeng.view.webview;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.Toast;

import com.liyafeng.view.R;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.util.Timer;
import java.util.TimerTask;

public class WebViewActivity extends AppCompatActivity {

    private WebView webview;
    private Timer timer;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (timer != null) {
            timer.cancel();
        }
    }

    /**
     * https://developer.android.google.cn/reference/android/webkit/WebView
     * <p>
     * webview详解
     * https://blog.csdn.net/carson_ho/article/details/52693322
     * <p>
     * 有赞js交互介绍
     * https://segmentfault.com/a/1190000010356403
     * <p>
     * <p>
     * <p>
     * 获取html高度
     * http://stackmirror.caup.cn/page/rhd9k6fnrqcz
     * <p>
     * 如何使得其他控件随着webview一起竖直滑动。
     * match_parent的scrollview->warp_content的LinearLayout->match_parent的webview
     * 但这个时候ide会给警告，这样写会导致一个问题就是，当页面的高度被js改变的时候，webview的高度
     * 还是初始时候的高度，这样就导致会有空白，或者页面显示不全，
     * <p>
     * 忽略警告
     * xmlns:tools="http://schemas.android.com/tools"
     * tools:ignore="WebViewLayout"
     * <p>
     * <p>
     * ============================
     * <p>
     * https://developer.chrome.com/multidevice/webview/overview
     * <p>
     * =============chrome调试webview================
     * （）
     * 更多工具-》开发者工具 （或者按f12）
     * 右面三个点，more tools -》remote devices
     * 就能看到每个应用的网络请求，点击inspect(检查) 需翻墙
     * 就能看到这个网页中的每个请求，包括js css img的请求
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        findViewById(R.id.btn_goto).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText et_text = findViewById(R.id.et_text);
                String url = et_text.getText().toString();
                if (!TextUtils.isEmpty(url)) {
                    webview.loadUrl(url);
                } else {
                    Toast.makeText(WebViewActivity.this, "请输入链接", Toast.LENGTH_SHORT).show();
                }
            }
        });

        findViewById(R.id.btn_start).setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                if (timer != null) {
                    return;
                }
                TimerTask timerTask = new TimerTask() {


                    @Override
                    public void run() {
                        Log.i("test", "run: 调用了");
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                //两种调用js方式，一种loadUrl()；刷新页面?无法获取返回值， 第二种 evaluateJavascript 高效，可以有返回值
                                webview.evaluateJavascript("javascript:aaa('" + System.currentTimeMillis() + "')", new ValueCallback<String>() {
                                    @Override
                                    public void onReceiveValue(String value) {//js返回的方法

                                    }
                                });
                            }
                        });

                    }
                };

                timer = new Timer();

                timer.scheduleAtFixedRate(timerTask, 0, 1000);

            }
        });

        initView();
    }


    @Override
    protected void onStart() {
        super.onStart();
        webview.evaluateJavascript("javascript:goForground()", new ValueCallback<String>() {
            @Override
            public void onReceiveValue(String value) {//js返回的方法

            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        webview.evaluateJavascript("javascript:goBackground()", new ValueCallback<String>() {
            @Override
            public void onReceiveValue(String value) {//js返回的方法

            }
        });
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void initView() {
        webview = (WebView) findViewById(R.id.webview);
        WebSettings settings = webview.getSettings();
        settings.setJavaScriptEnabled(true);

        //允许解决跨域访问
        settings.setAllowUniversalAccessFromFileURLs(true);
        //允许加载本地文件
        settings.setAllowFileAccessFromFileURLs(true);
        //适配网页，宽度
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        //支持缩放，两个要都写上
        settings.setSupportZoom(true); // 可以缩放
        settings.setBuiltInZoomControls(true);
        //不显示webview缩放按钮
        settings.setDisplayZoomControls(false);


        /*
        *  webView.settings.domStorageEnabled = true
        webView.settings.databaseEnabled = true
        webView.settings.allowFileAccess = true

        webView.settings.setAppCacheEnabled(true)
        webView.settings.setAppCachePath(cacheDir.absolutePath)

        webView.settings.javaScriptEnabled = true
        *
        * */

//        从Android5.0开始，WebView默认不支持同时加载Https和Http混合模式。如何解决呢：
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            settings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }

        webview.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int progress) {
                // Activities and WebViews measure progress with different scales.
                // The progress meter will automatically disappear when we reach 100%
                Log.i("test", "progress" + progress);
            }

            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
            }
        });

        webview.setWebViewClient(new WebViewClient() {
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
//                Toast.makeText(activity, "Oh no! " + description, Toast.LENGTH_SHORT).show();
            }


            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return super.shouldOverrideUrlLoading(view, request);
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);

                Log.i("test", "width" + view.getWidth() + "  " + view.getContentHeight() + "  " + view.getHeight());
            }
        });


//        webview.loadUrl("http://192.168.1.131:7876/dist/tod/");

//        webview.loadUrl("http://webassembly.org.cn/demo/Tanks/");
//        File file = new File(this.getExternalFilesDir(null), "roateCube1/index.html");
//        try {
//            file.createNewFile();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        URI uri = file.toURI();
//        try {
//            webview.loadUrl(uri.toURL().toString());
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }

        webview.setBackgroundColor(Color.TRANSPARENT);
    }


}
