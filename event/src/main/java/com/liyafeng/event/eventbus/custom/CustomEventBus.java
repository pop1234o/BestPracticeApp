package com.liyafeng.event.eventbus.custom;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * Created by liyafeng on 17/10/2017.
 * <p>
 * 单例
 * pool
 * <p>
 * 事件class - 订阅者list  = map
 * ======================
 * 反注册的时候还需要一个  订阅者 - 事件list 的map
 * <p>
 * ==============
 * 考虑线程问题，并发问题
 * 引入poster的概念
 * <p>
 * ==============
 * 在同一个线程中顺序执行？？？？
 * <p>
 * ===============
 * 还考虑了事件拦截
 * 当到某一个方法的时候，我可以进行拦截，那么该事件就不会向下传递了
 * 这个是在循环中判断标志位
 * <p>
 * ==============
 * 这里还考虑到了如果event没人监听，那么就发一个通用的event
 * 你可以监听到这个event，以备后续处理
 * <p>
 * ==============
 * 自定义了一个队列和消息对象，消息对象实现重用，队列实现阻塞
 * ===============
 *
 *
 */

public class CustomEventBus {

    private final HashMap<Class, List<Subscribion>> subscribionsByEventType;
    private final HashMap<Object, List<Class>> eventtypesBySubscribe;
    private final HandlerPoster handlerPoster;
    private final BackgroundPoster backgroundPoster;
    private final ExecutorService executorService;

    static class Holder {
        static CustomEventBus bus = new CustomEventBus();
    }

    SubscriberMethodFinder methodFinder;

    private CustomEventBus() {
        methodFinder = new SubscriberMethodFinder();
        subscribionsByEventType = new HashMap<>();
        eventtypesBySubscribe = new HashMap<>();
        handlerPoster = new HandlerPoster(Looper.getMainLooper(), this);
        backgroundPoster = new BackgroundPoster(this);
        executorService = Executors.newCachedThreadPool();
    }

    public static CustomEventBus getDefault() {
        return Holder.bus;
    }

    public void register(Object subscriber) {
        //找到这个类中带有对应注解的 方法对象 的列表
        List<SubscriberMethod> methodList = methodFinder.findMethods(subscriber);


        for (SubscriberMethod method : methodList) {

            subscribe(subscriber, method);
        }

    }

    public void unregister(Object subscriber) {
        List<Class> classes = eventtypesBySubscribe.get(subscriber);
        for (Class aClass : classes) {
            List<Subscribion> subscribions = subscribionsByEventType.get(aClass);
            ListIterator<Subscribion> iterator = subscribions.listIterator();
            while (iterator.hasNext()) {
                Subscribion next = iterator.next();
                if (next.subscriber == subscriber) {
                    iterator.remove();
                }
            }
        }
    }

    public void post(Object o) {
        Class<?> aClass = o.getClass();
        List<Subscribion> subscribions = subscribionsByEventType.get(aClass);
        for (int i = 0; i < subscribions.size(); i++) {
            Subscribion subscribion = subscribions.get(i);
            postToSubscription(o, subscribion);
        }
    }

    /**
     * 线程判断
     *
     * @param o
     * @param subscribion
     */
    private void postToSubscription(Object o, Subscribion subscribion) {
        ThreadMode threadMode = subscribion.method.threadMode;
        switch (threadMode) {
            case POSTING:
                invokeSubscriber(o, subscribion);
                break;
            case MAIN:
                if (Looper.myLooper() == Looper.getMainLooper()) {//当前是主线程
                    invokeSubscriber(o, subscribion);
                } else {
                    handlerPoster.enqueue(subscribion, o);
                }
                break;
            case BACKGROUND:
                if (Looper.myLooper() != Looper.getMainLooper()) {
                    invokeSubscriber(o, subscribion);
                } else {
                    backgroundPoster.enqueue(subscribion, o);
                }
                break;
            case ASYNC:
                break;
            default:
        }

    }

    /**
     * i这样每次都不用创建runnable对象了
     */
    static class BackgroundPoster implements Runnable {

        private final PendPostQueue queue;
        private CustomEventBus eventBus;

        public BackgroundPoster(CustomEventBus eventBus) {
            this.eventBus = eventBus;
            queue = new PendPostQueue();
        }

        public void enqueue(Subscribion subscribion, Object o) {
            queue.enqueue(PendPostQueue.PendingPost.obtain(subscribion, o));
            eventBus.getExecutor().execute(this);
        }

        @Override
        public void run() {

            try {
                while (true) {

                    PendPostQueue.PendingPost poll = queue.poll(1000);
                    if(poll==null){
                        return;
                    }
                    eventBus.invokeSubscriber(poll.o, poll.subscribion);
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private Executor getExecutor() {
        return executorService;
    }


    /**
     * post到主线程，里面定义的自己的队列
     */
    static class HandlerPoster extends Handler {

        private final CustomEventBus eventBus;
        PendPostQueue queue;

        public HandlerPoster(Looper looper, CustomEventBus eventBus) {
            super(looper);
            this.eventBus = eventBus;
            queue = new PendPostQueue();
        }

        @Override
        public void handleMessage(Message msg) {
            PendPostQueue.PendingPost poll = queue.poll();

            eventBus.invokeSubscriber(poll.o, poll.subscribion);

            poll.release();
        }

        public void enqueue(Subscribion subscribion, Object o) {
            queue.enqueue(PendPostQueue.PendingPost.obtain(subscribion, o));
            sendEmptyMessage(0);
        }
    }

    /**
     * 一个内部的消息队列
     * <p>
     * 这里还可以实现一个等待的消息队列
     * 取的时候如果没有就wait，然后enqueue的时候notify
     */
    static class PendPostQueue {
        PendingPost head;
        PendingPost tail;

        public synchronized void enqueue(PendingPost post) {
            if (head == null) {
                head = tail = post;
                return;
            }
            tail.next = post;
            tail = post;
            notifyAll();
        }

        public synchronized PendingPost poll() {

            PendingPost post = this.head;
            if (head != null) {//考虑到队列为null
                head = head.next;
                if (head == null) {//考虑到队列为null
                    tail = null;
                }
            }
            return post;

        }

        public synchronized PendingPost poll(int waitMillis) throws InterruptedException {
            if (head == null) {
                wait(waitMillis);
            }
            return poll();
        }

        /**
         * 这里要实现重用和回收
         */
        static class PendingPost {
            static ArrayList<PendingPost> postPool = new ArrayList<>();
            Subscribion subscribion;
            Object o;
            PendingPost next;

            private PendingPost(Subscribion subscribion, Object o) {
                this.subscribion = subscribion;
                this.o = o;
            }

            public static PendingPost obtain(Subscribion subscribion, Object o) {
                int size = postPool.size();
                if (size > 0) {
                    PendingPost remove = postPool.remove(size - 1);//这里不能用0，否则数组每次都创建
                    remove.subscribion = subscribion;
                    remove.o = o;
                    return remove;
                }
                return new PendingPost(subscribion, o);
            }

            public void release() {
                subscribion = null;
                o = null;
                next = null;
                if (postPool.size() < 100) {//防止过大
                    postPool.add(this);
                }
            }

        }
    }


    private void invokeSubscriber(Object o, Subscribion subscribion) {
        try {
            subscribion.method.method.invoke(subscribion.subscriber, o);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }


    private void subscribe(Object subscriber, SubscriberMethod method) {

        Class<?> eventType = method.eventType;


        //这个事件对应的观察者 们
        List<Subscribion> subscribions = subscribionsByEventType.get(eventType);
        if (subscribions == null) {
            subscribions = new ArrayList<>();
            subscribionsByEventType.put(eventType, subscribions);
        }

        Subscribion subscribion = new Subscribion(subscriber, method);
        subscribions.add(subscribion);


        //这个在解绑的时候有用
        List<Class> classes = eventtypesBySubscribe.get(subscriber);

        if (classes == null) {
            classes = new ArrayList<>();
            eventtypesBySubscribe.put(subscriber, classes);
        }

        classes.add(eventType);

    }

    /**
     * 一个订阅者
     */
    static class Subscribion {

        private final Object subscriber;
        private final SubscriberMethod method;

        public Subscribion(Object subscriber, SubscriberMethod method) {

            this.subscriber = subscriber;
            this.method = method;
        }
    }


    /**
     * 查找器
     */
    static class SubscriberMethodFinder {

        /**
         * 这里设计pool是为了可能会有频繁的register操作，一直创建对象占用内存而且慢
         */
        public static FindState[] FIND_STATE_POOL = new FindState[4];

        public List<SubscriberMethod> findMethods(Object subscriber) {
            Class<?> aClass = subscriber.getClass();

            //准备findState ，从pool中取
            FindState findState = prepareFindState();

            //初始化findState
            findState.initForSubscriber(aClass);

            //查找
            if (findState.aClass != null) {
                findUsingReflectionInSingleClass(findState);
            }


            //返回list，释放state
            return getMethodsAndRelease(findState);

        }

        /**
         * 回收
         *
         * @param findState
         * @return
         */
        private List<SubscriberMethod> getMethodsAndRelease(FindState findState) {
            ArrayList<SubscriberMethod> methodArrayList = new ArrayList<>(findState.methodList);
            findState.recycle();
            for (int i = 0; i < FIND_STATE_POOL.length; i++) {
                if (FIND_STATE_POOL[i] == null) {
                    FIND_STATE_POOL[i] = findState;
                    break;
                }
            }
            return methodArrayList;
        }

        private FindState prepareFindState() {
            for (int i = 0; i < FIND_STATE_POOL.length; i++) {
                FindState findState = FIND_STATE_POOL[i];
                if (findState != null) {
                    FIND_STATE_POOL[i] = null;//移出pool
                    return findState;
                }
            }
            return new FindState();
        }

        private void findUsingReflectionInSingleClass(FindState findState) {
            Method[] methods = findState.aClass.getDeclaredMethods();

            for (Method method : methods) {
                CustomSubscribe annotation = method.getAnnotation(CustomSubscribe.class);
                if (annotation != null) {
                    Class<?>[] parameterTypes = method.getParameterTypes();
                    if (parameterTypes.length == 1) {

                        ThreadMode threadMode = annotation.threadMode();
                        Class<?> parameterType = parameterTypes[0];//这个是方法的参数

                        SubscriberMethod subscriberMethod = new SubscriberMethod(threadMode, parameterType, method);

                        findState.methodList.add(subscriberMethod);
                    }

                }

            }
        }
    }


    /**
     * 一个类中有注解的方法 对象
     */
    static class SubscriberMethod {

        private final ThreadMode threadMode;
        private final Class<?> eventType;
        private Method method;

        public SubscriberMethod(ThreadMode threadMode, Class<?> eventType, Method method) {

            this.threadMode = threadMode;
            this.eventType = eventType;
            this.method = method;
        }
    }


    /**
     * 查找状态，找到的list<method>就在这里
     */
    static class FindState {

        private Class<?> aClass;
        public List<SubscriberMethod> methodList = new ArrayList<>();

        public void initForSubscriber(Class<?> aClass) {

            this.aClass = aClass;
        }

        public void recycle() {
            aClass = null;
            methodList.clear();
            methodList = null;
        }
    }

    public enum ThreadMode {
        POSTING,
        MAIN,
        BACKGROUND,
        ASYNC

    }
}
