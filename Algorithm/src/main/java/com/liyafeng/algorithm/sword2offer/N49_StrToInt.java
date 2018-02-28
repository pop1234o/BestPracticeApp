package com.liyafeng.algorithm.sword2offer;

/**
 * Created by liyafeng on 2018/2/28.
 */

public class N49_StrToInt {

    /**
     * 字符串转化为整型
     * ===============思路==============
     * 取每位进行求和计算
     * 难点在于考虑到整型溢出，异常的字符串等，考察你编程的严谨性
     * @param args
     */
    public static void main(String[] args) {

        int n = strToInt("967");//48-57
        System.out.println("number:" + n);
    }

    private static int strToInt(String str) {

        int c = str.charAt(0);

        int length = str.length();
        int n = 0;
        for (int i = length - 1; i >= 0; i--) {
            int c1 = str.charAt(i) - 48;
            int pow = (int) Math.pow(10, length - 1 - i);
            n += (c1 * pow);
        }
        return n;

    }
}
