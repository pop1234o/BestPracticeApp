package com.liyafeng.algorithm.basic.sort;

/**
 * Created by liyafeng on 2018/2/23.
 */

public class N_Sort_BubbleSort {


    /**
     * 冒泡排序
     * 时间复杂度：O(n^2)
     * =======原理===========
     * 每次把最大的元素冒泡到数组的最右端
     *
     * @param args
     */
    public static void main(String[] args) {
        int[] array = {1, 124, 54, 34, 34, 6, 5};
        sort(array);
        Util.print(array);
    }

    private static void sort(int[] array) {
        int length = array.length;
        for (int i = 0; i < length - 1; i++) {//控制次数
            for (int j = 0; j < length - i - 1; j++) {
                if (Util.less(array, j + 1, j)) {
                    Util.exchange(array, j, j + 1);
                }
            }
        }


    }
    
    
    
}
