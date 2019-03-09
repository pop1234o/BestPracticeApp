package com.liyafeng.event.rxjava;

/**
 * Created by liyafeng on 2018/4/28.
 */

public class Main {

    /**
     * (官方文档 wiki)
     * https://github.com/ReactiveX/RxJava/wiki/How-To-Use-RxJava
     *
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
     * 这样就使得数据处理的流程和流程中处理数据的细节解耦，在工程复杂的情况下修改逻辑不容易引起bug
     *
     *
     *  可以消除回调地狱，我们之前在model层请求网络或者io操作，
     * 可能还有localmodel和remotelocal ，还要加回调到model
     * 然后要回调到presenter，这就要很多回调
     * 但是用Rxjava就可以消除这种，将线程操作或者网络请求放到rxjava中的子线程
     * 然后返回直接return，不用回调，然后我们切换到主线程做更新UI的操作
     * 这样就省去了回调，和handler切换线程。代码就会清晰很多。
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
     * ====================rxjava的io和 computation======
     * io用来读取或者写入数据流，线程池是无界的
     * computation 用来执行计算敏感的逻辑，线程池有界（因为cpu有限，所以并不是线程越多越好
     * 反而会因为线程多导致频繁切换 ，导致性能开销）
     *
     * ===========什么是背压 backpressure=========
     * ﻿https://github.com/ReactiveX/RxJava/wiki/Backpressure
     * ﻿背压是指在异步场景中，被观察者发送事件速度远快于观察者的处理速度的情况下，一种告诉上游的被观察者降低发送速度的策略。
     *
     *
     * @param args
     */
    public static void main(String[] args) {

    }
}
