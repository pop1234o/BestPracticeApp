package com.liyafeng.algorithm.leetcode;

/**
 * Created by liyafeng on 2018/3/7.
 */

public class LeetCode_9_PalindromeNumber_Easy {

    /**
     * 判断是否是回文数字
     * Determine whether an integer is a palindrome. Do this without extra space.
     * <p>
     * Some hints:
     * Could negative integers be palindromes? (ie, -1)
     * <p>
     * If you are thinking of converting the integer to string, note the restriction of using extra space.
     * <p>
     * You could also try reversing an integer. However, if you have solved the problem "Reverse Integer", you know that the reversed integer might overflow. How would you handle such case?
     * <p>
     * There is a more generic way of solving this problem.
     *
     * @param args
     */
    public static void main(String[] args) {
        boolean palindrome = isPalindrome(10);
        System.out.println(palindrome);
    }

    public static boolean isPalindrome(int x) {
        //12345
        int i = 10;
        if(x==Integer.MIN_VALUE){
            return false;
        }
        if (x < 0) {
           return false;
        }
        if (x / 10 == 0) {//只有一位
            return true;
        }
        while (x / i > 9) {
            i = i * 10;
        }
        int j = 10;//保证高位和低位再取同一位置上的数字时相等
        while (i >= j) {
            int hi = x / i % 10;//除以相同位数的10,然后对10取余
            int lo = x / (j / 10) % 10;//永远对10取余才能取到个位
            if (hi != lo) {
                return false;
            }
            i /= 10;
            j *= 10;
        }

        return true;
    }
}
