package com.liyafeng.view.other.share;

public class Main {

    /**
     * 三方分享，微信好友，朋友圈，新浪微博，QQ好友和QQ空间
     * <p>
     * 微信分享 https://open.weixin.qq.com/cgi-bin/showdocument?action=dir_list&t=resource/res_list&verify=1&id=open1419317340&token=&lang=zh_CN
     * 分享分享图片到朋友圈，有的时候会很慢，是因为你直接传了bitmap过去，你应该传递图片的path
     * <p>
     * //weChat
     * implementation 'com.tencent.mm.opensdk:wechat-sdk-android-with-mta:5.1.4'
     * <p>
     * 微博
     * https://github.com/sinaweibosdk/weibo_android_sdk
     * https://github.com/sinaweibosdk/weibo_android_sdk/blob/master/新文档/微博SDK%204.1文档.pdf
     *
     * ===============获取应用签名  微信签名===============
     * keytool -list -v -keystore xxx.keystore
     * 会列出签名信息，其中MD5将冒号去掉就是我们应用的签名了
     * 或者 https://open.weixin.qq.com/cgi-bin/showdocument?action=dir_list&t=resource/res_list&verify=1&id=open1419319167&token=b4469dded1fe9fd7a6cb514cd3cc19d59eda1fa3&lang=zh_CN
     * 下载获取签名的app，安装到手机上，然后写包名就行，（注意：安装在手机上的apk必须是正式签名的）
     *
     *
     * 密钥库类型: jks
     * 密钥库提供方: SUN
     *
     * 您的密钥库包含 1 个条目
     *
     * 别名: brandykey
     * 创建日期: 2019-3-25
     * 条目类型: PrivateKeyEntry
     * 证书链长度: 1
     * 证书[1]:
     * 所有者: CN=brandyKey, OU=brandy, O=tal.brandy, L=beijing, ST=beijing, C=cn
     * 发布者: CN=brandyKey, OU=brandy, O=tal.brandy, L=beijing, ST=beijing, C=cn
     * 序列号: 64df0480
     * 有效期为 Mon Mar 25 14:37:14 CST 2019 至 Fri Mar 18 14:37:14 CST 2044
     * 证书指纹:
     * 	 MD5:  13:DC:56:29:43:F9:B0:7F:A0:73:AD:1C:A1:D1:09:93
     * 	 SHA1: FE:33:39:19:84:16:BE:C8:FE:E9:46:16:68:68:47:08:39:62:E3:9D
     * 	 SHA256: C1:2D:D6:57:9D:71:C3:12:62:4A:B0:CE:5B:03:33:F8:B2:45:8F:FC:6E:7A:A6:3E:A3:F4:0F:A7:08:4C:65:65
     * 签名算法名称: SHA256withRSA
     * 主体公共密钥算法: 2048 位 RSA 密钥
     * 版本: 3
     *
     * 扩展:
     *
     * #1: ObjectId: 2.5.29.14 Criticality=false
     * SubjectKeyIdentifier [
     * KeyIdentifier [
     * 0000: 24 AF B9 70 EE 67 6E D3   D6 09 26 FB 3D 77 86 3E  $..p.gn...&.=w.>
     * 0010: 19 A4 01 65                                        ...e
     * ]
     * ]
     *
     *
     *
     * *******************************************
     * *******************************************
     *
     * @param args
     */
    public static void main(String[] args) {

    }
}
