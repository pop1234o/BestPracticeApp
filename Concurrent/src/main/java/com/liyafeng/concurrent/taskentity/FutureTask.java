package com.liyafeng.concurrent.taskentity;

import com.liyafeng.concurrent.util.Executors;

/**
 * Created by lenovo on 2017/12/21.
 *
 * 一个具体的RunnableFuture （可run,可get()结果）的对象
 *
 * 内部持有一个Callable对象，使用阻塞，激活的方式来实现get()
 * callable对象来返回任务运行的结果
 *
 *  这样就实现了Feature的get()阻塞获取结果的特性
 *  也实现了Runnable 可以在子线程run的特性
 *
 * 可以说futuretask是集Runnable，Future，Callable三者的特性
 *
 * ---------------------
 * A cancellable asynchronous computation
 * 可取消的异步计算任务
 *
 * get()会导致当前线程阻塞，（多个阻塞的线程会加入到等待队列（链表））当有结果的时候，会依次唤醒
 *
 *
 */

public class FutureTask<V> implements RunnableFuture<V> {

    private final Callable<V> callable;

    private volatile int state;
    private static final int NEW = 0;
    private static final int COMPLETING = 1;//完成中
    private static final int NORMAL = 2;//完成
    private static final int EXCEPTIONAL = 3;//异常

    private Object outcome;//输出的结果

    public FutureTask(Runnable task, V value) {
        //将runnable适配为callable
        callable = Executors.callable(task, value);
        state = NEW;
    }

    @Override
    public void run() {

        try {
            V result = callable.call();
            set(result);
        } catch (Throwable e) {
            e.printStackTrace();
            outcome = e;
        }
        //唤醒get()的阻塞
        synchronized (this) {
            notify();
        }
    }

    //设置结果
    private void set(V result) {
        state = COMPLETING;
        outcome = result;
        state = NORMAL;
    }

    @Override
    public V get() throws Exception {
        int s = this.state;
        if (s <= COMPLETING) {
            s = awaitDone();
        }
        return report(s);
    }

    private V report(int s) throws Exception {
        if (state == NORMAL) {
            return (V) outcome;
        }
        throw new Exception((Throwable) outcome);
    }

    private int awaitDone() {
        for (; ; ) {
            if (state == NORMAL) {
                return state;
            } else if (state == COMPLETING) {
                Thread.yield();
            } else {
                try {
                    synchronized (this) {
                        wait();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
