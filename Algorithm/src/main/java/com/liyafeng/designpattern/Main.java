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
     * ====================================
     * 总的分为三类
     * 创建型设计模式：这类设计模式主要用于将创建 对象 的具体逻辑隔离，创建逻辑和调用者解耦
     * 行为型设计模式：使得调用者和被调用者的方法逻辑隔离，使得调用者不需要知道被调用者的具体行为
     *                  对调用者和执行者之间解耦
     * 结构型设计模式：更好的管理类与类之间的关系，对两个关联类之间解耦
     *
     *
     * ==========uml图/UML图==============
     * ===========什么是uml===============
     * https://www.visual-paradigm.com/cn/guide/uml-unified-modeling-language/what-is-uml/
     * UML 是统一建模语言的简称，它是一种由一整套图表组成的标准化建模语言。
     * 用图表当语音来描述软件系统，不同的图（图表）要描述的内容不同
     * UML主要使用图形符号来表示软件项目的设计，使用UML可以帮助项目团队沟通、探索潜在的设计和验证软件的架构设计
     *
     * =============uml的起源=========
     * 在UML之前就有很多软件分析方法，
     * 对象建模技术OMT [James Rumbaugh 1991] - 最适合分析和数据密集型信息系统。
     * Booch [Grady Booch 1994]强项为设计和實作。Grady Booch 曾经为 Ada 语言方面做过广泛的工作，并且一直是该语言面向对象技术发展的主要参与者。尽管 Booch 方法很强大，但是但并未广为接受 （因为他的模型包含着很多云状，序人不整齐的感觉）
     * OOSE（面向对象的软件工程[Ivar Jacobson 1992]） - 有一个称为用例的模型。
     *
     * 1994年，OMT 的创始人 Jim Rumbaugh 离开了通用电气公司 （General Electric），转投了 Rational，与 Grady Booch 并肩作战。这使得软件世界惊呆的合作，为的是要把二人的想法结合成一个统一的方法（项目名称也就是 “统一方法”）。
     *
     * 到了 1995 年，OOSE的创建者 Ivar Jacobson 也加入了Rational，他的想法（特别是有关“用例” (Use Case) 的概念）被整合于统一方法中，成为“统一建模语言”。Rumbaugh，Booch 和 Jacobson 的团队则被称为“三友”
     *
     *
     * =============uml的优点=========
     *
     *
     *
     * ============uml类图==========
     * https://design-patterns.readthedocs.io/zh_CN/latest/read_uml.html
     *（带三角的线）
     * 1.实线箭头，(generalization)继承 suv->car（suv继承自car）
     * 2.虚线箭头，(realize)实现，suv--->car 实现的是抽象类或接口
     *
     * 3.空心菱形：(aggregation)聚合关系 A-<>B  A是B的一部分 不是强依赖的，B不存在了，A还可以在  （员工-<>部门）
     * 4.实心菱形：(composition)组合关系 A-</>B  强依赖，B不存在了，A也不存在了（部门-</>公司）
     *
     * （带箭头的直线）
     * 5.实线箭头：关联关系(association) 成员变量 A->B B是A的成员变量，表示A知道B，但 B不知道A；
     * 6.虚线箭头：依赖关系(dependency) 参数 A--->B B是A的构造方法或者普通方法的参数，或者是方法中new出来的参数
     *
     *
     *
     * @param args
     */
    public static void main(String[] args) {

    }



}
