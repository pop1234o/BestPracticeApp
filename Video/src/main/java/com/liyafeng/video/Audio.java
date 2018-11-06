package com.liyafeng.video;

import android.content.Context;

public class Audio {

    /**
     * =================wav=================
     * https://zhuanlan.zhihu.com/p/27338283
     * Waveform Audio File Format（WAVE，又或者是因为扩展名而被大众所知的WAV），
     * 是微软与IBM公司所开发在个人计算机存储音频流的编码格式
     *
     *
     *
     *
     * @param args
     */
    public static void main(String[] args, Context context) {

        //wav文件格式 http://soundfile.sapp.org/doc/WaveFormat/
        context.getResources().getDrawable(R.drawable.wav_format);//注意里面大小端序
        context.getResources().getDrawable(R.drawable.wav_format_1);//注意里面大小端序
        context.getResources().getDrawable(R.drawable.wav_format_2);//注意里面大小端序

    }
}
