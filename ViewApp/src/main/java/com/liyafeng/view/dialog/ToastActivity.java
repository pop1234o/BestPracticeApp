package com.liyafeng.view.dialog;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.liyafeng.view.R;

public class ToastActivity extends AppCompatActivity implements View.OnClickListener {

    /**
     * Button
     */
    private Button mButton10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toast);
        initView();


    }

    private void initView() {
        mButton10 = (Button) findViewById(R.id.button10);
        mButton10.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.button10:
                showToast();
                break;
        }
    }

    private void showToast() {
        View toastRoot = getLayoutInflater().inflate(R.layout.toast_full, null);
        Toast toast = new Toast(getApplicationContext());
        toast.setView(toastRoot);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setMargin(0,0);
        toast.show();
    }
}
