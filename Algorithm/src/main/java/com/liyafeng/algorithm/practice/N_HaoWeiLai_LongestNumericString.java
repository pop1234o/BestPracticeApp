package com.liyafeng.algorithm.practice;

/**
 * Created by liyafeng on 2018/3/7.
 */

public class N_HaoWeiLai_LongestNumericString {

    /**
     * 来自：好未来2017秋招笔试
     * 题目：
     * 读入一个字符串 str，输出字符串 str 中的连续最长的数字串
     * <p>
     * <p>
     * 输入描述:
     * <p>
     * 测试输入包含 1 个测试用例，一个字符串 str，长度不超过 255。
     * 输出描述:
     * <p>
     * 在一行内输出 str 中里连续最长的数字串。
     * 输入例子:
     * <p>
     * abcd12345ed125ss123456789
     * 输出例子:
     * <p>
     * 123456789
     *
     * @param args
     */
    public static void main(String[] args) {
        String str = getLongest("abcd12345ed125ss123456789");
        System.out.println(str);
    }

    /**
     * 思路就是遍历字符串，然后比较长度
     * @param s
     * @return
     */
    private static String getLongest(String s) {
        char[] chars = s.toCharArray();
        int index = 0, length = chars.length;
        int max = 0;
        StringBuilder builder = new StringBuilder();
        String maxString = null;
        while (index < length) {
            if (chars[index] <= '9' && chars[index] >= '0') {
                builder.append(chars[index++]);
            } else {
                if (builder.length() > max) {
                    maxString = builder.toString();
                    max = builder.length();
                }
                builder.delete(0, builder.length());
                index++;
            }
        }
        if (builder.length() > max) {
            maxString = builder.toString();
        }
        return maxString;
    }
}
