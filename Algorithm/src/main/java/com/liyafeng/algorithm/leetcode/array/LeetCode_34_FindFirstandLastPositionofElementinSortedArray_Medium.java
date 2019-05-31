package com.liyafeng.algorithm.leetcode.array;

import java.util.Arrays;

public class LeetCode_34_FindFirstandLastPositionofElementinSortedArray_Medium {

    /**
     * Find First and Last Position of Element in Sorted Array
     * Given an array of integers nums sorted in ascending order, find the starting and ending position of a given target value.
     * Your algorithm's runtime complexity must be in the order of O(log n).
     * If the target is not found in the array, return [-1, -1].
     * <p>
     * 一个升序数组，给定target，找出target在数组中的范围，时间复杂度logn ,没找到返回[-1,-1]
     * <p>
     * <p>
     * ========示例=======
     * Input: nums = [5,7,7,8,8,10], target = 8
     * Output: [3,4]
     * <p>
     * =========思路==============
     * 升序数组，查找，肯定离不开二分法查找，但是此题要求找出target的范围
     * 所以我们用一个示例来进行 举例分析法 得出问题就是如何找出边界
     * <p>
     * <p>
     * <p>
     * <p>
     * <p>
     * =============
     * 知识点:数组+二分法查找
     *
     * @param args
     */
    public static void main(String[] args) {
        int[] ints = searchRange(new int[]{5, 7, 7, 8, 8, 10}, 6);
        System.out.println("result:" + ints[0] + "  " + ints[1]);
    }


    /**
     * 这个思路是 找到target+0.5 的位置，和target-0.5的位置
     * 然后判断位置是否是 那个target，如果不是则是-1，
     *
     * 还要判断 target小于/大于 所有nums的情况
     *
     * @param nums
     * @param target
     * @return
     */
    public static int[] searchRange(int[] nums, int target) {
        if (nums == null) {
            return new int[]{-1, -1};
        }
        if (nums.length == 0) {
            return new int[]{-1, -1};
        }

        int lo = 0, hi = nums.length - 1;

        double lower = target - 0.5;
        int posLower = -1;
        while (lo <= hi) {
            int mid = (lo + hi) / 2;
            if (lower > nums[mid]) {
                lo = mid + 1;
            } else if (lower < nums[mid]) {
                hi = mid - 1;
            }
        }
        posLower = lo;

        double higher = target + 0.5;
        int posHigher = -1;

        lo = 0;
        hi = nums.length - 1;
        while (lo <= hi) {
            int mid = (lo + hi) / 2;
            if (higher > nums[mid]) {
                lo = mid + 1;
            } else if (higher < nums[mid]) {
                hi = mid - 1;
            }
        }
        posHigher = lo;

        if (posLower < nums.length && nums[posLower] != target) {//不是target，那么
            posLower = -1;
        } else if (posLower >= nums.length) {//这种情况是target
            posLower = -1;
        }

        if (posHigher - 1 >= 0 && nums[posHigher - 1] != target) {//不是target，那么  还有一种情况是target都小于数组中的元素
            posHigher = -1;
        } else {
            posHigher -= 1;
        }


        return new int[]{posLower, posHigher};
    }
}
