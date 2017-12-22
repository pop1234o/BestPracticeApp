package com.liyafeng.thread;

/**
 * Created by lenovo on 2017/12/21.
 */

public abstract class AbstractExecutorService implements ExecutorService {

    @Override
    public Future<?> submit(Runnable task) {
        RunnableFuture<Void> ftask = newTaskFor(task,null);
        execute(ftask);
        return ftask;
    }

    private <T> RunnableFuture<T> newTaskFor(Runnable task, T value) {
        return new FutureTask<T>(task,value);
    }
}
