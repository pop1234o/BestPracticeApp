package com.liyafeng.algorithm.sword2offer;

public class N31_MaxSumInArray {

    /**
     * 求一个整型数组中和子数组和的最大值（数组中允许有负数）
     * ==========================
     * 举例分析法，这个最好举个简单的例子，算一算，然后从中就可以找出最大子数组的规律
     * ====================
     * <p>
     * <p>
     * 依次累加，直到和遇到负数，重新开始记录
     * <p>
     * 那么这个子数组的范围就是start，end
     * <p>
     * 这个需要一个额外的变量一直记录历史中最大的值
     * ====================================
     * 举例分析总结，就是动态规划，用公式来表示答案，用循环或者递归来实现动态规划
     * 动态规划就是这一个的解和前一个的关系
     * <p>
     * f(i)={
     * pData[i] 当i=0或者f(i-1)<0
     * f(i-1)+pData[i] 当i>0并且f(i-1)>0
     * }
     *
     * @param args
     */
    public static void main(String[] args) {

        int[] a = {1, -2, 3, 10, -4, 7, 2, -5};
        int sum = getMaxSum(a);
        System.out.println(sum);
    }

    private static int getMaxSum(int[] a) {
        int start = 0;
        int end = 1;
        int sum = 0;
        int maxSum = 0;
        for (int i = 0; i < a.length; i++) {
            if (sum <= 0) {
                sum = a[i];
            } else {
                sum += a[i];
            }
            if (sum > maxSum) {
                maxSum = sum;
            }
        }
        return maxSum;
    }


}
