package com.liyafeng.algorithm.leetcode.array;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class LeetCode_54_SpiralMatrix_Medium {

    /**
     * Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.
     * <p>
     * 打印螺旋矩阵
     * <p>
     * ========示例=======
     * <p>
     * Input:
     * [
     * [ 1, 2, 3 ],
     * [ 4, 5, 6 ],
     * [ 7, 8, 9 ]
     * ]
     * Output: [1,2,3,6,9,8,7,4,5]
     * <p>
     * [
     * [1,2,3,4],
     * [5,6,7,8],
     * [9,10,11,12]
     * ]
     * =========思路==============
     * 指针变化+举例分析法
     * 控制四个指针进行边界控制
     * <p>
     * <p>
     * <p>
     * =============
     *
     * @param args
     */
    public static void main(String[] args) {
        int[][] array = new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        List<Integer> integers = spiralOrder(array);
        for (Integer integer : integers) {
            System.out.print(integer + " ");
        }
    }


    public static List<Integer> spiralOrder(int[][] matrix) {
        LinkedList<Integer> integers = new LinkedList<>();
        if (matrix == null) {
            return integers;
        }
        if (matrix.length == 0) {
            return integers;
        }
        int rowBegin = 0, rowEnd = matrix.length - 1;
        int colBegin = 0, colEnd = matrix[0].length - 1;

        while (rowBegin <= rowEnd && colBegin <= colEnd) {
            //第一行
            for (int i = colBegin; i <= colEnd; i++) {
                integers.add(matrix[rowBegin][i]);
            }

            rowBegin++;

            //最后一列
            for (int i = rowBegin; i <= rowEnd; i++) {
                integers.add(matrix[i][colEnd]);
            }

            colEnd--;

            //这里判断是为了 3行4列这种情况
            if (rowBegin <= rowEnd) {
                //最后一行
                for (int i = colEnd; i >= colBegin; i--) {
                    integers.add(matrix[rowEnd][i]);
                }
            }


            rowEnd--;

            if (colBegin <= colEnd) {
                //第一列
                for (int i = rowEnd; i >= rowBegin; i--) {
                    integers.add(matrix[i][colBegin]);
                }
            }

            colBegin++;

        }

        return integers;
    }


}
