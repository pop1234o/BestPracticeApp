package com.liyafeng.algorithm.sort;

/**
 * Created by liyafeng on 2018/2/10.
 */

public class N_Sort_MergeSort {


    /**
     * @param args 归并排序（时间复杂度 O(n log n)）
     *             <p>
     *             采用分治法的一种算法
     */
    public static void main(String[] args) {

//        int[] array = {1, 124, 54, 34, 34, 6};
        int[] array = {1, 124, 54, 34, 34, 6, 5};
        MergeSortTopDown.mergeSort(array);
        for (int i : array) {
            System.out.print(i + ",");
        }
    }

}

class MergeSortBottomUp{

}

class MergeSortTopDown{
    private static int[] aux;


    public static void mergeSort(int[] array) {
        aux = new int[array.length];
        sort(array, 0, array.length - 1);

    }

    private static void sort(int[] array, int low, int high) {
        if (low >= high) {
            return;
        }
        int mid = low + (high - low) / 2;
        sort(array, low, mid);
        sort(array, mid + 1, high);
        merge(array, low, mid, high);

    }

    private static void merge(int[] array, int low, int mid, int high) {

        System.out.println("merge:" + low + "-" + mid + "-" + high);
        //复制到辅助数组中
        for (int i = low; i <= high; i++) {
            aux[i] = array[i];
        }

        int i = low;
        int j = mid + 1;
        for (int k = low; k <= high; k++) {
            if (i > mid) {
                array[k] = aux[j++];
            } else if (j > high) {
                array[k] = aux[i++];
            } else if (aux[i] < aux[j]) {
                array[k] = aux[i++];
            } else {
                array[k] = aux[j++];
            }
        }
    }
}