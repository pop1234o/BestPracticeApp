package com.liyafeng.algorithm.leetcode.array;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class LeetCode_15_3Sum_Medium {

    /**
     * 3Sum
     * 给定一个数组，找出所有 和为0的三个元素
     * <p>
     * 示例：
     * Given array nums = [-1, 0, 1, 2, -1, -4],
     * <p>
     * A solution set is:
     * [
     * [-1, 0, 1],
     * [-1, -1, 2]
     * ]
     * <p>
     * =========思路==============
     * 简化问题思考，第一个指针遍历所有可能的一个元素
     * 剩下的就变成了 2sum问题，而2sum问题需要数组是有序的
     * 所以我们需要对数组先排序
     * <p>
     * 这种的时间复杂度是O(n^2)
     * <p>
     * =============
     * 知识点:数组+问题简化+排序+指针
     *
     * @param args
     */
    public static void main(String[] args) {
//        List<List<Integer>> lists = threeSum(new int[]{-1, 0, 1, 2, -1, -4});
        List<List<Integer>> lists = threeSum(new int[]{-4, -1, -1, 0, 1, 2});
        for (int i = 0; i < lists.size(); i++) {
            List<Integer> integers = lists.get(i);
            for (Integer integer : integers) {
                System.out.print(integer + " ");
            }
            System.out.println();
        }
    }


    /**
     * 先升序排序
     * 第一个指针遍历数组，0~n-2
     * 然后就拆解为 求和为0-ai 的2sum
     * 2sum就是两个指针，lo = i+1 , hi = n
     *  value[lo]+value[hi]=sum
     * 如果 sum 等于 -ai ，就加入list，然后两个指针向中间靠拢（跳过不一样的元素）
     * sum小了，lo要++  大了，hi--
     * 直到遍历结束
     *
     * @param nums
     * @return
     */
    public static List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        LinkedList<List<Integer>> list = new LinkedList<>();
        //遍历到倒数第三个数
        for (int i = 0; i < nums.length - 2; i++) {
            int lo = i + 1, hi = nums.length - 1, sum = 0 - nums[i];
            //当前的数不能等于前一个数，因为这样会重复计算
            if (i == 0 || nums[i] != nums[i - 1]) {
                while (lo < hi) {
                    if (nums[lo] + nums[hi] == sum) {
                        //加入列表
                        list.add(Arrays.asList(nums[i], nums[lo], nums[hi]));
                        //lo指针向右找到第一个不等于当前值的数
                        while (lo < hi && nums[lo] == nums[lo + 1]) {
                            lo++;
                        }
                        //hi指针向左找到第一个不等于当前的数
                        while (lo < hi && nums[hi] == nums[hi - 1]) {
                            hi--;
                        }
                        //再次移动
                        lo++;
                        hi--;
                        //小了，lo要++
                    } else if (nums[lo] + nums[hi] < sum) {
                        lo++;
                        //大了，hi要--才能使得sum变小
                    } else {
                        hi--;
                    }
                }
            }
        }

        return list;

    }

    /**
     *
     */
    class Solution {

    }
}
