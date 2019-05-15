package com.liyafeng.algorithm.leetcode;

public class Main_Leetcode {


    /**
     * ============常用逻辑=======
     * <p>
     * *****************指针***************
     * 1.数组，两个指针，头，尾，定义 lo/hi或者l/r   low/high left/right
     * 头指针找到第一个比他大的的，尾指针找到第一个比他小的
     * -------指针形式---
     * 1.两个指针头尾向中间
     * 2.两个指针顺序遍历 i,j  j=[i+1,n]
     * <p>
     * 注意指针是 > 还是>=
     * **************找最值*****************
     * 2.找最值，定义一个变量，用if比较，如果小/大就替换
     * <p>
     * **************举例分析法*************
     * 举一个举例的例子，我们来分析它的特性，可以先知道结果
     * 然后查看输入 输出之间有什么关联
     *
     * @param args
     */
    public static void main(String[] args) {

    }


    /**
     * =========名称解释===========
     * -------in-place-----------
     * The replacement must be  in-place, do not allocate extra memory.
     * in-place是指原地，就是不重新申请内存空间，在原有的内存空间中进行操作
     * <p>
     * --------Permutation-----------
     * Permutation 全排列  lexicographically 字典序的全排列
     * 全排列：一个数组所有可能的排列
     * 字典序的全排列 ，从0最小 9最大，
     * 也就是排列从小到大 ，比如1，2，3的全排列
     * 1,2,3 最小
     * 1,3,2
     * 2,1,3
     * 2,3,1
     * 3,1,2
     * 3,2,1 最大
     */
    void a1() {
    }


    /**
     * 找出数组右侧起第一个顶峰元素（一个元素大于它两边的元素）,如果是升序，就返回最后一个元素
     * 1234351
     * 第一个顶峰元素就是5，第二个是4
     */
    void a2(int[] nums) {
        //这里默认是第0个元素，因为下面遍历遍历不到第0个元素
        int index = 0;
        for (int i = nums.length - 1; i > 0; i--) {
            if (nums[i] > nums[i - 1]) {
                index = i;
                break;
            }
        }

        System.out.println(index);

        //===========while实现==========
        int j = nums.length - 2;
        while (j >= 0 && nums[j] >= nums[j + 1]) {
            j--;
        }
        //这个找到的事顶峰元素的前一个元素
        System.out.println(j+1);


    }
}
