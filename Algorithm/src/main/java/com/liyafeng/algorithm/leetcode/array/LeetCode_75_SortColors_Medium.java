package com.liyafeng.algorithm.leetcode.array;

import com.liyafeng.algorithm.Util;

/**
 * @author pop
 */
public class LeetCode_75_SortColors_Medium {

    /**
     * Given an array with n objects colored red, white or blue, sort them in-place so that objects of the same color are adjacent, with the colors in the order red, white and blue.
     * Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.
     * Note: You are not suppose to use the library's sort function for this problem.
     * <p>
     * 数组中 红白蓝（0，1，2）三种颜色，进行原地排序 ，你不能使用系统库的排序方法
     * <p>
     * ========示例=======
     * <p>
     * Input: [2,0,2,1,1,0]
     * Output: [0,0,1,1,2,2]
     *
     * <p>
     * =========思路==============
     * 荷兰旗问题 ，三向切分的快排
     * <p>
     * <p>
     * <p>
     * =======知识点======
     * 三向切分的快排
     * <p>
     * =============
     *
     * @param args
     */
    public static void main(String[] args) {


        int[] nums = {2, 0, 2, 1, 1, 0};
        sortColors(nums);

        for (int num : nums) {
            System.out.print(num + ",");
        }


    }

    public static void sortColors(int[] nums) {

        //定义三个指针
        //小于1 lt永远指向基准值的第一个
        int lt = 0;
        //大于1
        int gt = nums.length - 1;
        //遍历的指针
        int i = 0;

        //当遍历指针大于gt时代表排序完成
        while (i <= gt) {
            //这里的基准值就是1
            if (nums[i] < 1) {
                //交换后两个指针同时向后
                swap(nums, lt++, i++);
            } else if (nums[i] > 1) {
                swap(nums, gt--, i);
            } else {
                //等于问题
                i++;
            }
        }
    }

    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }


}
