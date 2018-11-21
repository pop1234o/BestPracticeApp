package com.liyafeng.c;

public class Main {

    /**
     * ### C语言
     * =====================计算机历史=======================
     * <p>
     * ================汇编语言 assembly language===============================
     * 控制硬件，是低级语言，直接控制（描述）cpu的运行
     * <p>
     * <p>
     * 机器语言是一串二进制的数字，是cpu可以直接执行的，但是它没有可读性
     * 所以就有了汇编语言，他采用 操作符 操作数 的方式来编写
     * 经过编译器编译后变为机器语言执行。
     * 像unix，mysql都是c语言编写的，他的速度和汇编语言差不多。
     * ======================MinGW==========================
     * https://mingw-w64.org/doku.php/download 下载
     * https://sourceforge.net/projects/mingw-w64/
     * <p>
     * 在windows中要编译c语音，需要特定编译器
     * mingw Minimalist GNU for Windows
     * <p>
     * ======================Linux/GNU================
     * http://cn.linux.vbird.org/linux_basic/0110whatislinux_1.php
     * <p>
     * Linux的核心是由Linus Torvalds在1991年的时候给他开发出来的
     * Linux只是一个操作系统中的内核
     * 内核建立了计算机软件与硬件之间通讯的平台，即通常我 们所说的协议层，
     * 内核只提供系统服务，比如文件管理、虚拟内存、设备I/O等，与硬件本身无关
     * 我们通常所说的Linux，指GNU/Linux，即采用Linux内核的 GNU操作系统
     * GNU’s Not Unix
     * GNU内包含许多软件
     * 包括相当重要的系统软件，比如C语言编译器，shell等。 就连最常用的ls命令也是来自GNU的 fileutils
     * <p>
     * ======================内核==============================
     * https://zh.wikipedia.org/wiki/%E5%86%85%E6%A0%B8
     * kernel又称核心，是一个计算机程序，提供直接访问硬件的能力（api）
     * 是现代操作系统中最基本的部分
     * <p>
     * =====================UNIX 发展史=======================
     * http://cn.linux.vbird.org/linux_basic/0110whatislinux_1.php
     * UNIX 是对应硬件，操作硬件，不同厂商生产的计算机硬件不同
     * 所以要修改UNIX源码，使他能运行在自己生产的计算机中
     * <p>
     * 所以UNIX有很多分支
     * 柏克莱大学的Bill Joy在取得了Unix的核心原始码后，着手修改成适合自己机器的版本，
     * 并且同时增加了很多工具软件与编译程序，最终将它命名为Berkeley Software Distribution (BSD)。
     * 后来可以安装在x86硬件架构上面FreeBSD即是BSD改版而来
     * <p>
     * <p>
     * <p>
     * ========================gcc===================
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
     * ==============Visual Studio===================
     * https://visualstudio.microsoft.com/zh-hans/ （官方下载地址）
     *
     * @param args
     */
    public static void main(String[] args) {

    }

    /**
     * mac 自带编译软件，gcc/g++
     * gcc --version
     * g++ --help
     * <p>
     * =========gcc/g++区别========
     * 后缀为.c的，gcc把它当作是C程序，而g++当作是c++程序；后缀为.cpp的，两者都会认为是c++程序
     * 编译阶段，g++会调用gcc，对于c++代码，两者是等价的，
     * 但是因为gcc命令不能自动和C＋＋程序使用的库联接，所以通常用g++来完成链接，为了统一起见，干脆编译/链接统统用g++了
     */
    public void fun1() {
    }


    /**
     * =========取地址运算符 间接寻址运算符============
     * int n=5;
     * &n是返回变量n的地址 （取地址运算符）
     * <p>
     * int *p;
     * <p>
     * p=&n;
     * <p>
     * *p 就是5，p的值是个地址，*是来访问这个地址的值
     * <p>
     * ===========指针=======
     * int    *ip;    一个整型的指针
     * double *dp;    一个 double 型的指针
     * float  *fp;    一个浮点型的指针
     * char   *ch;    一个字符型的指针
     *
     * 这是定义指针，代表只用分配地址大小的空间就可以了
     * 有了地址后，后面多少位是数据？int是32位，所以要声明指针类型
     *
     * 地址开始存储的值 = *(地址)
     *
     * C++ 支持空指针。NULL 指针是一个定义在标准库中的值为零的常量
     *
     *
     *
     *
     */
    public void fun2() {
    }
}
