package com.liyafeng.algorithm.sword2offer;

import com.liyafeng.datastructure.LinkedList.Node;

import java.util.LinkedList;

/**
 * Created by liyafeng on 2018/2/23.
 */

public class N37_FindFirstCommonNode {

    /**
     * 找出两个单向链表的公共节点
     * ==========思路================
     * 两个链表如果有公共节点，那么他们一定是Y型的
     * 我们可以遍历得到两个链表的长度，然后在相同位置同时遍历两个链表
     * 直到两个节点相等为止
     *
     * @param args
     */
    public static void main(String[] args) {

        com.liyafeng.datastructure.LinkedList linkedList1 = new com.liyafeng.datastructure.LinkedList();
        com.liyafeng.datastructure.LinkedList linkedList2 = new com.liyafeng.datastructure.LinkedList();

        linkedList1.add(new Node(1));
        linkedList1.add(new Node(2));
        linkedList1.add(new Node(3));

        linkedList2.add(new Node(1));
        linkedList2.add(new Node(1));

        Node node = new Node(16);

        linkedList1.add(node);
        linkedList2.add(node);


        linkedList1.add(new Node(2));
        linkedList1.add(new Node(3));

        linkedList2.add(new Node(1));
        linkedList2.add(new Node(1));

        Node n = findCommonNode(linkedList1, linkedList2);
        if (n != null) {
            System.out.println("common node :" + n.key);
        } else {
            System.out.println("error");
        }
    }

    private static Node findCommonNode(com.liyafeng.datastructure.LinkedList linkedList1, com.liyafeng.datastructure.LinkedList linkedList2) {

        int size1 = linkedList1.size;
        int size2 = linkedList2.size;
        com.liyafeng.datastructure.LinkedList largeList;
        com.liyafeng.datastructure.LinkedList smallList;
        if (size1 > size2) {
            largeList = linkedList1;
            smallList = linkedList2;
        } else {
            smallList = linkedList1;
            largeList = linkedList2;
        }
        int i = 0;
        int n = Math.abs(size1 - size2);
        Node head = largeList.head;
        Node large = head;
        for (int j = 0; j < n; j++) {
            large = large.next;
        }
        Node small = smallList.head;
        for (int j = 0; j < smallList.size; j++) {
            if (large == small) {
                return small;
            } else {
                large = large.next;
                small = small.next;
            }
        }
        return null;
    }

}
