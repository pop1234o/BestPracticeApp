package com.liyafeng.algorithm.leetcode.array;

/**
 * @author pop
 */
public class LeetCode_73_SetMatrixZeroes_Medium {

    /**
     * Given a m x n matrix, if an element is 0, set its entire row and column to 0. Do it in-place.
     * <p>
     * 一个 m x b矩阵，如果元素为0，那么把它所在的行和列都置为0，在原地操作（不使用额外空间）
     * <p>
     * ========示例=======
     * Input:
     * [
     * [1,1,1],
     * [1,0,1],
     * [1,1,1]
     * ]
     * Output:
     * [
     * [1,0,1],
     * [0,0,0],
     * [1,0,1]
     * ]
     * <p>
     * <p>
     * Input:
     * [
     * [0,1,2,0],
     * [3,4,5,2],
     * [1,3,1,5]
     * ]
     * Output:
     * [
     * [0,0,0,0],
     * [0,4,5,0],
     * [0,3,1,0]
     * ]
     * <p>
     * =========思路==============
     * 举例分析+数组遍历
     * 很明显我们需要遍历一遍，然后存下哪行哪列，但是如果不用额外空间
     * 那么就需要把信息存在原有的数组中。
     * 我们的思路是用第一行来存储 需要变0的列，和第一列来存储需要变0的行
     * <p>
     * <p>
     * <p>
     * =======知识点======
     * 举例分析+数组遍历
     *
     * =============
     * 空间O（1）  时间O(m*n)
     * @param args
     */
    public static void main(String[] args) {


    }

    public void setZeroes(int[][] matrix) {

        boolean firstColIsZero = false;
        int col = matrix[0].length;
        int row = matrix.length;
        for (int i = 0; i < row; i++) {

            //这一行的第一个元素是0 ，那么第一列就是0
            if (matrix[i][0] == 0) {
                firstColIsZero = true;
            }
            //遍历后面的元素,第一行就标记了 为0的列
            for (int j = 1; j < col; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }

        }

        //遍历，设置为0，除了第一行第一列
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                //如果第一行或者第一列标记了0，那么置为0
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }

        //处理第一行,只有第一行有0元素的时候，matrix[0][0]才置为0
        if (matrix[0][0] == 0) {
            for (int i = 0; i < col; i++) {
                matrix[0][i] = 0;
            }
        }
        //处理第一列
        if (firstColIsZero) {
            for (int i = 0; i < row; i++) {
                matrix[i][0] = 0;
            }
        }


    }

}
