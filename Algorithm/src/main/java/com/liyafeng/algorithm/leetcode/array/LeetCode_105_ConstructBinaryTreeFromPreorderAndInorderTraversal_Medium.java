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
     * --/  \
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


    /**
     * 前序遍历中第一个元素肯定是根元素，在中序遍历中找到这个元素，那么它的左边肯定就是他的左子树，右边就是右子树
     * 如此递归，直到构造到叶子节点，他们的下个节点肯定为null了，所以就直接返回null，递归就结束了
     *
     * @param preorder
     * @param inorder
     * @return
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {

        return build(0, 0, inorder.length - 1, preorder, inorder);
    }

    /**
     * @param preStart 这组前序遍历开始的元素的index
     * @param inStart
     * @param inEnd
     * @param preorder
     * @param inorder
     * @return
     */
    private TreeNode build(int preStart, int inStart, int inEnd, int[] preorder, int[] inorder) {

        //递归退出条件
        if (preStart > preorder.length - 1 || inStart > inEnd) {
            return null;
        }

        int root = preorder[preStart];
        int positionRootInOrder = -1;
        for (int i = inStart; i <= inEnd; i++) {
            if (inorder[i] == root) {
                positionRootInOrder = i;
                break;
            }
        }

        TreeNode treeNode = new TreeNode(root);
        treeNode.left = build(preStart + 1, inStart, positionRootInOrder - 1, preorder, inorder);
        treeNode.right = build(preStart - inStart + positionRootInOrder + 1, positionRootInOrder + 1, inEnd, preorder, inorder);

        return treeNode;

    }


    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }


}
