package com.liyafeng.algorithm.leetcode.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author pop
 */
public class LeetCode_79_WordSearch_Medium {

    /**
     * Given a 2D board and a word, find if the word exists in the grid.
     * <p>
     * The word can be constructed from letters of sequentially adjacent cell,
     * where "adjacent" cells are those horizontally or vertically neighboring.
     * The same letter cell may not be used more than once.
     * <p>
     * 给一个二维数组，和一个单词，判断单词是否在二维数组中
     * adjacent（相邻的）
     * 单词只能构建与相邻的元素，包括垂直和水平的相邻元素，同一个单词不能使用两次
     *
     * <p>
     * ========示例=======
     * <p>
     * <p>
     * board =
     * [
     * ['A','B','C','E'],
     * ['S','F','C','S'],
     * ['A','D','E','E']
     * ]
     * <p>
     * Given word = "ABCCED", return true.
     * Given word = "SEE", return true.
     * Given word = "ABCB", return false.
     * <p>
     * =========思路==============
     * 深度优先遍历，
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
