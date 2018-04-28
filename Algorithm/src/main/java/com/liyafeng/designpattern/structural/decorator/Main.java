package com.liyafeng.designpattern.structural.decorator;

/**
 * Created by liyafeng on 2018/4/28.
 */

public class Main {

    /**
     * 装饰模式 （装饰者模式）也叫包装模式 wrapper
     * 他代替了继承的方式来拓展方法的行为
     * 这种叫组合的方式
     *
     *
     * @param args
     */
    public static void main(String[] args) {
        ConcreteComponent component1 = new ConcreteComponent();
        Component component = new ConcreteDecorator(component1);
        component.operation();
    }

    /**
     * 组件接口
     */
    interface Component{
        void operation();
    }

    static class ConcreteComponent implements Component{

        @Override
        public void operation() {

        }
    }

    /**
     * 装饰者，对被装饰者进行拓展
     * 他只负责装饰一层，而具体的拓展由子类完成
     */
    static class Decorator implements Component{
        Component component;//被装饰者

        public Decorator(Component component) {
            this.component = component;
        }

        @Override
        public void operation() {
            component.operation();
        }
    }

    static class ConcreteDecorator extends Decorator{

        public ConcreteDecorator(Component component) {
            super(component);
        }

        @Override
        public void operation() {
            addBehavior();
            super.operation();
        }

        void addBehavior(){

        }
    }
}
