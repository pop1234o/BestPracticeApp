package com.liyafeng.c;

public class Operating_System {

    /**
     *
     * =====================Unix历史======================
     * https://bbs.feng.com/read-htm-tid-6209622-page-1.html
     *
     *
     * ================汇编语言 assembly language===============================
     * 控制硬件，是低级语言，直接控制（描述）cpu的运行
     * <p>
     * <p>
     * 机器语言是一串二进制的数字，是cpu可以直接执行的，但是它没有可读性
     * 所以就有了汇编语言，他采用 操作符 操作数 的方式来编写
     * 经过编译器编译后变为机器语言执行。
     * 像unix，mysql都是c语言编写的，他的速度和汇编语言差不多。

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
     * ==============Visual Studio===================
     * https://visualstudio.microsoft.com/zh-hans/ （官方下载地址）
     *
     * ======================POSIX=================
     * 是一套准则
     * 可移植操作系统接口（英语：Portable Operating System Interface，缩写为POSIX）
     * 是IEEE为要在各种UNIX操作系统上运行软件，而定义API的一系列互相关联的标准的总称
     *
     * 操作系统管理硬件，提供io，用来管理不同的硬件
     * 那么如果我们直接操作硬件，就需要兼容很多类型的硬件
     * 所以干脆由系统按照标准提供一套api，软件开发者调用系统api就行
     * 这样在UNIX 类UNIX windows上开发的软件不用修改调用的api，
     * 使得开发效率提高
     * 比如
     * #include <pthread.h>  //在Linux下编写多线程程序需要包含的头文件
     * POSIX线程（POSIX threads），简称Pthreads，是线程的POSIX标准。该标准定义了创建和操纵线程的一整套API。
     *
     *  在类Unix操作系统（Unix、Linux、Mac OS X等）中，都使用Pthreads作为操作系统的线程。
     *
     * 每个cpu处理多线程任务肯定不一样，所以我们直接调用api就能兼容这么多操作系统，效率高
     *
     * 操作系统负责线程调度，那操作系统也是c语言写的，所以其实还是串行执行的代码
     * 只不过是操作系统负责何时执行
     *
     * @param args
     */
    public static void main(String[] args) {

    }
}
