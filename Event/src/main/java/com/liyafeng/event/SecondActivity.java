package com.liyafeng.event;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.liyafeng.event.MainActivity;
import com.liyafeng.event.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import static com.liyafeng.event.MainActivity.TAG;


public class SecondActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        EventBus.getDefault().register(this);
        MainActivity.Event stickyEvent = EventBus.getDefault().getStickyEvent(MainActivity.Event.class);
        Log.i(TAG, "onCreate: "+stickyEvent);
    }

    @Subscribe(sticky = true)
    public void dosome(MainActivity.Event event){
        Log.i(TAG, "dosome: "+event.msg);
        try {
             throw new Exception();
        } catch (Exception e) {
             e.printStackTrace();
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }


}
