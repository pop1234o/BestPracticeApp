package com.liyafeng.datastructure;

import com.liyafeng.algorithm.basic.sort.Util;

/**
 * Created by liyafeng on 2018/2/22.
 * <p>
 * 优先队列
 * 基于数组实现的
 * 特点：每次都可以取出数组中的最大元素
 * 插入和删除的时间复杂度O(lgN)
 */

public class MaxPriorityQueue<E extends Comparable<E>> {

    private E[] keys;

    private int N = 0;


    public MaxPriorityQueue(int maxN) {
        keys = (E[]) new Comparable[maxN + 1];//要保留第0个位置
    }

    /**
     * 插入元素，插入到末尾，然后上浮
     * @param key
     */
    public void insert(E key) {
        keys[++N] = key;//加入到二叉树末尾
        swim(N);//然后上浮
    }

    /**
     * 删除一个元素，将第一个和最后一个交换，然后下沉
     * @return
     */
    public E deleteMax() {
        E deleteKey = keys[1];
        Util.exchange(keys, 1, N--);//把已经删除的元素放到最后，然后长度减一
        keys[N + 1] = null;//删除那个元素
        sink(1);//此时第一个元素就是从末尾交换过来的，然后下沉，从新使堆有序
        return deleteKey;
    }

    /**
     * 索引为k的元素上浮
     *
     * @param k
     */
    private void swim(int k) {

        while (k > 1 && Util.less(keys, k / 2, k)) {//和父节点比较，如果大于父节点，就交换
            Util.exchange(keys, k / 2, k);
            k = k / 2;
        }
    }

    private void sink(int k) {//下沉，和较大的子节点比较，使较大的上浮
        while (k * 2 <= N) {

            int j = 2 * k;
            //j<N是因为没有N+1这个节点，也就是说节点k只有一个子节点，只能和他比较
            if (j < N && Util.less(keys, j, j + 1)) {
                j++;
            }
            if (Util.less(keys, k, j)) {//k节点和较大的子节点比较
                Util.exchange(keys, k, j);
                k = j;//接续向下比较
            } else {//已经下沉成功，k已经比子节点都大
                break;
            }

        }

    }
}
