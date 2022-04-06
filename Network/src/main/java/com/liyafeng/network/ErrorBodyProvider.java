package com.liyafeng.network;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.liyafeng.network.response.ResponseEntity;

import java.nio.charset.Charset;

import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.ResponseBody;
import okhttp3.internal.http.HttpHeaders;
import okio.Buffer;
import okio.BufferedSource;
import okio.GzipSource;
import retrofit2.HttpException;
import retrofit2.Response;

public class ErrorBodyProvider {
    private static final Charset UTF8 = Charset.forName("UTF-8");


    public static ResponseEntity tryGetErrorBody(HttpException exception) {
        Response<?> response = exception.response();
        if (response != null && response.errorBody() != null) {
            String string = getErrorBody(response);
            ResponseEntity resultEntity = new Gson().fromJson(string, new TypeToken<ResponseEntity>() {
            }.getType());
            return resultEntity;

        }
        return null;
    }

    public static String getErrorBody(Response response) {

        try {
            ResponseBody responseBody = response.errorBody();

            Headers headers = response.headers();

            if (!HttpHeaders.hasBody(response.raw())) {
                return "";
            } else if (bodyHasUnknownEncoding(response.headers())) {
                return "";
            }


            BufferedSource source = responseBody.source();
            source.request(Long.MAX_VALUE); // Buffer the entire body.
            Buffer buffer = source.buffer();

            Long gzippedLength = null;
            if ("gzip".equalsIgnoreCase(headers.get("Content-Encoding"))) {
                gzippedLength = buffer.size();
                GzipSource gzippedResponseBody = null;
                try {
                    gzippedResponseBody = new GzipSource(buffer.clone());
                    buffer = new Buffer();
                    buffer.writeAll(gzippedResponseBody);
                } finally {
                    if (gzippedResponseBody != null) {
                        gzippedResponseBody.close();
                    }
                }
            }

            Charset charset = UTF8;
            MediaType contentType = responseBody.contentType();
            if (contentType != null) {
                charset = contentType.charset(UTF8);
            }

            return buffer.clone().readString(charset);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";

    }

    private static boolean bodyHasUnknownEncoding(Headers headers) {
        String contentEncoding = headers.get("Content-Encoding");
        return contentEncoding != null
                && !contentEncoding.equalsIgnoreCase("identity")
                && !contentEncoding.equalsIgnoreCase("gzip");
    }

}
