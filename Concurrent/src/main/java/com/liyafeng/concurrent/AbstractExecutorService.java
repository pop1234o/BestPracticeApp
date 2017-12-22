package com.liyafeng.concurrent;

/**
 * Created by lenovo on 2017/12/21.
 */

public abstract class AbstractExecutorService implements ExecutorService {

    @Override
    public Future<?> submit(Runnable task) {
        //返回一个可返回数据，可执行的任务对象
        RunnableFuture<Void> ftask = newTaskFor(task, null);
        //让子类来决定如何执行
        execute(ftask);
        return ftask;
    }

    private <T> RunnableFuture<T> newTaskFor(Runnable task, T value) {
        return new FutureTask<T>(task, value);
    }
}
