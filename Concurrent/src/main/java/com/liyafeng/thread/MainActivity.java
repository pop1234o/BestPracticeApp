package com.liyafeng.thread;

import android.app.Activity;
import android.os.Bundle;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void thread(){
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<?> submit = executorService.submit(new Runnable() {
            @Override
            public void run() {

            }
        });

        try {
            Object o = submit.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
