package com.liyafeng.algorithm.practice;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by liyafeng on 2018/2/23.
 */

public class N_Sort_TopM {

    /**
     * 如何从10亿个数中找出前10大的数？
     * ==========分析============
     * 可以将数组排序，然后找出前十大的数，但是对于海量数据，不能一次加载到内存中
     * 这时我们可以使用优先队列，将新的数加入优先队列，而所需的空间就是10 ,和M成正比
     * ------------
     * 当然我们可以用插入排序的原理来找，但是随着M的增加比较次数有可能是MN
     * 但是优先队列的比较次数是NlgM，所以M
     *
     * @param args
     */
    public static void main(String[] args) {

        int[] array = {1, 124, 54, 34, 34, 6, 5};
        PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer t1, Integer t2) {//逆序，由大到小
                return t2 - t1;
            }
        });
        for (int i = 0; i < array.length; i++) {
            queue.add(array[i]);
        }
        for (int i = 0; i < 3; i++) {//找出最大的三个数
            System.out.println(queue.poll());
        }
    }
}
