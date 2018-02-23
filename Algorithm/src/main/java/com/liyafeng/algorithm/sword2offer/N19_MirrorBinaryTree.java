package com.liyafeng.algorithm.sword2offer;

public class N19_MirrorBinaryTree {

    /**
     * 求一个二叉树的镜像
     *
     * 先举一个例子
     * 然后分析一下可以实现的过程
     * 再写成代码
     * 最后加上边界条件，异常情况
     * @param args
     */
    public static void main(String[] args) {
        Tree tree = new Tree();
        mirror(tree);
    }

    private static void mirror(Tree tree) {
        Tree.Node root = tree.root;
        changeNode(root.left, root.right);
    }

    private static void changeNode(Tree.Node left, Tree.Node right) {
        if (left == null && right == null) {
            return;
        }
        //交换
        Tree.Node node = left;
        left = right;
        right = node;
        //再交换它的子节点
        changeNode(left.left, left.right);
        changeNode(right.left, right.right);

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
