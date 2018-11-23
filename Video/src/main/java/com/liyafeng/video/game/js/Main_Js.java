package com.liyafeng.video.game.js;

public class Main_Js {


    /**
     * 1990年底，欧洲核能研究组织（CERN）科学家Tim Berners-Lee，
     * 在全世界最大的电脑网络——互联网的基础上，
     * 发明了万维网（World Wide Web），从此可以在网上浏览网页文件。
     *
     * 互联网：n个计算机连接起来，集合叫互联网
     * 因特网/Internet：互联网的一种，  比如还有暗网，也是互联网的一种
     *
     *  Internet其中定义了指定的转发规则，符合这个规则，且将计算机接入这个网络，这个网络叫internet
     *
     * 万维网：定义了通过互联网（硬件）如何传递信息的规则，比如http/ftp都是万维网上的传输规则
     *  tcp/ip也是规则的一部分
     *
     * =======================================================
     *
     *
     *
     *
     * 网景公司创造了
     *
     *
     *
     * 当网页被加载时，浏览器会创建页面的文档对象模型（Document Object Model）。
     * HTML DOM 模型被构造为对象的树。
     * <p>
     * ==========================
     * 全局 JavaScript 变量
     * 在函数外声明的变量是全局变量，网页上的所有脚本和函数都能访问它。
     * ==================
     * 把值赋给尚未声明的变量，该变量将被自动作为 window 的一个属性。
     * name="pop";
     * delete name; window属性可被删除
     * ======================
     * 如果变量在函数内没有声明（没有使用 var 关键字），该变量为全局变量。
     * ==========JavaScript 事件==============
     * 以下是 HTML 事件的实例：
     * HTML 页面完成加载
     * HTML input 字段改变时
     * HTML 按钮被点击
     * <some-HTML-element some-event='JavaScript 代码'>
     * <button onclick="getElementById('demo').innerHTML=Date()">现在的时间是?</button>
     * <button onclick="this.innerHTML=Date()">现在的时间是?</button>
     * this指的是dom树中的button
     * <p>
     * =======================字符串====================
     * var carname = "Volvo XC60";
     * var c = carname[1];
     * 可以用+ 拼接
     * <p>
     * <p>
     * var x = "John";
     * var y = new String("John");//这个是对象
     * <p>
     * 原始值字符串，如 "John", 没有属性和方法(因为他们不是对象)。
     * <p>
     * 原始值可以使用 JavaScript 的属性和方法，因为 JavaScript 在执行方法和属性时可以把原始值当作对象。
     * <p>
     * http://www.runoob.com/jsref/jsref-obj-string.html （String对象参考手册）
     * <p>
     * <p>
     * ================typeof =====================
     * typeof 变量名  返回类型的字符串
     * 比如"string" "object"
     * <p>
     * typeof "John"                // 返回 string
     * typeof 3.14                  // 返回 number
     * typeof false                 // 返回 boolean
     * typeof [1,2,3,4]             // 返回 object
     * typeof {name:'John', age:34} // 返回 object
     * 在JavaScript中，数组是一种特殊的对象类型。 因此 typeof [1,2,3,4] 返回 object。
     * <p>
     * typeof undefined             // undefined
     * typeof null                  // object
     * null === undefined           // false
     * null == undefined            // true
     * =================================
     * === 为绝对相等，即数据类型与值都必须相等
     * <p>
     * ==============let =================
     * const condition = 值
     * let obj = {
     * '值1' : () => { ... },
     * '值2' : () => { ... },
     * '值3' : () => { ... },
     * }
     * obj[condition]()
     * <p>
     * <p>
     * ================for/in================
     * var person={fname:"John",lname:"Doe",age:25};
     * for (x in person)  // x 为属性名
     * {
     * txt=txt + person[x];
     * }
     * <p>
     * ============数据类型================
     * 在 JavaScript 中有 5 种不同的数据类型：
     * string
     * number
     * boolean
     * object
     * function
     * <p>
     * 3 种对象类型：
     * Object
     * Date
     * Array
     * <p>
     * typeof function () {}         // 返回 function
     */
    public static void main(String[] args) {

    }

    /**
     * 一次网页加载的过程
     * <p>
     * 网络请求到 html网页后
     * 从上到下执行代码
     * 1 开始解析html 成dom树，
     * 2 遇到外部css开始下载（异步下载，解析继续执行，有的时候网不好，css没下载下来，只显示了原始网页）
     * 但是css的下载会阻塞js的下载，因为js有可能要取元素的属性，而这些属性在css中
     * $('#id').width()
     * <p>
     * 3 遇到外部js开始下载（同步，代码停止，因为js可能操作dom树）
     * 执行js脚本( 函数只有调用的时候才执行)
     * 4 HTML DOM构造完成。
     * css解析成css rule，然后和html关联，通过渲染引擎渲染
     * <p>
     * 5 ，标签中的图片也是遇到后异步下载，
     * <p>
     * <p>
     * =============js===========================
     * js中可以定义函数，在dom加载完毕后调用，这样就能获取到数据
     * 否则js写在html元素前，是取不到这个元素
     * <p>
     * ECMA-262 是 JavaScript 标准的官方名称。
     * JavaScript 由 Brendan Eich 发明。它于 1995 年出现在 Netscape 中（该浏览器已停止更新）
     * ，并于 1997 年被 ECMA（一个标准协会）采纳。
     * <p>
     * 因为不同的浏览器内核（js解析引擎）需要知道解析js规则，而且开发者需要知道不同的引擎支持什么语法
     * 所以就需要有个协议制定规则
     * JavaScript 已经由 ECMA（欧洲电脑制造商协会）通过 ECMAScript 实现语言的标准化。
     * <p>
     * ----------------------------
     * 如果在文档已完成加载后执行 document.write，整个 HTML 页面将被覆盖。
     * <p>
     */
    public void fun1() {

    }

    /**
     * 年份	名称	描述
     * 1997	ECMAScript 1	第一个版本
     * 1998	ECMAScript 2	版本变更
     * 1999	ECMAScript 3	添加正则表达式
     * 添加 try/catch
     * ECMAScript 4	没有发布
     * 2009	ECMAScript 5	添加 "strict mode"，严格模式
     * 添加 JSON 支持
     * 2011	ECMAScript 5.1	版本变更
     * 2015	ECMAScript 6	添加类和模块
     * 2016	ECMAScript 7	增加指数运算符 (**)
     * 增加 Array.prototype.includes
     *
     */
    void fun2() {
    }


    /**
     * html dom事件
     * <p>
     * http://www.runoob.com/jsref/dom-obj-event.html
     */
    void fun3() {

    }


    /**
     * =================undefined null======================
     * 1、定义
     * <p>
     * （1）undefined：是所有没有赋值变量的默认值，自动赋值。
     * （2）null：主动释放一个变量引用的对象，表示一个变量不再指向任何对象地址。
     * 2、何时使用null?
     * <p>
     * 当使用完一个比较大的对象时，需要对其进行释放内存时，设置为 null。
     * <p>
     * 3、null 与 undefined 的异同点是什么呢？
     * <p>
     * 共同点：都是原始类型，保存在栈中变量本地。
     * <p>
     * 不同点：
     * <p>
     * （1）undefined——表示变量声明过但并未赋过值。
     * <p>
     * 它是所有未赋值变量默认值，例如：
     * <p>
     * var a;    // a 自动被赋值为 undefined
     * （2）null——表示一个变量将来可能指向一个对象。
     * <p>
     * 一般用于主动释放指向对象的引用，例如：
     * <p>
     * var emps = ['ss','nn'];
     * emps = null;     // 释放指向数组的引用
     * 4、延伸——垃圾回收站
     * <p>
     * 它是专门释放对象内存的一个程序。
     * <p>
     * （1）在底层，后台伴随当前程序同时运行；引擎会定时自动调用垃圾回收期；
     * （2）总有一个对象不再被任何变量引用时，才释放。
     */
    void fun4() {
    }

    /**
     * =================constructor 属性====================
     * "John".constructor                 // 返回函数 String()  { [native code] }
     * (3.14).constructor                 // 返回函数 Number()  { [native code] }
     * false.constructor                  // 返回函数 Boolean() { [native code] }
     * [1,2,3,4].constructor              // 返回函数 Array()   { [native code] }
     * {name:'John', age:34}.constructor  // 返回函数 Object()  { [native code] }
     * new Date().constructor             // 返回函数 Date()    { [native code] }
     * function () {}.constructor         // 返回函数 Function(){ [native code] }
     * <p>
     * function isArray(myArray) {
     * return myArray.constructor.toString().indexOf("Array") > -1;
     * }
     */
    void fun5() {
    }

    /**
     * http://www.runoob.com/js/js-type-conversion.html
     * JavaScript 类型转换
     * <p>
     * String(123)
     * String(new Date())
     * 123.toString()
     * <p>
     * Number("3.14")    // 返回 3.14
     * Number(" ")       // 返回 0
     * Number("")        // 返回 0
     * Number("99 88")   // 返回 NaN
     * <p>
     * ==========自动转型==================
     * 5 + null    // 返回 5         null 转换为 0
     * "5" + null  // 返回"5null"   null 转换为 "null"
     * "5" + 1     // 返回 "51"      1 转换为 "1"
     * "5" - 1     // 返回 4         "5" 转换为 5
     */
    void fun6() {
    }

    /**
     * http://www.runoob.com/js/js-regexp.html
     * /正则表达式主体/修饰符(可选)
     * str.replace(/microsoft/i,"Runoob");
     * <p>
     * 修饰符	描述
     * i	执行对大小写不敏感的匹配。
     * g	执行全局匹配（查找所有匹配而非在找到第一个匹配后停止）。
     * m	执行多行匹配。
     * <p>
     * <p>
     * 表达式	描述
     * [abc]	查找方括号之间的任何字符。
     * [0-9]	查找任何从 0 至 9 的数字。
     * (x|y)	查找任何以 | 分隔的选项。
     * <p>
     * \d	查找数字。
     * \s	查找空白字符。
     * \b	匹配单词边界。
     * \uxxxx	查找以十六进制数 xxxx 规定的 Unicode 字符。
     * <p>
     * n+	匹配任何包含至少一个 n 的字符串。
     * n*	匹配任何包含零个或多个 n 的字符串。
     * n?	匹配任何包含零个或一个 n 的字符串。
     */
    void fun7() {
    }

    /**
     * 变量提升：函数声明和变量声明总是会被解释器悄悄地被"提升"到方法体的最顶部。
     * 只是声明会提升
     * javaScript 严格模式(strict mode)不允许使用未声明的变量。
     * <p>
     * var getName=function(){ //定义匿名函数
     * console.log(2);
     * }
     * <p>
     * function getName(){ //函数声明，预处理 会提升到顶部
     * console.log(1);
     * }
     * <p>
     * getName();
     * //结果为2
     */
    void fun8() {
    }

    /**
     * ===========let const===============
     * 全局变量
     * 在 JavaScript 中, 全局作用域是针对 JavaScript 环境。
     * <p>
     * 在 HTML 中, 全局作用域是针对 window 对象。
     * <p>
     * 使用 var 关键字声明的全局作用域变量属于 window 对象:
     * <p>
     * var carName = "Volvo";
     * // 可以使用 window.carName 访问变量
     * <p>
     * let 声明的变量只在 let 命令所在的代码块内有效。
     */
    void fun9() {
    }

    /**
     * ============void============
     * <p>
     * 该操作符指定要计算一个表达式但是不返回值。
     * void(表达式)
     * javascript:void(表达式)
     * 或者
     * void 表达式
     * javascript:void 表达式
     */
    void fun10() {
    }

    /**
     * 函数定义
     * <p>
     * var myFunction = new Function("a", "b", "return a * b");
     * var x = myFunction(4, 3);
     * 等于
     * var myFunction = function (a, b) {return a * b}
     * var x = myFunction(4, 3);
     * <p>
     * -----------------
     * (function () {
     * var x = "Hello!!";      // 我将调用自己
     * })();
     * 匿名自我调用的函数
     * <p>
     * ======================
     * function myFunction(a, b) {
     * return arguments.length;
     * }
     * ===================
     * function myFunction(a, b) {
     * return a * b;
     * }
     * <p>
     * var txt = myFunction.toString();
     * <p>
     * ============================
     * 箭头函数
     * ES6 新增了箭头函数。
     * (参数1, 参数2, …, 参数N) => { 函数声明 }
     * --------------
     * (参数1, 参数2, …, 参数N) => 表达式(单一)
     * // 相当于：(参数1, 参数2, …, 参数N) =>{ return 表达式; }
     * -------------
     * (单一参数) => {函数声明}
     * 单一参数 => {函数声明}
     * ------------------
     * () => {函数声明}
     * ---------------
     * // ES6
     * const x = (x, y) => x * y;  //省略了function关键字和return
     */
    void fun11() {
    }

    /**
     * 函数参数
     * <p>
     * JavaScript 函数有个内置的对象 arguments 对象。
     * argument 对象包含了函数调用的参数数组。
     */
    void fun12() {
    }


    /**
     * 函数调用
     * function myFunction(a, b) {
     * return a * b;
     * }
     * myFunction(10, 2);
     * 以上函数不属于任何对象。但是在 JavaScript 中它始终是默认的全局对象。
     * -------------
     * 以上函数会自动变为 window 对象的函数。
     * <p>
     * myFunction() 和 window.myFunction() 是一样的：
     * <p>
     * ------------
     * function myFunction() {
     * return this;
     * }
     * myFunction();                // 返回 window 对象
     * <p>
     * var myObject = {
     * firstName:"John",
     * lastName: "Doe",
     * fullName: function () {
     * return this.firstName + " " + this.lastName;
     * }
     * }
     * myObject.fullName();         // 返回 "John Doe"
     * ========================================
     * 如果函数调用前使用了 new 关键字, 则是调用了构造函数。
     * // 构造函数:
     * function myFunction(arg1, arg2) {
     * this.firstName = arg1;
     * this.lastName  = arg2;
     * }
     * <p>
     * // This    creates a new object
     * var x = new myFunction("John","Doe");
     * x.firstName;                             // 返回 "John"
     * ============================
     * <p>
     * call() 和 apply() 是预定义的函数方法。 两个方法可用于调用函数
     * <p>
     * =================
     */
    void fun13() {
    }

    /**
     * 闭包
     * 闭包就是一个代码块，他能访问上一层级的私有变量，即使上一层函数已经销毁
     * <p>
     * var add = (function () {
     * var counter = 0;
     * return function () {return counter += 1;} //注意，这里return的是函数
     * })();
     * <p>
     * add();
     * add();
     * add();
     * <p>
     * // 计数器为 3
     */
    void fun14() {
    }


    /**
     * 事件冒泡或事件捕获
     * 向里冒泡，向外捕获
     * document.getElementById("myDiv").addEventListener("click", myFunction, true);
     */
    void fun15() {
    }

    /**
     * 添加元素
     * var para = document.createElement("p");
     * var node = document.createTextNode("这是一个新的段落。");
     * para.appendChild(node);
     * <p>
     * var parent = document.getElementById("div1");
     * var child = document.getElementById("p1");
     * parent.replaceChild(para, child);
     */
    void fun16() {
    }


     /**
      * 对象
      * person=new Object();
      * person.firstname="John";
      * person.lastname="Doe";
      * person.age=50;
      * person.eyecolor="blue";
      *
      * =====================
      * function person(firstname,lastname,age,eyecolor)
      * {
      *     this.firstname=firstname;
      *     this.lastname=lastname;
      *     this.age=age;
      *     this.eyecolor=eyecolor;
      * }
      * var myFather=new person("John","Doe",50,"blue");
      *
      * ============================
      * function person(firstname,lastname,age,eyecolor)
      * {
      *     this.firstname=firstname;
      *     this.lastname=lastname;
      *     this.age=age;
      *     this.eyecolor=eyecolor;
      *
      *     this.changeName=changeName;
      *     function changeName(name)
      *     {
      *         this.lastname=name;
      *     }
      * }
      * ==================================
      * JavaScript 是面向对象的语言，但 JavaScript 不使用类。
      * JavaScript 基于 prototype （原型），而不是基于类的。
      *
      * =============================
      * 原型是JavaScript全局构造函数。它可以构建新Javascript对象的属性和方法。
      * Array.prototype.myUcase=function(){
      *     for (i=0;i<this.length;i++){
      *         this[i]=this[i].toUpperCase();
      *     }
      * }
      *
      *
      */
     void fun17(){}

      /**
       * 浏览器对象模型 (BOM) 使 JavaScript 有能力与浏览器"对话"。
       * 浏览器对象模型（Browser Object Model (BOM)
       *
       *
       * window.document.getElementById("header");
       * 与此相同：
       * document.getElementById("header");
       */
      void fun18(){}
}
