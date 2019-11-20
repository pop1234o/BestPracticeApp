package com.liyafeng.algorithm.leetcode.array;

/**
 * @author pop
 */
public class LeetCode_74_Searcha2DMatrix_Medium {

    /**
     * Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:
     * <p>
     * Integers in each row are sorted from left to right.
     * The first integer of each row is greater than the last integer of the previous row.
     * <p>
     * 在m x n的矩阵中查找target
     * 矩阵每一行都是排序好的
     * 每一行第一个元素都比上一行最后一个元素大
     * <p>
     * ========示例=======
     * Input:
     * matrix = [
     * [1,   3,  5,  7],
     * [10, 11, 16, 20],
     * [23, 30, 34, 50]
     * ]
     * target = 3
     * Output: true
     * <p>
     * Input:
     * matrix = [
     * [1,   3,  5,  7],
     * [10, 11, 16, 20],
     * [23, 30, 34, 50]
     * ]
     * target = 13
     * Output: false
     *
     * <p>
     * =========思路==============
     * 排序数组，查找，肯定就是二分法，或者变种二分法
     *
     * <p>
     * <p>
     *  思路2：把它当成一个一维数组
     *  n * m matrix convert to an array =>
     *      matrix[x][y] => a[x * m + y]
     * an array convert to n * m matrix =>
     *      a[x] =>matrix[x / m][x % m];
     *
     * <p>
     * =======知识点======
     * 二分法查找
     * <p>
     * =============
     *
     * @param args
     */
    public static void main(String[] args) {

        int[][] matrix = {{1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 50}};
        boolean b = searchMatrix(matrix, 3);
        System.out.println("是否存在" + b);


    }

    public static boolean searchMatrix(int[][] matrix, int target) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        //先查找竖向，判断元素在哪一行
        int lo = 0, hi = matrix.length - 1;
        while (hi >= lo) {
            int mid = (hi + lo) / 2;
            if (matrix[mid][0] == target) {
                return true;
            } else if (matrix[mid][0] < target) {
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }

        //如果小于所有元素
        if (lo - 1 < 0) {
            return false;
        } else {
            //查找这一个横行
            int[] matrixRow = matrix[lo - 1];
            int start = 0, end = matrix[0].length - 1;
            while (start <= end) {
                //这样写防止数据溢出
                int mid = start + (end - start) / 2;
                if (matrixRow[mid] == target) {
                    return true;
                } else if (matrixRow[mid] < target) {
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            }
        }

        return false;

    }

}
