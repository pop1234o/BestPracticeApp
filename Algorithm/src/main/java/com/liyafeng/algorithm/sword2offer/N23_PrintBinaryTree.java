package com.liyafeng.algorithm.sword2offer;

import java.util.LinkedList;
import java.util.Queue;

public class N23_PrintBinaryTree {

    /**
     * 从上到下，从左到右打印二叉树
     *
     *
     * 举个例子
     *     0
     *   1    2
     * 3  4  5  6
     * 就应该打印出  0 1 2 3 4 5 6
     * 思路就是将根节点加入队列
     *
     * 然后循环队列
     * 取出队列中的第一个节点 ，将它的左右节点加入队列
     * 打印这个节点
     * 如此循环
     * ======================================
     * 用stack 和 queue 的特性能解决很多问题
     *
     * ===========================
     * 相当于广度优先遍历 二叉树
     * @param args
     */
    public static void main(String[] args) {

        Tree tree = new Tree();
        tree.root = new Tree.Node(0);
        tree.root.left = new Tree.Node(1);
        tree.root.right = new Tree.Node(2);

        tree.root.left.left = new Tree.Node(3);
        tree.root.left.right = new Tree.Node(4);

        tree.root.right.left = new Tree.Node(5);
        tree.root.right.right = new Tree.Node(6);

        print(tree);
    }

    private static void print(Tree tree) {
        Queue<Tree.Node> queue = new LinkedList<>();
        Tree.Node root = tree.root;

        queue.add(root);
        while (!queue.isEmpty()) {
            Tree.Node poll = queue.poll();
            if (poll.left != null) {
                queue.add(poll.left);
            }
            if (poll.right != null) {
                queue.add(poll.right);
            }

            System.out.println(poll.value);

        }

    }


    static class Tree {
        Node root;

        static class Node {

            public Node(int value) {
                this.value = value;
            }

            int value;
            Node left;
            Node right;
        }
    }
}
