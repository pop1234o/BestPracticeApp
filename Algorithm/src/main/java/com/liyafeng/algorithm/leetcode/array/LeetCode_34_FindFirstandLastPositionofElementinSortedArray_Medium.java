package com.liyafeng.algorithm.leetcode.array;

public class LeetCode_34_FindFirstandLastPositionofElementinSortedArray_Medium {

    /**
     * Find First and Last Position of Element in Sorted Array
     * Given an array of integers nums sorted in ascending order, find the starting and ending position of a given target value.
     * Your algorithm's runtime complexity must be in the order of O(log n).
     * If the target is not found in the array, return [-1, -1].
     *
     * 一个升序数组，给定target，找出target在数组中的范围，时间复杂度logn ,没找到返回[-1,-1]
     *
     *
     * ========示例=======
     * Input: nums = [5,7,7,8,8,10], target = 8
     * Output: [3,4]
     *
     * =========思路==============
     * 升序数组，查找，肯定离不开二分法查找，但是此题要求找出target的范围
     * 所以我们用一个示例来进行 举例分析法 得出问题就是如何找出边界
     *
     *
     *
     *
     * =============
     * 知识点:数组+二分法查找
     *
     * @param args
     */
    public static void main(String[] args) {
    }


    public int[] searchRange(int[] nums, int target) {
        int lo=0,hi=nums.length-1;

//        while (lo<=hi){}

    }
}
