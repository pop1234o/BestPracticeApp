package com.liyafeng.video.practice.c_video_record;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.ImageFormat;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.liyafeng.video.R;

import java.io.IOException;
import java.util.List;

public class Camera1_TextureActivity extends AppCompatActivity {

    private static final int REQ_CAMERA = 0;
    private SurfaceTexture surfaceTexture;
    private Camera camera;
    private Handler handler;
    private YuvUtils yuvtool;
    private ImageView iv_img;
    private boolean isShot;
    private TextureView texture_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_texture);
        texture_view = (TextureView) findViewById(R.id.texture_view);
        texture_view.setSurfaceTextureListener(new TextureView.SurfaceTextureListener() {
            @Override
            public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
                surfaceTexture = surface;
                checkPermission();
            }

            @Override
            public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {

            }

            @Override
            public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
                if (camera != null) {
                    camera.stopPreview();
                    camera.release();
                }
                return true;
            }

            @Override
            public void onSurfaceTextureUpdated(SurfaceTexture surface) {

            }
        });


        iv_img = (ImageView) findViewById(R.id.iv_img);
        iv_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isShot = true;
            }
        });

        initBackgroundHandler();

        yuvtool = new YuvUtils();

    }


    private void detect(byte[] data) {

        getBitmap(data);
        byte[] data270rotate = yuvtool.nv21RotateClockwise(data, 640, 480, 270);  // rotation=270
        byte[] data_det_input = yuvtool.nv21MirrorLeftRight(data270rotate, 480, 640);

//        engine.detect(data_det_input, 480, 640);
        //这里的preview数据是 -》 这样的，所以要转270度（时钟放向） 然后镜像，才是镜面显示
    }

    private void getBitmap(final byte[] data) {
        if (!isShot) {
            return;
        }

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                byte[] bytes = yuvtool.nv21ToRGB888(data, 640, 480);
                Bitmap bitmap = yuvtool.byteRGBToBitmap(bytes, 640, 480);

                iv_img.setImageBitmap(bitmap);
            }
        });


        isShot = false;
    }

    private void initBackgroundHandler() {
        HandlerThread t = new HandlerThread("engine");
        t.start();
        handler = new Handler(t.getLooper()) {
            @Override
            public void handleMessage(Message msg) {

                byte[] data = (byte[]) msg.obj;

                detect(data);
                camera.addCallbackBuffer(data);
            }
        };
    }


    private void startPreview() {
        if (checkHardware()) {
            Camera.getNumberOfCameras();
            camera = open();
            if (camera == null) {
                return;
            }
            /*
             * 不旋转的摄像头数据是相机显示向左旋转90度的图像，
             * 所以我们在这需要向右旋转90度
             * setPreviewSize是设置的旋转前的大小，旋转后宽高就倒过来了
             *
             * 《- 这样的宽高
             * 旋转90度  向上的箭头
             *
             *
             * */
            camera.setDisplayOrientation(90);
            Camera.Parameters parameters = camera.getParameters();

            List<Camera.Size> supportedPreviewSizes = parameters.getSupportedPreviewSizes();
//            Camera.Size size = supportedPreviewSizes.get(33);

            //如果textureview的大小比这个比例 大 那么就会拉伸,或者压缩
            //如果比例一样，会模糊，或者缩小
            //首先摄像头获取到的图片是固定大小，那么设置previewsize比例 得看这个比例和摄像头获取的比例大小，
            //所以会进行剪裁，
            //剪裁之后的图片会显示到textureview上，显示的时候根据控件的大小会进行拉伸。
            parameters.setPreviewSize(640, 480);
            int pixel_format = ImageFormat.NV21;
            parameters.setPreviewFormat(pixel_format);
            parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_AUTO);
            camera.setParameters(parameters);

            try {
                camera.setPreviewTexture(surfaceTexture);
            } catch (IOException e) {
                e.printStackTrace();
            }

            camera.startPreview();


            //这里不推荐使用setPreviewCallback，因为没有复用byte数组，导致内存占用高，频繁gc，导致handler的消息延时执行
            //这样我们及时取出的帧可能就是很早之前的了
            camera.setPreviewCallbackWithBuffer(new Camera.PreviewCallback() {
                @Override
                public void onPreviewFrame(byte[] data, Camera camera) {


                    Message msg = Message.obtain();
                    msg.obj = data;
                    handler.sendMessage(msg);

//                    detect(data);
                }
            });

            int sizeInBits = ImageFormat.getBitsPerPixel(pixel_format) * 640 * 480;
            int size = (int) Math.ceil((double) sizeInBits / 8.0D);
            camera.addCallbackBuffer(new byte[size]);
            camera.addCallbackBuffer(new byte[size]);
        }
    }


    public static Camera open() {
        int numberOfCameras = Camera.getNumberOfCameras();
        Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
        for (int i = 0; i < numberOfCameras; i++) {
            Camera.getCameraInfo(i, cameraInfo);
            if (cameraInfo.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
                return Camera.open(i);
            }
        }
        return null;
    }

    private boolean checkHardware() {
        return getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA);
    }


    private void checkPermission() {
        int flag = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA);
        if (flag != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, REQ_CAMERA);
        } else {
            startPreview();
        }

    }



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQ_CAMERA:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    startPreview();
                }
                break;
        }
    }

}
