package com.liyafeng.designpattern;

/**
 * Created by lenovo on 2017/12/20.
 */

public class Main {

    /**
     * 设计模式的网站
     * http://design-patterns.readthedocs.io/zh_CN/latest/creational_patterns/creational.html
     * 这个网站讲解的很详细，值得收藏
     * ============================
     * 设计模式六大原则 http://www.uml.org.cn/sjms/201211023.asp
     * 单一职责（每个模块只负责一个功能）
     * 开闭原则（对拓展放开，对修改关闭）就是说设计的时候要尽量设计出基类，
     *          以便于后期拓展功能时直接继承就行，而不是修改原来的代码
     * 依赖倒置 （高层模块不依赖底层模块，都应该依赖于其抽象，
     *          抽象类不能依赖实现类）反正在哪都要用抽象（抽象类、接口），不要用实现类
     *
     * 里式替换 （在能使用基类的地方能任意替换为其子类，而功能不变，这个强调的是功能不变）
     * 接口隔离 （一个接口只负责一类功能，而不是将所有功能就集合在一个集合中，
     *             这样一个接口就应该拆分成多个接口）
     * 迪米特法则 （一个类应该保持对其他类最少的了解，就是低耦合）
     *
     * @param args
     */
    public static void main(String[] args) {

    }
}
