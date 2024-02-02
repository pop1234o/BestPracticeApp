package com.liyafeng.algorithm.sword2offer;

/**
 * Created by liyafeng on 2018/2/26.
 */

public class N42_ReverseWordsInSentence {

    /**
     * 反转一个句子中的单词 I am good -> good am I
     * ==============思路====================
     *
     * @param args
     */
    public static void main(String[] args) {

        String s = "I am good";
        s = reverse(s);
        System.out.println(s);
    }

    private static String reverse(String s) {
        s = s.trim();
        int end = s.length() - 1;
        int count = 0;

        StringBuilder builder = new StringBuilder();
        //指针从后往前遍历，找到空白格，然后这个位置往后遍历，加入新的数组中。
        for (int i = end; i >= 0; i--) {
            if (s.charAt(i) != ' ') {
                count++;
            } else {
                for (int j = i+1; j <= i+count; j++) {
                    builder.append(s.charAt(j));
                }
                builder.append(" ");
                count = 0;
            }
        }
        for (int i = 0; i < count; i++) {
            builder.append(s.charAt(i));
        }
        return builder.toString();
    }
}
