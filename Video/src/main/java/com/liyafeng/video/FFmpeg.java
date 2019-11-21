package com.liyafeng.video;

public class FFmpeg {


    /**
     * https://www.ffmpeg.org/
     * A complete, cross-platform solution to record, convert and stream audio and video
     * 一个完整的，跨平台的录制和转换音视频的解决方案
     * 其实就是一个软件
     * 比如从视频提取声音
     * ffmpeg -i input.mp4 output.avi
     *
     *
     *
     * @param args
     */
    public static void main(String[] args) {

    }


    /**
     * ============硬解码和软解码区别，优缺点？===========
     * 硬件解码：硬件解码从字面意思很容易理解，就是通过硬件进行视频的解码工作，其中硬件解码是由GPU来进行的，
     * 使用GPU解码能够降低CPU的工作负荷，降低功耗。 软件解码：软件解码则是通过软件本身占用的CPU进行解码，
     * 所以会增加CPU工作负荷，提升功耗， 硬解及软解的优点跟缺点： 硬解优点：播放出来的视频较为流畅，
     * 并且能够延长移动设备播放视频的时间； 硬解缺点：所解码视频格式收到GPU影响，无法部分全部视频，画质也不够清晰。
     * 软解优点：软解能够解码所有视频格式文件，且画质更加清晰；
     * 软解缺点：由于软解加大CPU工作负荷，会占用过多的移动CPU资源，如果CPU能力不足，则软件也将受到影响。
     *
     *
     */
    public void a1(){

    }
}
