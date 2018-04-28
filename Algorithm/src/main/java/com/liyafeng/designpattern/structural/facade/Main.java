package com.liyafeng.designpattern.structural.facade;

/**
 * Created by liyafeng on 2018/4/28.
 */

public class Main {

    /**
     * 外观模式
     * 目的是同一管理多个子系统，将子系统之间的网状关系转为星状关系
     * 实现的迪米特法则
     *
     *
     * @param args
     */
    public static void main(String[] args) {
        new Facade().wrapOperation();
    }

    public static class Facade{
        SystemA systemA;
        SystemB systemB;
        SystemC systemC;

        public Facade() {
            this.systemA = new SystemA();
            this.systemB = new SystemB();
            this.systemC = new SystemC();
        }

        public void wrapOperation(){
            systemA.operation();
            systemB.operation();
            systemC.operation();

        }
    }

    public static class SystemA{
        public void operation(){

        }
    }
    public static class SystemB{
        public void operation(){

        }
    }
    public static class SystemC{
        public void operation(){

        }
    }
}
