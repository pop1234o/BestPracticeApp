package com.liyafeng.video;

import android.content.Context;

public class Audio {

    /**
     * =================wav=================
     * https://zhuanlan.zhihu.com/p/27338283
     * Waveform Audio File Format（WAVE，又或者是因为扩展名而被大众所知的WAV），
     * 是微软与IBM公司所开发在个人计算机存储音频流的编码格式
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
     */
    public static void main(String[] args, Context context) {

        //wav文件格式 http://soundfile.sapp.org/doc/WaveFormat/
        context.getResources().getDrawable(R.drawable.wav_format);//注意里面大小端序
        context.getResources().getDrawable(R.drawable.wav_format_1);//注意里面大小端序
        context.getResources().getDrawable(R.drawable.wav_format_2);//注意里面大小端序

    }
}
