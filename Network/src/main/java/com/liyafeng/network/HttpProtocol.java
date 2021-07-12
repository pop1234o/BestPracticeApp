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
 * https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/Connection （请求头讲解）
 * <p>
 * <p>
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
 * 206 Partial(局部的) Content 客户发送了一个带有Range头的GET请求，服务器完成了它（HTTP 1.1新）。
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
 * ===========Content-Type的 mime类型=====================
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


    /**
     * http 文件上传（注意文件上传）
     * <p>
     * http格式：
     * <p>
     * POST http://test2.aibrandy.com/api-manager/upload/uploadFile http/1.1
     * Content-Type: multipart/form-data; boundary=be5b4fe6-a570-49e4-9380-4515cdf87483
     * Content-Length: 564772
     * 【换行】
     * --be5b4fe6-a570-49e4-9380-4515cdf87483
     * Content-Disposition: form-data; name="file"; filename="melo.log"  //这个name其实就是key，如果是文件上传就加个filename
     * Content-Type: multipart/form-data
     * Content-Length: 564561 [这个可以不要]
     * 【换行】
     * 【文件内容】
     * --be5b4fe6-a570-49e4-9380-4515cdf87483
     * Content-Disposition: form-data; name="key"
     * 【换行】
     * value
     * --be5b4fe6-a570-49e4-9380-4515cdf87483--
     * <p>
     * <p>
     * <p>
     * <p>
     * ======================
     */
    void a1() {
    }


    /**
     * =========post请求格式==============
     * POST http://test2.aibrandy.com/api-manager/upload/uploadFile http/1.1
     * Content-Type:application/x-www-form-urlencoded
     * Content-Length: 21
     * [这里需要有个换行]
     * key=value&testKey=testValue
     * <p>
     * <p>
     * 我们看到这种格式就不好是文件上传了
     */
    void a2() {
    }


    /**
     * http connection 请求头
     * 在http1.1中，client和server都是默认对方支持长链接的， 如果client使用http1.1协议，
     * 但又不希望使用长链接，则需要在header中指明connection的值为close；如果server方也不想支持长链接，
     * 则在response中也需要明确说明connection的值为close.
     * 不论request还是response的header中包含了值为close的connection，都表明当前正在使用的tcp链接在请求处理完毕后会被断掉。
     * 以后client再进行新的请求时就必须创建新的tcp链接了
     * 。 HTTP Connection的 close设置允许客户端或服务器中任何一方关闭底层的连接双方都会要求在处理请求后关闭它们的TCP连接。
     */
    void a3() {
    }


    /**
     * ===========AES加密 高级加密标准(AES,Advanced Encryption Standard) ==============
     * https://zhuanlan.zhihu.com/p/45155135
     * <p>
     * <p>
     * <p>
     * 为最常见的对称加密算法(微信小程序加密传输就是用这个加密算法的)。对称加密算法也就是加密和解密用相同的密钥，具体的加密流程如下图：
     * <p>
     * 明文+秘钥=》加密函数=》密文 ---网络传输 --- =》密文+秘钥=》解密函数 =》明文
     * <p>
     * 是美国联邦政府采用的一种区块加密标准。这个标准用来替代原先的DES
     * <p>
     * 算法    秘钥长度  分数长度    加密轮数
     * AES-128	4	    4	        10   性能最高，因为加密轮数最少
     * AES-192	6	    4	        12
     * AES-256	8	    4	        14   安全最高
     * <p>
     * 128代表秘钥的长度是128位，8位1个字节，所以长度就是4个字节
     * <p>
     * <p>
     * AES的分组加密特性。
     * AES算法在对明文加密的时候，并不是把整个明文一股脑加密成一整段密文，而是把明文拆分成一个个独立的明文块，每一个明文块长度128bit。
     * 这些明文块经过AES加密器的复杂处理，生成一个个独立的密文块，这些密文块拼接在一起，就是最终的AES加密结果。
     * <p>
     * <p>
     * 总明文分成一个个128位（16字节）的明文
     * 【128位的明文】...【128位的明文】
     * =》秘钥+加密器
     * 【128位的密文】...【128位的密文】
     * <p>
     * 但是这里涉及到一个问题：
     * 假如一段明文长度是192bit，如果按每128bit一个明文块来拆分的话，第二个明文块只有64bit，
     * 不足128bit。这时候怎么办呢？就需要对明文块进行填充（Padding）。
     * <p>
     * NoPadding： 不做任何填充，但是要求明文必须是16字节的整数倍。
     * <p>
     * PKCS5Padding（默认）： 如果明文块少于16个字节（128bit），在明文块末尾补足相应数量的字符，且每个字节的值等于缺少的字符数。
     * 比如明文：{1,2,3,4,5,a,b,c,d,e},缺少6个字节，则补全为{1,2,3,4,5,a,b,c,d,e,6,6,6,6,6,6}
     * <p>
     * ISO10126Padding： 如果明文块少于16个字节（128bit），在明文块末尾补足相应数量的字节，最后一个字符值等于缺少的字符数，其他字符填充随机数。
     * 比如明文：{1,2,3,4,5,a,b,c,d,e},缺少6个字节，则可能补全为{1,2,3,4,5,a,b,c,d,e,5,c,3,G,$,6}
     * <p>
     * PKCS7Padding : PKCS5Padding 是他的子集，用
     * <p>
     * -------PKCS7Padding 与 PKCS5Padding区别---------------
     * https://crypto.stackexchange.com/questions/9043/what-is-the-difference-between-pkcs5-padding-and-pkcs7-padding
     * https://stackmirror.com/questions/29232705
     * <p>
     * PKCS#5 padding is defined for 8-byte block sizes,
     * PKCS#7 padding would work for any block size from 1 to 255 bytes.
     * <p>
     * -------------
     * java中用 PKCS7Padding
     * PKCS5 padding is not valid for AES, but Java still provides it which means
     * that Java is lying and is actually using PKCS7 padding in which case PKCS5Padding and PKCS7Padding
     * are the same for all intends and purposes
     * <p>
     * PKCS5对于AES算法无效，但java仍然使用它，说明java在说谎，实际上java用的是PKCS7填充方式
     * <p>
     * <p>
     * 用什么填充加密的，就得用什么填充解密，因为这样程序才能知道哪些是填充要去掉
     * -------------------
     * .模式  明文变为密文的处理方式
     * AES的工作模式，体现在把明文块加密成密文块的处理过程中。AES加密算法提供了五种不同的工作模式：
     * ECB、CBC、CTR、CFB、OFB
     * 模式之间的主题思想是近似的，在处理细节上有一些差别。我们这一期只介绍各个模式的基本定义。
     * ECB模式（默认）：
     * 电码本模式 Electronic Codebook Book
     * CBC模式：
     * 密码分组链接模式 Cipher Block Chaining
     * CTR模式：
     * 计算器模式 Counter
     * CFB模式：
     * 密码反馈模式 Cipher FeedBack
     * OFB模式：
     * 输出反馈模式 Output FeedBack
     * <p>
     * ---------------------
     * AES/CBC/PKCS7Padding
     * 加密算法 /模式/填充方式
     * <p>
     * <p>
     * 在这里我们重新梳理一下：
     * 1.把明文按照128bit拆分成若干个明文块。
     * 2.按照选择的填充方式来填充最后一个明文块。
     * 3.每一个明文块利用AES加密器和密钥，加密成密文块。
     * 4.拼接所有的密文块，成为最终的密文结果。
     * <p>
     * --------------------
     * CBC模式（Cipher Block Chaining）引入了一个新的概念
     * 初始向量IV（Initialization Vector）。
     * IV是做什么用的呢？它的作用和MD5的“加盐”有些类似，目的是防止同样的明文块始终加密成同样的密文块。
     * CBC模式在每一个明文块加密前会让明文块和一个值先做异或操作。IV作为初始化变量，参与第一个明文块的异或，后续的每一个明文块和它前一个明文块所加密出的密文块相异或。
     * 这样以来，相同的明文块加密出的密文块显然是不一样的。
     * CBC模式的好处是什么呢？
     * 安全性更高
     * 坏处也很明显：
     * 1.无法并行计算，性能上不如ECB
     * 2.引入初始化向量IV，增加复杂度。
     *
     * ---------- CBC 和 ECB ---------
     * ECB模式（Electronic Codebook Book）是最简单的工作模式，在该模式下，每一个明文块的加密都是完全独立，互不干涉的。
     * 这样的好处是什么呢？
     * 1.简单
     * 2.有利于并行计算
     * 缺点同样也很明显：
     * 相同的明文块经过加密会变成相同的密文块，因此安全性较差。
     *
     * CBC模式（Cipher Block Chaining）引入了一个新的概念：初始向量IV（Initialization Vector）。
     * IV是做什么用的呢？它的作用和MD5的“加盐”有些类似，目的是防止同样的明文块始终加密成同样的密文块。
     * （比如明文块有相同的，那么通过ECB加密出来的密文块都是相同的，而CBC会和前一个密文快异或，所以不会相同）
     *
     * CBC模式在每一个明文块加密前会让明文块和一个值先做异或操作。IV作为初始化变量，参与第一个明文块的异或，后续的每一个明文块和它前一个明文块所加密出的密文块相异或。
     * 这样以来，相同的明文块加密出的密文块显然是不一样的。
     * CBC模式的好处是什么呢？
     * 安全性更高
     * 坏处也很明显：
     * 1.无法并行计算，性能上不如ECB
     * 2.引入初始化向量IV，增加复杂度。
     *
     *
     *
     *
     */
    void a4() {
    }

/*
    public static String encrypt(String plainText) {
        try {

            StringBuilder builder = new StringBuilder("12345678123456781234567812345678");


            // 创建AES秘钥
            SecretKeySpec key = new SecretKeySpec(builder.toString().getBytes(), "AES");

            //获得加密器
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

            IvParameterSpec ivParameterSpec = new IvParameterSpec("1234567812345678".getBytes());
            // 初始化加密器
            cipher.init(Cipher.ENCRYPT_MODE, key, ivParameterSpec);

            //明文进行加密
            byte[] result = cipher.doFinal(plainText.getBytes());
            byte[] encode = Base64.encode(result, Base64.DEFAULT);
            return new String(encode);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        }

        return plainText;
    }*/

    /**
     * 解密
     *
     * @param content
     * @return
     */
   /* public static String decrypt(String content) {
        try {
            StringBuilder builder = new StringBuilder("12345678123456781234567812345678");


            // 创建AES秘钥
            SecretKeySpec key = new SecretKeySpec(builder.toString().getBytes(), "AES");

            Cipher cipherDecrypt = Cipher.getInstance("AES/CBC/PKCS5Padding");

            IvParameterSpec ivParameterSpec = new IvParameterSpec("1234567812345678".getBytes());

            cipherDecrypt.init(Cipher.DECRYPT_MODE, key, ivParameterSpec);

            byte[] doFinal = cipherDecrypt.doFinal(Base64.decode(content, Base64.DEFAULT));
            return new String(doFinal);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
        return content;
    }*/


    /**
     * ============http缓存优化 Etag ======
     *   ETAG在HTTP协议中的定义是资源实体的标记（entity tag），强标识一个资源。是缓存过期的一种代替方案（IF-MODIFIED-SINCE,IF-UNMODIFIED-SINCE）。服务器端资源一旦改变，ETAG值需要跟着改变。但是协议没有规定ETAG的计算方法，可以任意实现。一般对应静态资源（静态URL或文件）采用MD5摘要方式较好。
     * ETAG与IF-MATCH,IF-NONE-MATCH配合实现缓存方案，代替IF-MODIFIED-SINCE,IF-UNMODIFIED-SINCE（另外）
     *
     * 资源（Resource）：URL唯一标识的资源，一个URL对应一个资源。
     * ===================实现原理==================
     *
     * 服务器端创建资源，生成ETAG，每次修改也更新ETAG。
     * 客户端首次访问资源，服务器返回资源实体内容和在头区中返回ETAG值，客户端保存实体内容和ETAG值。
     * 客户端再次访问资源的时候，在头域（header）中加入“If-match:etag值”指令。
     * 服务器接受到请求后，检查资源的ETAG值是否与请求的If-match指定的etag值相同（强匹配），如果匹配则响应304 Not Modified，
     * 表示资源未改变，客户端可以直接使用前面请求中保存的资源，如果不匹配才返回资源实体（entity,也就是body体）.
     *
     * 或者：客户端再次访问资源的时候，在header中加入“If-None-Match:etag值”，如果服务器的ETAG值匹配客户端请求
     * 的etag值则返回412，表示条件冲突，不匹配则返回实体内容。
     * 客户端继续使用缓存的资源。
     * ================实际使用==================
     * If-Match:匹配则返回实体内容，否则响应304，不返回实体内容。
     * If-None-Match:不匹配则返回实体内容，否则响应412错误。
     *
     * =========ETag比Last-Modified和 Expires 的优势
     *
     * Last-Modified和Expires都是时间作为判断资源是否改变的标志存在一些隐患。
     *
     * 在秒级以内多次修改，Last-Modified和Expires无法表示出来，因为Last-Modified和Expires最小粒度为秒级。
     * 对资源多次修改，但是最后又修改回最初的内容，实际上内容并没有改变。
     * ETag值是根据实际内容变更才更新，所以可以更准确的标志资源。
     *
     * ==============实际应用
     *
     * 云存储中最为文件的tag，标记文件是否改变。一般使用MD5判断文件是否改变，也可以直接使用MD5值作为ETAG值。
     * =============问题：
     * 对大文件，修改一小部分内容后，更新ETAG，从新计算MD5，效率太低,解决方案啊~？
     *
     * ================
     * https://imweb.io/topic/5795dcb6fb312541492eda8c (HTTP缓存控制小结)
     *
     * 如果服务器发现ETag匹配不上，那么直接以常规GET 200回包形式将新的资源（当然也包括了新的ETag）发给客户端；
     * 如果ETag是一致的，则直接返回304知会客户端直接使用本地缓存即可。
     * 那么客户端是如何把标记在资源上的 ETag 传回给服务器的呢？请求报文中有两个首部字段可以带上 ETag 值：
     *
     * ⑴ If-None-Match: ETag-value
     * 示例为 If-None-Match: "5d8c72a5edda8d6a:3239" 告诉服务端如果 ETag 没匹配上需要重发资源数据，
     * 否则直接回送304 和响应报头即可。 当前各浏览器均是使用的该请求首部来向服务器传递保存的 ETag 值。
     *
     * ⑵ If-Match: ETag-value
     * 告诉服务器如果没有匹配到ETag，或者收到了“*”值而当前并没有该资源实体，则应当返回412(Precondition Failed) 状态码给客户端。
     * 否则服务器直接忽略该字段。
     * 需要注意的是，如果资源是走分布式服务器（比如CDN）存储的情况，需要这些服务器上计算ETag唯一值的算法保持一致，
     * 才不会导致明明同一个文件，在服务器A和服务器B上生成的ETag却不一样。
     *
     */
    void a5(){}


    /**
     * =======Last-Modified 来使用缓存数据==========
     * 第一次请求 返回响应中带有Last-Modified  ，把响应缓存，然后Last-Modified 的value（时间）也缓存
     * 示例： Thu, 08 Aug 2019 02:41:14 GMT
     * 下次请求的时候 带上这个时间 If-Modified-Since: Last-Modified-value
     * 如果响应没有改变，返回304，改变了返回200
     *
     *
     */
    void a6(){}


    /**
     * ===========互联网，因特网，万维网===========
     * 互联网（internet）==因特网
     * 指利用TCP/IP通讯协定所创建的各种网络，因特网的前身是阿帕网（ARPANET）
     * 最初，互联网一词代表那些使用IP协议架设而成的网络，而今天，它已引申泛指各种类型的网络，不再局限于IP网络
     *
     *
     * 1960年，美国国防部高等研究计划署（ARPA）出于冷战考虑创建的ARPANET引发了技术进步并使其成为互联网发展的中心
     * 1971年底，已经有15个节点连接到ARPANET，1973年6月，挪威地震数组所（NORSAR）连接到ARPANET成为美国本土之外的第一个网络节点
     *
     * 1974年，罗伯特·卡恩和文顿·瑟夫提出TCP/IP，定义了在电脑网络之间传送报文的方法（他们在2004年也因此获得图灵奖）
     *
     * 1986年，美国国家科学基金会创建了超级电脑中心与学术机构之间互联基于TCP/IP技术的骨干网络NSFNET
     *
     * 1980年代末和1990年代初，商业互联网服务提供商（ISP）出现。ARPANET于1990年退役
     *
     * 1980年代中后期，互联网于在欧洲和澳大利亚迅速扩张，并于1980年代后期和1990年代初期扩展至亚洲
     *
     * 1989年中，MCI Mail和CompuServe与互联网创建了连接，并且向50万大众提供了电子邮件服务
     *
     * 到1990年圣诞节，蒂姆·伯纳斯-李创建了运行万维网所需的所有工具：超文本传输协议（HTTP）[18]、超文本标记语言（HTML）、
     * 第一个网页浏览器、第一个网页服务器[19]和第一个网站
     * 他发明了三项关键技术：
     * 一个全球网络资源唯一认证的系统，统一资源标志符（URI）；
     * 超文本标记语言（HTML）；
     * 超文本传输协议（HTTP）。[12]
     *
     *
     * 1991年8月6日，世界上第一个网站在CERN（European Organization for Nuclear Research 欧洲核研究组织）搭建，
     * 而CERN则位于法国边境[27]。网站在1991年8月6日上线：
     * “Info.cern.ch是世界上第一个网站及网站服务器。网站在一台位于CERN的NeXT计算机上运作。
     * 第一个网页地址是http://info.cern.ch/hypertext/WWW/TheProject.html
     * 这个网站解释了万维网是什么，用户如何使用浏览器，如何创建网页服务器
     *
     *
     * 到1995年，NSFNET退役时，互联网在美国已完全商业化，从而解除了最后的商业流量限制
     *
     *
     * ==========www 万维网 World Wide Web ==============
     * 万维网就是基于客户机/服务器方式的 信息发现技术和超文本技术的综合。
     * 万维网并不等同互联网，万维网只是互联网所能提供的服务其中之一，是靠着互联网运行的一项服务
     * 1990年，第一个web浏览器诞生。万维网之父 tim-berners-lee 在NeXT电脑上发明了第一个web浏览器
     * 1991年，www诞生，标志web页面在
     *
     * dns其实和万维网没啥关系，万维网只是建立在dns服务之上罢了
     *
     * ==============域名 Domain Name===============
     * https://blog.csdn.net/m0_37263637/article/details/85157611 (域名分级与域名解析过程(DNS))
     * http://www.fdlly.com/p/1875261601.html（DNS 服务器基础）
     *
     * DNS（Domain Name System，域名系统）是因特网的一项服务；
     * DNS 是将域名和IP地址相互映射的一个分布式数据库；
     * DNS 是一种应用层协议，使用UDP和TCP的53端口；（tcp/ip都是需要ip的，所以在应用层必须就通过一个dns来获取到ip）
     *
     *
     * DNS最早于1983年由保罗·莫卡派乔斯（Paul Mockapetris）发明,；原始的技术规范在882号因特网标准草案（RFC 882）中发布
     * 1987年发布的第1034和1035号草案修正了DNS技术规范，并废除了之前的第882和883号草案
     *
     * 由于IP地址具有不方便记忆并且不能显示地址组织的名称和性质等缺点
     * 而全世界
     *
     * 所以域名-》ip的转换是涉及到数据存储和查找的算法。
     * DNS 以采用客户端/服务器模式的 分布式数据库 系统进行维护
     * 用树形结构来进行查找（因为这种查找效率最高，可以看看字符串匹配算法）
     *
     * -------网域---------
     * 网域可以看作是 DNS 域名命名空间内的子树
     * 网域可以按级别（顶级、二级，请参阅下文）或者按引用名（请参阅子网域）来引用。
     *
     * 根地区是 DNS 域名命名空间中的顶级地区。根地区包含所有互联网网域
     * 它可以告诉你 .com、.xxx的地址去哪个服务器去找，我们访问一个url都要经过根域名服务器（如果没有缓存）
     * 因为只有根域名服务器知道 名为.com 的顶级域名要去哪台服务器找
     *
     * 目前分布于全世界的根服务器只有13台，全部由Internet网络信息中心（InterNIC）管理
     * 在根域服务器中只保存了其下层的顶级域的DNS服务器名称和IP地址对应关系
     *
     * 顶级域位于根域下层，可以分为两类：组织域（.com，.net，.org，.gov，.edu，.mil等）和国家域（.iq，.tw，.hk，.jp，.cn等）；
     *
     * 二级域位于顶级域下层，是指为了在Internet上使用而注册到个人或企事业单位的域名；
     *
     * 子域是根据具体情况从二级域中按部门或地理位置创建
     *
     * 位于DNS域命名空间的最低层，主要指计算机的主机名；
     *
     * 也就是域名左边第一个就是主机名，当然主机名可以为空？
     *
     * 全球13组根域名服务器以英文字母A到M依序命名，域名格式为“字母.root-servers.net”。全部已以任播技术在全球多个地点设立镜像站
     * 现今，中国大陆境内共有F、I、J、L这4个根域的6台DNS镜像（L有三台镜像）在提供服务
     *
     *
     *
     * ----------------dns查找过程-------------
     * 见drawable/dns_search.png
     * 比如你电脑的网络配置里会设置dns，
     * 你从浏览器访问一个网址
     * 优先查找浏览器的 DNS 缓存，如果命中就会返回ip，然后请求这个ip
     * 未命中就会继续下一步，查找操作系统的缓存，位于操作系统的 hosts 文件
     * 当都没有的时候，会向你电脑系统里设置的dns服务器来进行查找。
     * 那个dns里如果没有缓存那么就要直接请求根域服务器，根域服务器再告诉你二级域名的服务器
     * 直到找到www.xxx.com的ip为止。然后那个dns就会返回给你ip了
     * 在你看来，你只是请求了系统设置里的dns服务器，它会给你返回对应ip
     *
     *
     *
     * --------------顶级域/顶级域名/顶级网域-----------
     * 顶级域（或顶级域名；英语：Top-level Domain；英文缩写：TLD）是互联网DNS等级之中的最高级的域
     * 顶级域名是域名的最后一个部分，即是域名最后一点之后的字母，例如在example.com这个域名中，顶级域是.com（或.COM），大小写视为相同
     * www.google.com
     * com是顶级域
     *
     *
     *
     * 顶级域主要分4类：
     * 通用顶级域（英语：Generic top-level domain，缩写为gTLD），如".com"、".net"、".org"、".edu"、".info"等，均由国外公司负责管理；
     * 国家及地区顶级域（英语：Country code top-level domain，缩写为ccTLD）
     * ，如".cn"代表中国，".uk"代表英国等，地理顶级域名一般由各个国家或地区负责管理。
     * 基础建设顶级域（.arpa，过去曾包括在“通用顶级域”内）；
     * 测试顶级域。
     *
     * .cn是地理顶级域
     * .com.cn 是.cn的子域名
     *
     *
     * .com：商业机构，任何人都可以注册；
     * .net：网络组织，例如互联网服务提供商和维修商，现在任何人都可以注册；
     * .org：非盈利组织，任何人都可以注册；
     * .edu：教育机构；
     * .gov：政府部门；
     *
     * -------子网域------
     * 子网域是网域内的子树。子网域的名称将包含它所属的网域的名称。
     * 例如，www.google.com 是 google.com 网域的子网域；而 google.com 是 .com 网域的子网域。所有网域都是根网域的子网域
     *
     *
     *
     * --------------URL-------------
     * 网址（统一资源定位符）是资源在互联网上的地址。例如，Google 主网站的地址是 http://www.google.com
     *
     *
     * -------------域名前的www-----------
     *
     * 为什么很多域名前面都有www，因为历史遗留原因，早期互联网公司以域名前缀来区分服务，如www.xxx.com,mail.xxx.com,ftp.xxx.com等，所以因为这个遗留原因导致了www延用至今。
     *
     * 在DNS行为上对www和不带www的的解析完全是处理成两个不同域名，如
     * www.xxx.com
     * xxx.com
     * 做域名后台解析设置可以将www.xxx.com指向网站ip
     * xxx.com也同样指向网站ip。当然从SEO优化上考虑，我们可以将xxx.com做301重定向到www.xxx.com
     *
     * ================dns配置==============
     *
     * ================cname===========
     * CNAME 即指别名记录，也被称为规范名字。一般用来把域名解析到别的域名上，
     * 当需要将域名指向另一个域名，再由另一个域名提供 ip 地址，就需要添加 CNAME 记录。
     *
     * 就是比如 xx.com cname 到 bb.com
     * 我去dns请求的 xx.com 的ip返回的是  bb.com 的ip （浏览器上的url不会发生变化）
     * 而配置301重定向浏览器域名就会发生变化
     *
     *
     * ===============服务器如何允许和禁止CNAME访问===========
     * https://blog.csdn.net/wzx19840423/article/details/47811545（服务器如何允许和禁止CNAME访问）
     * 如果是cloudxns.wuzx.xyz配置CNAME到www.cloudxns.net
     * 服务器可以根据http请求中的Host字段配置CNAME访问策略
     * 例如www.baidu.com就做了CNAME访问策略：baidu.xx.xx可以CNAME到www.baidu.com正常访问(现在好像也不行了)，sex.xx.xx不能CNAME到www.baidu.com正常访问
     *
     * 如果做了保护，访问会报403，比如你的申请的域名 cname到 www.baidu.com  ，主机名是baidu
     * 访问baidu.xxx.com，会报403 ，如果ping是能ping通的
     *
     * 如果没有配置解析规则，比如访问aaa.xxx.com会报一个 unknown host
     *
     *
     * ===============域名访问没有备案==========
     * 是服务器运营商搞得鬼
     * 比如阿里云服务器在有http请求到来的时候，会判断主机host，如果没有备案，则不允许访问阿里云的服务器。给用户返回无备案的信息
     *
     * 国外的服务器不存在这个问题，国内一些小的网络运营商也不太管这事儿 ，但如果你碰到新域名访问被限制，说明你的服务器运营商插手这事儿了
     *
     * ==============域名解析配置==============
     * -----设置这个域名指向（记录类型）
     * A - ipv4地址
     * cname - 指向别的域名
     *
     * -----设置域名解析前缀
     * 主机记录就是域名前缀，常见用法有：
     * www：解析后的域名为www.aliyun.com。
     * @：直接解析主域名 aliyun.com。
     * *：泛解析，匹配其他所有域名 *.aliyun.com。
     * mail：将域名解析为mail.aliyun.com，通常用于解析邮箱服务器。
     * 二级域名：如：abc.aliyun.com，填写abc。
     * 手机网站：如：m.aliyun.com，填写m。
     * 显性URL：不支持泛解析（泛解析：将所有子域名解析到同一地址）
     *
     *
     */
    void dns(){}

    /**
     * =====http referer======
     * 在header ，表示这个请求是从哪个页面发出的
     * 从别的网页上跳转到你的连接，referer会带上哪个网页的链接，
     * 服务端会判断，如果不是你的网站就给报403，不让访问。
     *
     * 1.防盗链
     * 2.防止恶意请求
     *
     * referer不对就禁止访问！
     *
     *
     */
    void referer(){}
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
 * =====================为什么内容用对称加密  =================
 * 因为非对称加密是很消耗性能，所以我们用对称加密，但是让服务端知道随机密码的过程也不安全
 * 所以这个可以用非对称加密
 * <p>
 * ==============对称加密和非对称加密的区别===========
 * 对称加密算法
 * 加密和解密用到的密钥是相同的，这种加密方式加密速度非常快，适合经常发送数据的场合。缺点是密钥的传输比较麻烦。
 * 因为秘钥传输中有可能被泄漏
 * <p>
 * 非对称加密算法
 * 加密和解密用的密钥是不同的，这种加密方式是用数学上的难解问题构造的，通常加密解密的速度比较慢，适合偶尔发送数据的场合。
 * 优点是密钥传输方便。常见的非对称加密算法为RSA、ECC和EIGamal。
 * <p>
 * 实际中，一般是通过RSA加密AES的密钥，传输到接收方，接收方解密得到AES密钥，然后发送方和接收方用AES密钥来通信。
 * 这样保证了秘钥的安全性，也保证了加密速度
 * <p>
 * =================HTTPS一般使用的加密算法和HASH算法===============
 * <p>
 * 非对称加密算法：RSA，DSA/DSS
 * <p>
 * 对称加密算法：AES，RC4，3DES
 * <p>
 * HASH算法：MD5，SHA1，SHA256    （用于生成摘要）
 * 内容可能很多，对内容加密，还不如对 摘要进行加密，hash后的内容叫做摘要
 *
 * SHA1 :算法生成 http://search.maven.org/remotecontent?filepath=commons-codec/commons-codec/1.11/commons-codec-1.11.jar
 * http://commons.apache.org/proper/commons-codec/
 * String sha1 = DigestUtils.sha1Hex(bytes)
 *
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