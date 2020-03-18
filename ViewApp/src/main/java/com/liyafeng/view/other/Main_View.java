package com.liyafeng.view.other;

public class Main_View {

    /**
     * /**
     * * ========EditText maxEms和 maxLength ============
     * * http://blog.csdn.net/JavaLive09/article/details/38661773
     * * https://blog.csdn.net/beiminglei/article/details/9317997
     * *
     * * maxLength是字符串长度
     * *
     * * An em is a unit in the field of typography
     * * em是一个印刷排版的单位，表示字宽的单位。 em字面意思为：equal M   （和M字符一致的宽度为一个单位）简称em。
     * *  ems是em的复数表达。
     * *
     * *
     * * =============自定义光标颜色=======
     * * https://segmentfault.com/a/1190000009507919
     * *    <EditText
     * *         android:textCursorDrawable="@drawable/cursor_color"
     * *         android:hint="自定义光标颜色"
     * *         android:layout_width="match_parent"
     * *         android:layout_height="wrap_content" />
     * *
     * * <?xml version="1.0" encoding="utf-8"?>
     * * <shape xmlns:android="http://schemas.android.com/apk/res/android"
     * *     android:shape="rectangle">
     * *     <size android:width="2dp" />
     * *     <solid android:color="@android:color/holo_blue_light" />
     * * </shape>
     * *
     *
     * ===============自定义下划线颜色============
     * https://segmentfault.com/a/1190000009507919
     * 让这个Activity用这个主题即可
     * <style name="MyEditText" parent="Theme.AppCompat.Light">
     *         <item name="colorControlNormal">@android:color/darker_gray</item>
     *         <item name="colorControlActivated">@android:color/holo_orange_dark</item>
     *     </style>
     * colorControlNormal 表示控件默认的颜色，colorControlActivated 表示控件被激活时的颜色，这样，我们就可以分别设置EditText不被选中和选中时的颜色了。这里我将选中的颜色设为橙色。
     *
     * 下划线和光标的颜色都一样
     *
     * 如果只想改光标，那么可以下划线还用background，这样下划线就能自定义了
     *
     *
     * @param args
     */

    public static void main(String[] args) {

        /*
        *
        * 一般我们在onTextChanged()；方法里做一些自己要做的事，比如监听输入的字符长度，或者应用在验证输入一个手机号就设置按钮可点击等等。
            beforeTextChanged();在View改变之前执行，好比你输入了字符，系统先统计你输入的信息，在这里可以提前获取你的动机。
            onTextChanged();在View改变之后短时间内执行，也就是区别afterTextChanged();的一直执行状态，他只调用一次。我们做自己的操作一般在这里；
            afterTextChanged();在你输入完成后执行，我们输入完后处于完成状态，他就监测到完成了就不断的执行，因为我们不操作，是不是一直处于完成状态？所以就处于死循环了。切记在此做操作。
            ---------------------
            作者：鼎鼎浩
            来源：CSDN
            原文：https://blog.csdn.net/Z_DingHao/article/details/63263129
            版权声明：本文为博主原创文章，转载请附上博文链接！
        * */
//        EditText editText;
//        editText.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//            }
//        });
    }


    /**
     * 简单实现ImageView宽度填满屏幕，高度自适应的两种方式
     *
     * 两种方式
     * 1.重写View的onMeasure方法参考这里easion_zms的专栏
     *
     * 核心代码
     *
     * protectedvoidonMeasure(intwidthMeasureSpec,intheightMeasureSpec){
     *
     * Drawable d = getDrawable();if(d!=null){// ceil not round - avoid thin vertical gaps along the left/right edgesintwidth = MeasureSpec.getSize(widthMeasureSpec);//高度根据使得图片的宽度充满屏幕计算而得intheight = (int) Math.ceil((float) width * (float) d.getIntrinsicHeight() / (float) d.getIntrinsicWidth());
     *
     * setMeasuredDimension(width, height);
     *
     * }else{super.onMeasure(widthMeasureSpec, heightMeasureSpec);
     *
     * }
     *
     * 2.设置ImageView的属性：
     *
     * //宽度填满屏幕
     *
     * android:layout_width=”match_parent”
     * android:scaleType=”fitXY”
     * android:layout_height=”wrap_content”
     * //保持比例，一定要设置
     * android:adjustViewBounds=”true”
     *
     * 作者：WiiHuu
     * 链接：https://www.jianshu.com/p/c9424615e99d
     * 来源：简书
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     *
     * ================Imageview清空背景=======
     * ivImage.setImageResource(0);
     *
     *
     * ==============滑动imageivew========
     *
     * <ScrollView
     *         android:layout_width="match_parent"
     *         android:layout_height="match_parent">
     *
     *         <ImageView
     *             android:layout_width="match_parent"
     *             android:layout_height="wrap_content"
     *             android:adjustViewBounds="true"
     *             android:scaleType="fitXY" />
     *
     *     </ScrollView>
     *
     * =============圆角的imageview=============
     *
     *
     * RoundedCorners roundedCorners = new RoundedCorners(radius);
     *             RequestOptions options = RequestOptions.bitmapTransform(roundedCorners);
     *             Glide.with(context).load(bitmap).apply(options).into(imageView);
     *
     * ----上面这个是给整个bitmap圆角
     * 这个是先CenterCrop 再圆角
     *  RequestOptions transforms = new RequestOptions().transforms(new CenterCrop(), new RoundedCorners(radius));
     *             Glide.with(context).load(bitmap).apply(transforms).into(imageView);
     *
     *
     *
     *
     */
    void a1(){}
}
