package com.liyafeng.network.okhttp;

/**
 * HTTP is the way modern applications network. It’s how we exchange data & media. Doing HTTP efficiently makes your stuff load faster and saves bandwidth.

 OkHttp is an HTTP client that’s efficient by default:

 HTTP/2 support allows all requests to the same host to share a socket.
 Connection pooling reduces request latency (if HTTP/2 isn’t available).
 Transparent GZIP shrinks download sizes.
 Response caching avoids the network completely for repeat requests.
 OkHttp perseveres when the network is troublesome: it will silently recover from common connection problems. If your service has multiple IP addresses OkHttp will attempt alternate addresses if the first connect fails. This is necessary for IPv4+IPv6 and for services hosted in redundant data centers. OkHttp initiates new connections with modern TLS features (SNI, ALPN), and falls back to TLS 1.0 if the handshake fails.

 Using OkHttp is easy. Its request/response API is designed with fluent builders and immutability. It supports both synchronous blocking calls and async calls with callbacks.

 OkHttp supports Android 2.3 and above. For Java, the minimum requirement is 1.7.
 *
 *
 *
 *
 * */
/**
 * Created by liyafeng on 01/11/2017.
 *
 *
 *
 *
 *
 *
 */

public class SimpleOkHttpClient {
}



/**
 * //找dns
 *
 *  android.os.NetworkOnMainThreadException
 at android.os.StrictMode$AndroidBlockGuardPolicy.onNetwork(StrictMode.java:1273)
 at java.net.InetAddress.lookupHostByName(InetAddress.java:431)
 at java.net.InetAddress.getAllByNameImpl(InetAddress.java:252)
 at java.net.InetAddress.getAllByName(InetAddress.java:215)
 at okhttp3.Dns$1.lookup(Dns.java:39)
 at okhttp3.internal.connection.RouteSelector.resetNextInetSocketAddress(RouteSelector.java:185)
 at okhttp3.internal.connection.RouteSelector.nextProxy(RouteSelector.java:149)
 at okhttp3.internal.connection.RouteSelector.next(RouteSelector.java:84)
 at okhttp3.internal.connection.StreamAllocation.findConnection(StreamAllocation.java:213)
 at okhttp3.internal.connection.StreamAllocation.findHealthyConnection(StreamAllocation.java:134)
 at okhttp3.internal.connection.StreamAllocation.newStream(StreamAllocation.java:113)
 at okhttp3.internal.connection.ConnectInterceptor.intercept(ConnectInterceptor.java:42)
 at okhttp3.internal.http.RealInterceptorChain.proceed(RealInterceptorChain.java:147)
 at okhttp3.internal.http.RealInterceptorChain.proceed(RealInterceptorChain.java:121)
 at okhttp3.internal.cache.CacheInterceptor.intercept(CacheInterceptor.java:93)
 at okhttp3.internal.http.RealInterceptorChain.proceed(RealInterceptorChain.java:147)
 at okhttp3.internal.http.RealInterceptorChain.proceed(RealInterceptorChain.java:121)
 at okhttp3.internal.http.BridgeInterceptor.intercept(BridgeInterceptor.java:93)
 at okhttp3.internal.http.RealInterceptorChain.proceed(RealInterceptorChain.java:147)
 at okhttp3.internal.http.RetryAndFollowUpInterceptor.intercept(RetryAndFollowUpInterceptor.java:125)
 at okhttp3.internal.http.RealInterceptorChain.proceed(RealInterceptorChain.java:147)
 at okhttp3.internal.http.RealInterceptorChain.proceed(RealInterceptorChain.java:121)
 at okhttp3.RealCall.getResponseWithInterceptorChain(RealCall.java:200)
 at okhttp3.RealCall.execute(RealCall.java:77)
 *
 *
 *
 *
 *
 * java.net.SocketTimeoutException: failed to connect to www.google.com/172.217.11.68 (port 80) after 10000ms
      at libcore.io.IoBridge.connectErrno(IoBridge.java:169)
      at libcore.io.IoBridge.connect(IoBridge.java:122)
      at java.net.PlainSocketImpl.connect(PlainSocketImpl.java:183)
      at java.net.PlainSocketImpl.connect(PlainSocketImpl.java:452)
      at java.net.Socket.connect(Socket.java:884)
      at okhttp3.internal.platform.AndroidPlatform.connectSocket(AndroidPlatform.java:69)
      at okhttp3.internal.connection.RealConnection.connectSocket(RealConnection.java:238)
      at okhttp3.internal.connection.RealConnection.connect(RealConnection.java:158)
      at okhttp3.internal.connection.StreamAllocation.findConnection(StreamAllocation.java:256)
      at okhttp3.internal.connection.StreamAllocation.findHealthyConnection(StreamAllocation.java:134)
      at okhttp3.internal.connection.StreamAllocation.newStream(StreamAllocation.java:113)
      at okhttp3.internal.connection.ConnectInterceptor.intercept(ConnectInterceptor.java:42)
      at okhttp3.internal.http.RealInterceptorChain.proceed(RealInterceptorChain.java:147)
      at okhttp3.internal.http.RealInterceptorChain.proceed(RealInterceptorChain.java:121)
      at okhttp3.internal.cache.CacheInterceptor.intercept(CacheInterceptor.java:93)
      at okhttp3.internal.http.RealInterceptorChain.proceed(RealInterceptorChain.java:147)
      at okhttp3.internal.http.RealInterceptorChain.proceed(RealInterceptorChain.java:121)
      at okhttp3.internal.http.BridgeInterceptor.intercept(BridgeInterceptor.java:93)
      at okhttp3.internal.http.RealInterceptorChain.proceed(RealInterceptorChain.java:147)
      at okhttp3.internal.http.RetryAndFollowUpInterceptor.intercept(RetryAndFollowUpInterceptor.java:125)
      at okhttp3.internal.http.RealInterceptorChain.proceed(RealInterceptorChain.java:147)
      at okhttp3.internal.http.RealInterceptorChain.proceed(RealInterceptorChain.java:121)
      at okhttp3.RealCall.getResponseWithInterceptorChain(RealCall.java:200)
      at okhttp3.RealCall.execute(RealCall.java:77)
      at com.liyafeng.network.MainActivity$6.run(MainActivity.java:159)
      at java.lang.Thread.run(Thread.java:818)
* */