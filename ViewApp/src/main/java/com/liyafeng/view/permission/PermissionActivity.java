package com.liyafeng.view.permission;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.liyafeng.view.R;

public class PermissionActivity extends AppCompatActivity {

    private static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 1;
    private PermissionActivity thisActivity;

    /**
     * 运行时请求权限
     * https://developer.android.google.cn/training/permissions/requesting
     *
     * targetApi在 23及以上的时候需要申请动态权限
     * 而且所有时候权限必须在xml中声名，否则动态申请也不授予权限
     *
     * 或者用第三方的库
     * https://github.com/tbruyelle/RxPermissions
     * https://github.com/lypeer/FcPermissions
     *
     * https://cloud.tencent.com/developer/article/1006326
     *
     * ========Android查看权限三方包中引入的====
     * https://www.jianshu.com/p/0dfe7db7ecde
     * 对于权限的合并，会生成一个相应的Log文件。在 app/build/outputs下，
     *
     * 删除权限
     * 2. tools:node 属性
     * 这个标签指定了manifest中冲突属性的合并规则或删除不必要的元素和属性，很明显，对于三方中的权限，我们是要进行删除的
     *
     * uses-permission
     *         android:name="android.permission.READ_PHONE_STATE"
     *         tools:node="remove" />
     *
     * tools是一个比较强大的功能，除了指定permission外，对于activity的属性、service以及application等中的属性都可指定，而除了remove,还有replace、strict等，具体可见官网：https://developer.android.com/studio/build/manifest-merge?hl=zh-cn
     *
     * 注意：在使用上述tools:node="remove"方式移除危险权限时，一定要保证sdk无此权限也能正常运行且不影响功能，否则的话，还需在应用中申请此权限。
     *
     * 作者：baifanger
     * 链接：https://www.jianshu.com/p/0dfe7db7ecde
     * 来源：简书
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission);
        thisActivity = this;
        findViewById(R.id.button12).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Here, thisActivity is the current activity
                int permission = ContextCompat.checkSelfPermission(thisActivity, Manifest.permission.READ_CONTACTS);
                //检查是否授权了
                if (permission != PackageManager.PERMISSION_GRANTED) {

                    // 当用户之前点击拒绝后，这个方法返回true，这个时候我们最好弹窗给用户解释我们为什么要这个权限
                    //如果用户拒绝而且勾选了dont ask again，此方法返回false
                    if (ActivityCompat.shouldShowRequestPermissionRationale(thisActivity, Manifest.permission.READ_CONTACTS)) {

                        // Show an expanation to the user *asynchronously* -- don't block
                        // this thread waiting for the user's response! After the user
                        // sees the explanation, try again to request the permission.

                        Toast.makeText(thisActivity, "请求权限", Toast.LENGTH_SHORT).show();
                        ActivityCompat.requestPermissions(thisActivity,
                                new String[]{Manifest.permission.READ_CONTACTS},
                                MY_PERMISSIONS_REQUEST_READ_CONTACTS);

                    } else {

                        // No explanation needed, we can request the permission.
                        //这里是可以不解释，直接请求权限
                        ActivityCompat.requestPermissions(thisActivity,
                                new String[]{Manifest.permission.READ_CONTACTS},
                                MY_PERMISSIONS_REQUEST_READ_CONTACTS);

                        // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                        // app-defined int constant. The callback method gets the
                        // result of the request.
                    }
                } else {
                    Log.i("test", "授权了");
                }
            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_CONTACTS: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.

                    Log.i("test", "授权了！！！");
                } else {

                    Log.i("test", "拒绝了！！！");

                    //如果之前拒绝了，勾选了dont ask again，每次请求权限都会调用到这里
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }


    /**
     * 表 1. 危险权限和权限组。
     * https://developer.android.google.cn/guide/topics/security/permissions?hl=zh-cn
     * <p>
     * 权限组	权限
     * CALENDAR
     * READ_CALENDAR
     * WRITE_CALENDAR
     * CAMERA
     * CAMERA
     * CONTACTS
     * READ_CONTACTS
     * WRITE_CONTACTS
     * GET_ACCOUNTS
     * LOCATION
     * ACCESS_FINE_LOCATION
     * ACCESS_COARSE_LOCATION
     * MICROPHONE
     * RECORD_AUDIO
     * PHONE
     * READ_PHONE_STATE
     * CALL_PHONE
     * READ_CALL_LOG
     * WRITE_CALL_LOG
     * ADD_VOICEMAIL
     * USE_SIP
     * PROCESS_OUTGOING_CALLS
     * SENSORS
     * BODY_SENSORS
     * SMS
     * SEND_SMS
     * RECEIVE_SMS
     * READ_SMS
     * RECEIVE_WAP_PUSH
     * RECEIVE_MMS
     * STORAGE
     * READ_EXTERNAL_STORAGE
     * WRITE_EXTERNAL_STORAGE
     */
    public void a1() {
    }
}
