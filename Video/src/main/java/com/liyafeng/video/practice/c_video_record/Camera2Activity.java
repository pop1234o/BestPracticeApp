package com.liyafeng.video.practice.c_video_record;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.ImageFormat;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.media.Image;
import android.media.ImageReader;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.util.Size;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.liyafeng.video.R;

import java.nio.ByteBuffer;
import java.util.Arrays;

import static android.content.ContentValues.TAG;

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class Camera2Activity extends Activity {

    private static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 1;
    private FrameLayout sv_content;
    private CameraPreview cameraPreview;
    private Button button_record_video;
    private TextureView tv_content;
    private SurfaceTexture surfaceTexture;
    private Size mPreviewSize;
    private Handler handler;
    private CaptureRequest.Builder mPreviewBuilder;

    /**
     * camera 是在native层的c/s架构，而camera2是移动到java层，使用aidl来获取数据
     * https://medium.com/google-developers/detecting-camera-features-with-camera2-61675bb7d1bf (需要翻墙)
     * https://github.com/googlesamples/android-Camera2Basic（一个示例）
     * <p>
     * https://www.jianshu.com/p/73fed068a795
     * <p>
     * (camera2获取每一帧的回调)
     * https://blog.csdn.net/Lyman_Ye/article/details/78897819
     * <p>
     * =============架构==========
     * 主要用到的5个类：
     * <p>
     *  CameraManager：是通过getSystemService（Context.CAMERA_SERVICE）；拿到所有相机的管理者。
     * CameraDevice： 通过CameraManager返回的一个可用摄像头，原过时的Carema(android.hardware.Camera)对应，但是API很不一样了。
     * 每个CameraDevice自己会负责建立CameraCaptureSession以及建立CaptureRequest。
     * CameraCaptureSession：某个CameraDevice的拍照请求会话，很重要的一个类。
     * CaptureRequest：一个拍照等的请求对象。
     * CameraCharacteristics: 摄像头的特征，即这个类里封装着该手机摄像头支持的参数。
     * 它们之间的关系大概就是：首先通过getSystemService（Context.CAMERA_SERVICE）拿到CameraManger，然后通过CameraManger拿到某个摄像头的CameraCharacteristics，得到支持的参数啊CameraCharacteristics以及预览尺寸等信息。然后再通过CameraManger的openCamera拿到一个具体的摄像头对象CameraDevice。该方法有一个参数会要求传递一个回调，当摄像头打开时会该方法：onOpen，然后再在该方法了开启摄像头预览，绑定Surface。
     * ---------------------
     * 作者：Plumcot_hz
     * 来源：CSDN
     * 原文：https://blog.csdn.net/qq_21898059/article/details/50986290
     * 版权声明：本文为博主原创文章，转载请附上博文链接！
     * <p>
     * <p>
     * =================
     * 019-02-21 21:44:34.563 12487-12638/com.liyafeng.video W/SurfaceTextureRenderer: Surface abandoned, dropping frame.
     * android.hardware.camera2.legacy.LegacyExceptionUtils$BufferQueueAbandonedException
     * at android.hardware.camera2.legacy.LegacyCameraDevice.getSurfaceId(LegacyCameraDevice.java:708)
     * at android.hardware.camera2.legacy.LegacyCameraDevice.getSurfaceIds(LegacyCameraDevice.java:737)
     * at android.hardware.camera2.legacy.SurfaceTextureRenderer.drawIntoSurfaces(SurfaceTextureRenderer.java:734)
     * at android.hardware.camera2.legacy.GLThreadManager$1.handleMessage(GLThreadManager.java:105)
     * at android.os.Handler.dispatchMessage(Handler.java:98)
     * at android.os.Looper.loop(Looper.java:163)
     * at android.os.HandlerThread.run(HandlerThread.java:61)
     *
     *
     * ==================
     * Process: com.liyafeng.video, PID: 25041
     *     java.lang.IllegalStateException: maxImages (1) has already been acquired, call #close before acquiring more.
     *         at android.media.ImageReader.acquireNextImage(ImageReader.java:402)
     *         at com.liyafeng.video.practice.c_video_record.Camera2Activity$6.onImageAvailable(Camera2Activity.java:301)
     *         at android.media.ImageReader$ListenerHandler.handleMessage(ImageReader.java:687)
     *         at android.os.Handler.dispatchMessage(Handler.java:102)
     *         at android.os.Looper.loop(Looper.java:163)
     *         at android.os.HandlerThread.run(HandlerThread.java:61)
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
//        sv_content = (FrameLayout) findViewById(R.id.sv_content);
        tv_content = (TextureView) findViewById(R.id.tv_content);
        initHandler();
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

    private CameraDevice mCameraDevice;

    /**
     * 开启相机
     */
    private void start() throws CameraAccessException {
        CameraManager manager = (CameraManager) this.getSystemService(CAMERA_SERVICE);
        try {
            for (String cameraId : manager.getCameraIdList()) {//获取列表
                //获取这个摄像头的特性

                CameraCharacteristics chars = manager.getCameraCharacteristics(cameraId);
                // Do something with the characteristics
                //支持的STREAM CONFIGURATION
                StreamConfigurationMap map = chars.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP);
                //摄像头支持的预览Size数组
                mPreviewSize = map.getOutputSizes(SurfaceTexture.class)[0];
            }
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


            @Override
            public void onOpened(@NonNull CameraDevice camera) {

                mCameraDevice = camera;
                try {
                    startPreview(mCameraDevice);
                } catch (CameraAccessException e) {
                    e.printStackTrace();
                }
//                createCameraPreviewSession();
            }

            @Override
            public void onDisconnected(@NonNull CameraDevice camera) {
                camera.close();
            }

            @Override
            public void onError(@NonNull CameraDevice camera, int error) {

            }
        }, handler);

    }

    private void startPreview(CameraDevice camera) throws CameraAccessException {
        SurfaceTexture surfaceTexture = tv_content.getSurfaceTexture();
        //这里必须是手机支持的像素
        surfaceTexture.setDefaultBufferSize(mPreviewSize.getWidth(), mPreviewSize.getHeight());

        Surface surface = new Surface(surfaceTexture);

        try {
            // 设置捕获请求为预览，这里还有拍照啊，录像等
            mPreviewBuilder = camera.createCaptureRequest(CameraDevice.TEMPLATE_PREVIEW);
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }

//      就是在这里，通过这个set(key,value)方法，设置曝光啊，自动聚焦等参数！！ 如下举例：
//      mPreviewBuilder.set(CaptureRequest.CONTROL_AE_MODE,CaptureRequest.CONTROL_AE_MODE_ON_AUTO_FLASH);

        //设置自动对焦策略
        mPreviewBuilder.set(CaptureRequest.CONTROL_AF_MODE,
                CaptureRequest.CONTROL_AF_MODE_CONTINUOUS_PICTURE);

        //设置曝光算法
        mPreviewBuilder.set(CaptureRequest.CONTROL_AE_MODE,
                CaptureRequest.CONTROL_AE_MODE_OFF);

        ImageReader mImageReader = ImageReader.newInstance(tv_content.getWidth(), tv_content.getHeight(),
                ImageFormat.YUV_420_888/*此处还有很多格式，比如我所用到YUV等*/,
                1/*最大的图片数，mImageReader里能获取到图片数，但是实际中是2+1张图片，就是多一张*/);

        mImageReader.setOnImageAvailableListener(new ImageReader.OnImageAvailableListener() {
            /**
             *  当有一张图片可用时会回调此方法，但有一点一定要注意：
             *  一定要调用 reader.acquireNextImage()和close()方法，否则画面就会卡住！！！！！我被这个坑坑了好久！！！
             *    很多人可能写Demo就在这里打一个Log，结果卡住了，或者方法不能一直被回调。
             **/
            @Override
            public void onImageAvailable(ImageReader reader) {
//                Image img = reader.acquireNextImage();
                /**
                 *  因为Camera2并没有Camera1的Priview回调！！！所以该怎么能到预览图像的byte[]呢？就是在这里了！！！我找了好久的办法！！！
                 **/
            /*    ByteBuffer buffer = img.getPlanes()[0].getBuffer();
                byte[] data = new byte[buffer.remaining()];
                buffer.get(data);
                Log.i("test", "onImageAvailable: ====" +data.length);
                img.close();*/


                // 获取捕获的照片数据
//                Image image = reader.acquireNextImage();
//                Log.i(TAG, "image format: " + image.getFormat());
//                // 从image里获取三个plane
//                Image.Plane[] planes = image.getPlanes();
//
//                for (int i = 0; i < planes.length; i++) {
//                    ByteBuffer iBuffer = planes[i].getBuffer();
//                    int iSize = iBuffer.remaining();
////                    Log.i(TAG, "pixelStride  " + planes[i].getPixelStride());
////                    Log.i(TAG, "rowStride   " + planes[i].getRowStride());
////                    Log.i(TAG, "width  " + image.getWidth());
////                    Log.i(TAG, "height  " + image.getHeight());
////                    Log.i(TAG, "Finished reading data from plane  " + i);
//                }
//                int n_image_size = image.getWidth() * image.getHeight() * 3 / 2;
//                final byte[] yuv420pbuf = new byte[n_image_size];
//                System.arraycopy(ImageUtil.getBytesFromImageAsType(image, 0), 0, yuv420pbuf, 0, n_image_size);
//
//                Log.i("test", "onImageAvailable:====== " + yuv420pbuf.length);
//                image.close();


            }
        }, handler);
        // 这里一定分别add两个surface，一个Textureview的，一个ImageReader的，如果没add，会造成没摄像头预览，或者没有ImageReader的那个回调！！
        mPreviewBuilder.addTarget(surface);
        mPreviewBuilder.addTarget(mImageReader.getSurface());
        mCameraDevice.createCaptureSession(Arrays.asList(surface, mImageReader.getSurface()), new CameraCaptureSession.StateCallback() {
            @Override
            public void onConfigured(@NonNull CameraCaptureSession session) {

                try {

                    session.setRepeatingRequest(mPreviewBuilder.build(), null, handler);
                } catch (CameraAccessException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onConfigureFailed(@NonNull CameraCaptureSession session) {

            }
        }, handler);

    }

    void initHandler() {
        HandlerThread camera = new HandlerThread("camera");
        camera.start();
        handler = new Handler(camera.getLooper());
    }

    private void createCameraPreviewSession() {


    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


}
