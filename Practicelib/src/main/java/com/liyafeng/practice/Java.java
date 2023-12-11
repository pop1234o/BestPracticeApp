package com.liyafeng.practice;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import android.view.View;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.IntSummaryStatistics;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.Vector;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Created by liyafeng on 16/11/2017.
 */

public class Java {


    interface a {
        public static int b = 1;

        public abstract void aa();

        void b(){}
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
     * ----------
     *
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
         *
         *
         *
         *
         */
        new StringBuffer().append(1);
    }

    /**
     *  =======以下两种创建String方式的区别
     *  String str = new String("hello");
     *  String str2 = "hello";
     *
     * 内存分区：
     * 堆：存放示例对象，数组对象，全局(线程)共享（就是不同地方都能引用到这个对象）
     * 栈：存放基本类型，对象引用，线程私有
     * 方法区：类被加载后的信息，存放常量，静态变量，全局共享
     *
     * 非堆（Non-Heap）包括永生代， 方法区，还有字符串常量池
     *
     * 创建String变量的时候，虚拟机就会到字符串常量池里找，看有没有能equals（“Hello”）的String，
     * 如果找到 就在栈区当前栈帧的局部变量表中创建str变量，然后把字符串常量池的引用复制给str变量。
     * 如果没找到，会在Heap堆中创建一个对象，然后把引用放到字符串常量池中，再复制到局部变量表。
     *
     *
     *  ========`==`与`equals`的区别
     *
     * ==比较引用对象的地址
     * 看对象有没有复写 equals，如果没有Object就是 ==
     *
     *
     */
    void a1_1_2(){}

    /**
     * 说说Object类中都有哪些方法？
     * -------------
     * 以及Object类中`equals`的原理
     *
     * @link java.lang.Object
     */
    public void a1_1_1() {
        /*
         * hashcode equals
         * wait notify notifyAll
         * clone(只有子类能调用)
         * finalize toString
         *
         */


        new Object().hashCode();
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

        //e.hash == hash && ((k = e.key) == key || key.equals(k))
        //这个hash值是根据hashcode来算出来的
    }

    /**
     * @link java.io.Serializable 和@link android.os.Parcelable 的区别
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
         *  原理就是取每一位字符（从左向右），转化为数字，然后result*10 +这一位数字
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
        Java o = (Java) Proxy.newProxyInstance(Java.class.getClassLoader(), Java.class.getInterfaces(), new InvocationHandler() {
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
     * ==================
     * 说说try-with-resources
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
         * =======================try-with-resources ======================
         * try( Closeable c =init()){
         * }catch(Exception e){
         * }
         * jdk7 加入
         * 就是在括号内创建的对象，会在最后调用他的close()方法
         * 就省去我们写finally了，比如IO流，锁
         */
        context.getResources().getDrawable(R.drawable.throwable);
        //Throwable
        //Error
        //Exception

    }


    /**
     * 说说你对Java注解的理解（Annotation）
     * http://gityuan.com/2016/01/23/java-annotation/
     * 说说注解处理器
     * https://docs.oracle.com/javase/8/docs/api/javax/annotation/processing/Processor.html
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
     *
     *
     */
    public void a1_12() {
        /*
         * 类型擦除
         * ----------
         * 最好能说出不同jdk之间的区别，说出编译后的class是什么样的。
         * =========如何使用泛型===============
         * 比如我们有三个页面，都很类似，都有上拉加载，下滑加载更多，加载失败，无数据，加载完了等情况
         * 这个时候实际上就是 Entity不同，这种就是我们能抽象出来，定义泛型<T>  泛型就是未知的数据
         *
         * 在类上定义，后面就可以使用了
         *
         * 我们要想清楚到底哪里可以不同，比如entity 和 adapter不同，其他都相同，这时就可以使用泛型
         *
         * 首先我们不要想，只需要先写一个代码，看哪些是可以复用的，这个时候再去抽取泛型
         *
         */
    }


    /**
     * 堆和栈在内存中的区别是什么
     * (解答提示：可以从数据结构方面以及实际实现方面两个方面去回答)？
     * jvm
     */
    public void a1_13() {
        /*
         * 数据结构上，堆是一种特殊的二叉树，栈是一种先进后出的数据结构
         * jvm中，我们执行一个方法，方法中的参数，局部变量，都压入栈中，
         * 方法执行完毕后自动释放
         *
         * 而对象都存储在堆中，由垃圾回收算法回收内存
         */
    }


    /**
     * 什么是深拷贝和浅拷贝、延时拷贝
     * https://segmentfault.com/a/1190000010648514
     */
    public void a1_14() {
        /*
         * 浅拷贝就是新创建一个对象，将原对象的基本数据类型值拷贝过去
         * 将引用类型的变量的引用值复制过去。
         *
         * 深拷贝，就是类中的引用类型变量也创建一个新的对象，将新的对象
         * 的引用拷贝过去。
         * Object的clone方法是浅拷贝。（而且要实现Cloneable）这是个标记接口，实现它就代表我们允许它进行拷贝
         * 如果要实现深拷贝，两个方法
         * 1.重写clone方法，将里面引用类型的变量也调用clone方法，直到类中没有
         * 引用数据类型即可
         * 2.实现Serializeable接口，使用写入输入对象流，然后再从输入流中读取
         * 读取出来后的对象就是深拷贝的对象。
         * -----------------
         * 延时拷贝用起来和深拷贝一样，但是拷贝的时候先进行浅拷贝，
         * 每拷贝一次计数器加一
         * 当引用类型的值发生改变的时候，我们就创建计数个对象。
         */
    }


    /**
     * 说说Unsafe类，里面有哪些操作？
     * https://www.jianshu.com/p/09477cec1478
     * http://blog.csdn.net/aesop_wubo/article/details/7537278
     * http://mishadoff.com/blog/java-magic-part-4-sun-dot-misc-dot-unsafe/
     */
    public void a1_15() {
        /*
         *  他可以直接操作jvm的内存，只用类加载器是系统的主加载器才能使用
         *  ===========用反射的方式获取======================
         *   Field f = Unsafe.class.getDeclaredField("theUnsafe");
         *    f.setAccessible(true);
         *    Unsafe unsafe = (Unsafe) f.get(null);
         *  =============================
         * 1.可以分配内存，扩充内存，释放内存
         * 2.修改对象的字段值，即使他是私有的
         * 3.线程的挂起和恢复
         * 4.CAS操作(compare and swap) 这是一种乐观操作\ 原子操作
         *  compareAndSwapInt(Object obj, long offset, int expect, int update);
         *                    操作对象，    偏移量        期望值      更新值
         *  当我们偏移量的变量的当前值和期望值一样的时候，我们将这个变量改为更新值
         *  获取head字段的偏移量（这个是相对对象内存地址偏移多少）
         *  long HEAD = U.objectFieldOffset(AbstractQueuedSynchronizer.class.getDeclaredField("head"))
         *
         */
    }


    /**
     * java中的四种引用的区别以及使用场景?
     * https://blog.csdn.net/u011936381/article/details/11709245
     * http://www.infoq.com/cn/articles/cf-java-garbage-references
     */
    public void a1_16() {
        /*
         * 我们垃圾回收算法，就是当我们申请内存但是不足时，扫描一遍，回收内存
         * 然后判断回收后内存够不够，如果不够，就会申请更多内存，如果达到上限就OOM
         *
         *  SoftReference 在请求更多的内存空间时清除，保证在OOM之前清除，
         *  也就是说在内存不足的时候，软引用才会被回收，而内存充足而触发gc的时候软引用不会被回收
         *  使用场景：SoftReference用于内存敏感的缓存，就是说内存一旦不足我们就要将他回收
         *  不建议用SoftReference缓存数据，因为它有可能过早的清除，建议使用android.Util.LruCache
         *
         * WeakReference是只要触发gc，不管内存空间还够不够，都会被回收
         * 使用场景，用于hash表 WeakHashMap （存的Entry本身是一个WeakReference）
         * 判断ReferenceQueue是否为null，如果不为空取出来从map中移除
         *
         *
         * PhantomReference
         * 基本没有引用，刚赋值直接返回null
         *
         * ReferenceQueue
         * 当引用被回收的时候，这个XXXReference就会被加入到这个队列
         *
         */
        new WeakReference<Integer>(1);
        new SoftReference<Integer>(1);
        ReferenceQueue<Integer> queue = new ReferenceQueue<>();
        new PhantomReference<Integer>(1, queue);
        if (queue.poll() != null) {

        }

//        ReferenceQueue<Integer> queue = new ReferenceQueue<>();
//        SoftReference<Integer> softReference = new SoftReference<>(1);
//        WeakReference<Integer> weakReference = new WeakReference<>(2);
//        PhantomReference<Integer> phantomReference = new PhantomReference<>(3, queue);
//
//        int i=0;
//        while (true){
//            Integer integer = softReference.get();
//            if (integer != null) {
//                System.out.println("soft:"+integer);
//            } else {
//                System.out.println("soft gc");
//            }
//            Integer integer1 = weakReference.get();
//            if (integer1 != null) {
//                System.out.println("weak:"+integer1);
//            } else {
//                System.out.println("weak gc");
//            }
//
//            Integer integer2 = phantomReference.get();
//            if (integer2 != null) {
//                System.out.println("phantom:"+integer2);
//            } else {
//                System.out.println("phantom gc"+queue.poll());
//            }
//            System.out.println("==================");
////            i++;
//            new java.lang.String("123"+"123"+"123");
//            Runtime.getRuntime().gc();
//            try {
//                Thread.sleep(100);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }

    }


    /**
     * 反射修改常量?
     */
    public void a1_17() {
        /*
         * 注意需要去除final
         */
//        //获取Bean类的INT_VALUE字段
//        Field field = Bean.class.getField("INT_VALUE");
//        //将字段的访问权限设为true：即去除private修饰符的影响
//        field.setAccessible(true);
//    /*去除final修饰符的影响，将字段设为可修改的*/
//        Field modifiersField = Field.class.getDeclaredField("modifiers");
//        modifiersField.setAccessible(true);
//        modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);
//        //把字段值设为200
//        field.set(null, 200);
        //
        //如果是方法参数是float类型，那么直接用float.class即可
    }


    /**
     * 枚举里可以有方法吗？如何使用？
     */
    public void a1_18() {
        /*
         * 可以有方法
         *
         * */
    }


    /**
     * 3按位或4等于多少？
     */
    public void a1_19() {
        /*
         * 0011 | 0100
         * 0111 = 7
         * 2^2+2^1+2^0= 4+2+1=7
         *
         * 哪位有1就2^n相加
         *
         * */
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
         *=================总结==========================
         * sleep 后不会释放锁， wait后会释放锁
         * sleep必须指定阻塞时间，而wait可以不指定
         * wait后能被notify和interrupt打断，而sleep只能被interrupt打断
         *
         */

        Thread.yield();
        int thread_status = R.drawable.thread_status;
    }

    /**
     * 什么是线程安全？ java中可以保证多线程安全的方式（包括：字段，多种锁，保证线程安全的数据结构与实现
     *
     *
     * ========线程安全
     * https://developer.51cto.com/art/201910/605093.htm
     * 1.synchronized关键字
     * 保证同一段代码同一时间只能有一个线程在执行。
     * 2.volatile关键字用来修饰共享变量，保证可见性，防止重排
     * 3.java提供三种类型的原子类，当某个操作因为不是原子操作导致的线程安全问题的时候，可以使用原子类来替代
     * 比如：多线程环境下执行a++，可以使用 AtomicInteger 类incrementAndGet()方法实现。相比synchronized，原子类是使用乐观锁来实现线程安全，synchronized使用悲观锁来实现线程安全。
     *
     * 4.使用ThreadLocal保存当前线程的变量值
     *
     *
     *
     *
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
         * 首先并发编程需要有三个概念
         * 一个是原子性，一个是可见性，一个是有序性
         * 1.原子性，比如在写int型的时候，先写低16位，在写高16位
         * 当只写了低16位的时候，这个时候另个线程去读取，那么就会
         * 导致读取的数据错误
         * 2.可见性，cpu会把变量先读取到寄存器（高速缓存），然后
         * 对这个变量进行操作，操作完再同步到内存中。所以这就会导致
         * 多个线程读取的数组不一致
         * 3.有序性，jvm会对java代码重排序，单保证结果不变
         * 但是这在多线程就会出问题
         *
         * java提供了volatile关键字来保证变量的可见性，阻止重排序问题
         *
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
     * ThreadLocal 原理
     *
     * set
     * t.threadLocals = new ThreadLocalMap(this, firstValue);
     *
     *
     * 而ThreadLocalMap是一个定制化的Hashmap
     *
     * get获取 t.threadLocals这个map，然后获取key是
     * 这个线程，获取value
     */
    public void a2_2_1(){}

    /**
     * 如何创建线程池?四种线程池？线程池好处？
     * 原文链接：https://blog.csdn.net/u011974987/article/details/51027795
     *
     * coreSize,maxSize，keepLiveTime
     *
     *  newCachedThreadPool //0, Integer.MAX_VALUE, 60L
     *
     *  newFixedThreadPool // nThreads nThreads ,0
     * newSingleThreadExecutor //1 ,1,0
     * newScheduledThreadPool // corePoolSize  Integer.MAX_VALUE ,10L
     *
     * newCachedThreadPool创建一个可缓存线程池，可灵活回收空闲线程 60s保活，若无可复用，则新建线程。
     * newFixedThreadPool 创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待。
     * newScheduledThreadPool 创建一个定长线程池，支持定时及周期性任务执行。
     * newSingleThreadExecutor 创建一个单线程化的线程池，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序(FIFO, LIFO, 优先级)执行。
     * ————————————————
     * 版权声明：本文为CSDN博主「徐昊Xiho」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
     *
     * ========线程池好处
     * new Thread的弊端如下：
     * a. 每次new Thread新建对象性能差。
     * b. 线程缺乏统一管理，可能无限制新建线程，相互之间竞争，及可能占用过多系统资源导致死机或oom。
     * c. 缺乏更多功能，如定时执行、定期执行、线程中断。
     *
     * 相比new Thread，Java提供的四种线程池的好处在于：
     *
     * a. 重用存在的线程，减少对象创建、消亡的开销，性能佳。
     * b. 可有效控制最大并发线程数，提高系统资源的使用率，同时避免过多资源竞争，避免堵塞。
     * c. 提供定时执行、定期执行、单线程、并发数控制等功能。
     *
     *
     *
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
         *
         * Executors 创建 ThreadPoolExecutor
         */

        Executors.newCachedThreadPool();
        Executors.newFixedThreadPool(1);
        Executors.newScheduledThreadPool(3);
        Executors.newSingleThreadExecutor();

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

    /**
     * 开启线程的四种方式？各自优缺点？
     */
    public void a2_6() {
        /*
         * 1.继承Thread
         * 2.实现Runnable接口
         * 3.实现Callable，用FutureTask来实现线程（这个类其实也实现了Runnable）
         * LockSupport实现等待
         *
         * 4.使用线程池创建线程
         *
         * 实现接口的方式优点就是还能够继承其他类，而且可以访问同一个Runnable中的对象
         * 缺点就是如果引用当前线程必须用Thread.currentThread()
         *
         * 继承的优点是用this可以访问线程中的一些变量
         * 缺点就是不能继承其他类了
         */

        FutureTask<String> futureTask = new FutureTask<String>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return null;
            }
        });
        new Thread(futureTask).start();

    }

    /**
     * 如何控制某个方法允许并发访问线程的个数？他的原理是什么？
     */
    public void a2_7() {
        /*
         * 用Semaphore类（赛摩佛），执行到一个方法时申请信号量加1，超过时等待
         * 它的原理是内部使用了AbstractQueuedSynchronizer
         * AQS原理见a2_19()
         */
    }


    /**
     * 说说Thread中的interrupt?interrupted 和isInterrupted区别？
     * http://www.cnblogs.com/skywang12345/p/3479949.html
     */
    public void a2_8() {
        /*
         * interrupt 中断阻塞线程（wait sleep join），阻塞位置抛出
         * InterruptedException
         * 如果线程不是阻塞，那么就只是将标记设置为true
         * 这个方法的作用就是将线程阻塞的状态打破，然后线程的阻塞方法比如wait等
         * 会抛出个异常
         * 如果线程不是阻塞的，那么调用这个方法只是标记为阻塞，线程仍然正常
         * 执行
         * ================================
         * interrupt()两种结果
         * 1.如果wait sleep 或者 join把线程阻塞，调用这个方法抛出异常
         * 2。如果线程没有阻塞，那么就将标记标记为ture
         *
         * Thread.interrupted()如果为true，那么返回标记，并且将标记设置为false
         * 这个一般用来做while的退出条件
         *
         *
         * interrupted是检测标记并将标记设置为false
         * 意思是如果是阻塞，调用了方法
         *
         * isInterrupted仅返回检测的标记
         */
        Thread.interrupted();//等于下面代码,后面版本就直接改成native代码了
//        Thread.currentThread().isInterrupted(true);

        Thread.currentThread().isInterrupted();//

        Thread.currentThread().interrupt();

    }

    /**
     * 谈谈wait/notify关键字的理解?为什么他们都要在同步代码块中？
     * 为什么他们都定义在Object中？
     */
    public void a2_9() {
        /*
        *
        * =============谈谈wait/notify关键字的理解?为什么他们都要在同步代码块中？=============
        * 他们必须在同步代码块中是为了防止竞态条件。
        *
        * 比如有很多消费者线程等待资源，这时一个生产者生产了一个资源，
        * 然后notifyAll了，如果没有锁，那么所有的消费者线程都会抢占这一个
        * 资源，会造成不可预知的结果，这是不能接受的
        * 而且生产者加锁也是为了安全，只有生产者准备就绪，释放锁的时候
        * 消费者线程才能获取到锁。
        *
        *
        * ============为什么他们都定义在Object中？==============
        * 因为任意对象都能成为锁
        *
        * Object o = new Object();
          synchronized (o){//当前线程 获取o对象的监视器
            try {
               o.wait();//一个线程获取到这个对象的监视器后，才能调用这个方法的wait
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        * 这里必须调用o.wait();，因为线程获取了对象o的监视器，所以wait只能
        * 在获取监视器那个对象 调用，否则会抛出异常 java.lang.IllegalMonitorStateException
        *
        *
        *
        *
        */
        try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        notify();
    }

    /**
     * 什么导致线程阻塞？
     */
    public void a2_10() {
        /*
         * Thread.sleep 这个不会放弃锁
         * wait方法 这个阻塞后会放弃锁
         * Thread.yield() 有可能导致阻塞
         * thread.join方法，会阻塞等待thread线程执行完
         * IO操作和Socket操作都是阻塞式操作
         */

        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        notify();

        Thread.yield();
    }

    /**
     * Thread.yield()有什么作用？什么场景使用？
     * yield(放弃)
     */
    public void a2_11() {
        /*
         * 使线程放弃cpu使用权限，一般可以用来复现bug，或者设计并发线程结构
         * 注意，这个放弃cpu使用权限，但是不放弃锁
         */
    }

    /**
     * thread join 是什么作用？
     */
    public void a2_12() {
        /*
         * 首先这个线程对象中的方法，thread.join();
         * 是调用线程，等待thread线程执行完后再继续执行
         *
         * 调用线程等待对象线程死亡，这个时候调用线程阻塞。
         * 里面原理就是 用 isAlive判断线程是否存活，如果存活，就wait（）
         * 如果不存活了，就跳出循环继续执行
         */
        try {
            Thread thread = new Thread();
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 线程如何关闭？
     */
    public void a2_13() {
        /*
         * 1.thread.stop() 这个已经废弃了,因为他不安全的,因为他会在任意逻辑处终止执行
         * 因为stop后线程中持有的锁会立刻释放掉，就导致并发的逻辑不可控制
         * 2.推荐使用变量标记的方式来终止线程，while(flag>0)  volatile flag
         *
         */
        Thread.currentThread().isInterrupted();
        Thread.currentThread().stop();
    }


    /**
     * 讲一下java中同步的方法?
     * synchronized和Lock(的子类ReentrantLock)有什么区别？
     * http://www.cnblogs.com/paddix/p/5405678.html
     */
    public void a2_14() {
        /*
         * 同步是为了防止多个线程访问同一资源，所以要排队访问线程
         * 1.可以用 关键字synchronized，优点就是使用方便，缺点是他是互斥锁，不能是共享锁，不够灵活，
         *  互斥锁和共享锁的区别见a2_16()
         *
         * synchronized 配合wait notify 来实现阻塞和唤醒
         * 2.可以用AbstractQueuedSynchronizer 的子类来实现同步
         * 其实Lock子类是用来代替synchronized的，内部的实现原理还是AQS的内部类
         * 配合 Condition await signal 来实现阻塞和唤醒
         * 用到AQS的类，比如Semaphore,ReentrantLock,CountDownLatch
         *
         * Condition是接口，ConditionObject是AQS中的Condition的实现类，里面有一个链表
         * 当调用await的时候，线程阻塞，LockSupport.park，然后加入等待队列中
         *
         *
         * ==============原理======================
         * 1.synchronized原理是对象锁，存储对象的内存中有一个标记
         * 当线程进入锁，标记加1，退出锁标记减1，来实现同步，这个操作也是CAS
         * 底层也有一个等待队列，
         * 2.AQS的原理是使用UnSafe类中的CAS（Compare and swap）操作来标记
         * 线程进入退出，使用park 和unpark来实现线程阻塞等待，然后当有线程释放锁的时候
         * 会从等待队列中取线程来唤醒，让他继续持有锁。
         *
         * ==============synchronized和Lock有什么区别？==========
         * synchronized是jvm来控制的，而Lock是java代码控制的
         * 他们在1.6后性能都差不多，因为1.5之前synchronized是重量级锁，但是之后版本做了优化
         * Lock比较灵活，他能控制唤醒哪些线程，而synchronized中只能唤醒所有线程
         *
         *
         */
    }

    /**
     * java中如何保证数据的一致性？
     */
    public void a2_15() {
        /*
         * 1.使用同步锁
         * 2.使用volatile关键字
         */
    }

    /**
     * 什么是互斥锁（悲观锁）、共享锁（乐观锁）？
     * 什么是自旋？
     * 什么是CAS操作？什么是ABA问题？
     * 什么是公平锁？什么是非公平锁？
     * 什么是轻量级锁？什么是偏量锁？
     * http://www.cnblogs.com/paddix/p/5405678.html
     * http://www.cnblogs.com/javaminer/p/3892288.html
     * https://www.zhihu.com/question/55075763
     */
    public void a2_16() {
        /*
         * =================什么是互斥锁、共享锁？=============================
         * https://blog.csdn.net/wojiushiwo945you/article/details/42292999
         * 互斥锁（排它锁、独占锁、写锁、X锁）悲观锁
         * 一个线程获取互斥锁后，其他线程不能再获取，直到这个线程释放锁 比如synchronized关键字 , ReentrantLock
         *
         * 如果事务T对数据A加上排他锁后，则其他事务不能再对A加任任何类型的锁，直到在事务的末尾将资源上的锁释放为止。获准排他锁的事务既能读数据，又能修改数据。
         * --------------------------------
         *
         * 共享锁（读锁、S锁）乐观锁
         * 一个线程获取共享锁后，其他线程只能获取共享锁，只能读取数据，比如 ReadWriteLock,CountDownLatch
         *
         * 如果事务T对数据A加上共享锁后，则其他事务只能对A再加共享锁，不能加排他锁，直到已释放所有共享锁。获准共享锁的事务只能读数据，不能修改数据。
         *
         * ==============什么是自旋？======================
         * 一个线程不停的尝试获取锁，而不是挂起等待
         *
         * ===============什么是CAS操作？什么是ABA问题？==============
         *
         *
         * ==============什么是公平锁？什么是非公平锁？===============
         * https://blog.csdn.net/wojiushiwo945you/article/details/42292999
         * 公平锁是指线程执行按照顺序来，会加入等待队列，前一个线程释放了锁，后一个线程获取锁
         * 而非公平锁，是可以插队，一个新线程到加锁处会先尝试获取锁，如果获取到就直接执行
         * 没有获取到才加入等待队列
         *
         *
         */
    }

    /**
     *  说说 CountDownLatch ,CyclicBarrier  ,Semaphore
     * https://www.cnblogs.com/dolphin0520/p/3920397.html
     *
     */
    public void a2_16_1(){
        /*
        * =======================
        * Latch 门闩
        *
        * CountDownLatch(3)
        * await() //调用await()方法的线程会被挂起，它会等待直到count值为0才继续执行
        *
        * latch.countDown(); //数值减一
        *
        *
        *
        * */
    }

    /**
     * synchronized 关键字的原理？
     * http://www.cnblogs.com/paddix/p/5367116.html
     */
    public void a2_17() {
        /*
         *
         */
    }


    /**
     * AbstractQueuedSynchronizer原理？
     */
    public void a2_19() {
        /*
         * 里面用status 标记线程状态
         * 用CAS操作和UnSafe类的park和unpark来实现线程阻塞和唤醒
         * 用双向列表来存储没有获取到锁的线程
         */
    }

    /**
     * ReentrantLock 原理？
     * https://www.jianshu.com/p/fe027772e156
     */
    public void a2_20() {
        /*
         * 内部用AQS实现了公平锁和非公平锁，重写的tryAcquire 和tryRelease
         * 来实现公平和非公平
         */
    }

    /**
     * 死锁的四个必要条件？如何防止死锁？
     */
    public void a2_21() {
        /*
         * 1.互斥条件：一个资源在同一时间只能被一个线程占有
         * 2.不可剥夺(抢占)条件:在一个线程未使用完这个资源时，其他线程不可抢占
         * 3.占有且(又)申请条件：一个线程已经占有一个资源，而他又去申请另一个资源
         * 4,循环等待条件：所有线程都申请新的资源，从而形成一个循环
         * ==========如何解决===========
         * 1.预防，只要打破四个条件中的一个就可以预防
         * 2.避免，可以用一个安全队列来判断是否即将产生死锁
         * 3.检测与恢复，用一个二维数组存储线程当前持有的资源，和即将申请的资源，
         * 每隔一段时间检测一次，如果发现线程持有的资源和要申请的资源已经形成死锁
         * 那么可以将其中的一个线程中断，释放资源，打破死锁
         *
         */
    }


    /**
     * 多线程断点上传原理?多线程断点下载（续传）原理？
     */
    public void a2_22() {
        /*
         * 多线程的原理就是每个线程负责文件 指定字节范围的下载、上传
         * =============下载=================
         * 下载可以用http协议的If-Match: etag，和Range : bytes=%d-
         * 的请求头，来指定从文件的哪里开始下载
         * 如果是断点下载就返回206的响应码，否则第一次下载就返回200
         * ===============上传===================
         * 上传需要和服务器协商，可以用http也可以用socket
         * 需要告知服务器，你这次上传是从哪个字节开始
         */
    }

    /**
     * 手写生产者-消费者模式
     */
    public void a2_23() {
        /*
         * 一个方法take()，一个方法put()
         * 其实就是取元素，没有就阻塞，一个放入元素，然后唤醒阻塞线程。
         * ArrayList<T> list = new ArrayList();
         * synchronized take(){
         *
         * while(true){
         *   if(list.size()>0){
         *       return list.get(0);
         *   }else(){
         *       wait();
         *   }
         * }
         * }
         *
         * synchronized put(T t){
         *   list.add(t);
         *   notifyAll();
         * }
         */
    }
    //endregion

    //region Java集合

    /**
     * 常用java集合框架简介
     * @link java.util.Collection}
     * @link java.util.Map}
     * ----------------------
     * http://wiki.jikexueyuan.com/project/java-collection/linkedhashmap.html
     */
    public void a3_2() {
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
         * List下有{
         *  Vector ，可增长和收缩的对象数组，线程安全，方法上加入synchronized
         *  ArrayList 可调整大小的对象数组，和Vector类似，只不过他没有同步锁
         *  LinkedList 双向链表，实现了List和Deque接口，
         *  CopyOnWriteArrayList （java.util.concurrent.）线程安全的随机访问列表 A thread-safe random-access list
         *  写入的时候是将数组复制到一个新的数组，然后再新的数组中写入，然后，引用指向
         *  新的数组（在写入时拷贝）
         * }
         * -----------------------------------
         * Set下有
         * {
         * =》SortedSet
         * =》NavigableSet
         *   TreeSet(内部是TreeMap实现)
         *
         * HashSet (内部是HashMap实现 )
         *
         * android.support.v4.util.ArraySet
         * 数组实现，二分法查找
         *
         * }
         *
         * =======================================
         * Queue下
         * {
         * =>AbstractQueue
         * PriorityQueue 一个基于最大/小堆的优先队列
         * *******************************
         * BlockingQueue(支持读取等待，和存储等待的队列)
         * 比如现在队列是空，我们调用读取，会等待队列中有数据再读取
         * =》ArrayBlockingQueue
         * =>LinkedBlockingQueue
         * =>PriorityBlockingQueue
         *
         * *****************************************
         * Deque(双向队列)
         *  =》LinkedList
         *  +>ArrayDeque （里面用数组实现，可变大小）
         *  ->BlockingDeque-> LinkedBlockingDeque
         *
         * }
         *
         * =====================================
         * Map(一个键值对映射的对象，key只有唯一值)
         * An object that maps keys to values.  A map cannot contain duplicate keys;
         * each key can map to at most one value.
         *
         * ------------------------------
         * Map
         * {
         * *****************************
         * HashMap 一个基于散列表的Map实现 ，键值可以为null
         * （拉链法的散列表）
         * *******************************
         * =>SortedMap(key排序的map，按Comparator升序排序 )
         * => NavigableMap(导航的Map,比如小于某个数字的最大key)
         * TreeMap 是他们的子类，都实现了他们的特性
         * *******************************
         * HashTable 散列表实现的Map，键值非null，和HashMap差不多，只不过他的方法都加入了同步锁
         * *******************************
         * ConcurrentMap=>
         * ConcurrentHashMap(线程安全的HashMap，里面使用了分段锁)
         * *******************************
         * LinkedHashMap 是HashMap的子类，提供有序的访问HashMap
         * http://wiki.jikexueyuan.com/project/java-collection/linkedhashmap.html
         * accessOrder =true //代表按访问顺序排序（访问一个元素，插入到顺序结尾），
         * false，我们遍历的时候就是按插入时的顺序遍历
         * **************************************
         * android.support.v4.util.ArrayMap
         * 里面是数组加二分法查找hash实现
         * 他更节省内存，因为只用数组存储，但是它只适合小数据量，几百个数据下
         * 和HashMap的性能不相差50%。
         * }
         *
         *
         */
    }

    /**
     * 并发集合了解哪些？
     * （线程安全的集合）
     */
    public void a3_3() {
        /*
         * java.util.concurrent 包下的
         * ConcurrentHashMap(分段锁)
         * CopyOnWriteArrayList（方法锁，加写时复制）（这个在遍历的时候线程安全，但是遍历的可能不是最新数组）
         * ConcurrentLinkedQueue（sun.misc.Unsafe 用这个类保持线程安全）
         */
        Collection<Object> collection = Collections.synchronizedCollection(new ArrayList<Object>());
        new Hashtable<>();
        new Vector<>();//这个在遍历的时候还是线程不安全

    }


    /**
     * HashMap的实现原理
     * 如何减少hash碰撞？（java用的什么算法来减少hash碰撞？）
     * 如何处理hash碰撞？
     * hashmap比较key相等的依据是什么？
     * SparseArray 和 ArrayMap 区别
     */
    public void a3_1() {
        /*
         * ===========HashMap的实现原理=================
         *
         * 数组加链表，拉链法实现的散列表(后来链表改成红黑树了。。)
         * 当链表长度大于8，则转换为红黑树，以后就直接插入红黑树中
         *
         * =========如何减少hash碰撞？（java用的什么算法来减少hash碰撞？）===========
         * 一个设计良好的 hash 算法，具有以下特性
         * 压缩性：任意长度的数据都可以通过hash来压缩（或扩展）到相同长度
         * 抗计算原性：给定一个hash结果h,寻找原来的M来满足H(M)=h是计算困难的。
         * 抗碰撞性。找到两个明文M,M’ 使hash值 H(M)=H(M’)是计算困难的
         *
         *   static final int hash(Object key) {
                int h;
                return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
            }
         *
         * =========如何处理hash碰撞？===========
         * 当有元素发生hash碰撞的时候，就讲碰撞的所有元素组成链表
         * 依次加入链表尾
         *
         * =========hashmap比较key相等的依据是什么？========
         * 先判断hash值，然后判断 ==（引用相等）或者equals(两者其一就好)
         * 为什么要先判断==呢？是因为这个是最直观的，高效
         *
         */
    }

    /**
     * ConcurrentHashMap作用？原理？
     * http://www.infoq.com/cn/articles/ConcurrentHashMap
     */
    public void a3_1_1() {
        /*
         * HashMap 在多线程中扩容重hash的时候导致死循环，脏读的问题
         * =======================ConcurrentHashMap======================
         * 旧版本采用分段锁，将整个散列表分为很多Segment[]，然后每个Segment
         * 中又有多个HashEntry[]，所以hashmap在put的时候就先用元素key的hash
         * 来找到指定Segment
         * return segments[(hash >>> segmentShift) & segmentMask];
         * 找到后调用segment的put方法，里面还是通过key的hash值来找到
         * 指定的HashEntry[index]，
         *  int index = hash & (tab.length - 1);
         *  这个时候调用lock来加锁，Segment继承ReentrantLock
         *  ------------
         * 后来就变成在某个链表中加锁了，用的synchronized关键字
         * 配合cas操作来完成同步
         *
         *
         *
         */
        new HashMap<>();
        ConcurrentHashMap<Integer, String> concurrentHashMap = new ConcurrentHashMap<>();
        concurrentHashMap.put(1, "");
        String s = concurrentHashMap.get(1);
    }

    /**
     * LinkedHashMap作用？原理？@link java.util.LinkedHashMap}
     * http://wiki.jikexueyuan.com/project/java-collection/linkedhashmap.html
     */
    public void a3_9() {
        /*
         * 有序的HashMap(按插入顺序，或者访问顺序)
         * Hash table and linked list implementation of the <tt>Map</tt> interface,
         * with predictable iteration order(有可预料的遍历顺序)
         * 因为hashmap的iteration是无序的，而这个是可以有序的遍历
         *
         * accessOrder为true，则遍历是按访问顺序
         * false就是按插入顺序
         *
         * =================原理==============
         * 原理就是重写了put中调用的 addEntry方法  如果是新加入的，那么加入Entry环的尾结点
         * 这个是LinkedHashMap中的 LinkedHashMapEntry header变量
         * 也从写了createEntry，返回LinkedHashMapEntry
         * LinkedHashMapEntry<K,V> extends HashMapEntry<K,V>  里面重写了recordAccess方法，
         * 每次访问，如果accessOrder=true(按访问顺序)，然后就把这个Entry加入环的尾节点（
         * 就是header的前一个节点）
         *
         * header是初始化的时候new的一个锚点对象，他的after before都指向自己
         *
         *
         * LinkedHashMapEntry是一个环的结构，有一个header，每次新加入或者新访问的节点加入环尾
         * 那么header的after节点是eldest 最老的节点
         *
         * -------------------------
         * 后续改为红黑树的是重写了newTreeNode ，里面将新的node创建后加入了链表
         *
         * 基本原理就是插入的时候 ，重写创建node的方法，创建完了加入链表
         *
         *
         *
         */
        LinkedHashMap<Integer, String> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put(1, "a");

    }


    /**
     * TreeMap作用？
     * @link java.util.TreeMap}
     */
    public void a3_11() {
        /*
         * A Red-Black tree based {@link NavigableMap} implementation.
         * 基于红黑树的NavigableMap实现  （Navigable 可导航的）
         *
         * 这个遍历出来是有序的，元素要实现Comparable,或者传入Comparator
         * 数据结构是左小右大的二叉树。
         * 遍历就是中序遍历二叉树
         *
         */
        TreeMap<Integer, String> treeMap = new TreeMap<>();
        treeMap.put(1, "1");
        String s = treeMap.get(1);
        Set<Map.Entry<Integer, String>> entries = treeMap.entrySet();
        Iterator<Map.Entry<Integer, String>> iterator = entries.iterator();

    }


    /**
     * ArrayList和LinkedList区别？使用场景？
     */
    public void a3_12() {
        /*
         * 内部数据结构不同，一个数组实现一个链表实现
         *
         * ArrayList适合读（比如取第3个元素），如果插入元素，那么
         * 要将后面的元素后移，而且要扩容还需要将原数组的数据拷贝
         *
         * LinkedList就是双向链表
         * 适合 添加/删除（添加删除频繁的用LinkedList）
         * 但是读取就需要遍历来读取了(判断index离头近还是离尾近，就从哪里开始遍历)
         *
         */

        ArrayList<String> strings = new ArrayList<>();
        strings.add("");
        strings.remove(1);
        strings.get(1);

        LinkedList<Long> longs = new LinkedList<>();
        longs.add(1L);
        longs.remove(1);
        longs.get(1);

    }


    /**
     * RandomAccess接口有什么用？
     */
    public void a3_4() {
        /*
         * 这是一个标记接口
         * 实现这个接口的List，表明它有能力提供 快速的随机访问数组的能力，
         * 比如ArrayList, 因为它可以通过索引在常数时间内访问任一元素
         *
         * 这个接口的作用是我们可以判断一个List是否实现了RandomAccess
         * 当实现的时候，我们可以用for循环来遍历数组
         * 如果没有实现（比如LinkedList），我们推荐用Iterator的方式遍历，
         * 如果用For循环会慢很多。
         */
    }


    /**
     * Iterator 和 Enumeration区别?
     * 说说ArrayList的Iterator是如何实现的？
     * 说说HashMap遍历如何实现？
     * @link java.util.ArrayList}
     * @link java.util.HashMap}
     */
    public void a3_5() {
        /*
         * 他们都是用来遍历集合的
         * Enumeration jdk1出的，Iterator是jdk2出的，是前者的升级
         * 主要两点不同
         * <li> Iterators allow the caller to remove elements from the
         *      underlying collection during the iteration with well-defined
         *      semantics.
         * <li> Method names have been improved.
         *
         * ========================说说List的Iterator是如何实现的？===========
         * ArrayList中的Iterator使用的迭代器模式，它里面有cursor初始值为0
         * 然后调用一次next()函数cursor+1，他访问的还是外部类中了数组
         * 在调用iterator时不允许添加或者删除元素，否则抛出ConcurrentModificationException
         * 并发修改异常，这是因为ArrayList中有个modCount来记录List的修改次数
         * 在Iterator初始化的时候会记录这个值，然后每次next()或者remove会比较这个值
         * 如果不一致抛出异常。
         * =======================说说HashMap遍历如何实现？==================
         * entrySet中返回一个set,里面提供了iterator()，我们知道HashMap中有HashMapEntry[]
         * 是一个散列表，然后迭代器初始化的时候用while找到第一个HashMapEntry[index]不为null的地方
         * 然后这就是当前的entry，返回后会判断entry.next是否为null，如果不为null，则next就是
         * nextEntry。就这么一直遍历，直到index到散列表结尾
         * ----------------------
         * keySet原理和Entryset一样，只不过entry中存有key值，每次遍历只返回key即可
         */
        Set<Map.Entry<Object, Object>> entries = new HashMap<>().entrySet();
        Iterator<Map.Entry<Object, Object>> iterator = entries.iterator();
        while (iterator.hasNext()) {
            Map.Entry<Object, Object> next = iterator.next();
            Object key = next.getKey();
            Object value = next.getValue();
        }

        Iterator<Object> iterator1 = new HashMap<>().keySet().iterator();
    }


    /**
     * 说说Arrays和Collections 类的作用？
     */
    public void a3_6() {
        /*
         * 都是工具类
         * Arrays主要对数据进行操作，主要是查找，复制，排序
         * Collections对Collection对象操作，复制，查找，变成同步的集合
         *
         */
    }


    /**
     * 如何实现二叉树深度优先和广度优先的遍历？
     * 图的广度优先遍历？
     */
    public void a3_7() {
        /*
         * 深度优先遍历可以用递归方法来遍历
         *
         * 广度优先遍历可以用一个队列来存储上一层已经遍历的节点
         * 然后将新遍历的节点加入队列，直到队列空
         * （比如，定节点入队列，然后进入循环，循环取队列中的元素，直到队列为0，
         * 取出后，获取他的子节点加入队列，如此循环，则是广度遍历）
         *
         * ===========================
         * 图的广度优先，可以标记已经遍历过的数据（对于有环图）
         *
         *
         */
    }

    /**
     * 堆和树的区别?
     */
    public void a3_8(Context context) {
        /*
         * 堆是一种特殊的树（我们通常讲的堆就是二叉堆）
         * 当一颗二叉树中的节点都大于等于他们的子节点的时候，这个二叉树是“堆有序”的
         * “堆有序”的“完全二叉树”叫做“二叉堆”，“二叉堆”简称为“堆”
         * 二叉堆的特性使得他可以用来实现优先队列。
         */
        context.getResources().getDrawable(R.drawable.tree);
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


    /**
     * 描述一次网络请求的流程?
     */
    public void a7_1(Context context) {
        /*
         * 1, 请求dns
         * 封装好http请求，用网络框架请求，比如Android原生的HttpUrlConnection
         * 或者OkHttp（这些底层都是用的Socket来请求）
         * 网络框架请求dns服务器，然后返回域名的ip地址
         * 2,建立Tcp\ip连接
         * 通过三次握手来确认双方都准备好接收和发送请求了。
         * 3.发起请求
         * 然后用Tcp/ip协议（Socket实现）请求对方ip，
         * 经过Socket封包，根据TCP协议封包，根据Ip协议封包，传入数据链路层
         * 我们的网卡会将请求，通过网线或者WiFi发到路由，然后路由通过转发
         * 达到指定ip的主机，主机通过拆包，发给指定 进程（比如tomcat）（它监听了端口号）
         * 4.接收到请求和返回响应
         * 这个进程接收到数据后进行拆包，最终返回http请求交给上层应用处理
         * 处理完后将结果写入响应流，客户端接收到响应流
         * 5.结束Tcp\ip连接
         *  用四次挥手，服务端释放连接资源（节省内存）
         *
         */
        context.getResources().getDrawable(R.drawable.http);
    }

    /**
     * 说说OSI七层模型 和 TCP/IP五层模型
     */
    public void a7_2_1(Context context) {

        /*
         * OSI 开放式系统互联通信参考模型
         * (Open System Interconnection Reference Model)
         * 由国际标准化 组织提出
         * */

        context.getResources().getDrawable(R.drawable.osi_7);

    }

    /**
     * 说说三次握手和四次挥手？
     * 为什么要三次握手？不是两次？
     * 为什么要四次挥手？
     * 什么是全双工/半双工？
     * https://github.com/jawil/blog/issues/14
     * https://hit-alibaba.github.io/interview/basic/network/TCP.html
     */
    public void a7_2(Context context) {
        /*
         * 三次握手和四次挥手是Tcp协议中定义，所以他们是建立Tcp连接和断开的基石
         * 利用TCP头部的SYN ACK标记来进行三次握手、
         *
         * 三次握手，先是client向sever发一个tcp请求，SYN标记位为1，告知服务器请求tcp连接
         * 然后sever返回ACK标记位为1 （这时表明sever是在线的）
         * 然后客户端收到sever的数据后，再发送一个tcp请求
         * （表明client知道了sever是在线的，所以client也维持在线的）
         *
         *
         * ================为什么要三次握手？不是两次？=======================
         * https://blog.csdn.net/xifeijian/article/details/12777187
         * 为了防止已失效的连接请求报文段突然又传送到了服务端，因而产生错误，浪费服务端资源
         *
         * 第一次握手，被阻塞在网络节点很长时间，在失效后才到达服务端
         * 那么服务端会认为这是一个新的连接请求，然后给客户端发送消息建立连接
         * 然后等待数据，但是这个时候客户端这个连接已经失效，所以服务端会一直等待
         * 浪费资源
         * ----------------
         * 如果两次握手，那么服务端就可能不知道client是否在线，（client不知道自己在线）
         * 造成自己不知道该不该保持这个链接来等待client的数据，就造成资源浪费
         * （如果两次握手sever不知道自己的第二次握手是否传到client，
         * 所以他不知道还该不该等待client的数据）
         *
         * =============为什么要四次挥手？===================
         * https://blog.csdn.net/xifeijian/article/details/12777187#commentsedit
         * 因为tcp是全双工的，就是数据可以双向传输
         * 所以客户端停止传输，需要发送一个请求，然后服务端一个响应（代表服务端知道客户端不会再发送数据了）
         * 服务端停止传输，也需要向客户端发送一个请求，然后客户端一个响应（代表客户端知道服务端不会发送数据了）
         * 至此tcp链接断开
         * 所以断开全双工的连接需要四次挥手
         *
         * ===============什么是全双工/半双工？============
         * 全双工： [1]  指可以同时（瞬时）进行信号的双向传输（A→B且B→A）。指A→B的同时B→A，是瞬时同步的。
         *   半双工：指一个时间内只有一个方向的信号传输（A→B或B→A）
         *
         */
        context.getResources().getDrawable(R.drawable.tcp_shake_hand);
        context.getResources().getDrawable(R.drawable.tcp_connection_made_three_way_handshake);
        context.getResources().getDrawable(R.drawable.tcp_connection_closed_four_way_handshake);
    }


    /**
     * HttpUrlConnection 和 okhttp关系?
     * <p>
     * http://www.maplejaw.com/2016/07/22/HttpURLConnection-%E6%BA%90%E7%A0%81%E8%A7%A3%E8%AF%BB/
     */
    public void a7_3() {
        /*
         * 都是实现Http协议的网络请求框架，
         * HttpURLConnection是个抽象类，android4.4后
         * HttpURLConnection使用OkHttp来实现的。
         */
    }

    /**
     * 什么是WebSocket?
     * 如何在Android中使用WebSocket?
     * <p>
     * https://www.liaoxuefeng.com/wiki/001434446689867b27157e896e74d51a89c25cc8b43bdb3000/001472780997905c8f293615c5a42eab058b6dc29936a5c000
     */
    public void a7_4() {
        /*
         * webSocket也是一种协议，基于tcp的，使用这种协议必须要服务端和客户端都实现相应的逻辑
         * 也是基于tcp协议，提供双向的数据通道
         * http协议是一次请求和响应后就断开连接了，webSocket是可以保持长连接的
         * 首先客户端用socket发送请求
         * GET ws://localhost:3000/ws/chat HTTP/1.1
         * Host: localhost
         * Upgrade: websocket
         * Connection: Upgrade
         * Origin: http://localhost:3000
         * Sec-WebSocket-Key: client-random-string
         * Sec-WebSocket-Version: 13
         * ----------------------
         * 该请求和普通的HTTP请求有几点不同：
         *   GET请求的地址不是类似/path/，而是以ws://开头的地址；
         *   请求头Upgrade: websocket和Connection: Upgrade表示这个连接将要被转换为WebSocket连接；
         *   Sec-WebSocket-Key是用于标识这个连接，并非用于加密数据；
         *   Sec-WebSocket-Version指定了WebSocket的协议版本。
         * -------------------
         * 然后服务器响应
         * HTTP/1.1 101 Switching Protocols
         * Upgrade: websocket
         * Connection: Upgrade
         * Sec-WebSocket-Accept: server-random-string
         *
         * 浏览器和服务器就可以随时主动发送消息给对方。
         * 消息有两种，一种是文本，一种是二进制数据。通常，我们可以发送JSON格式的文本
         *
         * Android4.4以上支持
         *
         * =====================如何在Android中使用WebSocket?======================
         * https://blog.csdn.net/moxiouhao/article/details/77840168
         *
         *
         *
         */
    }

    /**
     * 从网络加载一个10M的图片，说下注意事项?
     */
    public void a7_5() {
        /*
         * 使用断点下载，先存储到磁盘中，然后通过inSampleSize来进行合理的缩放
         * 再加载到内存中
         */
    }

    /**
     * TCP与UDP的区别?
     */
    public void a7_6() {
        /*
         * TCP：
         * 1面向连接 ，建立连接需要开销较多(时间，系统资源)
         * 2传输可靠(保证数据正确性,保证数据顺序（有标记序号）)、
         * 3传输是面向字节流，用于传输大量数据(流模式)
         * 4，tcp报文首部需要20字节
         * 5。一对一，端口对端口的
         * UDP：
         * 1面向非连接，速度快，没有拥塞机制
         * 2传输不可靠、
         * 3传输面向数据报，用于传输少量数据(数据包模式)
         * 4 首部需要8个字节
         * 5可以广播一对多
         */
    }


    /**
     * HTTP1.0与1.1与2.0的区别?
     * SPDY 是什么？
     * https://www.jianshu.com/p/52d86558ca57
     * https://www.zhihu.com/question/34074946
     */
    public void a7_7() {
        /*
         * ===================1.1 和1.0的区别===========================
         * 1：添加了请求头
         * Http1.1新增了Host请求头，这就使得我们可以在同一IP的同一端口来指定不同的服务主机
         * 新增了Connection请求头，keep-alive表示不关闭tcp连接，这就使得一次tcp连接能发送多个http请求
         *       close表示关闭
         * 添加了range请求头，支持了断点下载
         * 添加了cache-control请求头，优化了服务器时间和客户端时间不一致导致缓存过期或者不过期的问题
         * 2.添加了响应码
         * 101 表示切换了协议，支持了WebSocket
         * 412 比如断点下载的ETag不匹配，返回错误
         * ==========================2.0和1.x的区别=======================
         * 互联网工程任务组（IETF）2015年5推出
         * 1.单一长连接，向单个域名的请求，会用一个tcp连接，这样就减少三次握手带来的开销
         *   而且克服了tcp慢启动（一开始会会限制最大传输速度，如果响应成功，会提高传输速度）
         *   (同一个tcp连接中发送多个http请求)
         *
         * 2.（多路复用）head of line blocking 是http1.x的特性，一个请求没有响应之前，后面的请求将会阻塞
         *      （浏览器或者手机会限制同一个域名的并发请求的数量，一般是6-8个，如果还有请求，那么必须等待）
         *
         *   所以我们用二进制分帧的形式将多个请求合并为一个，每个请求编号，响应也是如此
         *   这样就提高了并发度
         *   二进制分帧（Frame），在Http协议和Tcp协议之间加入 二进制分帧层（Binary Framing）
         *   将 多个http请求头加入到一个HEADER frame 中，将多个请求体加入 一个 DATA frame 中
         *   然后服务端响应后再拆开
         *
         *
         * 3.首部压缩 ，SPDY 使用的是通用的DEFLATE 算法，而 HTTP/2 则使用了专门为首部压缩而设计的 HPACK 算法
         *   将http请求头由纯文本压缩
         *
         * 4.服务端推送（Server Push、Cache Push），一个请求可以有多个响应，比如我们请求A，那么服务端可以
         *   响应A后，再返回响应B,这样就相当于主动推送了（这在首页初次请求很多资源时很有用）
         *
         * ===================SPDY 是什么？===================
         * （SPeeDY）快速的，是Google用来加快网络速度，降低延迟。
         * SPDY并不是一种用于替代HTTP的协议，而是对HTTP协议的增强（所以是基于tcp的应用层协议）
         * 其实SPDY是在http和tcp中间，http请求无需改变，然后交给spdy，然后再交给tcp
         *
         * 是google为了改进Http1.x的缺陷来制定的协议，后来Http2.0出来，Google就不维护这个协议了
         * 全力支持Http2.0，Http2.0也是根据这个协议改进而来
         *
         */
    }

    /**
     * HTTP与HTTPS的区别
     * https何实现安全性?/tls层的实现原理?
     * 如何验证证书的合法性?
     * https中哪里用了对称加密，哪里用了非对称加密？
     * 对加密算法（如RSA）等是否有了解?
     * http://www.techug.com/post/https-ssl-tls.html
     * https://www.barretlee.com/blog/2015/10/05/how-to-build-a-https-server/
     */
    public void a7_8(Context context) {
        /*
         * ============HTTP与HTTPS的区别=====================
         * 总的区别就是安全 https并不是修改了http协议，而是http协议加上了tls协议或者ssl协议
         * 1.https是在http下层加入了tls层（传输层安全协议）
         * 2 url头部不同，
         * 3 端口不同，（一个80端口，一个433端口）
         * 4 http内容明文，https是密文
         * ================https何实现安全性?/tls层的实现原理=================
         * https还需要四次？握手
         *
         * 1客户端告知服务端可使用的 几套加密算法（对称加密+非对称加密+hash算法）
         * 2.服务器返回选择的一套加密算法，和数字证书（CA颁发，里面用CA私钥加密了服务器的公钥和网站信息）
         * 3.客户端接收到，验证数字证书的合法性，用正规CA机构的公钥解密，成功则数字证书合法
         *   解密后得到服务器的公钥。
         *   此时客户端生成随机密码，然后用公钥加密，发送给服务端
         * 4.服务端 用私钥解密 得到随机密码。（此时只有客户端和服务端知道随机密码）
         *   然后服务端将握手消息用随机密码加密发送
         * 5.客户端用随机密码解密 握手消息。此时tls握手完成，客户端以后发送的数据都用随机密码加密后传输
         *
         * 一开始非对称加密用来传输 对称加密用的随机密码
         * 后面双方都有随机密码了，就用对称加密
         *
         * ======================如何验证（数字）证书的合法性?==============
         * 浏览器内置根证书，验证数字证书链接合法性（用公钥解密，能成功就是合法）
         * 或者去指定CA机构获取公钥解密
         * =================https中哪里用了对称加密，哪里用了非对称加密？===========
         * 随机密码传递给服务器用了非对称加密
         * 内容的传输用了对称加密
         *
         */
        //图片中的key就是客户端生成的随机密码（随机秘钥）
        context.getResources().getDrawable(R.drawable.http_hand_shake_1);
    }


    //endregion

    //region JVM知识

    /**
     * 说说有几种jvm
     *
     * */
    public void a8_0() {

        /*
        * https://www.zhihu.com/question/29265430
        *
        * HotSpot VM
        * JRockit VM
        * J9 VM
        *
        * 自从Oracle把BEA和Sun都收购了之后，Java SE JVM只能二选一，JRockit就炮灰了
        * 。
        * 不同的jvm有不同的gc算法，所以我们一般都是基于HotSpot来说的
        * */
    }

    /**
     * 虚拟机中的内存分配？回收机制
     * 哪些情况下的对象会被垃圾回收机制处理掉? /jvm垃圾回收机制是怎样的?
     * 如果老年代引用了新生代的对象，而此时触发young gc，新生代是否会被回收？
     *
     * <p>
     * https://yunfengsa.github.io/2015/11/12/android-jvm-gc/
     * https://www.cnblogs.com/zhguang/p/3257367.html
     *
     * https://blog.csdn.net/CSDN_Terence/article/details/77771429 JVM内存划分、JVM内存分配机制、JVM垃圾回收机制
     */
    public void a8_1() {
        /*
         * =====java内存划分
         *
         * 1 堆(Heap)
         *  用于存储Java对象
         * 2 Java栈(VM Stack)
         *  每个线程创建的时候，JVM都会为他分配一个对应的Java栈，这个栈含有多个栈帧；栈帧则是个方法关联，每个方法的运行都会创建一个自己的栈帧，含有内存变量，操作栈、方法返回值
         * 3 本地方法栈(Native MethodStack)
         *  是为JVM运行Native方法（本地方法：非java语言编写的方法，被编译成和处理器相关的代码）准备的空间
         * 4 方法区(Method Area)
         *  用于存储类结构信息
         *  class文件加载进JVM时会被解析成JVM识别的几个部分分别存储在不同的数据结构中：
         * 常量池、域、方法数据、方法体、构造函数，包括类中的方法、实例初始化、接口初始化等
         *
         *  1常量池（Constant Pool）：常量池数据编译期被确定，是Class文件中的一部分。存储了类、方法、接口等中的常量，当然也包括字符串常量。
         *  2运行时常量池（Runtime Constant Pool）：方法区的一部分，所有线程共享
         *  3字符串常量池（String Pool/String Constant Pool）：是常量池中的一部分，存储编译期类中产生的字符串类型数据。
         *
         * 5 程序计数器(ProgramCounter Register)
         *  用于记录下一条要执行的字节码指令地址和被中断地址
         *
         * java栈和PC寄存器（程序计数器）是线程私有，每个执行引擎启动时都会创建自己的java栈和PC寄存器
         *
         *  ===============jvm内存回收采用的是基于分代的垃圾收集算法
         * 虚拟机中的内存区域分为新生代和老年代，新分配的对象被存储在新生代中
         * 当需要申请内存而内存空间不足时，就触发Minor GC（或叫Young GC）
         * 来回收新生代的内存，Minor（新生的）
         * 如果没被回收的对象就讲它年龄+1，一般到15的时候对象会被转移到老年代
         * （这里指的是最大15，如果新生代满了就会移动到老年代）
         * 老年代占满，会触发Major GC（也叫 Full GC），回收老年代的垃圾对象
         *
         * Perm（持久代）
         * 用于存放类的Class文件或静态文件，如Java类、方法等，垃圾回收是由FullGC触发的
         *
         *
         * ==================标记算法==================
         * 标记所有垃圾对象，那么标记的算法有两种：引用计数法和根搜索法
         * 引用计数法：如果对象被引用，则引用计数器加1，如果为0则是垃圾对象
         *   但是主流vm都没有采用这种算法，因为如果循环引用，那么这种算法则不起作用
         *
         * 根搜索法：将一些对象视作根对象，从根对象开始遍历引用，遍历到的就是可达的对象
         *   所以我们可以将不可达的对象清除
         * 如何选择根对象：
         *   Java栈中的引用的对象。
         *   本地方法栈中JNI引用的对象。
         *   方法区中运行时常量池引用的对象。
         *   方法区中静态属性引用的对象。
         *   运行中的线程
         *   由引导类加载器加载的对象
         *   GC控制的对象
         *   回收新生代时老年代持有他的引用，那么老年代也可作为gc root
         *
         * 当对象处于不可达状态，且垃圾回收器会调用他的finalize方法
         * 如果调用完finalize（），对象还是不可达，那么他将被永久回收
         *
         * 上面是一个对象如何被标记为垃圾对象的算法，下面是这些垃圾的回收算法
         * =======================回收算法================================
         * 1.标记-清除算法：标记后，直接将标记的内存区域清空
         *   缺点就是产生大量碎片内存，没有足够的连续内存分配给较大的对象，这样就触发下一次gc
         *   导致频繁gc
         * 2.标记-复制（停止-复制）算法，将内存分为两个区域，
         *   只在一个区域中分配内存，标记回收后，将剩下的对象
         *   复制到另一个区域，这样内存使用就连续了。缺点就是效率低，只能使用一半内存，而且
         *   如果内存中有大量存活对象，那么复制耗时较长（所以这种算法一般用于新生代中，因为
         *   新生代内存中对象存活率低）
         * 3.标记-压缩（整理）算法：标记回收后，将存活的对象压缩到内存的一端，这样解决了碎片化问题，
         *   也解决了内存使用率低的问题。（这种算法广泛应用于老年代中）
         *
         * =========================分代收集==========================
         * 1.将java堆分为新生代Young Generation，和老年代Tenured Generation，
         *   这样提高了回收的命中率
         *   新生代又可以分为Eden，from survivor,to survivor，三个空间
         *   新生代：老年代=1:2    Eden，from survivor,to survivor=8:1:1
         *   from survivor,to survivor这两个空间只能有一个可用
         * 2.两种收集类型，一种Minor GC ，一种Full GC(也叫Major GC)
         *   Minor GC，收集eden空间，和from survivor,to survivor中的一个
         *   然后将存活的对象复制到from survivor,to survivor中的另一个中
         *   （这就是复制算法），每在Minor GC中存活的对象年龄加1，
         *   直到年龄到15岁对象会被移动到老年代，
         *    默认是 15 岁（这里）可以通过参数 -XX:MaxTenuringThreshold 来设定
         *   Full Gc是老年代满的时候触发的，他采用标记-压缩算法来回收老年代
         *
         *
         *
         * ======如果老年代引用了新生代的对象，而此时触发young gc，新生代是否会被回收？=============================
         * 可能存在年老代对象引用新生代对象的情况，如果需要执行Young GC，
         * 则可能需要查询整个老年代以确定是否可以清理回收，这显然是低效的。
         * 解决的方法是，年老代中维护一个512 byte的块——”card table“，
         * 所有老年代对象引用新生代对象的记录都记录在这里。Young GC时，
         * 只要查这里即可，不用再去查全部老年代，因此性能大大提高。
         *
         *
         * ===============================
         * 方法区 （就是永久代）
         * 比如静态属性，常量
         *
         */
    }

    /**
     * java的安全性如果保证？/说说java安全模型
     * 说说java沙箱机制？
     * jvm内存模型？
     * ---------------------------------------
     * 《深入理解java虚拟机》
     */
    public void a8_2() {
        /*
         * ==============java的安全性如果保证？/说说java安全模型=================
         * 为了保证任何不可靠来源（比如网络）的代码对java原有逻辑进行破坏
         * 所以java建立了沙箱安全模型
         * ==============说说java沙箱机制？========================
         * 由四个部分组成
         * 1.类加载器结构（双亲委托模型）
         * 2.class文件检查器
         * 3 。
         *
         * --------------加载原理-------------------
         * 从磁盘中读取相应的class文件，然后获取输入流，然后用native方法
         * 来生成Class对象，从而完成Class文件加载（到jvm的内存中）
         * 优点：
         * 1，可以防止类重复加载
         * 2，保证安全性，比如我们自己定义了一个java.lang.String，那么
         *   没有这个机制就会加载我们的类，导致我们的String类能访问java.lang
         *   中可以包内访问的成员变量
         *
         * ==============jvm内存模型======================
         * http://liuwangshu.cn/java/jvm/1-runtime-data-area.html
         *
         */
        //系统类加载器
        ClassLoader.getSystemClassLoader().getParent();
        //线程上下文类加载器，这个获取到与当前程序相同的类加载器
        // 一般是AppClassLoader ,但是在java web环境中可能不是
        Thread.currentThread().getContextClassLoader();
    }

    /**
     * 说说类加载器？/说说类加载器的双亲委托模型？
     */
    public void a8_3() {
        /*
         *   * ===========说说类加载器的双亲委托模型？====================
         * https://blog.csdn.net/javazejian/article/details/73413292
         * https://www.ibm.com/developerworks/cn/java/j-lo-classloader/
         *
         * 我们有一个启动类加载器/引导类加载器（BootStrap ClassLoader），它是一个独立的类加载器，由C++实现
         * 还有拓展类加载器，系统类加载器
         * BootStrap ：由c++实现，负责将 <JAVA_HOME>/lib路径下的核心类库或
         *   -Xbootclasspath参数指定的路径下的jar包加载到内存中，注意必由于虚拟机是按照文件名识别加载jar包的，
         *   如rt.jar，如果文件名不被虚拟机识别，即使把jar包丢到lib目录下也是没有作用的
         *   (出于安全考虑，Bootstrap启动类加载器只加载包名为java、javax、sun等开头的类)。
         *
         * Extension（ExtClassLoader.java）： 扩展类加载器是指Sun公司(已被Oracle收购)实现的sun.misc.Launcher$ExtClassLoader类，
         *   由Java语言实现的，是Launcher的静态内部类，它负责加载<JAVA_HOME>/lib/ext目录下
         *   或者由系统变量-Djava.ext.dir指定位路径中的类库，开发者可以直接使用标准扩展类加载器
         *
         * System(AppClassLoader.java) 应用程序加载器是指 Sun公司实现的sun.misc.Launcher$AppClassLoader。它负责加载系统类路径java -classpath
         * 或-D java.class.path 指定路径下的类库，也就是我们经常用到的classpath路径，这个加载器用来加载用户的类
         * 开发者可以直接使用系统类加载器，一般情况下该类加载是程序中默认的类加载器，
         * 通过ClassLoader#getSystemClassLoader()方法可以获取到该类加载器
         * 这个加载器之前会先请求他的父类
         *
         *
         * 以上都是sun/misc/Launcher.class的静态成员内部类，都是URLClassLoader的子类
         * 在Launcher中先创建ExtClassLoader，然后将它作为parent创建AppClassLoader
         *
         * BootStrap 《- ExtClassLoader《- AppClassLoader
         * 他们虽然不是继承关系 ，但是通过getParent（）方法获取到的就是这个关系
         * 这个parent是在构造方法中传入的
         */
    }

    /**
     * new一个对象所经历的步骤
     * （判断是否加载，内存分配等等等）
     * https://zhuanlan.zhihu.com/p/86724942
     *
     * 1.现根据类名在常量池里面找到有没有这个类的符号引用。（方法区）
     *
     * 2.如果有这个符号引用，则需要检查这个类的符号引用有没有被加载、解析、初始化过。
     *
     *  2.1 如果没有这个符号引用，则对这个符号进行类的加载过程。（classlaoder）
     *
     * 3.虚拟机为对象在堆中分配内存，所需要的内存在类加载之后即可知道。
     *
     * 4.将分配到的内存空间都初始化为0，（不包括对象头）。
     *
     * 5.对对象头进行设置，对象hash值、GC
     *
     * 6.执行对象的init方法
     *
     *
     * 通过classloader来完成 加载、校验、准备、解析、初始化
     * 把class的二进制内容加载到虚拟机的方法区，在内存中生成一个java.lang.class杜象表示这个class
     *
     * 加载一个Class需要完成以下3件事：
     *
     * 通过Class的全限定名获取Class的二进制字节流
     * 将Class的二进制内容加载到虚拟机的方法区
     * 在内存中生成一个java.lang.Class对象表示这个Class
     * 获取Class的二进制字节流这个步骤有多种方式：
     *
     * 从zip中读取，如：从jar、war、ear等格式的文件中读取Class文件内容
     * 从网络中获取，如：Applet
     * 动态生成，如：动态代理、ASM框架等都是基于此方式
     * 由其他文件生成，典型的是从jsp文件生成相应的Class
     *
     * 准备：
     *
     * 为static中的变量初始化默认值0或者null
     *
     */
    public void a8_4(){}

    //endregion

    //region java8

    /**
     * 说说接口中的default method
     * https://blog.idrsolutions.com/2015/01/java-8-default-methods-explained-5-minutes/
     */
    public void a9_1() {
        /*
         * java8的特性之一，当我们在接口中新添加一个方法，那么他的实现类肯定要报错
         * 直到我们的实现类实现了他，那么这种体验不友好，所以就有了default method
         * 当一个方法被default关键字修饰的时候，那么他的实现类不一定要实现它
         * 当然我们实现类实现了方法，还想调用接口中的实现方法，Interface.super.Foo()
         *
         * 所以在java8中接口中也能有实现的方法了
         */
    }

    /**
     * lambda表达式 lambda是java8c出来的，其实就是简化函数定义，函数也可以作为参数传递
     * http://www.runoob.com/java/java8-lambda-expressions.html
     * http://www.importnew.com/16436.html (很详细的例子)
     *
     * ===============java 方法引用 Method references==========
     * http://zh.lucida.me/blog/java-8-lambdas-insideout-language-features/ （深入理解Java 8 Lambda（语言篇——lambda，方法引用，目标类型和默认方法））
     *
     * 静态方法引用：ClassName::methodName
     * 实例上的实例方法引用：instanceReference::methodName
     * 超类上的实例方法引用：super::methodName
     * 类型上的实例方法引用：ClassName::methodName
     * 构造方法引用：Class::new
     * 数组构造方法引用：TypeName[]::new
     *
     *
     */
    public void a9_2() {
        /*
         * (params) -> expression //只有一个表达式，默认是返回值
         *  (params) -> statement
         *  (params) -> { statements }
         */
        new Thread(() -> System.out.println("")).start();
        new View(null).setOnClickListener(v -> {//一个参数不用写圆括号
            Object tag = v.getTag();
            System.out.println(tag);
        });
        //方法体只有一个表达式，不用return,不用花括号
        Collections.sort(new ArrayList<Integer>(), (o1, o2) -> o1 - o2);
        Collections.sort(new ArrayList<Integer>(), (o1, o2) -> {
            int i = o1 - o2;//有一个以上的表达式，需要花括号和return
            return i;
        });

        //数组遍历
        ArrayList<String> strings = new ArrayList<>();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            //foreach简化处理
            strings.forEach(s -> System.out.println(s));
            strings.forEach(TextUtils::isEmpty);//自动引用参数，这个参数自动传入里面了
        }

        //断言
        ArrayList<String> list = new ArrayList<>();
        filter(list, str -> str.length() > 0);
        filter(list, str -> str.startsWith("j"));


    }

    /**
     * lambda表达式
     */
    private void filter(ArrayList<String> list, Predicate<String> condition) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            list.forEach(s -> {//过滤打印
                if (condition.test(s)) {
                    System.out.println(s);
                }
            });

            //流式处理,过滤操作，filter后是一个新的列表
            list.stream().filter(condition).forEach(System.out::print);

            //重新定义一个Predicate
            list.stream().filter(s -> s.length() > 1).forEach(System.out::print);

            //两个Predicate合并
            Predicate<String> stringPredicate = s -> s.length() > 1;
            list.stream().filter(stringPredicate.and(condition)).forEach(String::toCharArray);

            //map操作，使得数据流都经过Function处理
            list.stream().map(s -> s = s + "1").forEach(System.out::print);

            //reduce操作，减少/合并操作 ,将所有的string都相加并返回
            //每个元素都合并，合并后和其余的元素操作
            String s3 = list.stream().map(s -> s = s + "1").reduce((s1, s2) -> s1 + s2).get();
            System.out.println(s3);

            //返回过滤后的集合
            List<String> collect = list.stream().filter(s -> s.length() > 2).collect(Collectors.toList());
            collect.forEach(System.out::print);

            //Collectors 是将流中的元素经过处理移动到另一个对象中，
            String s1 = list.stream().map(s -> s + "1").collect(Collectors.joining(","));


            //distinct 去重 ，去除流中重复的数据
            List<String> strings = list.stream().distinct().collect(Collectors.toList());

            //::是只有一个参数，返回值，所以默认能识别
            //将string流转为int流
            IntSummaryStatistics statistics = list.stream().mapToInt(Integer::parseInt).summaryStatistics();
            double average = statistics.getAverage();
            int max = statistics.getMax();


        }

    }


    //endregion

    //region Java IO/NIO

    /**
     * 说说NIO
     * http://www.importnew.com/22623.html
     * https://tech.meituan.com/nio.html
     */
    public void a10() {
        /*
         * Non-Blocking IO
         * 传统的IO是阻塞式的，我们等待新的连接，等待连接的输入流，都是阻塞的操作
         * 这个时候线程不能做其他的事情，所以我们传统IO模型都是用线程池来解决
         * 但是当高并发的时候，多个线程很消耗资源，包括内存分配，线程调度
         * 而NIO就是非阻塞式的，他提供了选择器，这个是阻塞的，当有数据或者连接到来
         * 时，会通知当前线程去处理，
         *
         */
    }

    /**
     * FileWriter和BufferedWriter区别和用法
     * 如果同时使用2者，那么性能会大大提高，而单独使用FileWriter操作字符，每写一次数据，磁盘就有一个写操作，性能很差
     * 如果加了缓冲，那么会等到缓冲满了以后才会有写操作，效率和性能都有很大提高。
     *
     *  只有Bufferedxxx 才实现了 flush方法
     *  BufferedWriter 、BufferedOutputStream
     *  看源码，这个是在java层加了缓存，然后达到一定大小后一次写入磁盘
     *
     * ===========什么时候使用 Buffered?======
     * have multiple writes between flush/close
     * the writes are small compared with the buffer size.
     * 在 flush/close 之间多次调用writes
     * 每次写入的数据很小，可以用缓冲
     *
     *
     */
    public void a10_1(){

    }

    /**
     * IO 中的flush()方法
     * https://blog.csdn.net/dabing69221/article/details/16996877
     * 只有Bufferedxxx 才实现了 flush方法
     *
     * 此方法将缓冲区的数据写入磁盘
     *
     */
    public void a10_2(){

//        new BufferedOutputStream().flush();
    }

    /**
     * Java IO的结构与区别
     * https://blog.csdn.net/lexang1/article/details/77647545
     * 见java_io.jpg
     *
     * 以字节为导向的 stream——InputStream/OutputStream
     * 以字符为导向的 stream Reader/Writer
     * 表示以 Unicode 字符为单位从 stream 中读取或往 stream 中写入信息
     *
     *  Unicode 字符 2 个字节
     *
     *
     */
    public void a10_3(){}
    //endregion



    //region Java 代码规范
    /**
     * https://rules.sonarsource.com/java （代码规范讲解网站）
     *
     * 不要在finally块中使用return关键字
     * try{
     *             System.out.println("before return in try section.");
     *             return x+y;
     *         }finally{
     *             System.out.println("before return in finally section.");
     *             return x*y;
     *         }
     *  ————————————————
     * 版权声明：本文为CSDN博主「陈字文」的原创文章，遵循CC 4.0 by-sa版权协议，转载请附上原文出处链接及本声明。
     * 原文链接：https://blog.csdn.net/ziwen00/article/details/39318317
     *
     * 上面语句会返回x*y
     * 因为 调用try代码块的return之前就会去执行finally里面的代码，这样finally的return就会结束当前的方法，
     * 而不执行try中的return（如果有），从而返回一个错误的值
     *
     *
     *=========Double-checked locking should not be used========================
     * https://my.oschina.net/andylucc/blog/668919 （可以不要再使用Double-Checked Locking了
     * https://rules.sonarsource.com/java/tag/multi-threading/RSPEC-2168 （Double-checked locking should not be used）
     * 因为编译优化 导致的，对象已经被创建，但是里面的成员对象没有被初始化，这个时候第二个线程使用了对象的成员变量，因为没有初始化完成，所以报错
     *
     * 我们可以用静态内部类来实现 懒加载
     *
     * ================重写equals方法后重写hashCode方法的必要性 ==========
     * https://zhuanlan.zhihu.com/p/30321358 ( 重写equals方法后重写hashCode方法的必要性 )
     * https://rules.sonarsource.com/java/type/Bug/RSPEC-1206?search=equal ( "equals(Object obj)" and "hashCode()" should be overridden in pairs)
     * Object的 equals 是比较内存地址 ==   ，hashCode 是返回内存地址的hash值
     *
     * 因为要服从以下规则：
     * 1.如果两个对象  equals 比较相同，那么他们的 hashcode也应该相同，如果不同则不同
     * 2.不相等的对象产生不相等的hash值能提高哈希表的性能 （比如HashMap）
     *
     *
     *
     *
     */
    void a36(){}

    //endregion
}
