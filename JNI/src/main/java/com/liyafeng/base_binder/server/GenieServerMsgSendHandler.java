package com.liyafeng.base_binder.server;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;

import com.liyafeng.base_binder.service.GenieService;
import com.liyafeng.base_log.KLog.api.KLog;

import java.lang.ref.WeakReference;

import androidx.annotation.NonNull;

public class GenieServerMsgSendHandler extends Handler {
    private static final String TAG = GenieServerMsgSendHandler.class.getSimpleName();

    private final WeakReference<GenieService> weakReference;

    public GenieServerMsgSendHandler(Looper looper, GenieService service) {
        super(looper);
        weakReference = new WeakReference<>(service);
    }

    @Override
    public void handleMessage(@NonNull Message msg) {
        super.handleMessage(msg);
        if (msg == null) {
            KLog.d(TAG, "handleMessage() | msg null");
            return;
        }
        Bundle bundle = msg.getData();
        if (bundle == null) {
            KLog.d(TAG, "handleMessage() | bundle null");
            return;
        }

        String pkgName = bundle.getString("packageName", "");
        String func = bundle.getString("func", "");
        int code = bundle.getInt("code", 0);
        String params = bundle.getString("params", "");
        if (TextUtils.isEmpty(func) || TextUtils.isEmpty(pkgName)) {
            KLog.e(TAG, "handleMessage() | TextUtils.isEmpty(func) || TextUtils.isEmpty(pkgName) ");
            return;
        }

        try {
            GenieService callback = weakReference.get();
            if (callback != null) {
                callback.dispatchMessage(pkgName, func, code, params);
            } else {
                KLog.e(TAG, "handleMessage() | callback == null ");
            }
        } catch (Exception e) {
            KLog.e(TAG, "handleMessage() | exception:" + e);
        }
    }
}
