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
