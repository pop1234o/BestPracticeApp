package com.liyafeng.network.httpurlconnection;

/**
 * Created by liyafeng on 01/11/2017.
 */

public class SimpleHttpURLConnection {
}

/**
 *
 *
 *   android.os.NetworkOnMainThreadException
 at android.os.StrictMode$AndroidBlockGuardPolicy.onNetwork(StrictMode.java:1273)
 at java.net.InetAddress.lookupHostByName(InetAddress.java:431)
 at java.net.InetAddress.getAllByNameImpl(InetAddress.java:252)
 at java.net.InetAddress.getAllByName(InetAddress.java:215)
 at com.android.okhttp.internal.Network$1.resolveInetAddresses(Network.java:29)
 at com.android.okhttp.internal.http.RouteSelector.resetNextInetSocketAddress(RouteSelector.java:188)
 at com.android.okhttp.internal.http.RouteSelector.nextProxy(RouteSelector.java:157)
 at com.android.okhttp.internal.http.RouteSelector.next(RouteSelector.java:100)
 at com.android.okhttp.internal.http.HttpEngine.createNextConnection(HttpEngine.java:357)
 at com.android.okhttp.internal.http.HttpEngine.nextConnection(HttpEngine.java:340)
 at com.android.okhttp.internal.http.HttpEngine.connect(HttpEngine.java:330)
 at com.android.okhttp.internal.http.HttpEngine.sendRequest(HttpEngine.java:248)
 at com.android.okhttp.internal.huc.HttpURLConnectionImpl.execute(HttpURLConnectionImpl.java:433)
 at com.android.okhttp.internal.huc.HttpURLConnectionImpl.getResponse(HttpURLConnectionImpl.java:384)
 at com.android.okhttp.internal.huc.HttpURLConnectionImpl.getResponseCode(HttpURLConnectionImpl.java:497)
 at com.liyafeng.network.MainActivity.requestHttpUrlConnection(MainActivity.java:64)
 at com.liyafeng.network.MainActivity.access$000(MainActivity.java:36)
 at com.liyafeng.network.MainActivity$1.onClick(MainActivity.java:50)
*
 *
 *
 *
 *
 *
 * java.net.SocketTimeoutException: failed to connect to www.google.com/172.217.11.68 (port 80) after 1000ms
      at libcore.io.IoBridge.connectErrno(IoBridge.java:169)
      at libcore.io.IoBridge.connect(IoBridge.java:122)
      at java.net.PlainSocketImpl.connect(PlainSocketImpl.java:183)
      at java.net.PlainSocketImpl.connect(PlainSocketImpl.java:452)
      at java.net.Socket.connect(Socket.java:884)
      at com.android.okhttp.internal.Platform.connectSocket(Platform.java:117)
      at com.android.okhttp.internal.http.SocketConnector.connectRawSocket(SocketConnector.java:160)
      at com.android.okhttp.internal.http.SocketConnector.connectCleartext(SocketConnector.java:67)
      at com.android.okhttp.Connection.connect(Connection.java:152)
      at com.android.okhttp.Connection.connectAndSetOwner(Connection.java:185)
      at com.android.okhttp.OkHttpClient$1.connectAndSetOwner(OkHttpClient.java:128)
      at com.android.okhttp.internal.http.HttpEngine.nextConnection(HttpEngine.java:341)
      at com.android.okhttp.internal.http.HttpEngine.connect(HttpEngine.java:330)
      at com.android.okhttp.internal.http.HttpEngine.sendRequest(HttpEngine.java:248)
      at com.android.okhttp.internal.huc.HttpURLConnectionImpl.execute(HttpURLConnectionImpl.java:433)
      at com.android.okhttp.internal.huc.HttpURLConnectionImpl.getResponse(HttpURLConnectionImpl.java:384)
      at com.android.okhttp.internal.huc.HttpURLConnectionImpl.getResponseCode(HttpURLConnectionImpl.java:497)
      at com.liyafeng.network.MainActivity$2$override.run(MainActivity.java:69)
      at com.liyafeng.network.MainActivity$2$override.access$dispatch(MainActivity.java)
      at com.liyafeng.network.MainActivity$2.run(MainActivity.java:0)
* */