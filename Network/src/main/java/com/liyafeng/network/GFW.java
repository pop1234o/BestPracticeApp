package com.liyafeng.network;

public class GFW {

    /**
     * =====翻墙 科学上网=======
     * 中国国家防火墙（Great Fire Wall）
     *
     * =====翻墙 原理=======
     * 通过可以访问国外的服务器做 中转站
     *
     * ======软件======
     * shadowsocks 梭影
     *
     *
     * https://github.com/shadowsocks/ShadowsocksX-NG
     * https://github.com/qinyuhang/ShadowsocksX-NG-R/releases
     *
     * github上有服务端版本，有mac windows android ios 版本的源码
     *
     *
     *
     * =========ssr============
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
