package com.liyafeng.network.okhttp;

/**
 * HTTP is the way modern applications network. It’s how we exchange data & media. Doing HTTP efficiently makes your stuff load faster and saves bandwidth.
 * <p>
 * OkHttp is an HTTP client that’s efficient by default:
 * <p>
 * HTTP/2 support allows all requests to the same host to share a socket.
 * Connection pooling reduces request latency (if HTTP/2 isn’t available).
 * Transparent GZIP shrinks download sizes.
 * Response caching avoids the network completely for repeat requests.
 * OkHttp perseveres when the network is troublesome: it will silently recover from common connection problems. If your service has multiple IP addresses OkHttp will attempt alternate addresses if the first connect fails. This is necessary for IPv4+IPv6 and for services hosted in redundant data centers. OkHttp initiates new connections with modern TLS features (SNI, ALPN), and falls back to TLS 1.0 if the handshake fails.
 * <p>
 * Using OkHttp is easy. Its request/response API is designed with fluent builders and immutability. It supports both synchronous blocking calls and async calls with callbacks.
 * <p>
 * OkHttp supports Android 2.3 and above. For Java, the minimum requirement is 1.7.
 */

import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Created by liyafeng on 01/11/2017.
 */

public class SimpleOkHttpClient {

    Dispatcher dispatcher;

    public Dispatcher dispatcher() {
        return dispatcher;
    }

    SimpleOkHttpClient(Builder builder) {

    }

    public static class Builder {
        public Builder() {
        }

        public SimpleOkHttpClient build() {
            return new SimpleOkHttpClient(this);
        }
    }

    public Call newCall(Request request) {
        return Call.RealCall.newRealCall(this, request);
    }

    interface Call {
        Response execute();


        /**
         * real
         */
        class RealCall implements Call {

            private final SimpleOkHttpClient client;
            private final Request request;

            public RealCall(SimpleOkHttpClient client, Request request) {
                this.client = client;
                this.request = request;
            }

            static RealCall newRealCall(SimpleOkHttpClient client, Request request) {
                return new RealCall(client, request);
            }

            @Override
            public Response execute() {
                client.dispatcher.executed(this);
                Response response = getResponseWithInterceptorChain();
                return response;
            }

            private Response getResponseWithInterceptorChain() {

                ArrayList<Interceptor> interceptors = new ArrayList<>();
                Interceptor.ConnectInterceptor connectInterceptor = new Interceptor.ConnectInterceptor();

                interceptors.add(connectInterceptor);

                Interceptor.RealInterceptorChain chain = new Interceptor.RealInterceptorChain(interceptors, 0, request);

                return chain.proceed();
            }
        }
    }

    static class Dispatcher {

        void executed(Call call) {

        }
    }

    interface Interceptor {
        Response intercept(Chain chain);

        class ConnectInterceptor implements Interceptor {

            @Override
            public Response intercept(Chain chain) {
                Request request = chain.request();
                StreamAllocation allocation = new StreamAllocation();
                try {
                    String s = allocation.newStream(request);
                    Response response = new Response(s);
                    return response;
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }
        }


        interface Chain {
            Response proceed();

            Request request();
        }

        class RealInterceptorChain implements Chain {

            private ArrayList<Interceptor> interceptors;
            private int index;


            private Request request;

            public RealInterceptorChain(ArrayList<Interceptor> interceptors, int index, Request request) {

                this.interceptors = interceptors;
                this.index = index;
                this.request = request;
            }

            @Override
            public Response proceed() {

                Interceptor interceptor = interceptors.get(index);
                RealInterceptorChain chain = new RealInterceptorChain(interceptors, index + 1, request);
                Response response = interceptor.intercept(chain);
                return response;
            }

            @Override
            public Request request() {
                return request;
            }


        }


    }

    static final class StreamAllocation {

        public String newStream(Request request) throws IOException {
            Socket socket = new Socket(request.url, 80);
            String req = "GET www.google.com http1.0";
            socket.getOutputStream().write(req.getBytes());
            InputStream inputStream = socket.getInputStream();
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            byte[] bytes = new byte[1024];
            int length = 0;
            while ((length = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, length);
            }
            String s = new String(outputStream.toByteArray());

            socket.close();
            return s;
        }
    }

    static class Response {
        public Response(String s) {
            Log.i("test", "Response: "+s);
        }
    }

    static class Request {
        private String url;

        public Request(Builder builder) {
            url = builder.url;
        }

        static class Builder {
            private String url;

            public Builder url(String url) {
                this.url = url;
                return this;
            }

            public Request build() {
                return new Request(this);
            }
        }
    }
}


/**
 * //找dns
 * <p>
 * android.os.NetworkOnMainThreadException
 * at android.os.StrictMode$AndroidBlockGuardPolicy.onNetwork(StrictMode.java:1273)
 * at java.net.InetAddress.lookupHostByName(InetAddress.java:431)
 * at java.net.InetAddress.getAllByNameImpl(InetAddress.java:252)
 * at java.net.InetAddress.getAllByName(InetAddress.java:215)
 * at okhttp3.Dns$1.lookup(Dns.java:39)
 * at okhttp3.internal.connection.RouteSelector.resetNextInetSocketAddress(RouteSelector.java:185)
 * at okhttp3.internal.connection.RouteSelector.nextProxy(RouteSelector.java:149)
 * at okhttp3.internal.connection.RouteSelector.next(RouteSelector.java:84)
 * at okhttp3.internal.connection.StreamAllocation.findConnection(StreamAllocation.java:213)
 * at okhttp3.internal.connection.StreamAllocation.findHealthyConnection(StreamAllocation.java:134)
 * at okhttp3.internal.connection.StreamAllocation.newStream(StreamAllocation.java:113)
 * at okhttp3.internal.connection.ConnectInterceptor.intercept(ConnectInterceptor.java:42)
 * at okhttp3.internal.http.RealInterceptorChain.proceed(RealInterceptorChain.java:147)
 * at okhttp3.internal.http.RealInterceptorChain.proceed(RealInterceptorChain.java:121)
 * at okhttp3.internal.cache.CacheInterceptor.intercept(CacheInterceptor.java:93)
 * at okhttp3.internal.http.RealInterceptorChain.proceed(RealInterceptorChain.java:147)
 * at okhttp3.internal.http.RealInterceptorChain.proceed(RealInterceptorChain.java:121)
 * at okhttp3.internal.http.BridgeInterceptor.intercept(BridgeInterceptor.java:93)
 * at okhttp3.internal.http.RealInterceptorChain.proceed(RealInterceptorChain.java:147)
 * at okhttp3.internal.http.RetryAndFollowUpInterceptor.intercept(RetryAndFollowUpInterceptor.java:125)
 * at okhttp3.internal.http.RealInterceptorChain.proceed(RealInterceptorChain.java:147)
 * at okhttp3.internal.http.RealInterceptorChain.proceed(RealInterceptorChain.java:121)
 * at okhttp3.RealCall.getResponseWithInterceptorChain(RealCall.java:200)
 * at okhttp3.RealCall.execute(RealCall.java:77)
 * <p>
 * <p>
 * <p>
 * <p>
 * <p>
 * java.net.SocketTimeoutException: failed to connect to www.google.com/172.217.11.68 (port 80) after 10000ms
 * at libcore.io.IoBridge.connectErrno(IoBridge.java:169)
 * at libcore.io.IoBridge.connect(IoBridge.java:122)
 * at java.net.PlainSocketImpl.connect(PlainSocketImpl.java:183)
 * at java.net.PlainSocketImpl.connect(PlainSocketImpl.java:452)
 * at java.net.Socket.connect(Socket.java:884)
 * at okhttp3.internal.platform.AndroidPlatform.connectSocket(AndroidPlatform.java:69)
 * at okhttp3.internal.connection.RealConnection.connectSocket(RealConnection.java:238)
 * at okhttp3.internal.connection.RealConnection.connect(RealConnection.java:158)
 * at okhttp3.internal.connection.StreamAllocation.findConnection(StreamAllocation.java:256)
 * at okhttp3.internal.connection.StreamAllocation.findHealthyConnection(StreamAllocation.java:134)
 * at okhttp3.internal.connection.StreamAllocation.newStream(StreamAllocation.java:113)
 * at okhttp3.internal.connection.ConnectInterceptor.intercept(ConnectInterceptor.java:42)
 * at okhttp3.internal.http.RealInterceptorChain.proceed(RealInterceptorChain.java:147)
 * at okhttp3.internal.http.RealInterceptorChain.proceed(RealInterceptorChain.java:121)
 * at okhttp3.internal.cache.CacheInterceptor.intercept(CacheInterceptor.java:93)
 * at okhttp3.internal.http.RealInterceptorChain.proceed(RealInterceptorChain.java:147)
 * at okhttp3.internal.http.RealInterceptorChain.proceed(RealInterceptorChain.java:121)
 * at okhttp3.internal.http.BridgeInterceptor.intercept(BridgeInterceptor.java:93)
 * at okhttp3.internal.http.RealInterceptorChain.proceed(RealInterceptorChain.java:147)
 * at okhttp3.internal.http.RetryAndFollowUpInterceptor.intercept(RetryAndFollowUpInterceptor.java:125)
 * at okhttp3.internal.http.RealInterceptorChain.proceed(RealInterceptorChain.java:147)
 * at okhttp3.internal.http.RealInterceptorChain.proceed(RealInterceptorChain.java:121)
 * at okhttp3.RealCall.getResponseWithInterceptorChain(RealCall.java:200)
 * at okhttp3.RealCall.execute(RealCall.java:77)
 * at com.liyafeng.network.MainActivity$6.run(MainActivity.java:159)
 * at java.lang.Thread.run(Thread.java:818)
 * <p>
 * <p>
 * <p>
 * Unable to resolve host "www.google.com": No address associated with hostname
 * java.net.UnknownHostException: Unable to resolve host "www.google.com": No address associated with hostname
 * at java.net.InetAddress.lookupHostByName(InetAddress.java:470)
 * at java.net.InetAddress.getAllByNameImpl(InetAddress.java:252)
 * at java.net.InetAddress.getAllByName(InetAddress.java:215)
 * at okhttp3.Dns$1.lookup(Dns.java:39)
 * at okhttp3.internal.connection.RouteSelector.resetNextInetSocketAddress(RouteSelector.java:185)
 * at okhttp3.internal.connection.RouteSelector.nextProxy(RouteSelector.java:149)
 * at okhttp3.internal.connection.RouteSelector.next(RouteSelector.java:84)
 * at okhttp3.internal.connection.StreamAllocation.findConnection(StreamAllocation.java:213)
 * at okhttp3.internal.connection.StreamAllocation.findHealthyConnection(StreamAllocation.java:134)
 * at okhttp3.internal.connection.StreamAllocation.newStream(StreamAllocation.java:113)
 * at okhttp3.internal.connection.ConnectInterceptor.intercept(ConnectInterceptor.java:42)
 * at okhttp3.internal.http.RealInterceptorChain.proceed(RealInterceptorChain.java:147)
 * at okhttp3.internal.http.RealInterceptorChain.proceed(RealInterceptorChain.java:121)
 * at okhttp3.internal.cache.CacheInterceptor.intercept(CacheInterceptor.java:93)
 * at okhttp3.internal.http.RealInterceptorChain.proceed(RealInterceptorChain.java:147)
 * at okhttp3.internal.http.RealInterceptorChain.proceed(RealInterceptorChain.java:121)
 * at okhttp3.internal.http.BridgeInterceptor.intercept(BridgeInterceptor.java:93)
 * at okhttp3.internal.http.RealInterceptorChain.proceed(RealInterceptorChain.java:147)
 * at okhttp3.internal.http.RetryAndFollowUpInterceptor.intercept(RetryAndFollowUpInterceptor.java:125)
 * at okhttp3.internal.http.RealInterceptorChain.proceed(RealInterceptorChain.java:147)
 * at okhttp3.internal.http.RealInterceptorChain.proceed(RealInterceptorChain.java:121)
 * at okhttp3.RealCall.getResponseWithInterceptorChain(RealCall.java:200)
 * at okhttp3.RealCall.execute(RealCall.java:77)
 * at retrofit2.OkHttpCall.execute(OkHttpCall.java:180)
 * at retrofit2.ExecutorCallAdapterFactory$ExecutorCallbackCall.execute(ExecutorCallAdapterFactory.java:91)
 * at com.liyafeng.network.MainActivity$10.run(MainActivity.java:265)
 * at java.lang.Thread.run(Thread.java:818)
 * Caused by: android.system.GaiException: android_getaddrinfo failed: EAI_NODATA (No address associated with hostname)
 * at libcore.io.Posix.android_getaddrinfo(Native Method)
 * at libcore.io.ForwardingOs.android_getaddrinfo(ForwardingOs.java:55)
 * at java.net.InetAddress.lookupHostByName(InetAddress.java:451)
 * ... 26 more
 */