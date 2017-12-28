package com.liyafeng.event.eventbus.custom;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.liyafeng.event.R;

import static com.liyafeng.event.MainActivity.TAG;


public class CustomMainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CustomEventBus.getDefault().register(this);
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            int count= 0;
            @Override
            public void onClick(View v) {
                CustomEventBus.getDefault().post(new Event("1"));
                Log.i(TAG, "onClick: " + Thread.currentThread().getName());
            }
        });
        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(MainActivity.this, SecondActivity.class));
            }
        });
    }

    @CustomSubscribe(threadMode = CustomEventBus.ThreadMode.BACKGROUND)
    public void doSomething(Event event) {

        Log.i(TAG, "doSomething: " + Thread.currentThread().getName());
//        Toast.makeText(this, event.msg+ " do !", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        CustomEventBus.getDefault().unregister(this);
    }

    public static class Event {
        public String msg;

        public Event(String msg) {
            this.msg = msg;
        }
    }




}
