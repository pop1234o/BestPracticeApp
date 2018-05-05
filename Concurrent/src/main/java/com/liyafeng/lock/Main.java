package com.liyafeng.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.*;
import java.util.concurrent.locks.Condition;

/**
 * Created by liyafeng on 2018/3/19.
 */

public class Main {


    private static ReentrantLock reentrantLock;
    private static Condition condition;

    /**
     * 互斥锁(独立锁)  exclusive lock / Mutex
     * 共享锁 shared lock
     * 自旋锁 spin lock
     * 互斥锁和自旋锁都是保护共享资源的锁，只允许同一时刻只能有一个保持者
     * 只不过互斥锁在已经有线程持有锁的情况下，当前线程进入睡眠状态
     * 而自旋锁会一直去请求锁，直到请求到为止。
     * ========使用场景=========
     * 互斥锁适用于持有锁时间比较长的时候
     * 自旋锁适合比较短的时间
     * 共享锁，如果这几个线程只是读取，那么用互斥锁的效率太低，这时就可以用共享锁
     * <p>
     * {@link java.util.concurrent.locks.ReentrantLock}
     * {@link java.util.concurrent.locks.Lock}
     * {@link java.util.concurrent.locks.AbstractQueuedSynchronizer}
     * ============ReentrantLock======================
     * concurrent包下，除了线程线程池有关的，还有并发集合/原子的数据类型，剩下的就是同步锁了
     * <p>
     * Lock接口，用来代替synchronized关键字，lock() unlock()方法相当于进入和退出{}，申请锁
     * 和释放锁，但是里面还提供了更多的申请方式
     * tryLock() tryLock(time)
     * lockInterruptibly()
     * newCondition()
     * <p>
     * Condition 是用来代替Object的wait/notify ，await()/signal/signalAll,
     * 能提供更丰富的功能 awaitUntil awaitNanos awaitUninterruptibly
     * <p>
     * AbstractQueuedSynchronizer 是真正的lock的内部实现，里面用Unsafe类来实现线程挂起和唤醒
     * 里面有一个等待队列，没有获取锁的线程加入等待队列
     * <p>
     * ReentrantLock 可重入锁，Lock接口的实现类，
     * 内部有公平锁，非公平锁，FairSync ，NonfairSync
     * 他们都是AQS的子类
     * ====================源码解析=================================
     * ReentrantLock。lock方法，调用的FairSync ，NonfairSync的lock方法
     * FairSync。lock调用AQS的acquire(1)方法来申请锁，
     * AQS中
     * if (!tryAcquire(arg) &&acquireQueued(addWaiter(Node.EXCLUSIVE), arg))
     * selfInterrupt();
     * tryAcquire先尝试获取锁，这个AQS中没有实现，需要子类实现
     * FairSync中重写了这个方法，里面先获取锁的状态  int c = getState();
     * 初始是0，如果为0，那么将state用CAS操作设置为0，将当前线程设置为独占线程
     * setExclusiveOwnerThread(current)
     * 返回true，
     *
     * @param args
     */
    public static void main(String[] args) {

        reentrantLock = new ReentrantLock(true);
        condition = reentrantLock.newCondition();

        for (int i = 0; i < 5; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    tryLock();
                }
            }).start();
        }

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

    public static void signal() {
        reentrantLock.lock();
        try {
            //这个是按await的顺序来唤醒的
            condition.signal();
            condition.signal();
            condition.signal();
            condition.signal();
            condition.signal();
        } finally {
            reentrantLock.unlock();
        }
    }

    public static void tryLock() {

        try {
            //这种情况都能进入锁，因为线程获取锁后马上await，释放了锁，然后通知
            //等待的线程，线程获取锁又await，所以一瞬间都能进来了
            if (reentrantLock.tryLock(10000, TimeUnit.MILLISECONDS)) {
                try {

                    System.out.println("进入锁" + Thread.currentThread());
                    condition.await(5000, TimeUnit.MILLISECONDS);
                    System.out.println("唤醒" + Thread.currentThread());
                    //do..
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    reentrantLock.unlock();
                }
            } else {
                System.out.println("没获取到锁" + Thread.currentThread());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

    public static void lock() {

        reentrantLock.lock();
        try {

            System.out.println("进入锁" + Thread.currentThread());
            condition.await(50000, TimeUnit.MILLISECONDS);
            System.out.println("唤醒" + Thread.currentThread());
            //do..
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            reentrantLock.unlock();
        }

    }
}
