package com.liyafeng.concurrent;

import com.liyafeng.concurrent.taskentity.Runnable;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * Created by liyafeng on 2017/12/25.
 * 真正实现 “执行” 方法的类，它负责如何执行
 */

public class ThreadPoolExecutor extends AbstractExecutorService {
    private volatile int corePoolSize;//最小线程数
    private volatile int maximumPoolSize;//最大线程数

    private volatile int currentThreadSize;

    BlockingQueue<Runnable> workQueue;

    public ThreadPoolExecutor(int corePoolSize, int maximumPoolSize, int currentThreadSize, BlockingQueue<Runnable> workQueue) {
        this.corePoolSize = corePoolSize;
        this.maximumPoolSize = maximumPoolSize;
        this.currentThreadSize = currentThreadSize;
        this.workQueue = workQueue;
    }

    public ThreadPoolExecutor() {
        this.corePoolSize = 5;
        this.maximumPoolSize = 10;
        this.workQueue = new LinkedBlockingDeque<>();
    }

    @Override
    public void execute(Runnable command) {

        if (currentThreadSize < corePoolSize) {
            if (addWorker(command, true)) {
                return;
            }
        }

        if (workQueue.offer(command)) {//加入等待队列
            addWorker(null, false);
        } else if (!addWorker(command, false)) {//使用非核心线程
            reject(command);
        }
    }

    /**
     * 任务被拒绝
     * @param command
     */
    private void reject(Runnable command) {

    }

    /**
     * 将任务加入队列
     *
     * @param command
     * @param core    是否使用核心线程
     */
    private boolean addWorker(Runnable command, boolean core) {

        return core;
    }

    /**
     * 实现先进先出的任务队列
     */
    private final class Worker extends AbstractQueuedSynchronizer implements Runnable{

        @Override
        public void run() {

        }
    }
}
