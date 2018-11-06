package com.liyafeng.video.practice.drawimage;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.liyafeng.video.R;

public class SimpleSurfaceView extends SurfaceView {

    public SimpleSurfaceView(Context context) {
        super(context);
        init();
    }


    public SimpleSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SimpleSurfaceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private boolean isDrawing;


    public void stop() {
        isDrawing = false;
    }

    private void init() {
        final Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        final SurfaceHolder holder = getHolder();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getWidth());
        final Rect rect_dst = new Rect(0, 0, bitmap.getWidth(), bitmap.getWidth());

        final Paint paint = new Paint();
        holder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder surfaceHolder) {
                isDrawing = true;
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (isDrawing) {
                            Log.i("test", "run: 绘制");
                            /*
                            * Start editing the pixels in the surface.  The returned Canvas can be used
                             * to draw into the surface's bitmap.  A null is returned if the surface has
                             * not been created or otherwise cannot be edited.  You will usually need
                             * to implement {@link Callback#surfaceCreated Callback.surfaceCreated}
                             * to find out when the Surface is available for use.
                             * 获得一个画布，可以编辑这个surface的bitmap
                             * 画布本身的作用就是编辑bitmap
                            * */
                            Canvas canvas = holder.lockCanvas();
                            canvas.drawBitmap(bitmap, rect, rect_dst, paint);


                            /*
                            * Finish editing pixels in the surface.  After this call, the surface's
                             * current pixels will be shown on the screen, but its content is lost,
                             * in particular there is no guarantee that the content of the Surface
                             * will remain unchanged when lockCanvas() is called again.
                             * 完成编辑，显示在屏幕上
                            *
                            * */
                            holder.unlockCanvasAndPost(canvas);

                        }
                    }
                }).start();
            }

            @Override
            public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

                isDrawing = false;
            }
        });


    }


}
