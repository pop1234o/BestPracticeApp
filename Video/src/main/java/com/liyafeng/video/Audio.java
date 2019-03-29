package com.liyafeng.video;

import android.content.Context;

public class Audio {

    /**
     * =================wav=================
     * https://zhuanlan.zhihu.com/p/27338283
     * Waveform Audio File Format（WAVE，又或者是因为扩展名而被大众所知的WAV），
     * 是微软与IBM公司所开发在个人计算机存储音频流的编码格式
     * <p>
     * 只是在pcm基础上加上了文件头而已
     * <p>
     * <p>
     * =================PCM==============
     * PCM(Pulse Code Modulation)也被称为脉冲编码调制。
     * PCM音频数据是未经压缩的音频采样数据裸流，它是由模拟信号经过采样、量化、编码转换成的标准的数字音频数据。
     * <p>
     *  采样频率：即取样频率,指每秒钟取得声音样本的次数。采样频率越高,声音的质量也就越好,声音的还原也就越真实，但同时它占的资源比较多。由于人耳的分辨率很有限,太高的频率并不能分辨出来。在16位声卡中有22KHz、44KHz等几级,其中，22KHz相当于普通FM广播的音质，44KHz已相当于CD音质了，目前的常用采样频率都不超过48KHz。 
     *  采样位数：即采样值或取样值（就是将采样样本幅度量化）。它是用来衡量声音波动变化的一个参数，也可以说是声卡的分辨率。它的数值越大，分辨率也就越高，所发出声音的能力越强。
     *  声道数：很好理解，有单声道和立体声之分，单声道的声音只能使用一个喇叭发声（有的也处理成两个喇叭输出同一个声道的声音），立体声的PCM 可以使两个喇叭都发声（一般左右声道有分工） ，更能感受到空间效果。
     * <p>
     * 现在我们就可以得到PCM文件所占容量的公式：存储量 = (采样频率*采样位数*声道)*时间/8(单位：字节数).
     * 例如，数字激光唱盘(CD－DA，红皮书标准)的标准采样频率为44.lkHz，采样数位为16位，立体声(2声道)，可以几乎无失真地播出频率高达22kHz的声音，这也是人类所能听到的最高频率声音。激光唱盘一分钟音乐需要的存储量为：
     * <p>
     * (44.1*1000*l6*2)*60/8=10，584，000(字节)=10.584MBytes
     * <p>
     * <p>
     * <p>
     * =================RIFF=============
     * 其实就是一种约定好的文件格式，前几位放什么数据都规定好，这样统一好进行软件开发
     * <p>
     * 资源交换文件格式（英语：Resource Interchange File Format，缩写为RIFF），又译资源互换文件格式，是一种文件格式（meta-format）标准，把数据存储在被标记的区块（tagged chunks）中。它是在1991年时，由Microsoft和IBM提出。它是Electronic Arts在1985提出的Interchange File Format（IFF）的翻版。这两种标准的唯一不同处是在多比特整数的存储方式。 RIFF使用的是小端序，这是IBM PC使用的处理器80x86所使用的格式，而IFF存储整数的方式是使用大端序，这是Amiga和Apple Macintosh计算机使用的处理器，68k，可处理的整数类型。
     * <p>
     * Microsoft在AVI和WAV这两种著名的文件格式中，都使用RIFF的格式当成它们的基础。
     * <p>
     * RIFF文件由一个简单的表头（header）跟随着多个"chunks"所组。其格式完全跟IFF一样，除整数的存储方式不一样以外。
     * <p>
     * 表头（Header）
     * 4位组（bytes）：固定为"RIFF".
     * 4位组：little-endian 32-bit正整数，整个文件的大小，扣掉识别字符和长度，共8个字节。
     * 4位组：这个文件的类型字符，例如："AVI "或"WAVE".
     * 接下来是区块（Chunks），每个区块包含：
     * 4位组：此区块的ASCII识别字，例如："fmt "或"data".
     * 4位组：little-endian 32-bit正整数，表示本区块的长度（这个正整数本身和区块识别字的长度不算在内）。
     * 不固定长度字段：此区块的数据，大小等同前一栏之正整数。
     * 假如区块的长度不为偶数，则填入一个byte
     * ========AGC===========
     * AGC是自动增益补偿功能（Automatic Gain Control），AGC可以自动调麦克风的收音量，使与会者收到一定的音量水平，不会因发言者与麦克风的距离改变时，声音有忽大忽小声的缺点。
     *
     * ANS是背景噪音抑制功能（Automatic Noise Suppression），ANS可探测出背景固定频率的杂音并消除背景噪音，例如：风扇、空调声自动滤除。呈现出与会者清晰的声音。
     *
     * AEC是回声消除器（Acoustic Echo Chancellor）,AEC可以消除各种延迟的回声。
     */
    public static void main(String[] args, Context context) {

        //wav文件格式 http://soundfile.sapp.org/doc/WaveFormat/
        context.getResources().getDrawable(R.drawable.wav_format);//注意里面大小端序
        context.getResources().getDrawable(R.drawable.wav_format_1);//注意里面大小端序
        context.getResources().getDrawable(R.drawable.wav_format_2);//注意里面大小端序

    }
}
