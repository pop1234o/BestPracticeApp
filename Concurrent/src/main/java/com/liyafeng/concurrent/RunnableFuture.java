package com.liyafeng.concurrent;


/**
 * Created by lenovo on 2017/12/21.
 * 聚合两个接口的功能，加强拓展性？
 */

public interface RunnableFuture<V> extends  Runnable,Future<V>{
}
