package com.liyafeng.algorithm.basic.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * Created by liyafeng on 2018/2/10.
 */

public class N_Sort_MergeSort_TimSort {


    /**
     * Tim排序
     * 一种改进的归并排序
     * ==============================
     * 采用分治法的一种算法
     *
     * @param args
     */
    public static void main(String[] args) {

//        int[] array = {1, 124, 54, 34, 34, 6};
        int[] array = {1, 124, 54, 34, 34, 6, 51, 124, 54, 34, 34, 6, 51, 124, 54, 34, 34, 6, 51, 124, 54, 34, 34, 6, 51, 124, 54, 34, 34, 6, 51, 124, 54, 34, 34, 6, 5};
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < array.length; i++) {
            list.add(array[i]);
        }
        Object[] a = list.toArray();
        Arrays.sort(a);
        Util.print(a);
    }



}
