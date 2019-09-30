package com.liyafeng.practice.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.io.File;
import java.io.FileOutputStream;

public class ShareUtil {

    public static String saveBitmap(Context context, Bitmap bitmap) {
        String img = "share" + System.currentTimeMillis() + ".jpg";
        File file = new File(context.getExternalCacheDir(), img);
        try (FileOutputStream outputStream = new FileOutputStream(file)) {
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
        } catch (Exception e) {
            Log.e("Exception", e.getMessage());
        }
        return file.exists() ? file.getAbsolutePath() : "";

    }

    /**
     * 找出真正的图片内容
     *
     * @param share_image_content
     * @param mImageView
     */
    public static Bitmap getImageContent(View share_image_content, ImageView mImageView) {
        int contentHeight = share_image_content.getHeight();
        int contentWidth = share_image_content.getWidth();
        Bitmap bitmap = Bitmap.createBitmap(contentWidth, contentHeight, Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(bitmap);
        share_image_content.draw(canvas);
        try {
            Rect bounds = mImageView.getDrawable().getBounds();
            int dw = bounds.width();
            int dh = bounds.height();
            Matrix imageMatrix = mImageView.getImageMatrix();
            float[] values = new float[10];
            imageMatrix.getValues(values);

            float sx = values[0];
            float sy = values[4];

            //真正图片的显示宽高
            int imgWidth = (int) (dw * sx);
            int imgHeight = (int) (dh * sy);


            //需要去掉上下白边
            if (imgHeight < contentHeight || imgWidth < contentWidth) {
                //                Bitmap.createBitmap(imgWidth,imgHeight)
                float marginY = (contentHeight - imgHeight) / 2.0f;
                float marginX = (contentWidth - imgWidth) / 2.0f;

                Bitmap newBitmap = Bitmap.createBitmap(bitmap, (int) marginX, (int) marginY, imgWidth, imgHeight);

                bitmap = newBitmap;
            }
        } catch (Exception e) {
            Log.e("Exception", e.getMessage());
        }

        return bitmap;
    }

//    public static void canShare(Context context, View btnShare) {
//        boolean insall = QQShareApi.getInstance(context).isInsall(context);
//        boolean installWeChat = WxShareApi.getInstance(context).isInstallWeChat();
//        if (!insall && !installWeChat) {
//            btnShare.setVisibility(View.GONE);
//        } else {
//            btnShare.setVisibility(View.VISIBLE);
//        }
//    }

    public static Bitmap viewToBitmap(View view) {
        try {
            int width = view.getWidth();
            int height = view.getHeight();
            Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
            Canvas canvas = new Canvas(bitmap);
            view.draw(canvas);
            return bitmap;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
