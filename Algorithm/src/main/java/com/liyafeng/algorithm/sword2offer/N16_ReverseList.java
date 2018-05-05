package com.liyafeng.algorithm.sword2offer;

public class N16_ReverseList {

    /**
     *
     * 反转链表
     *
     * 我们可以用循环，用缓存next的方式来反转
     *
     * 也可以用 递归 的方式来反转
     * 因为递归天生就有参数缓存
     *
     * 但是递归会有内存开销，不适合长的链表
     * 但是递归代码简洁
     *
     * @param args
     */
    public static void main(String[] args) {
        List list = new List();
        list.add(new List.Node(1));
        list.add(new List.Node(2));
        list.add(new List.Node(3));
        list.add(new List.Node(4));
        list.add(new List.Node(5));
        list.add(new List.Node(7));

        List.Node head = reverse2(list);

        while (head != null) {
            System.out.println(head.value);
            head = head.next;
        }
    }

    /**
     * 1 2 3 4 5 6
     * <p>
     * next是临时变量
     * <p>
     * 首先pre 为null
     * node指向头
     * <p>
     * 缓存node.next
     * <p>
     * 然后让当前node.next = pre
     * <p>
     * 然后指针 pre->node node->next 移动
     * <p>
     * 先缓存，后操作，然后读缓存
     *=============================
     * 1->2->3
     * currentNode = 1;//这个用来缓存当前的node
     * pre =null;//之个缓存上一个node，一遍我们的next指向他
     * while(node!=null){
     *   next = node.next; //缓存当前节点的next
     *   if(next==null){//这里是遍历完成
     *       head = node;
     *   }
     *   currentNode.next = pre;//当前节点的next重写指向
     *   pre = currentNode;//向后移动，当前节点变为pre
     *   node = next;//当前节点变为下一个
     * }
     *
     * @param list
     * @return
     */
    private static List.Node reverse(List list) {
        //缓存当前节点和前一个节点
        List.Node currentNode = list.head;
        List.Node pre = null;

        while (currentNode != null) {

            //临时缓存下一个节点，作用就是最后当前节点指向下一个
            List.Node next = currentNode.next;
            if (next == null) {
                list.head = currentNode;
            }

            //改变指向
            currentNode.next = pre;

            //pre, currentNode指针向后移动
            pre = currentNode;
            currentNode = next;

        }


        return list.head;
    }

    public static List.Node reverse2(List list) {
        List.Node node = reverseInner(list.head, list.head.next);
        list.head.next = null;
        list.head = node;
        return list.head;
    }

    private static List.Node reverseInner(List.Node head, List.Node next) {
        if (next != null) {
            List.Node node = reverseInner(next, next.next);
            next.next = head;
            return node;
        } else {
            return head;
        }

        //最后一个节点 ->前一个
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
