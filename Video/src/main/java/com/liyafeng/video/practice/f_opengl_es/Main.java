package com.liyafeng.video.practice.f_opengl_es;

public class Main {

    /**
     * https://developer.android.com/guide/topics/graphics/opengl#java(官方使用)
     * https://developer.android.com/training/graphics/opengl/（官方教程）
     * OpenGL 是一个跨平台的图形api
     * OpenGL ES 是专为嵌入式设备设计的
     * https://blog.piasy.com/2016/06/07/Open-gl-es-android-2-part-1/
     *
     * ===============在Android上使用openGL ES=================
     * 如果你想绘制3d图形那么使用opengl
     * GLSurfaceView 是唯一能显示opengl图形的控件
     *
     * 1.我们需要a GLSurfaceView and a GLSurfaceView.Renderer
     * 2.定义图形
     * 3.绘制图形（定义顶点shader和fragment shader 和Program ）然后绘制
     *
     * 当然我们也可以加入动作，或者触摸事件
     *
     * ====================GLSurfaceView=========================
     *其中 GLSurfaceView 为 android.opengl 包中核心类：
     *
     * 起到连接 OpenGL ES 与 Android 的 View 层次结构之间的桥梁作用。
     * 使得 Open GL ES 库适应于 Anndroid 系统的 Activity 生命周期。
     * 使得选择合适的 Frame buffer 像素格式变得容易。
     * 创建和管理单独绘图线程以达到平滑动画效果。
     * 提供了方便使用的调试工具来跟踪 OpenGL ES 函数调用以帮助检查错误。
     *  ====================opengl的矩阵操作==================
     *  通过操作矩阵，可以实现，
     *  视图变换，不同位置去观察
     *  模型变换，平移，缩放，旋转
     *  投影变换，远近变换
     *  窗口变换？
     *
     *
      * @param args
     */
    public static void main(String[] args) {

    }
}
