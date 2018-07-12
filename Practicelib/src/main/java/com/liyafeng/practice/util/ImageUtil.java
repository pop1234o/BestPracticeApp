package com.liyafeng.practice.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ImageUtil {

    /**
     * 根据一个网络连接(String)获取bitmap图像
     *
     * @param imageUri
     * @return
     * @throws MalformedURLException
     */
    public static void getbitmap(Context context,String imageUri, OnDownListener onDownListener) {
        String child = Base64.encodeToString(imageUri.getBytes(), Base64.DEFAULT);
//        LogUtil.i("test", "getbitmap:" + imageUri);
//        LogUtil.i("test", "getbitmap:" + child);
        File file = new File(context.getCacheDir(), child);
        if (file.exists()) {
            Bitmap bitmap = null;
            try {
                bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (bitmap != null) {
                onDownListener.onSuccess(bitmap);
                return;
            }
        }

        // 显示网络上的图片
        Bitmap bitmap = null;
        try {
            URL myFileUrl = new URL(imageUri);
            HttpURLConnection conn = (HttpURLConnection) myFileUrl
                    .openConnection();
            conn.setDoInput(true);
            conn.connect();
            InputStream is = conn.getInputStream();
            bitmap = BitmapFactory.decodeStream(is);
            is.close();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, new FileOutputStream(file));
            onDownListener.onSuccess(bitmap);
//            LogUtil.i("test", "image download finished." + imageUri);
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
            onDownListener.onError(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
//            LogUtil.i("test", "getbitmap bmp fail---");
            onDownListener.onError(e.getMessage());
        }
    }

    public interface OnDownListener {
        void onSuccess(Bitmap bitmap);

        void onError(String msg);
    }
}
