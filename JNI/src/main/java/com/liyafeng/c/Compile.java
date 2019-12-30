package com.liyafeng.c;

public class Compile {



    public static void main(String[] args) {

    }

    /**
     * 源代码---编译器编译---》目标文件（对应微处理器的汇编语言）------链接器--》可执行文件（比如.exe）
     *
     * ===============机器码的执行过程================
     * 电脑怎样执行编程语言的？ - 曾煜的回答 - 知乎
     * https://www.zhihu.com/question/29227521/answer/154819061
     * 参考最早的打孔编程，那个0101就是cpu可执行的机器码
     *
     * =========汇编器（Assembler）============
     *    汇编器（Assembler）是将汇编语言翻译为机器语言的程序
     *  汇编生成的是目标代码，需要经链接器（Linker）生成可执行代码才可以执行。
     *
     *  汇编语言是一种以处理器指令系统为基础的低级语言，采用助记符表达指令操作码，采用标识符表示指令操作数。
     *  作为一门语言，对应于高级语言的编译器，需要一个“汇编器”来把汇编语言原文件汇编成机器可执行的代码。
     *  常用的高级语言编译器有Microsoft公司的MASM系列和Borland公司的TASM系列编译器，还有一些小公司推出的或者免费的汇编软件包等
     *
     *  =========目标代码（object code）===========
     *  目标代码（object code）指计算机科学中编译器或汇编器处理源代码后所生成的代码，它一般由机器代码或接近于机器语言的代码组成。
     *
     *  中间代码可以是等价的汇编代码，也可以是其它类型的代码例如 JVM 的字节码
     *  最终处理中间代码的程序可以是一个对应平台的汇编器，也可以是一个解释器
     *
     * ============ 链接器 Linker============
     * http://blog.jobbole.com/96225/ (帮 C/C++ 程序员彻底了解链接器)
     * https://juejin.im/post/5cd8042151882568d154c4bb （链接器到底是如何工作的？）
     * 见drawable/link.jpg
     *
     * hello.c-预处理器>hello.i -编译器> hello.s -汇编器-> hello.o (可重定位目标文件) -链接器> hello.exe
     *
     * ===============可执行文件==============
     * mac和linux的可执行程序的扩展名可以是任意的，也可以没有扩展名，但是都被文件系统标志为可执行
     * （权限一般755 wrx-rx-rx 或者777 wrxwrxwrx，x表示可执行，参考chmod命令），图标是一个命令行窗口的样子
     *
     * 不过一般的应用程序是一个扩展名为.app的文件夹（权限也是一般755 wrx-rx-rx 或者777 wrxwrxwrx，其实很多文件夹都是这个权限），
     * 它的资源文件和可执行程序都在这个文件夹里面，当你双击这个文件夹的时候系统就知道你要打开这个程序（
     *
     *
     * =============宏=============
     * 所谓“宏（macro）”就是一套预先定义好的指令序列。每当汇编进行的时候，先预处理一次将宏等价的展开，然后再进行翻译。
     * 比如我们用N+数字表示  n加这个数字再乘这个数字
     *
     *
     * ===============编程语言进化===============
     * 对于编程语言，基本上是这样进化的：
     * 先用机器语言写出汇编器，然后就可以用汇编语言编程了，然后再用汇编语言编写汇编器。
     * 先用汇编语言写出 C 编译器，然后就可以用 C 语言编程了，然后再用 C 语言来写 C 编译器。
     * 有了 C 编译器与 C 语言，就可以在这个基础上再编写高级语言的编译器或解释器或虚拟机了。
     * 非 C 系语言，进化过程同上。
     * 至于操作系统，先用汇编语言写一个操作系统。Ken Thompson 干过这样的事，他用汇编语言以及他自创的一种解释性语言——B 语言写出来 unix 第一版，是在一台内存只有 8KB 的废弃的计算机上写出来的。然后 Dennis Ritchie 发明了 C 语言，然后 Ken 与 Dennis 又用 C 语言在一台更好的计算机——16 位机器上将 unix 重写了一遍。
     * 至于 Windows 系统，MS 先是买了 QDOS，然后又在 QDOS 里引入了一些 Unix 的元素，然后比尔·盖茨靠着这个买来的系统赚了一大笔钱，然后就在 DOS 系统上开发了 windows 3.1，windows 95 ……
     *
     *
     * ============BIOS===============
     * BIOS是Basic Input/Output System的缩写，中文：基本输入输出系统
     * 是在通电引导阶段运行硬件初始化，以及为操作系统和程序提供运行时服务的固件
     * 通电后先启动预制的BIOS，然后通过BIOS来读取u盘中的操作系统，写入硬盘
     *
     *
     */
    public void a1(){}

    /**
     * yasm
     * Yasm是英特尔x86架构下的一个汇编器和反汇编器
     * =========

     *
     */
    public void tools(){}



    /**
     * ====================gcc（GNU Compiler Collection）介绍==========
     * https://zh.wikipedia.org/wiki/GCC
     * GNU编译器套装（英语：GNU Compiler Collection，缩写为GCC），指一套编程语言编译器，以GPL及LGPL许可证所发行的自由软件，
     * 也是GNU计划的关键部分，也是GNU工具链的主要组成部分之一
     * GCC（特别是其中的C语言编译器）也常被认为是跨平台编译器的事实标准。
     * 原名为GNU C语言编译器（GNU C Compiler），因为它原本只能处理C语言。GCC在发布后很快地得到扩展，变得可处理C++。
     * 之后也变得可处理Fortran、Pascal、Objective-C、Java、Ada，Go与其他语言。
     *
     * 开始只能编译c后来支持c++ oc等语言的编译，成为一个工具集
     *
     * =============教程========
     * http://c.biancheng.net/gcc/ （GCC编译器（Linux gcc命令）30分钟入门教程）
     *
     *
     * ==================================
     * mac 自带编译软件，gcc/g++
     * gcc --version
     * g++ --help
     * <p>
     * =========gcc/g++区别========
     * 后缀为.c的，gcc把它当作是C程序，而g++当作是c++程序；后缀为.cpp的，两者都会认为是c++程序
     * 编译阶段，g++会调用gcc，对于c++代码，两者是等价的，
     * 但是因为gcc命令不能自动和C＋＋程序使用的库联接，所以通常用g++来完成链接，为了统一起见，干脆编译/链接统统用g++了
     *
     *  ========================gcc===================
     * 下面是GNU计划中开发的几个软件
     * Emacs 一款c编辑器
     * GNU C Compiler(gcc) GNU上的c语音编译器
     * GNU C Library (glibc)
     * Bash shell
     * --------------------------------
     * GNU编译器套装（英语：GNU Compiler Collection，缩写为GCC）
     * 因为它原本只能处理C语言。GCC很快地扩展，变得可处理C++。之后也变得可处理Fortran、Pascal)、Objective-C、Java、Ada，以及Go与其他语言。
     * 许多操作系统，包括许多类Unix系统，如Linux及BSD家族都采用GCC作为标准编译器。苹果电脑Mac OS X操作系统也采用这个编译器。
     * <p>
     * <p>
     * ================工作步骤==============
     * https://www.runoob.com/w3cnote/gcc-parameter-detail.html （GCC 参数详解）
     * gcc 与 g++ 分别是 gnu 的 c & c++ 编译器 gcc/g++ 在执行编译工作的时候，总共需要4步：
     *
     * 1、预处理,生成 .i 的文件[预处理器cpp]
     * 2、将预处理后的文件转换成汇编语言, 生成文件 .s [编译器egcs]
     * 3、有汇编变为目标代码(机器代码)生成 .o 的文件[汇编器as]
     * 4、连接目标代码, 生成可执行程序 [链接器ld]
     *
     *
     * https://www.cnblogs.com/qytan36/archive/2010/05/25/1743955.html (GCC编程四个过程:预处理-编译-汇编-链接)
     * gcc可以实现这四个步骤
     *
     * 链接
     * gcc hello.o –o hello.exe
     *
     * =================静态编译和动态编译=====================
     *  静态编译是将所有代码编译到一个可执行文件中，因此生成的文件比较大
     *  动态编译是生成一个可执行文件作为入口，其他的生成动态链接库
     *  运行的时候加载这个库。
     *
     * 在没有特别指定时，gcc会到系统默认的搜索路径”/usr/lib”下进行查找依赖的库
     * libc.so.6的库文件是系统中自带的
     * .a是静态库的后缀
     * .so是动态库的后缀
     *
     *
     *
     * ================gcc语法===================
     * https://blog.csdn.net/tomatofly/article/details/6035363 （简单的GCC语法： 弄清gcc test.c 与 gcc -c test.c 的差别）
     *
     * gcc –c test.c，表示只编译test.c文件，成功时输出目标文件test.o
     *
     * gcc –c test.c –o test.o ，与上一条命令完全相同
     *
     * gcc –o test test.o，将test.o连接成可执行的二进制文件test
     *
     * gcc –o test test.c，将test.c编译并连接成可执行的二进制文件test
     *
     * gcc test.c –o test，与上一条命令相同
     *
     * gcc –c test1.c，只编译test1.c，成功时输出目标文件test1.o
     *
     * gcc –c test2.c，只编译test2.c，成功时输出目标文件test2.o
     *
     * gcc –o test test1.o test2.o，将test1.o和test2.o连接为可执行的二进制文件test
     *
     * gcc –c test test1.c test2.c，将test1.o和test2.o编译并连接为可执行的二进制文件test
     *
     * 注：如果你想编译cpp文件，那么请用g++，否则会有类似如下莫名其妙的错误：
     *
     * cc3r3i2U.o(.eh_frame+0x12): undefined reference to `__gxx_personality_v0’......
     *
     */
    public void fun_gcc() {
    }


    /**
     *
     * ======================MinGW(Minimalist GNU for Windows)==========================
     * http://www.mingw.org/(官网)
     * https://mingw-w64.org/doku.php/download 下载
     * https://sourceforge.net/projects/mingw-w64/
     *
     * is a minimalist development environment for native Microsoft Windows applications
     * 将GNU的开发工具，移植到Windows
     *
     * 在windows中要编译c语音，需要特定编译环境
     * mingw Minimalist GNU for Windows
     *
     * MinGW includes:
     *
     * A port of the GNU Compiler Collection (GCC), including C, C++, ADA and Fortran compilers;
     * GNU Binutils for Windows (assembler, linker, archive manager)
     * A command-line installer, with optional GUI front-end, (mingw-get) for MinGW and MSYS deployment on MS-Windows
     * A GUI first-time setup tool (mingw-get-setup), to get you up and running with mingw-get.
     *
     *
     * c:\MinGW\bin\mingw32-make.exe
     * 吧c:\MinGW\bin加入环境变量就可以运行mingw32-make了
     *
     *
     * =================Cygwin====================
     * https://www.cygwin.com/ （官网）
     *
     * 1.a large collection of GNU and Open Source tools which provide functionality similar to a Linux distribution on Windows
     * 2.a DLL (cygwin1.dll) which provides substantial POSIX API functionality
     *
     * 提供Linux中gnu和一些开源工具，使他们可以运行在windows上
     * 比如make工具，gcc工具
     *
     * ==============区别=======
     * MinGW占用内存、硬盘空间都比较少，能够链接到任意软件，但它对POSIX规范的实现没有Cygwin库完备
     *
     */
    void fun_MinGW(){}




    /**
     * ==============make==================
     * https://www.gnu.org/software/make/ （官方文档）
     * https://www.gnu.org/software/make/manual/make.html （官方文档）
     * https://zhuanlan.zhihu.com/p/72616078 （浅谈 GNU Make 构建项目实践）
     * https://seisman.github.io/how-to-write-makefile/invoke.html (make 的运行)
     * https://wangchujiang.com/linux-command/c/make.html (make用法)
     * Make是最常用的构建工具，诞生于1977年，主要用于C语言的项目。
     *
     *
     * 工程大了，源文件就多了
     * 如此多的源文件，如果每次都要键入gcc命令进行编译的话，那对程序员 来说简直就是一场灾难。
     * 而make工具则可自动完成编译工作，并且可以只对程序员在上次编译后修改过的部分进行编译
     *
     * 其实make不只是用来构建c项目，构建其他项目也可以
     * 它只是一个有特定功能的软件，他会解析makefile这个文件，然后执行特定的逻辑
     *
     * target … : prerequisites(先决条件) …
     * 【tab】 recipe（配方）
     *
     * target：程序生成文件的名字
     * prerequisites：是一个文件名，这个文件用来生成target
     * recipe ： 是make程序执行的命令，可以有多个，用来配合prerequisites文件来生成target，是生成target的命令
     *
     * 当target不存在，或者prerequisites有更新，那么make工具就会执行recipe命令来重新生成target
     * 这样就保证不用把没有改变的c文件再重新编译一次了。
     *
     * 示例：
     * main.exe: main.o
     *     gcc -o main.exe main.o
     *
     * main.o: main.c
     *     gcc -c -o main.o main.c
     *
     * main.exe没有回去找main.o，main.o也没有找下面的规则，找到第二条是生成main.o
     * 然后找main.c，存在，然后执行下面的命令，生成main.o，然后执行第一个命令，生成main.exe
     *
     * 这个文件名字命名为makefile 保存
     * 然后在当前目录下执行 make ，GNU make找寻默认的Makefile的规则是在当前目录下依次找三个文件——“GNUmakefile” 、“makefile”和“Makefile”
     * 也可以命名为其他名称
     * make -f [filename]
     * 即可执行，执行完毕后生成 main.exe 和main.o
     *
     *'make其实是依赖gcc来构建（先编译哪个后编译哪个）c工程的。
     * 他本质就是一个makefile解析器，实现了依赖关系的查找逻辑，和文件的时间戳比较逻辑，来判断下次这个文件是不是要重新编译
     *
     * 有了make我们就可以不用自己一行行的执行gcc了！而且还加入时间戳比较，自动编译有改动的文件，真是提升了不少效率！
     *
     * ===========window下使用make======
     *
     * Windows 上运行 make 命令或 makefile 文件，必须有 GNU 编译环境，
     * 因此在 Windows 上获取它的唯一方法是安装类似 GNUWin32 提供的 Windows 版本
     *
     */
    void fun_make(){}



    /**

     * =================cmake========================
     * https://www.hahack.com/codes/cmake/ （什么是 CMake）
     * CMake is an open-source, cross-platform family of tools designed to build, test and package software.
     * 开源，跨平台的构建，测试，打包工具
     *
     * 我们看cmake官网提供 windows,macOS 和Linux的安装包
     * 我们用的make不是跨平台的，可能每个平台文件的规则不同，所以我们要写一遍c代码，可能就要写三个平台的makefile文件
     *
     * 总之cmake就是让你配置一次，就能在各个平台上都能编译c工程
     *
     *
     * ===================教材===========
     * https://cmake.org/ （官网）
     * https://cmake.org/cmake/help/latest/guide/tutorial/index.html （官方教材）
     * https://juejin.im/post/5a6f32e86fb9a01ca6031230 （中文翻译）
     *
     * ===============================
     *
     * https://www.jianshu.com/p/6332418b12b1 （Android NDK开发扫盲及最新CMake的编译使用）
     * 跨平台编译工具
     * CMake的配置文件一般命名为CMakeLists.txt
     * -------cmake和ndk-build区别---------
     * 在Android Studio 2.2 之后，工具中增加了 CMake 的支持
     * 用 cmake 替换 ndk-build +Application.mk+Android.mk 来编译 C/C++
     * <p>
     * 在 Android Studio 2.2 之后你有2种选择来编译你写的 c/c++ 代码。
     * 一个是 ndk-build + Android.mk + Application.mk 组合，
     * 另一个是 CMake + CMakeLists.txt 组合。
     * 这2个组合与Android代码和c/c++代码无关，只是不同的构建脚本和构建命令。
     * <p>
     * cmake优点：
     * 不需要再去通过javah根据java文件生成头文件，并根据头文件生成的函数声明编写cpp文件
     * 当在Java文件中定义完native接口，可以在cpp文件中自动生成对应的native函数，所需要做的只是补全函数体中的内容
     * 不需要手动执行ndk-build命令得到so，再将so拷贝到对应的目录
     * 在编写cpp文件的过程中，可以有提示了
     * CMakeLists.txt要比Android.mk更加容易理解
     * <p>
     * ---------------cmakeList.txt脚本语法-------
     * https://cmake.org/cmake/help/latest/manual/cmake-commands.7.html
     * <p>
     * <p>
     * <p>
     * ----------cmake原理--------
     * c/c++ 的编译文件在不同平台是不一样的。Unix 下会使用 makefile 文件编译，
     * Windows 下会使用 project 文件编译。而 CMake 则是一个跨平台的编译工具，
     * 它并不会直接编译出对象，而是根据自定义的语言规则（CMakeLists.txt）
     * 生成 对应 makefile 或 project 文件，然后再调用底层的编译。
     * <p>
     * --------------使用命令行进行cmake编译---------------
     * https://blog.csdn.net/minghuang2017/article/details/78938852
     *
     * ==========Android studio中支持cmake和 lldb =======
     * 打开 设置，里面搜索 Android Sdk ，里面有sdk tools ，勾选 cmake和lldb即可
     * 下载的文件在sdk目录下，[sdkdir]/camke/版本/   [sdkdir]/lldb/版本
     * <p>
     * <p>
     * <p>
     * <p>
     *
     *
     *   =================Android studio中的cmake==========
     *  您可借助 Android Studio 2.2 及更高版本，使用 NDK 和 CMake 将 C 及 C++ 代码编译到原生库中
     *  之后，Android Studio 会使用 IDE 的集成构建系统 Gradle 将您的库封装到 APK。
     *
     * https://developer.android.com/studio/projects/configure-cmake?hl=zh-CN (配置 CMake)
     *
     *
     *
     * ===============ndk中的预置库============
     * 在sdk目录的ndk-bundle目录下，platforms/android-xx/arch-arm/usr/lib/中
     * 有
     * libandroiid.so
     * libc.so
     * libdl.so
     * libEGL.so
     * libGLESv2.so
     * libjnigraphics.so
     * liblog.so
     * libm.so
     * libOpenSLES.so
     * libstdc++.so
     * libz.so
     * 上面这些总共有10m大小
     *
     * 这些在编译的时候为你提供动态链接库，你可以引用其中的方法，然后让你编译通过
     * 当apk安装到手机上时，当要用到这些库的时候，Android系统已经内置了这些库，系统回到默认的目录下查找
     *
     *
     *  # Specifies a library name, specifies whether the library is STATIC or
     *     # SHARED, and provides relative paths to the source code. You can
     *     # define multiple libraries by adding multiple add_library() commands,
     *     # and CMake builds them for you. When you build your app, Gradle
     *     # automatically packages shared libraries with your APK.
     *     指定的你要编译的源文件和生成库的名字
     *     add_library( # Specifies the name of the library.
     *                  native-lib
     *
     *                  # Sets the library as a shared library.
     *                  SHARED
     *
     *                  # Provides a relative path to your source file(s).
     *                  src/main/cpp/native-lib.cpp )
     *
     * ============使用zlib==========
     * # Searches for a specified prebuilt library and stores the path as a
     * # variable. Because CMake includes system libraries in the search path by
     * # default, you only need to specify the name of the public NDK library
     * # you want to add. CMake verifies that the library exists before
     * # completing its build.
     * 找到这个库，第一个参数是设置别名，第二个参数是库的名字（不包含前面的lib）
     * find_library( # Sets the name of the path variable.
     *         z-lib
     *
     *         # Specifies the name of the NDK library that
     *         # you want CMake to locate.
     *         z)
     *
     *
     * # Specifies libraries CMake should link to your target library. You
     * # can link multiple libraries, such as libraries you define in this
     * # build script, prebuilt third-party libraries, or system libraries.
     *
     * 链接上面定义的链接库到你的库中
     * target_link_libraries( # Specifies the target library.
     *         native-lib
     *
     *         # Links the target library to the log library
     *         # included in the NDK.
     *         ${log-lib} ${z-lib})
     *
     *
     * ===========现有项目中加入c代码=========
     * 把c代码放到main/cpp下，CMakeList也放进去
     * 记得改变里面native代码中的包名和类名
     *
     * 然后在项目build.gradle中加入
     *
     *  defaultConfig {
     *         externalNativeBuild {
     *             cmake {
     *                 cppFlags ""
     *                 abiFilters 'armeabi-v7a', 'arm64-v8a'
     *             }
     *         }
     *     }
     * externalNativeBuild {
     *         cmake {
     *             path "src/main/cpp/CMakeLists.txt"
     *             version "3.10.2"
     *         }
     *     }
     *   可以好像可以不指定版本？
     *
     * https://stackmirror.com/questions/41218241 （Unable to find CMake in Android Studio）
     * Check in your build.gradle file for your App's module
     *
     * externalNativeBuild {
     *     cmake {
     *         path "CMakeLists.txt"
     *     }
     * }
     * Remove the cmake section
     * Re-synchronise the project: go to menu Build -> Refersh Lined C++ Projects
     * Right-click on the module, such as the app module, select "Link C++ Project with Gradle" from the menu.
     * You should see a dialog similar to the one shown in below figure.
     * Enter image description here
     * Click OK.
     *
     * 重新关联项目以后 这样.externalNativeBuild就能自动生成了
     *
     *
     * ------------------------
     * CMake Error: CMake was unable to find a build program corresponding to "Ninja".
     * CMAKE_MAKE_PROGRAM is not set. You probably need to select a different build tool
     *
     * 如果报上面那个错，那么是你gradle版本太低了
     * 将自己工程的Gradle升级为 3.3.2+，注意wrapper中对应的版本号
     *
     *
     *
     *
     */
    void fun_cmake(){}


    /**
     * ==============ndk-build===========
     * https://developer.android.com/ndk/guides/ndk-build?hl=zh-CN (官方文档)
     * Android的c、c++代码编译工具（编译成.so文件）
     *
     * ndk-build 脚本使用 NDK 的基于 Make 的编译系统构建项目。
     * 运行 ndk-build 脚本相当于运行以下命令
     *
     * $GNUMAKE -f <ndk>/build/core/build-local.mk
     *
     *
     *
     * 实际上ndk-build就是对make做了个封装
     *
     * Android.mk 文件位于项目 jni/ 目录的子目录中，用于向编译系统描述源文件和共享库。
     * Application.mk 指定 ndk-build 的项目范围设置。默认情况下，它位于应用项目目录中的 jni/Application.mk 下
     *
     *
     *
     *
     * ===================Android中添加c c++代码========
     * https://developer.android.com/studio/projects/add-native-code.html?hl=zh-CN （向您的项目添加 C 和 C++ 代码）
     *
     *
     *
     *
     */
    void fun_ndk_build(){}


    /**
     *   =====================LLVM=========================
     *   C++写成的编译器 底层虚拟机（Low Level Virtual Machine）
     *
     *  现今LLVM已单纯成为一个品牌，适用于LLVM下的所有项目，包含LLVM中介码（LLVM IR）、LLVM调试工具、LLVM C++标准库等。
     *
     *  LLVM项目的发展起源于2000年伊利诺伊大学厄巴纳-香槟分校维克拉姆·艾夫（Vikram Adve）
     *  与克里斯·拉特纳（Chris Lattner）的研究，他们想要为所有静态及动态语言创造出动态的编译技术。
     *  LLVM是以BSD许可来发展的开源软件。
     *  2005年，苹果计算机雇用了克里斯·拉特纳及他的团队为苹果计算机开发应用程序系统[3]，
     *  LLVM为现今Mac OS X及iOS开发工具的一部分。
     *
     *
     *
     *  =======================Clang===============
     *
     */
    void fun2(){}
}
