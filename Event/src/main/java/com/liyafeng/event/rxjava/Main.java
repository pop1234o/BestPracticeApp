package com.liyafeng.event.rxjava;

/**
 * Created by liyafeng on 2018/4/28.
 */

public class Main {

    /**
     * a library for composing asynchronous
     * and event-based programs by using observable sequences.
     * 一个用来异步的基于事件的库，通过使用被观察者队列。
     *
     * https://github.com/ReactiveX/RxJava（官方库）
     *
     * （airbnb的rxjava讲解，原来任何人都会面对困难，不是所有人都一看就懂，都有一个过程
     * 都有一个学习曲线，他们也花了2个月来熟悉rxjava，也不是一天两天都弄懂）
     * https://academy.realm.io/cn/posts/kau-felipe-lima-adopting-rxjava-airbnb-android/
     * <p>
     * io.reactivex.Flowable: 0..N flows, supporting Reactive-Streams and backpressure
     * io.reactivex.Observable: 0..N flows, no backpressure,
     * io.reactivex.Single: a flow of exactly 1 item or an error,
     * io.reactivex.Completable: a flow without items but only a completion or error signal,
     * io.reactivex.Maybe: a flow with no items, exactly one item or an error.
     *
     * ============================为什么要用rxjava=========================
     * 从我们传统的imperative（命令） 编程转化为 reactive（响应式） 编程
     * 以数据流为中心的编程方式，这样使得我们专注于数据流的处理，
     * 而不是一些线程切换，回调。
     *
     *
     * @param args
     */
    public static void main(String[] args) {

    }
}
