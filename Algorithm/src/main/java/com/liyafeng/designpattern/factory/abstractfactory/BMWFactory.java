package com.liyafeng.designpattern.factory.abstractfactory;

/**
 * Created by lenovo on 2017/12/20.
 */

public class BMWFactory extends AbstractFactory {

    @Override
    public Bus createBus() {
        return new BMWBus();
    }

    @Override
    public Suv createSuv() {
        return new BMWSuv();
    }
}
