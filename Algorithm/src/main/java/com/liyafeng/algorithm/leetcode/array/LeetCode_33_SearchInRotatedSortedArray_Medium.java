package com.liyafeng.algorithm.leetcode.array;

import java.util.Arrays;

public class LeetCode_33_SearchInRotatedSortedArray_Medium {

    /**
     * Search in Rotated Sorted Array
     * 有一个升序的旋转数组（rotated at some pivot ）
     * 给定一个target，找出这个target的index（在数组中的位置），没有返回-1
     * 需要时间复杂度是 O(logn)
     * assume no duplicate exists in the array.
     * (假定没有重复的元素)
     * <p>
     * ========示例=======
     * Input: nums = [4,5,6,7,0,1,2], target = 0
     * Output: 4
     * <p>
     * =========思路==============
     * 旋转数组特性 ，就是 pivot 左侧的元素都大于第一个元素，
     * 右侧的元素都小于最后一个元素，左侧的所有元素大于右侧的所有元素
     * 所以我们根据这个特性就知道当前元素在哪一侧。
     * 结合二分法查找就能找到这个target了
     *
     *
     * <p>
     * ========知识点=====
     * 知识点:数组+旋转数组特性+二分法查找
     *
     * @param args
     */
    public static void main(String[] args) {
        int index = search(new int[]{4, 5, 6, 7, 0, 1, 2}, 0);
        System.out.println("找到target位置：" + index);
    }

    /**
     * 先二分法查找出最小的那个元素，
     * 然后再用传统的二分法找到真正的mid
     * <p>
     * 4,5,6,7,0,1,2
     *
     * @param nums
     * @param target
     * @return
     */
    public static int search(int[] nums, int target) {
        int lo = 0, hi = nums.length - 1;
        //找到数组中最小值 ,注意这里是小于，而且不能是小于等于，否则找到的好像有问题 比如 5 1 3
        while (lo < hi) {
            int mid = (lo + hi) / 2;
            if (nums[mid] > nums[hi]) {
                lo = mid + 1;
            } else {
                hi = mid ;
            }
        }
        int fisrt = lo;

        lo = 0;
        hi = nums.length - 1;

        while (lo <= hi) {
            int mid = (lo + hi) / 2;
            //找到真正的中点，开始的索引理应是0，但是现在起始点往后偏移了first，所以要加上，取余
            int realMid = (fisrt + mid) % nums.length;
            if (target == nums[realMid]) {
                return realMid;
            } else if (target > nums[realMid]) {
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }

        }


        return -1;

    }


}
