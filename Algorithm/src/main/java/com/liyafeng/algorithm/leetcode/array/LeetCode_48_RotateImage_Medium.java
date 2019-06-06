package com.liyafeng.algorithm.leetcode.array;

import java.util.logging.Logger;

public class LeetCode_48_RotateImage_Medium {

    /**
     * You are given an n x n 2D matrix representing an image.
     * Rotate the image by 90 degrees (clockwise).
     * 顺时针旋转数组，禁止使用额外空间
     * <p>
     * ========示例=======
     * Given input matrix =
     * [
     * [1,2,3],
     * [4,5,6],
     * [7,8,9]
     * ],
     * <p>
     * <p>
     * <p>
     * rotate the input matrix in-place such that it becomes:
     * [
     * [7,4,1],
     * [8,5,2],
     * [9,6,3]
     * ]
     * <p>
     * <p>
     * [
     * [1,2,3,4],
     * [4,5,6,7],
     * [7,8,9,8],
     * [7,8,9,8]
     * ],
     * <p>
     * =========思路==============
     * 举例分析法+简化问题法
     * <p>
     * 将旋转转化为 上下交换+对角线交换
     * <p>
     * <p>
     * =============
     *
     * @param args
     */
    public static void main(String[] args) {

        int[][] array = new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        rotate(array);

        for (int i = 0; i < array.length; i++) {
            int[] ints = array[i];
            for (int j = 0; j < ints.length; j++) {
                System.out.print(" " + ints[j]);

            }
            System.out.println();
        }

    }

    public static void rotate(int[][] matrix) {
        //上下互换
        int start = 0, end = matrix.length - 1;
        while (start < end) {
            int[] temp = matrix[start];
            matrix[start] = matrix[end];
            matrix[end] = temp;
            start++;
            end--;
        }

        //对角线互换
        int line = 0;
        while (line < matrix.length - 1) {
            for (int i = line + 1; i < matrix.length; i++) {
                //第 line行 i列 的元素 和 第i行第line列的元素交换
                int temp = matrix[line][i];
                matrix[line][i] = matrix[i][line];
                matrix[i][line] = temp;
            }
            line++;
        }


    }


}
