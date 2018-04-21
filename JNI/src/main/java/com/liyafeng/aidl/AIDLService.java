package com.liyafeng.aidl;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.widget.TextView;

import com.liyafeng.jni.IMyAidlInterface;

public class AIDLService extends Service {
    public AIDLService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return stub;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("test", "当前线程"+Thread.currentThread());
    }

    IMyAidlInterface.Stub stub = new IMyAidlInterface.Stub() {

        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

        }

        @Override
        public String doSome(int i) throws RemoteException {
            Log.i("test", "执行了doSome"+Thread.currentThread().toString());
            return "result:"+i;
        }
    };

}
