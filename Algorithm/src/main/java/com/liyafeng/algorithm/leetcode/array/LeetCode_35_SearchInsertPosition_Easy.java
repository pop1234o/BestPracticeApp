package com.liyafeng.algorithm.leetcode.array;

public class LeetCode_35_SearchInsertPosition_Easy {

    /**
     * Given a sorted array and a target value, return the index if the target is found. If not, return the index where it would be if it were inserted in order.
     * <p>
     * You may assume no duplicates in the array.
     * <p>
     * 给定一个有序数组，找到一个数的插入位置。
     *
     * @param args
     */
    public static void main(String[] args) {
        int i = searchInsert(new int[]{1, 3, 5, 6}, 5);
        System.out.println("输出结果"+i);
    }


    /**
     * @param nums
     * @param target
     * @return
     */
    public static int searchInsert(int[] nums, int target) {
        int lo = 0, hi = nums.length - 1;

        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (target > nums[mid]) {
                lo = mid + 1;
            } else if (target < nums[mid]) {
                hi = mid - 1;
            } else {
                return mid;
            }
        }

        return lo;
    }
}
