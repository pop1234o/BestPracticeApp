package com.liyafeng.aidl;

/**
 * Created by liyafeng on 2018/4/20.
 */

public class Main {


    /**
     * ======================
     * 当我们服务是同一个进程中，直接bindservice返回Service中的Binder对象
     *
     * 在不同进程中会报错
     * java.lang.ClassCastException: android.os.BinderProxy cannot be cast to com.liyafeng.aidl.LocalService$MyBinder
     * at com.liyafeng.MainActivity$1.onServiceConnected(MainActivity.java:109)
     * at android.app.LoadedApk$ServiceDispatcher.doConnected(LoadedApk.java:1208)
     * at android.app.LoadedApk$ServiceDispatcher$RunConnection.run(LoadedApk.java:1225)
     * at android.os.Handler.handleCallback(Handler.java:739)
     * at android.os.Handler.dispatchMessage(Handler.java:95)
     * at android.os.Looper.loop(Looper.java:135)
     * at android.app.ActivityThread.main(ActivityThread.java:5271)
     * at java.lang.reflect.Method.invoke(Native Method)
     * at java.lang.reflect.Method.invoke(Method.java:372)
     * at com.android.internal.os.ZygoteInit$MethodAndArgsCaller.run(ZygoteInit.java:902)
     * at com.android.internal.os.ZygoteInit.main(ZygoteInit.java:697)
     *
     *=============================================
     *   android:process=":remote" 带冒号是表示这个进程是应用私有的，别的应用不能访问
     *
     * ===================================
     * 通过Messenger传递的obj要实现Parcelable
     *
     *
     * =================aidl=======================
     * https://blog.csdn.net/u012203641/article/details/74474664
     * IBinder接口代表一个可以远程传输的对象
     * Binder则实现了RPC(远程进程通信)的一个协议，定义了如何传输和接收对象
     *
     * =============aidl
     * 定义一个aidl文件，as自动生成一个java
     * server：定义service，然后返回这个实现。里面实现了方法
     *
     * client：bind这个service，获取到这个示例，调用方法。
     *
     * @param args
     */
    public static void main(String[] args) {

    }




    /**
     * ========进程保活====
     *
     *
     *   boolean success = context.bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
     *
     *
     *     private class XXServiceConnection implements ServiceConnection {
     *         @Override
     *         public void onServiceConnected(ComponentName name, IBinder service) {
     *             try {
     *                 mRtcKeepAliveBinder = IKeepAliveService.Stub.asInterface(service);
     *                 service.linkToDeath(mDeathRecipient, 0);//监听binder断开
     *             } catch (RemoteException e) {
     *                 e.printStackTrace();
     *             }
     *         }
     *
     *         @Override
     *         public void onServiceDisconnected(ComponentName name) {
     *             weakReference.get().unbindService(mLauncherServiceConnection);
     *         }
     *     }
     *
     *
     *   private IBinder.DeathRecipient mDeathRecipient = new IBinder.DeathRecipient() {
     *         @Override
     *         public void binderDied() {
     *             KLog.r(TAG,BIND_DIED, "msg","Launcher BinderDied");
     *             if (mRtcKeepAliveBinder != null) {
     *                 mRtcKeepAliveBinder.asBinder().unlinkToDeath(mDeathRecipient, 0);
     *                 mRtcKeepAliveBinder = null;
     *             }
     *             //断开重新绑定
     *             startKeepAliveService(weakReference.get());
     *         }
     *     };
     *
     */
    void a30(){}
}
