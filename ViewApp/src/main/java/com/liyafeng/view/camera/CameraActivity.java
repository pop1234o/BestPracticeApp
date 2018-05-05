package com.liyafeng.view.camera;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.liyafeng.view.R;

import java.io.IOException;

public class CameraActivity extends AppCompatActivity {

    private Camera camera;
    private ImageView iv_display;
    private CameraActivity activity;

    /**
     * Camera （api21后弃用）
     * https://developer.android.google.cn/reference/android/hardware/Camera
     * <p>
     * https://www.jianshu.com/p/7dd2191b4537(使用介绍)
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        activity = this;
        iv_display = (ImageView) findViewById(R.id.iv_display);

        findViewById(R.id.button11).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                camera.takePicture(new Camera.ShutterCallback() {
                    @Override
                    public void onShutter() {
                        Log.i("test", "拍照瞬间调用");
                    }
                }, new Camera.PictureCallback() {
                    @Override
                    public void onPictureTaken(byte[] data, Camera camera) {

                        if (data == null) {
                            Log.i("test", "raw数据为null");
                        } else {
                            Log.i("test", "raw" + data.length);
                            Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
                            iv_display.setImageBitmap(bitmap);
                        }

                    }
                }, new Camera.PictureCallback() {
                    @Override
                    public void onPictureTaken(byte[] data, Camera camera) {

                        if (data == null) {
                            Log.i("test", "jpeg数据为null");
                        } else {
                            Log.i("test", "jpeg" + data.length);
                            Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
                            iv_display.setImageBitmap(bitmap);
                        }
                    }
                });
            }
        });


        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int permission = ContextCompat.checkSelfPermission(activity, Manifest.permission.CAMERA);

                if (permission != PackageManager.PERMISSION_GRANTED) {
                    //
                    if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.CAMERA)) {
                        Toast.makeText(activity, "您之前拒绝了", Toast.LENGTH_SHORT).show();
                        ActivityCompat.requestPermissions(activity,
                                new String[]{Manifest.permission.CAMERA},
                                1);
                    } else {
                        ActivityCompat.requestPermissions(activity,
                                new String[]{Manifest.permission.CAMERA},
                                1);
                    }
                } else {
                    Log.i("test", "有权限了");
                    open();
                }

            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.i("test", "授权成功");
                    open();

                } else {
                    Toast.makeText(this, "拒绝了", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    private void open() {
        int numberOfCameras = Camera.getNumberOfCameras();
        Log.i("test", "数量" + numberOfCameras);
        if (numberOfCameras >= 2) {

            camera = Camera.open(0);//0后置，1前置
//            camera = Camera.open(1);//0后置，1前置
        } else {
            camera =   Camera.open(0);
        }
        Camera.Parameters parameters = camera.getParameters();
        parameters.setGpsTimestamp(System.currentTimeMillis());
//        parameters.setPictureSize(800,800);
        camera.setParameters(parameters);

        camera.setDisplayOrientation(90);//90垂直 180水平


        SurfaceView sv_view = (SurfaceView) findViewById(R.id.sv_view);

        SurfaceHolder holder = sv_view.getHolder();

        try {
            camera.setPreviewDisplay(holder);
        } catch (IOException e) {
            e.printStackTrace();
        }


        camera.startPreview();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (camera != null) {
            camera.release();
        }
    }
}
