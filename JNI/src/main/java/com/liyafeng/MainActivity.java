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
     * ndk入门指南
     * https://developer.android.google.cn/ndk/guides/index.html?hl=zh-cn
     * jni介绍
     * https://developer.android.google.cn/training/articles/perf-jni.html
     * <p>
     * hello-jni示例
     * https://github.com/googlesamples/android-ndk/tree/master/hello-jni
     * <p>
     * 使用c/c++示例
     * https://developer.android.google.cn/studio/projects/add-native-code.html
     * <p>
     * ndk下载
     * https://developer.android.google.cn/ndk/downloads/index.html
     * <p>
     * application_mk
     * https://developer.android.google.cn/ndk/guides/application_mk.html
     * https://developer.android.google.cn/ndk/guides/android_mk.html
     * <p>
     * jni官方介绍（包括原理）
     * https://docs.oracle.com/javase/7/docs/technotes/guides/jni/spec/jniTOC.html
     * ============为什么用jni====================
     * 从设备获取卓越性能以用于计算密集型应用，例如游戏或物理模拟。
     * 重复使用您自己或其他开发者的 C 或 C++ 库。
     * <p>
     * <p>
     * ====================jni使用步骤===========================
     * 首先我们要下载ndk，我们生成so文件就是靠里面的 ndk-build.cmd
     * 在Tools=>Android=>SDK Manager中安装Cmake，和NDK
     * 1，我们要在java文件中生命native方法
     * 2.使用javac 编译java文件为class文件（或者点AS的 Build->Make Module "xxx",
     * 在Module的build/intermediates/classes/包名/ 下有自动编译的class文件）
     * 3.用javah命令生成xx.h头文件，javah com/liyafeng/jni/xxx  在com的同级目录生成
     * 头文件，我们将它改成.c文件，然后实现里面的方法，注意方法参数要补全（或者自己定义
     * 一个.c文件，把生成的头文件include进来）
     * 4.生成Android.mk，这个文件时是帮助ndk-build命令来生成so文件的，一般我们
     * 在项目的src/main/jni中创建了c/c++文件，然后build一下，就在build/intermediates/ndk
     * 中生成了Android.mk
     * 5.此时需要把Android.mk拷贝到src/main/jni中，然后再这个目录运行 ndk-build命令
     * 然后就会生成对应的so文件，然后将这些so文件加入，src/main/jniLibs/xxabi中
     * 当然我们可以指定so文件的位置 jniLibs.srcDirs = ['libs']
     * 然后我们在java文件中，我们将so文件的lib前缀去掉
     * static {
     * System.loadLibrary("custom_jni");
     * }
     * 最后我们就可以用native方法了
     * ====================================================
     * 需要将jni.h（该文件可以在%JAVA_HOME%/include文件夹下面找到）文件引入，
     * 因为在程序中的JNIEnv、 jobject等类型都是在该头文件中定义的
     * <p>
     * =================================================
     * 1写java native方法
     * 2.生成class，生成.h
     * 3.编写Android.mk，使用NDK的ndk-build命令来生成so
     * 4.将so加入工程，用静态代码块加载，运行，即可调用native方法了
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
