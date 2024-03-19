

### android 如何抓包

1.配置app，允许debug下抓包
   <?xml version="1.0" encoding="utf-8"?>
   <network-security-config>
       <debug-overrides>
           <trust-anchors>
               <!-- Trust user installed CA certificates in debug build -->
               <certificates src="user" />
           </trust-anchors>
       </debug-overrides>
   </network-security-config>
   <application
       ...
       android:networkSecurityConfig="@xml/network_security_config"
       ...>
       ...
   </application>

2.安装charles
https://www.charlesproxy.com/


3.手机设置代理到电脑
打开wifi高级设置，开启代理。输入电脑ip，和charles端口8888
（电脑ip，可以通过ipconfig命令看到，是wifi分配的ip）



给电脑安装证书
在Charles中，选择Help > SSL Proxying > Install Charles Root Certificate。安装后
给手机安装证书。
在Charles中，选择Help > SSL Proxying > Install Charles Root Certificate。安装后
(这个一定要打开链接到代理，才能下载。（而且每个电脑的证书都是不一样的，所以要链接其他电脑，要重新安装下载证书） 
下载后，打开设置-》wifi设置=》高级设置，安装证书。)
（设置中可以看到用户安装的证书，也可以看到预置的根证书。）

配置启用https监听
为了能够查看HTTPS通信，需要启用SSL代理设置。进入Proxy > SSL Proxying Settings，勾选Enable SSL Proxying，
并添加你需要监控的域名（不用加https://）和端口，通常端口是443（不添加是监听不到的）



34.打开app
即可看到流量，也可以开启过滤。

注意：如果是乱码，可能就是你SSL Proxying Settings 中监听的域名和端口配置错了。



### ca证书
证书颁发机构（Certificate Authority）证书，是数字证书的一种，用于在互联网通信中建立信任关系
CA证书由受信任的第三方机构颁发，用于确认网站或服务的身份。
CA证书包含公钥，用于SSL/TLS加密过程中的密钥交换（就是对称加密的秘钥交换）
客户端使用证书中的公钥加密信息，只有持有对应私钥的服务器才能解密 （反之亦然），从而保证了数据传输过程的安全性。
包含公钥、证书持有者的信息（如网站的域名）以及签发CA的签名。

#### 根ca
根CA是顶级证书颁发机构，其证书通常预装在操作系统或浏览器中。
根CA可以签发中间CA证书，中间CA再签发给终端实体（如网站）。通过这种方式，只要信任根CA，就可以通过信任链验证由其签发的任何证书的有效性。

#### 验证过程
1当用户访问一个使用SSL/TLS加密的网站时，网站会提供一个由CA签发的证书，里面包含公钥。及证书链中的所有中间证书（根证书通常不提供，因为它应该已经预装在客户端中）。

2客户端首先验证服务器证书的签名是否由列表中的中间CA证书颁发，然后再验证该中间CA证书的签名是否由另一个中间CA或根CA证书颁发，依此类推，直到达到一个预装的、信任的根CA证书。

3.如果信任此证书（验证了证书信任链），就用此证书交换密钥，后续就是对称加密了。

#### charles原理 
其实就是手机设置代理，然后手机中也安装了charles的根证书

这样app发送请求，到charles（模拟的服务端），charles返回一个证书。然后这个证书能被 刚才安装的charles的根证书验证。
交换秘钥，完成ssl 握手。  charles再模拟app端发送请求就好了。

破戒方式，
只要配置app是否信任用户安装的证书就可以了，release都是不信任的，所以验证不过。

在Android 7.0以前，如果用户在设备上安装了CA证书，所有应用默认都会信任这个证书。
这使得使用Charles这样的代理工具通过安装其自己的CA证书来解密HTTPS流量变得相对简单。
然而，从Android 7.0开始，应用默认只信任系统预装的CA证书，除非应用明确在其network_security_config.xml中配置为信任用户安装的CA证书。

### 其他方法
或者root，让系统默认信任用户安装的证书。


































