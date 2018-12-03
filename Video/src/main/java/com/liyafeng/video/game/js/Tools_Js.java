package com.liyafeng.video.game.js;

public class Tools_Js {

    public static void main(String[] args) {

    }

    /*
    * cli
    * command line interface
    * =========为什么需要配置文件============
    *
    * 因为一个工具，要执行，需要一些传入
    * 一些参数，而我们在命令行输入又很麻烦，所以以配置文件的形式
    * 比如json或者xml
    *
    *
    *
    * */

    /**
     * =========webpack============
     * https://www.webpackjs.com/
     * https://www.webpackjs.com/concepts/modules/ （什么是模块）
     * https://www.webpackjs.com/guides/getting-started/  具体使用方法
     *
     * js打包器，js工程有很多js文件和资源，用这个软件打包到一个js文件中，以便于
     * 别的项目引用
     *
     *  build Phaser
     *  =====================安装====================
     *  https://zhaoda.net/webpack-handbook/install.html
     * 安装webpack需要npm，所以我们需要安装node.js.因为他里面自带npm
     *
     * 然后npm install webpack -g (-g 代表全局安装，安装目录在user下)
     * 然后 npm install webpack-cli -g
     * 然后我们运行
     * webpack --version
     * 然后就看到版本号了
     * window上安装在 C:\Users\user\AppData\Roaming\npm 目录下
     * ============语法=======================
     * 两个js文件，使用require('./module.js') 来引入另一个js的代码
     *
     * =======================使用====================
     * webpack ./xxx.js --output ./bundle.js
     * 这样我们就把xx.js 依赖的js文件和依赖的文件树都合并到bundle.js
     * 中了，
     * 有个坑，网上说是 webpack xxx.js bundle.js 但是会报错
     * basedir=$(dirname "$(echo "$0" | sed -e 's,\\,/,g')")
     * SyntaxError: missing ) after argument list
     * 这个新版的webpack命令格式已经变了，所以你用之前的有可能会报错
     * 所以还是 webpack -help来看这个版本的用法
     *
     *
     *
     */
    void fun1() {
    }


    /**
     * ================npm  ====================
     * <p>
     * * https://www.npmjs.com.cn/
     * *
     * * NPM（node package manager),通常称为node包管理器，主要功能就是管理node包，包括：安装、卸载、更新、查看、搜索、发布等。
     * * NPM是基于couchdb一个数据库，详细记录了每个包的信息（作者、版本、依赖、授权信息等）
     * *
     *   比如我们js中需要依赖一些工具，和定义一些cli命令，都可以在配置文件中配置
     *   比如我们要讲我们的js源码打包到一个js中发布，用npm都能轻松实现
     *   所以配置文件中要配置name version 等信息
     *  ==================npm使用方法=======================
     *  找个空文件夹，npm init [-yes]
     *  会生成一个package.json ，里面是npm的配置文件
     *
     *
     *
     *
     * * ====================================
     * * npm install xxx # 本地安装
     * * npm install -g xxx # 全局安装
     * *
     * * 将安装包放在 ./node_modules 下（运行npm时所在的目录）
     * * 可以通过 require() 来引入本地安装的包
     * * 全局安装
     * * 将安装包放在 /usr/local 下
     * *可以直接在命令行里使用
     * <p>
     * ============anywhere====================
     * node.js ，里面用npm命令
     * * 因为phaser运行需要通过服务器访问，也就是http
     * * 而不是file://xxx
     * * 里面有个anywhere软件，他的作用是随时随地将你的当前目录变成一个静态文件服务器的根目录。
     * * npm install anywhere -g
     * *
     */
    void fun2() {
    }


     /**
      * CommonJs  ==========定义js模块系统的规范
      * https://zhaoda.net/webpack-handbook/commonjs.html
      * 为了解决 浏览器环境之外 的js项目 的 作用域问题
      * 是一种规范，node.js实现了这个规范
      *
      * 因为js开发服务端*（没有前端代码）那么你就无法关联其他文件中的属性和方法
      * 所以就需要有个规范来规定js的依赖规则
      * ----------------------
      * 模块，一个js文件就是一个模块
      * ==============webpack和commonjs================
      * commonjs是一个规范，他定义了模块间如何进行依赖（语法）
      * webpack这个工具就是对commonjs规范的一种实现，
      * webpack分析commonjs语法，然后生成对应的依赖关系
      * =========es6 module===============
      * 也是js模块化的一种规范，用import语法
      *
      * =================webpack和es6 module=================
      * webpack也支持es6 module规范的依赖，也能识别es6 module的语法
      *
      * ==============各种模块化规范======================
      * https://zhaoda.net/webpack-handbook/module-system.html
      * CommonJS ========服务器端的 Node.js Browserify，浏览器端的 CommonJS 实现
      * Asynchronous Module Definition 规范 =======RequireJS
      * Common Module Definition 规范 ====Sea.js
      * Universal Module Definition 规范
      * ES6（ES2015） 模块 ---新版的 Node.js才支持
      *
      *
      *
      *
      *
      */
     void fun3(){}
}
