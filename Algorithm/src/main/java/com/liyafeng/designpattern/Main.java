package com.liyafeng.designpattern;

/**
 * Created by lenovo on 2017/12/20.
 */

public class Main {

    /**
     * 设计模式的网站
     * http://design-patterns.readthedocs.io/zh_CN/latest/creational_patterns/creational.html
     * 这个网站讲解的很详细，值得收藏
     * https://refactoringguru.cn/design-patterns/catalog （设计模式目录）
     * =============设计模式六大原则===============
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
     * =============三类设计模式=======================
     * 总的分为三类
     * 创建型设计模式：这类设计模式主要用于将创建 对象 的具体逻辑隔离，创建逻辑和调用者解耦
     * 行为型设计模式：使得调用者和被调用者的方法逻辑隔离，使得调用者不需要知道被调用者的具体行为
     *                  对调用者和执行者之间解耦
     * 结构型设计模式：更好的管理类与类之间的关系，对两个关联类之间解耦
     *
     * 使用这些设计模式，主要功能就是解耦，优雅的解耦，解耦的正确姿势，可以使代码更有拓展性，易维护
     * 符合上面的的六种原则，使得新添加功能不需要改动老的代码，用户在使用老版本也不会报错，使用新版本只需要改一下配置即可
     * 修改旧的功能只需要修改很少的代码。
     *
     *
     * ==========uml图/UML图==============
     * ===========什么是uml===============
     * https://www.visual-paradigm.com/cn/guide/uml-unified-modeling-language/what-is-uml/
     * UML 是统一建模语言（unified-modeling-language）的简称，它是一种由一整套图表组成的标准化建模语言。
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
     * 改善软件生产流程、提高质量、降低成本并缩短产品上市时间
     *
     *
     * ============uml图种类==========
     * 什么是类图？
     * 类图是一切面向对象方法的核心建模工具。
     * 什么是组件图？
     * 在统一建模语言中，组件图描绘了组件如何连接在一起以形成更大的组件或软件系统。
     * 什么是部署图？
     * 部署图有助于模拟面向对象软件系统的物理方面。
     * 什么是对象图？
     * 对象图是实例 (Instance) 的表达，包括对象和数据值。类图表示了一个由类及其关系组成的抽象模型，而对象图则表达了特定时刻的实例
     * 什么是包图？
     * 包图是 UML 一種用以显示包和包之间的依赖关系的结构性图表
     * 什么是用例图？
     * 用例模型从用例的角度描述系统的功能需求，它是系统预期功能（用例）及其环境（参与者）的模型。
     * 什么是活动图？
     * 活动图用于展示工作流程，它支持选择 (Choice)，迭代 (Iteration)和并发 (Concurrency)。
     * 什么是状态机图？
     * 状态图是 UML 中用来描述基于 David Harel 的状态图概念的系统行为的一种图表。
     * 什么是序列图？
     * 序列图根据时间序列展示对象如何进行协作。
     * 什么是通訊圖？
     * 与序列图类似，通訊圖也用于模拟用例的动态行为。与序列图相比，通訊圖更侧重于显示对象的协作而不是时间顺序。
     *
     *
     * ============uml类图==========
     * https://www.cnblogs.com/shindo/p/5579191.html （类图讲解）
     *
     * https://design-patterns.readthedocs.io/zh_CN/latest/read_uml.html
     *（带空心三角的线）
     * 1.实线箭头，(generalization)继承/泛化 suv->小汽车（suv继承自小汽车）
     * 2.虚线箭头，(realize)实现，小汽车--->车 实现的是接口 。抽象类应该用继承
     *
     * 3.空心菱形：(aggregation)聚合关系 A<-<>B  A是B的一部分 不是强依赖的，B不存在了，A还可以在  （员工-<>部门）
     * （对象是传入类内部的）
     * 4.实心菱形：(composition)组合关系 A<-</>B  强依赖，B不存在了，A也不存在了（部门-</>公司）
     * （对象是内部new的）
     *  有的类图菱形另一侧有箭头
     * 组合和聚合关系都是(可能)以成员变量的形式存在
     * 组合的成员变量对象是类内new出来的，聚合的成员变量是构造参数传过来的。
     *
     * 雁群<>->大雁  大雁离了雁群还可以存在
     * 大雁</>->翅膀  翅膀离了大雁就不能单独存在
     *
     * （带箭头的线，非空心箭头）
     * 5.实线箭头：关联关系(association) 成员变量 A->B B是A的成员变量，表示A知道B，但 B不知道A；
     * 6.虚线箭头：依赖关系(dependency) 参数 A--->B B是A的构造方法或者普通方法的参数，或者是方法中new出来的参数
     * A中的方法返回B,这就是依赖
     *
     *
     * 聚合 和 关联的关系类似。
     *
     * 关系表示的联系强弱程度：继承>实现>组合>聚合>关联>依赖
     *
     * 类名
     * 属性列表
     * 方法列表
     *
     *  + ：表示public
     *  - ：表示private
     *  #：表示protected（friendly也归入这类）
     *
     * 示例
     *  ClassName
     *  ------
     *  + attribute1:type = defaultValue
     *  + attribute2:type
     *  - attribute3:type
     *  ------
     *  + operation1(params):returnType
     *  - operation2(params)
     *  - operation3()
     *
     * @param args
     */
    public static void main(String[] args) {

    }


    /**
     * 常见的设计模式包括但不限于以下几种：22个
     *
     * 1. 创建型模式：
     * - 工厂模式（Factory Pattern）
     * - 抽象工厂模式（Abstract Factory Pattern）
     * - 单例模式（Singleton Pattern）
     * - 建造者模式（Builder Pattern）
     * - 原型模式（Prototype Pattern）
     *
     * 2. 结构型模式：(解耦类与类之间的关联)
     * - 适配器模式（Adapter Pattern）
     * - 桥接模式（Bridge Pattern）
     * - 组合模式（Composite Pattern）
     * - 装饰器模式（Decorator Pattern）
     * - 外观模式（Facade Pattern）
     * - 享元模式（Flyweight Pattern）
     * - 代理模式（Proxy Pattern）
     *
     * 3. 行为型模式：（调用者和被调用者之间解耦）
     * - 策略模式（Strategy Pattern）
     * - 模板方法模式（Template Method Pattern）
     * - 观察者模式（Observer Pattern）
     * - 迭代器模式（Iterator Pattern）
     * - 命令模式（Command Pattern）
     * - 备忘录模式（Memento Pattern）
     * - 状态模式（State Pattern）
     * - 责任链模式（Chain of Responsibility Pattern）
     * - 访问者模式（Visitor Pattern）
     * - 中介者模式（Mediator Pattern）
     *
     *
     * 这些设计模式可以帮助开发人员解决特定类型的问题，并提供了一种经过验证的解决方案。选择合适的设计模式可以提高代码的可维护性、可扩展性和可重用性。
     */
    void a1(){}


}
