package com.liyafeng.imageloader;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.backends.okhttp3.OkHttpImagePipelineConfigFactory;

/**
 * Created by liyafeng on 2018/4/2.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);

//        https://www.fresco-cn.org/docs/using-other-network-layers.html#_
        //配置使用OkHttp
        com.facebook.drawee.backends.pipeline.Fresco.initialize(this, OkHttpImagePipelineConfigFactory.newBuilder(this, new okhttp3.OkHttpClient()).build());

    }
}
