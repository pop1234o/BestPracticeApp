package com.liyafeng.designpattern.factory.normal;

/**
 * Created by lenovo on 2017/12/20.
 */

public class NormalFactory extends Factory{

    @Override
    public Product createProduct() {
        return new ProductA();
    }
}
