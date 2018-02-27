package com.liyafeng.algorithm.sword2offer;

/**
 * Created by liyafeng on 2018/2/26.
 */

public class N45_LastNumberInCircle {

    /**
     * 0,1,2...n 个数字排成圆环，从0开始删除圆圈中的第m个数字，求剩下的最后一个数字
     * ==============思路========================
     * 先抽象成数据结构，可以用数组，也可以用链表
     *
     * ==============考点==================
     * 考察建模能力（确定数据结构的能力）
     * 用链表实现需要额外空间
     * 用数组实现需要建立方程式，找规律
     * @param args
     */
    public static void main(String[] args) {

//        int[] array = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int[] array = {0, 1, 2, 3};
        int m = 3;
        int number = getLastNumber(array, m);
        System.out.println("number:" + array[number]);
    }

    private static int getLastNumber(int[] array, int m) {
        int length = array.length;
        int index = m;
        for (int i = 2; i <= length; i++) {
            index = (index + m) % i;
        }
        return index;
    }
}
