package com.liyafeng.video.practice.b_audio_record;

public class RecordAndPlayWAV {


    /**
     * =================录制=============
     * wav 文件中只不过是在pcm文件中加入的头部信息，有44个字节
     * 所以我们录制完pcm文件后，再向文件中写入头部信息，就可以
     * 保存为wav文件了
     * https://blog.csdn.net/chezi008/article/details/53064604
     *
     * ===============播放=================
     * 可以用mediaplayer 也可以用AudioTrack读取文件中的数据
     * 跳过头部的44个字节
     *
     * @param args
     */
    public static void main(String[] args) {

//        MediaPlayer
    }
}
