package com.liyafeng.designpattern.factory.abstractfactory;

/**
 * Created by lenovo on 2017/12/20.
 */

public class AudiFactory  extends AbstractFactory{

    @Override
    public Bus createBus() {
        return new AudiBus();
    }

    @Override
    public Suv createSuv() {
        return new AudiSuv();
    }
}
