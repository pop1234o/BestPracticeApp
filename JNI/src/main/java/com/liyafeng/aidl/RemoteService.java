package com.liyafeng.aidl;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by liyafeng on 2018/4/20.
 */

public class RemoteService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new Messenger(handler).getBinder();
    }

    public  Handler handler = new Handler(){

        @Override
        public void handleMessage(Message msg) {
            String key =msg.obj.toString();
            if(msg.obj instanceof Bundle){
                Bundle bundle = (Bundle) msg.obj;
                 key = bundle.getString("key");
            }
            Log.i("test", "远程服务接收到了"+key);

            try {
                //给客户端返回消息
                msg.replyTo.send(msg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    };
}
