package com.liyafeng.view.webview;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.liyafeng.view.R;

public class WebViewActivity extends Activity {

    private WebView webview;

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
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);


    }

    private void initView() {
        webview = (WebView) findViewById(R.id.webview);
        WebSettings settings = webview.getSettings();
        settings.setJavaScriptEnabled(true);


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

        webview.loadUrl("https://developer.android.google.cn/");

    }



}
