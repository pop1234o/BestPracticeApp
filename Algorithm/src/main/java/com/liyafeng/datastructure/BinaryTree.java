package com.liyafeng.datastructure;

/**
 * Created by liyafeng on 2018/2/24.
 * 完全二叉树
 * 满二叉树
 * 平衡二叉树：左右子树的高度相差不大于1
 */

public class BinaryTree {

    public TreeNode root;

    public static class TreeNode {
        int value;

        public TreeNode(int value) {
            this.value = value;
        }

        public TreeNode getLeft() {
            return left;
        }

        public TreeNode getRight() {
            return right;
        }

        TreeNode left;
        TreeNode right;

        public void putNode(TreeNode left, TreeNode right) {
            this.left = left;
            this.right = right;
        }
    }
}
