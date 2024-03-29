package com.liyafeng.video;

public class WebRTC {

    /**
     *
     * =====================介绍===================
     * WebRTC，名称源自网页即时通信（英语：Web Real-Time Communication）的缩写
     *      * 是一个支持网页浏览器进行实时语音对话或视频对话的API。
     *      * 它于2011年6月1日开源并在Google、Mozilla、Opera支持下被纳入万维网联盟的W3C推荐标准[2][3][4]。
     *      * 2010年5月，Google以6820万美元收购VoIP软件开发商Global IP Solutions的GIPS引擎[17][18][19]，并改为名为“WebRTC”
     *      * WebRTC使用GIPS引擎，实现了基于网页的视频会议，并支持722，PCM，ILBC，ISAC等编码，
     *      * 同时使用谷歌自家的VP8影片解码器；同时支持RTP/SRTP传输等
     *      * 2012年1月，谷歌已经把这款软件集成到Chrome浏览器中。
     *
     *
     * ================实现多人音视频通话==========
     * https://www.agora.io/cn/blog/%E5%9F%BA%E4%BA%8Ewebrtc%E6%8A%80%E6%9C%AF%E7%9A%84%E5%A4%9A%E4%BA%BA%E9%9F%B3%E8%A7%86%E9%A2%91%E8%A7%A3%E5%86%B3%E6%96%B9%E6%A1%88/
     * （声网基于webrtc的多人音视频通话解决方案）
     * 它是基于p2p(点对点)的交换音视频媒体流，和数据流的技术
     * 要实现多对多就有难度
     * 1.网状模型（P2P模式），两两连接，浪费上行带宽
     * 2.混流或者转发模型（服务器模式）（将不同的帧标记id，一起通过一个通道发送给客户端，客户端通过id进行分帧）
     * 第二种模型不是webrtc的原生实现，需要我们自己服务端进行实现
     * ==============原生的webrtc服务器模型====================
     * http://www.52im.net/thread-356-1-1.html
     * 三部分：
     *   信令服务器（（Signaling Server））
     *   TURN和STUN服务器：
     * 媒体服务器（房间服务器（Room Server））：
     *
     * Google webrtc的服务器Demo：详见https://github.com/webrtc/apprtc
     *
     * ================webrtc服务端（多人的）实现原理==============
     * 大家都知道WebRTC的服务器模型有两种，
     * 分别是MCU(Multipoint Control Unit 多点控制单元)和SFU(Selective Forwarding Unit 选择方向单元)两种
     * 这两个是模型，模型定义了功能有哪些，可以有很多不同逻辑细节的实现，但提供的主要功能都是一样的
     * 我们说某某开源系统只支持SFU，只是说他实现了这个功能
     * SFU实现的是简单转发的路由功能，而MCU可以提供更多扩展性的功能实现，
     * 而且MCU型的服务器往往包含SFU，所以MCU的实现难度较大
     *
     * MCU :对接收到的多路流进行 转码 和混合，输出为1路流，从而节省下行带宽
     *      还能够对不同网络条件的用户，订制不同码率的输出视频流，让多人场景有更好的用户体验
     *      还可以对视频流进行视频分析，做人脸检测和前景识别等，实现比较炫酷的功能
     *
     * SFU:（相当于转发路由）转发各路媒体流，典型的应用场景是1对多的直播服务，
     *      SFU从发布客户端复制音视频流的信息，然后分发到多个订阅客户端
     *
     * --------------------开源的webrtc服务器(多人的)----------------------
     * 目前在开源社区有免费的WebRTC 服务器实现，比较著名的有Licode和Kurento，
     * 使用它们可以很方便的进行服务部署并且实现多人的音视频互通功能
     *
     * ---------------------webrtc原理---------------------
     * https://blog.csdn.net/foruok/article/details/53005728 （webrtc资料大全）
     * https://hpbn.co/webrtc/ （翻墙）
     * http://luoxia.me/code/2017/01/23/WebRTC%E9%80%9A%E4%BF%A1%E5%88%9D%E6%8E%A2/（WebRTC通信初探）
     *
     * WebRTC 音频编解码是OPUS，视频编解码是VP8编解码器
     * WebRTC的传输协议是RTP/RTCP
     * 整个WebRTC通信是基于UDP的
     *
     * 他其实用到了很多协议
     * （https://developer.mozilla.org/en-US/docs/Web/API/WebRTC_API/Protocols）
     *
     *
     * 大家都知道现今直播的发展要得益于CDN分发体系，CDN主要通过RTMP协议进行传输，
     * 而WebRTC的传输协议是RTP/RTCP，所以如果我们需要使用CDN网络进行分发，
     * 就需要在服务器中将RTP/RTCP转成RTMP。在WebRTC中，编码格式是OPUS(音频)，
     * 而RTMP协议对应的编解码格式一般是AAC（音频），通常这两种编码格式之间的转换也是非常现实的需求。
     * 同时，在MCU模型中，我们还可以在服务器中增加录制、混流的功能，在直播连麦的情况下，
     * 通过混流的方式可以大大减少下行的带宽。
     *
     * 除了实现以上功能外，由于如今的直播中美颜、滤镜几乎成为了标配，所以实现这种附加功能也是市场普遍的需求。
     * 虽然在WebRTC中并没有提供实现美颜、滤镜的接口，但是我们可以在服务器端实现类似的附加功能。
     * 根据实际的业务需求，通过MCU多点控制单元，可以实现这类附加功能。
     *
     * =================连麦和观众观看==========================
     * 互动直播由连麦互动和直播两部分组成，其中连麦互动基于音视频通话实现，
     * 可以实现1个主播+3个连麦者的音视频通话连麦（基于私有协议实现）。
     *
     * 主播和连麦者的音视频数据在互动直播高性能服务器合成为一道流后推流到CDN流媒体服务器，
     * 普通观众拉流观看即可（RTMP推流协议）。
     *
     * @param args
     */
    public static void main(String[] args) {

    }

    /**
     * ========webrtc  rtc  IM 直播 ========
     *
     * -------rtc 与 webrtc -----------
     * https://www.agora.io/cn/community/blog/best/21947 Why WebRTC｜前世今生
     * （WebRTC现状及优缺点，音视频通信必看的开源项目）
     * https://mp.weixin.qq.com/s?__biz=MzIwNzA1OTA2OQ==&mid=2657207480&idx=2&sn=a34fce2d9d18d5e697a0ca79288d05e1&chksm=8c8d45aabbfaccbc38835368cd438e2907c2219eed6439dcf58f85b9f27d9173b0cac23b4a6c&scene=21#wechat_redirect
     *
     * RTC（Real-time Communications），实时通信 （音频/视频）
     * 从功能流程上来说，包含采集、编码、前后处理、传输、解码、缓冲、渲染等很多环节
     * 有美颜、滤镜、回声消除、噪声抑制等，采集有麦克风阵列等，编解码有 VP8、VP9、H.264、H.265 等
     *
     * WebRTC，是 Google 的一个专门针对网页实时通信的标准及开源项目，只提供了基础的前端功能实现，包括编码解码和抖动缓冲
     *
     * WebRTC只是RTC 技术栈中的几个小细分的技术组合
     *
     * An open framework for the web that enables Real-Time Communications (RTC) capabilities in the browser.
     * 一个开源框架，支持web端的rtc（实时通信）
     *
     *
     * 声网Agora 是怎样基于 WebRTC 进行二次开发，在Android ios端实现
     * WebRTC的传输设计基于P2P
     * WebRTC比较适合一对一的单聊，虽然功能上可以扩展实现群聊，但是没有针对群聊，特别是超大群聊进行任何优化。
     * webRTC中将 RTP协议-实时传输协议（Real-time Transport Protocol） 作为传输协议
     *
     * ---------WebRTC定义
     * 其实 WebRTC 在不同场景下包含不同的含义，它既可以代表 Google 开源的 WebRTC 项目，
     * 又可以代表 W3C（World Wide Web Consortium-万维网联盟） 工作组制定的 WebRTC 标准，
     * 也可以代表浏览器中的 WebRTC 接口，我们将他们统称为 WebRTC 技术。”
     * 多数时候，对于开发者而言 WebRTC 是一套支持网页浏览器进行实时音视频对话的 W3C Javascript API，
     * 它包括了音视频的采集、编解码、网络传输、显示等功能 。
     *
     * ------webrtc优点
     * https://mp.weixin.qq.com/s?__biz=MzIwNzA1OTA2OQ==&mid=2657207480&idx=2&sn=a34fce2d9d18d5e697a0ca79288d05e1&chksm=8c8d45aabbfaccbc38835368cd438e2907c2219eed6439dcf58f85b9f27d9173b0cac23b4a6c&scene=21#wechat_redirect
     * 1. 方便
     * WebRTC技术内置于浏览器中，用户不需要使用任何插件或者软件就能通过浏览器来实现实时通信
     * 2. 免费。虽然WebRTC技术已经较为成熟，其集成了最佳的音/视频引擎，十分先进的codec，但是Google对于这些技术不收取任何费用。
     * 3. 强大的打洞能力。WebRTC技术包含了使用STUN、ICE、TURN、RTP-over-TCP的关键NAT和防火墙穿透技术，并支持代理。
     *
     * -------webrtc缺点
     *
     *
     *
     *
     * -----------rtc 与 直播------------
     * https://bbs.huaweicloud.com/forum/thread-66540-1-1.html RTC与传统直播有什么区别 ？
     * RTC的一个具体应用是直播场景中的直播连麦，也就是低延时直播。
     * 普通直播，一般采用　RTMP协议，使用CDN进行内容分发，会有几秒甚至十几秒的延时，主播和观众的互动只能通过文字短消息或送礼来进行。
     * 而直播连麦，使用UDP协议，内容实时传输，主播和观众可以进行音视频连麦互动，实时沟通，延时一般低至几百毫秒。
     *
     * 视频通话不同于视频互动直播。视频通话不区分主播和观众，所有用户都可以发言并看见彼此
     * 直播只是主播推流，观众拉流
     *
     * 直播推流一般是rtmp(只是一个传输协议）。前面还有采集，编码，封装，rtmp封装推流，转码(转hls ，http-flv) rtmp 拉流，解封装，解码，渲染(音频/视频)
     *
     * RTC的一个具体应用是直播场景中的直播连麦
     *
     * 普通直播，一般采用TCP协议（比如rtmp），使用CDN进行内容分发，会有几秒甚至十几秒的延时，主播和观众的互动只能通过文字短消息或送礼来进行。
     * 而直播连麦，使用UDP协议，内容实时传输，主播和观众可以进行音视频连麦互动，实时沟通，延时一般低至几百毫秒。
     *
     * ----rtmp直播下的rtc连麦
     * 但这套方案需要主播端上传两路视频：
     * 一路 P2P 与连麦者进行交互，一路使用 RTMP 推到 CDN。
     * 还要下载一路视频：连麦者P2P发送过来的交互数据。对主播端带宽需求较高。
     * 主播端需要进行多路视频的编码、解码，又对主播端设备配置要求较高。
     * 而由于主播端和连麦者经过 CDN 合并成一路，无法实现主播端和连麦者视频大小窗口切换。
     *
     *
     * ----rtmp连麦 （可行，不可用）
     * 则主播端和连麦端，都分别推一路 RTMP 流到 CDN，CDN 再将这两路 RTMP 流发送给观众端，观众端将两路 RTMP 流合成为一个画面。
     * 主播也拉连麦者的rtmp流
     *
     * 主播与连麦者交互时，声音会产生干扰，形成回音；
     * 播与连麦者进行交互，在 CDN 中传输延时较大
     * 观众端要接收两条视频流，带宽、流量消耗过大，并且两路视频流解码播放，耗费CPU等资源也非常多。
     *
     *
     *
     * --------- rtc 与 im ---------
     * https://www.cnblogs.com/elesos/p/9530800.html
     * 即时通信（IM=Instant Messaging）和实时通信（rtc=Real-time Communication）都是一套网络通信系统
     * 1 场景
     * 即时通信
     * 常见场景包括文字聊天、语音消息发送、文件传输、音视频播放等。通俗的说，就是发短信。
     * 实时通信
     * 场景包括语音、视频电话会议、网络电话等。通俗的说，就是打电话。
     *
     *
     * 3.技术环节
     * 即时通信
     * 消息发送和确认，【消息接入端、服务端消息逻辑处理，服务端消息缓存和存储，转发，服务端用户状态管理，心跳机制，消息发送端】、消息接收和确认。
     *
     * 实时通信
     * 技术环节：采集、前处理、编码、【服务端接入、转发、服务端接入】、解码、播放和渲染。
     *
     * 6.可用的解决方案
     * 即时通信：XMPP，MQTT
     * 实时通信：WebRTC、Tokbox
     *
     *
     *
     *
     *
     */
    public void f1(){

    }

    /**
     * ==========信令===========
     * https://zhuanlan.zhihu.com/p/72028159 浅谈信令实现的技术难点
     * 在设备之间传递的控制信息(指令) Signaling
     *
     * 信令是为了解决一些 “网络控制”问题，比如常见的电话呼叫、接听(接受)、挂断。以前面电话的列子展开来讲，
     * 当A 拨通 B的电话，其实就是 A 给 B 发送了一个呼叫信令，
     * 而当 B 接听时，B就给 A 发送了一个接听(接受)信令，
     * 但后面两人的通话过程就不属于信令的范畴了
     * 最后 B结束通话时，需要一个挂断的操作，也就自然对应于挂断信令。
     *
     *
     * ------实现音视频通话
     * 信令通知：基于 IMLib SDK，例如 A 给 B 发起通话，A 优先会通过 IM 给 B 发送一个通话请求，
     * B 同意后再进入音视频房间进行音视频通信 音视频传输：基于 RongRTCLib SDK，例如 A 给 B 发起通话，
     * 信令传输完成后，会适用融云提供的音视频能力进行通信
     *
     * 作者：融云即时通讯云
     * 链接：https://www.zhihu.com/question/56259918/answer/1751230203
     * 来源：知乎
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * -------使用场景-------
     * 信令可以用在音视频通话、互动直播 、互动娱乐、在线教育等诸多行业，而其对应的具体场景更是丰富多彩，
     * 例如：音视频呼叫/邀请、弹幕、礼物、主播权限控制、游戏同步、白板、IoT控制消息.......
     *
     * 音视频呼叫：一对一及多人音视频通话信令
     * 聊天互动：一对一/群聊文字消息、图片消息、表情消息等
     *
     * 课程互动：白板消息、班级群消息、文件共享等
     * 课堂状态维护：互动状态（奖励、举手）、学生列表、在线状态显示等
     *
     * 聊天互动：群聊消息、弹幕、礼物、点赞等
     * 直播间状态维护：上下麦状态、直播间人数、名单管理等
     *
     * 会议互动：会议消息互动、白板消息、会议文件分享等
     * 会议室状态维护：与会人员信息、会议公告、与会人员发言、麦位控制等
     *
     * 智能手表呼叫与推送、摄像头控制、AR/VR 实时标注、智能硬件控制与信令
     *
     *
     * ---------核心技术-----
     * 信令的核心技术要素主要是以下2点：
     * 1. 网络传输的可靠性及时性：
     * 产品架构、发送接收机制、核心的TCP 长连接及其保活问题
     * 2. client 的到达率：
     * 一是，尽量保持 client 在线，这样就能实时收到信令消息，
     * 二是，如果 client 不在线，如何让它及时上线并收取信令消息
     *
     *
     * ------厂商-----
     * ---网易云信
     * 具体架构见 im_signal.png
     * 网易云信的信令SDK是19年6月新推出的信令通道产品
     * 云信信令是建立在云信多年丰富的IM技术积累之上的，具有以下特点：
     * 1. 消息到达率高达100% ，保证消息不丢失，因为整个信令沿用了云信即时通信技术的架构和经验；
     * 2. 支持可以配置的离线及在线通知，同时集成了强大的推送功能（支持Android厂商推送）；
     * 3. 接口设置简洁且丰富；
     * 4. 在Android 端做了很多进程保活的工作；
     *
     * 1.一套高效且安全的私有协议，基于tcp
     * 2.一次连接多次复用。设计了相配套长连的心跳策略以保证连接的稳定性
     * 3. Android 保活措施，包括轻量进程+双进程 、 双服务、JobScheduler、Alarm
     * 4. 云信支持了主流厂商的推送，包括小米、华为、VIVO、魅族，保证消息触达
     *
     *
     * ---声网
     * https://www.agora.io/cn/realtimemessage
     * 云信令 API
     * 与声网 Agora 实时音视频、互动直播应用打通，为用户提供实时音视频一站式解决方案
     * 消息必达，使用私有传输协议，保证优异弱网对抗能力，70% 丢包情况下到达率 100%
     *
     *
     *
     */
    void f1_0(){}

    /**
     * rtc厂商
     * ========融云=======
     * https://www.rongcloud.cn/product/call
     * 快速实现基于 WebRTC 技术的高清晰、低延迟一对一实时音视频通话功能。
     *
     * 100% 可靠的呼叫信令
     * 融云基于自有 IM 通道实现了 100% 可靠必达的音视频呼叫信令能力，通过 CallLib 组件可快速实现标准音视频通话功能。
     *
     * =======声网========
     * https://www.agora.io/cn/videocall
     *
     * 超分辨率
     * 支持移动端实时超分，实现低分辨率视频到高分辨率视频的实时重建，全面提升源视频画质和分辨率
     *
     * ======阿里云========
     * 基于阿里云自研的音频预处理、视频编解码器、弱网对抗算法、数据监控大盘，确保优质的音视频体验和全面的质量监测
     *
     * 旁路转推
     * 可将音视频通话内容旁路转推至视频直播中心，观众可通过CDN方式观看直播。
     *
     * 云端录制
     * 可将音视频通话画面同步到云端进行云端混流，并将混流后的频道内容进行录制保存。
     *
     * ======环信=======
     *
     * =====腾讯云======
     * https://cloud.tencent.com/developer/article/1645585?from=14588
     *
     * IM和TRTC协同。
     *
     *
     *
     */
    void f1_1(){}


    /**
     * ======== webrtc架构图 =====
     * https://webrtc.github.io/webrtc-org/architecture/
     *
     *
     * =======rtc架构图=======
     * https://blog.csdn.net/vn9PLgZvnPs1522s82g/article/details/97713312
     *
     *
     * ------融云
     * http://www.fromgeek.com/daily/1044-318205.html
     * 为开发者提供即时通讯与实时音视频能力融合的PaaS云服务，
     * 未来将以“IM+RTC+推送”的整体解决方案，满足开发者对于互联网通信能力的需求
     *
     *
     * ----高可靠的IM信令保障
     * 基于TCP协议的消息通道，把一段数据通过一个长连接的、永远在线的通道从一端推送到另外一端
     *
     * 当TCP连接断开时，融云还可将消息通过自有的推送通道或厂商推送通道通知手机客户端，无需额外集成第三方推送平台。
     * 正是通过这种方式，能够确保在长时间通话及弱网情况下，融云音视频服务依然可靠连通且稳定。
     *
     * ---- RTC和IM
     * 由于RTC和IM在线应用的需求不同，决定了二者对网络传输中的技术要求有所不同。
     * 区别在于：IM 追求的是消息 100% 到达，不丢、不重、不乱序，低延时不是首要目标。
     * 但对于RTC而言，保证画面传输的实时性，画面清晰、流畅、无卡顿，保障低延时性才是重中之重
     *
     */
    void f1_2(){}

    /**
     * 目前，主流的视频编码器分为3个系列：VPx（VP8，VP9），H.26x（H.264，H.265），AVS（AVS1.0，AVS2.0）
     *
     * VP8，是视频压缩解决方案厂商On2 Technologies的第八代视频编解码标准，
     * Google收购On2后，就将VP8开源了，并且将其应用到WebRTC中。目前，Google也在主推新一代的编解码标准——VP9。
     *
     * H.264，是由ITU-T视频编码专家组（VCEG）和ISO/IEC动态图像专家组（MPEG）联合组成的
     * 联合视频组（JVT，Joint Video Team）提出的高度压缩数字视频编解码器国际标准。 WebRTC也同时支持H.264。
     *
     * H.265在硬件支持上比较广泛，Apple、高通、intel等的芯片都支持H.265的硬件编解码器。VP9的硬件支持依然十分有限
     *
     * AVS是我国具备自主知识产权的第二代信源编码标准。目前，AVS1.0在第三世界国家中已有广泛应用。
     * AVS2.0，属于与H.265和VP9同级的新一代标准。
     *
     *
     *
     */
    void f2(){}

    /**
     * ======RTP协议即实时传输协议（Real-time Transport Protocol）========
     * https://www.huaweicloud.com/articles/79999bf7e4235d59927366e0dd1ca267.html 音视频传输：RTP协议详解和H.264打包方案
     *
     * 一种网络传输协议,一般负责音视频数据的封包和传输。
     * 其中IETF多媒体小组在1996年的RFC1889就给出了该协议的规范和细节，其后在RFC3550中进行了更新，
     * 如果你要系统性学习，直接看RFC3550规范即可。
     *
     * 跟RTSP、RTCP的关系：
     *
     * RTCP协议：实时传输控制协议即Real-time Transport Control Protocol，这个协议是RTP协议的姊妹协议，
     * 它是为了进行服务质量的监视与反馈、媒体间的同步，以及多播组中成员的标识，目前WecRTC用这个协议进行流量和拥塞控制。
     *
     * RTSP协议：实时流协议即Real Time Streaming Protocol，这是一种会话管理和媒体控制的协议，用的最多地方就是视频监控。
     * 视频监控中摄像机、NVR、前后端之间都是用这个协议和RTP协议配合进行流媒体传输。
     *
     *
     */
    void f3(){}


    /**
     * =====码率波动===
     * https://www.sohu.com/a/214741523_355140 每日数亿次微信视频通话背后，靠什么技术支撑？
     * 设置目标码率 360kbps 的每秒数据量波动图中，
     * 紫色线是微信自研视频编解码器的码率波动情况，可以看出每秒的码率基本都稳定在 360kb 左右，
     * 而蓝色线表示的同等编码效率下 x264 码率波动情况，
     * 在一些运动比较剧烈的场景，码率会上升到 420kb，明显高于目标码率，这对我们实时视频通话应用就会有很大的冲击。
     *
     * 谷沉沉，腾讯专家研究员 & 微信视频技术负责人。2007 年硕士毕业于哈尔滨工业大学，在校课题研究期间参与过 AVS、H.264SVC 等视频编解码标准技术研究，
     * 加入腾讯后也一直从事视频图像相关的技术研发工作，先后主导过 QQ、微信、手机 QQ 视频通话、腾讯视频等产品的视频技术研发，
     * 目前主要负责微信视频通话、朋友圈视频图片等业务相关的视频图像技术研发和团队技术管理。
     * 拥有丰富的视频技术研究与应用实战经验，在国际视频领域知名学术会议刊物上发表多篇论文，数十项视频技术领域的发明专利在国内外获得授权，其中两件独立发明的专利荣获中国专利奖。
     *
     */
    void f4(){}


    /**
     * ==========阿里云 微消息队列MQTT版 ========
     * https://help.aliyun.com/document_detail/175683.html#section-n7z-mvf-kql
     * 见im_flow2.jpg
     * 微消息队列MQTT版在音视频会议场景中充当了信令传输的角色。
     *
     */
    void f5(){}


    /**
     * ======WebRTC P2P 连接技术之 STUN 和 ICE 协议解密========
     * https://zhuanlan.zhihu.com/p/344480511 WebRTC P2P 连接技术之 STUN 和 ICE 协议解密
     *
     */
    void f6(){}


    /**
     * ======实时通话文档===
     * https://docs-im.easemob.com/im/200androidclientintegration/80audiovideo
     * 接听是广播，然后唤起接听页面
     *
     *
     * -------声网
     *
     * https://docs.agora.io/cn/Video/start_call_android?platform=Android
     *
     *
     *
     */
    void f7(){}


    /**
     * =========voip=======
     * http://www.qzlink.com/voip 启智科技官网
     * https://zhuanlan.zhihu.com/p/387214280 启智科技VoIP SIP SDK产品
     *
     * 基于IP的语音传输（英语：Voice over Internet Protocol，缩写为VoIP）是一种语音通话技术，经由网际协议（IP）来达成语音通话与多媒体会议
     * ，也就是经由互联网来进行通信。其他非正式的名称有IP电话（IP telephony）、互联网电话（Internet telephony）、
     * 宽带电话（broadband telephony）以及宽带电话服务（broadband phone service）
     *
     * voip使用的协议 https://baike.baidu.com/item/VoIP/110300?fr=aladdin
     * H.323是一种ITU-T标准，最初用于局域网（LAN）上的多媒体会议，后来扩展至覆盖VoIP。该标准既包括了点对点通信也包括了多点会议。
     * 会话发起协议（SIP）是建立VOIP连接的IETF标准。SIP是一种应用层控制协议，用于和一个或多个参与者创建、修改和终止会话。SIP的结构与HTTP（客户－服务器协议）相似
     * 媒体网关控制协议（MGCP）定义了呼叫控制单元（呼叫代理或媒体网关）与电话网关之间的通信服务
     * 媒体网关控制协议（MEGACO）是IETF和ITU-T（ITU-TH.248建议）共同努力的结果。Megaco/H.248是一种用于控制物理上分开的多媒体网关的协议单元的协议，从而可以从媒体转化中分离呼叫控制。
     *
     * 在业界IP电话有两种并列的体系结构，一种是以上介绍的ITU－TH.323协议，另一种是由互联网工程任务组（IETF）提出的SIP协议（RFC2543），SIP协议更适用于智能化终端。
     *
     * 人们利用voip双模手机可以通过网络，以超低的价格拨打普通电话和手机，
     * voip手机之间通过网络互相通话更是免费的，且无需运营商的支持，同时简单通过手机终端就能实现3方通话
     *
     *
     *
     *
     * ======rtc与voip ==========
     *
     * https://www.vocal.com/webrtc/webrtc-and-voip-compatibility/ 【好文】（WebRTC and VOIP Compatibility）
     * rtc是一套编码解码，然后音视频帧传输的协议，低延时，高通话质量保证。
     *
     *
     * WebRTC 旨在与传统的 IP 语音 (VoIP) 网络兼容，同时仍留有创新空间。
     * 因此，WebRTC 指定使用 VoIP 设备中常见的音频和视频编码器和解码器，
     * 例如用于视频的 H.264 和用于音频的 OPUS 和 G.711。这允许在 WebRTC 浏览器和 VoIP 端点之间进行媒体通信，而无需对流进行转码。
     *
     * 即使具有媒体编码器兼容性，WebRTC 也不能与 VoIP 设备完全互操作；
     * WebRTC 将 WebSockets 定义为用于发送呼叫信号的会话发起协议 (SIP) 的传输。
     * VoIP 设备倾向于使用用户数据报协议 (UDP) 和传输控制协议 (TCP) 作为信令层中 SIP 的传输。
     *  （
     *  WebRTC defines the use of WebSockets as a transport for the Session Initiation Protocol (SIP) used to signal calls.
     *  VoIP devices tend to use User Datagram Protocol (UDP) and Transmission Control Protocol (TCP) as transports for SIP in the signaling layer.
     *
     *  ）
     *  总结：音视频通话都有sip协议，用于打电话的 signal call（呼叫），只是规定点与点之间如何通信的，而不是具体的实现技术
     *  webrtc用 websocket 实现的sip协议  SIP over WebSockets
     *  voip 用 udp+tcp 实现的sip协议 SIP over UDP
     *
     * =========webrtc端和voip端通信
     * 启用 WebRTC 和 VoIP 端点之间通信的一种可能方法是使用网关。
     * 网关可以放置在信令路径和媒体路径的端点之间。
     * 信令网关用于将 SIP over WebSockets 转换为 VoIP 设备可以理解的信令协议，例如 SIP over UDP
     *
     * （通过网关将 SIP over WebSockets 转换为  SIP over UDP）
     *
     * =======SIP协议==========
     * SIP（Session initialization Protocol，会话初始协议）是由IETF（Internet Engineering Task Force，因特网工程任务组）制定的多媒体通信协议。
     * 它是一个基于文本的应用层控制协议，用于创建、修改和释放一个或多个参与者的会话。
     * SIP 是一种源于互联网的IP 语音会话控制协议，具有灵活、易于实现、便于扩展等特点
     *
     *
     * ====H.323协议和SIP协议的比较======
     * http://www.zcallr.cn/tech/cc/tech-6.html
     * 都利用RTP作为媒体传输的协议
     *
     * h.323标准是itu-t组织 1996年在h.320/h.324的基础上建立起来的，其应用目标是，在基ip的网络环境中，实现可靠的面向音视频和数据的实时应用。如今经过多年的技术发展和标准的不断完善，h.323已经成为被广大的itu成员以及客户所接受的一个成熟标准族。
     *
     * sip标准是ietf组织在1999年提出的，其应用目标是在基于internet环境，实现数据、音视频实时通讯，特别是通过internet将视频通讯这种应用大众化，引入到千家万户。由于sip协议相对于h.323而言，相对简单、自由，厂商可以使用相对小的成本就可以构造满足应用的系统。例如仅仅使用微软基于sip协议的msn，和rtc就可以构造一个简单的，基于internet应用环境的视频通讯环境。这样网络运营商就可以在尽量少的成本基础上，利用现有的网络资源开展视音频通讯业务的扩展工作。
     *
     * ========  H.323 与 ，SIP协议======
     * 两者都是信令协议（用于呼叫，接通，挂断）
     * http://www.zcallr.cn/tech/cc/tech-9.html  VoIP相关技术标准
     * 在业界IP电话有两种并列的体系结构，一种是以上介绍的ITU－TH.323协议，
     * 另一种是由互联网工程任务组（IETF）提出的SIP协议（RFC2543），SIP协议更适用于智能化终端。
     *
     * =======Android SIP应用============
     * https://developer.android.google.cn/guide/topics/connectivity/sip
     * 需要用 SipManager 于sip服务商建立链接，才能发起语音通话。
     *
     * android.net.sip.SipManager #isVoipSupported  isVoipSupported()
     * isApiSupported isApiSupported()
     *
     * android.Manifest.permission#INTERNET} and {@link android.Manifest.permission#USE_SIP}
     *
     * 用于过滤支持voip的应用，支持才在Google Play 上展示
     * <uses-feature android:name="android.hardware.sip.voip" />
     *
     * 目前国内厂商都不支持SipManager  voip
     *
     *
     *
     */
    void f8(){

    }

}
