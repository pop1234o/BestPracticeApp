package com.liyafeng.c;

public class Compile {


    /**
     *      * ======================MinGW==========================
     *      * https://mingw-w64.org/doku.php/download 下载
     *      * https://sourceforge.net/projects/mingw-w64/
     *      * <p>
     *      * 在windows中要编译c语音，需要特定编译器
     *      * mingw Minimalist GNU for Windows
     *      * <p>
     *   * ========================gcc===================
     *      * 下面是GNU计划中开发的几个软件
     *      * Emacs 一款c编辑器
     *      * GNU C Compiler(gcc) GNU上的c语音编译器
     *      * GNU C Library (glibc)
     *      * Bash shell
     *      * --------------------------------
     *      * GNU编译器套装（英语：GNU Compiler Collection，缩写为GCC）
     *      * 因为它原本只能处理C语言。GCC很快地扩展，变得可处理C++。之后也变得可处理Fortran、Pascal)、Objective-C、Java、Ada，以及Go与其他语言。
     *      * 许多操作系统，包括许多类Unix系统，如Linux及BSD家族都采用GCC作为标准编译器。苹果电脑Mac OS X操作系统也采用这个编译器。
     *      * <p>
     *      * <p>
     *
     *  =====================LLVM=========================
     *  C++写成的编译器 底层虚拟机（Low Level Virtual Machine）
     *
     * 现今LLVM已单纯成为一个品牌，适用于LLVM下的所有项目，包含LLVM中介码（LLVM IR）、LLVM调试工具、LLVM C++标准库等。
     *
     * LLVM项目的发展起源于2000年伊利诺伊大学厄巴纳-香槟分校维克拉姆·艾夫（Vikram Adve）
     * 与克里斯·拉特纳（Chris Lattner）的研究，他们想要为所有静态及动态语言创造出动态的编译技术。
     * LLVM是以BSD许可来发展的开源软件。
     * 2005年，苹果计算机雇用了克里斯·拉特纳及他的团队为苹果计算机开发应用程序系统[3]，
     * LLVM为现今Mac OS X及iOS开发工具的一部分。
     *
     * =================静态编译和动态编译=====================
     * 静态编译是将所有代码编译到一个可执行文件中
     * 动态编译是生成一个可执行文件作为入口，其他的生成动态链接库
     * 运行的时候加载这个库。
     *
     *
     *
     * @param args
     */
    public static void main(String[] args) {

    }

    /**
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
     * ============ 链接器 Linker============
     * http://blog.jobbole.com/96225/ (帮 C/C++ 程序员彻底了解链接器)
     *
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
}
