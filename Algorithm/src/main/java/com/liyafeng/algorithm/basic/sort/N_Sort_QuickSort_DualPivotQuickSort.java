package com.liyafeng.algorithm.basic.sort;

import java.util.Arrays;

public class N_Sort_QuickSort_DualPivotQuickSort {

    /**
     * 双轴快速排序（时间复杂度 O(n log n)）
     * ==============原理==================
     *
     *
     * @param args
     */
    public static void main(String[] args) {

        int[] ints = {6, 1, 2, 5, 4, 8};
        Arrays.sort(ints);
        for (int i = 0; i < ints.length; i++) {
            System.out.print(ints[i]+" ");
        }
    }

    public static void sort(int[] ints, int lo, int hi) {


    }


}
