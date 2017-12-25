package com.liyafeng.concurrent.util;

import com.liyafeng.concurrent.taskentity.Callable;
import com.liyafeng.concurrent.taskentity.Runnable;

/**
 * Created by lenovo on 2017/12/21.
 */

public class Executors {
    public static <V> Callable<V> callable(Runnable task, V value) {
        return new RunnableAdapter<V>(task,value);
    }

    public final static class RunnableAdapter<V> implements Callable<V>{

        private final Runnable task;
        private final V value;

        public RunnableAdapter(Runnable task, V value) {

            this.task = task;
            this.value = value;
        }

        @Override
        public V call() throws Exception {
            task.run();
            return value;
        }
    }
}
