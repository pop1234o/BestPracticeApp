package com.liyafeng.designpattern.proxy;

/**
 * Created by liyafeng on 2018/3/14.
 */

public class Main {

    /**
     * 代理模式 （Prosy Pattern）
     * 代理类，委托类（被代理类）
     * =========================
     * 优点：
     * 协调调用者和被调用者，从而实现解耦
     * 保护真实对象的使用权限
     * 缺点：
     * 调用速度变慢
     * =============场景===========
     * 主要用于在委托类执行方法之前做一些预处理工作
     * 或者在执行之后做一些处理
     *
     * @param args
     */
    public static void main(String[] args) {
        Subject subject = new SubjectProxy(new RealSubject());
        subject.request();

    }


    public interface Subject {
        void request();
    }

    public static class RealSubject implements Subject {

        @Override
        public void request() {
            System.out.println("do request");
        }
    }

    public static class SubjectProxy implements Subject {

        private RealSubject realSubject;

        public SubjectProxy(RealSubject realSubject) {
            this.realSubject = realSubject;
        }

        @Override
        public void request() {
            preRequest();
            realSubject.request();
        }

        public void preRequest() {
            System.out.println("do pre");
        }
    }
}
