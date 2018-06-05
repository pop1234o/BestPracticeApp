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
     * 一般我们返回一个数据源，然后我们用流式处理他
     *
     *
     * ===================rxjava结合mvp=====
     * http://wuxiaolong.me/2016/06/12/mvpRetrofitRxjava/
     *
     * 网络层返回一个Observable<T>对象 ，在Presenter中处理它，然后数据流处理完，交给view层来更新
     *
     *
     * https://www.jianshu.com/p/a7e58fab4ff1
     *
     * 将传统的model层回调，变为model层直接返回Observable对象，这样就省去了写回调方法
     *
     * 但是有可能我们获得model层的数据后需要在model层做统一处理。。。
     *
     * 我们也可以封装一层callback，如果有统一处理就在model中callback,然后在present中传入callback
     * 如果没有统一处理，那么直接用presenter传入的callback
     *
     *
     *
     *
     * @param args
     */
    public static void main(String[] args) {

    }
}
