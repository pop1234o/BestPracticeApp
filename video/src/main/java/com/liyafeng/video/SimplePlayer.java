package com.liyafeng.video;

import android.content.Context;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;

import static com.liyafeng.video.MainActivity.TAG;


/**
 * 读取文件
 * <p>
 * 解复用 （可以根据位数自己解复用）
 * <p>
 * 解码
 * <p>
 * 数据渲染 render
 * <p>
 * Created by liyafeng on 23/10/2017.
 *
 * 一个byte 是8位 0000 0000
 *
 *
 *
 */

public class SimplePlayer {


    public SimplePlayer() {


    }

    public void read(Context context) {

        InputStream open = null;
        try {
            open = context.getAssets().open("test.mp4");
            byte[] bytes = new byte[1024];
            int length = 0;
            int sum = 0;
            while ((length = open.read(bytes)) != -1) {
                sum += length;
                StringBuilder builder = new StringBuilder();
                for (int i = 0; i < length; i++) {
                    builder.append(toHexString(bytes[i]));
                }
//                String s = new String(bytes, 0, length);
//                Log.i(TAG, s);
                Log.i(TAG, builder.toString());
            }
            Log.i(TAG, "read: " + sum / 1024);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (open != null) {
                try {
                    open.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static String toHexString(byte b) {
        char[] digits =  DIGITS;
        char[] buf = new char[2]; // We always want two digits.
        buf[0] = digits[(b >> 4) & 0xf];
        buf[1] = digits[b & 0xf];
        return new String(buf,0, 2);
    }
    private static final char[] DIGITS = {
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
            'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't',
            'u', 'v', 'w', 'x', 'y', 'z'
    };
}
