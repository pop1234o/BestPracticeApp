package com.liyafeng.network.okhttp;

public class Main {


    /**
     * ======networkInterceptors+Interceptors区别
     *
     * https://www.zybuluo.com/Warning1943/note/699633
     *
     * 如果是cache读取的就不会经过networkInterceptors
     *
     * ================为何 response.body().string() 只能调用一次？ =======
     * https://blog.csdn.net/my_truelove/article/details/78998996(为何 response.body().string() 只能调用一次？)
     * 因为响应体的内容可能很大，所以我们在内存中保存响应体，而是持有数据流的链接，我们读完后它会自动关闭。
     *
     *
     */
    void a1(){}
}
