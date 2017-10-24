package com.liyafeng.video;

import android.app.Activity;
import android.os.Bundle;

/**
 * 对于视频
 * 原生的有VideoView，当然这其实是封装了MediaPlayer和SurfaceView
 *
 * Exoplayer谷歌出品的播放器，用java写的
 *
 * ITU 国际电信联盟  International Telecommunication Union
 * VCEG video coding expert group 是ITU下的子组织（工作组）
 *
 * ISO international organization for standardization  (国际标准化组织)
 * IEC 国际电工委员会
 *
 * MPEG moving picture expert group 移动图像专家组，是iso/IEC下的一个工作组
 * 几百名成员组成，专门负责 音频视频 编码标准制定的工作
 *
 * 他们制定的Container Format(（视频的） 封装格式/（放置视频的）容器格式)
 * 有.mp4 .mpg .mpeg
 *
 * 当然还有其他组织/公司制定的container format
 * 比如苹果的 .mov 微软的.avi  realnetworks 的.rmvb adobe的.flv
 *
 * container format 容器格式 封装格式 是定义编码后的视频和音频 如果存放的格式
 *
 *
 * Video coding format(视频编码格式)
 *

 * 1.H.262 or MPEG-2 Part 2 = MPEG-2 Part 2 = H.262
 *
 * 2.MPEG-4 Part 2 = 兼容H.263
 *
 * 3.AVC Advanced Video Coding  = H.264 or MPEG-4 Part 10 = MPEG-4 AVC (这是MPEG组织和ITU-T组织联合定义的)
 *
 * 4.High Efficiency Video Coding (HEVC) = H.265 and MPEG-H Part 2 = H.265 =  MPEG-H Part 2
 *
 *===================================
 * PCM Pulse-code modulation 脉冲编码调制  （调制=调整，转化）模拟信号-》数字信号 叫调制
 * PCM是一种调制规则，并不是编码（压缩）规则
 *
 *
 * 音频编码规则
 * ACC Advanced Audio Coding 97年由杜比实验室、AT&T、Sony、Nokia 共同研发
 * 基于MPEG-2  所以又叫 MPEG-2 ACC
 * 后来2000年MPEG-4标准出现后，经过改良 成为 MPEG-4 ACC
 *
 * MP1 MPEG-1 Audio Layer I 是mp2的简化，它不用很复杂的压缩算法(计算快)，但是压缩率比较低，现在几乎都不支持这个格式 .mp1
 * MP2 MPEG-1 Audio Layer II .mp2 这个压缩规则多用于广播
 * MP3 MPEG-1 or MPEG-2 Audio Layer III 这个用于网络音乐（压缩率高）
 *
 * 注意不要混淆MPEG-1 和MP3的关系，MPEG-1是一组规则，而MP3是MPEG-1中音频压缩规则的一种
 *
 * WMV 99年微软提出的音频编码格式
 * ========================
 * MPEG 是组织，他们制定了
 * · MPEG-1  （这个组织在1990年制定的第一个视频 和音频 压缩（编码）标准） 用于CD 、VCD
 *
 * · MPEG-2 也叫"ISO/IEC 13818-2"   94年制定的第二个版本，用于DVD
 *
 * · MPEG-3 本来是用于为HDTV（High Definition Television 高清电视）制定的压缩标准，
 *   但后来发现MPEG-2就足以满足需求，所以就合并到MPEG-2中了，其实没有MPEG-3的叫法
 *
 * · MPEG-4 99年制定，用于网络流媒体
 * · MPEG-7 ？？？
 * · MPEG-21 正在开发？
 * · MPEG-H ？？
 *
 * 每个MPEG-xxx都由很多部分（part）组成，每个部分定义了不同的规则
 * 比如MP3压缩规则是在MPEG-1 Layer 3 中定义的
 * 当然后来有改进 MPEG-1 or MPEG-2 Audio Layer III 是一种音频编码 ，有损压缩
 *
 * ==================
 * ITU-T 是ITU下的一个部门，他下的一个叫VCEG的工作组制定了音频视频编码标准
 * H.261
 * H.262 就是MPEG-2 的视频部分
 * H.263
 * H.264 其实就是 MPEG-4 part 10 ,AVC 这个VCEG和MPEG一起制定的，只不过他们的叫法不一样，就像圣西罗和煤阿茶
 * H.265 就是MPEG-H 的第二部分 ，他们内容一样，叫法不一样
 *
 * ======================
 * RGB /YUV 是视频的模拟信号-》数字信号（调制）的规则
 * PCM 是音频调制的规则
 *
 * H.26x/MPEG-4 part10 ----视频编码规则
 * ACC /MPEG-1 Layer 3 ----音频编码规则
 *
 * 封装格式
 * .mp4 .rmvb .avi .mkv  .flv（flash video）--都是不同组织定义的封装视频的规则
 * .mp3 .acc .ogg  --封装音频规则
 *
 * *MP4 (MPEG-4 Part 14)
 *
 * 传输协议
 * RTMP
 * RTSP+RTP
 * HTTP
 *
 * =======================
 * 六间房 ：RTMP + FLV +(H264 + ACC)  Flash播放器
 * ======================
 * flash player 在大部分浏览器中都默认添加
 * 支持flv 格式的视频，
 * flv包括头 和 内容  内容又分很多tag
 *
 * ====================
 * 赫兹 hz 频率单位，指1秒内 "运动周期"的 次数
 *
 * 采样率，从连续的模拟信号中 转换为数字信号 的 频率
 * 比如采样率为 10kHz 就是说1秒内从模拟信号中采取 10k 个数字信号
 *  那么它的倒数就是0.1/1k，代表取一个数字信号所用的时间
 *
 * 采样率越高代表 越接近 真实
 * ==============
 * SDL Simple DirectMedia Layer 一个跨平台的库 由C写成，用于视频 音频 输出到硬件
 *
 * 多路复用 MUX和 解复用 DEMUX，因为提高一条线路上的传输效率，如果有多路信号需要传递
 * 那么我们可以将这多个信号合并为一个信号，传输，然后在另一端进行解复用，这样节约成本
 *
 * 读取数据（网络或者本地硬盘） -》因为音频可以看做一条轨道，视频也是，所以将他们合到一个容器中就是复用（线路）
 * 解复用就是将容器拆分成 音轨，视频轨道 -》解码  ——》输出
 *
 *
 * ===============
 * color format
 * yuv444/420 (亮度 饱和度 。。)
 * rgb
 * =============
 * 码率 = 比特率 = bit/s = bit per second = bps
 * 采样率*每个样本的bit = 码率
 * 清晰度高，每个样本的bit就大（更清晰）， 或者采样率高（更流畅）  =  视频更接近真实
 *
 * ===============
 * ASCII 有128个字符，0-127  = 00-7F = 0000 0000 - 0111 1111
 * 8位带符号
 * 一个byte 8位 可以用-128-127 的整数来表示 ，可以用
 * 80 7f 7e
 *
 *
 *
 *
 *
 *
 *
 */

public class MainActivity extends Activity {

    public  static  String TAG ="test";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        MediaCodec

        SimplePlayer player = new SimplePlayer();
        player.read(this);
    }
}
