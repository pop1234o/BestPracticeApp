package com.liyafeng.algorithm.basic.sort;

import java.util.Arrays;

public class N_Sort_QuickSort_Quick3WaySort {

    /**
     * 三向切分的快速排序
     * http://www.importnew.com/8445.html
     * =====================
     * <p>
     * ========优点==============
     * 能处理含有大量重复元素的数组
     * <p>
     * ==============原理==================
     * <p>
     * <p>
     * <p>
     * =============比较========
     * 1000w个数据
     * 随机数据：
     * <p>
     * 基本排序：1222ms。
     * 三路（Three-way）快速排序：1295ms（我是认真的！）。
     * 双基准快速排序：1066ms。
     * 重复数据：
     * <p>
     * 基本排序：378ms。
     * 三路快速排序：15ms。
     * 双基准快速排序：6ms。
     *
     * @param args
     */
    public static void main(String[] args) {


    }

    public static void sort(int[] ints) {

        sort(ints, 0, ints.length);

    }

    public static void sort(int[] ints, int lo, int hi) {



    }


    /**
     * 3,1,4,6,5
     * 思路是三个指针，
     * 第一个指针是lt ,它代表小于基准,初始值0
     * 第二个指针是gt ,它代表大于基准,初始值 length-1
     * 第三个指针i ,遍历整个数组和基准值比较,初始值lo+1
     * （这里为什么要lo+1? 因为我们取第一个元素为基准值，i和lt比较永远相等，然后i++ ，
     *  就相当于i从 lo+1开始了）
     *
     * lt一直指向的事基准值的第一个元素
     * xx 333 xx
     *    lt
     * gt在循环最后一定指向的事基准值的最后一个元素
     *
     * 所以后面再进行递归
     *  sort(input, lowIndex, lt - 1);
     *  sort(input, gt + 1, highIndex);
     *
     *
     *
     */
    public class QuickSort3Way {

        public void sort(int[] input) {
            //input=shuffle(input);
            sort(input, 0, input.length - 1);
        }

        public void sort(int[] input, int lowIndex, int highIndex) {

            if (highIndex <= lowIndex){
                return;
            }

            //小于的指针
            int lt = lowIndex;
            //大于的指针
            int gt = highIndex;
            //移动的指针
            int i = lowIndex + 1;

//            int pivotIndex = lowIndex;
            //比较的元素
            int pivotValue = input[lowIndex];

            while (i <= gt) {

                //当前值小于基准值，当前值和小于的指针交换，两个指针向后走
                if (input[i] < pivotValue) {
                    Util.exchange(input, i++, lt++);
                } else if (pivotValue < input[i]) {
                    //当前值大于基准值，那么和大于的指针交换，大于指针向前，当前指针不动
                    //因为交换后，我们还需要判断当前值和基准值哪个大哪个小
                    Util.exchange(input, i, gt--);
                } else {
                    //如果等于基准值，那么不动
                    i++;
                }

            }

            // xxx 333 xxx  lt之前的一定都是小于基准值的，gt之后的一定都是大于基准值的
            sort(input, lowIndex, lt - 1);
            sort(input, gt + 1, highIndex);

        }

    }

}
