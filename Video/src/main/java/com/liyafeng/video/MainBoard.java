package com.liyafeng.video;

public class MainBoard {

    /**
     * =================BIOS和CMOS=======================
     * BIOS - Basic Input/Output System 基本输入输出系统，可以看成是最底层的操作系统
     * 我们开机通电后，先执行这的程序，将硬件初始化
     *
     *  BIOS指一段放到ROM中的程序（固件）这段程序以前放到ROM中不可更改，
     *  现在计算机基本都放到flash里，是可以更新的。
     *
     * CMOS - Complementary Metal Oxide Semiconductor 互补氧化物半导体
     * 他是一种硬件，里面存储着设置信息
     *
     * 跟BIOS相关的一些设置存储在CMOS芯片中
     *
     * 上文中BIOS运行需要哪些参数呢？主要是: 系统时间，硬盘类型，启动顺序，
     * CPU电压与频率，各项设备的IO地址与IRQ等[2][3]，
     * 这些你在启动计算机时按下Del或F12都能看到。
     *
     * 当计算机启动时，bios通过读取cmos中的信息初始化各硬件
     *
     * 主板上的电池就是保持CMOS数据不丢失，如果断电，系统时钟停止，其他不受影响
     *
     * =======================UEFI===============================
     * 目前BIOS已经逐步淘汰，采用更好的UEFI
     *
     * =====================PCI插槽=====================
     * Peripheral Component Interconnection，周边元件扩展接口
     * 可插接声卡、网卡、内置Modem、内置ADSL Modem、USB2.0卡、IEEE1394卡、
     * IDE接口卡、RAID卡、电视卡、视频采集卡以及其它种类繁多的扩展卡。
     * ======================PCI-E插槽=========================
     * PCI Express 有x1，x4 ，x16
     * 接显卡，声卡，网卡，但速度更快
     *
     * x1 最短的
     * 独立网卡、独立声卡、USB 3.0/3.1扩展卡
     *
     * x16 接显卡
     *
     *
     * ====================AGP插槽===========================
     * AGP，加速图形接口（Accelerated Graphics Port）
     * 显卡拓展插槽
     * 老的显卡接口，现被PCI-E替代
     *
     * =========================SATA接口==========================
     *  串行ATA（Serial ATA: Serial Advanced Technology Attachment）
     *  它是一种电脑总线，主要功能是用作主板和大量存储设备（如硬盘及光盘驱动器）之间的数据传输之用
     *
     *
     *
     *
     *
     *
     * @param args
     */
    public static void main(String[] args) {

    }
}
