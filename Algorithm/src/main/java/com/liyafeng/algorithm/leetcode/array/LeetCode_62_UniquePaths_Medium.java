package com.liyafeng.algorithm.leetcode.array;

import java.util.ArrayList;

/**
 * @author pop
 */
public class LeetCode_62_UniquePaths_Medium {

    /**
     * A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
     * The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).
     * How many possible unique paths are there?
     * <p>
     * 一个二维表格，起始位置在左上角，目标是走到右下角，只能向右向下移动
     * 问有多少种走法
     * <p>
     * ========示例=======
     * <p>
     * Input: m = 3, n = 2
     * Output: 3
     * Explanation:
     * From the top-left corner, there are a total of 3 ways to reach the bottom-right corner:
     * 1. Right -> Right -> Down
     * 2. Right -> Down -> Right
     * 3. Down -> Right -> Right
     * <p>
     * =========思路==============
     * 很明显的动态规划
     * 当前点到最后点的路径个数，等于右侧点和下侧点路径个数相加
     *
     * <p>
     * <p>
     * <p>
     * =======知识点======
     * 动态规划+右上方向遍历数组
     *
     * @param args
     */
    public static void main(String[] args) {

        int i = uniquePaths(3, 2);
        System.out.println("路径个数：" + i);

    }

    /**
     * 通过自底向上动态规范来消除递归
     *
     * 其实自顶向下也可以，就是把起点当终点了
     *
     * 下面方法是斜着遍历，
     * 其实可以从右向左，从下向上遍历
     *
     * @param m 列
     * @param n 行
     * @return
     */
    public static int uniquePaths(int m, int n) {

        //1行  或者 1列的情况
        if (m == 1 || n == 1) {
            return 1;
        }
        int[][] ints = new int[n][m];
        ints[n - 1][m - 1] = 1;
        //定义指针
        int row = n - 1, col = m - 2;
        //遍历
        while (row >= 0) {

            int curRow = row;
            int curCol = col;
            while (curRow >= 0 && curCol < m) {
                int right = 0, down = 0;
                //下面的元素
                if (curRow + 1 < n) {
                    right = ints[curRow + 1][curCol];
                }
                //右侧的元素
                if (curCol + 1 < m) {
                    down = ints[curRow][curCol + 1];
                }

                //判断当前元素的路径个数
                ints[curRow][curCol] = right + down;

                curRow--;
                curCol++;
            }
            if (col > 0) {
                col--;
            } else {
                row--;
            }

        }

        return ints[0][0];
    }


}
