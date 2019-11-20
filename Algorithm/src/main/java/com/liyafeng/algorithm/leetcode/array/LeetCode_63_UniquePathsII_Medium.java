package com.liyafeng.algorithm.leetcode.array;

/**
 * @author pop
 */
public class LeetCode_63_UniquePathsII_Medium {

    /**
     * A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
     * The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).
     * Now consider if some obstacles are added to the grids. How many unique paths would there be?
     * <p>
     * 一个二维表格，起始位置在左上角，目标是走到右下角，只能向右向下移动
     * 如果在表格中添加一些障碍物，问有多少种走法
     * 1代表障碍物， 0代表可通过
     * <p>
     * ========示例=======
     * <p>
     * Input:
     * [
     * [0,0,0],
     * [0,1,0],
     * [0,0,0]
     * ]
     * Output: 2
     * Explanation:
     * There is one obstacle in the middle of the 3x3 grid above.
     * There are two ways to reach the bottom-right corner:
     * 1. Right -> Right -> Down -> Down
     * 2. Down -> Down -> Right -> Right
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

        int i = uniquePathsWithObstacles(new int[][]{{0, 0, 0}, {0, 1, 0}, {0, 0, 0}});
        System.out.println("路径个数：" + i);

    }

    /**
     * 通过自底向上动态规范来消除递归
     * <p>
     * 其实自顶向下也可以，就是把起点当终点了
     * <p>
     * 下面方法是斜着遍历，
     * 其实可以从右向左，从下向上遍历
     *
     * @return
     */
    public static int uniquePathsWithObstacles(int[][] obstacleGrid) {

        int n = obstacleGrid.length;
        int m = obstacleGrid[0].length;
        int row = n - 1, col = m - 1;
        //第一个就是障碍物
        if (obstacleGrid[0][0] == 1 || obstacleGrid[row][col] == 1) {
            return 0;
        }

//        int[][] ints = new int[n][m];
//        ints[n - 1][m - 1] = 1;
        //定义指针,指向最后一个元素
        //遍历,外层从右向左遍历，里层从下向上遍历
        while (col >= 0) {

            int curRow = row;
            int curCol = col;
            while (curRow >= 0) {
                //最后一个元素，直接忽略
                if (curRow == n - 1 && curCol == m - 1) {
                    obstacleGrid[curRow][curCol] = 1;
                } else if (obstacleGrid[curRow][curCol] == 1) {
                    //当前是障碍物，路径个数为0
                    obstacleGrid[curRow][curCol] = 0;
                } else {
                    //计算
                    int right = 0, down = 0;
                    //下面的元素
                    if (curRow + 1 < n) {
                        right = obstacleGrid[curRow + 1][curCol];
                    }
                    //右侧的元素
                    if (curCol + 1 < m) {
                        down = obstacleGrid[curRow][curCol + 1];
                    }

                    //判断当前元素的路径个数
                    obstacleGrid[curRow][curCol] = right + down;

                }
                curRow--;

            }

            col--;

        }

        return obstacleGrid[0][0];
    }


}
