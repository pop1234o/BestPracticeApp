package com.liyafeng.algorithm.basic.sort;

import java.util.Arrays;

/**
 * Created by liyafeng on 2018/2/12.
 */

public class N_Sort_InsertionSort_BinaryInsertionSort {

    /**
     * 二分插入排序   {@link java.util.TimSort}
     * 时间复杂度，最坏是O(n^2)，最好是O(nlogn)每次插入用logn次比较，有n-1次插入
     * 移动元素还是需要O(n^2)次
     * ======================================
     * 插入排序的改进算法，以前我们将新的元素插入有序数组，需要逐个比较
     * 现在我们将新插入的元素在前面有序数组中进行二分查找，然后插入
     * <p>
     * ==================================
     * 这种算法就是比较次数减少了，移动次数不变，所以在大量数据中会有差距
     * 而且这种排序算法是稳定的（相同元素前后位置不变）
     *
     * @param args
     */
    public static void main(String[] args) {
        int[] array = {1, 124, 54, 34, 34, 6, 5};
        binarySort(array);
        Util.print(array);
    }

    private static void binarySort(int[] array) {
        int lo = 0, hi = array.length, start = 1;
        for (; start < hi; start++) {
            int i = array[start];
            //二分查找并插入
            int left = lo;
            int right = start;
            while (left < right) {
                int mid = (left + right) >>> 1;
                if (i < array[mid]) {
                    right = mid;//这里不是mid减一了，因为right包含了右侧那个不比较的元素
                } else {//这里有可能是等于，
                    left = mid + 1;
                }
            }
            //此时left==right
            //left就是插入的位置，先把数组整体向右移动，然后插入
            int n = start - left;
            switch (n) {
                case 2:
                    //这种情况是   1,2,3...4,5,[6] 。 4,5分别右移
                    //                    left  start
                    array[left + 2] = array[left + 1];//右移
                case 1:
                    array[left + 1] = array[left];
                    break;
                default:
                    //left开始,包括left，整体向右移动
                    //指定开始位置，和长度即可
                    System.arraycopy(array, left, array, left + 1, n);

            }
            //插入
            array[left] = i;
        }
    }


}
