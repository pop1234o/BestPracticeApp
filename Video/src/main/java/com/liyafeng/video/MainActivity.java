package com.liyafeng.video;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.liyafeng.video.practice.b_audio_record.AudioRecordActivity;
import com.liyafeng.video.practice.a_draw_image.DrawImageActivity;
import com.liyafeng.video.practice.c_video_record.CameraActivity;
import com.liyafeng.video.practice.f_opengl_es.OpenGLES20Activity;

/**
 * 对于视频
 * 原生的有VideoView，当然这其实是封装了MediaPlayer和SurfaceView
 * <p>
 * Exoplayer谷歌出品的播放器，用java写的
 * <p>
 * ITU 国际电信联盟  International Telecommunication Union
 * VCEG video coding expert group 是ITU下的子组织（工作组）
 * <p>
 * ISO international organization for standardization  (国际标准化组织)
 * IEC 国际电工委员会
 * <p>
 * MPEG moving picture expert group 移动图像专家组，是iso/IEC下的一个工作组
 * 几百名成员组成，专门负责 音频视频 编码标准制定的工作
 * <p>
 * 他们制定的Container Format(（视频的） 封装格式/（放置视频的）容器格式)
 * 有.mp4 .mpg .mpeg
 * <p>
 * 当然还有其他组织/公司制定的container format
 * 比如苹果的 .mov 微软的.avi  realnetworks 的.rmvb adobe的.flv
 * <p>
 * container format 容器格式 封装格式 是定义编码后的视频和音频 如果存放的格式
 * <p>
 * <p>
 * Video coding format(视频编码格式)
 * <p>
 * <p>
 * 1.H.262 or MPEG-2 Part 2 = MPEG-2 Part 2 = H.262
 * <p>
 * 2.MPEG-4 Part 2 = 兼容H.263
 * <p>
 * 3.AVC Advanced Video Coding  = H.264 or MPEG-4 Part 10 = MPEG-4 AVC (这是MPEG组织和ITU-T组织联合定义的)
 * <p>
 * 4.High Efficiency Video Coding (HEVC) = H.265 and MPEG-H Part 2 = H.265 =  MPEG-H Part 2
 * <p>
 * =================音频==================
 * PCM Pulse-code modulation 脉冲编码调制  （调制=调整，转化）模拟信号-》数字信号 叫调制
 * PCM是一种调制规则，并不是编码（压缩）规则
 * <p>
 * 调制解调器（模拟信号-》数字信号 叫调制  数字信号=》模拟信号 叫解调）
 * -------------------------
 * pcm是音频 由模拟信号转换为数字信号的规则
 * <p>
 * 视频是一张张图片的连续播放
 * yuv/rgb 是视频模拟信号转化为数字信号的规则
 * yuv y表示亮度 u和v表示色差(u和v也被称为：Cb－蓝色差，Cr－红色差)，
 * rgb red green blue  代表三原色
 * <p>
 * yuv 是常用的视频调制模式 ，因为节省空间
 * <p>
 * ------------------------
 * <p>
 * 音频编码规则
 * ACC Advanced Audio Coding 97年由杜比实验室、AT&T、Sony、Nokia 共同研发
 * 基于MPEG-2  所以又叫 MPEG-2 ACC
 * 后来2000年MPEG-4标准出现后，经过改良 成为 MPEG-4 ACC
 * <p>
 * MP1 MPEG-1 Audio Layer I 是mp2的简化，它不用很复杂的压缩算法(计算快)，但是压缩率比较低，现在几乎都不支持这个格式 .mp1
 * MP2 MPEG-1 Audio Layer II .mp2 这个压缩规则多用于广播
 * MP3 MPEG-1 or MPEG-2 Audio Layer III 这个用于网络音乐（压缩率高）
 * <p>
 * 注意不要混淆MPEG-1 和MP3的关系，MPEG-1是一组规则，而MP3是MPEG-1中音频压缩规则的一种
 * <p>
 * WMV 99年微软提出的音频编码格式
 * ========================
 * MPEG 是组织，他们制定了
 * · MPEG-1  （这个组织在1990年制定的第一个视频 和音频 压缩（编码）标准） 用于CD 、VCD
 * <p>
 * · MPEG-2 也叫"ISO/IEC 13818-2"   94年制定的第二个版本，用于DVD
 * <p>
 * · MPEG-3 本来是用于为HDTV（High Definition Television 高清电视）制定的压缩标准，
 * 但后来发现MPEG-2就足以满足需求，所以就合并到MPEG-2中了，其实没有MPEG-3的叫法
 * <p>
 * · MPEG-4 99年制定，用于网络流媒体
 * · MPEG-7 ？？？
 * · MPEG-21 正在开发？
 * · MPEG-H ？？
 * <p>
 * 每个MPEG-xxx都由很多部分（part）组成，每个部分定义了不同的规则
 * 比如MP3压缩规则是在MPEG-1 Layer 3 中定义的
 * 当然后来有改进 MPEG-1 or MPEG-2 Audio Layer III 是一种音频编码 ，有损压缩
 * <p>
 * ==================
 * ITU-T 是ITU下的一个部门，他下的一个叫VCEG的工作组制定了音频视频编码标准
 * H.261
 * H.262 就是MPEG-2 的视频部分
 * H.263
 * H.264 其实就是 MPEG-4 part 10 ,AVC 这个VCEG和MPEG一起制定的，只不过他们的叫法不一样，就像圣西罗和煤阿茶
 * H.265 就是MPEG-H 的第二部分 ，他们内容一样，叫法不一样
 * <p>
 * ======================
 * RGB /YUV 是视频的模拟信号-》数字信号（调制）的规则
 * PCM 是音频调制的规则
 * <p>
 * H.26x/MPEG-4 part10 ----视频编码规则
 * ACC /MPEG-1 Layer 3 /杜比数字（Dolby Digital），或称AC-3----音频编码规则
 * <p>
 * 封装格式
 * .mp4 .rmvb .avi .mkv  .flv（flash video）--都是不同组织定义的封装视频的规则
 * .mp3 .acc .ogg  --封装音频规则
 * --------------------------
 * Flash Video（简称FLV），是一种网络视频格式，用作流媒体格式，它的出现有效地解决了视频文件导入Flash后，使导出的SWF文件体积庞大，不能在网络上有效使用等缺点。
 * ----------------------------------
 * FLV在H.264的视频规格或是AAC的音源规格都达到功能极限，为了克服这个格式上的限制，F4V于是诞生。
 * F4V是基于ISO base media file format制定出来的容器格式（Container format）
 * 。至少于Flash Player 12.0 update 3以上版本才能播放。
 * F4V支持的视频类型为H.264，视频类型为GIF、PNG以及JPEG，音频类型为AAC以及MP3。
 * ------------------------------------
 * *MP4 (MPEG-4 Part 14)
 * <p>
 * 传输协议
 * RTMP
 * RTSP+RTP
 * HTTP
 * ==================编码后的传输协议=========================
 * RTMP、HLS 和 HTTP FLV
 * http://blog.ucloud.cn/archives/699
 * http://www.daliqckj.com/experience/2017/02-13/376.html
 * <p>
 * =================RTMP  Real-Time Messaging Protocol，实时消息传输协议=============================
 * http://mingyangshang.github.io/2016/03/06/RTMP%E5%8D%8F%E8%AE%AE/
 * 是最初由Macromedia为通过互联网在Flash播放器与一个服务器之间传输流媒体音频、视频和数据而开发的一个专有协议。Macromedia后被Adobe Systems收购
 * TCP 长连接
 * <p>
 * 优点 延时低
 * <p>
 * 缺点
 * 高并发下不稳定
 * iOS 平台要开发支持相关协议的播放器
 * 使用非标准TCP端口 默认使用TCP端口1935
 * <p>
 * ==========================HTTP Live Streaming（缩写是HLS）==========================
 * 是一个由苹果公司提出的基于HTTP的流媒体网络传输协议。是苹果公司QuickTime X和iPhone软件系统的一部分。
 * 它的工作原理是把整个流分成一个个小的基于HTTP的文件来下载，每次只下载一些。当媒体流正在播放时，
 * 客户端可以选择从许多不同的备用源中以不同的速率下载同样的资源，允许流媒体会话适应不同的数据速率
 * 。在开始一个流媒体会话时，客户端会下载一个包含元数据的extended M3U (m3u8) playlist文件，用于寻找可用的媒体流。
 * <p>
 * HLS只请求基本的HTTP报文，与实时传输协议（RTP）不同，
 * HLS可以穿过任何允许HTTP数据通过的防火墙或者代理服务器。它也很容易使用内容分发网络来传输媒体流。
 * <p>
 * 优点
 * 跨平台
 * 可通过html5解封包播放
 * <p>
 * 缺点
 * 延时高 》10s
 * <p>
 * ========================HTTP-FLV.================
 * TCP 长连接
 * <p>
 * 优点
 * 延时低
 * 可通过html5解封包播放
 * 缺点
 * 需要集成SDK才能播放
 * <p>
 * <p>
 * =======================
 * 六间房 ：RTMP + FLV +(H264 + ACC)  Flash播放器
 * ======================
 * flash player 在大部分浏览器中都默认添加
 * 支持flv 格式的视频，
 * flv包括头 和 内容  内容又分很多tag
 * <p>
 * ====================
 * 赫兹 hz 频率单位，指1秒内 "运动周期"的 次数
 * <p>
 * 采样率，从连续的模拟信号中 转换为数字信号 的 频率
 * 比如采样率为 10kHz 就是说1秒内从模拟信号中采取 10k 个数字信号
 * 那么它的倒数就是0.1/1k，代表取一个数字信号所用的时间
 * <p>
 * 采样率越高代表 越接近 真实
 * ---------------------------------
 * * 码率 = 比特率 = bit/s = bit per second = bps
 * * 采样率*每个样本的bit = 码率
 * * 清晰度高，每个样本的bit就大（更清晰）， 或者采样率高（更流畅）  =  视频更接近真实
 * *
 * * 一个MP4的比特率就是文件字节数*8bit/ 视频长度
 * ==============
 * SDL Simple DirectMedia Layer 一个跨平台的库 由C写成，用于视频 音频 输出到硬件
 * <p>
 * 多路复用 MUX和 解复用 DEMUX，因为提高一条线路上的传输效率，如果有多路信号需要传递
 * 那么我们可以将这多个信号合并为一个信号，传输，然后在另一端进行解复用，这样节约成本
 * <p>
 * 读取数据（网络或者本地硬盘） -》因为音频可以看做一条轨道，视频也是，所以将他们合到一个容器中就是复用（线路）
 * 解复用就是将容器拆分成 音轨，视频轨道 -》解码  ——》输出
 * <p>
 * <p>
 * ===============
 * color format
 * yuv444/420 (亮度 饱和度 。。)
 * rgb
 * =============
 * <p>
 * ===============
 * ASCII 有128个字符，0-127  = 00-7F = 0000 0000 - 0111 1111
 * 8位带符号
 * 一个byte 8位 可以用-128-127 的整数来表示 ，可以用
 * 80 7f 7e
 * <p>
 * 所以一个字节可以用2个16进制数来表示，如果是ascii码，则可以转化为对应的字符
 * <p>
 * ================
 * 所以一个MP4 的开始4个字节（开始的32位 或者开始的2*4个16进制数）是表示大小，后面是ftyp 表示一个box
 * 所以数据就是这样存储起来的
 * <p>
 * ============
 * 小端序-》反向；大端序-》正向排列 Endianness
 * <p>
 * ===========
 * compile 'com.googlecode.mp4parser:isoparser:1.1.21'
 * <p>
 * ============
 * unicode 和 utf-8的区别
 * https://www.zhihu.com/question/23374078
 * <p>
 * unicode是字符集，utf-8是unicode的编码规则
 * unicode是两个字节表示 0000 0000 0000 0000 65536种可能
 * 后来加上Planes（平面）的概念，每个平面就0000-ffff  现在定义了17个平面
 * U+0000 默认代表plane 0 ，U+2ffff 代表平面2， 一直到U+10ffff 代表平面16 ，所以又110w个code points(码点) 编码指向
 * <p>
 * utf-8是一种编码规则 需要n个字节 开始的n个字节写1，后面跟0， 后面每个8位开头是10，这样剩下的位就写原来的 二进制
 * <p>
 * ============
 * 高级加密标准（英语：Advanced Encryption Standard，缩写：AES
 * <p>
 * ===========
 * exoplayer完全java书写，读取，和解复用都是 自己写的
 * 解码使用MediaCodec，最终传递给系统的Surface渲染
 * <p>
 * 使用数据队列的数据结构，一遍读，入队列，取数据，解码，交给surface渲染
 */

public class MainActivity extends Activity {

    public static String TAG = "test";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        MediaCodec

//        SimplePlayer player = new SimplePlayer();
//        player.read(this);
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, DrawImageActivity.class));
            }
        });

        findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, AudioRecordActivity.class));
            }
        });
        findViewById(R.id.button_video).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, CameraActivity.class));
            }
        });

        findViewById(R.id.button_open).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, OpenGLES20Activity.class));
            }
        });

    }
}
