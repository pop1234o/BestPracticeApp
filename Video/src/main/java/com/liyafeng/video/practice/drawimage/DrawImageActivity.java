package com.liyafeng.video.practice.drawimage;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.liyafeng.video.R;

public class DrawImageActivity extends Activity {

    /**
     * 1. 在 Android 平台绘制一张图片，使用至少 3 种不同的 API，ImageView，SurfaceView，自定义 View
     * https://developer.android.google.cn/reference/android/view/SurfaceView
     *
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw_image);
        final SimpleSurfaceView view =  (SimpleSurfaceView)findViewById(R.id.surfaceview);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view.stop();
            }
        });
    }


}
