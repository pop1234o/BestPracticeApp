package com.liyafeng.algorithm.practice;

import java.util.Collections;
import java.util.LinkedList;

/**
 * Created by liyafeng on 2018/3/8.
 */

public class N_Unknow_SortLinkedList {

    /**
     * 算法题：链表排序
     * <p>
     * 请写一个函数实现在 O(n log n) 时间复杂度和常数级的空间复杂度下给链表排序。
     * <p>
     * 格式：
     * <p>
     * 输入第一行输入一个链表，最后输入按升序排列后的链表。
     * <p>
     * 样例输入
     * <p>
     * 1 -> 3 -> 2 -> null
     * <p>
     * 样例输出
     * <p>
     * 1 -> 2 -> 3 -> null
     *
     * @param args
     */
    public static void main(String[] args) {

        //jdk的思路是先toArray()，然后再常规排序
        LinkedList<Integer> objects = new LinkedList<>();
        Collections.sort(objects);

    }
}
