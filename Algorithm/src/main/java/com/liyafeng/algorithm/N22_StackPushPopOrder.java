package com.liyafeng.algorithm;

import java.util.Stack;

public class N22_StackPushPopOrder {

    /**
     * 给出入栈序列，判断一个序列是否是 弹出序列集合 中的一种
     * (数字都不相同)
     *
     * push 1 2 3 4 5
     * pop  4 5 3 2 1
     *
     * 设置一个辅助栈
     * 取pop的第一个，和辅助栈的top比较
     * 如果不同从push入栈，直到和pop的相等，如果栈以空，那么不匹配
     *
     * 如果匹配，这个时候取pop的第二个数字，和stack的top比较
     * 如果相等，接着比较
     * 如果不等，接着从push中入栈，直到相等
     *
     * ======================
     * @param args
     */
    public static void main(String[] args) {
        //1 2 3 4 5
        int[] push = {1, 2, 3, 4, 5};
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
//        int[] pop = {6, 4, 3, 2, 1};
        int[] pop = { 5};

        boolean b = isCanPop(stack, pop);

        System.out.println(b);
    }

    private static boolean isCanPop(Stack<Integer> push, int[] pop) {
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < pop.length; i++) {
            int p = pop[i];
            Integer peek = null;
            if (!stack.empty()) {
                peek = stack.peek();
            }
            if (peek==null||peek != p) {//如果栈顶不是当前 要出栈的数字 ，那么入栈，直到
                while (!push.empty()&&push.peek() != p) {
                    stack.push(push.pop());
                }
                if (!push.empty()) {//找到这个
                    continue;
                } else { //已经空了
                    return false;
                }
            } else {
                stack.pop();
            }
        }

        return true;
    }


}
