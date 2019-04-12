package com.liyafeng.practice.util;

import android.content.Context;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;
import android.os.Build;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class AudioUtil {


    private static FileOutputStream outputStream;

    public static void startRecord(Context context) {
        try {
            if (outputStream != null) {
                outputStream.close();
                outputStream = null;
            }
            outputStream = new FileOutputStream(new File(context.getExternalCacheDir(), "test.pcm"));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveAudioFile(byte[] bytes) {
        if (outputStream != null) {
            try {
                outputStream.write(bytes);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void endRecord() {
        if (outputStream != null) {
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            outputStream = null;
        }
    }


    /**
     * @param file
     * @param recorderSamplerate 16000
     * @throws IOException
     */
//    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static void play(File file, int recorderSamplerate) throws IOException {

        if (!file.exists()) {
            Log.i("test", "play: 不存在");
            return;
        }
        FileInputStream inputStream = new FileInputStream(file);

//        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//        new AudioTrack.Builder().
        int bufferSize = AudioTrack.getMinBufferSize(recorderSamplerate,
                AudioFormat.CHANNEL_OUT_MONO, AudioFormat.ENCODING_PCM_16BIT);
        Log.i("test", "play: size " + bufferSize);//1296

        AudioTrack audioTrack = new AudioTrack(
                AudioManager.STREAM_MUSIC,
                recorderSamplerate,
                AudioFormat.CHANNEL_OUT_MONO,
                AudioFormat.ENCODING_PCM_16BIT,
                bufferSize,
                AudioTrack.MODE_STREAM
        );

        //java.lang.IllegalStateException: play() called on uninitialized AudioTrack.
        // 如果是 MODE_STATIC模式，必须先调用write将数据全部写入，才能调用play播放
        audioTrack.play();

        byte[] bytes = new byte[2048];
        int length = 0;
        while ((length = inputStream.read(bytes)) != -1) {
//            Log.i("test", "play: 写入" + length);
            audioTrack.write(bytes, 0, length);//写入到播放流中，系统自动将pcm数据转化为数字信号，然后是模拟信号
        }
        audioTrack.stop();
        audioTrack.release();
        Log.i("test", "play: 播放结束");
        audioTrack = null;

        inputStream.close();
    }
}
