package com.liyafeng.algorithm.sword2offer;

public class N1_ObjectSize {
    /**
     * 变量占用的内存数
     * 8 bit  = 1 byte      0000 1111
     * byte 组成了基础数据类型
     *
     * boolean 1 byte
     * byte    1 byte (这里的第一个byte指的是java中的数据类型)
     * char    2
     * short   2
     * int     4
     * long    8
     * float   4
     * double  8
     *
     * 一个Object本身需要16字节（byte）
     * 一个引用占用 8 字节
     *
     * 内部类需要额外的8个字节指 ，是向外部类的引用 的消耗
     *
     * 数组
     * 一个原始类型的数组，24字节 = 16字节的对象开销，4字节保存int的数组长度，4字节补齐
     * 再加上它的内部数据
     *
     * 一个对象的数组是 24字节本身信息，加上8N个对象引用的占用内存
     *
     * 比如，一个 M*N的double数组占用多少字节？
     * 24二维数组对象本身+8M一维数组对象的引用 +24M个一维数组对象的开销 +8MN个double = 24+32M+8MN 字节
     *
     * 一个String 对象占用的内存 ？
     * 16字节对象占用，3个int(长度，偏移，散列值)12字节，数组对象引用8字节，4字节补齐 = 40字节
     * 字符数组是共享的，数组对象占用24字节，一个char占2字节
     * 所以一个长度为N的String 占用 40+24+2N = 64+2N 个字节 的内存
     *
     * 当执行一个方法时，java会在“栈”中分配方法中局部变量锁需要的内存，当方法结束时回收
     *
     * 所以尽量不要在递归方法中创建数组或者new对象，这将消耗大量内存
     *
     *
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.printf("hi");

    }
}
