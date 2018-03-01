package com.liyafeng.algorithm.leetcode;

/**
 * Created by liyafeng on 2018/3/1.
 */

public class LeetCode_2_AddTwoNumbers {

    /**
     * You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.
     * <p>
     * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
     * <p>
     * Example
     * <p>
     * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
     * Output: 7 -> 0 -> 8
     * Explanation: 342 + 465 = 807.
     * ==============思路===========================
     *
     * @param args
     */
    public static void main(String[] args) {

//        ListNode node = new ListNode(2);
//        node.next = new ListNode(4);
//        node.next.next = new ListNode(3);
//
//        ListNode node1 = new ListNode(5);
//        node1.next = new ListNode(6);
//        node1.next.next = new ListNode(4);


        ListNode node = new ListNode(1);

        ListNode node1 = new ListNode(9);
        node1.next = new ListNode(9);

        ListNode listNode = addTwoNumbers1(node, node1);

        while (listNode != null) {
            System.out.println(listNode.val);
            listNode = listNode.next;
        }
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null && l2 == null) {
            return null;
        }
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }

        ListNode root = null;
        ListNode node = null;
        int carry = 0;
        do {
            int i = (l1 != null ? l1.val : 0) + (l2 != null ? l2.val : 0);
            int val = (i % 10) + carry;
            if (val >= 10) {
                val = val % 10;
                carry = i / 10 + 1;
            } else {
                carry = i / 10;
            }
            if (root == null) {
                root = new ListNode(val);
                node = root;
            } else {
                node.next = new ListNode(val);
                node = node.next;
            }
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        } while (l1 != null || l2 != null || carry > 0);

        return root;
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }


    public static ListNode addTwoNumbers1(ListNode l1, ListNode l2) {
        ListNode result = new ListNode(0);

        ListNode p = l1, q = l2, curr = result;
        int carry = 0;

        while (p != null || q != null) {
            int x = (p != null) ? p.val : 0;
            int y = (q != null) ? q.val : 0;

            int sum = carry + x + y;
            carry = sum / 10;

            curr.next = new ListNode(sum % 10);
            curr = curr.next;

            if (p != null)
                p = p.next;
            if (q != null)
                q = q.next;
        }

        if (carry > 0)
            curr.next = new ListNode(carry);

        return result.next;
    }
}
