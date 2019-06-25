package com.liyafeng.algorithm.leetcode.array;

/**
 * @author pop
 */
public class LeetCode_80_RemoveDuplicatesFromSortedArrayII_Medium {

    /**
     * Given a sorted array nums, remove the duplicates in-place such
     * that duplicates appeared at most twice and return the new length.
     * <p>
     * Do not allocate extra space for another array,
     * you must do this by modifying the input array in-place with O(1) extra memory.
     * <p>
     * 一个有序数组，移除重复的元素，使得每个元素最多显示两次，返回新数组的长度
     * 不要使用额外的内存空间。
     *
     *
     * <p>
     * ========示例=======
     * <p>
     * <p>
     * Example 1:
     * Given nums = [1,1,1,2,2,3],
     * <p>
     * Your function should return length = 5, with the first five elements of nums being 1, 1, 2, 2 and 3 respectively.
     * <p>
     * It doesn't matter what you leave beyond the returned length.
     * <p>
     * <p>
     * Example 2:
     * Given nums = [0,0,1,1,1,1,2,3,3],
     * <p>
     * Your function should return length = 7, with the first seven elements of nums being modified to 0, 0, 1, 1, 2, 3 and 3 respectively.
     * <p>
     * It doesn't matter what values are set beyond the returned length.
     * <p>
     * =========思路==============
     * 双指针遍历
     * 一个指针遍历数组，一个指针指向当前小于两个的元素
     * <p>
     * <p>
     * <p>
     * =======知识点======
     *
     * <p>
     * =============
     *
     * @param args
     */
    public static void main(String[] args) {
//        int[] nums = {0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
//        int[] nums = {0, 0, 1, 1, 1, 1, 2, 3, 3};
        int[] nums = {1, 2};
        int length = removeDuplicates(nums);
        for (int i = 0; i < length; i++) {
            System.out.print(nums[i] + " ");
        }

    }

    public static int removeDuplicates(int[] nums) {
        if (nums == null) {
            return 0;
        }
        if (nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return 1;
        }


        int currentIndex = 0;

        //当前元素的重复个数
        int count = 1;
        //注意这里是从1开始遍历的
        for (int i = 1; i < nums.length; i++) {
            //如果不相等，那么直接赋值
            if (nums[i] != nums[currentIndex]) {
                nums[++currentIndex] = nums[i];
                count = 1;
            } else if (count < 2) {
                //相等，但是没到两个重复，currentIndex向后
                //这里要赋值一下
                nums[++currentIndex] = nums[i];
                count++;
            }
            //相等，而且count为2，那么 i 继续向后遍历，直到遇到一个不相等的为止。

        }
        return currentIndex + 1;

    }


}
