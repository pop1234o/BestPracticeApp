package com.liyafeng.algorithm.sword2offer;

import java.util.TreeMap;

public class N18_SubstractureInTree {

    /**
     * 一个树
     * <p>
     * 求一个树B是否是树A的子树
     * <p>
     * 用的还是递归
     * ====================
     * 遍历树，判断当前节点是否和treeB的根节点相等
     * 如果相等就 比较他们
     *
     * ============================
     * 其实还是一个策略问题
     * 查找是不是子树 我们首先要想到比较每个节点
     * 如果不匹配就要接着遍历树
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


        Tree treeB = new Tree();
        treeB.root = new Tree.Node(2);
        treeB.root.left = new Tree.Node(5);
        treeB.root.right = new Tree.Node(6);


        boolean substracture = isSubstracture(tree, treeB);
        System.out.println(substracture);
    }

    public static boolean isSubstracture(Tree treeA, Tree treeB) {

        return isSub(treeA.root, treeB.root);

    }

    private static boolean isSub(Tree.Node root1, Tree.Node root2) {
        boolean result = false;
        //到叶子节点
        if (root1 == null || root2 == null) {
            return false;
        }
        if (root1.value == root2.value) {//比较是否相等
            result = findSub(root1, root2);
        }
        //不相等就继续遍历
        if (!result) {
            result = isSub(root1.left, root2);
        }
        if (!result) {
            result = isSub(root1.right, root2);
        }

        return result;
    }

    /**
     * 如果相等的话，那么它的左边和右边都应该相等
     * <p>
     * 直到treeB的left是null了
     * 如果treeB不为null,但是treeA结束了，或者值不匹配，那么返回false
     *
     * @param root1
     * @param root2
     * @return
     */
    private static boolean findSub(Tree.Node root1, Tree.Node root2) {

        if (root2 == null) {
            return true;
        }
        if (root1 == null) {
            return false;
        }
        if (root1.value == root2.value) {
            return findSub(root1.left, root2.left) && findSub(root1.right, root2.right);
        }
        return false;
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
