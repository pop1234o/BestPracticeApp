package com.liyafeng.event.rxjava;

import android.util.Log;

import org.reactivestreams.Publisher;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

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
import io.reactivex.observables.GroupedObservable;
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
 * <p>
 * ========================
 * 如果一系列数据发送，如果发送了一个error，那么后面的数据就不会被发送
 * ---------
 * rxjava数据流是根据操作符的先后顺序来的
 * ----------
 */

public class RxJavaSample {

    static class CommonResponse {
        public int error_code;
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
        do11();
    }

    //region 线程切换

    /**
     * rxjava线程切换
     * =========subscribeOn 和 observeOn 的区别=====
     * https://segmentfault.com/a/1190000004856071（ subscribeOn 和 observeOn 的区别）
     * <p>
     * subscribeOn的调用切换之前的线程。
     * observeOn的调用切换之后的线程。
     * observeOn之后，不可再调用subscribeOn 切换线程
     * <p>
     * 只有第一subscribeOn() 起作用（所以多个 subscribeOn() 毛意义）
     * 这个 subscribeOn() 控制从流程开始的第一个操作，直到遇到第一个 observeOn()
     * <p>
     * observeOn() 可以使用多次，每个 observeOn() 将导致一次线程切换()，这次切换开始于这次 observeOn() 的下一个操作
     * <p>
     * 不论是 subscribeOn() 还是 observeOn()，后面"操作"线程将不再改变，不会自动切换到其他线程
     * 直到受到下一个 observeOn() 的干预
     */
    static void d10() {


    }

    //endregion

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


        //创建List发射器
        Observable.fromIterable(new ArrayList<String>()).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {

            }
        });
    }

    /**
     * timer
     */
    private void do3() {
        Flowable<Long> timer = Flowable.timer(3000, TimeUnit.MILLISECONDS);
        timer.subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Exception {
                Log.i("test", "打印" + aLong);
            }
        });

        //发送一个0L的数据在指定时间后
        Observable.timer(1, TimeUnit.SECONDS);
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
     * Disposable.subscribe 参数
     * =======Consumer 和 Observer 区别====
     * Observer有四个方法
     * Consumer 可以指定只处理其中的某几个方法
     * <p>
     * 有 Consumer 参数的 subscribe 返回一个 Disposable对象
     * 而 Observer 参数的 subscribe 无返回值 ， 而在 Observer 里的  onSubscribe方法返回 Disposable对象
     * <p>
     * Consumer里面其实包了一层 LambdaObserver ，然后还是调用 Observer参数的那个方法
     * LambdaObserver里面判断如果dispose了就不调用 onError onNext等回调了
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
     * https://mcxiaoke.gitbooks.io/rxdocs/content/operators/Retry.html
     * ==========区别=========
     * retry可以是遇到 Observable（被观察者） 调用onError，就立即重试调用 Observable ，使之重新发送数据源
     * 或者 有重试次数， 或者 一个 Predicate来判断是否重试
     * <p>
     * 而 retryWhen 接收一个可以发送 Throwable 的被观察者 Observable<Throwable> throwableObservable
     * 返回一个被观察者 ObservableSource<?> ，如果这个 ObservableSource 最终发射了 error，那么整体就走error
     * 如果发射了一个正常的数据源，那么会重试 最初的 Observable
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
        Observable.fromArray(2, 3, 4, 5)
                .flatMap(new Function<Integer, ObservableSource<Integer>>() {
                    @Override
                    public ObservableSource<Integer> apply(Integer integer) throws Exception {
                        System.out.println("apply=====" + integer);
                        if (integer - 2 == 0) {
                            throw new Exception("   xxxException");
                        }
                        return Observable.just(integer);
                    }
                })
                .retryWhen(new Function<Observable<Throwable>, ObservableSource<?>>() {
                    @Override
                    public ObservableSource<?> apply(Observable<Throwable> throwableObservable) throws Exception {

                        return throwableObservable.flatMap(new Function<Throwable, ObservableSource<?>>() {
                            @Override
                            public ObservableSource<?> apply(Throwable throwable) throws Exception {
                                System.out.println("retryWhen=====" + throwable);
                                return Observable.error(throwable);
                            }
                        });
                    }
                })
                .retry(1, new Predicate<Throwable>() {
                    @Override
                    public boolean test(Throwable throwable) throws Exception {
                        System.out.println("retry=====" + throwable);
                        //如果返回true，retry应该再次订阅和镜像原始的Observable，如果返回false，retry会将最新的一个onError通知传递给它的观察者。
                        return true;
                    }
                })
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


        //两个retryWhen
//        Observable.just(new CommonResponse())
//                .flatMap(new Function<CommonResponse, ObservableSource<CommonResponse>>() {
//                    @Override
//                    public ObservableSource<CommonResponse> apply(CommonResponse commonResponse) throws Exception {
//                        if (2 - 1 == 1) {
//                            System.out.println("retry=====");
//                            throw new IllegalAccessException("   xxxException");
//                        }
//                        return Observable.just(commonResponse);
//                    }
//                })
//                .retryWhen(new Function<Observable<Throwable>, ObservableSource<?>>() {
//                    @Override
//                    public ObservableSource<?> apply(Observable<Throwable> throwableObservable) throws Exception {
//                        return throwableObservable.flatMap(new Function<Throwable, ObservableSource<?>>() {
//                            @Override
//                            public ObservableSource<?> apply(Throwable throwable) throws Exception {
//                                System.out.println("抛出异常" + throwable);
//                                //token 过期
//                                if (throwable instanceof IllegalArgumentException) {
//                                    //处理过期后返回，再次请求
//                                    return Observable.just(new CommonResponse());
//                                }
//                                return Observable.error(throwable);
//                            }
//                        });
//                    }
//                })
//                .retryWhen(new Function<Observable<Throwable>, ObservableSource<?>>() {
//                    @Override
//                    public ObservableSource<?> apply(Observable<Throwable> throwableObservable) throws Exception {
//                        return throwableObservable.flatMap(new Function<Throwable, ObservableSource<?>>() {
//                            @Override
//                            public ObservableSource<?> apply(Throwable throwable) throws Exception {
//                                System.out.println("抛出异常" + throwable);
//                                //timestamp过期
//                                if (throwable instanceof IllegalAccessException) {
//                                    //处理过期后返回，再次请求
//                                    return Observable.just(new CommonResponse());
//                                }
//                                return Observable.error(throwable);
//                            }
//                        });
//                    }
//                })
//                .subscribe(callback);

    }

    /**
     * rxjava实现刷新token逻辑
     * <p>
     * https://mcxiaoke.gitbooks.io/rxdocs/content/operators/Retry.html
     */
    static void do9_1() {
        Observable.just(new CommonResponse())
                .flatMap(new Function<CommonResponse, ObservableSource<CommonResponse>>() {
                    @Override
                    public ObservableSource<CommonResponse> apply(CommonResponse commonResponse) throws Exception {
                        //如果过期，走error，走 retryWhen
                        if (commonResponse.error_code == 401) {
                            return Observable.error(new IllegalStateException());
                        }
                        return Observable.just(commonResponse);
                    }
                })
                .retryWhen(new Function<Observable<Throwable>, ObservableSource<?>>() {
                    @Override
                    public ObservableSource<?> apply(Observable<Throwable> throwableObservable) throws Exception {
                        //这里接收到一个 error
                        //如果这个 ObservableSource 发出一个 正常的item，那么会再次retry上面的Observable
                        //如果发出一个 error ，那么直接走下面的 onError
                        return throwableObservable
                                .flatMap(new Function<Throwable, ObservableSource<?>>() {
                                    @Override
                                    public ObservableSource<?> apply(Throwable throwable) throws Exception {
                                        //不是token过期的Exception ，直接走error
                                        if (!(throwable instanceof IllegalStateException)) {
                                            return Observable.error(throwable);
                                        }
                                        //如果正在请求，等待一秒再次请求接口
//                                        return Observable.timer(1000, TimeUnit.MILLISECONDS);

                                        //请求token的  Observable
                                        return Observable.timer(1000, TimeUnit.MILLISECONDS);
                                    }
                                });


                    }
                }).subscribe(new Observer<CommonResponse>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(CommonResponse commonResponse) {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }


    static void do9_2() {
        /*
         * 打印结果
         * apply=====
         * subscribing
         * delay retry by 1 second(s)
         * subscribing
         * delay retry by 2 second(s)
         * subscribing
         * delay retry by 3 second(s)
         * subscribing
         *
         *
         * 很明显 retryWhen 中只走了一次，也就是后面 Throwable源 转化为 Timer源，然后到重新订阅Observable.timer(1, TimeUnit.SECONDS)
         * 然后抛出异常，然后又是之前的 Observable<Throwable> 源再次发出一另一个 Throwable对象
         * 所以 Observable<Throwable> 这个数据源能连续发出不同 的 Throwable对象（因为你 throw new RuntimeException();）
         *
         *
         *
         * */
//        Observable.timer(1, TimeUnit.SECONDS)
//                .doOnSubscribe(s -> System.out.println("subscribing"))
//                .map(v -> {
//                    throw new RuntimeException();
//                })
//                .retryWhen(errors -> {
//                    AtomicInteger counter = new AtomicInteger();
//                    return errors
//                            .takeWhile(e -> counter.getAndIncrement() != 3)
//                            .flatMap(e -> {
//                                System.out.println("delay retry by " + counter.get() + " second(s)");
//                                //这里只是发出一个数据，然后调用完成
//                                return Observable.timer(counter.get(), TimeUnit.SECONDS);
//                            });
//                })
//                .blockingSubscribe(System.out::println, System.out::println);
//


//        int i = 0;
//        if(i==0){
//            return;
//        }


        Observable.just("请求结果")
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        System.out.println("=====doOnSubscribe" + disposable);
                    }
                })
                .map(v -> {
                    throw new RuntimeException();
                })
                .retryWhen(new Function<Observable<Throwable>, ObservableSource<?>>() {
                    @Override
                    public ObservableSource<?> apply(Observable<Throwable> throwableObservable) throws Exception {
                        System.out.println("apply=====");
                        AtomicInteger counter = new AtomicInteger();
                        return throwableObservable
                                .takeWhile(e -> counter.getAndIncrement() != 3)
                                .flatMap(e -> {
                                    System.out.println("delay retry by " + counter.get() + " second(s)" + e.hashCode());
                                    //模拟刷新token，这里用timer模拟不行，就得用这个
                                    return Observable
                                            .just("新的token")
                                            .doOnNext(new Consumer<String>() {
                                                @Override
                                                public void accept(String aLong) throws Exception {
                                                    System.out.println("doOnNext " + aLong);

                                                    //模拟 刷新接口失败
                                                    throw new RuntimeException("刷新接口失败");
                                                }
                                            }).doOnError(new Consumer<Throwable>() {
                                                @Override
                                                public void accept(Throwable throwable) throws Exception {
                                                    System.out.println("doOnError " + throwable.getMessage());
                                                }
                                            });
                                });
                    }
                })
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        System.out.println("=====onSubscribe" + d);
                    }

                    @Override
                    public void onNext(Object integer) {
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

    //endregion


    //region 条件和布尔操作

    /**
     * ============takeWhile================
     * <p>
     * TakeWhile发射原始Observable，直到你指定的某个条件不成立的那一刻，它停止发射原始Observable，并终止自己的Observable
     */
    static void do10() {

        /*
         *
         * =====onSubscribe0
         * =====flatMap原始数据源0
         * =====onNext1
         * =====flatMap原始数据源1
         * =====onNext2
         * =====flatMap原始数据源2
         * =====onNext3
         * =====onComplete
         *
         * 最后调用onComplete，这个最后直接走最外层的onComplete 了，根本没走
         * */
        AtomicInteger counter = new AtomicInteger();
        Observable.just("原始数据源0", "原始数据源1", "原始数据源2", "原始数据源3", "原始数据源4")
                .takeWhile(new Predicate<String>() {
                    @Override
                    public boolean test(String s) throws Exception {
                        return counter.getAndIncrement() != 3;
                    }
                })
                .flatMap(new Function<String, ObservableSource<Integer>>() {
                    @Override
                    public ObservableSource<Integer> apply(String s) throws Exception {
                        System.out.println("=====flatMap" + s);
                        return Observable.just(counter.get());
                    }
                }).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                System.out.println("=====onSubscribe" + d);
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

    //endregion

    /**
     * 分组（发射） groupby操作符
     * <p>
     * 实际应用，比如有个list，我们要给item根据level分组，
     * 而且要知道每个item相对于本组的index，和本组的totalSize
     * groupedObservable的Observer创建数组，onNext 赋值index
     * onComplete遍历赋值totalSize
     */
    //region 变换操作
    static void do11() {


        ArrayList<AbstractMap.SimpleEntry<String, Integer>> list = new ArrayList<>();
        list.add(new AbstractMap.SimpleEntry("A", 1));
        list.add(new AbstractMap.SimpleEntry("B", 2));
        list.add(new AbstractMap.SimpleEntry("C", 3));
        list.add(new AbstractMap.SimpleEntry("D", 1));
        list.add(new AbstractMap.SimpleEntry("E", 2));
        list.add(new AbstractMap.SimpleEntry("F", 3));


        Observable.fromIterable(list)
                .groupBy(new Function<AbstractMap.SimpleEntry<String, Integer>, Integer>() {
                    @Override
                    public Integer apply(AbstractMap.SimpleEntry<String, Integer> simpleEntry) throws Exception {
                        return simpleEntry.getValue();
                    }
                }).subscribe(new Observer<GroupedObservable<Integer, AbstractMap.SimpleEntry<String, Integer>>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(GroupedObservable<Integer, AbstractMap.SimpleEntry<String, Integer>> groupedObservable) {

                //这里总共会走三次，1，2，3 ，上面发射一个新的种类，那么这里就会新来一个，只会来一次
                //后面如果有重复的元素，那么直接就发射到之前创建过的groupedObservable中了
                System.out.println("out=======" + groupedObservable.getKey());

                groupedObservable.subscribe(new Observer<AbstractMap.SimpleEntry<String, Integer>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(AbstractMap.SimpleEntry<String, Integer> entry) {
                        System.out.println("in========" + entry.getValue());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        System.out.println("onComplete========" + groupedObservable.getKey());
                    }
                });
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                System.out.println("onComplete========");
            }
        });


        /*
         * 运行结果
         * out=======1
         * in========1
         * out=======2
         * in========2
         * out=======3
         * in========3
         * in========1
         * in========2
         * in========3
         * onComplete========1
         * onComplete========2
         * onComplete========3
         * onComplete========
         * */
    }

    //endregion

}
