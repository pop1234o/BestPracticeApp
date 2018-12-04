package com.liyafeng.video.game.phaser;

 class Main_Phaser {


    /**
     * h5游戏引擎
     * https://phaser.io/
     * phaser游戏引擎需要利用js开发
     * ==================================
     * https://zhuanlan.zhihu.com/p/32846345 （）
     * ================================
     * https://github.com/photonstorm/phaser-examples (示例)
     *=====================================
     * https://github.com/photonstorm/phaser-ce/tree/master/build/custom  (自定义包)
     *
     * @param args
     */
    public static void main(String[] args) {

    }

     /**
      * phaser示例
      * https://segmentfault.com/a/1190000009226335
      *  preload - 尽管我们有预加载的场景，但如果你希望能缩短进入页面时加载的时间，可以分摊一些到其他场景，只需要在其他场景加入preload方法即可。
      *
      * create - 如果存在preload方法，则会在加载完毕后执行此方法；否则将在进入该场景时直接执行此方法。
      *
      * update - 更新周期自动执行的方法，例如在play场景的update方法中可以去检测两个物体是否有接触。
      *
      * render - 渲染完毕后执行的方法，例如可以在此方法中勾勒出物体的边缘，来方便观察物体的碰撞区域。
      *
      * ==========================
      * 总体4个场景（state）
      * 加载，开始（相当于一个logo展示），游戏，结束
      *
      *
      * ============3个物理引擎===========
      * Arcade
      * 最简单快速的物理引擎，因为只支持AABB式的碰撞，计算速度最快，实现简单的物理碰撞、接触、重力等效果最佳。
      * 全称是Axis-Aligned Bounding Box，直译就是轴对称盒
      *
      * p2
      * 如果说Arcade是小而精，P2引擎则是大而全了。各种物理模型均可实现，
      * 诸如多边形、弹簧、摩擦力、碰撞物体的材质、反弹系数等等都可以实现。尽管在性能上有一定消耗，
      * 毕竟要做更多复杂的运算，但为了效果，我们也很常用P2，作者引进P2也是由于它的全面。
      *
      * Box2D
      * 咦？为什么没有上面没有提到Box2D？很遗憾，这个引擎是收费的，40刀，如果没有特别大的需求，估计也用不上。
      *
      * =========================
      * 是因为Phaser只要焦点离开了页面，就会自动暂停游戏，包括定时任务也会暂停，而setInterval和setTimeout则不会
      *
      *
      *
      *
      */
     void fun1(){}
}
