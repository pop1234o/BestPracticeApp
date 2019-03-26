package com.liyafeng.video.practice.b_audio_record;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class RecordAndPlayWAV {


    private static final String TAG = "test";

    /**
     * =================录制=============
     * wav 文件中只不过是在pcm文件中加入的头部信息，有44个字节
     * 所以我们录制完pcm文件后，再向文件中写入头部信息，就可以
     * 保存为wav文件了
     * https://blog.csdn.net/chezi008/article/details/53064604
     * <p>
     * 文件格式分析
     * http://www.veryitman.com/2018/02/20/%E9%9F%B3%E8%A7%86%E9%A2%91%E7%BC%96%E7%A8%8B-%E7%AE%80%E5%8D%95%E5%88%86%E6%9E%90-WAV-%E6%96%87%E4%BB%B6/
     * <p>
     * ==========pcm 转 wav==========
     * https://zmywly8866.github.io/2015/03/12/wav-and-pcm.html
     * <p>
     * <p>
     * ===============播放=================
     * 可以用mediaplayer 也可以用AudioTrack读取文件中的数据
     * 跳过头部的44个字节
     *
     * @param args
     */
    public static void main(String[] args) {

//        MediaPlayer


    }

    /**
     * pcm转wav，格式转换成功后立即删除源文件，得到的wav文件头信息为：16位声双道 8000 hz
     *
     * @param source         源文件
     * @param target         目标文件
     * @param isDeleteSource 是否删除源文件，true 删除，false 保留
     * @throws IOException
     * @throws Exception   抛出错误
     * @author liyuqi
     * @date 2017年1月11日
     */
    public static void pcmToWav(File source, File target)
            throws IOException {
        FileInputStream fis = new FileInputStream(source);
        FileOutputStream fos = new FileOutputStream(target);
        // 计算长度
        byte[] buf = new byte[1024 * 4];
        int size = fis.read(buf);
        int PCMSize = 0;
        while (size != -1) {
            PCMSize += size;
            size = fis.read(buf);
        }
        fis.close();
        // 填入参数，比特率等等。这里用的是16位声双道 8000 hz
        WaveHeader header = new WaveHeader();
        // 长度字段 = 内容的大小（PCMSize) + 头部字段的大小(不包括前面4字节的标识符RIFF以及fileLength本身的4字节)
        header.fileLength = PCMSize + (44 - 8);
        header.fmtHeaderLength = 16;// 头字节数，16或18，如果是18则又附加信息
        header.BitsPerSample = 16;// 每个采样需要的bit数，相当于64K，计算方式为16位(16bit)则代表2的16次方=65536 / 1024 =64K
        header.Channels = 1;// 声道，单声道为1，双声道为2
        header.FormatTag = 0x0001;// 编码方式，一般为0x0001
        header.SamplesPerSec = 16000;// 采样频率
        header.BlockAlign = (short) (header.Channels * header.BitsPerSample / 8);// 数据块对齐单位(每个采样需要的字节数)
        header.AvgBytesPerSec = header.BlockAlign * header.SamplesPerSec;// 每秒所需字节数
        header.DataHeaderLength = PCMSize;// 采样数据字节长度
        byte[] head = header.getHeader();
        assert head.length == 44; // WAV标准，头部应该是44字节
        // 写入wav头信息
        fos.write(head, 0, head.length);
        // 写入数据流
        fis = new FileInputStream(source);
        size = fis.read(buf);
        while (size != -1) {
            fos.write(buf, 0, size);
            size = fis.read(buf);
        }
        fis.close();
        fos.close();
        System.out.println("****pcm 转 wav格式转换成功****");
//        if (isDeleteSource){
//            source.delete();// 删除源文件
//            System.out.println("******删除源成功******");
//        }
    }

}
