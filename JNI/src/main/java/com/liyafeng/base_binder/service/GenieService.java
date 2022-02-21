package com.liyafeng.base_binder.service;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.text.TextUtils;

import com.liyafeng.base_binder.IGenieService;
import com.liyafeng.base_binder.IGenieServiceCallback;
import com.liyafeng.base_binder.server.GenieServerMsgSendHandler;
import com.liyafeng.base_log.KLog.api.KLog;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class GenieService extends Service {
    private static final String TAG = "GenieService";

    /**
     * RemoteCallbackList 是系统专门提供的用于删除系统跨进程 listener 的接口
     */
    private RemoteCallbackList<IGenieServiceCallback> mClientCallbacks;
    private GenieServerMsgSendHandler mSendHandler;
    private IClientMessageReceiver messageReceiver;

    public static GenieService instance;

    @Override
    public void onCreate() {
        super.onCreate();
        GenieService.instance = this;
        initialize();
        KLog.d(TAG, "onCreate");
    }

    private void initialize() {
        mClientCallbacks = new RemoteCallbackList<>();
        initSendHandler();
    }

    private void initSendHandler() {
        HandlerThread sendThread = new HandlerThread("genieServiceSendThread");
        sendThread.start();
        mSendHandler = new GenieServerMsgSendHandler(sendThread.getLooper(), this);
    }

    public void registReceiver(IClientMessageReceiver receiver) {
        KLog.d(TAG,"registReceiver");
        this.messageReceiver = receiver;
    }

    public void unRegistReceiver() {
        KLog.d(TAG,"unRegistReceiver");
        messageReceiver = null;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return stub;
    }

    IGenieService.Stub stub = new IGenieService.Stub() {
        @Override
        public void register(final String packageName, IGenieServiceCallback callback) throws RemoteException {
            KLog.d(TAG, "===>client register: " + packageName);
            if (packageName == null || callback == null) {
                KLog.e(TAG, "register() | ageName == null || callback == null");
                return;
            }

            callback.asBinder().linkToDeath(new ClientRecipient(packageName) {
                @Override
                public void binderDied() {
                    super.binderDied();

                    KLog.e(TAG, "####################### aidl client  binderDied ......  #######################  " + packageName);
                    unbindBinder(this);
                }
            }, 0);

            if (mClientCallbacks != null && callback != null) {
                mClientCallbacks.register(callback);
            } else {
                KLog.e(TAG, "register() | mClientCallbacks == null ");
            }
        }

        @Override
        public void unRegister(String packageName, IGenieServiceCallback callback) throws RemoteException {
            if (packageName == null || callback == null) {
                KLog.e(TAG, "unRegister() | packageName == null || callback == null ");
                return;
            }

            if (mClientCallbacks != null && callback != null) {
                mClientCallbacks.unregister(callback);
            } else {
                KLog.e(TAG, "unRegister() | mClientCallbacks == null ");
            }
        }

        @Override
        public void fechAsync(String pacakgeName, String func, String params) throws RemoteException {
            KLog.d(TAG,"fechAsync: pkg->" + pacakgeName + " func->" +func + " params->" + params);
            if (TextUtils.isEmpty(func) || TextUtils.isEmpty(pacakgeName)) {
                KLog.e(TAG, "fechAsync() | TextUtils.isEmpty(func) || TextUtils.isEmpty(pacakgeName) ");
                return;
            }
            if (messageReceiver != null) {
                messageReceiver.fechAsync(pacakgeName,func,params);
            }
        }

        @Override
        public String fetchSync(String pacakgeName, String func) throws RemoteException {
            KLog.d(TAG,"fetchSync: pkg->" + pacakgeName + " func->" +func);
            if (TextUtils.isEmpty(func) || TextUtils.isEmpty(pacakgeName)) {
                KLog.e(TAG, "fetchSync() | TextUtils.isEmpty(func) || TextUtils.isEmpty(pacakgeName) ");
                return "error";
            }
            if (messageReceiver != null) {
                return messageReceiver.fetchSync(pacakgeName,func);
            }
            return "success";
        }

        @Override
        public String fetchSyncWithParam(String pacakgeName, String func, String params) throws RemoteException {
            KLog.d(TAG, "fetchSyncWithParam: pkg->" + pacakgeName + " func->" + func);
            if (TextUtils.isEmpty(func) || TextUtils.isEmpty(pacakgeName)) {
                KLog.e(TAG, "fetchSync() | TextUtils.isEmpty(func) || TextUtils.isEmpty(pacakgeName) ");
                return "error";
            }
            if (messageReceiver != null) {
                return messageReceiver.fetchSyncWithParams(pacakgeName, func, params);
            }
            return "success";
        }
    };

    @Override
    public void onDestroy() {
        super.onDestroy();

        if (mClientCallbacks != null) {
            mClientCallbacks.kill();
        } else {
            KLog.e(TAG, "onDestroy() | mClientCallbacks == null ");
        }
    }

    private void unbindBinder(ClientRecipient recipient) {
        if (mClientCallbacks != null) {
            KLog.e(TAG, "register() | binderDied() | mClientCallbacks == null ");
            return;
        }

        try {
            // 从 server端 分发到各个 client 端，解除绑定 unlinkToDeath
            int count = mClientCallbacks.beginBroadcast();
            if (count == 0) {
                KLog.e(TAG, "register() | binderDied() | count == 0 ");
                mClientCallbacks.finishBroadcast();
                return;
            }
            for (int i = 0; i < count; i++) {
                IGenieServiceCallback listener = mClientCallbacks.getBroadcastItem(i);
                if (listener != null) {
                    listener.asBinder().unlinkToDeath(recipient, 0);
                }
            }
        } catch (Exception e) {
            KLog.e(TAG, "register() | binderDied() | Exception : " + e.getMessage());
        } finally {
            mClientCallbacks.finishBroadcast();
        }
    }

    /**
     * server下发消息给client
     *
     * @param pacakgeName 接收消息的包名
     * @param func        包名下对应的业务方法名
     * @param params      下发数据
     */
    public void sendMessage(@NonNull String pacakgeName, @NonNull String func, String params) {
        if (TextUtils.isEmpty(func) || TextUtils.isEmpty(pacakgeName)) {
            KLog.e(TAG, "sendMessage() | TextUtils.isEmpty(func) || TextUtils.isEmpty(pacakgeName) ");
            return;
        }
        Message message = mSendHandler.obtainMessage();
        Bundle bundle = new Bundle();
        bundle.putString("packageName", pacakgeName);
        bundle.putString("func", func);
        bundle.putString("params", params == null ? "" : params);
        message.setData(bundle);
        mSendHandler.sendMessage(message);
    }

    /**
     * 从 server端 分发到各个 client 端，回调事件
     *
     * @param func
     * @param code
     * @param params
     * @throws RemoteException
     */
    public void dispatchMessage(String pkg, String func, int code, String params) throws RemoteException {
        if (mClientCallbacks == null) {
            KLog.e(TAG, "dispatchMessage() | mClientCallbacks == null ");
            return;
        }

        try {
            int count = mClientCallbacks.beginBroadcast();
            if (count == 0) {
                KLog.e(TAG, "dispatchMessage() | binderDied() | count == 0 ");
                mClientCallbacks.finishBroadcast();
                return;
            }
            for (int i = 0; i < count; i++) {
                IGenieServiceCallback listener = mClientCallbacks.getBroadcastItem(i);
                if (listener != null) {
                    listener.onReceiver(pkg, func, code, params);
                }
            }
        } catch (Exception e) {
            KLog.e(TAG, "dispatchMessage() | binderDied() | Exception : " + e.getMessage());
        } finally {
            mClientCallbacks.finishBroadcast();
        }
    }
}