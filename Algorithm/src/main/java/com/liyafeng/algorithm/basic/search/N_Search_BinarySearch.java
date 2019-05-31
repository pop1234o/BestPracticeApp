package com.liyafeng.algorithm.basic.search;

import java.util.Arrays;

/**
 * Created by liyafeng on 2018/2/23.
 */

public class N_Search_BinarySearch {

    /**
     * 二分法查找
     * =========原理===============
     * 在排序好的数组中找中间的，小的在左，大的在右
     *
     * @param args
     */
    public static void main(String[] args) {

//        int[] array = {1, 3, 4, 6, 62, 345, 346};
//        int n = 5;
//        int i1 = Arrays.binarySearch(array, n);
//        int i2 = binarySearch(array, n);
//        System.out.println("search:"+i1+" =="+~i2);
        Object o = new Object();
        synchronized (o){
            try {
               o.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    /**
     * 示例
     * 1,2,3,4
     * 0 1 2 3
     *
     * 1,2,3
     *
     *
     * 1,2
     * 0 1
     *
     * 1
     *
     * 除以2指针是在左侧的那个（如果是偶数个元素）
     *
     * @param array
     * @param n
     * @return
     */
    private static int binarySearch(int[] array, int n) {
        //两个指针指向开始和结尾
        int start = 0;
        int end = array.length - 1;

        //最终start指针会指向插入的那个位置
        //如果是等于那么就这一个元素的可能性了
        while (start <= end) {//求出中位和n比较，小于end指针指向mid前一个，大于指向mid后一个
            int mid = (end + start) >>> 1;//无符号右移1位，相当于除2
            if (n < array[mid]) {
                end = mid - 1;
            } else if (n > array[mid]) {
                start = mid + 1;
            } else {
                return mid;
            }
        }
        return ~start;//相当于 -(start+1)
    }

}
