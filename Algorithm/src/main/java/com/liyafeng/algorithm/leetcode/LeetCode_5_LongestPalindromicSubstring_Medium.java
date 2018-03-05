package com.liyafeng.algorithm.leetcode;

/**
 * Created by liyafeng on 2018/3/5.
 */

public class LeetCode_5_LongestPalindromicSubstring_Medium {

    /**
     * 最长回文子串
     * Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.
     * <p>
     * Example:
     * <p>
     * Input: "babad"
     * <p>
     * Output: "bab"
     * <p>
     * Note: "aba" is also a valid answer.
     * <p>
     * <p>
     * Example:
     * <p>
     * Input: "cbbd"
     * <p>
     * Output: "bb"
     * ============================
     *
     * @param args
     */
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
//        String s = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabcaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
//        String s = "civilwartestingwhetherthatnaptionoranynartionsoconceivedandsodedicatedcanlongendureWeareqmetonagreatbattlefiemldoftzhatwarWehavecometodedicpateaportionofthatfieldasafinalrestingplaceforthosewhoheregavetheirlivesthatthatnationmightliveItisaltogetherfangandproperthatweshoulddothisButinalargersensewecannotdedicatewecannotconsecratewecannothallowthisgroundThebravelmenlivinganddeadwhostruggledherehaveconsecrateditfaraboveourpoorponwertoaddordetractTgheworldadswfilllittlenotlenorlongrememberwhatwesayherebutitcanneverforgetwhattheydidhereItisforusthelivingrathertobededicatedheretotheulnfinishedworkwhichtheywhofoughtherehavethusfarsonoblyadvancedItisratherforustobeherededicatedtothegreattdafskremainingbeforeusthatfromthesehonoreddeadwetakeincreaseddevotiontothatcauseforwhichtheygavethelastpfullmeasureofdevotionthatweherehighlyresolvethatthesedeadshallnothavediedinvainthatthisnationunsderGodshallhaveanewbirthoffreedomandthatgovernmentofthepeoplebythepeopleforthepeopleshallnotperishfromtheearth";
        String s = "babad";
                String ss = longestPalindrome(s);
//        String ss = new Solution().longestPalindrome(s);
        long end = System.currentTimeMillis();
        System.out.println("longest Palindrome:  " + ss);
        System.out.println("time:  " + (end - start));


    }

    /**
     * start指针和end指针，start依次遍历数组中元素，end指针向前找到
     * 第一个和start相等的元素，然后判断是不是回文，不是则继续向前找
     * 但是这个时间复杂度是n^2，还是很慢
     *
     * @param s
     * @return
     */
    public static String longestPalindrome(String s) {
        if (s.isEmpty()) {
            return null;
        }
        char[] chars = s.toCharArray();
        int length = chars.length;
        int end = length - 1;
        int maxLength = 0;
        int maxStart = 0;
        int maxEnd = 0;
        for (int start = 0; start <= length - 1; start++) {
            char startChar = chars[start];
            while (end >= start) {
                char endChar = chars[end];
                if (endChar == startChar) {
                    boolean palindrome = isPalindrome(chars, start, end);
                    if (palindrome && end - start + 1 > maxLength) {
                        maxStart = start;
                        maxEnd = end ;
                        maxLength = end - start + 1;
                    }
                }
                end--;
            }
            end = length - 1;
        }

        return s.substring(maxStart, maxEnd+1 );
    }

    private static boolean isPalindrome(char[] chars, int start, int end) {
        while (start <= end) {
            if (chars[start++] != chars[end--]) {
                return false;
            }
        }
        return true;
    }


    /**
     * 这种思路就是以每个点为中心，
     */
    static class Solution {
        int len = 0, maxLength = 0, init = 0;

        public String longestPalindrome(String s) {
            char[] chars = s.toCharArray();
            len = s.length();
            if (len <= 1) return s;
            for (int i = 0; i < len; i++) {
                i = manacher(chars, i);//这样是返回中心点，就不用一个个遍历了
            }
            return s.substring(init, init + maxLength);
        }

        public int manacher(char[] chars, int k) {//"babad"
            int i = k - 1, j = k;//j就是中心点的index，i是左边第一个和中心点不同的
            while (j < len - 1 && chars[j] == chars[j + 1]) j++;//j右移至第一个和中心点不相等的元素
            int nextCenter = j++;//记录下一个中心点
            while (i >= 0 && j < len && chars[i] == chars[j]) {//从中心点从两边扩散，直到不相等
                i--;
                j++;
            }
            if (j - i - 1 > maxLength) {//判断长度
                maxLength = j - i - 1;
                init = i + 1;
            }
            return nextCenter;
        }
    }
}
