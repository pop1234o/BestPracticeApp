package com.liyafeng.view.webview;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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
     *
     * webview详解
     * https://blog.csdn.net/carson_ho/article/details/52693322
     *
     * 有赞js交互介绍
     * https://segmentfault.com/a/1190000010356403
     *
     *
     *
     * 获取html高度
     * http://stackmirror.caup.cn/page/rhd9k6fnrqcz
     *
     * 如何使得其他控件随着webview一起竖直滑动。
     * match_parent的scrollview->warp_content的LinearLayout->match_parent的webview
     *
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);


    }

    private void initView() {
        webview =  findViewById(R.id.webview);
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

        webview.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int progress) {
                // Activities and WebViews measure progress with different scales.
                // The progress meter will automatically disappear when we reach 100%
                Log.i("test","progress"+progress);
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

                Log.i("test","width"+view.getWidth()+"  "+view.getContentHeight()+"  "+view.getHeight());
            }
        });

        webview.loadUrl("https://developer.android.google.cn/");

    }
}
