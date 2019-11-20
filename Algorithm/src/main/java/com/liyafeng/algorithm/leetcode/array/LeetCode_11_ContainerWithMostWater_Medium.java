package com.liyafeng.algorithm.leetcode.array;

import java.util.HashMap;

public class LeetCode_11_ContainerWithMostWater_Medium {

    /**
     * Container With Most Water
     * <p>
     * Given n non-negative integers a1, a2, ..., an  （n>2）
     * <p>
     * <p>
     * (i, ai) (i, 0)形成一条在坐标系中的竖线， 所以一共有n条竖线
     * i代表 1-n中的一个数
     * <p>
     * 往坐标系中灌水，求水的最大体积
     * <p>
     * =====解法=========
     * 1.暴力解法，依次遍历出各个情况
     * i 从0-n  ,j 从i+1-n 两个for嵌套
     * <p>
     * ---------------
     * 2.两个指针解法
     *
     * ==================
     * 知识点:数组+两个指针
     *
     * @param args
     */
    public static void main(String[] args) {
        int max = maxArea(new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7});
        System.out.println("最大值" + max);
    }


    public static int maxArea(int[] height) {
        int low = 0, high = height.length - 1;
        int max = 0;
        while (low < high) {
            //计算面积
            int sum = (high - low) * (height[low] < height[high] ? height[low] : height[high]);
            if (sum > max) {
                max = sum;
            }
            //左右指针向中间靠拢
            if (height[low] < height[high]) {
                low++;
            } else {
                high--;
            }
        }

        return max;
    }


    /**
     *
     */
    class Solution {

    }
}
