package com.liyafeng.video.practice.c_video_record;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.ImageFormat;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.media.CamcorderProfile;
import android.media.MediaRecorder;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.util.Log;
import android.util.Size;
import android.view.TextureView;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.liyafeng.video.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import static com.liyafeng.video.MainActivity.TAG;

public class CameraActivity extends Activity {

    private static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 1;
    private FrameLayout sv_content;
    private Camera cameraInstance;
    private MediaRecorder mMediaRecorder;
    private CameraPreview cameraPreview;
    private Button button_record_video;
    private TextureView tv_content;
    private SurfaceTexture surfaceTexture;

    /**
     * camera api能获取图片和视频，5.0以后推荐camera2
     * 1.需要权限
     * 2.Android9.0 以后，如果app在后台就不能使用camera
     * <p>
     * =================将你的图片或者视频保存，让其他app可见============
     * https://developer.android.google.cn/guide/topics/media/camera#saving-media
     * <p>
     * ===================使用camera步骤=================
     * https://developer.android.google.cn/guide/topics/media/camera#custom-camera
     * ===================拍摄照片====================
     * （采坑）https://zhuanlan.zhihu.com/p/20559606
     *
     * ====================录制视频（带音频）=========
     * MediaRecorder录制好的是自带给你编码了，支持3gp和mp4
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
                CameraActivity.this.surfaceTexture = surface;
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
            @Override
            public void onClick(View v) {

                request();
            }
        });

        findViewById(R.id.button4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takePhoto();
            }
        });

        button_record_video = (Button) findViewById(R.id.button_record_video);
        button_record_video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    record();
            }
        });

    }

    boolean isRecording;
    private void record() {
        if (isRecording) {
            // stop recording and release camera
            mMediaRecorder.stop();  // stop the recording
            releaseMediaRecorder(); // release the MediaRecorder object
            cameraInstance.lock();         // take camera access back from MediaRecorder

            // inform the user that recording has stopped
//            setCaptureButtonText("Capture");
            button_record_video.setText("录制");
            isRecording = false;
        } else {
            // initialize video camera
            if (prepareVideoRecorder()) {
                // Camera is available and unlocked, MediaRecorder is prepared,
                // now you can startCamera recording
                mMediaRecorder.start();

                // inform the user that recording has started
//                setCaptureButtonText("Stop");
                button_record_video.setText("停止");
                isRecording = true;
            } else {
                // prepare didn't work, release the camera
                releaseMediaRecorder();
                // inform user
            }
        }
    }

    private boolean prepareVideoRecorder() {
        mMediaRecorder = new MediaRecorder();

        // Step 1: Unlock and set camera to MediaRecorder
        cameraInstance.unlock();
        mMediaRecorder.setCamera(cameraInstance);

        // Step 2: Set sources
        mMediaRecorder.setAudioSource(MediaRecorder.AudioSource.CAMCORDER);
        mMediaRecorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);

        // Step 3: Set a CamcorderProfile (requires API Level 8 or higher)
        mMediaRecorder.setProfile(CamcorderProfile.get(CamcorderProfile.QUALITY_HIGH));


        //设置封装格式 默认是MP4
//        mMediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.DEFAULT);
//        //音频编码
//        mMediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);
//        //图像编码
//        mMediaRecorder.setVideoEncoder(MediaRecorder.VideoEncoder.H264);
//        //声道
//        mMediaRecorder.setAudioChannels(1);





        // Step 4: Set output file
        File file = new File(getExternalFilesDir(null), System.currentTimeMillis() + ".mp4");
        mMediaRecorder.setOutputFile(file.getAbsolutePath());

//        mMediaRecorder.setOutputFormat();
        // Step 5: Set the preview output
        mMediaRecorder.setPreviewDisplay(cameraPreview.getHolder().getSurface());
//        mMediaRecorder.setPreviewDisplay(new Surface(surfaceTexture));

        // Step 6: Prepare configured MediaRecorder
        try {
            mMediaRecorder.prepare();
        } catch (IllegalStateException e) {
            Log.d(TAG, "IllegalStateException preparing MediaRecorder: " + e.getMessage());
            releaseMediaRecorder();
            return false;
        } catch (IOException e) {
            Log.d(TAG, "IOException preparing MediaRecorder: " + e.getMessage());
            releaseMediaRecorder();
            return false;
        }
        return true;

    }


    private void takePhoto() {
        if (cameraInstance != null) {

            cameraInstance.takePicture(null, null, new Camera.PictureCallback() {
                @Override
                public void onPictureTaken(byte[] data, Camera camera) {
                    File externalFilesDir = CameraActivity.this.getExternalFilesDir(null);
                    File file = new File(externalFilesDir, System.currentTimeMillis() + ".jpg");
                    FileOutputStream outputStream = null;
                    try {
                        outputStream = new FileOutputStream(file);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    try {
                        if (outputStream != null) {
                            outputStream.write(data);
                            outputStream.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    private void request() {
        int permission = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA);
        if (permission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CAMERA},
                    MY_PERMISSIONS_REQUEST_READ_CONTACTS);
        } else {
            startCamera();
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
                    startCamera();
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
    private void startCamera() {

        if (checkCameraHardware(this)) {
            //摄像头数量
            int numberOfCameras = Camera.getNumberOfCameras();
            cameraInstance = getCameraInstance();
            if (cameraInstance != null) {
                Camera.Parameters parameters = cameraInstance.getParameters();
                cameraInstance.setDisplayOrientation(90);//设置显示角度
                parameters.setRotation(90);//设置照出的图片角度

                //预览的数据，只能是这种数据
                parameters.setPreviewFormat(ImageFormat.NV21);

                /*
                * 这里的照片宽高只能选择系统支持的
                * */
                List<Camera.Size> supportedPictureSizes = cameraInstance.getParameters().getSupportedPictureSizes();
                Camera.Size size = supportedPictureSizes.get(33);//有很多像素选择  ,120*160


//                Camera.Size  sizeOut=null;
//                for (Camera.Size  size : supportedPictureSizes) {
//                    if (size.width>=this.getResources().) {
//                        break;
//                    }
//                    sizeOut=size;
//                }

                //这个size好像必须小于
                parameters.setPictureSize(size.width,size.height);
                parameters.setPreviewSize(size.width,size.height);

                parameters.setPictureFormat(ImageFormat.JPEG);

                /*
                * 设置自动对焦，需要适配（可以实现触摸对焦，然后还有根据传感器判断手机移动后对焦）
                * https://blog.csdn.net/huweigoodboy/article/details/51378751
                * */
                parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE);




                cameraInstance.setParameters(parameters);


                /*
                * 处理图像的每一帧
                *
                * */
//              //!!!!!!!!!!妈了b，这个方法只是通知他回调，所以这个是必须有preview的时候才能加载，这个时候还没有preview
            /*    cameraInstance.setPreviewCallback(new Camera.PreviewCallback() {
                    @Override
                    public void onPreviewFrame(byte[] data, Camera camera) {
                        //这里面的Bytes的数据就是NV21格式的数据。(难道是5.0以上的系统没有回调了？？)
                        Log.i(TAG, "onPreviewFrame: "+data.length);
                        //这里的length
                        //通过android.hardware.Camera 采集的视频源数据为nv21格式的数据，
                        属于yuv420sp格式的一种，所以数据的总长度为：width*height*3/2

                        所以160*120*1.5 =28800 个字节
                    }
                });*/


                cameraPreview = new CameraPreview(this, cameraInstance);
                sv_content.addView(cameraPreview);
            }
        }
    }

    /**
     * Check if this device has a camera
     */
    private boolean checkCameraHardware(Context context) {
        if (context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
            // this device has a camera
            return true;
        } else {
            // no camera on this device
            return false;
        }
    }

    /**
     * A safe way to get an instance of the Camera object.
     */
    public static Camera getCameraInstance() {
        Camera c = null;
        try {
            c = Camera.open(); // attempt to get a Camera instance
        } catch (Exception e) {
            // Camera is not available (in use or does not exist)
        }
        return c; // returns null if camera is unavailable
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(cameraInstance!=null){
            cameraInstance.stopPreview();
            cameraInstance.release();
        }
    }

    private void releaseMediaRecorder(){
        if (mMediaRecorder != null) {
            mMediaRecorder.reset();   // clear recorder configuration
            mMediaRecorder.release(); // release the recorder object
            mMediaRecorder = null;
            cameraInstance.lock();           // lock camera for later use
        }
    }

}
