package com.liyafeng.practice;

/**
 * Created by liyafeng on 2018/3/13.
 */

public class Main {

    /**
     * 猿酒馆面试题
     * https://github.com/JackyAndroid/AndroidInterview-Q-A/tree/master/chinese
     *
     * @param args
     */
    public static void main(String[] args) {

        // 2023.12.11 复习计划
        //先把github 原来的知识点看完，包括算法题，然后根据面试点来复习
        //
        //***项目架构（mvc,mvp，mvvm）,android架构 （4层）
        //***数据结构 （list map set  tree 红黑树，二叉树， 堆（大根堆，小根堆），）
        //设计模式（21个）
        //***算法（查找算法，排序算法，各种算法题）
        //***性能优化（内存，cpu，网络）
        //***jvm（类加载机制，内存管理）
        //***通信机制（handler，binder，aidl）
        //***kotlin熟悉。
        //***多线程(创建，线程池，线程安全，锁)
        //***网络 socket ace（网络协议，socket，https，tcp udp，三次握手四次挥手，）
        //***自定义控件 ，绘制，事件冲突
        //***第三方库 okhttp， retrofit ，glide ，rxjava，eventbus
        //*** 数据库  Sqlite Greendao
        //***linux系统知识，操作系统，cpu(见jni库)
        //*** ndk jni (是什么，怎么使用)


        //***架构 组件化，mvp ,mvvm
        //***性能优化（内存优化，内存泄漏，cpu优化， anr排查，卡顿优化，启动优化，网络优化，包大小优化，构建速度优化）
        //***安全性，（签名，混淆，加固，key so加密，秘钥动态下法）
        //***稳定性 （异常处理，性能优化，兼容性测试，监控告警）
        //***ui，自定义控件，开源控件（SmartRefreshLayout，pannellayout，齿轮选择控件，自定义尺子控件，标签控件，转轮控件，banner）
        //滑动冲突（）
        // RulerView（onDraw，onTouch，Scroller，VoleCityTracker，）
        // TagFlowLayout（onMeasure测量子控件 加起来和 warp_content ，onLayout测量子控件 left，）。
        //
        // ***多线程（开启，线程池，线程安全，锁，）
        // ***网络，json，http，http2.0，https，tcp ip
        // ***sql数据库
        // ***视频列表（RecycleView+单播放器） Exoplayer IjkPlayer
        // ***视频播放，横竖屏切换，抖音视频切换，图片分享，自定义手势
        // ***视频秒开，surface texture区别，
        //基本算法


    }


    /**
     *
     * Android 中的 Kotlin 是一种现代化的编程语言，它具有以下特点和优势：
     *
     * 1. 互操作性：Kotlin 可与 Java 无缝互操作，因此可以逐步将现有的 Java 代码迁移到 Kotlin，也可以在同一个项目中同时使用 Kotlin 和 Java 编写的代码。
     *
     * 2. 简洁性：Kotlin 代码通常比 Java 代码更为简洁，减少了样板代码的编写，提高了开发效率。
     *
     * 3. 安全性：Kotlin 提供了空安全（null safety）和类型推断等特性，可以减少空指针异常和类型转换错误。
     *
     * 4. 函数式编程：Kotlin 支持函数式编程范式，提供了诸如 lambda 表达式、高阶函数等特性，使得代码更为简洁和易读。
     *
     * 5. 协程：Kotlin 提供了协程（coroutine）支持，可以简化异步编程，使得处理并发任务更加方便和直观。
     *
     * 6. Android Studio 支持：Android Studio 对 Kotlin 提供了良好的支持，包括代码自动补全、调试、重构等功能。
     *
     * 总的来说，Kotlin 在 Android 开发中受到了广泛的欢迎，它的简洁性、安全性和现代化特性使得开发者能够更加高效地编写 Android 应用。
     *
     *
     * ===
     * 高阶函数是指可以接受其他函数作为参数，或者返回一个函数作为结果的函数。在 Kotlin 中，高阶函数是一种强大的编程工具，它可以用于简化代码、提高灵活性，并支持函数式编程范式。
     *
     * 以下是一个简单的示例，演示了一个接受函数作为参数的高阶函数：
     * fun operation(x: Int, y: Int, op: (Int, Int) -> Int): Int {
     *     return op(x, y)
     * }
     *
     * fun main() {
     *     val result = operation(10, 5) { a, b -> a + b }
     *     println(result)  // 输出 15
     * }
     * 在这个示例中，operation 函数接受一个函数 op 作为参数，这个函数接受两个整数参数并返回一个整数结果。在 main 函数中，我们调用 operation 函数并传入一个 lambda 表达式作为 op 参数，实现了对两个整数的加法操作。
     *
     * 除了接受函数作为参数，高阶函数还可以返回一个函数作为结果。这种特性使得在 Kotlin 中可以更加灵活地组合和使用函数，从而实现更加简洁和易读的代码。
     *
     * ===== 用协程异步读取文件，然后返回文件内容
     *
     * import kotlinx.coroutines.Dispatchers
     * import kotlinx.coroutines.withContext
     * import java.io.File
     *
     * suspend fun readFileAsync(filePath: String): String = withContext(Dispatchers.IO) {
     *     val file = File(filePath)
     *     if (file.exists()) {
     *         return@withContext file.readText()
     *     } else {
     *         return@withContext "File not found"
     *     }
     * }
     *
     * // 在其他协程作用域中调用 readFileAsync 函数
     * // 例如在 ViewModel 或者协程范围内
     * viewModelScope.launch {
     *     val fileContent = readFileAsync("path_to_your_file.txt")
     *     // 处理文件内容
     * }
     *
     * =====没有viewmodel
     * import kotlinx.coroutines.Dispatchers
     * import kotlinx.coroutines.runBlocking
     * import kotlinx.coroutines.withContext
     * import java.io.File
     *
     * fun main() {
     *     runBlocking {
     *         val fileContent = readFileAsync("path_to_your_file.txt")
     *         // 处理文件内容
     *     }
     * }
     *
     * suspend fun readFileAsync(filePath: String): String = withContext(Dispatchers.IO) {
     *     val file = File(filePath)
     *     if (file.exists()) {
     *         return@withContext file.readText()
     *     } else {
     *         return@withContext "File not found"
     *     }
     * }
     *
     * ==== 和线程的区别
     * 协程和线程都是用于并发编程的工具，它们之间的主要区别在于以下几点：
     *
     * 1. 调度方式：线程是由操作系统内核进行调度和管理的，而协程是由编程语言的运行时库进行调度和管理的。
     *
     * 2. 资源消耗：线程是操作系统级别的实体，创建和销毁线程会消耗较多的系统资源，
     *   而协程是在用户空间实现的轻量级线程，创建和销毁协程的开销较小。
     *
     * 3. 并发性：线程是并发执行的基本单位，每个线程都有自己的堆栈和上下文，
     * 而协程是在一个或多个线程上执行的，可以在同一个线程上执行多个协程。
     *
     * 4. 阻塞：线程在遇到阻塞操作时会被挂起，而协程可以通过挂起函数（如 delay）来暂停执行而不会阻塞线程。
     *
     * 5. 编程模型：使用线程编程通常需要考虑线程同步、锁和共享状态的问题，而协程提供了更加简洁和直观的编程模型，可以避免一些并发编程中常见的问题。
     *
     * 总的来说，协程相对于线程来说更加轻量级、高效，并且提供了更加灵活和直观的并发编程模型。
     *
     *
     * ==== 实现原理
     * Kotlin 协程的实现是基于 Kotlin 标准库中的 kotlinx.coroutines 模块。
     * 它的核心是基于挂起函数（suspending functions）和编译器插件来实现的。
     *
     * 在 Kotlin 协程中，挂起函数是指能够暂停执行并在稍后恢复的函数。这些函数可以通过 suspend 修饰符来声明。当调用一个挂起函数时，它会将当前的协程挂起，并在后续恢复执行。
     *
     * 编译器插件会将包含挂起函数的代码转换成状态机，以便在挂起和恢复时能够正确地保存和恢复执行状态。这样就实现了协程的挂起和恢复功能。
     *
     * 此外，Kotlin 协程还依赖于调度器（Dispatchers）来管理协程的调度和执行。调度器负责将协程分配到合适的线程上执行，并提供了不同的调度策略，如 IO、CPU 等。
     *
     * 总的来说，Kotlin 协程的实现依赖于挂起函数、编译器插件和调度器，通过这些组件的协同工作，实现了协程的挂起和恢复功能，以及灵活的并发编程模型。
     *
     */
    void a1() {

    }



}
