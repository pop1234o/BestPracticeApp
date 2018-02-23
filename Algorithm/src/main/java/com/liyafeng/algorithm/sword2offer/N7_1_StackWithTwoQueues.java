package com.liyafeng.algorithm.sword2offer;

import java.util.LinkedList;
import java.util.Queue;

public class N7_1_StackWithTwoQueues {

    /**
     * 这个也是一个出队列，然后剩下最后一个就是要出栈的
     * <p>
     * 反复如此
     *
     * @param args
     */
    public static void main(String[] args) {

        Stack<String> stack = new Stack<>();

        stack.push("aa");
        stack.push("bb");
        stack.push("cc");

        String pop1 = stack.pop();
        System.out.println(pop1);

        stack.push("dd");
        while (!stack.isEmpty()) {
            String pop = stack.pop();
            System.out.println(pop);
        }


    }

    private static class Stack<T> {
        Queue<T> queue1 = new LinkedList<>();
        Queue<T> queue2 = new LinkedList<>();

        public void push(T t) {//哪个不空加入哪个
            if (queue1.size() > 0) {
                queue1.add(t);
            } else if (queue2.size() > 0) {
                queue2.add(t);
            } else {
                queue1.add(t);
            }
        }

        public T pop() {

            if (queue1.size() > 0) {
                return popInner(queue1, queue2);
            } else if (queue2.size() > 0) {
                return popInner(queue2, queue1);
            } else {
                return null;
            }

        }

        private T popInner(Queue<T> queue1, Queue<T> queue2) {
            int size = queue1.size();
            for (int i = 0; i < size - 1; i++) {
                T poll = queue1.poll();
                queue2.add(poll);
            }
            return queue1.poll();
        }

        public T peek() {
            if (queue1.size() > 0) {
                T t = popInner(queue1, queue2);
                push(t);
                return t;
            } else if (queue2.size() > 0) {
                T t = popInner(queue2, queue1);
                push(t);
                return t;
            } else {

                return null;
            }
        }

        public boolean isEmpty() {
            return queue1.isEmpty() && queue2.isEmpty();
        }
    }

}
