package com.liyafeng.imageloader;

import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    /**
     * http://stormzhang.com/opensource/2016/06/26/android-open-source-project-recommend1/
     * 图片加载框架
     * 这个必须要加网络请求权限
     * ====================================
     * Picasso Square公司创造 A powerful image downloading and caching library for Android
     * http://square.github.io/picasso/
     * <p>
     * ======================================
     * Glide google员工写的 一个快速高效的Android图片加载库，注重于平滑的滚动。
     * https://muyangmin.github.io/glide-docs-cn/
     * =========================================
     * Fresco  Facebook出品，主要将bitmap存入native堆中，不受jvm控制，减少OOM
     * https://www.fresco-cn.org/docs/index.html
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ImageView imageView = (ImageView) findViewById(R.id.imageView);
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "http://i.imgur.com/DvpvklR.png";
                loadImageByFresco(url, imageView);
            }
        });
    }

    /**
     * Picasso 集中管理的类，使用了单例和Builder模式（对于这种复杂的构建）
     * Picasso =》{
     * Downloader，具体的网络下载器
     * Cache，缓存
     * ExecutorService，执行加载的线程池服务
     * Dispatcher ，请求响应分发
     * List<RequestHandler> requestHandlers 这个是所有种类请求的集合，比如从网络，从res，从磁盘
     * }
     * load方法后，创建了RequestCreator 对象
     * RequestCreator  = {Request.Builder 构建Request}
     * <p>
     * into() 创建ImageViewAction(持有Request) 用Picasso中的dispatcher.
     * dispatcher.dispatchSubmit(action);发送消息，在子线程中执行performSubmit（Action）
     * <p>
     * Dispatcher={
     * DispatcherThread ,这是一个HandlerThread
     * DispatcherHandler ,这个持有DispatcherThread的Looper,他发送消息直接发送到子线程中
     * HANDLER 这个是Picasso中的主线程Handler
     * 也持有ExecutorService ,downloader,cache，这也是
     * }
     * BitmapHunter 这个是Dispatcher.performSubmit中创建的,是Runnable子类，用来获取
     * 创建BitmapHunter的时候会获取Picasso中的requestHandlers，然后遍历，传入Request，判断哪个Handler能处理这个Request
     * BitmapHunter=>{
     * picasso,
     * dispatcher,
     * cache,
     * stats, 这个是状态回调，可以监听各个步骤的状态
     * action,
     * requestHandler
     * }
     * <p>
     * performSubmit()方法中用ExecutorService执行BitmapHunter
     * <p>
     * BitmapHunter 中的run方法中，真正获取bitmap ,如果可以从缓存中读取，那么读取
     * 如果不存在，则调用 requestHandler.load（Request，Policy）方法
     * 拿网络请求来说，就是调用NetWorkRequestHandler.load(),里面调用了 Okhttp来请求网络，获取响应流
     * 然后用 BitmapFactory 解析流，返回bitmap对象
     * <p>
     * BitmapHunter成功获取到bitamp后调用  dispatcher.dispatchComplete(this);
     * 里面在子线程中再次调用performComplete（），将BitmapHunter加入批处理队列
     * List<BitmapHunter> batch，然后复制一份，用Picasso中的MainHandler发送到Picasso中
     * 最终在主线程中调用了Action.complete(),
     * 这里是ImageViewAction ,最终将imageview，设置了bitmap
     * ========================总结=================================
     * Request封装成ImageViewAction，封装成BitmapHunter （Runnable）
     * 然后线程池执行，返回结果，handler发送到主线程，然后Action中负责如何显示
     *
     * @param url
     * @param imageView
     */
    private void loadImageByPicasso(String url, ImageView imageView) {
        Picasso.get().load(url)
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher_round)
                .into(imageView);

        Picasso.get()
                .load(url)
//                .resize(50, 50)
//                .centerCrop()
                .transform(new CropSquareTransformation())
                .into(imageView);

        Picasso.get().load(R.mipmap.ic_launcher).into(imageView);
        Picasso.get().load("file:///android_asset/DvpvklR.png").into(imageView);
        Picasso.get().load(new File("file.jpg")).into(imageView);


    }

    public class CropSquareTransformation implements Transformation {
        @Override
        public Bitmap transform(Bitmap source) {
            //变成正方形
            int size = Math.min(source.getWidth(), source.getHeight());
            int x = (source.getWidth() - size) / 2;
            int y = (source.getHeight() - size) / 2;
            Bitmap result = Bitmap.createBitmap(source, x, y, size, size);
            if (result != source) {
                source.recycle();
            }
            return result;
        }

        @Override
        public String key() {
            return "square()";
        }
    }


    /**
     * 这个和Picasso差不多，是谷歌员工根据Picasso改造而来，专门用于RecyclerView这样的滑动列表图片的加载
     * =======================Glide和Picasso的区别======================
     * 1.Glide 默认的 Bitmap 格式是 RGB_565 格式，而 Picasso 默认的是 ARGB_8888 格式，这个内存开销要小一半。
     * 2.在磁盘缓存方面，Picasso 只会缓存原始尺寸的图片，而 Glide 缓存的是ImageView尺寸的图片，这样占用内存更小
     * 3.Glide支持gif
     * 4.可以传入Activity或者Fragment，他们onPause的时候可以停止加载，onResume的时候继续加载
     * 缺点：Glide如果要配置占位图等比较麻烦，如果要简单就要配置注解分析器，生成GlideApp代码
     *
     * ==============================glide源码分析========================
     * https://juejin.im/entry/586766331b69e60063d889ea（文章写得很好）
     * Glide借鉴了Picasso的思路，解耦更彻底，但是构成的逻辑更复杂
     * Glide用外观模式将各个模块协调起来。
     *
     * @param url
     * @param imageView
     */
    private void loadImageByGlide(String url, ImageView imageView) {
        Glide.with(this).load(url).into(imageView);
//        Glide.with(this).clear(imageView);//取消加载

        //调用这个需要在Application添加注解，而且要依赖额外的注解处理的库
//        GlideApp.with(this).load(url).placeholder(R.mipmap.ic_launcher).into(imageView);
    }


    /**
     * ================================
     * 源码解析：https://www.jianshu.com/p/265c628a0d59
     * 内部网络层用的HttpUrlConnection,当然你可以自己配置成Okhttp
     * 整个fresco结构类似于MVC
     * DraweeView，负责显示加载好的图片，相当于V
     * DraweeHierarchy，组织和维护Drawable对象（封装了Drawable）相当于M
     * DraweeController，负责和image loader（就是image pipeline）交互来加载图片，相当于C
     * ----------------------
     * 里面使用了Builder模式，策略模式，
     *
     * @param url
     * @param imageView
     */
    private void loadImageByFresco(String url, ImageView imageView) {
        //流程图
        getResources().getDrawable(R.drawable.fresco);

        Uri uri = Uri.parse(url);
        SimpleDraweeView draweeView = (SimpleDraweeView) findViewById(R.id.my_image_view);
        //下载gif或者webp后自动播放
//        DraweeController controller = Fresco.newDraweeControllerBuilder()
//                .setUri(uri)
//                .setAutoPlayAnimations(true) .build();
//        draweeView.setController(controller);

        //代码中使用Drawees
     /*   GenericDraweeHierarchyBuilder builder =
                new GenericDraweeHierarchyBuilder(getResources());
        GenericDraweeHierarchy hierarchy = builder
                .setFadeDuration(300)
                .setPlaceholderImage(R.mipmap.ic_launcher_round)
                .build();
        draweeView.setHierarchy(hierarchy);*/

        draweeView.setImageURI(uri);
    }


    /**
     *  类型	        SCHEME	                    示例
     远程图片	http://, https://	            HttpURLConnection 或者参考 使用其他网络加载方案
     本地文件	file://	                        FileInputStream
     Content provider	content://	            ContentResolver
     asset目录下的资源	asset://	            AssetManager
     res目录下的资源	res://	                    Resources.openRawResource
     Uri中指定图片数据	data:mime/type;base64,	数据类型必须符合 rfc2397规定 (仅支持 UTF-8)
     *
     * */
}
