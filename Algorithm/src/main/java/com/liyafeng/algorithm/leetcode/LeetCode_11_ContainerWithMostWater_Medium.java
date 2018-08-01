package com.liyafeng.algorithm.leetcode;

/**
 * Created by liyafeng on 2018/3/16.
 */

public class LeetCode_11_ContainerWithMostWater_Medium {

    /**
     * https://leetcode-cn.com/problems/container-with-most-water/description/
     * <p>
     * 给定一组非负整数，每个数代表坐标点（i, ai），找到两个直线，盛的水最多
     * Given n non-negative integers a1, a2, ..., an,
     * where each represents a point at coordinate (i, ai).
     * n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0).
     * Find two lines, which together with x-axis forms a container,
     * such that the container contains the most water.
     * <p>
     * Note: You may not slant the container and n is at least 2.
     * <p>
     * 给定 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。
     * 在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。
     * 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
     * <p>
     * 说明：你不能倾斜容器，且 n 的值至少为 2
     * index 1 2 3 4
     * value 3,4,5,2
     * \
     * \ \
     * \ \ \
     * \ \ \ \
     * \ \ \ \
     * 1 2 3 4
     *
     * @param args
     */
    public static void main(String[] args) {

        int[] num = {1, 8, 6, 2, 5, 4, 8, 3, 7};//49
        int max = getMax(num);
        System.out.print("最大：" + max);
    }

    /**
     * 两个指针，指向头，尾，然后两个指针小的向中间移动（因为指针大的移动肯定要比当前容量小）
     * 而且移动的指针一定要移动到比上一个指针要高才行
     *
     * @param num
     * @return
     */
    private static int getMax(int[] num) {
        int start = 0, end = num.length - 1;
        int capacity = 0;
        while (start < end) {
            int n = getCapacity(start, end, num);
            if (n > capacity) {
                capacity = n;
            }

            //移动
            if (num[start] > num[end]) {
                end--;
            } else {
                start++;
            }

        }
        return capacity;
    }

    /**
     * 获取容量
     *
     * @param start
     * @param end
     * @param num
     * @return
     */
    private static int getCapacity(int start, int end, int[] num) {
        int n = num[start] > num[end] ? num[end] : num[start];
        return n * (end - start);
    }




    public int maxArea(int[] height) {
        //思路：Firstly, we place two boundarys at the end and the beginning. Then move the lower one, because if we move the higher       //one, it must become smaller.
        int max = 0;
        int left = 0;
        int right = height.length-1;
        while(right > left){
            max = Math.max(max,(Math.min(height[right],height[left])*(right-left)));
            if(height[left]>height[right]){
                right--;
            }else{
                left++;
            }
        }
        return max;
    }
}
