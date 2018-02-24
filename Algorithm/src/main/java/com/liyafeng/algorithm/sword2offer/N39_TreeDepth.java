package com.liyafeng.algorithm.sword2offer;

import com.liyafeng.datastructure.BinaryTree;

import static com.liyafeng.datastructure.BinaryTree.*;

/**
 * Created by liyafeng on 2018/2/24.
 */

public class N39_TreeDepth {

    /**
     * 求一个二叉树的深度，
     *  ============思路============
     *  一个节点的深度其实就是他的左子树和右子树深度较大的那个深度加一
     *  ============考点==============
     *  考察的是分治思想，这种用递归解决代码会很简洁
     * @param args
     */
    public static void main(String[] args) {
        BinaryTree binaryTree = new BinaryTree();
        binaryTree.root = new TreeNode(1);
        TreeNode left = new TreeNode(2);
        binaryTree.root.putNode(left, new TreeNode(3));
        TreeNode right = new TreeNode(5);
        left.putNode(new TreeNode(4), right);
        TreeNode treeNode = new TreeNode(6);
        right.putNode(treeNode, null);
        treeNode.putNode(new TreeNode(7),null);
        int depth = getTreeDepth(binaryTree.root);
        System.out.println("tree depth:"+depth);
    }

    private static int getTreeDepth(TreeNode root) {

        if(root==null){
            return 0;
        }

        int left = getTreeDepth(root.getLeft());
        int right = getTreeDepth(root.getRight());

        return left > right ? left + 1 : right + 1;
    }

}
