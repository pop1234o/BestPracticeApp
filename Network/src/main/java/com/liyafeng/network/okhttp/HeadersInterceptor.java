/**
 * Created by Rockey on 2019/2/11
 * Desc:
 */

package com.liyafeng.network.okhttp;



import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 配置请求头部信息
 */
public class HeadersInterceptor implements Interceptor {

    public HeadersInterceptor() {
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Request.Builder builder = request.newBuilder();
//        builder.addHeader("token", SPUtil.getToken());
        request = builder.build();
        return chain.proceed(request);
    }
}
