package com.liyafeng.practice;

import android.content.Context;

/**
 * Created by liyafeng on 16/11/2017.
 */

public class AndroidFrame {


    //region 网络请求框架
    //region volley

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

    //endregion
    //region okhttp

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
     *
     *
     *
     * OkHttp是一个用于处理网络请求的开源库，其实现思路主要包括以下几个方面：
     *
     * 1. 底层网络通信：OkHttp使用Java的Socket和URLConnection等API来进行底层的网络通信，包括建立连接、发送请求、接收响应等操作。
     *
     * 2. 连接池管理：OkHttp通过连接池管理网络连接，以提高网络请求的效率和性能。它可以复用已经建立的连接，减少连接的建立和关闭次数。
     *
     * 3. 请求拦截器和响应拦截器：OkHttp通过拦截器机制来处理请求和响应，可以在发送请求前和接收响应后进行一系列的处理，比如添加公共参数、处理重定向、处理缓存等。
     *
     * 4. 异步请求和回调：OkHttp支持异步的网络请求，可以通过回调机制来处理网络请求的结果，包括成功的响应、失败的错误等情况。
     *
     * 5. 缓存策略：OkHttp支持缓存策略，可以根据缓存的有效期、缓存的控制头等信息来处理缓存的读取和存储。
     *
     * 总的来说，OkHttp通过底层网络通信、连接池管理、拦截器机制、异步请求和回调、缓存策略等方式来实现网络请求的处理，提供了高效、灵活和可定制的网络请求功能。
     *
     *
     * =====okhttp如何处理网络缓存的？
     * OkHttp处理网络缓存的方式主要包括以下几个方面：
     *
     * 1. 缓存策略：OkHttp通过Cache-Control和Expires等HTTP响应头来确定缓存策略，包括缓存的有效期、缓存的控制方式等。
     *
     * 2. 缓存存储：OkHttp使用DiskLruCache来进行缓存的存储，将网络请求的响应数据存储到本地磁盘中，以便后续的读取和使用。
     *
     * 3. 缓存的读取：在发起网络请求时，OkHttp会根据缓存策略来判断是否可以使用缓存的响应数据，如果满足缓存条件，则直接从缓存中读取响应数据，而不发起实际的网络请求。
     *
     * 4. 缓存的更新：当缓存的响应数据过期或者需要刷新时，OkHttp会发起实际的网络请求，并将新的响应数据存储到缓存中，以便后续的读取和使用。
     *
     * 总的来说，OkHttp通过缓存策略、缓存存储、缓存的读取和缓存的更新等方式来处理网络缓存，以提高网络请求的效率和性能。
     *
     */
    public void a4() {
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
    //endregion

    //region retrofit
    /**
     * 说说retrofit原理
     *
     * Retrofit是一个基于OkHttp的RESTful HTTP网络请求库，其原理主要包括以下几个方面：
     *
     * 1. 接口定义：通过定义Java接口的方式来描述网络请求的各种操作，包括请求方法、请求路径、请求参数、请求头等信息。
     *
     * 2. 注解处理器：Retrofit使用注解处理器来解析接口中的注解，比如@GET、@POST、@Query等，以生成对应的网络请求实现代码。
     *
     * 3. 动态代理：Retrofit使用动态代理技术来创建接口的代理对象，当调用接口方法时，实际上是通过代理对象来发起网络请求。
     *
     * 4. Converter：Retrofit使用Converter来处理请求和响应的数据转换，可以将请求参数和响应数据转换为Java对象或其他格式。
     *
     * 5. Call Adapter：Retrofit使用Call Adapter来处理网络请求的调度和执行，可以将网络请求转换为可观察对象、回调对象等形式。
     *
     * 总的来说，Retrofit通过接口定义、注解处理器、动态代理、Converter和Call Adapter等技术来实现网络请求的封装和处理，提供了简洁、灵活和可定制的网络请求功能。
     * */
    public void a5(){
        /*
        *
        */
    }
    //endregion


    //endregion


    //region 图片加载框架

    /**
     * 图片库对比?
     * 图片库的源码分析?
     * 图片框架缓存实现？
     *
     * =====说说glide原理
     *
     * Glide是一个用于加载和缓存图片的开源库，其原理主要包括以下几个方面：
     *
     * 1. 请求管理：Glide通过RequestManager来管理图片加载请求，包括加载图片、设置占位符、设置错误图片等操作。
     *
     * 2. 图片加载：Glide使用网络请求库（通常是OkHttp）来加载图片，可以从网络、本地文件、资源文件等位置加载图片数据。
     *
     * 3. 内存缓存：Glide使用内存缓存来存储最近使用的图片数据，以便快速的再次加载。
     *
     * 4. 磁盘缓存：Glide使用磁盘缓存来存储长期不使用的图片数据，以便在需要时从本地磁盘加载。
     *
     * 5. 图片解码：Glide使用图片解码器来将原始的图片数据解码为Bitmap对象，以便在应用中显示。
     *
     * 6. 图片变换：Glide支持对图片进行各种变换操作，比如裁剪、缩放、圆角处理等。
     *
     * 总的来说，Glide通过请求管理、图片加载、内存缓存、磁盘缓存、图片解码和图片变换等方式来实现图片的加载和缓存，
     * 提供了高效、灵活和可定制的图片加载功能。
     *
     *
     *
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
        *
        * ========fresco和glide对比===========
        * glide默认加载控件大小的图片，而且缓存的也是，如果下次要加载大图需要重新请求网络
        * 但是我们可以设置缓存策略，全部缓存
        *
        * fresco 默认加载原图，我们可以通过代码来加载指定尺寸的图片，这样节约了内存
        *
        *
        *
        *
        * https://www.jianshu.com/p/6729dc17586b
        */
    }

    //endregion


}
