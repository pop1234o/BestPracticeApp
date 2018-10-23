package com.liyafeng.algorithm.practice;

/**
 * Created by liyafeng on 2018/3/7.
 */

public class N_Unknow_无序数组中找出和为k的一组数 {

    /**
     * 算法题：k 数和
     * <p>
     * 给定 n 个不同的正整数，整数 k（k < = n）以及一个目标数字。在这 n 个数里面找出 k 个数，使得这 k 个数的和等于目标数字，写一个函数实现找到不同的方案的数量。
     * <p>
     * =========相同题目============
     * 一个无序，不重复数组，输出N个元素，使得N个元素的和相加为M，给出时间复杂度、空间复杂度
     * =====================
     * 格式：
     * <p>
     * 输入第一行输入一个整数数组，第二行输入一个整数 k ，第三行输入一个整数 target最后输出使得数组中不同的 k 个数的和为 target 的种类。
     * <p>
     * 样例输入
     * <p>
     * [ 1，2，3，4 ]
     * k = 2
     * target = 5
     * <p>
     * 样例输出
     * <p>
     * 2
     * ============================================
     * 思路，这个题有点像找出数组中和为k的两个元素
     * {@link com.liyafeng.algorithm.sword2offer.N41_TwoNumbersWithSum}
     * 但是前提是有序数组
     * -----------------------------
     * 如果是无序数组，
     * 那么这个题的考点就是动态规划！
     *
     * @param args
     */
    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4};
        int k = 2;
        int target = 5;
        int i = Solution.kSum(array, k, target);
        System.out.println(i);
    }


    /**
     * 这类问题一般都是使用动态规划的方法。
     * 建立到一个三维数组dp[i][j][k]表示在前i个数里取j个数和为k的方案数，
     * 于是dp[i][j][k] = dp[i-1][j][k] + dp[i-1][j-1][k-A[i]]，
     * 即包括A[i]和不包括A[i]的两种方案。
     * 可以看到i只跟i-1有关，所以我们可以把三维数组变成二维数组，把第一维去掉。
     *
     * @param A
     * @param k
     * @param target
     * @return
     */
    public static int kSum(int A[], int k, int target) {
        int n = A.length;
        int[][][] f = new int[n + 1][k + 1][target + 1];
        for (int i = 0; i < n + 1; i++) {
            f[i][0][0] = 1;
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= k && j <= i; j++) {
                for (int t = 1; t <= target; t++) {
                    f[i][j][t] = 0;
                    if (t >= A[i - 1]) {
                        f[i][j][t] = f[i - 1][j - 1][t - A[i - 1]];
                    }
                    f[i][j][t] += f[i - 1][j][t];
                } // for t
            } // for j
        } // for i
        return f[n][k][target];
    }


    public static class Solution {
        /**
         * 利用二维数组，像纸币面额问题一样，递归关系：dp[ y ][ z ] += dp[ y-1 ][ z-A[x] ] ，dp[ y ][ z ]代表 y 个数之和为 z 的方案个数。
         * <p>
         * ***  时间复杂度  O（n*k*target）， 空间复杂度 O（k*target）****
         *
         * @param A:      an integer array.
         * @param k:      a positive integer (k <= length(A))
         * @param target: a integer
         * @return an integer
         */
        public static int kSum(int A[], int k, int target) {
            //   T(n, k, target) = O(n*k*target). area(n, k, target) = O(k*target)
            int n = A.length;
            int[][] dp = new int[k + 1][target + 1];
            dp[0][0] = 1;
            for (int x = 0; x < n; x++)
                for (int y = k; y >= 1; y--)
                    for (int z = target; z >= A[x]; z--)
                        dp[y][z] += dp[y - 1][z - A[x]];
            return dp[k][target];
        }
    }
}
