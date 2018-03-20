package com.liyafeng.lock;

/**
 * Created by liyafeng on 2018/3/19.
 */

public abstract class AbstractQueuedSynchronizer extends AbstractOwnableSynchronizer {
    private static final long serialVersionUID = 7373984972572414691L;


    /**
     * 只能在内部类 的外部类中初始化
     */
    protected AbstractQueuedSynchronizer() { }


    static final class Node{

    }

    private transient volatile Node head;

    private transient volatile Node tail;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    private volatile int state;



}
