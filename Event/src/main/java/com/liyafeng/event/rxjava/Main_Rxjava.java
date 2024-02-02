package com.liyafeng.event.rxjava;

/**
 * Created by liyafeng on 2018/4/28.
 */

public class Main_Rxjava {

    /**
     * (官方文档 wiki)
     * https://github.com/ReactiveX/RxJava/wiki/How-To-Use-RxJava
     * 中文文档
     * https://mcxiaoke.gitbooks.io/rxdocs/content/
     * <p>
     * a library for composing asynchronous
     * and event-based programs by using observable sequences.
     * 一个用来异步的基于事件的库，通过使用被观察者队列。
     * <p>
     * https://github.com/ReactiveX/RxJava（官方库）
     * <p>
     * （airbnb的rxjava讲解，原来任何人都会面对困难，不是所有人都一看就懂，都有一个过程
     * 都有一个学习曲线，他们也花了2个月来熟悉rxjava，也不是一天两天都弄懂）
     * https://academy.realm.io/cn/posts/kau-felipe-lima-adopting-rxjava-airbnb-android/
     * <p>
     * io.reactivex.Flowable: 0..N flows, supporting Reactive-Streams and backpressure
     * io.reactivex.Observable: 0..N flows, no backpressure,
     * io.reactivex.Single: a flow of exactly 1 item or an error,
     * io.reactivex.Completable: a flow without items but only a completion or error signal,
     * io.reactivex.Maybe: a flow with no items, exactly one item or an error.
     * ================如何学习rxjava================
     * https://mcxiaoke.gitbooks.io/rxdocs/content/
     * 我们最好先理解 被观察者 向 观察者发送事件的原理
     * 然后学习操作符，操作符很多，我们最好看图来记
     * 时间线，事件，error等
     *
     * <p>
     * ============================为什么要用rxjava=========================
     * 从我们传统的imperative（命令） 编程转化为 reactive（响应式） 编程
     * 以数据流为中心的编程方式，这样使得我们专注于数据流的处理，
     * 而不是一些线程切换，回调。
     * <p>
     * 一般我们返回一个数据源，然后我们用流式处理他
     * <p>
     * 这样就使得数据处理的流程和流程中处理数据的细节解耦，在工程复杂的情况下修改逻辑不容易引起bug
     * <p>
     * <p>
     * 可以消除回调地狱，我们之前在model层请求网络或者io操作，
     * 可能还有localmodel和remotelocal ，还要加回调到model
     * 然后要回调到presenter，这就要很多回调
     * 但是用Rxjava就可以消除这种，将线程操作或者网络请求放到rxjava中的子线程
     * 然后返回直接return，不用回调，然后我们切换到主线程做更新UI的操作
     * 这样就省去了回调，和handler切换线程。代码就会清晰很多。
     * <p>
     * <p>
     * ===================rxjava结合mvp=====
     * http://wuxiaolong.me/2016/06/12/mvpRetrofitRxjava/
     * <p>
     * 网络层返回一个Observable<T>对象 ，在Presenter中处理它，然后数据流处理完，交给view层来更新
     * <p>
     * <p>
     * https://www.jianshu.com/p/a7e58fab4ff1
     * <p>
     * 将传统的model层回调，变为model层直接返回Observable对象，这样就省去了写回调方法
     * <p>
     * 但是有可能我们获得model层的数据后需要在model层做统一处理。。。
     * <p>
     * 我们也可以封装一层callback，如果有统一处理就在model中callback,然后在present中传入callback
     * 如果没有统一处理，那么直接用presenter传入的callback
     * <p>
     * ====================rxjava的io和 computation======
     * io用来读取或者写入数据流，线程池是无界的
     * computation 用来执行计算敏感的逻辑，线程池有界（因为cpu有限，所以并不是线程越多越好
     * 反而会因为线程多导致频繁切换 ，导致性能开销）
     * <p>
     * ===========什么是背压 backpressure=========
     * ﻿https://github.com/ReactiveX/RxJava/wiki/Backpressure
     * ﻿背压是指在异步场景中，被观察者发送事件速度远快于观察者的处理速度的情况下，一种告诉上游的被观察者降低发送速度的策略。
     * 如果事件发送快，观察者来不及处理，那么只能将数据缓存，这样很浪费资源，所以我们用背压策略来限制事件的发送速度
     *
     *
     * =============学习资料==========
     * https://mcxiaoke.gitbooks.io/rxdocs/content/ （中文文档，里面有各种操作符用法）
     * http://gank.io/post/560e15be2dca930e00da1083#toc_8 （扔物线 朱凯写的rxjava详解）
     * https://www.jianshu.com/p/fb68dfeee66d （RxJava学习笔记）
     * http://reactivex.io/documentation/observable.html （官方文档）
     *
     *
     *
     *
     *
     * @param args
     */
    public static void main(String[] args) {

    }


    /**
     * ==========主要操作符==========
     * Creating 创建操作 - Create/Defer/From/Just/Start/Repeat/Range
     * Transforming 变换操作 - Buffer/Window/Map/FlatMap/GroupBy/Scan
     * Filtering 过滤操作 - Debounce/Distinct/Filter/Sample/Skip/Take
     * Combining 结合操作 - And/StartWith/Join/Merge/Switch/Zip
     * Error Handling 错误处理 - Catch/Retry
     * Utility 辅助操作 - Delay/Do/ObserveOn/SubscribeOn/Subscribe
     * Conditional 条件和布尔操作 - All/Amb/Contains/SkipUntil/TakeUntil
     * Mathematical 算术和聚合操作 - Average/Concat/Count/Max/Min/Sum/Reduce
     * Async 异步操作 - Start/ToAsync/StartFuture/FromAction/FromCallable/RunAsync
     * Connect 连接操作 - Connect/Publish/RefCount/Replay
     * Convert 转换操作 - ToFuture/ToList/ToIterable/ToMap/toMultiMap
     * Blocking 阻塞操作 - ForEach/First/Last/MostRecent/Next/Single/Latest
     * String 字符串操作 - ByLine/Decode/Encode/From/Join/Split/StringConcat
     *
     *
     * RxJava 2 提供了丰富的操作符，用于对事件流进行处理和转换。以下是一些主要的操作符：
     *
     * 1. map：用于对事件流中的每个事件进行映射转换，将事件转换为另一种类型。
     *
     * 2. filter：用于过滤事件流中的事件，只保留满足条件的事件。
     *
     * 3. flatMap：用于将每个事件转换为一个新的 Observable，并将这些 Observables 合并成一个单一的 Observable。
     *
     * 4. concatMap：类似于 flatMap，但是保证事件的顺序性，即按照原始顺序发射事件。
     *
     * 5. zip：将多个 Observable 发射的事件组合成一个新的事件，然后发射这些组合的事件。
     *
     * 6. merge：将多个 Observable 发射的事件合并成一个单一的事件流，无序合并。
     *
     * 7. reduce：对事件流中的事件进行累积操作，得到一个最终的结果并发射出去。
     *
     * 8. take：仅发射事件流中的前 N 个事件，忽略后续的事件。
     *
     * 9. debounce：用于过滤掉事件流中发射频率过快的事件，只保留最后一个事件。
     *
     * 10. retry：在发生错误时，重新订阅事件流，可以指定重试次数。
     *
     * 这些操作符可以帮助开发者对事件流进行各种处理和转换，从而实现丰富的异步操作和事件处理逻辑。
     *
     *
     *
     *
     */
    void a1(){}

}
