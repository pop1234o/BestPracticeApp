package com.liyafeng.algorithm.sword2offer;

public class N24_PostTraversalBinarySearchTree {
    /**
     * 输入一个数组，判断它是否有可能是 一颗 二叉搜索树 的后序遍历的结果
     * <p>
     * 5 7 6 9 11 10 8
     *
     * =====================================
     * 其实还是举个具体的例子，画一个搜索树，左小，右大
     * 然后写出后序遍历
     * 发现最后一个数字是根节点
     *
     * 那么前面所有比它小的肯定是左子树
     * 接下来的肯定是右子树的，那么右子树中的节点肯定比root大，如果存在小的，那么就不是一个搜索树的后序遍历
     *
     * 接下来就是递归判断 左右子树 是不是后序了
     *
     * @param args
     */
    public static void main(String[] args) {

//        int[] ints = {5, 7, 6, 9, 11, 10, 8};
        int[] ints = {7, 4, 6, 5};
        boolean b = isSequeue(ints, 0, ints.length - 1);
        System.out.println(b);
    }

    private static boolean isSequeue(int[] ints, int start, int end) {
        int root = ints[end];

        int i = start;
        while (i < ints.length && ints[i] < root) {
            i++;
        }

        for (int j = i; j < end; j++) {
            if (ints[j] < root) {
                return false;
            }
        }

        boolean sequeue = true;

        if (i > start) {//判断左子树
            sequeue = isSequeue(ints, start, i - 1);
        }
        if (i < end) {//有右子树
            sequeue = isSequeue(ints, i, end - 1);
        }

        return sequeue;
    }
}
