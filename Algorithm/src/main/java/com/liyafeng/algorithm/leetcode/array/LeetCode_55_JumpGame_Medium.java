package com.liyafeng.algorithm.leetcode.array;

import java.util.LinkedList;
import java.util.List;

public class LeetCode_55_JumpGame_Medium {

    /**
     * Given an array of non-negative integers, you are initially positioned at the first index of the array.
     * Each element in the array represents your maximum jump length at that position.
     * Determine if you are able to reach the last index.
     * <p>
     * 一个非负数组，初始的时候你在 第一个元素
     * 每个元素的值代表你能向后跳多少步
     * 判断你能否跳到最后一个元素
     * <p>
     * ========示例=======
     * Input: [2,3,1,1,4]
     * Output: true
     * Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.
     * <p>
     * Input: [3,2,1,0,4]
     * Output: false
     * Explanation: You will always arrive at index 3 no matter what. Its maximum
     * jump length is 0, which makes it impossible to reach the last index.
     * <p>
     * <p>
     * =========思路==============
     * 动态规划
     * ========知识点=====
     *
     * @param args
     */
    public static void main(String[] args) {


    }


    /**
     * 递归方法
     * 如果判断第0个元素是否能到达 最后
     * 那就要看第0个元素能到达的那些元素 是否能到达最后
     * <p>
     * 递归的终止条件就是如果是最后一个元素，那么返回true，否则判断是否还能递归，否则返回false
     * <p>
     * 有点像深度优先的遍历
     */
    static class Solution1 {
        public static boolean canJump(int[] nums) {

            return canJumpLast(0, nums);
        }


        public static boolean canJumpLast(int index, int[] nums) {
            //已经到了最后一个
            if (index == nums.length - 1) {
                return true;
            }
            //遍历当前index能到达的index，判断他们canJumpLast

            //最远不能超过最后一个  当前index，和能跳的最远长度相加
            int furthestJump = Math.min(index + nums[index], nums.length - 1);
            for (int i = index + 1; i <= furthestJump; i++) {
                if (canJumpLast(i, nums)) {
                    return true;
                }
            }

            //不是前两种情况，此路不通
            return false;


        }

    }


    /**
     * 通过备忘录来减少重复的计算
     */
    static class Solution2 {
        enum Index {
            /**
             * GOOD代表能到达 BAD反之
             * UNKNOW代表位置
             */
            GOOD, BAD, UNKNOW
        }

        public static Index[] memory;

        public static boolean canJump(int[] nums) {
            //初始化备忘录状态
            memory = new Index[nums.length];

            for (int i = 0; i < nums.length; i++) {
                memory[i] = Index.UNKNOW;
            }
            //最后一个
            memory[nums.length - 1] = Index.GOOD;

            return canJumpLast(0, nums);
        }


        public static boolean canJumpLast(int index, int[] nums) {
            //已经到了最后一个
            if (memory[index] != Index.UNKNOW) {
                return memory[index] == Index.GOOD;
            }

            //遍历当前index能到达的index，判断他们canJumpLast

            //最远不能超过最后一个  当前index，和能跳的最远长度相加
            int furthestJump = Math.min(index + nums[index], nums.length - 1);
            for (int i = index + 1; i <= furthestJump; i++) {
                if (canJumpLast(i, nums)) {
                    memory[index] = Index.GOOD;
                    return true;
                }
            }
            memory[index] = Index.BAD;
            //不是前两种情况，此路不通
            return false;


        }
    }


    /**
     * 消除递归，自底向上的动态规划
     * 最后一个开始，他的前一个能到达他吗
     * 能到达他的前一个，的前一个也能到达吗
     * <p>
     * 实际上这里每个元素都做了判断，判断能不能到达最后一个
     */
    static class Solution3 {
        enum Index {
            /**
             * GOOD代表能到达 BAD反之
             * UNKNOW代表位置
             */
            GOOD, BAD, UNKNOW
        }

        public static Index[] memory;


        public boolean canJump(int[] nums) {
            //初始化备忘录状态
            memory = new Index[nums.length];

            for (int i = 0; i < nums.length; i++) {
                memory[i] = Index.UNKNOW;
            }
            //最后一个
            memory[nums.length - 1] = Index.GOOD;


            //这里从右向左,从最后一个的前一个开始

            for (int i = nums.length - 2; i > 0; i--) {

                //找到这个元素能到达的最远距离
                int furthestJump = Math.min(i + nums[i], nums.length - 1);
                //遍历最远距离中的元素，看有没有能到达的
                for (int j = i + 1; j < furthestJump; j++) {
                    //如果他能到达的元素中有good，那么他也是good的
                    if (memory[j] == Solution3.Index.GOOD) {
                        memory[i] = Index.GOOD;
                        break;
                    }

                }

            }

            //直接看0是不是good
            return memory[0] == Index.GOOD;

        }
    }


    /**
     * 使用贪心算法进行优化
     * 还是从后向前遍历每个元素
     * 如果他能到达上一个能到达的元素的位置
     * 那么他就能到达
     * <p>
     * 前面几种方法 O(n^2) 这个方法O(n)
     */
    static class Solution4 {
        public boolean canJump(int[] nums) {
            int lastCanJumpPostion = nums.length - 1;
            for (int i = nums.length - 2; i >= 0; i--) {
                //当前index是否能到达上一个 （能到达最后一个元素）的元素
                if (i + nums[i] >= lastCanJumpPostion) {
                    lastCanJumpPostion = i;
                }
            }

            return lastCanJumpPostion == 0;

        }
    }


}
