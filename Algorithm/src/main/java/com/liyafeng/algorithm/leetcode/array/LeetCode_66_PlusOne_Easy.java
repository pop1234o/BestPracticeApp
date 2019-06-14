package com.liyafeng.algorithm.leetcode.array;

/**
 * @author pop
 */
public class LeetCode_66_PlusOne_Easy {

    /**
     * Given a non-empty array of digits representing a non-negative integer, plus one to the integer.
     * The digits are stored such that the most significant digit is at the head of the list, and each element in the array contain a single digit.
     * You may assume the integer does not contain any leading zero, except the number 0 itself.
     * <p>
     * 用数组表示一个数字，将数字加1，返回加1后的数组
     * <p>
     * ========示例=======
     * <p>
     * Example 1:
     * <p>
     * Input: [1,2,3]
     * Output: [1,2,4]
     * Explanation: The array represents the integer 123.
     * <p>
     * Example 2:
     * <p>
     * Input: [4,3,2,1]
     * Output: [4,3,2,2]
     * Explanation: The array represents the integer 4321.
     * <p>
     * =========思路==============
     * 举例分析
     *
     * <p>
     * <p>
     * <p>
     * =======知识点======
     * 举例分析
     *
     * @param args
     */
    public static void main(String[] args) {

        int[] ints = plusOne(new int[]{0});
        for (int anInt : ints) {
            System.out.println("," + anInt);
        }

    }

    public static int[] plusOne(int[] digits) {


        for (int i = digits.length - 1; i >= 0; i--) {
            //不是9直接加
            if (digits[i] < 9) {
                digits[i]++;
                return digits;
            }
            //否则进位
            digits[i] = 0;
        }
        int[] ints = new int[digits.length + 1];
        ints[0] = 1;
        return ints;
    }


}
