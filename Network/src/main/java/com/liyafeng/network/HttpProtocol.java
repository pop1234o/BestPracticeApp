package com.liyafeng.network;

/**
 * Created by liyafeng on 05/11/2017.
 * 实际上都是文本，只不过是按规定写的文本，美名其曰协议。
 * <p>
 * <p>
 * OSI（Open System Interconnect） 七层网络模型  （多了表示层、传输层）
 * <p>
 * Tcp/ip五层网络模型/架构
 * <p>
 * 应用层(application layer)  http   请求头、响应头  https://developer.mozilla.org/en-US/docs/Web/HTTP/Overview
 * ftp ,smtp
 * (表示层)  这一层的主要功能是定义数据格式及加密
 * （会话层） 它定义了如何开始、控制和结束一个会话，包括对多个双向消息的控制和管理
 * 网络层(network layer)      tcp / udp   3次握手/4次挥手  syn攻击       https://hit-alibaba.github.io/interview/basic/network/TCP.html
 * 这层的功能包括是否选择差错恢复协议还是无差错恢复协议，
 * 及在同一主机上对不同应用的数据流的输入进行复用，还包括对收到的顺序不对的数据包的重新排序功能。示例：TCP，UDP，SPX。
 * 传输层(transport layer)    ip
 * 这层对端到端的包传输进行定义，它定义了能够标识所有结点的逻辑地址，还定义了路由实现的方式和学习的方式。
 * 为了适应最大传输单元长度小于包长度的传输介质，网络层还定义了如何将一个包分解成更小的包的分段方法。示例：IP，IPX
 * 数据链路层(data link layer)    wifi/网卡
 * 它定义了在单个链路上如何传输数据。这些协议与被讨论的各种介质有关。示例：ATM，FDDI等。
 * 物理层(phycial layer)      网线，无线网络
 * OSI的物理层规范是有关传输介质的特这些规范通常也参考了其他组织制定的标准。连接头、帧、帧的使用、电流、编码及光调制等都属于各种物理层规范中的内容。
 * 物理层常用多个规范完成对所有细节的定义。示例：Rj45，802.3等。
 * <p>
 * tcp/ip下面就是网卡，上面就是http (application layer)
 * <p>
 * socket.connect()是三次握手
 * socket.close()四次挥手
 * <p>
 * http messages http报文（指的是整个http请求/响应的内容，包括start-line，请求头，请求体）
 * status-line
 * <p>
 * ======================
 * <p>
 * CSRF（Cross-site request forgery，跨站请求伪造
 * XSS（Cross Site Scripting，跨站脚本攻击） 注入脚本，让看其他用户打开执行你的脚本
 * <p>
 * ======================
 * <p>
 * 多用途互联网邮件扩展（MIME，Multipurpose Internet Mail Extensions）是一个互联网标准，它扩展了电子邮件标准
 * <p>
 * 格式为：type/subtype
 * <p>
 * 常见的type有text、application 、audio 、video 、image 、multipart
 * <p>
 * application/json
 * image/mp4
 * multipart/from-data
 * <p>
 * ===============http请求格式=================
 * <p>
 * 请求方法 URL?key=value&key=value 协议版本(HTTP/1.1)   （请求行）
 * key:value                                   （请求头）
 * key:value
 * <p>
 * key=value&key=value                         （请求体）get一般没有
 * <p>
 * ===========请求方法===========
 * <p>
 * get  获取
 * post 添加
 * put 更新
 * delete 删除
 * head 只返回响应的头部，我们比如我们获取一个文件的大小而不获取它本身
 * patch (put是全部修改，patch是局部修改)
 * options (获取服务端支持的 请求方法 request method )
 * trace 请求服务器回送收到的请求信息，主要用于测试或诊断
 * connect 保留将来使用
 * ------------------请求头---------------
 * Host: www.example.com（没有端口号默认是80）
 * User-Agent: Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US; rv:1.7.6)     客户端信息
 * Cache-Control:no-cache;no-store;max-age=60;max-stale=60;min-fresh=60;only-if-cached   缓存控制
 * Accept：text/html  客户端接收的响应类型
 * Accept-Charset:iso-8859-1,gb2312
 * Accept-Encoding:gzip.deflate
 * Accept-Language:zh-cn
 * Authorization:授权码  如果没有权限返回401
 * If-Modified-Since:Wed,05 Jan 2007 11:21:25 GMT （如果服务器从这个时间后有修改响应，那么返回响应）
 * （响应头Last-Modified返回一个时间，我们在这里设置这个时间，服务器获取到这个时间，
 * 如果从这个时间到现在响应内容没有改变，那么直接返回304即可，告诉客户端内容没有改变，
 * 可以用缓存）（这个最大的缺点就是只能响应到秒级别，如果一秒内有改变，那么将无法判断）
 * <p>
 * If-None-Match:W/"80b1a4c018f3c41:8317"  （如果不匹配就返回响应，匹配就可以用缓存）
 * （响应头ETag返回值，加入到这个请求头中，如果服务器判断上次返回的这个ETag没有改变
 * 那么返回304？？？，）
 * <p>
 * If-Match:W/"80b1a4c018f3c41:8317" （如果匹配就返回）
 * （这个是断点下载用的，携带上次返回的Etag，配合Range来从指定的地方下载，返回206）
 * <p>
 * Range:bytes=%d- （下载文件的范围）
 * <p>
 * <p>
 * <p>
 * ===================响应格式===================
 * 协议 响应码 OK(响应信息)
 * key:value
 * key:value
 * <p>
 * 响应体
 * -----------------响应码------------------
 * 1xx：指示信息--表示请求已接收，继续处理
 * 2xx：成功--表示请求已被成功接收、理解、接受
 * 3xx：重定向--要完成请求必须进行更进一步的操作
 * 4xx：客户端错误--请求有语法错误或请求无法实现
 * 5xx：服务器端错误--服务器未能实现合法的请求
 * 常见状态代码、状态描述、说明：
 * <p>
 * <p>
 * 100 Continue 初始的请求已经接受，客户应当继续发送请求的其余部分。（HTTP 1.1新）
 * 101 Switching Protocols 服务器将遵从客户的请求转换到另外一种协议（HTTP 1.1新）
 * 200 OK 一切正常，对GET和POST请求的应答文档跟在后面。
 * 201 Created 服务器已经创建了文档，Location头给出了它的URL。
 * 202 Accepted 已经接受请求，但处理尚未完成。
 * 203 Non-Authoritative Information 文档已经正常地返回，但一些应答头可能不正确，因为使用的是文档的拷贝（HTTP 1.1新）。
 * 204 No Content 没有新文档，浏览器应该继续显示原来的文档。如果用户定期地刷新页面，而Servlet可以确定用户文档足够新，这个状态代码是很有用的。
 * 205 Reset Content 没有新的内容，但浏览器应该重置它所显示的内容。用来强制浏览器清除表单输入内容（HTTP 1.1新）。
 * 206 Partial Content 客户发送了一个带有Range头的GET请求，服务器完成了它（HTTP 1.1新）。
 * 300 Multiple Choices 客户请求的文档可以在多个位置找到，这些位置已经在返回的文档内列出。如果服务器要提出优先选择，则应该在Location应答头指明。
 * 301 Moved Permanently 客户请求的文档在其他地方，新的URL在Location头中给出，浏览器应该自动地访问新的URL。
 * 302 Found 类似于301，但新的URL应该被视为临时性的替代，而不是永久性的。注意，在HTTP1.0中对应的状态信息是“Moved Temporatily”。
 * 出现该状态代码时，浏览器能够自动访问新的URL，因此它是一个很有用的状态代码。
 * 注意这个状态代码有时候可以和301替换使用。例如，如果浏览器错误地请求http://host/~user（缺少了后面的斜杠），有的服务器返回301，有的则返回302。
 * 严格地说，我们只能假定只有当原来的请求是GET时浏览器才会自动重定向。请参见307。
 * <p>
 * 303 See Other 类似于301/302，不同之处在于，如果原来的请求是POST，Location头指定的重定向目标文档应该通过GET提取（HTTP 1.1新）。
 * 304 Not Modified 客户端有缓冲的文档并发出了一个条件性的请求（一般是提供If-Modified-Since头表示客户只想比指定日期更新的文档）。服务器告诉客户，原来缓冲的文档还可以继续使用。
 * 305 Use Proxy 客户请求的文档应该通过Location头所指明的代理服务器提取（HTTP 1.1新）。
 * 307 Temporary Redirect 和302（Found）相同。许多浏览器会错误地响应302应答进行重定向，即使原来的请求是POST，即使它实际上只能在POST请求的应答是303时 才能重定向。由于这个原因，HTTP 1.1新增了307，以便更加清除地区分几个状态代码：当出现303应答时，浏览器可以跟随重定向的GET和POST请求；如果是307应答，则浏览器只能跟随对GET请求的重定向。（HTTP 1.1新）
 * 400 Bad Request 请求出现语法错误。
 * 401 Unauthorized 客户试图未经授权访问受密码保护的页面。应答中会包含一个WWW-Authenticate头，浏览器据此显示用户名字/密码对话框，然后在填写合适的Authorization头后再次发出请求。
 * 403 Forbidden 资源不可用。服务器理解客户的请求，但拒绝处理它。通常由于服务器上文件或目录的权限设置导致。
 * 404 Not Found 无法找到指定位置的资源。这也是一个常用的应答。
 * 405 Method Not Allowed 请求方法（GET、POST、HEAD、DELETE、PUT、TRACE等）对指定的资源不适用。（HTTP 1.1新）
 * 406 Not Acceptable 指定的资源已经找到，但它的MIME类型和客户在Accpet头中所指定的不兼容（HTTP 1.1新）。
 * 407 Proxy Authentication Required 类似于401，表示客户必须先经过代理服务器的授权。（HTTP 1.1新）
 * 408 Request Timeout 在服务器许可的等待时间内，客户一直没有发出任何请求。客户可以在以后重复同一请求。（HTTP 1.1新）
 * 409 Conflict 通常和PUT请求有关。由于请求和资源的当前状态相冲突，因此请求不能成功。（HTTP 1.1新）
 * 410 Gone 所请求的文档已经不再可用，而且服务器不知道应该重定向到哪一个地址。它和404的不同在于，返回407表示文档永久地离开了指定的位置，而404表示由于未知的原因文档不可用。（HTTP 1.1新）
 * 411 Length Required 服务器不能处理请求，除非客户发送一个Content-Length头。（HTTP 1.1新）
 * 412 Precondition Failed 请求头中指定的一些前提条件失败（HTTP 1.1新）。
 * 413 Request Entity Too Large 目标文档的大小超过服务器当前愿意处理的大小。如果服务器认为自己能够稍后再处理该请求，则应该提供一个Retry-After头（HTTP 1.1新）。
 * 414 Request URI Too Long URI太长（HTTP 1.1新）。
 * 416 Requested Range Not Satisfiable 服务器不能满足客户在请求中指定的Range头。（HTTP 1.1新）
 * 500 Internal Server Error 服务器遇到了意料不到的情况，不能完成客户的请求。
 * 501 Not Implemented 服务器不支持实现请求所需要的功能。例如，客户发出了一个服务器不支持的PUT请求。
 * 502 Bad Gateway 服务器作为网关或者代理时，为了完成请求访问下一个服务器，但该服务器返回了非法的应答。
 * 503 Service Unavailable 服务器由于维护或者负载过重未能应答。例如，Servlet可能在数据库连接池已满的情况下返回503。服务器返回503时可以提供一个Retry-After头。
 * 504 Gateway Timeout 由作为代理或网关的服务器使用，表示不能及时地从远程服务器获得应答。（HTTP 1.1新）
 * 505 HTTP Version Not Supported 服务器不支持请求中所指明的HTTP版本。（HTTP 1.1新）
 * <p>
 * 作者：吴白
 * 链接：https://www.jianshu.com/p/52d86558ca57
 * 來源：简书
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 * <p>
 * -------------响应头-------------------------
 * Server: Microsoft-IIS/5.0    //web服务器
 * Date: Thu,08 Mar 200707:17:51 GMT
 * Connection: Keep-Alive或者close
 * Content-Length: 23330
 * Content-Type: text/html
 * Set-Cookie: ASPSESSIONIDQAQBQQQB=BEJCDGKADEDJKLKKAJEOIMMH; path=/
 * Expires: Thu,08 Mar 2007 07:16:51 GMT
 * （在此日期之前，响应都是有效的,这种有个缺点，是服务端时间和客户端时间不匹配）
 * <p>
 * Cache-control: public、private、no-cache、no-store、no-transform、must-revalidate、proxy-revalidate、max-age、s-maxage.
 * (Cache-Control: max-age=3600代表响应的有效期，单位是秒，这就解决了C/S时间不匹配问题
 * 缺点是HTTP1.1才有的，所以一般是两者都返回，优先使用Cache-control）
 * <p>
 * Last-Modified: Thu, 30 Nov 2006 11:35:41 GMT  (服务端最后修改（响应的）时间)
 * ETag: "6277a-415-e7c76980"     （响应的唯一标识）
 * Accept-Ranges: bytes
 * <p>
 * <p>
 * <p>
 * ==========断点下载================
 * <p>
 * <p>
 * <p>
 * etag_/if-match 一般配合range使用，来实现断点下载
 * 第一次请求返回200，本地保存etag
 * 后面再次请求加上 if-match:etag  range:bytes=1024-
 * 如果服务端文件没有改变，那么etag值匹配，将从第1025个字节开始返回
 * 如果返回206代表匹配成功
 * 406代表range不在范围
 * <p>
 * -------------------
 * <p>
 * put方法配合if-match ，如果服务端文件改变，etag值就会改变，那么我们本地不知道
 * 我们用之前文件的etag，加上if-match请求头，如果是匹配了我们之前的那个etag的文件才更新
 * 否则返回412 (Precondition Failed，先决条件失败)
 * <p>
 * =========== 缓存  ==============
 * https://developers.google.com/web/fundamentals/performance/optimizing-content-efficiency/http-caching?hl=zh-cn
 * <p>
 * last-modified/if-modified-since:<day-name>, <day> <month> <year> <hour>:<minute>:<second> GMT
 * Wed, 21 Oct 2015 07:28:00 GMT
 * <p>
 * （只可用在get head）
 * 如果在这个日期后，服务端资源做出修改，返回200
 * 如果没有做出修改，304
 * <p>
 * --------------
 * if-none-match:etag
 * 当服务端不匹配到这个etag时候，请求才会被执行
 * <p>
 * <p>
 * ------------
 * cache-control:        https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/Cache-Control
 * 请求的时候使用代表满足什么条件才使用缓存
 * <p>
 * 如果在响应中返回代表 服务端告诉客户端这个最多缓存多长时间，超过这个期限就应该删掉缓存
 * <p>
 * no-cache -每次请求都会到达服务器，检查缓存是否更新，无更新则用本地的缓存
 * no-store -强制不缓存，比如银行信息
 * public -cdn缓存
 * private -浏览器缓存
 * max-age -缓存有效期，这个浏览器或者其他支持http的软件（okhttp），每次请求的时候会判断这个字段，
 * 如果过期，则重新请求
 * <p>
 * <p>
 * -----响应头：缓存-------
 * expires:格林威治时间 指定响应的缓存有效期
 * 如果有cache-control:max-age=格林威治时间  那么这个将被忽略
 * cache-control是http1.1取代了expires这个字段
 * <p>
 * ======================
 * 断点续传/大文件上传
 * <p>
 * 我们要为上传的文件创建一个md5的唯一标识，来判断服务端有没有这个文件
 * <p>
 * 事件是就是将文件分片，用RandomAccessFile来进行读取操作
 * 然后分片发送post请求，如果做成同步的就是上传成功一个part后再上传下一个
 * 直到全部成功，返回上传文件的url
 * 如果中间中断，下次判断本地有没有url，没有代表上传未成功，
 * 服务端根据它已经接收到的part返回响应，告诉我们从哪里开始上传
 * <p>
 * 一般一个part定义为100kb（最小）到10M
 * <p>
 * ==============mime类型=========
 * <p>
 * mime类型
 * post提交表单，
 * 默认是application/x-www-form-urlencoded;charset=utf-8
 * 这样表单中的内容就以 key=value&key=value的形式在请求体中
 * 服务端获取到request的content-type后，就知道以什么方式来解析request body中的内容了
 * 这个操作都是约定好的，是很多语言框架中所支持的
 * <p>
 * ================文件上传=====================
 * 同样支持的还有  multipart/form-data
 * 用来上传文件
 * 他的request body是一块一块的
 * <p>
 * 他的request body 的约定格式是：
 * boundary=---xxxxxxxxxxxxxx
 * [换行]
 * ---xxxxxxxxxxxxx
 * content-description:from-data;name="key"
 * <p>
 * value
 * ---xxxxxxxxxxxxx
 * content-description:from-data;name="file";filename="xxx.png"
 * content-type:image/png
 * <p>
 * [png的内容]
 * <p>
 * ---xxxxxxxxxxxxx--
 * <p>
 * ================================
 * 媒体格式 Content-Type
 * <p>
 * text/html ： HTML格式
 * text/plain ：纯文本格式
 * text/xml ：  XML格式
 * image/gif ：gif图片格式
 * image/jpeg ：jpg图片格式
 * image/png：png图片格式
 * <p>
 * application/xhtml+xml ：XHTML格式
 * application/xml     ： XML数据格式
 * application/atom+xml  ：Atom XML聚合格式
 * application/json    ： JSON数据格式
 * application/pdf       ：pdf格式
 * application/msword  ： Word文档格式
 * application/octet-stream ： 二进制流数据（如常见的文件下载）
 * application/x-www-form-urlencoded ： <form encType=””>中默认的encType，form表单数据被编码为key/value格式发送到服务器（表单默认的提交数据的格式）
 * <p>
 * <p>
 * multipart/form-data ： 需要在表单中进行文件上传时，就需要使用该格式
 */

public class HttpProtocol {


}

/**
 * =================== https ===================================https://blog.csdn.net/think_program/article/details/60780843
 * https  Hypertext Transfer Protocol Secure
 * <p>
 * <p>
 * 就是http over tls/ssl
 * <p>
 * tls transport layer security 传输层安全协议
 * ssl secure socket layer 安全套接层
 * <p>
 * ssl 是网景公司发布的协议，
 * tsl是IETF(internet engineering task force 互联网工程任务小组) 在ssl 上加以改进
 * <p>
 * <p>
 * <p>
 * 于http的差异
 * 1.url开头不同
 * 2.端口不同，80  443
 * 3.https 基于 tls 上
 * 4.而 tls 是基于 Public key certificate（公开密钥认证）的
 * 5.证书是由权威CA机构颁发，
 * <p>
 * ================
 * 对称加密，加密 解密都是一个key
 * 非对称加密 ，两个key 一个public key / private key
 * <p>
 * 为什么要有https ,比如我们购物的时候，我们发送支付密码给服务器进行核对 www.taobao.com 通过post uid=001&password=123
 * 这个时候信息在网络中传输的时候可能会被劫持，从http请求中提取出我们的密码，这就不安全
 * <p>
 * 我们可以进行加密，比如讲密码123 +1 =124  ,然后传到服务器，服务器减一得到原密码
 * 但是这种是对称加密，我们加密的过程是在客户端进行的，这就会导致将加密算法暴露出来的问题
 * <p>
 * 这个时候非对称加密就诞生了！
 * <p>
 * <p>
 * 我们请求一个https 的网站，首先他会把公钥给客户端，客户端用这个公钥将我们要发送给服务器的信息加密
 * 而解密必须要用private key ,而私钥只有服务器有，所以中间拦截了也不会破解报文（messages）内容
 * <p>
 * 也就是说公钥是暴露出来的，告诉客户端用都用这个加密，私钥只要服务端才有，所以中间任何人都不能解密
 * <p>
 * 这就是非对称加密的好处
 * <p>
 * =======================非对称加密用途：数字签名==============================
 * 数字签名
 * <p>
 * 上面的例子是public key进行加密，服务端的private key进行解密，这样客户就能保证自己的信息安全的传输到服务端
 * <p>
 * 那么反过来，我们可以用private key 进行加密，服务端的内容用私钥加密，
 * 所有的客户端都可以获取到公钥，那么这个时候用公钥解密成功的，就能认证这个信息确定是指定服务端发送的
 * 而不是其他人伪造发送的，这就是用私钥签名！！
 * <p>
 * 这个技术可以仿假冒网站
 * <p>
 * 只有真正的taobao才有private key ,我们访问他的内容是他用private key加密过的
 * 我们从证书网站获取这个域名的public key 进行解密，如果解密成功，那么就可以认定这个网站是真网站
 * <p>
 * <p>
 * 非对称加密的算法也有很多种，比如 RSA 是三位科学家姓氏的首字母组合
 * 因式分解能破解它？
 * <p>
 * <p>
 * ------------------------数字签名---------------------------------
 * http://www.ruanyifeng.com/blog/2011/08/what_is_a_digital_signature.html
 * 1.首先对要发送的内容用hash函数计算，得到摘要（digest），然后对摘要用私钥进行加密
 * 加密后的信息（一串字符串）叫做数字签名，添加在内容的结尾（这个内容可以是明文）。
 * 2.然后接收人收到消息后，用公钥解密数字签名得到摘要1，然后对内容用hash函数计算得到摘要2
 * 摘要1和摘要2比较，如果相等，就说明这个内容没有被篡改过
 * 总结 hash(内容)=》摘要=》私钥加密（摘要）=》数字签名
 * 服务端 返回 内容+数字签名
 * 客户端 公钥解密（数字签名）=》摘要1 =》摘要1==hash(内容) 相等代表内容没问题
 * <p>
 * <p>
 * ===================有了数字签名为什么还要CA颁发的证书？=================
 * A用私钥加密数据，发送，B接收到数据，用A的公钥解密，成功，则代表A是合法的
 * 但是A的公钥在发送给B的时候，有可能被别人拦截，换成C的公钥，这个时候C用C的私钥
 * 加密数据发送给B，B还以为是A的。这就出现了中间人攻击。
 * 那么为了解决这个问题，就有了CA机构，A向CA机构申请制作一个证书，这个证书
 * 中有A的网站信息，A的公钥，然后CA机构用自己的私钥将这些信息加密，加密后的信息就叫做 数字证书（Digital Certificate）
 * 下次，A向B发送消息，不仅带上自己数字签名，还加上了数字证书
 * 然后B接收到消息后，用CA机构的公钥解密数字证书，然后得到A的公钥，这个公钥肯定是A的
 * 这就防止了A的公钥被造假。因为浏览器内置了各大机构的公钥。
 * <p>
 * <p>
 * ============https的用处========================================
 * https可以用来抵御中间人攻击，因为客户端有公钥，服务端有私钥，私钥才能解密
 * https还可以用作客户端认证，比如付费的接口，注册的app才能调用成功。
 * 那么一个注册的app给他分配一个私钥，服务端会认证这个私钥。
 * <p>
 * ======================https连接、请求/响应过程===============
 * https://blog.csdn.net/think_program/article/details/60780843
 * 首先进行tls握手
 * <p>
 * ( 注意！！！tcp的三次握手不变，Tcp连接成功后进行tls层的握手，成功后才能进行http的Request、Response)
 * <p>
 * 1.浏览器将自己支持的一套加密规则发送给网站。
 * 2.网站从中选出一组加密算法与HASH算法，并将自己的身份信息以证书的形式发回给浏览器。
 * 证书里面包含了网站地址，加密公钥，以及证书的颁发机构等信息。
 * 3.获得网站证书之后浏览器要做以下工作：
 * a) 验证证书的合法性（颁发证书的机构是否合法，证书中包含的网站地址是否与正在访问的地址一致等）
 * 如果证书受信任，则浏览器栏里面会显示一个小锁头，否则会给出证书不受信的提示。
 * b) 如果证书受信任，或者是用户接受了不受信的证书，浏览器会生成
 * 一串随机数的密钥（这个是后面发送信息用的对称加密密钥），
 * 并用证书中提供的公钥加密。
 * c) 使用约定好的HASH算法计算握手消息，并使用生成的随机数（上面的密钥）
 * 对hash后的握手消息进行加密，最后将之前生成的所有信息发送给网站。
 * （    随机密码加密（hash(握手消息)）+ 公钥加密（随机密码） 发送给服务器  +握手消息         ）
 * <p>
 * 4.网站接收浏览器发来的数据之后要做以下的操作：
 * a) 使用自己的私钥将信息解密取出客户端的随机密码，使用密码解密浏览器发来的握手消息，并验证HASH是否与浏览器发来的一致。
 * （  公钥解密=》随机密码 =》随机密码解密 =》hash（握手消息）=摘要1  ，自己hash(握手消息)=摘要2  比较两者             ）
 * b) 使用密码加密一段握手消息，发送给浏览器。
 * 随机密码加密（hash(握手消息)） + 握手消息
 * 5.浏览器解密并计算握手消息的HASH，如果与服务端发来的HASH一致，此时握手过程结束，之后所有的通信数据将由之前浏览器生成的随机密码并利用对称加密算法进行加密。
 * 随机密码解密=》hash(握手消息)=摘要1   自己hash(握手消息)=摘要2 比较两者
 * <p>（上面有四次来回请求响应，但其实是一次握手？？？）
 * <p>
 * 以后我们就用那个随机密码进行加密和解密了（对称加密）。非对称加密是为了安全的让服务端知道客户端的随机密码
 * 而数字证书的作用是验证服务端发来的公钥是可靠的
 * <p>
 * <p>
 * =====================为什么内容用对称加密=================
 * 因为非对称加密是很消耗性能，所以我们用对称加密，但是让服务端知道随机密码的过程也不安全
 * 所以这个可以用非对称加密
 * <p>
 * =================HTTPS一般使用的加密算法和HASH算法===============
 * <p>
 * 非对称加密算法：RSA，DSA/DSS
 * <p>
 * 对称加密算法：AES，RC4，3DES
 * <p>
 * HASH算法：MD5，SHA1，SHA256    （用于生成摘要）
 * 内容可能很多，对内容加密，还不如对 摘要进行加密，hash后的内容叫做摘要
 * <p>
 * ================VPN===============================================
 * 中国的GFW可以通过证书黑名单来拦截请求
 * Vpn  virtual private network  公司内部局域网，那么我们要通过因特网连接
 * 可以先连接到公司的对外服务器，由这个服务器进行转发，我们-》公司对外服务器之间的通信是加密的
 * <p>
 * 通信协议有：pptp Point to Point Tunneling Protocol，缩写为PPTP
 * l2tp Layer Two Tunneling Protocol，缩写为L2TP 第二层隧道协议
 * <p>
 * l2tp的加密协议一般使用 （英语：Internet Protocol Security，缩写为IPsec 互联网安全协议
 * <p>
 * gfw可以通过请求的关键字来过滤
 * 如果是内容加密，那么可以根据请求的证书（证书黑名单）来拦截
 * 还可以根据协议的特征来过滤
 * <p>
 * <p>
 * ======================如何生成数字证书=============
 * https://www.barretlee.com/blog/2015/10/05/how-to-build-a-https-server/
 * 用openssl命令
 * =============CA认证=========================
 * https://www.barretlee.com/blog/2016/04/24/detail-about-ca-and-certs/ //关于ca和证书
 * CA指的是能颁发证书的机构、个体
 * 从知名认证中心 (CA)Catificate Authority 供应商购买证书，例如 VeriSign 或 Thawte
 * 能够受浏览器默认信任的 CA 大厂商有很多，其中 TOP5 是 Symantec、Comodo、Godaddy、GolbalSign 和 Digicert。
 * <p>
 * CA 认证分为三类：DV ( domain validation)，OV ( organization validation)，EV ( extended validation)，
 * 证书申请难度从前往后递增，貌似 EV 这种不仅仅是有钱就可以申请的。
 * <p>
 * <p>
 * <p>
 * <p>
 * ========================== RFC======================================
 * 征求意见稿（英语：Request For Comments，缩写为RFC），是由互联网工程任务组（IETF）发布的一系列备忘录。
 * 文件收集了有关互联网相关信息，以及UNIX和互联网社区的软件文件，以编号排定。目前RFC文件是由互联网协会（ISOC）赞助发行。
 * RFC始于1969年，由斯蒂芬·克罗克用来记录有关ARPANET开发的非正式文档，最终演变为用来记录互联网规范、协议、过程等的标准文件。
 * <p>
 * <p>
 * <p>
 * <p>
 * ============tcp udp 区别================
 * <p>
 * <p>
 * <p>
 * 正如上一小节所指出的，当您编写 socket 应用程序的时候，您可以在使用 TCP 还是使用 UDP 之间做出选择。它们都有各自的优点和缺点。
 * TCP 是流协议，而UDP是数据报协议。换句话说，TCP 在客户机和服务器之间建立持续的开放连接，在该连接的生命期内，
 * 字节可以通过该连接写出（并且保证顺序正确）。然而，通过 TCP 写出的字节没有内置的结构，所以需要高层协议在被传输的字节流内部分隔数据记录和字段。
 * 另一方面，UDP 不需要在客户机和服务器之间建立连接，它只是在地址之间传输报文。UDP 的一个很好特性在于它的包是自分隔的（self-delimiting），
 * 也就是一个数据报都准确地指出它的开始和结束位置。然而，UDP 的一个可能的缺点在于，它不保证包将会按顺序到达，甚至根本就不保证。
 * 当然，建立在 UDP 之上的高层协议可能会提供握手和确认功能。
 * <p>
 * 对于理解 TCP 和 UDP 之间的区别来说，一个有用的类比就是电话呼叫和邮寄信件之间的区别。
 * 在呼叫者用铃声通知接收者，并且接收者拿起听筒之前，电话呼叫不是活动的。只要没有一方挂断，该电话信道就保持活动，
 * 但是在通话期间，他们可以自由地想说多少就说多少。来自任何一方的谈话都按临时的顺序发生。另一方面，当你发一封信的时候，
 * 邮局在投递时既不对接收方是否存在作任何保证，也不对信件投递将花多长时间做出有力保证。接收方可能按与信件的发送顺序不同的顺序接收不同的信件，
 * 并且发送方也可能在他们发送信件是交替地接收邮件。与（理想的）邮政服务不同，无法送达的信件总是被送到死信办公室处理，而不再返回给发送者。
 * <p>
 * <p>
 * <p>
 * <p>
 * <p>
 * ======================三次握手===========================
 * 第一次握手：客户端发送syn包(syn=j)到服务器，并进入SYN_SEND状态，等待服务器确认；
 * <p>
 * 第二次握手：服务器收到syn包，必须确认客户的SYN（ack=j+1），同时自己也发送一个SYN包（syn=k），
 * 即SYN+ACK包，此时服务器进入SYN_RECV状态；
 * <p>
 * 第三次握手：客户端收到服务器的SYN＋ACK包，向服务器发送确认包ACK(ack=k+1)，
 * 此包发送完毕，客户端和服务器进入ESTABLISHED状态，完成三次握手。
 */