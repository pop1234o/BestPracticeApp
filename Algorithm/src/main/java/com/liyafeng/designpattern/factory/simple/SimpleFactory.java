package com.liyafeng.designpattern.factory.simple;

/**
 * Created by lenovo on 2017/12/20.
 */

public class SimpleFactory {

    public static final int TYPE_PRODUCT_A = 1;
    public static final int TYPE_PRODUCT_B = 2;

    public Product createProduct(int product) {
        switch (product) {
            case TYPE_PRODUCT_A:
                return new ProductA();
            case TYPE_PRODUCT_B:
                return new ProductB();

        }

        return null;

    }
}
