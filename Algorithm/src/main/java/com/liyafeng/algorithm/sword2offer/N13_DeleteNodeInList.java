package com.liyafeng.algorithm.sword2offer;

public class N13_DeleteNodeInList {

    /**
     * 在O(1)时间内删除单向链表的节点
     * <p>
     * 只需要将后一个节点的值复制到这个节点
     * <p>
     * 然后我们删除后一个节点即可
     * <p>
     * 如果是最后一个节点，那么就要遍历链表了
     *
     * @param args
     */
    public static void main(String[] args) {

        List list = new List();
        list.add(new List.Node(1));
        list.add(new List.Node(2));
        list.add(new List.Node(3));
        List.Node node = new List.Node(4);
        list.add(node);
        list.add(new List.Node(5));

        boolean delete = list.delete(node);
        System.out.println(delete);
    }

    public static class List {

        Node head;

        private static class Node {
            public Node(int value) {
                this.value = value;
            }

            int value;
            Node next;
        }

        public void add(Node node) {
            if (head == null) {
                head = node;
            } else {
                node.next = head;
                head = node;
            }
        }

        public boolean delete(Node node) {
            if (head == null) {
                return false;
            }
            if (node == head) {
                head = node.next;
                node.next = null;
                return true;
            }

            Node n = head;
            while (n.next !=null) {
                if (n.next == node) {
                    n.next = node.next;
                    node.next = null;
                    return true;
                } else {
                    n = n.next;
                }

            }

            return false;

        }
    }
}