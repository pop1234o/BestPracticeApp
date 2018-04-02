package com.liyafeng.practice;

import android.content.Context;

/**
 * Created by liyafeng on 16/11/2017.
 */

public class AndroidFrame {


    /**
     * 谈谈对Volley的理解?
     */
    public void a1() {
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

    /**
     * 图片库对比?
     * 图片库的源码分析?
     * 图片框架缓存实现？
     */
    public void a2() {
        /*
        * ===========图片库对比?===============
        * Picasso  square开发，使用双缓存，体积小，使用简单，但是不能加载gif
        * Glide  谷歌员工根据Picasso改进，功能强大，能加载gif，根据imageview大小进行缓存图片,
        *        但是需要注解处理器，自动生成代码GlideApp才能简洁配置占位图，错误图
        * Fresco Facebook开发，功能最强大，加载gif ,webp，将图片存在native堆中，减少OOM
        *       体积最大（2M），要使用DraweeView，原生不支持ImageView
        * ===============图片库的源码分析?======================
        * 都是使用双缓存，先读缓存，没有从网络加载（或者本地），然后写缓存
        *  分发主线程，渲染到ImageView上
        * =============图片框架缓存实现？========================
        * 都是用LruCache，DiskLruCache
        * Picasso用的http的缓存机制（OKHttp默认实现），不是自己做缓存？？？
        */
    }


    /**
     * 网络框架对比?
     * OkHttp和HttpUrlConnection区别？
     * 网络框架源码分析?
     */
    public void a3(Context context) {
        /*
        * xUtil 里的HttpUtil
        * Async-HttpClient 不维护了，基于HttpClient
        * Volley，Google出品，轻量级，适合数据量小、频繁的网络请求,基于HttpUrlConnection
        *       缺点是不支持同步，因为默认在内存中缓存，所以不适合请求数据量大的，或者请求量
        *       大的，容易OOM
        * Retrofit Square出品，内部使用OkHttp，支持RESTful API,(POST，GET,PUT，DELETE)
        *       使用注解定义请求，代码清晰
        * ， 提供了自定义Converter（序列化，反序列化）
        *       支持同步和异步的请求
        *       来解析自己定义的数据结构，默认可以用Json形式
        * ==============================OkHttp和HttpUrlConnection区别？=====================
        * 相同点：OkHttp和HttpUrlConnection 是相同职责，是Http协议的实现，内部都用的Socket
        *          从Android4.4开始HttpURLConnection的底层实现采用的是okHttp
        * 不同点：OkHttp使用拦截器的结构来对请求添加配置，我们可以自定义拦截器，使用更方便更灵活
        *       OkHttp内部使用http协议的请求头字段进行缓存，内部使用DiskLruCache
        *       而HttpUrlConnection默认没有实现缓存，需要我们自己写逻辑
        *
        * =======================网络框架源码分析?=======================
        * 见Network模块中的介绍
        */

        context.getResources().getDrawable(R.drawable.volley_construction);
        context.getResources().getDrawable(R.drawable.okhttp_construct);
        context.getResources().getDrawable(R.drawable.okhttp_cache);
    }

    /**
     * ===============OkHttp专题================
     * 说说OkHttp源码？
     * okhttp如何处理网络缓存的？
     * */
    public void a4(){
        /*
        * 主要是使用链式的拦截器，处理请求和响应，然后用Socket来处理请求
        * ================okhttp如何处理网络缓存的？=============
        * 里面用的Cache=>DiskLruCache，用url作为key，将整个Response保存
        * 他有个CacheInterceptor ，先从磁盘读取相应的Response对象
        * 读取响应的last-modify expire ,cache-control，etag响应头，判断是否过期
        * 没过期直接返回，如果过期
        * 然后请求的时候会加上 if-modify-since ，if-none-match 请求头（如果有）
        * 然后请求，如果没有修改，那么服务器直接返回304
        */
    }
}
