package com.liyafeng.video.practice.b_audio_record;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class WaveHeader {

    public final char[] fileID = {'R', 'I', 'F', 'F'};//4个字节
    public int fileLength; //4个字节 这个文件的文件总大小  头+数据
    public char wavTag[] = {'W', 'A', 'V', 'E'};
    ;
    public char[] fmtHeaderID = {'f', 'm', 't', ' '};
    public int fmtHeaderLength; //wav头大小
    public short FormatTag; //编码方式 pcm 为1
    public short Channels; //通道数
    public int SamplesPerSec; //采样路
    public int AvgBytesPerSec; //每秒的字节数
    public short BlockAlign; // 采样块大小
    public short BitsPerSample; //采样点大小
    public char DataHeaderID[] = {'d', 'a', 't', 'a'};
    public int DataHeaderLength;//数据长度

    public byte[] getHeader() throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        WriteChar(bos, fileID);
        WriteInt(bos, fileLength);
        WriteChar(bos, wavTag);
        WriteChar(bos, fmtHeaderID);
        WriteInt(bos, fmtHeaderLength);
        WriteShort(bos, FormatTag);
        WriteShort(bos, Channels);
        WriteInt(bos, SamplesPerSec);
        WriteInt(bos, AvgBytesPerSec);
        WriteShort(bos, BlockAlign);
        WriteShort(bos, BitsPerSample);
        WriteChar(bos, DataHeaderID);
        WriteInt(bos, DataHeaderLength);
        bos.flush();
        byte[] r = bos.toByteArray();
        bos.close();
        return r;
    }

    private void WriteShort(ByteArrayOutputStream bos, int s) throws IOException {
        byte[] mybyte = new byte[2];
        mybyte[1] = (byte) ((s << 16) >> 24);
        mybyte[0] = (byte) ((s << 24) >> 24);
        bos.write(mybyte);
    }


    private void WriteInt(ByteArrayOutputStream bos, int n) throws IOException {
        byte[] buf = new byte[4];
        buf[3] = (byte) (n >> 24);
        buf[2] = (byte) ((n << 8) >> 24);
        buf[1] = (byte) ((n << 16) >> 24);
        buf[0] = (byte) ((n << 24) >> 24);
        bos.write(buf);
    }

    private void WriteChar(ByteArrayOutputStream bos, char[] id) {
        for (int i = 0; i < id.length; i++) {
            char c = id[i];
            bos.write(c);
        }
    }
}
