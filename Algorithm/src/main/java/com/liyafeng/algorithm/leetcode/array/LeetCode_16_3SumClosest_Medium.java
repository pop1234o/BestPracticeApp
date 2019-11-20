package com.liyafeng.algorithm.leetcode.array;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class LeetCode_16_3SumClosest_Medium {

    /**
     * 3Sum
     * 给定一个数组，找出 和 距离target最近的三个元素（假定只有唯一解）
     * <p>
     * Given array nums = [-1, 2, 1, -4], and target = 1.
     * The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
     * <p>
     * =========思路==============
     * 简化问题思考，变种的3sum问题，改变sum的判断条件
     *
     * <p>
     * =============
     * 知识点:数组+问题简化+排序+指针
     *
     * @param args
     */
    public static void main(String[] args) {
        int sum = threeSumClosest(new int[]{-1, 2, 1, -4}, 1);
        System.out.println("最接近的sum " + sum);

    }


    /**
     * 先升序排序
     * 第一个指针遍历数组，0~n-2
     * 然后就拆解为 求和为0-ai 的2sum
     * 2sum就是两个指针，lo = i+1 , hi = n
     * value[lo]+value[hi]=sum
     * 如果 sum 等于 -ai ，就加入list，然后两个指针向中间靠拢（跳过不一样的元素）
     * sum小了，lo要++  大了，hi--
     * 直到遍历结束
     * ==============
     * 时间复杂度 O(n^2)
     * 空间  O(1)
     *
     * @return
     */
    public static int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int closest = nums[0] + nums[1] + nums[nums.length - 1];
        //遍历到倒数第三个数
        for (int i = 0; i < nums.length - 2; i++) {
            int lo = i + 1, hi = nums.length - 1;
            while (lo < hi) {
                //计算三个sum
                int sum = nums[i] + nums[lo] + nums[hi];
                //sum和target比较
                if (sum == target) {
                    return target;
                    //如果sum大了，那肯定hi要变小才能使得sum变小
                } else if (sum > target) {
                    hi--;
                } else {
                    lo++;
                }
                //如果当前sum比上一个closest结果还要近
                if (Math.abs(sum - target) < Math.abs(closest - target)) {
                    closest = sum;
                }
            }
        }

        return closest;

    }

    /**
     *
     */
    class Solution {

    }
}
