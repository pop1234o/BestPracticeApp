package com.liyafeng.practice;

import android.content.Context;
import android.os.Parcelable;
import android.os.SystemClock;
import android.util.Log;

import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Vector;

/**
 * Created by liyafeng on 16/11/2017.
 */

public class Java {


    interface a {
        public static int b = 1;

        public abstract void aa();
    }

    static abstract class b {
        int a = 1;

        public void a() {

        }

        protected abstract void b();
    }

    //region Java基础

    /**
     * =====================
     * ### Java基础
     * =====================
     * */

    /**
     * 1.接口和抽象类的区别
     * http://www.importnew.com/12399.html
     */
    void a1() {
        /*
        * 从微观上说
        * 一个类能实现多个接口，但只能继承一个抽象类
        * 接口中只能有公有的抽象方法,而抽象类中可以有抽象方法，也可以没有抽象方法，也可以有普通方法
        * 接口中只能定义静态成员变量，而抽象类中可以定义非静态成员变量
        *
        * 相同点是他们都不能被实例化
        *
        * 从宏观上说
        * 定义抽象类是为了捕捉子类的通用特性，通用的实现逻辑可以在抽象类中完成，而接口中不能有逻辑
        * 而接口定义的是一种约定，实现这个接口就代表实现了接口中方法提供的服务
        *
        *
        *
        * */
    }

    /**
     * 说说String ，StringBuilder ,StringBuffer的区别
     * -------------------------
     * String如何保证不可变？为什么设计成不可变的？
     */
    public void a1_1() {
        /*
        * String 是一个不可变的字符序列
        * StringBuilder是可变的字符序列，他在单线程中使用，比速度StringBuffer更快
        * StringBuffer是线程安全的可变的字符序列，原理就是在每个方法中加上synchronized
        * 他们两个操作的是一个字符数组，而String操作的是常量池中的数据
        * ==================
        * String 是final类，里面是final 的char[]数组，而且是private的，
        * 也没有提供修改char[]内容的方法，所以String是不可变的
        * ------------------------
        * 主要是为了安全，比如放入hashmap中，本来是相等的，后来就变成其他字符串
        * 那么会引起数据混乱
        * 线程安全，每个线程取出的字符串是相等的
        * 减少空间占用，相同的字符串可以指向内存中的同一空间。
        */
        new StringBuffer().append(1);
    }

    /**
     * 说说Java的三大特性？如何理解多态？
     * http://www.runoob.com/java/java-polymorphism.html
     */
    public void a1_2() {
        /*
        * 多态，封装，继承
        * 封装意思是隐藏类中的实现细节，只暴露它的方法
        * 继承，子类继承父类中的特性，减少重复代码
        * 多态，就是父类引用指向子类对象，当父类引用调用方法的时候，调用的是子类重写的方法
        * 就是一个行为有多种实现方式
        * 多态的三个必要条件，继承，重写，父类引用指向子类对象
        * override
        */
    }

    /**
     * == 和equals 和hashcode()的关系和区别
     */
    public void a1_3() {
        /*
        * ==对于基本数据类型比较的是值，对引用类型比较的是引用值
        * Object中的equals 方法里面用的也是 ==
        * ==一般用于判断两个对象的地址是否相等，而equal一般是覆盖后用于
        * 判断值是否相等
        * -----------------------------
        * hashcode方法是用在java集合框架中的 散列表 中的，比如hashmap hashset hashtable
        * Object中的hashcode返回的是对象内存地址的映射值
        * 而重写equals一般要重写hashcode，在hashmap中会用hashcode判断两个对象的hash值
        * 如果只重写equals而不重写hashcode会在hashmap中存着两个“相等”的key
        * 这个相等表示equals是相等的，而在hashmap中却表示两个不同的值
        *
        */
        Object o = new Object();
        o.equals(o);
        o.hashCode();
        "sf".equals("a");
        new HashMap<>().put(null, null);
    }

    /**
     * {@link java.io.Serializable} 和{@link android.os.Parcelable} 的区别
     * https://www.cnblogs.com/trinea/archive/2012/11/09/2763213.html
     */
    public void a1_4() {
        /*
        * 都是序列化，Serializable是个标记接口，而Parcelable是将对象写入
        * Parcel对象中，反序列化也是从Parcel中读取
        * ------------------
        * Parcelable转为android系统设计，用于在不同进程间传递，因为他是通过内存读写来
        * 序列化和反序列化的。所以速度快，内存占用少。
        * 而Serializable是通过反射来读取和写入。所以速度慢，占用临时变量多
        * 但是它可以序列化到本地存储，或者网络传输。
        */
    }


    /**
     * 有几种内部类？他们的使用场景
     */
    public void a1_5() {
        /*
        * 静态内部类，非静态内部类，局部内部类，匿名内部类
        * -------
        * 外部类只能单继承，这时可以让内部类来继承，实现继承多个类
        */

        //局部内部类，作用域只在这个方法，别处引用不到
        final int num = 3;
        class InnerClass {
            int n = num;
        }
        InnerClass a;
    }

    /**
     * 局部内部类和闭包的区别？
     */
    public Object a1_6() {
        /*
        * 闭包就是把函数以及变量包起来，使得变量的生存周期延长
        * -----------------
        * 当函数可以记住记住并访问所在的词法作用域，即使函数是在当前词法作用域之外执行，就会形成闭包
        * ---------------------
        * 这样，我们在main函数中调用a1_6().toString(),就会输出8
        * 因为count和 InnerClass在方法结束后就应该被销毁了
        * 但是在外部却能调用，这就形成了闭包
        * =======================
        * 区别：闭包是个概念。。而局部内部类是java语法中类声明的一种？？？
        */

        final int count = 8;
        class InnerClass {
            @Override
            public String toString() {
                return count + "";
            }
        }
        return new InnerClass();
    }


    /**
     * string 转换成 integer的方式及原理
     */
    public void a1_7() {
        /*
        *  Integer.parseInt("0");
        *  原理就是取每一位字符，转化为数字，然后result*10 +这一位数字
        *  从index =0 的时候开始取字符
        */
        Integer.parseInt("0123");
    }


    /**
     * Java instanceof 关键字是如何实现的？
     * https://www.zhihu.com/question/21574535/answer/18989437
     */
    public void a1_8() {
       /*
       * 先简单介绍一下instanceof的字节码操作：
       * 确定对象是否为给定的类型指令格式：instanceof|indexbyte1|indexbyte2指令执行前后的栈顶状态：
       * ……，objectref=>
       * ……，result
       * 描述：indexbyte1和indexbyte2用于构造对当前类的常量池的索引，
       * objectref为reference类型，可以是某个类，数组的实例或者是接口。
       * 基本的实现过程：对indexbyte1和indexbyte2构造的常量池索引进行解析，
       * 然后根据java规范判断解析的类是不是objectref的一个实例，最后在栈顶写入结果。
       */
    }

    /**
     * 静态代理和动态代理的区别，什么场景使用？
     */
    public void a1_9() {
        /*
        * 静态代理，实际上就是指的设计模式中的代理模式，代理类在运行之前就已经
        * 产生了class文件。（代理模式，代理类和委托类都实现同一接口，然后我们执行代理类
        * 中的方法）
        * 动态代理，使用java reflect包中，Proxy类，将委托类的类加载器，和接口传入
        * 里面用native方法动态创建代理类，然后通过反射来创建代理类的对象，我们
        * 调用代理类的方法，就会执行InvocationHandler中的invoke方法，
        * 在这个方法中我们可以加入附加逻辑
        * =======================场景===============
        * 静态代理一般是我们对于项目中的业务逻辑进行代理
        * 而静态代理适合aop，面向切面编程，比如加统计日志
        */

        //会创建一个代理类的对象，实现了委托类的所有接口中的方法
        Object o = Proxy.newProxyInstance(Java.class.getClassLoader(), Java.class.getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("执行前处理");
                Object invoke = method.invoke(proxy, args);//proxy是真正的执行者，就是委托类
                System.out.println("执行后处理");
                return invoke;
            }
        });
        o.toString();//这个是代理对象
    }


    /**
     * 说说Java的异常体系
     */
    public void a1_10(Context context) {
        /*
        * 结构见下图
        * Throwable是异常的顶级类，子类有Error和Exception类
        * Error是程序无法处理的错误，比如OutOfMemoryError，这时Jvm会强制
        * 终止进程。
        * Exception是程序能处理的异常，它又分运行时异常和非运行时异常
        * RuntimeException 是运行时异常，他是由我们的业务逻辑出错而导致的
        * 我们可以选择捕获或者捕获，编译时不强制要求，比如NullPointException
        * OutOfIndexException 。
        * 非运行时异常一般可能由外界环境引起的异常，比如IOException,SQLException
        * 这些要求我们在编译的时候就要捕获。否则编译不通过
        */
        context.getResources().getDrawable(R.drawable.throwable);
        //Throwable
        //Error
        //Exception

    }


    /**
     * 说说你对Java注解的理解（Annotation）
     * http://gityuan.com/2016/01/23/java-annotation/
     */
    public void a1_11() {
        /*
        * @Documented //生成文档
        * @Retention(RetentionPolicy.RUNTIME) //表示保留此注解到什么时候
        * @Target(ElementType.METHOD) //注解在哪里使用
        * @Inherited //是否继承
        * public @interface CustomSubscribe {
        *     boolean sticky() default false;
        *     int priority() default 0;
        * }
        * //使用方法
        * @CustomSubscribe(sticky = true,priority = 1)
        * public void fun(){}
        * ===============系统注解==============
        * @Target(ElementType.METHOD)
        * @Retention(RetentionPolicy.SOURCE)
        * public @interface Override {
        * }
        * ==================注解的意义========================
        * 注解也叫元数据
        * 注解是JDK1.5版本开始引入的一个特性，用于对代码进行说明，可以对包、类、接口、字段、方法参数、局部变量等进行注解。它主要的作用有以下四方面：
        * 生成文档，通过代码里标识的注解生成javadoc文档。 @Documented
        * 编译检查，通过代码里标识的注解让编译器在编译期间进行检查验证。 @Override
        * 编译时动态处理，编译时通过代码里标识的注解动态处理，例如动态生成代码。//比如GreenDao的注解
        * 运行时动态处理，运行时通过代码里标识的注解动态处理，
        *     例如使用反射注入实例。//比如EventBus，可以动态的取出注解，判断是主线程执行还是子线程
        *     //比图Dragger2的依赖注入，就是运行时进行引用和实例的关联
        * ===============注解的原理===================
        * 定义完注解，编译后其实是一个普通接口继承Annotation接口，然后我们通过动态代理
        * 为这个接口产生对应的实例类，然后我们就可以取得里面的值
        * java注解是怎么实现的？ - 曹旭东的回答 - 知乎
ht      * https://www.zhihu.com/question/24401191/answer/37601385
        */
    }


    /**
     * 说一下泛型原理，并举例说明
     * */
    public void a1_12(){
        /*
        * 类型擦除
        * ----------
        * 最好能说出不同jdk之间的区别，说出编译后的class是什么样的。
        */
    }



    //endregion

    //region Java线程

    /**
     * =====================
     * ### 线程
     * 推荐阅读书籍《Java并发编程实战》
     * =====================
     * 线程中的锁就是那个对象，对象就是锁，也叫内置锁，监视器，monitor ,监视器锁，互斥锁，可重入锁
     *
     * 一个线程获取锁的唯一方式就是进入这个锁的代码块
     *
     * race conditions 竞态条件，线程执行顺序的不确定
     *
     * java重排序 http://www.infoq.com/cn/articles/java-memory-model-2#anch94124
     *
     * */

    /**
     * sleep和wait有什么区别
     */
    public void a2() {
        /*
        * 首先线程有6种状态，new waiting runnable blocked ,timed waiting ,terminated
        * 见下图
        * 首先sleep是Thread中的方法，而wait是Object中的方法
        * 调用wait，线程必须在synchronized中调用，也就是说调用wait之前
        * 线程必须先获取锁，否则会抛出异常java.lang.IllegalMonitorStateException
        * 然后调用wait后，线程释放锁，进入阻塞队列，直到被同一对象notify
        *
        * sleep 和 wait都是让线程进入阻塞状态，但是sleep是指定时间后恢复运行状态
        * 而wait需要notify，而且操作和锁有关
        *
        *
        */

        Thread.yield();
        int thread_status = R.drawable.thread_status;
    }

    /**
     * 什么是线程安全？
     */
    public void a2_1() {
        /*
        * 多个线程访问某个类时，这个类始终都表现出正确的行为，那么我们就说这个类时线程安全的
        *
        * 无状态：不包含任何字段，也不包含对其他类中字段的引用（只包含局部变量）
        * 无状态对象一定是线程安全的
        */
    }

    /**
     * volatile关键字有什么作用
     * http://aleung.github.io/blog/2012/09/17/Java-volatile-/
     */
    public void a2_2() {
        /*
        * volatile只能保证变量的可见性，就是说保证每次读取变量的时候是最新的值
        * 而不是保存在寄存器中的值，它不能保证i++的原子性
        *
        * 标记为volatile的变量表明这个变量是共享的，所以编译器不会将
        * 这个该变量上的操作和其他内存操作一起重排序
        * 也不会将变量寄存在寄存器或对其他处理器不可见的地方
        *
        * 写入相当于退出同步代码块，读取的时候是进入同步代码块
        *
        * volatile的一个典型应用就是多线程中，作为while(flag)退出的标记
        * volatile boolean flag;
        * 因为jvm在server环境（应用环境）时，会进行优化，将在while内没有改变的变量提取到循环外部
        * 所以就成了 flag = true ; while(true)，这就导致了死循环，或者说循环没有及时退出
        *
        *
        *
        */
    }


    /**
     * 线程池的原理？
     */
    public void a2_3() {
        /*
        * 线程池会指定核心线程数，和最大线程数，根据这个来创建线程
        * 里面会进行死循环，从任务队列中取任务，如果没有任务就阻塞
        * 有任务就唤醒执行。除了核心线程其他线程有超时时间，超过超时时间
        * 线程就会中断
        * 线程池好处就是不用频繁创建线程了，最大可创建500w个线程，2^29-1;
        * 剩下两位是状态；
        */
    }


    /**
     * 多线程中的安全队列通过什么实现？
     */
    public void a2_4() {
        /*
        * 通过AbstractQueuedSynchronizer
        */
    }


    /**
     * 进程和线程的区别
     * http://www.ruanyifeng.com/blog/2013/04/processes_and_threads.html
     * https://www.jianshu.com/p/7ce30a806c51
     */
    public void a2_5() {
        /*
        * 进程和线程都是cpu执行的一个时间段，线程共享进程的资源
        *
        */
    }

    //endregion

    //region Java集合

    /**
     * HashMap的实现原理
     */
    public void a3_1() {
        /*
        * 数组加链表，拉链法实现的散列表
        */
    }
    
    /**
     * 常用java集合框架简介
     * {@link java.util.Collection}
     * {@link java.util.Map}
     * ----------------------
     * */
    public void a3_2(){
        /*
         * 总体有两种接口，Collection 和 Map
         *
         * Collection=>{List ，Set，Queue }
         * Collection是“集合”的抽象 ，
         * -----------------------------
         * List 是代表有序的集合 An ordered collection (also known as a <i>sequence</i>)
         * Queue  元素优先处理的集合 A collection designed for holding elements prior to processing.
         * Set 一个没有重复元素的集合 A collection that contains no duplicate elements
         * -------------------------
         *
         *
        */
    }
    /**
     * 并发集合了解哪些？
     * */
    public void a3_3(){
        /*
        * ConcurrentHashMap 和 CopyOnWriteArrayList
        */
        Collection<Object> collection = Collections.synchronizedCollection(new ArrayList<Object>());
        new Hashtable<>();
        new Vector<>();

    }
    //endregion

    //region Java网络

    /**
     * =====================
     * ### 网络
     * =====================
     * */

    /**
     * 说说断点下载
     */
    public void a6() {
        /*
        * 在http请求头中加入 range字段， range:bytes=0-
        * 然后加入etag，If-Match:asdfasdfa
        * 第一次请求的时候可以不用传etag，服务器相应200
        * 返回数据和Etag,将Etag保存到本地
        * 下次请求获取本地以下载文件的字节数，然后加上
        * range:bytes=1024-
        * If-Match:asdfasdfa
        * 服务器会响应206，如果服务器返回206，就用RandomAccessFile .seekTo()
        * 到最后一个字节，然后再继续写入
        */
    }

    /**
     * 说说多线程断点下载
     */
    public void a7() {
        /*
        * 和断点下载一样，开启多个线程，每个线程请求指定范围的数据，
        * 首先获取文件的总大小，用get方式请求文件，然后getContentLength获取文件长度
        * 用RandomAccessFile写入文件
        */
    }


    //endregion

    //region JVM知识

    /**
     * 哪些情况下的对象会被垃圾回收机制处理掉?
     * /jvm垃圾回收机制是怎样的?
     * <p>
     * https://yunfengsa.github.io/2015/11/12/android-jvm-gc/
     */
    public void a8_1() {
        /*
        *
        * 虚拟机中的内存区域分为新生代和老年代，新分配的对象被存储在新生代中
        * 当需要申请内存而内存空间不足时，就触发Minor GC来回收新生代的内存，
        * 如果没被回收的对象就讲它年龄+1，一般到15的时候对象会被转移到老年代
        * 老年代占满，会触发Major GC，回收老年代的垃圾对象
        * 直到所有内存都占满，就触发Full GC，回收所有内存空间中的垃圾对象
        * Android使用的是标记-清除算法来回收垃圾对象
        *
        * 另外的GC算法还有复制算法（就是定义两个大小相等的区域，一次GC把非垃圾对象
        * 复制到另一块内存中，这样减少了内存碎片）
        * 标记-整理算法，标记非垃圾对象，并且整理到连续的内存中
        */
    }

    //endregion
}
