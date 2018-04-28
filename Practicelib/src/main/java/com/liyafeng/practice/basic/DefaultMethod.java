package com.liyafeng.practice.basic;

/**
 * Created by liyafeng on 2018/4/28.
 */

public interface DefaultMethod {

    default void foo(){
        System.out.println("");
    }
}
class A implements DefaultMethod{
    @Override
    public void foo() {
        DefaultMethod.super.foo();
    }
}
