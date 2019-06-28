package com.liyafeng.algorithm.leetcode.array;

/**
 * @author pop
 */
public class LeetCode_105_ConstructBinaryTreeFromPreorderAndInorderTraversal_Medium {

    /**
     * Given preorder and inorder traversal of a tree, construct the binary tree.
     * You may assume that duplicates do not exist in the tree.
     * <p>
     * 给定一个树的前序遍历和中序遍历，构造二叉树。假定没有重复元素
     *
     *
     * <p>
     * ========示例=======
     * <p>
     * <p>
     * For example, given
     * <p>
     * preorder = [3,9,20,15,7]
     * inorder = [9,3,15,20,7]
     * Return the following binary tree:
     * <p>
     * 3
     * / \
     * 9  20
     * /  \
     * 15   7
     * <p>
     * =========思路==============
     * 递归，动态规划的思路
     * preorder第一个元素肯定是root，
     * 然后在中序遍历中查找这个root，左边的元素就是左子树，右边的就是右子树
     *
     * <p>
     * <p>
     * <p>
     * =======知识点======
     * 递归+二叉树遍历特性
     * <p>
     * =============
     *
     * @param args
     */
    public static void main(String[] args) {

    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {

    }


}
