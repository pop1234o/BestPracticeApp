package com.liyafeng.video.game.js;

public class Main_Js {


    /**
     * 当网页被加载时，浏览器会创建页面的文档对象模型（Document Object Model）。
     * HTML DOM 模型被构造为对象的树。
     *
     *
     *
     * @param args
     */
    public static void main(String[] args) {

    }

    /**
     * 一次网页加载的过程
     *
     * 网络请求到 html网页后
     * 从上到下执行代码
     * 1 开始解析html 成dom树，
     * 2 遇到外部css开始下载（异步下载，解析继续执行，有的时候网不好，css没下载下来，只显示了原始网页）
     * 但是css的下载会阻塞js的下载，因为js有可能要取元素的属性，而这些属性在css中
     * $('#id').width()
     *
     * 3 遇到外部js开始下载（同步，代码停止，因为js可能操作dom树）
     *  执行js脚本
     * 4 HTML DOM构造完成。
     *  css解析成css rule，然后和html关联，通过渲染引擎渲染
     *
     * 5 ，标签中的图片也是遇到后异步下载，
     *
     *
     * =============js===========================
     * js中可以定义函数，在dom加载完毕后调用，这样就能获取到数据
     * 否则js写在html元素前，是取不到这个元素
     *
     *
     */
    public void fun1(){

    }
}
