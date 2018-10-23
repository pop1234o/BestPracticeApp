package com.liyafeng.algorithm.sword2offer;

public class N3_SearchInArray_有序二维数组中查找k {
    /**
     * 二维数组查找问题
     * <p>
     * 思路：
     * 右上角开始，一行，一列的排除
     *
     * @param args
     */
    public static void main(String[] args) {
        int[][] ints = new int[10][10];
        for (int i = 0; i < ints.length; i++) {
            for (int j = 0; j < ints[i].length; j++) {
                ints[i][j] = i * 2 + j;
                System.out.print(ints[i][j] + "\t");
            }
            System.out.println();
        }


        searchInArray(ints, 18);


    }

    public static boolean searchInArray(int[][] ints, int num) {
        if (ints == null) {
            return false;
        }
        int y = ints.length;
        for (int i = 0; i < y; i++) {
            int x = ints[i].length;
            for (int j = x - 1; j >= 0; j--) {
                int n = ints[i][j];
                if (num == n) {//找到
                    System.out.println("找到：[" + i + "] [" + j + "]");
                    return true;
                } else if (num > n) {//直接找下一行
                    break;
                } else if (num < n) {
                    continue;
                }
            }
        }
        System.out.println("没找到");
        return false;
    }
}
