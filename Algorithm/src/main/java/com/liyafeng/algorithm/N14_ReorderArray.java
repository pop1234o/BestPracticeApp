package com.liyafeng.algorithm;

public class N14_ReorderArray {

    /**
     * 数组排序
     * <p>
     * 所有奇数都在偶数前面
     * <p>
     * <p>
     * =====================
     * 考察程序拓展性，把判断条件抽象出来（其实就是写一个函数在父类中，子类按需实现即可）
     * 当然也可以传入参数，根据参数来配置
     *
     * @param args
     */
    public static void main(String[] args) {

//        int[] array = {1, 2, 3, 5, 8, 6, 4, 1};
        int[] array = {1, 3, 5, 7, 8, 6, 4};

        reOrder(array);

        for (int n : array) {
            System.out.println(n);
        }

    }

    private static void reOrder(int[] array) {

        int start = 0;
        int end = array.length - 1;

        while (start < end) {
            if (array[start] % 2 == 1) {//找到第一个偶数
                start++;
                continue;
            }
            if (array[end] % 2 == 0) {//找到第一个奇数
                end--;
                continue;
            }
            //交换
            int i = array[start];
            array[start] = array[end];
            array[end] = i;

        }

    }


}
