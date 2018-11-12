package com.liyafeng.video.practice.b_audio_record;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.liyafeng.video.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class AudioRecordActivity extends Activity {

    private static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 1;
    private AudioRecord recorder;
    private boolean isRecording;
    private Thread recordingThread;
    private File file;

    /**
     * 2. 在 Android 平台使用 AudioRecord 和 AudioTrack API 完成音频 PCM 数据的采集和播放，
     * 并实现读写音频 wav 文件
     * =================================
     * http://stackmirror.caup.cn/page/4brq8fjaqlu (例子)
     * <p>
     * ====================================
     * PCM16 模式，一个采样点的字节是2个字节
     * 所以我们从流中读取的数据，每两个字节代表一个音频数据，
     * 如果是双声道录制模式，那么就是左声道两个字节，右声道两个字节这样排列下来
     *
     * 所以一个frame的大小就是一个采样点的字节数*声道数
     * ==========================
     * 采样率（率指的是频率 hz ，赫兹是频率的基本单位 代表，周期/1秒   比如），
     * 代表1秒钟有多少个采样点
     * <p>
     * <p>
     * 一个采样数据可以是16位，也可以是24或者其他，这就是采样精度。
     * <p>
     * 码率（比特率）= 采样率*每个采样的精度*声道数
     * 其实就是1秒钟的声音有多少位 kbps  千bit per 秒
     * <p>
     * 44.1KHz的，因为这是音乐CD的标准
     * CD的码率就是44100*16*2=1411kbps=1.4Mbps
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_record);
        Button viewById = (Button) findViewById(R.id.button2);
        String child = "录制.pcm";
        file = new File(getExternalCacheDir(), child);
        viewById.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isRecording) {

                    checkPermission();
                } else {
                    stopRecord();
                }
            }
        });
        findViewById(R.id.button3).setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                try {
                    AudioPlayPCM.play(file, RECORDER_SAMPLERATE);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void stopRecord() {
        if (recorder != null) {
            isRecording = false;
            recorder.stop();
            recorder.release();
            recorder = null;
            recordingThread = null;
        }
    }

    private static final int RECORDER_SAMPLERATE = 8000;//采样率
    private static final int RECORDER_CHANNELS = AudioFormat.CHANNEL_IN_MONO;//单声道
    private static final int RECORDER_AUDIO_ENCODING = AudioFormat.ENCODING_PCM_16BIT;//编码格式，录制返回数据的格式 ENCODING_PCM_8BIT
    int BufferElements2Rec = 1024; // want to play 2048 (2K) since 2 bytes we use only 1024
    //这里的Elements指的是采样点的个数
    int BytesPerElement = 2; // 2 bytes in 16bit format

    //这里我们是返回16位数据，所以缓冲区大小是2k字节


    private void start() {
        //最小的buffersize，如果再小AudioRecord就会创建失败
        int bufferSize = AudioRecord.getMinBufferSize(RECORDER_SAMPLERATE,
                RECORDER_CHANNELS, RECORDER_AUDIO_ENCODING);

        recorder = new AudioRecord(
                MediaRecorder.AudioSource.MIC,
                RECORDER_SAMPLERATE,//采样率，每秒多少个采样点（frame）
                RECORDER_CHANNELS,//声道
                RECORDER_AUDIO_ENCODING,//编码规则
                BufferElements2Rec * BytesPerElement//读取的缓冲区大小
        );

        //注意，这里需要权限，否则崩溃
        recorder.startRecording();// java.lang.IllegalStateException: startRecording() called on an uninitialized AudioRecord.
        isRecording = true;

        recordingThread = new Thread(new Runnable() {
            public void run() {
                try {
                    writeAudioDataToFile();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }, "AudioRecorder Thread");
        recordingThread.start();
    }

    /**
     * 6.0权限检查
     */
    private void checkPermission() {
        int permission = ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO);
        if (permission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.RECORD_AUDIO},
                    MY_PERMISSIONS_REQUEST_READ_CONTACTS);
        } else {
            start();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_CONTACTS: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.i("test", "授权了！！！");
                    start();
                } else {


                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

    private void writeAudioDataToFile() throws IOException {

        if (file.exists()) {
            file.delete();
        }

        short[] sData = new short[BufferElements2Rec];
        FileOutputStream fileOutputStream = new FileOutputStream(file);

        while (isRecording) {
            int read = recorder.read(sData, 0, BufferElements2Rec);
            Log.i("test", "writeAudioDataToFile: 录制中 " + read);
            byte bData[] = short2byte(sData);
            fileOutputStream.write(bData, 0, BufferElements2Rec * BytesPerElement);
        }

        fileOutputStream.close();
    }

    /**
     * byte占用一个字节（8bit位）的空间
     * short是两个字节 16位
     *
     * @param sData
     * @return
     */
    private byte[] short2byte(short[] sData) {
        int length = sData.length;
        byte[] bytes = new byte[length * 2];
        for (int i = 0; i < length; i++) {
            bytes[i * 2] = (byte) (sData[i] & 0x00ff);//16进制4bit表示一个字母 保留低8位
            bytes[i * 2 + 1] = (byte) (sData[i] >> 8);//取高8位
            sData[i] = 0;
        }
        return bytes;
    }
}
