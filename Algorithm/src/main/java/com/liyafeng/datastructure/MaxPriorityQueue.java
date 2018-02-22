package com.liyafeng.datastructure;

import com.liyafeng.algorithm.sort.Util;

/**
 * Created by liyafeng on 2018/2/22.
 * <p>
 * 优先队列
 * 基于数组实现的
 */

public class MaxPriorityQueue<E extends Comparable<E>> {

    private E[] keys;

    private int N = 0;


    public MaxPriorityQueue(int maxN) {
        keys = (E[]) new Comparable[maxN + 1];//要保留第0个位置
    }

    public void insert(E key) {
        keys[++N] = key;//加入到二叉树末尾
        swim(N);//然后上浮
    }

    public E deleteMax() {
        E deleteKey = keys[1];
        Util.exchange(keys, 1, N--);//把已经删除的元素放到最后，然后长度减一
        keys[N + 1] = null;//删除那个元素
        sink(1);//此时第一个元素就是从末尾交换过来的，然后下沉，从新使堆有序
        return deleteKey;


    }

    private void swim(int k) {

    }

    private void sink(int k) {

    }
}
