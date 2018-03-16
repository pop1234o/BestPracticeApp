package com.liyafeng.algorithm.practice;


import com.liyafeng.datastructure.LinkedList;

/**
 * Created by liyafeng on 2018/3/16.
 */

public class N_Unknow_HasCircleInLinkList {

    /**
     * 判断链表中是否有环
     * https://juejin.im/entry/58340acb61ff4b006c7314c2
     * ========================
     * 思路1：遍历链表，将元素加入HashSet，新遍历一个元素要判断HashSet有没有
     * 如果有，则是存在环，如果遍历到null，那么链表中没有环
     * 时间复杂度O(n) 空间复杂度O(n)
     * 思路2：
     * 两个指针指向head节点，一个每次走一步，一个每次走两步，如果遇到
     * 两个指针相等的情况，那么链表中有环。如果快的那个遇到null，
     * 那么代表没有环
     *
     * @param args
     */
    public static void main(String[] args) {
        LinkedList linkedList = new LinkedList();
        LinkedList.Node node = new LinkedList.Node(1);
        linkedList.add(node);
        linkedList.add(new LinkedList.Node(3));
        linkedList.add(new LinkedList.Node(4));
//        linkedList.add(node);

        boolean hasCircle = hasCircle(linkedList);
        System.out.println(hasCircle);
    }

    private static boolean  hasCircle(LinkedList linkedList) {
        LinkedList.Node head = linkedList.head;
        LinkedList.Node index1 = head,index2 = head;
        while ((index1 = index1.next) != null && index2.next != null && (index2 = index2.next.next) != null) {
            if (index1 == index2) {
                return true;
            }
        }

        return false;

    }


}
