package com.liyafeng.designpattern.strategy;

/**
 * Created by liyafeng on 2018/4/2.
 */

public class Main {

    /**
     * 策略模式，比如我们同一个操作有不同的行为实现。
     * 那么我们如何选择用哪种行为实现呢？
     * 可以 if else来配置 比如传入一个常量，用if-else或者switch-case实现
     * 如果我们要多加入一种行为，那么我们又要多写一个case，这违背了开闭原则
     * 所以我们用策略模式来注入具体的行为（的类），这种复合开闭原则
     *
     * ========================
     * https://refactoringguru.cn/design-patterns/strategy （策略模式）
     *
     * 比如我们有个 Navigator 类来实现计算A.B两点之间的最优路线
     *
     * List<Point> list = Navigator.buildRoute(A,B);
     * 那么开始我们计算的是开车的最优路线
     * 后面我们要增加需求要求也要支持行走最优路线，那么这个时候怎么办？
     * 这样？
     * Navigator.buildCarRoute(A,B);
     * Navigator.buildPeopleRoute(A,B);
     * 或者
     * Navigator.buildRoute(A,B,TYPE)
     * 很显然这样违反了开闭原则，而且你一直往Navigator类中加逻辑会导致很臃肿
     *
     * 这个时候我们采用策略模式
     *
     * 定义interface RouteStrategy{
     *     public List<Point> buildRoute(A,B)
     * }
     * 定义子类 CarRouteStrategy 和 PeopleRouteStrategy 里面实现相应算法
     *
     * Navigator.setStrategy(new CarRouteStrategy()).buildRoute(A,B);
     * Navigator.setStrategy(new PeopleRouteStrategy()).buildRoute(A,B);
     *
     * 这样以后在增加其他的策略，也丝毫不用改动原有代码，而且每种策略都隔离，都分别在一个类中
     *
     *
     *
     *
     * @param args
     */
    public static void main(String[] args) {
        //通过注入来实现不同策略的实现，而不是通过常量 if-else或者写死来实现
        Context.doSomething(new StrategyA());
        Context.doSomething(new StrategyB());
    }

    public static class Context {

        static void doSomething(Strategy strategy) {
            strategy.realDo();
        }
    }

    public interface Strategy {
        void realDo();
    }

    public static class StrategyA implements Strategy {

        @Override
        public void realDo() {
            System.out.println("strategy a");
        }
    }


    public static class StrategyB implements Strategy {

        @Override
        public void realDo() {
            System.out.println("strategy b");
        }
    }
}
