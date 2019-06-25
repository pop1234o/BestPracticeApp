package com.liyafeng.algorithm.leetcode.array;

/**
 * @author pop
 */
public class LeetCode_80_RemoveDuplicatesFromSortedArrayII_Medium {

    /**
     * Given a sorted array nums, remove the duplicates in-place such
     * that duplicates appeared at most twice and return the new length.
     *
     * Do not allocate extra space for another array,
     * you must do this by modifying the input array in-place with O(1) extra memory.
     * <p>
     * 一个有序数组，移除重复的元素，使得每个元素最多显示两次，返回新数组的长度
     * 不要使用额外的内存空间。
     *
     *
     * <p>
     * ========示例=======
     * <p>
     * <p>
     * Example 1:
     * Given nums = [1,1,1,2,2,3],
     *
     * Your function should return length = 5, with the first five elements of nums being 1, 1, 2, 2 and 3 respectively.
     *
     * It doesn't matter what you leave beyond the returned length.
     *
     *
     * Example 2:
     * Given nums = [0,0,1,1,1,1,2,3,3],
     *
     * Your function should return length = 7, with the first seven elements of nums being modified to 0, 0, 1, 1, 2, 3 and 3 respectively.
     *
     * It doesn't matter what values are set beyond the returned length.
     * <p>
     * =========思路==============
     * 双指针遍历
     * <p>
     * <p>
     * <p>
     * =======知识点======
     * DFS  deep first search 深度优先遍历
     * <p>
     * =============
     *
     * @param args
     */
    public static void main(String[] args) {

        char[][] board = {{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}};
        boolean exist = exist(board, "ABCCED");
        System.out.println("是否存在" + exist);

    }


    public static boolean exist(char[][] board, String word) {
        //遍历每一个字符
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                //从word的第0个单词开始查找
                if (charExist(row, col, board, word, 0)) {
                    return true;
                }
            }
        }

        return false;
    }

    private static boolean charExist(int row, int col, char[][] board, String word, int i) {

        //i到最后一个了，说明word查找到了，返回true
        if (i == word.length()) {
            return true;
        }

        //查找超出边界了
        if (row < 0 || col < 0 || row >= board.length || col >= board[0].length) {
            return false;
        }

        //不匹配
        if (board[row][col] != word.charAt(i)) {
            return false;
        }

        //不再查找这个单词了，所以置为0，这样就匹配不到了
        board[row][col] = 0;

        //查找下一个字母是否存在于相邻的点中，上下左右共四个点
        boolean existNext = charExist(row + 1, col, board, word, i + 1)
                || charExist(row - 1, col, board, word, i + 1)
                || charExist(row, col + 1, board, word, i + 1)
                || charExist(row, col - 1, board, word, i + 1);

        //恢复这个单词
        board[row][col] = word.charAt(i);
        return existNext;
    }

}
