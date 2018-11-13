package com.liyafeng.video.practice.f_opengl_es;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.liyafeng.video.R;

public class OpenGLES20Activity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_gles20);

        MyGLSurfaceView myGLSurfaceView = new MyGLSurfaceView(this);
        ViewGroup ll_content = (ViewGroup) findViewById(R.id.ll_content);
        ll_content.addView(myGLSurfaceView);
    }
}
