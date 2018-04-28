package com.liyafeng.event.eventbus;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.concurrent.Executors;

/**
 * Created by liyafeng on 2018/4/28.
 */

public class Main {

    /**
     * 事件总线
     * https://github.com/greenrobot/EventBus
     * http://greenrobot.org/eventbus/documentation/
     * =============使用方法===========
     * 1.必须要定义事件对象
     * 2.注册和反注册
     * 3.在注册的类的处理方法上加上@Subscribe注解，参数列表中加上对应的事件对象类型
     *    注意，这个方法只能有一个参数，否则注册的时候抛出异常
     *     throw new EventBusException("@Subscribe method " + methodName + must have exactly 1 parameter but has " + parameterTypes.length);
     *     而且签名必须是public的，非static，非abstract，否则抛出异常
     * 4.post指定的事件对象，参数列表中有这个事件对象类型的方法会被调用
     *
     * ===============原理==========================
     * 1 首先注册观察者（subscriber订阅者），通过getDeclaredMethods();获取观察者的所有方法
     *  遍历所有方法，通过方法来找出所有有@Subscribe注解的方法
     *   Subscribe subscribeAnnotation = method.getAnnotation(Subscribe.class);
     *  通过 Class<?>[] parameterTypes= method.getParameterTypes();获取参数类型
     *  将Method对象(value)和Event对象(key)放入map
     *  然后返回List<Method>，这里的方法对象都是有@Subscribe注解的
     *      然后开始注册，Map<Event,CopyOnWriteArrayList<Subscription>>，
     *  先从map中取出事件对应的List<Subscription>，然后将Subscription加入里面
     *  还有一个Map<Object, List<Class<?>>><订阅者，订阅的事件列表>，这个主要作用
     *  是提高反注册时的效率，我们只需要取出这个订阅者所有的订阅事件，找到对应事件的订阅者列表
     *  fori来比对移除即可。
     *  2.当我们post一个事件，直接从Map<Event,CopyOnWriteArrayList<Subscription>>取出相应的
     *  订阅者列表，依次通过反射来调用他们对应的方法即可。
     *
     *  EventBus切换到主线程用的也是handler
     *  切换到后台线程用的线程池 Executors.newCachedThreadPool()//可以开int最大值个线程，最多存活60秒
     *  后台模式如果当前是子线程，那么直接执行，不新分配线程
     *  异步模式是开一个新的线程
     *
     * @param args
     */
    public static void main(String[] args) {
        EventBus.getDefault().post(null);
    }

    public void init() {
        EventBus.getDefault().register(this);
    }

    @Subscribe(threadMode = ThreadMode.ASYNC, sticky = true, priority = 5)
    public void foo() {

    }

    public void onDestory() {
        EventBus.getDefault().unregister(this);
    }
}
