package com.liyafeng.video.game.js;

public class Node_Js {


    /**
     * https://nodejs.org/en/download/
     * 安装nodejs自带也安装npm
     * mac 安装在usr/local/bin 目录下
     * <p>
     * npm -v  安装成功
     * <p>
     * <p>
     * npm 的配置文件是 package.json
     * npm init -yes
     * 生成配置文件
     * <p>
     * https://javascript.ruanyifeng.com/nodejs/packagejson.html
     *
     * @param args
     */
    public static void main(String[] args) {

    }


    /**
     * js需要模块化`
     * 代码多，可维护
     * js变量都是全局的，模块避免了污染，（使用命名空间（类似于java的包名））
     * ========如何实现======
     * <p>
     * js开始没有类的概念，所以也就没有 私有方法，或者私有属性这一说
     * 但是我们可以通过语法（设计模式）来实现,比如闭包
     * -------模块模式---
     * https://www.oschina.net/translate/javascript-module-pattern-in-depth
     *
     * 闭包就是匿名内部类可以访问方法中的局部变量，而外部通过访问匿名内部类，延长了局部变量的生命周期
     * var myNameSpace = (function toString() {
     * var num =10;
     * document.write("aaa");
     * return{
     * getNum:function () {
     * return num;
     * }
     * }
     * })();
     *
     * 比如我们在module.js中写这样，就是返回一个对象，这个对象中的方法访问的局部变量，
     * myNameSpace.getNum() 就相当于对象调用方法，这样就可以把隐私写成局部变量或者方法
     * return的对象是对外暴露的
     * (function(){})()  这个是立即执行的函数
     *
     * ----------CommonJs AMD UMD-------
     * 上面这样写解决了作用域的问题，也就是用闭包的方法提供了私有作用域，使得外界访问不到
     * 但还有一个问题，就是myNameSpace，这个对象我要在其他js中引用它，那么这个
     * myNameSpace的js就要先引入，而且如果myNameSpace 中引入了其他js的对象
     * 那么还要先引入其他js文件，所以js的依赖关系解决也很重要
     *
     * Commonjs通过 module.exports语法来导出对象为模块，require('myModule')导入
     * 如果没有导出就不能使用
     * 这个是同步的导入，服务器还好，但是浏览器就不行了
     *
     * AMD是异步的 define来使用依赖
     * UMD是两者都有，
     *
     * 注意：CommonJs AMD UMD 是语法规则，而识别语法则是 js引擎的事情
     *
     * =========es6的import和export======
     *
     * ===========浏览器支持==========
     * 一些浏览器原生不支持的模块系统（例如CommonJS 或 AMD，以及ES6 模块的支持现在也不完整）
     * 就是说浏览器的js引擎不能识别这个代码
     *
     * 你就需要使用一些专门的构建工具来把它们转换成浏览器支持的代码。
     * 这类工具就是我们最近经常听说的Browserify, RequireJS, Webpack等等模块化构建、模块化加载工具了。
     *
     *
     *
     *
     *
     *
     *
     */
    void fun1() {
    }
}
