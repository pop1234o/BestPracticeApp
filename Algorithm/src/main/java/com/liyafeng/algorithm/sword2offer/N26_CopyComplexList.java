package com.liyafeng.algorithm.sword2offer;

public class N26_CopyComplexList {

    /**
     * 复制复杂链表
     * 复杂链表就是在节点中还有一个指针 p_sibling，指向链表中的任意节点
     *
     * 复制
     * O(n^2)时间复杂度的复制是 先复制链表，再遍历，然后根据长度来 复制指向p_sibling
     *
     * 添加辅助空间
     * 弄一个HashMap<N ,N'>
     *
     * 整合成一条链接
     *
     * 这样 N'的 p_sibling 就是N的 p_sibling 的next
     *
     * ========================================
     *
     *
     * @param args
     */
    public static void main(String[] args) {

    }
}
