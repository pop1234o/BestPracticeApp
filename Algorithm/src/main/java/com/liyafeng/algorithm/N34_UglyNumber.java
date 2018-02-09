package com.liyafeng.algorithm;

/**
 * Created by liyafeng on 2018/2/9.
 */

public class N34_UglyNumber {

    /**
     * @param args 丑数：只包含因子 2/3/5 的数叫做丑数
     *             输出从小到大第1500个丑数 （1是第一个丑数）
     *             <p>
     *             ====================================
     *             我们的思路是，所有的丑数肯定是之前的丑数乘以2/3/5 得来的
     *             那么我们需要的是将丑数排序，然后再根据已经排好序的丑数中，最大的是M
     *             这时找到一个数字,他乘以2的积是第一个大于M的，我们把这个数叫做M2
     *             同理找出M3，M5 ，然后找出M2 M3 M5 中最小的一个，就是M的下一个丑数
     *             依次类推，找出1500个
     */
    public static void main(String[] args) {

        int n = getUgly(15);
        System.out.println("ugly:" + n);
    }

    private static int getUgly(int index) {
        int[] arrays = new int[index];
        arrays[0] = 1;
        int nextUgly = 1;
        int t_2_index = 0;//指定*2大于M的第一个数 的数
        int t_3_index = 0;
        int t_5_index = 0;
        while (nextUgly < index) {
            int min = min(arrays[t_2_index]*2, arrays[t_3_index]*3, arrays[t_5_index]*5);
            arrays[nextUgly] = min;
            while (arrays[t_2_index] < min) {
                t_2_index++;
            }
            while (arrays[t_3_index] < min) {
                t_3_index++;
            }
            while (arrays[t_5_index] < min) {
                t_5_index++;
            }
            nextUgly++;
        }

        return arrays[nextUgly - 1];
    }

    private static int min(int n1, int n2, int n3) {

        int i = n1 < n2 ? n1 : n2;
        return i < n3 ? i : n3;
    }
}
