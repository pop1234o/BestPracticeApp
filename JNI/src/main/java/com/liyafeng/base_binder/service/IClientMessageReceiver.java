package com.liyafeng.base_binder.service;

public interface IClientMessageReceiver {

    String fetchSync(String pacakgeName, String func);

    void fechAsync(String pacakgeName, String func, String params);

    String fetchSyncWithParams(String pacakgeName, String func, String params);
}
