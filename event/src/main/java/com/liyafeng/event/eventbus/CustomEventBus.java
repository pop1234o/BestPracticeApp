package com.liyafeng.event.eventbus;

import java.lang.reflect.Method;
import java.util.List;

/**
 * Created by liyafeng on 17/10/2017.
 * <p>
 * 单例
 */

public class CustomEventBus {

    static class Holder {
        static CustomEventBus bus = new CustomEventBus();
    }

    SubscriberMethodFinder methodFinder;
    private CustomEventBus() {
        methodFinder = new SubscriberMethodFinder();
    }

    public static CustomEventBus getDefault() {
        return Holder.bus;
    }

    public void register(Object subscriber){
       List<SubscriberMethod> methodList = methodFinder.findMethods(subscriber);
    }


    class SubscriberMethodFinder {

        public List<SubscriberMethod> findMethods(Object subscriber) {
            Class<?> aClass = subscriber.getClass();
            Method[] methods = aClass.getDeclaredMethods();

            for (Method method: methods ) {
                method.getAnnotation()
            }
            return null;

        }
    }

    class SubscriberMethod{

    }


}
