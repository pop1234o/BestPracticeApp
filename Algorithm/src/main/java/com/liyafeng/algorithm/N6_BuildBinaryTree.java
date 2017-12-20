package com.liyafeng.algorithm;

public class N6_BuildBinaryTree {
    /**
     * 堆是一种遵守特定规则的树
     * 堆是树的一种
     * <p>
     * 给出前序和中序遍历，还原出二叉树
     * <p>
     * 这个问题是分治思想的应用
     * <p>
     * 重点是四个指针
     * 重点是搞清四个指针的位置
     *
     * @param args
     */
    public static void main(String[] args) {

        int[] pre = {1, 2, 4, 7, 3, 5, 6, 8};

        int[] mid = {4, 7, 2, 1, 5, 3, 8, 6};


        BinaryTree tree = buildBinaryTree(pre, mid);

        tree.printEnd(tree.root);


    }

    public static BinaryTree buildBinaryTree(int[] pre, int[] mid) {

        BinaryTree tree = new BinaryTree();


        tree.root = build(pre, mid, 0, pre.length - 1, 0, mid.length - 1);


        return tree;
    }

    static BinaryTree.Node build(int[] pre, int[] mid, int startpre, int endpre, int startmid, int endmid) {

        int midroot = -1;

        BinaryTree.Node root = new BinaryTree.Node();
        root.value = pre[startpre];

        //找到根节点
        for (int i = 0; i < mid.length; i++) {
            if (mid[i] == root.value) {
                midroot = i;
                break;
            }
        }

        int leftlength = midroot - startmid;
        if (leftlength > 0) {//左
            root.left = build(pre, mid, startpre + 1, startpre + leftlength, startmid, midroot - 1);
        }

        if (leftlength < endmid - startmid) {//右

            root.right = build(pre, mid, startpre + leftlength + 1, endpre, midroot + 1, endmid);
        }

        return root;
    }


    private static class BinaryTree {
        Node root;

        static class Node {
            int value;
            Node left;
            Node right;
        }


        public void printEnd(Node root) {
            Node right = root.right;
            Node left = root.left;
            if (right != null) {
                printEnd(right);
            }
            if (left != null) {
                printEnd(left);
            }
            System.out.print(" " + root.value);
        }
    }
}
