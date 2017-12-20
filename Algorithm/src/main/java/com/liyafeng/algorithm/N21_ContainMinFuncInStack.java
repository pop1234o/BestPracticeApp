package com.liyafeng.algorithm;

import java.util.Comparator;

public class N21_ContainMinFuncInStack {

    /**
     * 设计一个栈，用O(1)时间完成 push() ,pop() ,min()操作
     *
     * 最小值用一个辅助栈，记录每次入栈时当前的最小值
     *
     * 这里其实是用空间换时间的 典型例子
     *
     * 如何题中对时间有特殊要求，那么我们应该考虑 使用 辅助空间
     *
     * ============================
     * 编写测试用例，要考虑全面，比如这里新压入栈的值是最小值，不是最小值，只压入一个值，压入相同值， 空栈
     * 然后 弹出最小，弹出非最小
     *
     * @param args
     */
    public static void main(String[] args) {
        //1，5，3，5，4，8
        Stack<Integer> stack = new Stack<>();
        stack.push(3);
        stack.push(2);
        stack.push(10);
        stack.push(10);
        stack.push(5);
        stack.push(6);

        System.out.println(stack.pop());
//        System.out.println(stack.pop());
//        System.out.println(stack.pop());
//        System.out.println(stack.pop());

        System.out.println(stack.min.value);

    }

    static class Stack<T extends Comparable<T>> {
        Node top;
        Node min;

        //这里需要一个记录每次入栈的最小值的栈
        Node minStackTop;

        class Node {
            T value;
            Node next;

            public Node(T value) {
                this.value = value;
            }

        }

        /**
         * 这里注意要新创建对象，否则公用引用会出现bug
         *
         * @param t
         */
        public void push(T t) {
            Node node = new Node(t);
            if (top == null) {
                top = node;
                min = node;
                minStackTop = new Node(node.value);
                return;
            }
            node.next = top;
            top = node;

            if (node.value.compareTo(min.value) < 0) {
                min = node;
                pushInMinStack(new Node(node.value));
            } else {
                pushInMinStack(new Node(min.value));
            }
        }

        private void pushInMinStack(Node min) {
            min.next = minStackTop;
            minStackTop = min;
        }

        private void popMinStack() {
            Node min = this.minStackTop;
            this.minStackTop = this.minStackTop.next;
            min.next = null;
            //重新指向栈顶最小的
            this.min = minStackTop;
        }

        public T pop() {

            Node node = this.top;

            Node next = top.next;
            top.next = null;
            top = next;

            popMinStack();
            return node.value;
        }

        public T min() {
            return min.value;
        }
    }
}
