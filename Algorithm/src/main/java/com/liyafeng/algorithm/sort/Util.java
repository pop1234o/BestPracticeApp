package com.liyafeng.algorithm.sort;

/**
 * Created by liyafeng on 2018/2/12.
 */

public class Util {

    public static void exchange(int[] array, int i, int j) {
        int t = array[i];
        array[i] = array[j];
        array[j] = t;
    }

    public static void print(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + ",");
        }
    }

    /**
     * 交换
     *
     * @param array
     * @param i
     * @param j
     * @param <E>
     */
    public static <E> void exchange(E[] array, int i, int j) {
        E t = array[i];
        array[i] = array[j];
        array[j] = t;
    }

    /**
     * 小于
     *
     * @param keys
     * @param i
     * @param j
     * @param <E>
     * @return
     */
    public static <E extends Comparable<E>> boolean less(E[] keys, int i, int j) {
        return keys[i].compareTo(keys[j]) < 0;
    }

    public static boolean less(int[] keys, int i, int j) {
        return keys[i] < keys[j];
    }
}
