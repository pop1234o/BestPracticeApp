package com.liyafeng.algorithm.basic.sort;

/**
 * Created by liyafeng on 2018/2/22.
 */

public class N_Sort_HeapSort {


    /**
     * 堆排序
     * 时间复杂度O(nlogn)，先构造堆有序，n个元素进行下沉，每个元素比较logn次
     * 总共是nlogn次比较和交换
     * 然后是根元素和尾元素交换 n次，然后尾元素下沉logn次比较，
     * 所以总共是2nlogn次比较和交换
     * 所以时间复杂度是O(nlogn)
     * -------------------
     * 不需要额外空间，所以空间复杂度是O(1)
     * ============概念===================
     * PriorityQueue 优先队列，一种数据结构，提供的api有insert和删除最大或者最小的元素并返回值
     * 我们要实现这个数据结构，可以用二叉堆
     * --------------
     * 当一颗二叉树中的节点都大于等于他们的子节点的时候，这个二叉树是“堆有序”的
     * “堆有序”的“完全二叉树”叫做“二叉堆”，“二叉堆”简称为“堆”
     * 堆的有序化：将堆无序的完全二叉树变为堆有序的，叫做堆的有序化
     * 有两种方式，上浮swim和下沉sink
     * -------------------------------
     * 多叉堆：堆有序的完全多叉树
     * ---------------------
     * 优先队列的多向归并：多个优先队列归并为一个优先队列，来自多处的有序流合并为一个有序流
     *
     * @param args
     */
    public static void main(String[] args) {
        int[] array = {1, 124, 54, 34, 34, 6, 5};
        sort(array);
        Util.print(array);
    }

    private static void sort(int[] array) {
        int N = array.length;//这里其实是忽略了第0个元素
        //构造堆有序
        for (int i = N / 2; i > 0; i--) {//从右到左依次下沉，忽略大小为1的子树，所以从k/2开始
            sink(array, i, N);
        }

        //依次删除最大的元素到数组末尾
        while (N > 1) {
            exchange(array, 1, N--);
            sink(array, 1, N);
        }
    }


    /**
     * 将i 下沉（较小的元素下沉）
     */
    private static void sink(int[] array, int i, int N) {
        while (i * 2 < N) {//有子节点
            int j = i * 2;//子节点索引
            if (j < N && less(array, j, j + 1)) {//找出较大的子节点
                j++;
            }
            if (less(array, i, j)) {//和较大的子节点比较，如果小于子节点，则交换
                exchange(array, i, j);
                i = j;
            } else {
                break;
            }
        }
    }

    private static boolean less(int[] array, int i, int j) {
        return array[i - 1] < array[j - 1];
    }

    private static void exchange(int[] array, int i, int j) {
        i--;
        j--;
        int n = array[i];
        array[i] = array[j];
        array[j] = n;
    }
}
