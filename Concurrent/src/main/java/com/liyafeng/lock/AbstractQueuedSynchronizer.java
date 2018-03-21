package com.liyafeng.lock;

import java.util.concurrent.locks.LockSupport;

/**
 * Created by liyafeng on 2018/3/19.
 * 其实这个类提供了一个抽象的持有锁和等待模型
 * tryAcquire tryRelease 都是子类要实现的方法
 * 他们确定是否线程能获取到锁
 * 如果获取不到就加入等待队列，然后释放锁后这个类会自动从
 * 等待队列取出符合条件的等待线程，然后唤醒他
 *
 */

public abstract class AbstractQueuedSynchronizer extends AbstractOwnableSynchronizer {
    private static final long serialVersionUID = 7373984972572414691L;


    /**
     * 只能在内部类 的外部类中初始化
     */
    protected AbstractQueuedSynchronizer() {
    }


    static final class Node {
        //两种锁的模式
        static final Node SHARED = new Node();
        static final Node EXCLUSIVE = null;
        //四种状态
        static final int CANCELLED = 1; //已经被取消了
        static final int SIGNAL = -1; //表示下一个节点的Thread需要被唤醒
        static final int CONDITION = -2; //在等待条件被唤醒
        static final int PROPAGATE = -3;//获取共享锁应该无条件获取锁？

        volatile int waitStatus;

        Node pre;
        Node next;

        Thread thread;
        Node nextWaiter;


        public Node(Node mode) {
            nextWaiter = mode;
            thread = Thread.currentThread();
        }

        public Node() {
        }

        public Node predecessor() {
            Node p = pre;
            if (p == null)
                throw new NullPointerException();
            else
                return p;
        }
    }

    private transient volatile Node head;

    private transient volatile Node tail;

    //标记锁状态
    private volatile int state;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }


    /**
     * 尝试去获取锁，请求独占锁 ，需要子类去实现请求成功规则
     *
     * @param arg 就是一个数值
     * @return 如果获取成功返回true
     */
    protected boolean tryAcquire(int arg) {
        throw new UnsupportedOperationException();
    }


    public final void acquire(int arg) {
        //如果没有获取锁成功，而且线程加入队列挂起等待的时候被中断，那么调用中断方法
        if (!tryAcquire(arg) && acquireQueued(addWaiter(Node.EXCLUSIVE), arg)) {
            Thread.currentThread().interrupt();
        }
    }


    final boolean acquireQueued(Node node, int arg) {

        boolean interrupted = false;//标记这个线程在等待获取锁期间是否被中断了
        for (; ; ) {
            Node p = node.predecessor();//获取当前节点的前一个节点
            //这个节点就是等待队列的头节点，自旋后终于可以获取到锁了
            if (p == head && tryAcquire(arg)) {
                setHead(node);//设置这个节点为新的头节点
                p.next = null; // help GC
                return interrupted;
            }

            if (shouldParkAfterFailedAcquire(p, node) && parkAndCheckInterrupt()) {
                interrupted = true;//表示已经被中断了
            }
        }
    }

    /**
     * 挂起线程，然后阻塞到这
     *
     * @return
     */
    private boolean parkAndCheckInterrupt() {
        LockSupport.park(this);//如果在挂起的时候被中断，那么将返回true
        return Thread.interrupted();//返回中断标记，且清除中断标记为false
    }

    /**
     * 判断根据前一个节点的状态来判断 当前节点是否应该park（挂起）
     *
     * @param p    上一个节点
     * @param node 当前节点
     * @return
     */
    private boolean shouldParkAfterFailedAcquire(Node p, Node node) {
        int waitStatus = p.waitStatus;
        if (waitStatus == Node.SIGNAL) {//前一个节点状态是等待状态
            return true;
        }

        if (waitStatus > 0) {//其实只有CANCELLED大于0
            //如果前面的节点状态是已取消，那么再取前一个节点
            do {
                p = p.pre;
                node.pre = p;
            } while (p.waitStatus > 0);
            p.next = node;//最新前面的节点指向node（新加入的）
        } else {//此时肯定是0或者负数
            p.waitStatus = Node.SIGNAL;//将前一个节点状态设置为SIGNAL
        }
        return false; //此时不能确定新加入节点是否要挂起
    }

    /**
     * 设置为头节点
     *
     * @param node
     */
    private void setHead(Node node) {
        head = node;
        node.thread = null;
        node.pre = null;
    }

    private Node addWaiter(Node mode) {
        Node node = new Node(mode);

        for (; ; ) {
            Node oldTail = tail;
            if (oldTail != null) {
                node.pre = oldTail;//新加入的节点pre指向原来的尾节点
                tail = node;//指向新的尾节点
                oldTail.next = node;
                return node;
            } else {//链表为null，加入一个新的节点
                head = new Node();
                tail = head;
            }
        }
    }


    public final boolean release(int arg) {
        if (tryRelease(arg)) {
            Node h = this.head;
            if (h != null && h.waitStatus != 0) {//队列中有数据，而且在等待
                unparkSuccessor(h);//唤醒下一个节点
            }
            return true;
        }
        return false;
    }

    private void unparkSuccessor(Node node) {
        int waitStatus = node.waitStatus;
        if (waitStatus < 0) {
            node.waitStatus = 0;
        }
        Node s = node.next;
        if (s == null || s.waitStatus > 0) {//已经被取消，或者头结点下一个节点已经取消了
            s = null;//这个是因为
            //从后向前遍历找出等待的那个节点(队列中的第一个)（这种情况是head.next断了）
            //从后向前遍历是因为防止head.next是null?
            for (Node p = tail; p != node && p != null; p = p.pre) {
                if (p.waitStatus <= 0) {
                    s = p;
                }
            }
        }
        if (s != null) {
            LockSupport.unpark(s.thread);//唤醒下一个节点
        }
    }

    protected boolean tryRelease(int arg) {
        throw new UnsupportedOperationException();
    }
}
