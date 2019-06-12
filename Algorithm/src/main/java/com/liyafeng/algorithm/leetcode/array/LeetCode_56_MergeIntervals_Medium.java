package com.liyafeng.algorithm.leetcode.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class LeetCode_56_MergeIntervals_Medium {

    /**
     * Given a collection of intervals, merge all overlapping intervals.
     * <p>
     * 给定一个二维数组，合并重叠的区域
     * <p>
     * ========示例=======
     * <p>
     * Input: [[1,3],[2,6],[8,10],[15,18]]
     * Output: [[1,6],[8,10],[15,18]]
     * Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].
     * <p>
     * <p>
     * Input: [[1,4],[4,5]]
     * Output: [[1,5]]
     * Explanation: Intervals [1,4] and [4,5] are considered overlapping.
     * <p>
     * =========思路==============
     * 朴素解法，每次进入一个数组，和已经进入的比较，查看是否有交集，如果有就合并，没有就加入
     * 时间复杂度O(n^2)
     * <p>
     * 排序解法
     * 对第一个元素排序后，然后遍历，加入合并后的数组
     * 只需要比较哪个end大即可
     * 时间复杂度O(nlogn)
     *
     *
     * <p>
     * <p>
     * <p>
     * =============
     *
     * @param args
     */
    public static void main(String[] args) {

        int[][] array = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};
        int[][] merge = merge(array);
        for (int[] ints : merge) {
            for (int anInt : ints) {
                System.out.print(anInt + ",");
            }
            System.out.println();
        }

    }

    public static int[][] merge(int[][] intervals) {

        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] t1, int[] t2) {
                return t1[0] - t2[0];
            }
        });
        //新的数组
        ArrayList<int[]> integers = new ArrayList<>();
        for (int i = 0; i < intervals.length; i++) {
            //intervals待加入的数组中的第一个元素的start大于已有数组的end,那么代表没有重叠，直接加入数组
            if (integers.size() == 0 || ((int[]) integers.get(integers.size() - 1))[1] < intervals[i][0]) {
                integers.add(intervals[i]);
            } else {
                //有交集，end判断哪个大就用哪个
                int end = integers.get(integers.size() - 1)[1];
                int endIn = intervals[i][1];
                integers.get(integers.size() - 1)[1] = end > endIn ? end : endIn;
            }
        }

        int[][] ints = new int[integers.size()][];

        for (int i = 0; i < integers.size(); i++) {
            ints[i] = integers.get(i);
        }
        return ints;
    }

}
