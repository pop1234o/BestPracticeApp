package com.liyafeng.designpattern.factory.abstractfactory;

/**
 * Created by lenovo on 2017/12/20.
 */

public class Main {
    public static void main(String[] args) {

        AbstractFactory factory =new BMWFactory();
        Bus bus = factory.createBus();
        Suv suv = factory.createSuv();

    }
}
