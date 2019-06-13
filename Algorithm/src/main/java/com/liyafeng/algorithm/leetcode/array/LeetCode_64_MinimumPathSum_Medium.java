package com.liyafeng.algorithm.leetcode.array;

/**
 * @author pop
 */
public class LeetCode_64_MinimumPathSum_Medium {

    /**
     * Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right which minimizes the sum of all numbers along its path.
     * Note: You can only move either down or right at any point in time.
     * <p>
     * 一个二维表格，起始位置在左上角，目标是走到右下角，只能向右向下移动
     * 每个元素都有数字，找到一个最短路径
     * <p>
     * ========示例=======
     * <p>
     * Input:
     * [
     * [1,3,1],
     * [1,5,1],
     * [4,2,1]
     * ]
     * Output: 7
     * Explanation: Because the path 1→3→1→1→1 minimizes the sum.
     * <p>
     * =========思路==============
     * 很明显的动态规划
     * 从后向前遍历，找出每个点到最后一个点的最短路径
     *
     * <p>
     * <p>
     * <p>
     * =======知识点======
     * 动态规划+遍历二维数组
     *
     * @param args
     */
    public static void main(String[] args) {

        int i = minPathSum(new int[][]{{1, 3, 1}, {1, 5, 1}, {4, 2, 1}});
        System.out.println("最短路径：" + i);

    }

    /**
     * 外层从右向左遍历，内层从下到上遍历
     *
     * @return
     */
    public static int minPathSum(int[][] grid) {

        int col = grid[0].length - 1;
        while (col >= 0) {

            int row = grid.length - 1;
            while (row >= 0) {
                //最后一个元素，忽略
                if (row == grid.length - 1 && col == grid[0].length - 1) {

                } else {
                    //比较右 和 下哪个距离最后一个点短
                    int right = Integer.MAX_VALUE, down = Integer.MAX_VALUE;
                    if (col + 1 < grid[0].length) {
                        right = grid[row][col + 1];
                    }
                    if (row + 1 < grid.length) {
                        down = grid[row + 1][col];
                    }

                    grid[row][col] = (right < down ? right : down) + grid[row][col];
                }

                row--;
            }

            col--;
        }

        return grid[0][0];
    }


}
