package com.liyafeng.practice;

/**
 * Created by liyafeng on 2018/3/13.
 */

public class Main {

    /**
     *
     * 猿酒馆面试题
     * https://github.com/JackyAndroid/AndroidInterview-Q-A/tree/master/chinese
     *
     *
     * ===============================
     * 如何阅读代码
     * 首先，他们写的代码肯定要遵循设计模式6大原则，他们的类设计一定是单一职责的
     * 所以我们看一个类，先看一下注释和方法名，和里面的方法，知道这个类的职责是什么
     *      然后是开闭原则，里式替换，依赖倒置，所以他们每个模块肯定会设计抽象类或者接口
     * 然后这些接口肯定有子类，就是具体功能的实现（或者不同功能的实现）。而且各个模块之间
     * 肯定是依赖抽象（就是持有其他模块的接口)
     *      迪米特法则，所以如果模块过多，肯定会有一个Manager类，来协调各个模块之间的关系
     * 避免他们之间过度依赖，就像EventBus.class Volley.class Picasso.class
     *      接口隔离，他们设计的接口肯定是不能再分了，里面没有多余的方法
     *      通过这6个模式的分析，我们能知道一个项目大体有几个模块，每个模块负责什么
     *      然后他们里面肯定用了23种设计模式。所以我们根据模式的作用，也知道他的逻辑
     *
     * @param args
     */
    public static void main(String[] args) {

    }
}
