package com.liyafeng.kotlin

import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    /**
     *
     * =================kotlin配置，=================================
     * https://kotlinlang.org/docs/tutorials/kotlin-android.html
     * 主要原理就是下载支持编译kotlin的gradle插件，
     *  在project的gradle中加入
     *    classpath "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version"
     *   然后在我们的module中应用插件
     *   apply plugin: 'kotlin-android'
     *   ----------------------------
     *  我们也可以在AS中Tools->Kotlin->Configure Kotlin in Project
     *
     * =============将一个java文件转为kotlin==================
     * AS中Help->Find Action,输入 convert java file to kotlin file
     * 注意，要转化哪个文件，哪个文件就在当前页
     *
     *
     * ====================为什么使用Kotlin?========================
     * https://droidyue.com/blog/2017/05/18/why-do-i-turn-to-kotlin/
     * 代码简洁，提高编写效率。安全，引入了指针的判断
     *
     * */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    /*函数定义*/
    fun sum(a: Int, b: Int): Int {
        return a + b;
    }

    /*自动识别返回值*/
    fun sum1(a: Int, b: Int): Int = a + b

    /*Unit是void值*/
    fun printSum(s: String): Unit = print(s)

    /*Unit可以省略*/
    fun printSum1(s: String) = print(s)

    fun fun1() {
        val a: Int = 1;//定义int类型
        val a1 = 1;//自动识别类型
        val a2: Int;//没有初始化不能省略Int

        a2 = 2;

        //val是只读变量,只能赋值一次，相当于常量

        //var是定义变量

        var b = 1;
        b += 1;
        //引用值,花括号中的值自动转为string
        var s = "value is $b and +1 = ${b + 1}";

    }

    /*可以省略返回值的声明，省略return ,省略分号*/
    fun max(a: Float, b: Float) = if (a > b) a else b


    /*加上?代表这个函数可以返回null值*/
    fun parseInt(s: String): Int? {
        return null;
    }

    /*当函数返回可能为null值的时候，我们必须判断null，否则编译不过*/
    /*注意可以不用分号*/
    fun fun2(x: String) {
        var i = parseInt(x)
        if (i != null) {
            i++
            print(i)
        }

        //或者我们直接判断,如果i为null，那么就不会调用后面的方法，直接返回null
        print(i?.toLong())

    }


    /*使用is来进行类型检测和自动类型转换*/
    fun fun3(obj: Any): Int? {
        if (obj is String) {
            return obj.length;
        }
        return null
    }

    /*使用循环*/
    fun fun4() {
        var list = listOf("a", "b", "c")
        for (s in list) {
            print(s)
        }

        /*循环下标*/
        for (n in list.indices) {
            print(n + 1)
        }
    }


    /*when表达式*/
    /*方法体不加花括号*/
    fun fun5(arg: Any): String = //这里语句块只有一个表达式，可以不用花括号
            //相当于switch，比它兼容更多类型
            when (arg) {
                1 -> "one"
                "123" -> "string"
                is Long -> "value ${arg.and(1)}"
                !is String -> "not string"
                else -> "unknow"
            }


    /*使用区间*/
    fun fun6(x: Int): Boolean {

        val b = x in 1..10
        val b1 = x !in 1..10

        for (i in 1..10) {
            print(i)
        }

        //相当于i+=2
        for (i in 1..10 step 2) {
        }
        //相当于i-=3
        for (i in 10 downTo 1 step 3) {

        }

        //用 in 判断元素是否在集合中
        val list = listOf(1, 2, 3)
        val isIn = 1 in list
        return b
    }


    /*kotlin的lambda表达式，这是kotlin自身支持的写法，不是用java8的*/
    fun fun7() {
        val list = listOf(1, 2, 3)
        //这里必须要用it
        val map = list.filter { it > 2 }.sortedBy { it }.map { it + 1 }
        map.forEach { print(it) }

        //创建实例，不需要new
        val arrayList = ArrayList<Int>(10);
    }


    /*函数拓展，不使用继承就对类进行拓展*/
    fun String.containOne(): Boolean {
        return this.contains("one")
    }

    fun fun8(){
        val b = "has one".containOne();
    }
}
