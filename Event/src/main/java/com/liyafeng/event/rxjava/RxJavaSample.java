package com.liyafeng.event.rxjava;

import android.util.Log;

import org.reactivestreams.Publisher;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.internal.operators.observable.ObservableJust;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by liyafeng on 2017/12/27.
 * <p>
 * =========什么事rxjava======================
 * a library for composing asynchronous and event-based programs by using observable sequences.
 * 一个组成异步和基于事件的程序 ，通过使用被观察者的队列
 * 实际上就是一个异步的处理库，而且异步处理完还能通知观察者 进行下一步处理
 * <p>
 * <p>
 * 订阅者-发布者
 * 观察者-被观察者
 * <p>
 * 订阅者 订阅 发布者发布的消息
 * Publisher<T>-发布者
 * Subscriber<T> -订阅者
 * T代表发布的消息类型
 * <p>
 * =============================
 * <p>
 * <p>
 * <p>
 * ==============使用流程================================
 * 创建一个被观察者（有各种创建操作符）
 * 变换、过滤操作符
 * 连接，合并操作
 * 错误处理操作
 * 最后的订阅操作
 * <p>
 * 数据流经过一系列处理被输出，极大方便了复杂的数据流操作
 * <p>
 * ===============
 * <p>
 * io.reactivex.Flowable: 0..N flows, supporting Reactive-Streams and backpressure  支持背压
 * io.reactivex.Observable: 0..N flows, no backpressure,   不支持背压
 * io.reactivex.Single: a flow of exactly 1 item or an error,   发射一个元素
 * io.reactivex.Completable: a flow without items but only a completion or error signal,
 * io.reactivex.Maybe: a flow with no items, exactly one item or an error.
 */

public class RxJavaSample {

    static class CommonResponse {

    }

    /**
     * https://github.com/ReactiveX/RxJava/wiki/What's-different-in-2.0 (2.0详细用法)
     * ==========Function============
     * io.reactivex.functions.Function<T, R>
     * 这个接口将一个值转化为另一个
     * <p>
     * ==========Predicate=======
     * io.reactivex.functions.Predicate<T>
     * A functional interface (callback) that returns true or false for the given input value
     * 传入一个值，返回true或者false
     */
    public RxJavaSample() {

        do1();
        do2();
        do3();

        observable();

    }

    public static void main(String[] args) {
        do9();
    }

    //region 创建操作


    private void observable() {
        ObservableJust.just("").map(new Function<String, Integer>() {
            @Override
            public Integer apply(String s) throws Exception {
                return null;
            }
        }).subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {

                    }
                });

//        ObservableZip.zip()


        //==========================================
        Observable.create(new ObservableOnSubscribe<String>() {

            //当有观察者订阅了我的（Observable）消息
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                //这里是被观察者
                e.onNext("1");
                e.onNext("2");
                if (System.currentTimeMillis() > 0) {
                    //只能调用一次，而且下面的语句不再被接收了
                    e.onError(new NullPointerException());
                }
                e.onComplete();
                //onComplete后，不会执行了
                e.onNext(":");


                //这里执行的线程是 subscribeOn指定的
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .map(new Function<String, Integer>() {
                    @Override
                    public Integer apply(String s) throws Exception {
                        //这里指定的线程是 Schedulers.computation()
                        return Integer.parseInt(s);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Integer>() {
                    private Disposable d;

                    @Override
                    public void onSubscribe(Disposable d) {
                        //Disposable相当于事件对象，用于取消订阅关系？？？
                        this.d = d;
                    }

                    @Override
                    public void onNext(Integer s) {
                        //这个线程是最近一个observeOn指定的
                        if ("compelete".equals(s)) {
                            d.dispose();//中断事件流
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });


        io.reactivex.Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                e.onNext(1);
                e.onNext(1);
                e.onComplete();
                e.onNext(1);
            }
        }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                //这里只接受onNext中的事件，onComplete后的事件不会接收到
            }
        });
    }

    private void do3() {
        Flowable<Long> timer = Flowable.timer(3000, TimeUnit.MILLISECONDS);
        timer.subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Exception {
                Log.i("test", "打印" + aLong);
            }
        });
    }

    /**
     * Flowable
     */
    private void do2() {
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

                Log.i("test", "执行：" + s);
            }
        });
    }

    private void do1() {
        //创造事件队列,然后加入
        Flowable<String> just = Flowable.just("123");//这个是被观察的事件队列，123是内容
        just.subscribe(new Consumer<String>() {//这个是观察者
            @Override
            public void accept(String s) throws Exception {
                Log.i("test", "打印" + s);
            }
        });

    }

    private void do4() {
        Flowable.range(1, 10).flatMap(new Function<Integer, Publisher<?>>() {
            @Override
            public Publisher<?> apply(Integer integer) throws Exception {
                return null;
            }
        }).filter(new Predicate<Object>() {
            @Override
            public boolean test(Object o) throws Exception {
                return false;
            }
        }).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {

            }
        });
    }
    //endregion

    //region  合并操作符
    // https://blog.csdn.net/jdsjlzx/article/details/52415615 ( RxJava 合并组合两个（或多个）Observable数据源)


    /**
     * 合并两个请求同时返回
     * <p>
     * 将两个请求合并为一个请求返回
     * 但是如果有一个发生错误，则返回error
     * <p>
     * 如果你想任何一个成功都返回，那么可以用 onErrorResumeNext 或者 onErrorReturnItem
     * http://cn.voidcc.com/question/p-vukjpiee-ho.html(RxJava：如何处理zip运算符的错误?)
     * 一个返回 ObservableSource ，一个返回 T
     */
    static void do8() {
        Observable<ResponseEntity1> just1 = Observable.just(new ResponseEntity1("1")).flatMap(new Function<ResponseEntity1, ObservableSource<ResponseEntity1>>() {
            @Override
            public ObservableSource<ResponseEntity1> apply(ResponseEntity1 responseEntity1) throws Exception {
                if ("1".equals(responseEntity1.name1)) {
                    throw new Exception("网络请求发生错误");
                }
                return Observable.just(responseEntity1);
            }
        }).onErrorReturnItem(new ResponseEntity1("3"));


        Observable<ResponseEntity2> just2 = Observable.just(new ResponseEntity2("2"));
        Observable.zip(just1, just2, new BiFunction<ResponseEntity1, ResponseEntity2, MergeEntity>() {
            @Override
            public MergeEntity apply(ResponseEntity1 responseEntity1, ResponseEntity2 responseEntity2) throws Exception {
                MergeEntity mergeEntity = new MergeEntity();
                mergeEntity.entity1 = responseEntity1;
                mergeEntity.entity2 = responseEntity2;
                return mergeEntity;
            }
        }).subscribe(new Observer<MergeEntity>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(MergeEntity mergeEntity) {
                System.out.println("收到结果" + mergeEntity.toString());
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("收到结果" + e.getMessage());
            }

            @Override
            public void onComplete() {

            }
        });
    }

    static class ResponseEntity1 {
        public ResponseEntity1(String name1) {
            this.name1 = name1;
        }

        public String name1;

        @Override
        public String toString() {
            return "ResponseEntity1{" +
                    "name1='" + name1 + '\'' +
                    '}';
        }
    }

    static class ResponseEntity2 {
        public ResponseEntity2(String name2) {
            this.name2 = name2;
        }

        public String name2;

        @Override
        public String toString() {
            return "ResponseEntity2{" +
                    "name2='" + name2 + '\'' +
                    '}';
        }
    }

    static class MergeEntity {
        public ResponseEntity1 entity1;
        public ResponseEntity2 entity2;

        @Override
        public String toString() {
            return "MergeEntity{" +
                    "entity1=" + entity1 +
                    ", entity2=" + entity2 +
                    '}';
        }
    }

    //endregion

    //region  过滤操作符

    /**
     * 过滤重复数据
     */
    static void do6() {
        ArrayList<DistinctEntity> distinctEntities = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            DistinctEntity distinctEntity = new DistinctEntity(i, i + "");
            distinctEntities.add(distinctEntity);
        }
        DistinctEntity distinctEntity = new DistinctEntity(2, 2 + "");
        distinctEntities.add(distinctEntity);

        DistinctEntity[] array = distinctEntities.toArray(new DistinctEntity[10]);
        //java8 才支持 Method References

        Disposable disposable = Observable.fromArray(array).distinct(new Function<DistinctEntity, Integer>() {
            @Override
            public Integer apply(DistinctEntity distinctEntity) throws Exception {
                //返回key，所有key相等的会被过滤
                return distinctEntity.getId();
            }
        }).subscribe(new Consumer<DistinctEntity>() {
            @Override
            public void accept(DistinctEntity distinctEntity) throws Exception {
                System.out.println("输出" + distinctEntity.getName());
            }
        });

    }

    static class DistinctEntity {

        public DistinctEntity(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public int id = 0;
        public String name = "";
    }


    //endregion

    //region  订阅操作

    /**
     * Disposable.subscribe 参数  Consumer 和 Observer区别
     * ===========
     * Observer有四个方法
     * Consumer 可以指定只处理其中的某几个方法
     * <p>
     * 有 Consumer参数的 subscribe 返回一个 Disposable对象
     * 而 Observer 参数的 subscribe 无返回值 ， 而在 Observer 里的  onSubscribe方法返回 Disposable对象
     */
    void do7() {
        Disposable subscribe = Observable.just(1).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {

            }
        });
        Observable.just(1).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Integer integer) {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    /**
     * subscribeWith 和 subscribe 区别
     * <p>
     * subscribeWith 可以 替代 subscribe 反之不行
     * <p>
     * subscribeWith是返回了 observer对象，里面也调用了subscribe
     * subscribe(observer);
     * return observer;
     */
    void do7_1() {
        Observable.just(1).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Integer integer) {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });

        Observer<Integer> observer = Observable.just(1).subscribeWith(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Integer integer) {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });


    }

    //endregion


    //region 错误处理
    //https://github.com/ReactiveX/RxJava/wiki/Error-Handling-Operators


    /**
     * retry 和retryWhen
     */
    static void do9() {
        Observer<CommonResponse> callback = new Observer<CommonResponse>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(CommonResponse commonResponse) {
                System.out.println("=====onNext" + commonResponse);
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("=====error" + e.getMessage());
            }

            @Override
            public void onComplete() {
                System.out.println("=====onComplete");
            }
        };

        //这里要在 flatMap 后 retry ，否则retry无效，操作符是顺序执行的
//        Observable.just(new CommonResponse())
//                .flatMap(new Function<CommonResponse, ObservableSource<CommonResponse>>() {
//                    @Override
//                    public ObservableSource<CommonResponse> apply(CommonResponse commonResponse) throws Exception {
//                        if (2 - 1 == 1) {
//                            System.out.println("retry=====");
//                            throw new Exception("   xxxException");
//                        }
//                        return Observable.just(commonResponse);
//                    }
//                })
//                .retry()
//                .subscribe(callback);


        //如果失败，那么重新从1开始发射数据源
        Observable.fromArray(1, 2, 3, 4, 5)
                .flatMap(new Function<Integer, ObservableSource<Integer>>() {
                    @Override
                    public ObservableSource<Integer> apply(Integer integer) throws Exception {
                        System.out.println("retry=====" + integer);
                        if (integer - 2 == 0) {
                            throw new Exception("   xxxException");
                        }
                        return Observable.just(integer);
                    }
                })
                .retry()
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Integer integer) {
                        System.out.println("=====onNext" + integer);
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("=====error" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("=====onComplete");
                    }
                });
    }

    /**
     * rxjava实现刷新token逻辑
     */
    static void do9_1() {

    }

    //endregion

}
