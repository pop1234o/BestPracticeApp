package com.liyafeng.datastructure;

/**
 * Created by liyafeng on 2018/2/23.
 */

public class LinkedList {

    public Node head;
    public Node last;

    public int size = 0;

    public static class Node {
        public Node(int key) {
            this.key = key;
        }

        public int key;
        public Node next;
    }

    public void add(Node node) {
        size++;
        if (last == null) {
            last = head = node;
            return;
        }
        last.next = node;
        last = node;
    }
}
