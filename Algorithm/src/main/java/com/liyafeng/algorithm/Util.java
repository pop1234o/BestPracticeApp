package com.liyafeng.algorithm;

public class Util {

    /**
     * 交换元素值
     *
     * @param array
     * @param i
     * @param j
     */
    public static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    /**
     * 反转这个数组
     *
     * @param nums
     * @param begin
     */
    public static void reverse(int[] nums, int begin) {
        int i = begin, j = nums.length - 1;
        while (i < j) {
            swap(nums, i, j);
            i++;
            j--;
        }
    }
}
