package com.liyafeng.c;

public class Operating_System {

    /**
     *
     * =====================Unix历史======================
     * https://bbs.feng.com/read-htm-tid-6209622-page-1.html
     * https://blog.csdn.net/qq_26849233/article/details/74527779
     *
     * 60年代，美国电话及电报公司（AT&T） 通用电器公司麻省理工学院（MIT）计划合作开发一个多用途、分时及多用户的操作系统，也就是这个MULTICS
     * ，几年下来都没有任何成果，而且性能都很低。于是到了1969年2月，贝尔实验室决定退出这个项目
     * 贝尔实验室有个叫 Ken Thompson 的，想在MULTICS操作系统上写个游戏，但是发现这个系统很差，
     * 所以自己写了个系统叫Unix跑在Digital PDP-7计算机上（他找来Dennis Ritchie合伙）
     *
     * 1971年 申请到了一台PDP-11/24的机器
     * 于是Unix第一版出来了。在一台PDP-11/24的机器上完成。这台电脑只有24KB的物理内存和500K磁盘空间。
     * Unix占用了12KB的内存，
     * 剩下的一半内存可以支持两用户进行Space Travel的游戏。而著名的fork（）系统调用也就是在这时出现的。
     *
     * 这些操作系统都是用汇编写的，于是换一个机器由于cpu结构不同，所以就得用汇编重新写
     * 很麻烦
     * 一开始他们想尝试用Fortran，可是失败了。后来他们用一个叫BCPL的语言开发，他们整合了BCPL形成B语言，
     * 后来 Dennis Ritchie 觉得B语言还是不能满足要求，就是就改良了B语言，这就是今天的大名鼎鼎的C语言
     *
     * 其实C很显然就是文本，重要的是编译器，编译器可以用汇编写，也可以用b语音写
     * 编译器的自举(self-hosting )
     * 一开始编译器用汇编或者b语言写，后面的编译器就能用c语言编译c语言
     * 最终都是编译成机器码
     *
     * nix的第一篇文章 “The UNIX Time Sharing System”由Ken Thompson和
     * Dennis Ritchie于1974年7月的 the Communications of the ACM发表。这是UNIX与外界的首次接触。
     *
     * 1978年，对 Unix而言是革命性的一年；因为学术界的老大柏克利大学，推出了一份以第六版为基础，加上一些改进和新功能而成的 Unix。
     * 这就是著名的“1 BSD（1st Berkeley Software Distribution）”，开创了Unix的另一个分支：BSD 系列。
     *
     * unix有源码，拿来源码改改加加，自己起个名字，就是一个unix分叉了
     *
     * AT&T想商业化，推出了Unix分叉，System III(1982年)
     *
     * Richard Stallman倡导的Open Source的概念
     * Richard Stallman于1984年创业了GNU，计划开发一套与Unix相互兼容的的软件
     * GNU只是一些列运行在Unix上的软件，函数库、编译器、调式工具、文本编辑器、网站服务器
     * 以及一个Unix的使用者接口（Unix shell）
     * ----------------------Linux诞生-------------------------------------
     * 1990年，Linus Torvalds还是芬兰赫尔辛基大学的一名学生，
     * 最初是用汇编语言写了一个在80386保护模式下处理多任务切换的程序，
     * 后来从Minix（Andy Tanenbaum教授所写的很小 的Unix操作系统，主要用于操作系统教学）得到灵感，
     * 进一步产生了自认为狂妄的想法——写一个比Minix更好的Minix，于是开始写了一些硬件的设备驱动程序，
     * 一个小的文件系统。这样0。0。1版本的Linux就出来了，但是它只具有操作系统内核的勉强的雏形，
     * 甚至不能运行，你必须在有Minix的机器上编译以后才能玩。这时候Linus已经完全着迷而不想停止，
     * 决定踢开Minix，
     * 于是在1991年10 月5号发布Linux 0。0。2版本，在这个版本中已经可以运行bash 和gcc。
     *
     * ，到1993年底94年初，Linux 1。0终于诞生了！Linux 1。0已经是一个功能完备的操作系统，
     * 而且内核写得紧凑高效，
     * 可以充分发挥硬件的性能，在4M内存的80386机器上也表现得非常好，至今人们还在津津乐道。
     *
     *
     * （Linux 的标志和吉祥物是一只名字叫做 Tux 的 企鹅，
     * 标志的由来是因为Linus在澳洲时曾被一只动物园里的企鹅咬了一口，便选择了企鹅作为Linux的标志。）
     *
     * GNU想开发一个完全自由的操作系统，但是他只是没有内核，这时Linux出现了
     * 于是GNU的软件套装和Linux内核合并了
     *
     * 整个内核是基于 GNU 通用公共许可，也就是GPL（GNU General Public License，GNU通用公共许可证）的，但是Linux内核并不是GNU 计划的一部分。
     * 1994年3月，Linux1。0版正式发布，Marc Ewing成立了 Red Hat 软件公司，成为最著名的 Linux 分销商之一。
     *
     *
     *
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


    /**
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
     */
    void a1(){}
}
