package com.liyafeng.algorithm.leetcode;

/**
 * Created by liyafeng on 2018/3/6.
 */

public class LeetCode_7_ReverseInteger_Easy {

    /**
     * 反转整数
     * Given a 32-bit signed integer, reverse digits of an integer.
     * <p>
     * Example 1:
     * <p>
     * Input: 123
     * Output:  321
     * Example 2:
     * <p>
     * Input: -123
     * Output: -321
     * Example 3:
     * <p>
     * Input: 120
     * Output: 21
     * Note:
     * Assume we are dealing with an environment which could only hold integers within the 32-bit signed integer range.
     * For the purpose of this problem, assume that your function returns 0 when the reversed integer overflows.
     *
     * @param args
     */
    public static void main(String[] args) {
        int reverse = reverse(-123);
        System.out.println(reverse);
    }

    /**
     * 从个位取数字，加入字符串中，然后转化为整型
     * ======================
     * 如果不允许使用字符串，那么取个位，然后乘以长度，然后相加，如果和大于整型最大值
     * 那么直接返回0
     *
     * @param x
     * @return
     */
    public static int reverse(int x) {
        if (x == 0) {
            return 0;
        }
        boolean isNegative = false;
        if (x < 0) {
            x = -x;
            isNegative = true;
        }
        StringBuilder builder = new StringBuilder();

        while (x != 0) {
            builder.append(x % 10);
            x = x / 10;
        }
        int i = 0;
        try {
            i = Integer.parseInt(builder.toString());
        } catch (NumberFormatException e) {
        }
        if (isNegative) {
            i = -i;
        }

        return i;
    }
}
