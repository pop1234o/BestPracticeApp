package com.liyafeng.algorithm.sword2offer;

/**
 * Created by liyafeng on 2018/2/27.
 */

public class N47_AddTwoNumbers {

    /**
     * 不用加减乘除计算两个整数的和
     * ==================思路==================
     * 位运算
     *
     * @param args
     */
    public static void main(String[] args) {

        int num1 = 5;
        int num2 = 7;
        int sum;
        int carry;
        do {
            sum = num1 ^ num2; //不进位加法的结果
            carry = (num1 & num2) << 1;//求进位，只有1+1 才会进位，所以左移就是进位的
            num1 = sum;
            num2 = carry;
        } while (num2 != 0);
        System.out.println("sum:" + sum);
    }
}
