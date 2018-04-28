package com.liyafeng.designpattern.structural.adapter;

/**
 * Created by liyafeng on 2018/4/28.
 */

public class Main {

    /**
     * 适配器模式，是结构型设计模式
     * 适配器模式的目的是将两个不兼容的 接口进行适配
     * 就像插座只有一种220v的，但是电器需要5v 100v的，我们通过适配器来达到插座通用的目的
     *
     * 适配器有两种实现模式，
     * 一种是对象适配器，就是被适配者从构造方法传入适配器
     * 适配器并不实现被适配者的接口
     * 一种是类适配器
     * 适配器实现被适配者的接口，这时它既是适配器也是被适配者
     *
     * 优点：
     * 将目标类和适配者类解耦，通过引入一个适配器类来重用现有的适配者类，而无须修改原有代码
     * 符合开闭原则
     *
     * @param args
     */
    public static void main(String[] args) {

        Target target =new ObjectAdapter(new Adaptee());
        target.request();

        Target target1 =new ClassAdapter();
    }

    interface Target{
        void request();
    }

    static class ObjectAdapter implements Target{
        Adaptee adaptee;

        public ObjectAdapter(Adaptee adaptee) {
            this.adaptee = adaptee;
        }

        @Override
        public void request() {
            adaptee.realRequest();
        }
    }

    static class Adaptee{
        void realRequest(){
            //真正的请求实现
        }
    }


    /**
     * 类适配器，既是适配器又是被适配者
     */
    static class ClassAdapter extends Adaptee implements Target{
        @Override
        public void request() {
            realRequest();
        }
    }
}
