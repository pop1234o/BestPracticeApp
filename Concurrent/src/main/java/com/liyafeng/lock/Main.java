package com.liyafeng.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.*;
import java.util.concurrent.locks.Condition;

/**
 * Created by liyafeng on 2018/3/19.
 */

public class Main {



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
     * acquire(1){
     * if (!tryAcquire(arg) &&acquireQueued(addWaiter(Node.EXCLUSIVE), arg))
     * selfInterrupt();
     * }
     * <p>
     * tryAcquire先尝试获取锁，这个AQS中没有实现，需要子类实现
     * tryAcquire{
     * FairSync中重写了这个方法，里面先获取锁的状态  int c = getState();
     * 初始是0，如果为0，那么将state用CAS操作设置为0，将当前线程设置为独占线程
     * setExclusiveOwnerThread(current)
     * 返回true，代表了获取锁成功了（其实就是state改为1了）
     * }
     * 那么tryAcquire返回true，acquire中的其他代码就不执行了，lock()会继续执行，
     * 这就是第一个获取锁的线程，
     * 那么当第二个线程到来时，调用Lock.lock，调用AQS.acquire，然后调用Sync实现的
     * tryAcquire,判断状态不为0，而且不是独占的那个线程，那么直接返回false，
     * 走if的第二个方法 acquireQueued(addWaiter(Node.EXCLUSIVE), arg)
     * Node.EXCLUSIVE 是null值 ，这个标记是独占锁，
     * AQS中有个Node  head ,tail 是个链表
     * private Node addWaiter(Node mode) {
     * Node node = new Node(mode);
     * <p>
     * for (;;) {
     * Node oldTail = tail;
     * if (oldTail != null) {//下面的逻辑就是将node加入链表中
     * U.putObject(node, Node.PREV, oldTail);  node.pre= oldTail
     * if (compareAndSetTail(oldTail, node)) {  tail = node;
     * oldTail.next = node;  //到这里是 head->node(tail也指向他)
     * return node;
     * }
     * } else {
     * initializeSyncQueue();//第一次头尾都为null，然后会new Node,head,tail都指向他
     * }
     * }
     * }
     * <p>
     * <p>
     * 这里是请求加入等待队列
     * final boolean acquireQueued(final Node node, int arg) {
     * try {
     * boolean interrupted = false;
     * for (;;) {
     * final Node p = node.predecessor();//获取当前节点的前一个节点，第二个线程就是head
     * if (p == head && tryAcquire(arg)) {//再次请求锁
     * setHead(node); //请求成功，head->node,这就是新的head了
     * p.next = null; // help GC 旧的head.next = null
     * return interrupted; //这时acquire执行完毕，lock也执行完毕，那么可以继续执行了
     * }
     * if (shouldParkAfterFailedAcquire(p, node) && //判断是否应该park，只要没intercept就应该挂起
     * parkAndCheckInterrupt()) //这里是park操作，等待持有锁的线程释放后唤醒
     * interrupted = true;
     * }
     * } catch (Throwable t) {
     * cancelAcquire(node);
     * throw t;
     * }
     * }
     * 这段代码就是将新的节点加入队列尾，然后循环判断是否能获取锁，只有当前
     * 锁没被持有而且是队列头的节点才能被唤醒
     * 否则就进入park方法挂起
     * <p>
     * -------------------------------------
     * unlock，是持有锁的线程释放锁，lock.unlock()->AQS.release(1);
     * public final boolean release(int arg) {
     * //这里是ReentrantLock中的Sync重写的方法，判断当前status-1后是否为0，
     * //如果为0那么返回true，表示释放成功，
     * if (tryRelease(arg)) {
     * Node h = head;
     * if (h != null && h.waitStatus != 0)//如果有等待的线程队列，且没有中断
     * unparkSuccessor(h);
     * //唤醒head.next线程，调用unpark，这个线程就会再次被唤醒调用treAcquire()这个时候status+1
     * //然后等最后还会调用unlock，来唤醒下一个线程
     * return true;
     * }
     * return false;
     * }
     * <p>
     * 非公平锁和公平锁的差别在于它允许插队，公平锁是如果在一瞬间线程释放了锁，那么
     * 下一个进入的是队列头，而非公平是如果这个时候有一个不在队列中的线程申请锁，
     * 那么他可以得到锁。
     * <p>
     * final void lock() {
     * if (compareAndSetState(0, 1)) //不用加入队列，直接获得锁，将status改为1
     * setExclusiveOwnerThread(Thread.currentThread());
     * else
     * acquire(1);
     * }
     *
     * 模式是非公平锁，因为他的吞吐量高。因为将一个线程唤醒是需要时间的
     * 一个线程挂起也是需要时间，所以非公平锁是谁正好来，就可以插到前面
     * 但是当持有锁时间较长，非公平锁带来的效果会不明显
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
    private static ReentrantLock reentrantLock;
    private static Condition condition;

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
