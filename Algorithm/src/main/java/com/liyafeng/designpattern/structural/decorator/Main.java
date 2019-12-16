package com.liyafeng.designpattern.structural.decorator;

/**
 * Created by liyafeng on 2018/4/28.
 */

public class Main {

    /**
     * 装饰模式 （装饰者模式）也叫包装模式 wrapper
     * 他代替了继承的方式来拓展方法的行为
     * 这种叫组合的方式
     * <p>
     * 适合于有很多个方法，他们里面只有一部分逻辑不一样，这样通过这个来抽取通用逻辑
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
    interface Component {
        void operation();
    }

    static class ConcreteComponent implements Component {

        @Override
        public void operation() {

        }
    }

    /**
     * 装饰者，对被装饰者进行拓展
     * 他只负责装饰一层，而具体的拓展由子类完成
     */
    static class Decorator implements Component {
        Component component;//被装饰者

        public Decorator(Component component) {
            this.component = component;
        }

        @Override
        public void operation() {
            component.operation();
        }
    }

    static class ConcreteDecorator extends Decorator {

        public ConcreteDecorator(Component component) {
            super(component);
        }

        @Override
        public void operation() {
            addBehavior();
            super.operation();
        }

        void addBehavior() {

        }
    }


    //    static Logger loggerV = new LoggerImpl(Log::v);
    static Logger loggerI = new LoggerImpl(new Logger() {
        @Override
        public void log(String tag, String s) {
            //这里是逻辑不一样的地方，相当于把重复逻辑抽离出来了
//        Log.i(tag,s);
        }
    });

    private interface Logger {
        void log(String tag, String s);
    }

    static class LoggerImpl implements Logger {

        private Logger logger;

        public LoggerImpl(Logger logger) {
            this.logger = logger;
        }

        @Override
        public void log(String tag, String s) {
            if (s.length() > 4000) {
                printLongI(tag, s);
            } else {
                logger.log(tag, s);
            }
        }

        private void printLongI(String tag, String s) {
            for (int i = 0; i < s.length(); i += 4000) {
                if (i + 4000 < s.length()) {
                    logger.log(tag + i, s.substring(i, i + 4000));
                } else {
                    logger.log(tag + i, s.substring(i));
                }
            }
        }

    }


}
