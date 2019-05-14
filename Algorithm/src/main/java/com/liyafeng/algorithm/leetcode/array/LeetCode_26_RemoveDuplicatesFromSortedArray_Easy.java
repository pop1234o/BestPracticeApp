package com.liyafeng.algorithm.leetcode.array;

import java.util.Arrays;

public class LeetCode_26_RemoveDuplicatesFromSortedArray_Easy {

    /**
     * Remove Duplicates from Sorted Array
     * 给定一组有序数组，移除数组中的重复元素到数组最后（不能创建额外的内存空间）
     * 返回不重复元素的长度
     * ========示例=======
     * [0,0,1,1,1,2,2,3,3,4],
     * 前5个元素是 [0, 1, 2, 3, 4 ....]  返回length = 5
     * <p>
     * <p>
     * =========思路==============
     *
     * <p>
     * =============
     * 知识点:数组+两个指针遍历
     *
     * @param args
     */
    public static void main(String[] args) {

        int[] nums = {0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
        int length = removeDuplicates(nums);
        for (int i = 0; i < length; i++) {
            System.out.printf(nums[i] + " ");
        }
    }

    /**
     * 两个指针，第一个i遍历数组，第二个currentIndex表示当前不重复的数
     *
     * i 1~n  当i那个元素和当前的不重复元素不一样时，我们将i元素赋值给 currentIndex的下一个元素
     * =============
     * 时间 O(n)
     * 空间 O(1)
     * @param nums
     * @return
     */
    public static int removeDuplicates(int[] nums) {
        if (nums == null) {
            return 0;
        }
        if (nums.length == 0) {
            return 0;
        }
        int currentIndex = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[currentIndex]) {
                nums[++currentIndex] = nums[i];
            }
        }

        return currentIndex + 1;
    }

}
