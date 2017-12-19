package com.liyafeng.practice;

/**
 * Created by liyafeng on 16/11/2017.
 */

public class Java {


    interface a {
        public static int b = 1;

        public abstract void aa();
    }

    static abstract class b {
        int a = 1;

        public void a() {

        }

        protected abstract void b();
    }

    /**
     * 1.接口和抽象类的区别
     * http://www.importnew.com/12399.html
     */
    void a1() {
        /*
        * 从微观上说
        * 一个类能实现多个接口，但只能继承一个抽象类
        * 接口中只能有公有的抽象方法,而抽象类中可以有抽象方法，也可以没有抽象方法，也可以有普通方法
        * 接口中只能定义静态成员变量，而抽象类中可以定义非静态成员变量
        *
        * 相同点是他们都不能被实例化
        *
        * 从宏观上说
        * 定义抽象类是为了捕捉子类的通用特性，通用的实现逻辑可以在抽象类中完成，而接口中不能有逻辑
        * 而接口定义的是一种约定，实现这个接口就代表实现了接口中方法提供的服务
        *
        *
        *
        * */
    }


    /**
     * =====================
     * ### 线程
     * 推荐阅读书籍《Java并发编程实战》
     * =====================
     * */

    /**
     * sleep和wait有什么区别
     */
    public void a2() {
        /*
        * 首先线程有6种状态，new waiting runnable blocked ,timed waiting ,terminated
        * 见下图
        *
        *
        */

        int thread_status = R.drawable.thread_status;
    }

    public static void main(String[] args) {

    }
}
