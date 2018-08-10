package com.liyafeng.view.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.TextView;

import com.liyafeng.view.R;

public class DialogDemo extends Dialog {
    public DialogDemo(@NonNull Context context) {
        super(context, R.style.Theme_AppCompat_Dialog);//这里要设置一下兼容的主题，要不在4.x的机器上显示边框

//        implementation 'com.android.support:appcompat-v7:27.0.2'
//        implementation 'com.android.support:design:27.0.2'
        setContentView(R.layout.dialog_invite);
        initView();
    }

    private void initView() {
        TextView tv_invite = findViewById(R.id.tv_invite);
        TextView tv_not_invite = findViewById(R.id.tv_not_invite);
        tv_not_invite.setOnClickListener(v -> dismiss());

        tv_invite.setOnClickListener(v -> {
        });
    }


    public static void showDialog(@NonNull Activity context) {

        new DialogDemo(context).show();

    }

}
