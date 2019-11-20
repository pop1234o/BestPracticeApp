package com.liyafeng.algorithm.leetcode.dynamic_programming;

/**
 * @author pop
 */
public class LeetCode_70_ClimbingStairs_Easy {

    /**
     * You are climbing a stair case. It takes n steps to reach to the top.
     * Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
     * Note: Given n will be a positive integer.
     * <p>
     * n层台阶，一次只能上1阶或者2阶，问有多少种走法？
     * n为正数
     *
     *
     * <p>
     * ========示例=======
     * Input: 3
     * Output: 3
     * Explanation: There are three ways to climb to the top.
     * 1. 1 step + 1 step + 1 step
     * 2. 1 step + 2 steps
     * 3. 2 steps + 1 step
     * <p>
     * <p>
     * =========思路==============
     * 递归，动态规划的思路
     * <p>
     * <p>
     * =======知识点======
     * 递归+动态规划
     * <p>
     * =============
     *
     * @param args
     */
    public static void main(String[] args) {


        int count = climbStairs(5);
        System.out.println("走法：" + count);
    }

    /**
     * 这里不能用这个退出方式，如果是 n=2的话就出问题了
     * if (n == 0) {
     * return 0;
     * }
     * <p>
     * 因为只能走1步或者2步，所以最后的退出条件也只能是1 或者 2
     * <p>
     * <p>
     * //这样也是不行，如果输入 3 有3中走法，而输出的是4
     * if (n == 1) {
     * return 2;
     * }
     * if (n == 0) {
     * return 0;
     * }
     * <p>
     * 这种时间复杂度是 2^n
     * 因为每个数都分裂为两种情况，然后 树的高度有n层
     *
     * @param n
     * @return
     */
    public static int climbStairs(int n) {

        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        //这样理解，第一次走1步 + 第一次走2步的和，走到最后，只剩1 步和2 步的时候直接返回 结果
        //比如n =2 如果用0判断，那么直接返回0就不对了。。。
        //因为斐波那契是从第三个元素开始的，前面元素不符合规律，所以需要单独判断

        return climbStairs(n - 1) + climbStairs(n - 2);
    }


    /**
     * 这是一个斐波那契数列，所以
     *
     * n = 0 1 2 3 4 5 6  ...
     *     0 1 2 3 5 8 13 ...
     *
     * 从3开始，当前值是 f(n) = f(n-1)+f(n-2)
     *
     *
     * 时间复杂度 O(n) 控件复杂度 O(1)
     */
    public class Solution {
        public int climbStairs(int n) {

            if(n<=0){
                return 0;
            }
            if(n==1){
                return 1;
            }
            if(n==2){
                return 2;
            }

            // f(n) = f(n-1)+f(n-2)
            int first = 1, second = 2, result = 0;
            for (int i = 2; i < n; i++) {
                result = first + second;
                first = second;
                second = result;
            }

            return result;
        }
    }


}
