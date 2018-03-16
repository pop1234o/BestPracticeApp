package com.liyafeng.view.fragment;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;

import com.liyafeng.view.R;
import com.liyafeng.view.fragment.custom.CustomFragment;

public class CustomFragmentActivity extends FragmentActivity {

    private final String TAG = this.getClass().getSimpleName();
    private CustomFragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);


        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                FragmentManager fragmentManager = getFragmentManager();
//                FragmentTransaction transaction = fragmentManager.beginTransaction();
//                transaction.commit();
                android.support.v4.app.FragmentManager manager = getSupportFragmentManager();
                android.support.v4.app.FragmentTransaction transaction = manager.beginTransaction();
//                transaction.remove(fragment);
                transaction.detach(fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

//         getFragmentManager()
//        FragmentTransaction transaction = fragmentManager.beginTransaction();
//
//        frameFragment = new FrameFragment();
//        transaction.replace(R.id.rl_content, frameFragment);
//        transaction.commit();

//        transaction.addToBackStack(null);
        android.support.v4.app.FragmentManager manager = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction transaction = manager.beginTransaction();
        fragment = new CustomFragment();
        transaction.add(R.id.rl_content, fragment);
        transaction.addToBackStack(null);
        transaction.commit();

        Log.i(TAG, "onCreate: ");
    }



    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause: ");

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop: ");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy: ");

    }
}
