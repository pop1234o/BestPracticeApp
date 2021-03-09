package com.liyafeng.video;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.liyafeng.video.practice.b_audio_record.AudioRecordActivity;
import com.liyafeng.video.practice.a_draw_image.DrawImageActivity;
import com.liyafeng.video.practice.c_video_record.Camera2Activity;
import com.liyafeng.video.practice.c_video_record.Camera2_1Activity;
import com.liyafeng.video.practice.c_video_record.CameraActivity;
import com.liyafeng.video.practice.f_opengl_es.OpenGLES20Activity;

/**
 * 对于视频
 * 原生的有VideoView，当然这其实是封装了MediaPlayer和SurfaceView
 * <p>
 * Exoplayer谷歌出品的播放器，用java写的
 * <p>
 *
 * <p>
 * 视频是一张张图片的连续播放
 * yuv/rgb 是视频模拟信号转化为数字信号的规则
 * yuv y表示亮度 u和v表示色差(u和v也被称为：Cb－蓝色差，Cr－红色差)，
 * rgb red green blue  代表三原色
 * <p>
 * yuv 是常用的视频调制模式 ，因为节省空间
 * ======================
 * RGB /YUV 是视频的模拟信号-》数字信号（调制）的规则
 * PCM 是音频调制的规则
 * <p>
 * H.26x/MPEG-4 part10 ----视频编码规则
 * ACC /MPEG-1 Layer 3 /杜比数字（Dolby Digital），或称AC-3----音频编码规则
 *
 *
 *
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
 * ==========m3u8 文件格式详解
 * https://www.jianshu.com/p/e97f6555a070
 * M3U8 是 Unicode 版本的 M3U，用 UTF-8 编码。"M3U" 和 "M3U8" 文件都是苹果公司使用的 HTTP Live Streaming（HLS） 协议格式的基础，这种协议格式可以在 iPhone 和 Macbook 等设备播放。
 *
 * m3u8 文件其实是 HTTP Live Streaming（缩写为 HLS） 协议的部分内容，
 *
 * HLS 的工作原理是把整个流分成一个个小的基于 HTTP 的文件来下载，每次只下载一些。当媒体流正在播放时，客户端可以选择从许多不同的备用源中以不同的速率下载同样的资源，允许流媒体会话适应不同的数据速率。
 *
 *
 *
 *
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

 *
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
 * ============
 * 小端序-》反向；大端序-》正向排列 Endianness
 * <p>
 * <p>
 */



/**
 *  =============播放器====================
 *
 *  =============exoplayer=============
 * exoplayer 完全java书写，读取，和解复用都是 自己写的
 * 解码使用MediaCodec，最终传递给系统的Surface渲染
 * <p>
 * 使用数据队列的数据结构，一遍读，入队列，取数据，解码，交给surface渲染
 *
 *
 *
 *
 *
 * ===========mp4parser=======
 * compile 'com.googlecode.mp4parser:isoparser:1.1.21'
 *
 *
 * */
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
//                startActivity(new Intent(MainActivity.this, CameraActivity.class));
                startActivity(new Intent(MainActivity.this, Camera2Activity.class));
//                startActivity(new Intent(MainActivity.this, Camera2_1Activity.class));
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
