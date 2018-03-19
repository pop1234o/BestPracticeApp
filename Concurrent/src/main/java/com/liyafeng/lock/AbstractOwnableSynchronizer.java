package com.liyafeng.lock;

import java.io.Serializable;

/**
 * Created by liyafeng on 2018/3/19.
 *  synchronizer that may be exclusively owned by a thread.
 * 独有的同步器（独有锁/排他锁）
 * 只有一个线程能持有锁，持有锁期间其他线程不能占有锁
 * 直到持有锁的线程放弃锁
 */

public abstract class AbstractOwnableSynchronizer implements Serializable{

    //序列化的id，系统会根据这个id来判断序列化和反序列化的类的版本是否一致
    //如果不一致会抛出异常InvalidCastException
    // 如果没有显示指定，那么编译的时候会根据字段和方法自动生成一个唯一的id
    private static final long serialVersionUID = 3737899427754241961L;


    private transient Thread exclusiveOwnerThread;//独占的那个线程
    protected final Thread getExclusiveOwnerThread() {
        return exclusiveOwnerThread;
    }

    protected final void setExclusiveOwnerThread(Thread exclusiveOwnerThread) {
        this.exclusiveOwnerThread = exclusiveOwnerThread;
    }




}
