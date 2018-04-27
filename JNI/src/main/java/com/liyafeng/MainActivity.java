package com.liyafeng;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.liyafeng.aidl.AIDLService;
import com.liyafeng.aidl.LocalService;
import com.liyafeng.aidl.RemoteService;
import com.liyafeng.jni.IMyAidlInterface;
import com.liyafeng.jni.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ServiceConnection conn;
    private Messenger messenger;
    private Messenger messenger_remote;
    private IMyAidlInterface anInterface;

    /**

     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            throw new Exception();
        } catch (Exception e) {
            e.printStackTrace();
        }
//        int s = Hello.doSomething("1");
//        Log.i("test", "==========" + s);

        //==========Android.mk文件格式
        /*
        *
        *   LOCAL_PATH := $(call my-dir)
        *    include $(CLEAR_VARS)
        *
        *    LOCAL_MODULE := custom_jni
        *    LOCAL_LDFLAGS := -Wl,--build-id
        *    LOCAL_SRC_FILES := \
        *    	E:\mynewpro\JNI\src\main\jni\Hello.c \
        *
        *    LOCAL_C_INCLUDES += E:\mynewpro\JNI\src\debug\jni
        *    LOCAL_C_INCLUDES += E:\mynewpro\JNI\src\main\jni
        *
        *    include $(BUILD_SHARED_LIBRARY)

        * */


        conn = new ServiceConnection() {


            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                binder = (LocalService.MyBinder) service;
                Log.i("test", "绑定成功");
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        };

        findViewById(R.id.button).setOnClickListener(this);
        findViewById(R.id.button2).setOnClickListener(this);
        findViewById(R.id.button3).setOnClickListener(this);
        findViewById(R.id.button4).setOnClickListener(this);
        findViewById(R.id.button5).setOnClickListener(this);
        findViewById(R.id.button6).setOnClickListener(this);

        initRemote();

        Runtime runtime = Runtime.getRuntime();
        long l = runtime.maxMemory();
        long l1 = runtime.totalMemory();
        
        Log.i("test", "max"+l+ "   "+l1);
        //268435456 256m  18250546  17.4m
    }


    private LocalService.MyBinder binder;


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (binder != null) {
            unbindService(conn);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                String s = binder.doSomething(1);
                Log.i("test", "======" + s);
                break;
            case R.id.button2: {
                final Intent intent = new Intent(this, LocalService.class);
                bindService(intent, conn, Context.BIND_AUTO_CREATE);

            }

            break;
            case R.id.button3: {
                final Intent intent = new Intent(this, RemoteService.class);
                bindService(intent, new ServiceConnection() {
                    @Override
                    public void onServiceConnected(ComponentName name, IBinder service) {
                        Log.i("test", "绑定成功" + name.getPackageName());
                        messenger_remote = new Messenger(service);

                    }

                    @Override
                    public void onServiceDisconnected(ComponentName name) {

                    }
                }, Context.BIND_AUTO_CREATE);
            }

            break;
            case R.id.button4:

                Message obtain = Message.obtain();
                Bundle bundle = new Bundle();
                bundle.putString("key", "来自客户端的消息");
                obtain.obj = bundle;
                obtain.replyTo = messenger;
                try {
                    messenger_remote.send(obtain);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                break;

            case R.id.button5: {
                final Intent intent = new Intent(this, AIDLService.class);
                bindService(intent, new ServiceConnection() {
                    @Override
                    public void onServiceConnected(ComponentName name, IBinder service) {
                        Log.i("test", "绑定成功" + name.getPackageName());
                        anInterface = IMyAidlInterface.Stub.asInterface(service);

                    }

                    @Override
                    public void onServiceDisconnected(ComponentName name) {

                    }
                }, Context.BIND_AUTO_CREATE);
            }

            break;
            case R.id.button6:
                String s1 = null;
                try {
                    s1 = anInterface.doSome(2);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                Log.i("test", "执行：" + s1);
                break;

        }
    }


    private void initRemote() {
        messenger = new Messenger(handler);
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {

            Log.i("test", "来自服务端的消息:" + msg.obj.toString());


        }
    };

}
