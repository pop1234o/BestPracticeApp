package com.liyafeng.algorithm;

public class N9_Fibonacci {

    /**
     * 解决青蛙跳台阶的问题
     * 解决方块填充问题
     *
     * 都是分治法思想的应用
     * 当前解决方案有多少种，然后将剩下的部分循环用这个方法解决
     * f8 = f(7)+f(6) 后面代表两种解决办法，括号中是剩下问题的规模
     *
     * f(n) =f(n-1)+f(n-2)
     * @param args
     */
    public static void main(String[] args) {

        int n = getFibN(15);
        System.out.println(n);
    }

    private static int getFibN(int n) {

        int fib[] = {0, 1};
        if (n <= 2) {
            return fib[n];
        }

        int f1 = fib[0];
        int f2 = fib[1];

        int fn = -1;
        for (int j = 2; j <n; j++) {
            fn = f1+f2;
            f1 = f2;
            f2 = fn;
        }

        return fn;
    }


}
