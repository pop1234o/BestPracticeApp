// IGenieService.aidl
package com.liyafeng.base_binder;
import com.liyafeng.base_binder.IGenieServiceCallback;

// Declare any non-default types here with import statements

interface IGenieService {

    //客户端注册
    void register(in String packageName, in IGenieServiceCallback callback);

    //客户端注销
    void unRegister(in String packageName, in IGenieServiceCallback calllback);

    //客户端异步获取数据
    void fechAsync(in String pacakgeName, in String func, String params);

    //客户端同步获取数据
    String fetchSync(in String pacakgeName, in String func);

        //客户端同步获取数据
    String fetchSyncWithParam(in String pacakgeName, in String func, in String params);
}