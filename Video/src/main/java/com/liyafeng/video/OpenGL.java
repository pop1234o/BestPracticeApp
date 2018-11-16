package com.liyafeng.video;

public class OpenGL {

    /**
     * ============显卡（The graphics card）========================= https://www.zhihu.com/question/22047211/answer/173264617
     * http://www.sohu.com/a/117375685_351912 （显卡的历史）
     * Video card、Display card、Graphics card、Video adapter
     * 中文又称《显示适配器》  显示适配器 图形加速卡
     * 最早，显卡的作用仅仅是将CPU计算生成的图形数据“翻译”成显示设备可以识别的数据，
     * 实现图形的显示，并不具备计算能力，被称作图形适配器(VGA Card)
     *
     * 到了1993年，大部分显卡已经具备加速图形处理的能力，并开始配备较高容量的显存
     * ，性能进一步提升，名称也从图形适配器改成了图形加速卡(Graphics Card)。
     *-----------------渲染管线------------------------
     * 90年代中期,出现了3D游戏 ,但那时渲染是通过cpu来计算的
     * 后来NVIDIA等厂家推出了 拥有 多边形转换与光源处理等功能的显卡
     *
     * 当时，每个厂家都提供不同的图形渲染api，导致开发程序变得复杂，需要适配很多显卡
     * 后来微软的Direct3D  和 OpenGL 这两个通用的api规则开始使用，解决了一个问题
     *
     *
     * 到了1999年，显卡的处理能力已经大大提升，开始成为计算机不可或缺的一部分。
     * NVIDIA在推出GeForce 256芯片时提出了GPU的概念，将显示核心与CPU并列，成为计算机内两大运算核心。
     *
     * 早期GPU具备的核心技术有硬件T&L、立方环境材质贴图和顶点混合、纹理压缩和凹凸映射贴图、
     * 双重纹理四像素256位渲染引擎等。其后，顶点着色器和像素着色器的引入让GPU的可编程性大大提升了。
     *
     * ---------------流处理器---------------
     * 到了2002年，GPU的可编程性已经达到了一定水平。随之，一场革命到来的时机已经成熟。
     * SIGGRAPH 2003大会上，许多业界泰斗开始共同探讨利用GPU进行各种通用运算的设想和实验模型，
     * 奠定了GPGPU的发展基础。其后3年，
     * 用统一的流处理器取代GPU中原有的不同着色单元的设计大大释放了GPU的计算能力，
     * 为今天的异构计算打下了基础。
     *
     *
     *
     * ------------------------------------
     * 显卡中包括gpu ，显存，散热器，显示器插口
     * <p>
     * 一端连接主板，一端连接显示器，负责将数字信号转化为模拟信号输出到显示器上
     * 数字信号是处理前的图形信息，模拟信号就是直接传给显示器显示的信息
     * 向显示器提供逐行或隔行扫描信号
     * <p>
     * 显卡还有图形处理功能，所以显卡有显存，相当于内存，专门存储图形信息
     * 独立显卡还有散热器
     * <p>
     * 集成显卡
     * 集成图形处理器，或称集成显卡（集显）、核芯显卡（核显）、内建显示核心，
     * 是设在主板或CPU上的图形处理器，运作时会借用计算机内部分的系统内存。
     * <p>
     * <p>
     * ======================GPGPU=====================
     * 图形处理单元上的通用计算（英语：General-purpose computing on graphics processing units，
     * 简称GPGPU或GP²U），
     * 是利用处理图形任务的图形处理器来计算原本由中央处理器处理的通用计算任务。
     *
     * 强大的并行处理能力
     *
     * ====================NVIDIA和AMD 与 华硕、微星、技嘉显卡的关系==================
     * NVIDIA和AMD 只负责生产GPU
     * 而华硕、微星等厂家负责将GPU与显存、散热器，等组件安装到一起，组成了最终可售卖
     * 的显卡
     * <p>
     * ==================GPU（不是显卡）支持的主流的图形渲染接口=====================
     * 以前我们显示都需要根据不同硬件来编写不同的程序，这样加大了开发难度
     * 后来又公司提出了一套标准，显卡负责实现这个标准，开发人员直接调用api
     * 就行，因为api都是一样的，所以可以在不用的显卡上调用，只是实现不同
     * <p>
     * 比如 NVIDIA GEFORCE GTX 1080 Ti
     * Microsoft DirectX Direct eXtension 由微软公司建立的一系列专为多媒体以及游戏开发的应用程序接口（API）。
     * Vulkan是一个低开销、跨平台的二维、三维图形与计算的应用程序接口。
     * OpenGL（英语：Open Graphics Library，译名：开放图形库或者“开放式图形库”）是用于渲染2D、3D矢量图形的跨语言、跨平台的应用程序接口
     * <p>
     * 所以不同显卡厂商实现了open gl接口，我们直接调用api即可
     * （我们可以去京东上查看显卡的参数，有一个3D API - OpenGL-4.5）说明是
     * 支持opengl 接口规范到4.5版本
     * <p>
     * ====================GPU 图形处理器==========
     * 显示适配器的主要芯片叫GPU，是显示适配器（显卡）的主要处理单元。
     * 又叫：
     * 显示芯片 Video chipset
     * GPU Graphic Processing Unit，图形处理单元 图形处理器
     * VPU，视觉处理器
     * ----------
     * Intel、NVIDIA和AMD都是目前台式机图形处理器市场的领导者，分别拥有68.1%、17.5%和14.4%的市场占有率。
     * Intel一般是低性能的集成图像处理器，扣除这些数字，NVIDIA和AMD控制将近100%的市场占有率。
     * <p>
     * * NVIDIA分很多不同的图形处理器
     * * GeForce，面向消费者的图形处理产品
     * * Quadro，计算机辅助设计和数字内容创建工作站图形处理产品
     * * NVS，多显示商用显卡
     * * Tegra，用于移动设备的芯片系列
     * * Tesla，主要用于服务器高性能电脑运算
     * * Nvidia Grid，Nvidia用于图形虚拟化的一套硬件和服务
     * *
     * =================GPU与CPU====================
     * CPU负责逻辑性强的事物处理和串行计算，GPU则专注于执行高度线程化的并行处理任务（大规模计算任务）。
     * 为什么这么分工？这是由二者的硬件构成决定的。
     * GPU无论发展得多快，都只能是替CPU分担工作，而不是取代CPU
     * （因为由硬件结构决定）
     * <p>
     * ================显示器接口============================
     * VGA端子（Video Graphics Array (VGA) connector）
     * (DVI) 数码视频接口 Digital Visual Interface
     * HDMI 高画质多媒体接口（英语：High Definition Multimedia Interface，简称 HDMI）
     * DP DisplayPort
     * <p>
     * 显示器需要屏幕上显示的每个像素的颜色信息，会从显卡中取
     * 这就是当耗时计算没有完成，显卡中数据没有更新，导致卡顿的原因
     * <p>
     * ================OpenGL ==========
     * https://learnopengl-cn.readthedocs.io/zh/latest/01%20Getting%20started/01%20OpenGL/ (中文学习网站)
     * https://www.opengl.org/ 官网
     * <p>
     * opengl是Khronos组织制定并维护的规范，定义了接口，即每个函数的功能，输入，返回
     * Khronos是一个图形软硬件行业协会，该协会主要关注图形和多媒体方面的开放标准
     *
     * 所以opengl的实现是被编程在gpu中的，我们通过各个显卡厂商的驱动就可以调用api了
     *
     * 显示程序调用api，比如显示三角形，直线，光照-》opengl规则-》显卡实现open api 处理-》显示器取显卡中的像素数据显示
     *
     * ===================曾经的3d api===================
     * 3dfx的Glide、微软的DirectX、OpenGL、PowerVR的PowerSGL、ATi的3DCIF
     *
     * ===============opengl es=======================
     * OpenGL ES是从OpenGL裁剪定制而来的，去除了glBegin/glEnd，四边形（GL_QUADS）、多边形（GL_POLYGONS）等复杂图元等许多非绝对必要的特性。经过多年发展，
     * 现在主要有两个版本，OpenGL ES 1.x针对固定管线硬件的，OpenGL ES 2.x针对可编程管线硬件。
     *
     * 所以opengl es是opengl 的子集
     * ====================Android中调用opengl es=======================
     * https://developer.android.com/guide/topics/graphics/opengl
     * OpenGL ES 3.0 - This API specification is supported by Android 4.3 (API level 18)
     * Android通过调用framework api 或者ndk中的api来使用opengl es
     *
     * framework api是通过GLSurfaceView 和GLSurfaceView.Renderer这两个类来实现的
     *
     * ==========================Android中的图形渲染过程=======================
     * https://tech.meituan.com/hardware_accelerate.html （美团的Android硬件加速原理与实现简介）
     * View-》 Canvas(Java API) —> OpenGL(C/C++ Lib) —> 驱动程序 —> GPU
     * 可见，驱动就是实现了opengl api，以至于我们调用驱动程序，从而控制gpu
     *
     * 在Android中，大多数应用的界面都是利用常规的View来构建的
     * （除了游戏、视频、图像等应用可能直接使用OpenGL ES）
     *
     *
     *
     * @param args
     */
    public static void main(String[] args) {

    }
}
