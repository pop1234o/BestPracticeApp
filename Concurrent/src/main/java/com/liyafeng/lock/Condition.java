package com.liyafeng.lock;

/**
 * Created by liyafeng on 2018/3/19.
 *
 * 用来代替 Object中的wait(),notify(), notifyAll()
 * 可以指定唤醒某个线程，比Object更灵活
 * 他要在Lock子类的lock和unlock间使用
 * 可以参考{@link java.util.concurrent.locks.AbstractQueuedSynchronizer}
 * 中的ConditionObject。
 * {@link java.util.concurrent.locks.ReentrantLock}
 * 中有使用方法
 *
 * {@link java.util.concurrent.locks.Condition}
 * ====================
 * Condition 是AQS的内部类，所以一个Condition对应一个AQS
 *
 */

public class Condition {
}
