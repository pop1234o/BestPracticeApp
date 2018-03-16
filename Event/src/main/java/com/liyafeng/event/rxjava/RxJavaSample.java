package com.liyafeng.event.rxjava;

import android.util.Log;

import java.util.Observable;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by liyafeng on 2017/12/27.
 * a library for composing asynchronous and event-based programs by using observable sequences.
 * 一个组成异步和基于事件的程序 ，通过使用被观察者的队列
 * 实际上就是一个异步的处理库，而且异步处理完还能通知观察者 进行下一步处理
 * <p>
 * 我们可以手写 Thread然后使用Handler来实现相同的逻辑，但是rxjava它简洁，链式调用，
 * 可维护性高
 *
 * 订阅者-发布者
 * 观察者-被观察者
 *
 * 订阅者 订阅 发布者发布的消息
 */

public class RxJavaSample {

    public RxJavaSample() {

        //创造事件队列,然后加入
        Flowable<String> just = Flowable.just("123");//这个是被观察的事件队列，123是内容
        just.subscribe(new Consumer<String>() {//这个是观察者
            @Override
            public void accept(String s) throws Exception {
                Log.i("test", "打印" + s);
            }
        });

//        int[] ints = new int[3];
//        Flowable<Long> timer = Flowable.timer(3000, TimeUnit.MILLISECONDS);
//        timer.subscribe(new Consumer<Long>() {
//            @Override
//            public void accept(Long aLong) throws Exception {
//                Log.i("test", "打印" + aLong);
//            }
//        });
//
        Flowable.fromCallable(new Callable<String>() {//发布者
            @Override
            public String call() throws Exception {
                Thread.sleep(3000);
                return "完成";
            }
        })
                .subscribeOn(Schedulers.io())//订阅的事件（被观察者）执行在哪个线程
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<String>() {//订阅者
            @Override
            public void accept(String s) throws Exception {

                Log.i("test", "执行："+s);
            }
        });
    }
}
