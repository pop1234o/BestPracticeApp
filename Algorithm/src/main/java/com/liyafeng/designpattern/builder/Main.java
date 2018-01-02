package com.liyafeng.designpattern.builder;

/**
 * Created by liyafeng on 2018/1/2.
 */

public class Main {

    /**
     * @param args
     * Build是内部类，最后build中new Entity(builder);
     * 然后将属性赋值
     * -------------------
     * 这种模式可以免去 实体类 配置的具体过程
     *
     * 比如某个方法必须要在另一个之前调用，那么这种模式用户不必知道调用顺序
     * 还比如new的时候必须有个默认值，那么builder中可以设置默认值
     * ----------------------
     * 而且一个builder可以创建多个Entity，这样如果修改多个Entity的属性的时候
     * 只需要修改Builder属性即可。提高可维护性
     */
    public static void main(String[] args) {

    }
}
