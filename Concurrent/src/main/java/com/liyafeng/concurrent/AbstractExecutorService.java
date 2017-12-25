package com.liyafeng.concurrent;

import com.liyafeng.concurrent.taskentity.Future;
import com.liyafeng.concurrent.taskentity.FutureTask;
import com.liyafeng.concurrent.taskentity.Runnable;
import com.liyafeng.concurrent.taskentity.RunnableFuture;

/**
 * Created by lenovo on 2017/12/21.
 *
 * 这个类负责执行前对“任务”对象的一些处理
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
