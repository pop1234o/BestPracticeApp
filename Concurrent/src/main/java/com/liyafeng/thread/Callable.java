package com.liyafeng.thread;

/**
 * Created by lenovo on 2017/12/21.
 *
 * 这个是用来代替Runnable的，因为Runnable不能返回结果
 * 而这个可以，代表一个可返回数据的任务
 */

public interface Callable<V> {

    V call() throws Exception;
}
