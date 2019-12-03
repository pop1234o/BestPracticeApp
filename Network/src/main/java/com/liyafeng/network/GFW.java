package com.liyafeng.network;

public class GFW {

    /**
     * =====翻墙 科学上网=======
     * 中国国家防火墙（Great Fire Wall）
     * crifan大神总结的科学上网
     * https://crifan.github.io/scientific_network_summary/website/
     *
     * =====翻墙 原理=======
     * 通过可以访问国外的服务器做 中转站
     *
     * ======shadowsocks======
     * shadowsocks 梭影 http://shadowsocks.org/en/index.html
     *
     * Shadowsocks在技术上可以简单总结为：
     * 一套基于Socks5代理方式的网络加密传输数据包的技术
     * = 用socks5技术加密的技术
     *
     * ------------------------
     * 请注意：不要把ss技术的官网，和其他提供ss服务的公司的网站，比如：
     * shadowsocks.com -> 现已改为：shadowsocks.to
     * shadowsocksvpn.com
     * 搞混淆了。
     * shadowsocks.to 提供企业版翻墙
     *
     *
     * Shadowsocks技术本身
     *  SOCKS5代理
     *  AEAD
     *  等等
     * Shadowsocks的服务端
     *  Python的shadowsocks
     *  shadowsocks-server
     *  shadowsocks-libev
     *  libQtShadowsocks
     *  shadowsocks-go
     *  等等
     * Shadowsocks的客户端
     *  Window https://github.com/shadowsocks/shadowsocks-windows
     *  Mac https://github.com/shadowsocks/ShadowsocksX-NG
     *  iOS https://github.com/shadowsocks/shadowsocks-iOS
     *  Android https://github.com/shadowsocks/shadowsocks-android
     *  等等
     *
     * ============ShadowsocksX-NG===================
     * ShadowsocksX就是mac osX 版本，就是mac上的版本
     * -NG 就是next generation 的意思
     * Next Generation of ShadowsocksX
     *
     * 因为之前的ShadowsocksX 太难维护了，所以重构了一下，起名ShadowsocksX-NG
     *
     * Difference from original ShadowsocksX
     * ss-local is run as a background service through launchd, not as an in-app process. So after you quit the app, the ss-local might be still running.
     * Added a manual mode which won't configure the system proxy settings, so that you could configure your apps to use the SOCKS5 proxy manually.
     *
     *
     * github上有服务端版本，有mac windows android ios 版本的源码
     *
     *
     *
     * =========ssr============
     * https://github.com/qinyuhang/ShadowsocksX-NG-R/releases
     * https://ssr.tools/
     * https://ssr.tools/164 (什么是ssr)
     * ShadowSocksR则是原版ShadowSocks（SS）的一个衍生版，相比原版而言，主要增加了混淆参数功能
     * 源码地址 https://github.com/shadowsocksrr
     *
     * mac本来有两个版本，一个ShadowsocksX  ShadowsocksX-NG
     * 加入混淆功能后，就叫ShadowsocksX-R ShadowsocksX-NG-R
     * 源码地址 https://github.com/qinyuhang/ShadowsocksX-NG-R/tree/master
     * 下载地址 https://github.com/qinyuhang/ShadowsocksX-NG-R/releases
     *
     * Android源码
     * https://github.com/shadowsocksrr/shadowsocksr-android/
     *
     * windows
     * 下载地址 https://github.com/shadowsocksrr/shadowsocksr-csharp/releases/download/4.9.0/ShadowsocksR-win-4.9.0.zip
     * 源码 https://github.com/shadowsocksrr/shadowsocksr-csharp
     *
     *
     *
     * ===========ssr参数=============
     * https://coderschool.cn/2498.html （ss、ssr链接解析，查看对应密码、端口、协议）
     *
     * {
     *     "server": "a-xjp-1.xxx.vip",   主机名
     *     "local_address": "127.0.0.1",
     *     "local_port": 1080,
     *     "timeout": 300,
     *     "workers": 1,
     *     "server_port": 47635,            主机端口
     *     "password": "",                  密码
     *     "method": "chacha20",            加密方法
     *     "obfs": "tls1.2_ticket_auth",  混淆规则
     *     "obfs_param": "",                混淆参数
     *     "protocol": "auth_aes128_md5",  协议
     *     "protocol_param": ""             协议参数
     * }
     *
     * 二维码方式
     *
     * ssr://server:port:protocol:method:obfs:password_base64/?params_base64
     *
     * obfs:混淆规则
     *
     * 如果字符串中有包含 – 和 _ 的字符，要先分别替换为 + 和 / , 然后再通过 base64_decode 解码就行了
     *
     * a-xjp-1.xxx.vip:47635:auth_aes128_md5:chacha20:tls1.2_ticket_auth:password_base64/?obfsparam=&protoparam=&remarks=VjHmlrDliqDlnaEx&group=6L-F5o23572R57uc
     *
     *
     *
     * @param args
     */
    public static void main(String[] args) {

    }

    /**
     * ssh的使用
     * 首先我们用ssh命令可以登录远程服务器
     * ```
     * ssh user@liyafeng.net
     * ```
     * 要每次要输入密码，很不方便，我们可以配置ssh证书登录，每个操作系统都要用户登录，比如Window默认用户是Administrator，Linux是root，我们使用命令来为当前用户生成证书
     * ```
     * ssh-keygen
     * ```
     * 下面我们输入保存的位置：` ~/.ssh/id_rsa_xxx`，这样我们就为当前用户生成了公钥文件，和私钥文件，他们是 `~/.ssh/id_rsa_xxx`，`~/.ssh/id_rsa_xxx.pub` 。
     * 然后我们把公钥上传到服务器，我们可以用sftp命令
     * ```
     * sftp user@liyafeng.net
     * put ~/.ssh/id_rsa_xxx.pub
     * ```
     * 这样我们就在服务器的 `~/`目录下看到`id_rsa_xxx.pub`，我们要将这个拷贝到服务器的~/.ssh下，然后将内容追加到authorized_keys这个文件中
     * ```
     * #移动公钥
     * mv id_rsa_xxx.pub .ssh
     * #追加公钥内容到authorized_keys中
     * cat id_rsa_xxx.pub>>authorized_keys
     * ```
     * 然后我们还要确定` ~/.ssh` 和` ~/.ssh/authorized_keys`的权限，如果非本帐号有authorized_keys文件的读写权限，那么ssh证书认证是不起效果的。
     * ```
     * #修改权限
     * chmod 7 ~/.ssh
     * chmod 6 ~/.ssh/authorized_keys
     * ```
     * 下面我们还要保证ssh软件的配置文件`/etc/ssh/sshd_config`，要开启公钥认证功能
     * ```
     * vi /etc/ssh/sshd_config
     * ```
     * 将`# RSAAuthentication yes` 和`# PublicKeyAuthentication yes `前面的“#”去掉，然后保存一下，就可以了。
     * 如果我们本地有多个网站的ssh证书，那么我们可以在本地的`~/.ssh`目录下新建一个`config`文件，里面配置每个网站对应要使用的私钥
     * ```
     * Host liyafeng.net
     *   HostName liyafeng.net
     *   IdentityFile ~/.ssh/id_rsa_xxx
     *   user username
     * Host github
     *   HostName github.com
     *   IdentityFile ~/.ssh/id_rsa_github
     *   user username
     * ```
     * 这样，我们就可以用ssh liyafeng.net来登录远程服务器了。
     *
     */
    void a1(){}


    /**
     * =========ss和ssr区别=====
     * https://www.5xiaobo.com/?id=650 （浅谈VPN和SS/SSR的区别）
     *
     * ss：ss作者是clowwindy，大约两年前，他自己为了浏览谷歌查资料而写了Shadowsocks，简称ss或者叫影梭，
     * 后来他觉得这个东西非常好用，速度快，而且不会被封锁，他就把源码共享在了github上，然后就火了，
     * 但是后来作者被请去喝茶，删了代码，并且保证不再参与维护更新。现在这个好像是一个国外的大兄弟在维护。
     *
     * ssr：在ss作者被喝茶之后，github上出现了一个叫breakwa11(破娃)的帐号，声称ss容易被防火墙检测到，
     * 所以在混淆和协议方面做了改进，更加不容易被检测到，而且向下兼容ss，改进后的项目叫shadowsocks-R，简称ssr，
     * 然后ss用户和ssr用户自然分成了两个派别，互相撕逼，直到前阵子，破娃被人肉出来，无奈之下删除了ssr的代码，并且解散了所有相关群组。
     *
     */
    void a2(){}


    /**
     * ============手动搭建ssr服务========
     *
     * https://www.5xiaobo.com/?id=693（国外VPS搭建SSR多用户教程【中文一键安装版】）
     *
     * ============vps选择=================
     * https://coderschool.cn/2598.html （ 申请谷歌云教程，可免费使用一年）
     * https://banwagong.cn/ （搬瓦工VPS - 一款性价比较高的便宜VPS主机）
     *
     *
     * 搬瓦工 bandwagonHost（https://bwh88.net/）
     * 谷歌云
     * vultr https://www.vultr.com/
     *
     *
     *
     *
     *
     *
     *
     */
    void a3(){}


    /**
     * ===========云服务器和VPS服务器有什么区别==========
     * https://cloud.tencent.com/developer/news/362988（云服务器和VPS服务器有什么区别）
     *
     * VPS是从一台物理服务器用虚拟机划分成多个操作系统。而云服务器则是从云计算中分离出来的一个类似VPS的虚拟主机
     * VPS是在一台服务器上利用虚拟化技术实现的，云服务器是在服务器集群的资源池利用虚拟化实现的
     *
     *
     *
     * ============云服务器和云虚拟主机区别=============
     * 而「云主机」似乎一般就是「云服务器」，而「云虚拟主机」
     * 则是指一种买家对操作系统并无访问权、而只能进行有限的诸如上传 PHP 代码和连接 MySQL 实例等等行为的服务，
     * 可以理解为早期的托管服务的集成。
     */
    void a4(){}


}
