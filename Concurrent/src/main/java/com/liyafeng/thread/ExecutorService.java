package com.liyafeng.thread;

/**
 * Created by lenovo on 2017/12/21.
 *
 * 这个接口提供了管理任务的方法，因为任务可以被取消
 * 而且可以判断当前任务的状态
 * 而且任务可以有返回值
 *
 */

public interface ExecutorService extends Executor {

    Future<?> submit(Runnable task);
}
