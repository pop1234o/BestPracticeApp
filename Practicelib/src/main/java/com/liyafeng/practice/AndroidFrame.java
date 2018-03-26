package com.liyafeng.practice;

/**
 * Created by liyafeng on 16/11/2017.
 */

public class AndroidFrame {
    
    
    /**
     * 谈谈对Volley的理解?
     * */
    public void a1(){
        /*
        * Volley=>{RequestQueue类,}
        * RequestQueue=》{
        *  Cache（实现缓存的接口）从构造方法传入 默认是DiskBasedCache 从磁盘中读取
        *  ResponseDelivery（响应方法器）从构造方法传入,负责响应的线程切换
        *       这个里面主要是用handler切换到主线程，然后request.deliverResponse();
        *  NetWork（真正发起http请求的）从构造方法传入，负责真正请求网络
        *
        *  NetworkDispatcher[poolSize](Tread子类用于读取networkQueue的 )
        *         poolSize从构造方法传入
        *  负责从网络队列中取request，然后请求网络
        *
        *  CacheDispatcher 就一个，是Tread子类，用于读取cacheQueue
        *  负责从缓存队列中取request，然后用Cache获取缓存
        *
        *  mCacheQueue, mNetworkQueue,这两个都是存放请求对象的对方，是 PriorityBlockingQueue
        *
        * }
        *
        * Request=>RetryPolicy 这个是重试策略，一般设置超时时间
        *    当请求超时的时候，我们会调用这个方法
        * RequestQueue中、会判断这个请求（的响应）是否可以用缓存
        *      如果不可以，直接加入网络请求队列（这是个优先队列），然后
        *   直接从网络获取响应
        *      如果可以读缓存，那么加入缓存队列。
        *
        * ======================流程========================
        * new Request对象，如果使用缓存，加入缓存队列，然后缓存线程从缓存中取，
        * 如果取出来为null,那么直接加入 网络队列，
        * 如果取出来不为null,那么判断是否需要刷新，如果不需要刷新，直接回调响应的方法
        * 如果需要刷新，那么先返回旧的response，然后再讲request加入网络队列
        *
        * 网络线程，从网络队列中取出request，然后用network对象performRequest();
        * 返回响应，Request对象解析 响应流 成Response对象，mDelivery分发响应
        *
        *
        */
    }
}
