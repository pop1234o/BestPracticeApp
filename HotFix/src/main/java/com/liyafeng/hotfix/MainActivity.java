package com.liyafeng.hotfix;

import android.app.Activity;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.io.File;

import dalvik.system.DexClassLoader;

public class MainActivity extends Activity {

    /**
     * DexClassLoader
     * http://androidxref.com/8.0.0_r4/xref/libcore/dalvik/src/main/java/dalvik/system/DexClassLoader.java
     * http://androidxref.com/8.0.0_r4/xref/libcore/dalvik/src/main/java/dalvik/system/DexPathList.java
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BugClass.doSomething();
            }
        });

        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File externalCacheDir = getExternalCacheDir();
                Log.i("test", "getExternalCacheDir"+externalCacheDir.getAbsolutePath());
                File externalFilesDir = getExternalFilesDir(null);
                Log.i("test", "getExternalFilesDir"+externalFilesDir.getAbsolutePath());
                File filesDir = getFilesDir();
                Log.i("test", "getFilesDir"+filesDir.getAbsolutePath());
                File cacheDir = getCacheDir();
                Log.i("test", "getCacheDir"+cacheDir.getAbsolutePath());

                File dataDirectory = Environment.getDataDirectory();
                Log.i("test", "getDataDirectory"+dataDirectory.getAbsolutePath());
                File downloadCacheDirectory = Environment.getDownloadCacheDirectory();
                Log.i("test", "getDownloadCacheDirectory"+downloadCacheDirectory.getAbsolutePath());
                File externalStorageDirectory = Environment.getExternalStorageDirectory();
                Log.i("test", "getExternalStorageDirectory"+externalStorageDirectory.getAbsolutePath());
                File rootDirectory = Environment.getRootDirectory();
                Log.i("test", "getRootDirectory"+rootDirectory.getAbsolutePath());



            }
        });
//        DexClassLoader
    }
}
