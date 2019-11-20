package com.liyafeng.architecture.mvvm;

public class Main_MVVM {


    /**
     * MVVM可以算是MVP的升级版，其中的VM是ViewModel的缩写，ViewModel可以理解成是View的数据模型和Presenter的合体，
     * ViewModel和View之间的交互通过Data Binding完成，而Data Binding可以实现双向的交互，
     * 这就使得视图和控制层之间的耦合程度进一步降低，关注点分离更为彻底，同时减轻了Activity的压力。
     *
     * android中使用databinding这个库来实现mvvm，原理是使用观察者模式
     *
     * @param args
     */
    public static void main(String[] args) {

    }
}
