package com.liyafeng.jni;

public class Main_Cmake {


    /**
     * ==========Android studio中支持cmake和 lldb =======
     * 打开 设置，里面搜索 Android Sdk ，里面有sdk tools ，勾选 cmake和lldb即可
     * 下载的文件在sdk目录下，[sdkdir]/camke/版本/   [sdkdir]/lldb/版本
     * <p>
     * <p>
     * <p>
     * <p>
     * =================cmake========================
     * CMake is an open-source, cross-platform family of tools designed to build, test and package software.
     * 开源，跨平台的构建，测试，打包工具
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
     *

     *
     * @param args
     */
    public static void main(String[] args) {

    }


    /**
     *
     *
     *
     */
    void a1(){}
}
