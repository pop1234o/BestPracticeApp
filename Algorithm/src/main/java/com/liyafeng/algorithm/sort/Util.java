package com.liyafeng.algorithm.sort;

/**
 * Created by liyafeng on 2018/2/12.
 */

public class Util {
    
    public static void exchange(int[] array,int i,int j){
        int t = array[i];
        array[i] = array[j];
        array[j] = t;
    }

    public static void print(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]+",");
        }
    }

    public static <E> void exchange(E[] array, int i, int j) {
        E t = array[i];
        array[i] = array[j];
        array[j] = t;
    }
}
