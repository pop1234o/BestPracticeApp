package com.liyafeng.designpattern.creational.factory;

/**
 * Created by lenovo on 2017/12/20.
 */

public class Main {

    /**
     * 简单工厂是一个工厂对象产生多种产品，这些产品都有共同的父类（工厂没有接口类）
     * 普通工厂是一个工厂产生一类产品，这个工厂有个抽象父类
     * 抽象工厂是多个工厂产生多类产品
     *
     * 实际上这几个工厂之间可以互相转换，普通工厂再多加一个方法一个产品就是抽象工厂了
     *
     * 简单工厂之所以简单是因为没有抽象父类
     * 普通工厂有抽象父类
     * 抽象工厂是可以生产多个产品
     * @param args
     */
    public static void main(String[] args) {

    }
}
