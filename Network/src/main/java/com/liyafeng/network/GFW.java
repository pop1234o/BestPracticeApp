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
     *
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
}
