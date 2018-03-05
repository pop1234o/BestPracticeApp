package com.liyafeng.algorithm.leetcode;

import java.util.ArrayList;

/**
 * Created by liyafeng on 2018/3/5.
 */

public class LeetCode_6_ZigZagConversion_Medium {

    /**
     * Z型转化
     * The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern in a fixed font for better legibility)
     * <p>
     * P   A   H   N
     * A P L S I I G
     * Y   I   R
     * And then read line by line: "PAHNAPLSIIGYIR"
     * Write the code that will take a string and make this conversion given a number of rows:
     * <p>
     * string convert(string text, int nRows);
     * convert("PAYPALISHIRING", 3) should return "PAHNAPLSIIGYIR".
     *
     * @param args
     */
    public static void main(String[] args) {
        String s = convert("PAYPALISHIRING", 3);
        System.out.println("out:"+s );
        System.out.println(s.equals("PAHNAPLSIIGYIR"));
    }


    /**
     * 可以每一行用一个队列，然后上下加入队列
     * 最终按行出队列，就是转化后的值
     * ===============================
     * 或者按照每一行的元素数字变化的规律直接加入一个队列，这个队列就是转化后的值
     * 0   4   8        4
     * 1 3 5 7 9        2
     * 2   6  10        4
     * <p>
     * 0    6     12      6
     * 1  5 7  11 13      4 2
     * 2 4  8 10  14      2 4
     * 3     9    15      6
     * <p>
     * 0     8      16      8
     * 1   7 9    15 17    6 2
     * 2  6 10   14  18    4 4
     * 3 5  11 13    19    2 6
     * 4    12       20    8
     *
     * @param s
     * @param numRows
     * @return
     */
    public static String convert(String s, int numRows) {
        if (numRows <= 1) {
            return s;
        }
        char[] chars = s.toCharArray();
        ArrayList<Character>[] lists = new ArrayList[numRows];
        for (int i = 0; i < numRows; i++) {
            lists[i] = new ArrayList<>();
        }
        int i = 0;
        int length = chars.length;
        int flag = 1, index = 0;
        while (i < length) {
            lists[index].add(chars[i++]);
            if (index == numRows - 1) {
                flag = -1;
            } else if (index == 0) {
                flag = 1;
            }

            index = index + flag;
        }
        StringBuilder builder = new StringBuilder();
        for (int k = 0; k < numRows; k++) {
            int size = lists[k].size();
            for (int j = 0; j < size; j++) {
                builder.append(lists[k].get(j));
            }
        }

        return builder.toString();

    }
}
