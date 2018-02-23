package com.liyafeng.algorithm.sword2offer;

public class N25_PathOfBinaryTree {

    /**
     * 给定一个二叉树，和一个整数，找出和为这个整数的 所有路径
     * 这个路径是根节点到子节点
     *
     * 思路就是递归
     *
     * 减去根节点的值，看子节点能不能满足，直到叶子节点
     *
     * 这是一种分治思想
     *
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

        findPath(tree, 5);
    }

    private static void findPath(Tree tree, int sum) {
        boolean inner = findInner(tree.root, sum);


    }

    private static boolean findInner(Tree.Node root, int sum) {

        if (root == null) {//没有节点了
            return false;
        }

        int value = root.value;
        int subValue = sum - value;

        if (root.left == null && root.right == null) {//叶子节点,如果和假定值一样，就找到
            boolean b = subValue == 0;
            if(b){
                System.out.println(value);
            }
            return b;
        }

        if (subValue <= 0) {//如果当前节点和为sum，但是又不是叶子节点，那么直接返回false 。或者再往下的节点和都大于sum了，也直接返回false
            return false;
        }
        boolean left = findInner(root.left, subValue);
        if (left) {
            System.out.println(value);
        }

        boolean right = findInner(root.right, subValue);

        if (right) {
            System.out.println(value);
        }
        if (left || right) {//如果左右子树中有一个找到了，那么就算找到了
            return true;
        } else {
            return false;
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
