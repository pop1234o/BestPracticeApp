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
     * =========思路==============
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
        int rowBegin = 0, rowEnd = matrix.length;
        int colBegin = 0, colEnd = matrix[0].length;

        while (rowBegin<=rowEnd&&colBegin<=colEnd){

        }

        return integers;
    }


}
