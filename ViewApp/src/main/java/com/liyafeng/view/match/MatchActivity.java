package com.liyafeng.view.match;

import android.app.Activity;
import android.os.Bundle;

import com.liyafeng.view.R;

public class MatchActivity extends Activity {

    /**
     * 屏幕适配
     * https://developer.android.google.cn/training/multiscreen/screendensities.html
     *
     * dpi dots per inch 每英寸的像素点个数 ，一定程度上代表屏幕的大小（屏幕密度）
     *
     * dip 独立像素密度  pixel
     *
     * 标准的是 在dpi为160 的设备上 1px = 1dp ，这个160是认为定义
     *
     * mdpi-160-1  ldpi-120-0.75  hdpi-240-1.5 xhdpi-320-2 xxhdpi- 480-3
     *
     *
     * px = dp * (dpi /160)
     *
     *
     *
     * 一般设计师根据某个分辨率设计出来后，有专门的单位转换工具，比如标你妹
     * 上面可以生成其他 dpi的dp值，我们依照写即可
     *
     * 这个是按每英寸的像素来定义的，但是dpi相同分辨率也可能不同
     * 所以需要单独的 values-xxx适配
     *
     * RelativeLayout + 多文件夹的 dp
     *
     * LinearLayout 的权重
     *
     *
     *
     * 代码动态计算宽高
     *
     * 针对特殊value-xx重写布局
     *
     * 对照生成指定的像素转换文件（鸿翔）
     *
     * ==========================mipmap===================
     * https://developer.android.google.cn/guide/topics/resources/providing-resources.html（官方的目录介绍）
     * mipmap在图片缩放上会有优化，但是在同等dpi的设备上，图片不缩放，那么和drawable是一样的效果
     * 所以mipmap用来存放app的启动图片。原来的图片还是放在drawable下
     *
     *
     * ===================res文件下资源文件夹的命名规则======================
     * https://developer.android.google.cn/guide/topics/resources/providing-resources.html
     *
     *
     *
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);
    }
}
