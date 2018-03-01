package com.liyafeng.algorithm.leetcode;

public class LeetCode_1_TwoSum {

    /**
     * 给定一个数组，和一个目标值，找出数组中和是目标值的两个元素
     *
     * ===========
     * Given an array of integers, return indices of the two numbers such that they add up to a specific target.
     * <p>
     * You may assume that each input would have exactly one solution, and you may not use the same element twice.
     * <p>
     * Example:
     * Given nums = [2, 7, 11, 15], target = 9,
     * <p>
     * Because nums[0] + nums[1] = 2 + 7 = 9,
     * return [0, 1].
     * ==============思路=================
     * 两个循环，第一个是sum减去元素，然后再之后的元素中找到和他们差相等的元素
     * 时间复杂度是O(n^2)
     * 也可以先排序，然后两个指针分别指向头尾，如果start+end>sum，则end--
     * 小于sum，则start++
     * 时间复杂度n+nlogn
     * =================考点=================
     * 找规律，如果两个元素和大于sum，那么肯定要减小某个元素，那肯定只能减小end
     * @param args
     */
    public static void main(String[] args) {
        int[] nums = {2, 7, 11, 15};
        int[] ints = twoSum(nums, 9);
        if (ints != null) {
            System.out.printf("i:" + ints[0] + " j:" + ints[1]);
        }

    }

    public static int[] twoSum(int[] nums, int target) {
        int lenght = nums.length;
        for (int i = 0; i < lenght; i++) {
            int other = target - nums[i];
            for (int j = i + 1; j < lenght; j++) {
                if (nums[j] == other) {
                    return new int[]{i, j};
                }
            }
        }

        return null;
    }
}
