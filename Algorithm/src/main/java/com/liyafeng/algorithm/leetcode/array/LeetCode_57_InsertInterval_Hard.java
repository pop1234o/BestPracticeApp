package com.liyafeng.algorithm.leetcode.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class LeetCode_57_InsertInterval_Hard {

    /**
     * Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).
     * You may assume that the intervals were initially sorted according to their start times.
     * <p>
     * 给定一个二维数组，合并重叠的区域，且插入一个间隔
     * 可以假定已经排好序了
     * <p>
     * ========示例=======
     * <p>
     * Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
     * Output: [[1,5],[6,9]]
     * <p>
     * Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
     * Output: [[1,2],[3,10],[12,16]]
     * Explanation: Because the new interval [4,8] overlaps with [3,5],[6,7],[8,10].
     * <p>
     * =========思路==============
     * 排序解法
     *
     * 先插入到数组，然后就是插入合并
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

        int[][] array = {{1, 2}, {3, 5}, {6, 7}, {8, 10}, {12, 16}};
        int[][] merge = insert(array, new int[]{4, 8});
        for (int[] ints : merge) {
            for (int anInt : ints) {
                System.out.print(anInt + ",");
            }
            System.out.println();
        }

    }

    public static int[][] insert(int[][] intervals, int[] newInterval) {
//        Arrays.sort(intervals, new Comparator<int[]>() {
//            @Override
//            public int compare(int[] t1, int[] t2) {
//                return t1[0] - t2[0];
//            }
//        });
        ArrayList<int[]> original = new ArrayList<>();
        boolean isInsert = false;
        //插入排序
        for (int[] interval : intervals) {
            if (!isInsert && newInterval[0] < interval[0]) {
                original.add(newInterval);
                isInsert = true;
            }
            original.add(interval);

        }
        //如果没有被插入
        if (!isInsert) {
            original.add(newInterval);
        }
        //新的数组
        ArrayList<int[]> integers = new ArrayList<>();
        for (int i = 0; i < original.size(); i++) {
            //intervals待加入的数组中的第一个元素的start大于已有数组的end,那么代表没有重叠，直接加入数组
            if (integers.size() == 0 || ((int[]) integers.get(integers.size() - 1))[1] < original.get(i)[0]) {
                integers.add(original.get(i));
            } else {
                //有交集，end判断哪个大就用哪个
                int end = integers.get(integers.size() - 1)[1];
                int endIn = original.get(i)[1];
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
