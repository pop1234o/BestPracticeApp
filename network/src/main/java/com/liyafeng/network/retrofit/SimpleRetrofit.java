package com.liyafeng.network.retrofit;

/**
 * Created by liyafeng on 06/11/2017.
 *
 * http://square.github.io/retrofit/
 *
 *
 */

public class SimpleRetrofit {
}

/**
 *
 * java.lang.IllegalArgumentException: @Headers value must be in the form "Name: Value". Found: "User-agent=xxx"
 for method RequestService.getUser
 at retrofit2.ServiceMethod$Builder.methodError(ServiceMethod.java:752)
 at retrofit2.ServiceMethod$Builder.methodError(ServiceMethod.java:743)
 at retrofit2.ServiceMethod$Builder.parseHeaders(ServiceMethod.java:314)
 at retrofit2.ServiceMethod$Builder.parseMethodAnnotation(ServiceMethod.java:267)
 at retrofit2.ServiceMethod$Builder.build(ServiceMethod.java:172)
 at retrofit2.Retrofit.loadServiceMethod(Retrofit.java:170)
 at retrofit2.Retrofit$1.invoke(Retrofit.java:147)
 at java.lang.reflect.Proxy.invoke(Proxy.java:393)
 at $Proxy0.getUser(Unknown Source)
 at com.liyafeng.network.MainActivity.requestRetrofit(MainActivity.java:259)
 *
 *
 *=====================
 * FATAL EXCEPTION: main
 Process: com.liyafeng.network, PID: 10963
 android.os.NetworkOnMainThreadException
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
 at retrofit2.OkHttpCall.execute(OkHttpCall.java:180)
 at retrofit2.ExecutorCallAdapterFactory$ExecutorCallbackCall.execute(ExecutorCallAdapterFactory.java:91)
 at com.liyafeng.network.MainActivity.requestRetrofit(MainActivity.java:261)
 at com.liyafeng.network.MainActivity.access$000(MainActivity.java:47)
 at com.liyafeng.network.MainActivity$1.onClick(MainActivity.java:61)
 at android.view.View.performClick(View.java:5198)
 at android.view.View$PerformClick.run(View.java:21147)
 at android.os.Handler.handleCallback(Handler.java:739)
 at android.os.Handler.dispatchMessage(Handler.java:95)
 at android.os.Looper.loop(Looper.java:148)
 at android.app.ActivityThread.main(ActivityThread.java:5417)
 at java.lang.reflect.Method.invoke(Native Method)
 at com.android.internal.os.ZygoteInit$MethodAndArgsCaller.run(ZygoteInit.java:726)
 at com.android.internal.os.ZygoteInit.main(ZygoteInit.java:616)

 *
*
 *
 * requestRetrofit: Use JsonReader.setLenient(true) to accept malformed JSON at line 1 column 1 path $
  com.google.gson.stream.MalformedJsonException: Use JsonReader.setLenient(true) to accept malformed JSON at line 1 column 1 path $
      at com.google.gson.stream.JsonReader.syntaxError(JsonReader.java:1559)
      at com.google.gson.stream.JsonReader.checkLenient(JsonReader.java:1401)
      at com.google.gson.stream.JsonReader.doPeek(JsonReader.java:593)
      at com.google.gson.stream.JsonReader.peek(JsonReader.java:425)
      at com.google.gson.internal.bind.ReflectiveTypeAdapterFactory$Adapter.read(ReflectiveTypeAdapterFactory.java:205)
      at retrofit2.converter.gson.GsonResponseBodyConverter.convert(GsonResponseBodyConverter.java:37)
      at retrofit2.converter.gson.GsonResponseBodyConverter.convert(GsonResponseBodyConverter.java:25)
      at retrofit2.ServiceMethod.toResponse(ServiceMethod.java:119)
      at retrofit2.OkHttpCall.parseResponse(OkHttpCall.java:218)
      at retrofit2.OkHttpCall.execute(OkHttpCall.java:180)
      at retrofit2.ExecutorCallAdapterFactory$ExecutorCallbackCall.execute(ExecutorCallAdapterFactory.java:91)
      at com.liyafeng.network.MainActivity$10.run(MainActivity.java:265)
      at java.lang.Thread.run(Thread.java:818)
*
*
*
* */
