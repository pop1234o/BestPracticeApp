package com.liyafeng.algorithm.leetcode.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author pop
 */
public class LeetCode_78_Subsets_Medium {

    /**
     * Given a set of distinct integers, nums, return all possible subsets (the power set).
     * Note: The solution set must not contain duplicate subsets.
     * <p>
     * 给定一组不同的整数，输出他所有的子集
     *
     * <p>
     * ========示例=======
     * <p>
     * Input: nums = [1,2,3]
     * Output:
     * [
     * [3],
     * [1],
     * [2],
     * [1,2,3],
     * [1,3],
     * [2,3],
     * [1,2],
     * []
     * ]
     * <p>实际输出结果
     * []
     * 1,
     * 1,2,
     * 1,2,3,
     * 1,3,
     * 2,
     * 2,3,
     * 3,
     * =========思路==============
     * 回溯法，就像深度优先遍历一样，在遍历的时候加入数组。
     * <p>
     * <p>
     * <p>
     * =======知识点======
     * 回溯法
     * <p>
     * =============
     *
     * @param args
     */
    public static void main(String[] args) {

        List<List<Integer>> subsets = subsets(new int[]{1, 2, 3});

        for (List<Integer> subset : subsets) {
            for (Integer integer : subset) {
                System.out.print(integer + ",");
            }
            System.out.println();
        }

    }


    public static List<List<Integer>> subsets(int[] nums) {

        //存放结果的集合
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(nums);
        backtrack(list, new ArrayList<Integer>(), nums, 0);
        return list;

    }


    private static void backtrack(List<List<Integer>> list, List<Integer> tempList, int[] nums, int start) {
        //存储一个结果,这里要用新建的数组存储
        list.add(new ArrayList<Integer>(tempList));
        //遍历每个元素开头的情况
        for (int i = start; i < nums.length; i++) {
            //加入第一个元素，这里是为了1开头的那种情况
            tempList.add(nums[i]);
            //继续深入
            backtrack(list, tempList, nums, i + 1);
            //移除栈顶元素，实际上就是回溯到上一个解
            tempList.remove(tempList.size() - 1);
        }
    }


}
