package com.liyafeng.algorithm.leetcode.array;

import com.liyafeng.algorithm.Util;

public class LeetCode_31_NextPermutation_Medium {

    /**
     * Next Permutation
     * 实现Implement next permutation ，根据字典排序的（lexicographically）
     * 如果输入的是最大的一个排列，那么返回最小的一个排列
     * <p>
     * ========示例=======
     * <p>
     * 1,2,3 → 1,3,2 输出下一个大于这个排列的排列 （大于是按照字典序的）
     * 3,2,1 → 1,2,3  输入最大，输出最小
     * 1,1,5 → 1,5,1
     * =========思路==============
     * <p>
     * 如对于全排列1,2,3,5,4来说，因为1,2,3,5,4并不是1开头的所有全排列中字典序最大的一个，所以它的下一个全排列的第一个数字仍然是1，我们只需要考虑2,3,5,4的下一个全排列是多少即可（然后在这个全排列的基础上最前面写下1即可得到原来全排列的下一个全排列）。
     * 同理，我们可以知道2,3,5,4的下一个全排列的第1个数字一定是2，所以我们只需要求解3,5,4的下一个全排列。
     * 但是对于3,5,4来说，它已经是所有3开头的全排列中字典序最大的一个了（3,5,4>3,4,5），所以需要发生“进位”的操作，即令3变为字典中的下一个元素4，然后将剩下的数字变为最小的排列——3,5，也就是说3,5,4的下一个全排列是4,3,5。
     * <p>
     * 先找到已经是 子 最大全排列的那个数，比如3，然后在子数组中，找到比3大的下一个数，两者替换，然后后面进行升序排序即可
     * <p>
     * =============
     * 知识点:数组+全排列特性+举例分析法+指针
     *
     * @param args
     */
    public static void main(String[] args) {
        int[] nums = {9, 5, 4, 3, 1};
        nextPermutation(nums);
        for (int num : nums) {
            System.out.print(num + " ");
        }
    }


    /**
     * 找到顶峰元素前面那个元素i,找到比i大的第一个元素，两者交换
     * 然后i后的元素reverse 将i后的排列变成升序，至此，找出下一个全排列
     *
     * @param nums
     */
    public static void nextPermutation(int[] nums) {

        int i = nums.length - 2;
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }
        //因为i有可能是-1，这种情况就是数组是降序的
        if (i >= 0) {
            int j = nums.length - 1;
            while (j >= 0 && nums[j] <= nums[i]) {
                j--;
            }
            //交换i和j
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }

        //反转 i+1
        int l = i + 1, r = nums.length - 1;
        while (l < r) {
            int temp = nums[l];
            nums[l] = nums[r];
            nums[r] = temp;
            l++;
            r--;
        }

    }

}
