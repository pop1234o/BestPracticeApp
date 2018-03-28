package com.liyafeng.view.listview.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import com.liyafeng.view.listview.custom.adapter.ListAdapter;

/**
 * Created by liyafeng on 2018/3/28.
 *
 * ListView具体负责，view的测量，和布局位置，和
 */

public class ListView extends AbsListView {


    public ListView(Context context) {
        super(context);
    }

    public ListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setAdapter(ListAdapter adapter) {
        mAdapter = adapter;

        //初始化缓存大小
        mRecycleBin.setViewTypeCount(adapter.getViewTypeCount());

    }

    @Override
    protected void layoutChildren() {

    }

    /**
     * 这两个是父布局给的范围，（自己定义的大小）（包括了模式）
     *
     * 这里是确定了各个模式下布局的高度，最终记录到 mMeasuredWidth mMeasuredHeight 中
     * @param widthMeasureSpec  这里是父布局给的高度，（可以给的最大高度或者ListView请求的高度的最小的那个）
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);


        final int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        final int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int childHeight = 0;
        //这里外层是ScrollView，所以ListView的模式是任意大小
        if(mAdapter.getCount()>0 ||(widthMode == MeasureSpec.UNSPECIFIED
                || heightMode == MeasureSpec.UNSPECIFIED)){
            //这种模式下，我们将ListView的高度设置为1一个child的高度
            View view = obtainView(0);
            int height = view.getLayoutParams().height;
            int measureHeightSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY);
            view.measure(widthMeasureSpec,measureHeightSpec);
            childHeight = view.getMeasuredHeight();
        }
        //在ScrollView中测量出的ListView高度就是一个child的高度
        if(heightMode == MeasureSpec.UNSPECIFIED){
            heightSize = childHeight;
        }

        // TODO: 2018/3/28 这里其实还有很多情况的高度，比如warp_content，那么就要计算子View的高度和
        // 然后和父布局的高度比较，如果高与heightSize，就返回heightSize，如果低，那么返回子View的高度和
        //真正的赋值
        setMeasuredDimension(widthSize, heightSize);

    }
}
