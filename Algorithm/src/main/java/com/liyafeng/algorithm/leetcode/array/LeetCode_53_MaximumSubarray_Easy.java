package com.liyafeng.algorithm.leetcode.array;

public class LeetCode_53_MaximumSubarray_Easy {

    /**
     * Given an integer array nums, find the contiguous subarray (containing at least one number)
     * which has the largest sum and return its sum.
     * <p>
     * If you have figured out the O(n) solution,
     * try coding another solution using the divide and conquer approach, which is more subtle.
     * <p>
     * 找到和最大的子数组 ，要求时间复杂度低于O(n)
     * <p>
     * ========示例=======
     * <p>
     * Input: [-2,1,-3,4,-1,2,1,-5,4],
     * Output: 6
     * Explanation: [4,-1,2,1] has the largest sum = 6.
     * <p>
     * =========思路==============
     * 动态规划
     * <p>
     * <p>
     * 到当前元素的最大和的子序列一定是前一个的元素的最大和 如果是负数，那么最大就是当前元素，如果是正数，那么就相加
     *
     * A代表数组，i代表 0-i maxSubArray(A,i)代表 0-i中子序列的最大和
     * 数学建模：
     * maxSubArray(A,i) = A[i]+ ( maxSubArray(A,i-1)>0?maxSubArray(A,i-1):0 )
     *
     *
     * ========知识点=====
     * 动态规划
     *
     * @param args
     */
    public static void main(String[] args) {

        int maxSubArray = maxSubArray(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4});
        System.out.println("最大子数组和" + maxSubArray);
    }


    public static int maxSubArray(int[] nums) {

        int maxSum = Integer.MIN_VALUE;
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            if (sum <= 0) {
                sum = nums[i];
            } else {
                sum += nums[i];
            }

            if (sum > maxSum) {
                maxSum = sum;
            }
        }

        return maxSum;

    }



}
