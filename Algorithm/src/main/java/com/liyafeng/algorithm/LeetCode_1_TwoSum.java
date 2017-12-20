package com.liyafeng.algorithm;

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
     *
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
