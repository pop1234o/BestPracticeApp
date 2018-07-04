package com.liyafeng.view.dialog;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.PopupWindow;

import com.liyafeng.view.R;

public class MainPopUpWindowActivity extends AppCompatActivity implements View.OnClickListener {

    /**
     * @param args
     *
     * https://www.jianshu.com/p/c9a83decb314
     */
    public static void main(String[] args) {

    }
    /**
     * Button
     */
    private Button mButton7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_pop_up_window);
        initView();


    }

    private void initView() {
        mButton7 = (Button) findViewById(R.id.button7);
        mButton7.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.button7:

                showPopBottom();



                break;
        }
    }

    private void showPop() {
        final PopupWindow popupWindow = new PopupWindow(this,null,R.style.bottomDialog);
        View view = LayoutInflater.from(this).inflate(R.layout.popup, null);
        popupWindow.setContentView(view);

        popupWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);

        popupWindow.setOutsideTouchable(true);
        popupWindow.setTouchable(true);


        //这个很重要，如果不设置，那么再次点击按钮就不是消失pop，而是消失又弹出，而且键盘没法弹出
        popupWindow.setFocusable(true);
//                popupWindow.setAnimationStyle(R.style.);

        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#88000000")));
//                popupWindow.showAsDropDown(mButton7, 0, -mButton7.getHeight());//在view下方

        //如果是0,0的话，默认是在这个view（anchor锚）的左下角作为起始点
        //Gravity.RIGHT代表在view的右下角开始 这个是api19开始支持


        //这种显示方式如果是和view无关，里面只是为了获得token
        popupWindow.showAtLocation(mButton7, Gravity.BOTTOM|Gravity.LEFT,100,200);
        //这个x y的位置是先计算重力所在的位置，然后再以那个位置为左上角，x,y是相对位置

        //如果是Gravity.BOTTOM   设置y值不能为负值
        //设置了重力后坐标系的方向就变了，如果是Gravity.BOTTOM 那么y轴的方向就是向上
    }

    private void showPopBottom() {
        final PopupWindow popupWindow = new PopupWindow(this);
        View view = LayoutInflater.from(this).inflate(R.layout.popup, null);
        popupWindow.setContentView(view);


        popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setTouchable(true);

        //要设置背景是透明的
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#00000000")));

        popupWindow.setAnimationStyle(R.style.anim_bottomDialog);

        popupWindow.showAtLocation(mButton7, Gravity.BOTTOM|Gravity.LEFT,0,0);


        View cl_content = view.findViewById(R.id.cl_content);
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) view.getLayoutParams();
        layoutParams.height = 500;
        layoutParams.gravity = Gravity.BOTTOM;


        FrameLayout parent = (FrameLayout) cl_content.getParent();
        parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });

        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = 0.7f;//设置阴影透明度
        getWindow().setAttributes(lp);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = getWindow().getAttributes();
                lp.alpha = 1f;
                getWindow().setAttributes(lp);
            }
        });
//        getWindow().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#88000000")));

    }
}
