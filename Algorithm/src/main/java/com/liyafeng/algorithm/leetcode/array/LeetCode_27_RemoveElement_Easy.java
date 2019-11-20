package com.liyafeng.algorithm.leetcode.array;

public class LeetCode_27_RemoveElement_Easy {

    /**
     * Remove Element
     * 给定一组数组，移除数组中的指定元素到数组最后（不能创建额外的内存空间）
     * 返回不重复元素的长度
     * ========示例=======
     * [0,1,2,2,3,0,4,2], val = 2,
     * 前5个元素是 [0, 1, 3, 0, 4.....]  返回length = 5
     * <p>
     * <p>
     * =========思路==============
     *
     * <p>
     * =============
     * 知识点:数组+两个指针遍历
     *
     * @param args
     */
    public static void main(String[] args) {
        int[] nums = {0, 1, 2, 2, 3, 0, 4, 2};
        int length = removeElement(nums, 2);
        for (int i = 0; i < length; i++) {
            System.out.printf(nums[i] + " ");
        }
    }

    /**
     * 两个指针，lo指向第一个是val的元素，hi指向第一个不是val的元素，赋值，
     * 继续遍历，直到lo左侧都不是val，hi右侧都是val
     * =============
     * 时间 O(n)
     * 空间 O(1)
     *
     * @return
     */
    public static int removeElement(int[] nums, int val) {
        if (nums == null) {
            return 0;
        }
        if (nums.length == 0) {
            return 0;
        }

        int lo = 0, hi = nums.length - 1;
        //注意，这里是<= ，为什么可以等于，是因为要确保lo左侧的元素都不是val，而hi右侧的元素都是val
        while (lo <= hi) {
            while (lo <= hi && nums[lo] != val) {
                lo++;
            }

            while (lo <= hi && nums[hi] == val) {
                hi--;
            }
            if (lo <= hi) {
                nums[lo] = nums[hi];
                lo++;
                hi--;
            }
        }


        return lo;
    }

}
