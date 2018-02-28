package com.liyafeng.algorithm.sword2offer;

/**
 * Created by liyafeng on 2018/2/28.
 */

public class N50_LastCommonNode {

    /**
     * 输入两个树节点，求一个他们的最低公共祖先
     * ================思路====================
     * 如果是二叉搜索树，那么从根节点遍历树，如果节点大于 两个树节点，那么这两个节点
     * 肯定都在左子树中，反之在右子树中，直到遇到一个节点在两个树节点之间，那么这个节点就是
     * 他们的最低公共祖先（规律分析）
     *
     * 如果不是二叉搜索树，也不是二叉树，就是普通的树。（节点有指向父节点的指针）
     * 那么这个题就变成了求两个链表的第一个公共节点。（知识迁移能力）
     *
     *
     * @param args
     */
    public static void main(String[] args) {

    }
}
