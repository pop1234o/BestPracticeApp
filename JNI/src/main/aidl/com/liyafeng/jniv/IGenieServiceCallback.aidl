// IGenieServiceCallback.aidl
package com.liyafeng.base_binder;

// Declare any non-default types here with import statements

interface IGenieServiceCallback {

    //收到服务端下发消息
    void onReceiver(in String pkg,in String func,in int code,in String params);
}