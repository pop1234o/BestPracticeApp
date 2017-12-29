package com.liyafeng.dependencyinjection;

/**
 * Created by liyafeng on 2017/12/29.
 */

public class Main {

    /**
     * @param args
     * 依赖注入
     * https://www.jianshu.com/p/39d1df6c877d
     * -------------
     * dependency injection
     * 依赖就是一个类中持有另一个类的引用。就是字段
     * 这个引用指向的对象通过什么方式new出来呢？
     * 可以直接在这个类中new出另一个类。但是这违背了单一职责的原则
     * 所以我们要通过外部将对象传进来，可以通过构造方法，set方法，注解
     * 来 “注入”。
     * 我们经常用View v = findViewById(R.id.view);来对将v指向对象，
     * v就是“依赖”，就是引用的意思
     * 但是这样的代码太多，降低编写效率，所以我们用 注解的方式来注入
     * 这就是butterKnife的作用，用注解来替换繁琐的findViewById
     * --------------------------------
     *
     *
     *
     */
    public static void main(String[] args) {

    }
}
