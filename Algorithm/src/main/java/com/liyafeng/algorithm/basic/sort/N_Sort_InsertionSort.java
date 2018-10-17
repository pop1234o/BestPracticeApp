package com.liyafeng.algorithm.basic.sort;

/**
 * Created by liyafeng on 2018/2/12.
 */

public class N_Sort_InsertionSort {

    /**
     * 插入排序（平均需要n^2/4次比较和n^2/4次交换
     * 最坏情况是逆序数组，需要n^2/2次比较和n^2/2次交换
     * 最好情况是正序数组，需要n-1次比较和0次交换）
     * 时间复杂度：O(n^2)
     * ================原理================
     * 从第二个元素开始，插入前面已经排序好的数组
     * 依次和前面元素比较，小于就交换，直到大于停止内循环
     *
     * @param args
     */
    public static void main(String[] args) {
        int[] array = {1, 124, 54, 34, 34, 6, 5};
        sort(array);
        Util.print(array);
    }

    private static void sort(int[] array) {
//        for (int i = 1; i < array.length; i++) {
//            for (int j = i; j > 0 && array[j] < array[j - 1]; j--) {//依次插入之前已排序好的元素中
//                Util.exchange(array, j, j - 1);
//            }
//        }


    }
}
