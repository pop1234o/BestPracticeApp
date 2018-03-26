package com.liyafeng.network;

/**
 * Created by liyafeng on 05/11/2017.
 *
 *
 * OSI 七层网络模型
 *
 * Tcp/ip五层网络模型/架构
 *
 * 应用层(application layer)  http   请求头、响应头  https://developer.mozilla.org/en-US/docs/Web/HTTP/Overview
 * 网络层(network layer)      tcp / udp   3次握手/4次挥手  syn攻击       https://hit-alibaba.github.io/interview/basic/network/TCP.html
 * 传输层(transport layer)    ip
 * 数据链路层(data link layer)    wifi/网卡
 * 物理层(phycial layer)      网线
 *
 * tcp/ip下面就是网卡，上面就是http (application layer)
 *
 * socket.connect()是三次握手
 * socket.close()四次挥手
 *
 * http messages http报文（指的是整个http请求/响应的内容，包括start-line，请求头，请求体）
 *                                                     status-line
 *
 * ======================
 *
 * CSRF（Cross-site request forgery，跨站请求伪造
 * XSS（Cross Site Scripting，跨站脚本攻击） 注入脚本，让看其他用户打开执行你的脚本
 *
 * ======================
 *
 * 多用途互联网邮件扩展（MIME，Multipurpose Internet Mail Extensions）是一个互联网标准，它扩展了电子邮件标准
 *
 * 格式为：type/subtype
 *
 * 常见的type有text、application 、audio 、video 、image 、multipart
 *
 * application/json
 * image/mp4
 * multipart/from-data
 *
 * ======================
 * 请求方法method  : 幂等==多次请求结果相同
 * get post
 *
 * put更新
 *
 * delete
 *
 * head 只返回响应的头部，我们比如我们获取一个文件的大小而不获取它本身
 *
 * patch (put是全部修改，patch是局部修改)
 * connect (开启隧道？)
 * options (获取服务端支持的 请求方法 request method )
 *
 * ==========================
 * 断点下载
 *
 *
 * if-match:etag_value 一般配合range使用，来实现断点下载
 * 第一次请求返回200，本地保存etag
 * 后面再次请求加上 if-match:etag  range:bytes=1024-
 * 如果服务端文件没有改变，那么etag值匹配，将从第1025个字节开始返回
 * 如果返回206代表匹配成功
 * 406代表range不在范围
 *
 * -------------------
 *
 * put方法配合if-match ，如果服务端文件改变，etag值就会改变，那么我们本地不知道
 * 我们用之前文件的etag，加上if-match请求头，如果是匹配了我们之前的那个etag的文件才更新
 * 否则返回412 (Precondition Failed，先决条件失败)
 *
 * =========================
 * 缓存               https://developers.google.com/web/fundamentals/performance/optimizing-content-efficiency/http-caching?hl=zh-cn
 *
 * if-modified-since:<day-name>, <day> <month> <year> <hour>:<minute>:<second> GMT
 * Wed, 21 Oct 2015 07:28:00 GMT
 *
 * （只可用在get head）
 * 如果在这个日期后，服务端资源做出修改，返回200
 * 如果没有做出修改，304
 *
 * --------------
 * if-none-match:etag
 * 当服务端不匹配到这个etag时候，请求才会被执行
 *
 *
 * ------------
 * cache-control:        https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/Cache-Control
 * 请求的时候使用代表满足什么条件才使用缓存
 *
 * 如果在响应中返回代表 服务端告诉客户端这个最多缓存多长时间，超过这个期限就应该删掉缓存
 *
 * no-cache -每次请求都会到达服务器，检查缓存是否更新，无更新则用本地的缓存
 * no-store -强制不缓存，比如银行信息
 * public -cdn缓存
 * private -浏览器缓存
 * max-age -缓存有效期，这个浏览器或者其他支持http的软件（okhttp），每次请求的时候会判断这个字段，
 * 如果过期，则重新请求
 *
 *
 * ------------
 * expires:格林威治时间   说明响应缓存过期的时间？
 * 如果有cache-control:max-age=格林威治时间  那么这个将被忽略
 * cache-control是http1.1取代了expires这个字段
 *
 * ======================
 * 断点续传/大文件上传
 *
 * 我们要为上传的文件创建一个md5的唯一标识，来判断服务端有没有这个文件
 *
 * 事件是就是将文件分片，用RandomAccessFile来进行读取操作
 * 然后分片发送post请求，如果做成同步的就是上传成功一个part后再上传下一个
 * 直到全部成功，返回上传文件的url
 * 如果中间中断，下次判断本地有没有url，没有代表上传未成功，
 * 服务端根据它已经接收到的part返回响应，告诉我们从哪里开始上传
 *
 * 一般一个part定义为100kb（最小）到10M
 *
 * =======================
 *
 * mime类型
 * post提交表单，
 * 默认是application/x-www-form-urlencoded;charset=utf-8
 * 这样表单中的内容就以 key=value&key=value的形式在请求体中
 * 服务端获取到request的content-type后，就知道以什么方式来解析request body中的内容了
 * 这个操作都是约定好的，是很多语言框架中所支持的
 *
 * 同样支持的还有  multipart/form-data
 * 用来上传文件
 * 他的request body是一块一块的
 *
 * 他的request body 的约定格式是：
 * boundary=---xxxxxxxxxxxxxx
 * [换行]
 * ---xxxxxxxxxxxxx
 * content-description:from-data;name="key"
 *
 * value
 * ---xxxxxxxxxxxxx
 * content-description:from-data;name="file";filename="xxx.png"
 * content-type:image/png
 *
 * [png的内容]
 *
 * ---xxxxxxxxxxxxx--
 *
 *
 *
 */

public class HttpProtocol {
}

/**
 *
 * https  Hypertext Transfer Protocol Secure
 *
 * 就是http over tls/ssl
 *
 * tls transport layer security 传输层安全协议
 * ssl secure socket layer 安全套接层
 *
 * ssl 是网景公司发布的，tsl是IETF(internet engineering task force 互联网工程任务小组) 在ssl 上加以改进
 *
 *
 *
 * 于http的差异
 * 1.url开头不同
 * 2.端口不同，80  443
 * 3.https 基于 tls 上
 * 4.而tls 是基于 Public key certificate（公开密钥认证）的
 * 5.证书是由权威CA机构颁发，
 *
 * ================
 * 对称加密，加密 解密都是一个key
 * 非对称加密 ，两个key 一个public key / private key
 *
 * 为什么要有https ,比如我们购物的时候，我们发送支付密码给服务器进行核对 www.taobao.com 通过post uid=001&password=123
 * 这个时候信息在网络中传输的时候可能会被劫持，从http请求中提取出我们的密码，这就不安全
 *
 * 我们可以进行加密，比如讲密码123 +1 =124  ,然后传到服务器，服务器减一得到原密码
 * 但是这种是对称加密，我们加密的过程是在客户端进行的，这就会导致将加密算法暴露出来的问题
 *
 * 这个时候非对称加密就诞生了！
 *
 *
 * 我们请求一个https 的网站，首先他会把公钥给客户端，客户端用这个公钥将我们要发送给服务器的信息加密
 * 而解密必须要用private key ,而私钥只有服务器有，所以中间拦截了也不会破解报文（messages）内容
 *
 * 也就是说公钥是暴露出来的，告诉客户端用都用这个加密，私钥只要服务端才有，所以中间任何人都不能解密
 *
 * 这就是非对称加密的好处
 *
 * ---------------------
 * 数字签名
 *
 * 上面的例子是public key进行加密，服务端的private key进行解密，这样客户就能保证自己的信息安全的传输到服务端
 *
 * 那么反过来，我们可以用private key 进行加密，服务端的内容用私钥加密，
 * 所有的客户端都可以获取到公钥，那么这个时候用公钥解密成功的，就能认证这个信息确定是指定服务端发送的
 * 而不是其他人伪造发送的，这就是用私钥签名！！
 *
 * 这个技术可以仿假冒网站
 *
 * 只有真正的taobao才有private key ,我们访问他的内容是他用private key加密过的
 * 我们从证书网站获取这个域名的public key 进行解密，如果解密成功，那么就可以认定这个网站是真网站
 *
 *
 * 非对称加密的算法也有很多种，比如 RSA 是三位科学家姓氏的首字母组合
 * 因式分解能破解它？
 *
 *
 * ================
 * 中国的GFW可以通过证书黑名单来拦截请求
 * Vpn  virtual private network  公司内部局域网，那么我们要通过因特网连接
 * 可以先连接到公司的对外服务器，由这个服务器进行转发，我们-》公司对外服务器之间的通信是加密的
 *
 * 通信协议有：pptp Point to Point Tunneling Protocol，缩写为PPTP
 *  l2tp Layer Two Tunneling Protocol，缩写为L2TP 第二层隧道协议
 *
 *  l2tp的加密协议一般使用 （英语：Internet Protocol Security，缩写为IPsec 互联网安全协议
 *
 *  gfw可以通过请求的关键字来过滤
 *  如果是内容加密，那么可以根据请求的证书（证书黑名单）来拦截
 *  还可以根据协议的特征来过滤
 *
 *
 * ===================
 * 从知名认证中心 (CA) 供应商购买证书，例如 VeriSign 或 Thawte
 *
 * =========================
 * https可以用来抵御中间人攻击，因为客户端有公钥，服务端有私钥，私钥才能解密
 * https还可以用作客户端认证，比如付费的接口，注册的app才能调用成功。
 * 那么一个注册的app给他分配一个私钥，服务端会认证这个私钥。
 *
*
* */



/**
 *
 * RFC
 * 征求意见稿（英语：Request For Comments，缩写为RFC），是由互联网工程任务组（IETF）发布的一系列备忘录。
 * 文件收集了有关互联网相关信息，以及UNIX和互联网社区的软件文件，以编号排定。目前RFC文件是由互联网协会（ISOC）赞助发行。
  RFC始于1969年，由斯蒂芬·克罗克用来记录有关ARPANET开发的非正式文档，最终演变为用来记录互联网规范、协议、过程等的标准文件。
 *
 *
 *
 *
 *============================
 *
 * tcp udp
 *
 * 正如上一小节所指出的，当您编写 socket 应用程序的时候，您可以在使用 TCP 还是使用 UDP 之间做出选择。它们都有各自的优点和缺点。
 TCP 是流协议，而UDP是数据报协议。换句话说，TCP 在客户机和服务器之间建立持续的开放连接，在该连接的生命期内，
 字节可以通过该连接写出（并且保证顺序正确）。然而，通过 TCP 写出的字节没有内置的结构，所以需要高层协议在被传输的字节流内部分隔数据记录和字段。
 另一方面，UDP 不需要在客户机和服务器之间建立连接，它只是在地址之间传输报文。UDP 的一个很好特性在于它的包是自分隔的（self-delimiting），
 也就是一个数据报都准确地指出它的开始和结束位置。然而，UDP 的一个可能的缺点在于，它不保证包将会按顺序到达，甚至根本就不保证。
 当然，建立在 UDP 之上的高层协议可能会提供握手和确认功能。

 对于理解 TCP 和 UDP 之间的区别来说，一个有用的类比就是电话呼叫和邮寄信件之间的区别。
 在呼叫者用铃声通知接收者，并且接收者拿起听筒之前，电话呼叫不是活动的。只要没有一方挂断，该电话信道就保持活动，
 但是在通话期间，他们可以自由地想说多少就说多少。来自任何一方的谈话都按临时的顺序发生。另一方面，当你发一封信的时候，
 邮局在投递时既不对接收方是否存在作任何保证，也不对信件投递将花多长时间做出有力保证。接收方可能按与信件的发送顺序不同的顺序接收不同的信件，
 并且发送方也可能在他们发送信件是交替地接收邮件。与（理想的）邮政服务不同，无法送达的信件总是被送到死信办公室处理，而不再返回给发送者。
*
*
*
*
* */