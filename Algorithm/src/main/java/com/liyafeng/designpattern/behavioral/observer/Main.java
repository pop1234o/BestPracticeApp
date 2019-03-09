package com.liyafeng.designpattern.behavioral.observer;

import java.util.ArrayList;

/**
 * Created by liyafeng on 2018/4/13.
 */

public class Main {

    /**
     * 被观察者 持有 观察者对象 的集合
     *
     * Observable-Observer（update 收到消息）
     *
     * Subscriber-Publisher
     * 订阅者接收消息，发布者发布消息
     * 订阅者收到订阅的消息 onSubscribe（Subscription）
     *
     *
     * @param args
     */
    public static void main(String[] args) {
        Observable observable = new Observable();

        //被观察者添加观察者
        observable.addObserver(new Observer() {
            @Override
            public void update(Observable o, Object arg) {
                //收到被观察者发来的通知
                System.out.println(arg);
            }
        });

        observable.notifyObservers(1);
    }


    public static class Observable {

        ArrayList<Observer> observers = new ArrayList<>();

        public void addObserver(Observer observer) {
            observers.add(observer);
        }

        public void deleteObserver(Observer observer) {
            observers.remove(observer);
        }

        public void notifyObservers(Object arg) {
            for (Observer observer : observers) {
                observer.update(this, arg);
            }
        }
    }

    public static interface Observer {

        public void update(Observable o, Object arg);
    }
}
