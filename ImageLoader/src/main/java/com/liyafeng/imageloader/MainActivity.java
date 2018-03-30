package com.liyafeng.imageloader;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

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
     *
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       final ImageView imageView  = (ImageView)findViewById(R.id.imageView);
       findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               String url = "http://i.imgur.com/DvpvklR.png";
               loadImageByPicasso(url,imageView);
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
     *
     * into() 创建ImageViewAction(持有Request) 用Picasso中的dispatcher.
     *  dispatcher.dispatchSubmit(action);发送消息，在子线程中执行performSubmit（Action）
     *
     *  Dispatcher={
     *    DispatcherThread ,这是一个HandlerThread
     *    DispatcherHandler ,这个持有DispatcherThread的Looper,他发送消息直接发送到子线程中
     *    HANDLER 这个是Picasso中的主线程Handler
     *    也持有ExecutorService ,downloader,cache，这也是
     *  }
     * BitmapHunter 这个是Dispatcher.performSubmit中创建的,是Runnable子类，用来获取
     * 创建BitmapHunter的时候会获取Picasso中的requestHandlers，然后遍历，传入Request，判断哪个Handler能处理这个Request
     * BitmapHunter=>{
     *     picasso,
     *     dispatcher,
     *     cache,
     *     stats, 这个是状态回调，可以监听各个步骤的状态
     *     action,
     *     requestHandler
     * }
     *
     * performSubmit()方法中用ExecutorService执行BitmapHunter
     *
     * BitmapHunter 中的run方法中，真正获取bitmap ,如果可以从缓存中读取，那么读取
     * 如果不存在，则调用 requestHandler.load（Request，Policy）方法
     * 拿网络请求来说，就是调用NetWorkRequestHandler.load(),里面调用了 Okhttp来请求网络，获取响应流
     * 然后用 BitmapFactory 解析流，返回bitmap对象
     *
     * BitmapHunter成功获取到bitamp后调用  dispatcher.dispatchComplete(this);
     * 里面在子线程中再次调用performComplete（），将BitmapHunter加入批处理队列
     * List<BitmapHunter> batch，然后复制一份，用Picasso中的MainHandler发送到Picasso中
     * 最终在主线程中调用了Action.complete(),
     * 这里是ImageViewAction ,最终将imageview，设置了bitmap
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
        @Override public Bitmap transform(Bitmap source) {
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

        @Override public String key() { return "square()"; }
    }

}
