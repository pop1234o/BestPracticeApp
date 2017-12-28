package com.liyafeng.network;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/**
 * Created by liyafeng on 2017/12/28.
 * <p>
 * javax.net.ssl.SSLHandshakeException: java.security.cert.CertPathValidatorException: Trust anchor for certification path not found.
 * at com.android.org.conscrypt.OpenSSLSocketImpl.startHandshake(OpenSSLSocketImpl.java:328)
 * at okhttp3.internal.connection.RealConnection.connectTls(RealConnection.java:281)
 * <p>
 * 如果我们请求不收Android系统本身信任的证书，那么就会报这个异常
 * 设置-》安全-》受信任的证书
 */

public class Https {


    public static SSLSocketFactory getSSLSocketFactory() {
        //创建一个不验证证书的 “信任证书管理器”
        TrustManager[] manager = new TrustManager[]{
                new X509TrustManager() {
                    @Override
                    public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {

                    }

                    @Override
                    public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {

                    }

                    @Override
                    public X509Certificate[] getAcceptedIssuers() {
                        return new X509Certificate[0];
                    }
                }

        };


        try {
            SSLContext tls = SSLContext.getInstance("TLS");
            tls.init(null, manager, new SecureRandom());
            SSLSocketFactory socketFactory = tls.getSocketFactory();
            return socketFactory;
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
}
