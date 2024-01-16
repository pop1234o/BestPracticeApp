package com.liyafeng.designpattern.singleton;

public class Singleton {

    public static void main(String[] args) {
//        Singleton instance1 = Singleton.getInstance();
//        Singleton instance2 = Singleton.getInstance();
//
//        System.out.printf(instance1 + " " + instance2);

    }


//    /**
//     * 传统双锁
//     */
//    public static class Singleton {
//        private static Singleton singleton;
//
//        private Singleton() {
//        }
//
//        public static Singleton getInstance() {
//            if (singleton == null) {
//                synchronized (Singleton.class) {
//                    if (singleton == null) {
//                        singleton = new Singleton();
//                    }
//                }
//            }
//            return singleton;
//        }
//    }

//    /**
//     * 静态内部类，懒汉式。
//     */
//    public static class Singleton {
//
//
//        private Singleton() {
//        }
//
//        static class Holder {
//            static Singleton singleton = new Singleton();
//        }
//
//        public static Singleton getInstance() {
//            return Holder.singleton;
//        }
//    }

//    /**
//     * 饿汉式
//     */
//    public static class Singleton {
//        private static Singleton singleton = new Singleton();
//
//        private Singleton() {
//        }
//
//        public static Singleton getInstance() {
//            return singleton;
//        }
//    }
}
