package com.liyafeng.video.game;

public class Game_H5 {


    /**
     * 常用的框架
     * ===================webgl=========================
     * WebGL（全写Web Graphics Library）是一种3D绘图协议
     * 这种绘图技术标准允许把JavaScript和OpenGL ES 2.0结合在一起，
     * 通过增加OpenGL ES 2.0的一个JavaScript绑定，
     * WebGL可以为HTML5 Canvas提供硬件3D加速渲染，
     * 这样Web开发人员就可以借助系统显卡来在浏览器里更流畅地展示3D场景和模型了，
     * 还能创建复杂的导航和数据视觉化。
     *
     * webGL是协议， js-》WebGL接口-》浏览器-》openGL
     *
     * =========================h5游戏引擎=======================
     * 其实就是对webgl和h5 canvas的封装
     *
     * ======================物理引擎====================
     * 物理引擎是一个计算机程序模拟牛顿力學模型，
     * 使用质量、速度、摩擦力和空气阻力 等变量。可以用来预测这种不同情况下的效果。
     *
     * 精灵之间碰撞后角度和速度的变化
     * 自由落体速度的变化
     * 角速度的变化
     * 这一系列都需要进行坐标计算，而且这些计算都是可复用的
     * 所以游戏引擎没必要再自己写一套物理引擎（计算框架）
     * 所以直接用现有的即可，比如arcard box2d
     *
     *
     * @param args
     */
    public static void main(String[] args) {

    }
}
