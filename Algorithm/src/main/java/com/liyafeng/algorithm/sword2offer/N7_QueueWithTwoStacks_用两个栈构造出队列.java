package com.liyafeng.algorithm.sword2offer;

import java.util.Stack;

public class N7_QueueWithTwoStacks_用两个栈构造出队列 {


    /**
     * 两个栈实现一个队列
     *
     * 思路就是
     *
     * 删除操作
     * 第二个stack不为空，就出栈。如果为0，就将stack1出栈到stack2，
     *
     * 添加操作
     * 加入只加入到stack1
     * @param args
     */
    public static void main(String[] args) {

        Queue<Integer> queue = new Queue<>();

        queue.add(1);
        queue.add(2);
        queue.add(3);

        Integer delete = queue.delete();
        System.out.println(delete);

        Integer delete1 = queue.delete();
        System.out.println(delete1);

        queue.add(1);

        Integer delete2 = queue.delete();
        System.out.println(delete2);

        Integer delete3 = queue.delete();
        System.out.println(delete3);
    }

    private static class Queue<T> {
        Stack<T> stack1 = new Stack<>();
        Stack<T> stack2 = new Stack<>();

        public void add(T t) {

            stack1.push(t);
        }

        public T delete() {

            if (stack2.isEmpty()) {
                while (!stack1.isEmpty()) {
                    stack2.push(stack1.pop());
                }
            }
            if (stack2.isEmpty()) {
                return null;
            }
            return stack2.pop();
        }


    }
}
