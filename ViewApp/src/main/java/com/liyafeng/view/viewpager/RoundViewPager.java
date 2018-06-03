package com.liyafeng.view.viewpager;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;

public class RoundViewPager extends ViewPager{
    public RoundViewPager(Context context) {
        super(context);
    }

    public RoundViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //clipPath实现
     /*   Path path = new Path();
        Rect clipBounds = canvas.getClipBounds();
        RectF rectF = new RectF(clipBounds);
        path.addRoundRect(rectF,100,100, Path.Direction.CW);
        canvas.clipPath(path);*/


        //Xfermode实现 原理就是画一个圆形的bitmap，然后再用xfermode模式来画原来的bitmap，取两者交集

//        final Paint paint = new Paint();
//        paint.setAntiAlias(true);
//        Bitmap target = Bitmap.createBitmap(canvas.getWidth(), canvas.getHeight(), Bitmap.Config.ARGB_8888);
//        /**
//         * 产生一个同样大小的画布
//         */
//        Canvas canvas1 = new Canvas(target);
//        /**
//         * 首先绘制圆形
//         */
////        canvas1.drawCircle(min / 2, min / 2, min / 2, paint);
//
//        //绘制圆角矩形  https://blog.csdn.net/u010041075/article/details/72628269
//        Rect clipBounds = canvas.getClipBounds();
//        RectF rectF = new RectF(clipBounds);
//
//        paint.setColor(Color.GREEN);
//        canvas1.drawRoundRect(rectF,50,50,paint);
//        /**
//         * 使用SRC_IN
//         */
//        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
//        /**
//         * 绘制图片
//         */
//        canvas.drawBitmap(target, 0, 0, paint);




        //shader实现 https://livesun.github.io/2017/06/19/cunstom_drawable/
        //


        //这个bitmap是我们原图的bitmap
//        BitmapShader shader=new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
//        Paint paint = new Paint();
//        paint.setAntiAlias(true);
//        paint.setShader(shader);

        //我们画一个圆形，用带shader的画笔，这样我们画的形状就会被paint中的shader 填充了，而且填充模式也可以指定
//        canvas.drawRoundRect(rectF,radisu,radisu,paint);

    }
}
