package com.liyafeng.algorithm.leetcode;

/**
 * Created by liyafeng on 2018/3/6.
 */

public class LeetCode_8_StringToInteger_atoi_Medium {

    /**
     * 字符串转整型，里面会有干扰字符
     * Implement atoi to convert a string to an integer.
     * <p>
     * Hint: Carefully consider all possible input cases.
     * If you want a challenge, please do not see below and ask yourself what are the possible input cases.
     * <p>
     * Notes: It is intended for this problem to be specified vaguely (ie, no given input specs).
     * You are responsible to gather all the input requirements up front.
     * <p>
     * <p>
     * <p>
     * Requirements for atoi:
     * <p>
     * The function first discards as many whitespace characters as necessary until the first non-whitespace character is found.
     * Then, starting from this character, takes an optional initial plus or minus sign followed by as many numerical digits as possible,
     * and interprets them as a numerical value.
     * <p>
     * The string can contain additional characters after those that form the integral number,
     * which are ignored and have no effect on the behavior of this function.
     * <p>
     * If the first sequence of non-whitespace characters in str is not a valid integral number,
     * or if no such sequence exists because either str is empty or it contains only whitespace characters,
     * no conversion is performed.
     * <p>
     * If no valid conversion could be performed, a zero value is returned. I
     * f the correct value is out of the range of representable values,
     * INT_MAX (2147483647) or INT_MIN (-2147483648) is returned.
     *
     * @param args
     */
    public static void main(String[] args) {

        int i = myAtoi("  -0012a42");
        System.out.println(i);
    }


    /**
     * 思路是取每一个字符，先移动到第一个非空格字符，然后判断是不是加减号，记录下正负
     * 然后开始加入字符数组，直到遇到非数字字符
     * ===========================
     * "n = 123"
     * int result  =0;
     *  result =result*10+n%10;
     *  n=n/10;
     *  每次将之前的结果乘以10，然后加个位，这就可以将数字反转
     * @param str
     * @return
     */
    public static int myAtoi(String str) {//"  -0012a42"
        if(str==null){
            return 0;
        }
        if("".equals(str)){
            return 0;
        }
        char[] chars = str.toCharArray();
        int length = chars.length;
        int index = 0;
        while (chars[index] == ' ') {
            index++;
        }

        boolean isNegative = false;
        if (chars[index] == '-') {
            isNegative = true;
            index++;
        } else if (chars[index] == '+') {
            index++;
        }
        if(index>=length){
            return 0;
        }
        StringBuilder builder = new StringBuilder();
        while (index < length && chars[index] <= '9' && chars[index] >= '0') {
            builder.append(chars[index++]);
        }

        if(builder.length()==0){
            return 0;
        }
        int i = 0;
        try {
            i = Integer.parseInt(builder.toString());
            if(isNegative){
                i = -i;
            }
        } catch (NumberFormatException e) {
            if(isNegative){
                i = Integer.MIN_VALUE;
            }else{
                i = Integer.MAX_VALUE;
            }
        }

        return i;
    }



    class Solution {
        public int myAtoi(String str) {
            if(str.length()==0) return 0;
            int index=0;
            int result=0;
            while(str.charAt(index)==' ')
            {
                index++;
            }
            int isNegative=1;
            if(str.charAt(index)=='-' || str.charAt(index)=='+')
            {
                isNegative=str.charAt(index)=='+'?1:-1;
                index++;
            }
            while(index<str.length()&&str.charAt(index)=='0')
            {
                index++;
            }
            while(index<str.length())
            {
                int digit=str.charAt(index)-'0';
                if(digit<0||digit>9)
                {
                    break;
                }
                if(Integer.MAX_VALUE/10<result || (Integer.MAX_VALUE/10==result && Integer.MAX_VALUE%10<digit))
                {
                    return isNegative==1?Integer.MAX_VALUE:Integer.MIN_VALUE;
                }
                result=result*10+digit;
                index++;
            }

            return result*isNegative;
        }
    }
}
