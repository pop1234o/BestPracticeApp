package com.liyafeng.designpattern.behavioral.template_method;

public class Main {

    /**
     * ============= 模板方法模式
     * 它在超类中定义了一个算法的框架， 允许子类在不修改结构的情况下重写算法的特定步骤。
     * <p>
     * 就是把一些通用的逻辑定义在父类中，子类可以覆盖或者实现 父类中特定的方法
     * <p>
     * 这样就实现了调用端和被调用端的隔离，调用端不需要知道里面改了什么，只需要持有父类的引用即可
     *
     * ===========优缺点==============
     * 优点：
     * 使得算法其他部分修改对其所造成的影响减小。
     *  你可将重复代码提取到一个超类中
     *
     * 缺点：
     * 部分客户端可能会受到算法框架的限制
     *
     * @param args
     */
    public static void main(String[] args) {
        BaseClass aClass = new SubClass1();
//        BaseClass aClass = new SubClass2();
        //只需要替换实现类即可
        aClass.doSomething();
    }


    public static abstract class BaseClass {

        abstract boolean step1();

        abstract void step2();

        abstract void step3();

        void doSomething() {
            //通用逻辑，定义模板，子类实现具体的逻辑（各个类可能逻辑不一样）
            if (step1()) {
                step2();
            } else {
                step3();
            }
        }
    }

    public static class SubClass1 extends BaseClass {

        @Override
        boolean step1() {
            return false;
        }

        @Override
        void step2() {

        }

        @Override
        void step3() {

        }
    }

    public static class SubClass2 extends BaseClass {

        @Override
        boolean step1() {
            return true;
        }

        @Override
        void step2() {

        }

        @Override
        void step3() {

        }
    }
}
