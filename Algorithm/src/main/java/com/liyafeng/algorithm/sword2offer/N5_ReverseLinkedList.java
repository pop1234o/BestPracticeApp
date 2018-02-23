package com.liyafeng.algorithm.sword2offer;

public class N5_ReverseLinkedList {


    /**
     * 队列和栈、背包是抽象的数据结构，他们规定规则
     * <p>
     * 链表和数组则是  数据结构的实现方式
     * <p>
     * 实现链表的插入和删除要说明规则，符合特定规则的才叫队列和栈
     *
     * 反向打印链表，可以用递归
     *
     * 也可以显示用栈的方式打印，递归本身就是一个栈
     *
     * @param args
     */
    public static void main(String[] args) {


        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
        list.print();

        reverse(list);
    }


    public static void reverse(LinkedList list) {
        print(list.head);
    }

    public static void print(LinkedList.Node node) {
        if (node.next != null) {
            print(node.next);
        }
        System.out.println(node.t);
    }

    /**
     * 插入和删除都在head
     *
     * @param <T>
     */
    private static class LinkedList<T> {
        Node head;

        class Node {
            public Node(T t) {
                this.t = t;
            }

            T t;
            Node next;
        }

        public void add(T t) {

            Node node = new Node(t);
            node.next = head;
            head = node;

        }

        public void print() {
            if (head != null) {
                Node node = head;
                do {
                    System.out.println(node.t + "");
                } while ((node = node.next) != null);
            }
        }
    }
}
