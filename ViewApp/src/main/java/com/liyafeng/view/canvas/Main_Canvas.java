package com.liyafeng.view.canvas;

public class Main_Canvas {


    /**
     * canvas.clipRect
     * https://www.jianshu.com/p/550d85419121 (Android画布剪裁函数clipRect详解)
     * 这个剪裁只是把内容留下，而画布大小还是一样的
     *
     * android p 开始只支持 Region.Op.INTERSECT  Region.Op.DIFFERENCE 两种模式
     *    if (sCompatiblityVersion >= Build.VERSION_CODES.P
     *                 && op != Region.Op.INTERSECT && op != Region.Op.DIFFERENCE) {
     *             throw new IllegalArgumentException(
     *                     "Invalid Region.Op - only INTERSECT and DIFFERENCE are allowed");
     *     }
     */
    void a1() {
    }
}