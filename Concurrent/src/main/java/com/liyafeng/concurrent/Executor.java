package com.liyafeng.concurrent;

/**
 * Created by lenovo on 2017/12/21.
 *
 * 这个接口代表一个执行任务的特性
 * 接口隔离原则，实现这个借口的类，要提供执行Runnable的实现
 */

public interface Executor {
    void execute(Runnable command);
}
