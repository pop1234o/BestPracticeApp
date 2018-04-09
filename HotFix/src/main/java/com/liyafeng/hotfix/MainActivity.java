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
                new BugClass().doSomething();
            }
        });

        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    DexInsertHotFix.loadFix(MainActivity.this);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }

            }
        });
//        DexClassLoader
    }
}
