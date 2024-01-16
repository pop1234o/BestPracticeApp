package com.liyafeng.algorithm.basic.sort;

/**
 * Created by liyafeng on 2018/2/10.
 */

public class N_Sort_MergeSort {


    /**
     * 归并排序（  ）
     * 时间复杂度: O(n log n)
     * 空间复杂度: O(n)
     * ==============================
     * 采用分治法的一种算法
     *
     * @param args
     */
    public static void main(String[] args) {

//        int[] array = {1, 124, 54, 34, 34, 6};
        int[] array = {1, 124, 54, 34, 34, 6, 5};
//        MergeSortTopDown.mergeSort(array);
        MergeSortBottomUp.mergeSort(array);
        for (int i : array) {
            System.out.print(i + ",");
        }
    }


    /**
     * 自底向上的归并排序，他是直接将数组分为长度为2的子数组，然后长度为4,
     * 一点点变大
     */
    static class MergeSortBottomUp {

        private static int[] aux;

        public static void mergeSort(int[] array) {
            int length = array.length;
            //复制一个数组
            aux = new int[length];

            //这个是来控制有序子数组长度的
            for (int i = 1; i < length; i *= 2) {//外循环是1 2 4 6 8，代表是各个归并的子数组的长度
                //这个就是 子数组的长度的2倍进行一个个排序
                for (int j = 0; j < length - i; j += i * 2) {
                    merge(array, j, j + i - 1, Math.min(j + i * 2 - 1, length - 1));
                }
            }
        }

        /**
         * 两个有序数组，合并为一个有序数组
         * mid 是两个有序数组的分解。 mid是前面数组的最后一个数，
         * 后面数组开始是mid+1
         * @param array
         * @param low
         * @param mid
         * @param high
         */
        private static void merge(int[] array, int low, int mid, int high) {
            for (int i = low; i <= high; i++) {
                aux[i] = array[i];
            }
            int i = low;
            int j = mid + 1;
            for (int k = low; k <= high; k++) {//这里要有等于
                if (i > mid) {
                    array[k] = aux[j++];
                } else if (j > high) {
                    array[k] = aux[i++];
                } else if (aux[i] > aux[j]) {
                    // j指针的数小，插入新数组，j++后移
                    array[k] = aux[j++];
                } else {
                    // i指针的数小，插入新数组，i++后移
                    array[k] = aux[i++];
                }
            }

        }
    }


//    int[] aux;
//
//    void sort(int[] arrays, int lo, int mid, int hi) {
//        if (lo >= hi) {
//            return;
//        }
//        sort(arrays, lo, (lo + mid) / 2, mid);
//        sort(arrays, mid + 1, (hi + mid + 1) / 2, hi);
//
//
//        for (int i = lo; i < hi; i++) {
//            aux[i] = arrays[i];
//        }
//
//        int i = lo, j = mid + 1;
//        for (int k = lo; k < hi; k++) {
//            if (i > mid) {
//                aux[k] = aux[j++];
//            } else if (j > hi) {
//                aux[k] = aux[i++];
//            } else if (aux[i] < aux[j]) {
//                aux[k] = aux[i++];
//            } else {
//                aux[k] = aux[j++];
//            }
//        }
//    }


    /**
     * 自顶向下的归并排序，因为他是从顶层开始递归分解数组，直到分解到底层然后对子数组两两归并
     * =======
     * 需要1/2nlgn — nlgn 次比较 ，需要6nlgn 次访问数组 2n次复制时访问，2n次移回原数组时访问
     * 最多2n次比较时访问
     */
    static class MergeSortTopDown {
        private static int[] aux;


        public static void mergeSort(int[] array) {
            aux = new int[array.length];
            sort(array, 0, array.length - 1);

        }

        private static void sort(int[] array, int low, int high) {
            if (low >= high) {
                return;
            }
            int mid = (high + low) / 2;
            sort(array, low, mid);//这其实就是一个拆分过程
            sort(array, mid + 1, high);
            merge(array, low, mid, high);//上面的拆分/排序完成，开始左右两个有序数组归并

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
}
