package com.liyafeng.architecture.mvp;

/**
 * Created by liyafeng on 2018/1/9.
 */

public class Main_MVP {
    /**
     * @param args
     * mvp真正实现了业务逻辑和 UI逻辑的解耦
     * 与UI无关的都在presenter中，业务表现层
     * UI显示的都用接口方式回调
     * ----------------
     * 这里的 activity implement view，activity就相当于view，
     * 而mvc中的 activity相当于controller
     *
     *
     * =========与mvc的区别===========
     * 以前是数据在model中直接返回给view进行更新，
     * 而现在数据先返回到presenter中，然后由presenter持有的view进行更新
     *
     *
     *
     */
    public static void main(String[] args) {

    }
}
