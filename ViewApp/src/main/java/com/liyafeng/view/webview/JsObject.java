package com.liyafeng.view.webview;

import android.util.Log;
import android.webkit.JavascriptInterface;

public class JsObject {

    @JavascriptInterface
    public void execute(String msg) {
        Log.i("test", "调用了execute" + msg);
    }
}
