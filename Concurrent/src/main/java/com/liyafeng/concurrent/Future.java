package com.liyafeng.concurrent;

/**
 * Created by lenovo on 2017/12/21.
 * 代表一个异步任务的返回的对象
 * 里面可以获取到我们想要的V类型的对象
 *
 */

public interface Future<V> {

    V get() throws Exception;
}
