package com.liyafeng.video.practice.c_video_record;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.TextureView;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.liyafeng.video.R;

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class Camera2Activity extends Activity {

    private static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 1;
    private FrameLayout sv_content;
    private CameraPreview cameraPreview;
    private Button button_record_video;
    private TextureView tv_content;
    private SurfaceTexture surfaceTexture;

    /**
     * camera 是在native层的c/s架构，而camera2是移动到java层，使用aidl来获取数据
     * https://medium.com/google-developers/detecting-camera-features-with-camera2-61675bb7d1bf (需要翻墙)
     * https://github.com/googlesamples/android-Camera2Basic（一个示例）
     *
     * https://www.jianshu.com/p/73fed068a795
     *
     * (camera2获取每一帧的回调)
     * https://blog.csdn.net/Lyman_Ye/article/details/78897819
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        sv_content = (FrameLayout) findViewById(R.id.sv_content);
        tv_content = (TextureView) findViewById(R.id.tv_content);

        tv_content.setSurfaceTextureListener(new TextureView.SurfaceTextureListener() {

            @Override
            public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
                Camera2Activity.this.surfaceTexture = surface;
            }

            @Override
            public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {

            }

            @Override
            public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
                return false;
            }

            @Override
            public void onSurfaceTextureUpdated(SurfaceTexture surface) {

            }
        });
        findViewById(R.id.btn_record).setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {

                request();
            }
        });

        findViewById(R.id.button4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                takePhoto();
            }
        });

        button_record_video = (Button) findViewById(R.id.button_record_video);
        button_record_video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                    record();
            }
        });

    }


    private void request() {
        int permission = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA);
        if (permission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CAMERA},
                    MY_PERMISSIONS_REQUEST_READ_CONTACTS);
        } else {
            try {
                start();
            } catch (CameraAccessException e) {
                e.printStackTrace();
            }
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
                    try {
                        start();
                    } catch (CameraAccessException e) {
                        e.printStackTrace();
                    }
                } else {


                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

    /**
     * 开启相机
     */
    private void start() throws CameraAccessException {
        CameraManager manager = (CameraManager) this.getSystemService(CAMERA_SERVICE);
        try {
            for (String cameraId : manager.getCameraIdList()) {//获取列表
                //获取这个摄像头的特性

                CameraCharacteristics chars = manager.getCameraCharacteristics(cameraId);
            }
            // Do something with the characteristics
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        manager.openCamera(manager.getCameraIdList()[0], new CameraDevice.StateCallback() {
            private CameraDevice mCameraDevice;

            @Override
            public void onOpened(@NonNull CameraDevice camera) {

                mCameraDevice = camera;
                createCameraPreviewSession();
            }

            @Override
            public void onDisconnected(@NonNull CameraDevice camera) {
                camera.close();
            }

            @Override
            public void onError(@NonNull CameraDevice camera, int error) {

            }
        }, null);

        }

    private void createCameraPreviewSession() {

    }


    @Override
        protected void onDestroy () {
            super.onDestroy();
        }


    }
