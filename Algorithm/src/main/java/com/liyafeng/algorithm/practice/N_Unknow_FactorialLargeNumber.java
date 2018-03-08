package com.liyafeng.algorithm.practice;

import java.math.BigInteger;

/**
 * Created by liyafeng on 2018/3/8.
 */

public class N_Unknow_FactorialLargeNumber {

    /**
     * 算法题：大数阶乘
     * <p>
     * 给一个数字 n ( 0 < n <= 5000 ), 写一个程序，以字符串的形式返回数字的阶乘
     * <p>
     * 格式：
     * <p>
     * 第一行输入一个 整数 n，最后输出数字阶乘
     * <p>
     * 样例输入
     * <p>
     * n = 20
     * <p>
     * 样例输出
     * <p>
     * 2432902008176640000
     *
     * @param args
     */
    public static void main(String[] args) {
         String s = factorial(20);
        System.out.println(s);
    }

    /**
     * 思路是用整型数组来存储每一位
     * 2 0
     * *19
     * ----
     * 9*20 + 10*20 用数组计算
     * ======================
     * 这种是利用jdk提供的类来实现，里面原理也是用
     * @param n
     * @return
     */
    private static String factorial(int n) {

        BigInteger result = new BigInteger("1");

        for (int i = 1; i <= n; i++) {
            String s = Integer.toString(i);
            result =  result.multiply(new BigInteger(s));
        }
        return result.toString();
    }
}
