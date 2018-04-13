package com.liyafeng.designpattern.creational.factory.simple;

/**
 * Created by lenovo on 2017/12/20.
 */

public class Main {
    public static void main(String[] args) {

        Product product = new SimpleFactory().createProduct(SimpleFactory.TYPE_PRODUCT_A);

    }
}
