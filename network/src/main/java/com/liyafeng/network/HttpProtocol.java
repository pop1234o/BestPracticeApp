package com.liyafeng.network;

/**
 * Created by liyafeng on 05/11/2017.
 *
 *
 * OSI 七层网络模型
 *
 * Tcp/ip五层网络模型/架构
 *
 * (application layer)  http   请求头、响应头  https://developer.mozilla.org/en-US/docs/Web/HTTP/Overview
 * (network layer)      tcp / udp   3次握手/4次挥手  syn攻击       https://hit-alibaba.github.io/interview/basic/network/TCP.html
 * (transport layer)    ip
 * (data link layer)    wifi/网卡
 * (phycial layer)      网线
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
 * 断点下载用的是 etag 和
 * 缓存
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
 * ------------------
 *
 *
 *
 *
 */

public class HttpProtocol {
}
