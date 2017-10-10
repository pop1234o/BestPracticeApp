package com.liyafeng.view.svg;

import android.app.Activity;
import android.os.Bundle;

import com.liyafeng.view.R;

public class SVGActivity extends Activity {

    /**
     * scalable vector graphics
     * 用向量来描述一个图形
     *
     * 我们可以用svg转path，然后就可以在Android中实现一些比较复杂的图形了
     *
     * 当然还有开源的lottie，airbnb的，它需要ae来制作动画，用插件来转换为json
     * json中有一些关键信息，转化为代码中的path 等基本图形
     *
     * 仔细看demo里的效果都可以由基本图形组成
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_svg);
    }
}
