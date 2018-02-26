package com.liyafeng.algorithm.sword2offer;

/**
 * Created by liyafeng on 2018/2/26.
 */

public class N42_1_LeftRotateString {

    /**
     * 左旋一个字符串，左旋的意思是将字符串开头的n个数组移到数组末尾
     * 比如左旋两位
     * Iamgood -> mgoodIa
     * ==============思路====================
     *
     * @param args
     */
    public static void main(String[] args) {

        String s = "abcdefg";
        s = leftRotate(s, 2);
        System.out.println(s);
    }

    private static String leftRotate(String s, int n) {
        StringBuilder builder = new StringBuilder(s);
        builder.delete(0, 2);
        builder.append(s.substring(0, 2));
        return builder.toString();
    }
}
