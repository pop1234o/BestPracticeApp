package com.liyafeng.video.practice.c_video_record;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.liyafeng.video.R;

public class Camera2_1Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera2_1);
        if (null == savedInstanceState) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, Camera2BasicFragment.newInstance())
                    .commit();
        }
    }
}
