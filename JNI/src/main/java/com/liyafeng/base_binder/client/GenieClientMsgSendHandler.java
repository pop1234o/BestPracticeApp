package com.liyafeng.base_binder.client;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;

import com.liyafeng.base_binder.IGenieService;
import com.liyafeng.base_log.KLog.api.KLog;

import java.lang.ref.WeakReference;

import androidx.annotation.NonNull;

public class GenieClientMsgSendHandler extends Handler {
    private static final String TAG = "GenieClientMsgSendHandler";

    private WeakReference<IGenieService> weakReference = null;

    public GenieClientMsgSendHandler(Looper looper) {
        super(looper);
    }

    @Override
    public void handleMessage(@NonNull Message msg) {
        super.handleMessage(msg);
        if (msg == null || weakReference == null) {
            KLog.e(TAG, "handleMessage | msg == null || weakReference == null");
            return;
        }

        Bundle data = msg.getData();
        String pacakgeName = data.getString("pacakgeName", "");
        String func = data.getString("func", "");
        String params = data.getString("params", "");
        try {
            weakReference.get().fechAsync(pacakgeName, func, params);
        } catch (RemoteException e) {
            KLog.e(TAG, "handleMessage | weakReference.get().fechAsync exception : " + e.getMessage());
        }
    }

    public void setGenieService(IGenieService iGenieService) {
        if (iGenieService != null) {
            weakReference = new WeakReference<>(iGenieService);
        }
    }
}
