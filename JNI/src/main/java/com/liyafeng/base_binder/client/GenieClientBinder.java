package com.liyafeng.base_binder.client;

import android.app.Application;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;

import com.liyafeng.base_binder.IGenieService;
import com.liyafeng.base_binder.IGenieServiceCallback;
import com.liyafeng.base_log.KLog.api.KLog;

import androidx.annotation.NonNull;

public class GenieClientBinder {
    private IGenieService iGenieService;
    private IGenieServiceCallback iGenieServiceCallback;
    private GenieClientMsgSendHandler msgSendHandler;
    private static String TAG = GenieClientBinder.class.getSimpleName();
    private Context context;

    public static GenieClientBinder getInstance() {
        return InstanceHolder.INSTANCE;
    }

    /**
     * 初始化，传applicationContext
     *
     * @param application
     */
    public void initClient(Application application) {
        this.context = application.getApplicationContext();
    }

    private static class InstanceHolder {
        public static final GenieClientBinder INSTANCE = new GenieClientBinder();
    }

    private GenieClientBinder() {
        init();
    }

    private void init() {
        HandlerThread sendThread = new HandlerThread("client-send-thread");
        sendThread.start();
        msgSendHandler = new GenieClientMsgSendHandler(sendThread.getLooper());
    }

    IBinder.DeathRecipient deathRecipient = new IBinder.DeathRecipient() {
        @Override
        public void binderDied() {
            KLog.e(TAG, "####################### aidl server binderDied  ......  #######################  ");
        }
    };

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            iGenieService = IGenieService.Stub.asInterface(iBinder);
            msgSendHandler.setGenieService(iGenieService);
            try {
                iGenieService.asBinder().linkToDeath(deathRecipient, 0);
                iGenieService.register(context.getPackageName(), iGenieServiceCallback);
                KLog.d(TAG, context.getPackageName() + " connect to server");
            } catch (Exception e) {
                e.printStackTrace();
                KLog.e(TAG, "serviceConnect error:" + e);
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            if (iGenieService != null) {
                iGenieService.asBinder().unlinkToDeath(deathRecipient, 0);
            }
            iGenieService = null;
            iGenieServiceCallback = null;
            KLog.d(TAG, context.getPackageName() + " disconnect to server");
        }
    };

    /**
     * 服务绑定
     *
     * @param context
     * @param destPkgName           对应服务所在进程的报名
     * @param action                接收action
     * @param iGenieServiceCallback 回调
     */
    public void bindService(@NonNull Context context, String destPkgName, @NonNull String action, @NonNull IGenieServiceCallback iGenieServiceCallback) {
        this.iGenieServiceCallback = iGenieServiceCallback;
        Intent intent = new Intent(action);
        intent.setPackage(destPkgName);
        boolean bindResult = context.bindService(intent, connection, Context.BIND_AUTO_CREATE);
        KLog.d(TAG, context.getPackageName() + " bind service : " + destPkgName + " result: " + bindResult);
    }

    /**
     * 服务解绑
     *
     * @param context
     * @param destPkgName 对应服务所在进程的报名
     */
    public void unBindService(Context context, String destPkgName) {
        try {
            if (iGenieServiceCallback != null) {
                iGenieService.unRegister(destPkgName, iGenieServiceCallback);
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        context.unbindService(connection);
        iGenieService = null;
        iGenieServiceCallback = null;
        KLog.d(TAG, context.getPackageName() + "unbind service :" + destPkgName);
    }

    /**
     * 异步获取服务端消息
     *
     * @param context
     * @param func    方法名
     * @param params  携带参数
     */
    public void asyncFetch(Context context, @NonNull String func, @NonNull String params) {
        Message message = msgSendHandler.obtainMessage();
        Bundle bundle = new Bundle();
        bundle.putString("pacakgeName", context.getPackageName());
        bundle.putString("func", func);
        bundle.putString("params", params);
        message.setData(bundle);
        msgSendHandler.sendMessage(message);
    }

    /**
     * 同步获取服务端消息
     *
     * @param func 方法名
     * @return 服务返回信息
     */
    public String fetch(@NonNull String func) {
        try {
            if (null != iGenieService) {
                return iGenieService.fetchSync(context.getPackageName(), func);
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 同步获取服务端消息
     *
     * @param func 方法名
     * @return 服务返回信息
     */
    public String fetchWithParams(@NonNull String func, String params) {
        try {
            if (null != iGenieService) {
                return iGenieService.fetchSyncWithParam(context.getPackageName(), func, params);
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return null;
    }
}
