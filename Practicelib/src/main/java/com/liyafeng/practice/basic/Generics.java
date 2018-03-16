package com.liyafeng.practice.basic;

/**
 * Created by liyafeng on 2018/2/22.
 *
 * 泛型：泛型类，泛型接口，泛型方法
 */

public class Generics<E extends Comparable<E>> {//这个就定义了这个泛型类，可以在类中使用

    public E getKey() {
        return key;
    }

    public void setKey(E key) {
        this.key = key;
    }

    E key;

    public Generics(E key) {
        this.key = key;
    }

    /**
     * 这个是泛型方法，必须要在访问限制修饰符和返回值之间，和static后
     * @param <T>
     */
    public static  <T> void  function(){

    }

    /**
     * 泛型接口
     * @param <K>
     */
    public static interface Inter<K>{
        K dispose(K key);
    }
}
