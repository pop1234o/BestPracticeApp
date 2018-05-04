package com.liyafeng.view.camera;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.liyafeng.view.R;

import java.io.File;

public class SystemCameraActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int REQUEST_IMAGE_CAPTURE = 1;
    /**
     * Button
     */
    private Button mButton13;
    private ImageView mIvView;

    /**
     * https://developer.android.google.cn/training/camera/photobasics
     * 我们可以调用系统的照相app来为我们照相或者录像
     * 这样能进解决很多适配问题
     * 我们可以指定拍照或者录像的存储位置
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system_camera);
        initView();


    }

    private void initView() {
        mButton13 = (Button) findViewById(R.id.button13);
        mButton13.setOnClickListener(this);
        mIvView = (ImageView) findViewById(R.id.iv_view);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.button13:
                startCamera();
                break;
        }
    }

    private void startCamera() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            mIvView.setImageBitmap(imageBitmap);
        }
    }

    /**
     * 通知相册有新相片了
     */
    private void galleryAddPic() {
//        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
//        File f = new File(mCurrentPhotoPath);
//        Uri contentUri = Uri.fromFile(f);
//        mediaScanIntent.setData(contentUri);
//        this.sendBroadcast(mediaScanIntent);
    }
}
