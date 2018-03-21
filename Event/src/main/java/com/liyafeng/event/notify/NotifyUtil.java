package com.liyafeng.event.notify;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by liyafeng on 2018/3/21.
 */

public class NotifyUtil extends Observable {
    private static NotifyUtil instance = new NotifyUtil();

    private NotifyUtil() {
    }

    public static NotifyUtil getInstance() {
        return instance;
    }

    public void register(Observer observer){
        addObserver(observer);
    }

    public void unRegister(Observer observer){
        deleteObserver(observer);
    }

    public void notify(Event event){
        setChanged();
        notifyObservers(event);
    }
}
