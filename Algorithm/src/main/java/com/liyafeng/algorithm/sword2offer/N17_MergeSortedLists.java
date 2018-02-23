package com.liyafeng.algorithm.sword2offer;

public class N17_MergeSortedLists {
    /**
     * 用循环很复杂，逻辑比较多
     * 需要你举个实际的例子，一步步走，指针移动什么的都要考虑
     *
     * ========================
     *
     * 递归比较简单
     * 递归就是先解决当下问题，然后将剩下的问题还传入这个方法，如此循环
     *
     * 最后找到终止的点，返回即可
     *
     * @param args
     */
    public static void main(String[] args) {
        List list1 = new List();
        list1.add(new List.Node(101));
        list1.add(new List.Node(5));
        list1.add(new List.Node(5));
        list1.add(new List.Node(5));
        list1.add(new List.Node(3));
        list1.add(new List.Node(1));

        List list2 = new List();
        list2.add(new List.Node(12));
        list2.add(new List.Node(11));
        list2.add(new List.Node(10));
        list2.add(new List.Node(4));
        list2.add(new List.Node(2));

        List.Node head = merge2(list1, list2);

        List.Node next = head;
        while (next != null) {
            System.out.println(next.value);
            next = next.next;
        }
    }


    private static List.Node merge2(List list1, List list2) {


        List.Node head =  mergeInner(list1.head,list2.head);

        return head;
    }

    /**
     *
     * 1 3
     * 2 4
     * @param head1
     * @param head2
     * @return
     */
    private static List.Node mergeInner(List.Node head1, List.Node head2) {

        if(head1==null){
            return head2;
        }else if(head2==null){
            return head1;
        }
        List.Node winner ;
        if (head1.value < head2.value) {
            winner = head1;
            winner.next = mergeInner(head1.next,head2);
        } else {
            winner =head2 ;
            winner.next =  mergeInner(head1,head2.next);
        }
        return winner;
    }


    /**
     * 1 3 5
     * 2 4 6
     * merge->1
     *
     * @param list1
     * @param list2
     * @return
     */
    private static List.Node merge(List list1, List list2) {
        if (list1 == null || list2 == null) {
            return list2 == null ? (list1 == null ? null : list1.head) : list2.head;
        }
        List.Node mergedHead = null;

        List.Node head1 = list1.head;
        List.Node head2 = list2.head;

        if (list1.head.value < list2.head.value) {
            mergedHead = list1.head;
            head1 = head1.next;
        } else {
            mergedHead = list2.head;
            head2 = head2.next;

        }
        List.Node currentNode = mergedHead;
        while (head1 != null && head2 != null) {

            if (head1.value < head2.value) {
                currentNode.next = head1;
                head1 = head1.next;
            } else {
                currentNode.next = head2;
                head2 = head2.next;
            }

            currentNode = currentNode.next;
        }
        if (head1 != null) {
            currentNode.next = head1;
        } else if (head2 != null) {
            currentNode.next = head2;
        }
        return mergedHead;

    }


    public static class List {

        Node head;

        static class Node {
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
    }

}
