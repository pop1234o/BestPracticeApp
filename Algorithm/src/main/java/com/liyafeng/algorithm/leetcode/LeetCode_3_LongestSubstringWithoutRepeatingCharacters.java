package com.liyafeng.algorithm.leetcode;

import java.util.Arrays;

/**
 * Created by liyafeng on 2018/3/1.
 */

public class LeetCode_3_LongestSubstringWithoutRepeatingCharacters {

    /**
     * Given a string, find the length of the longest substring without repeating characters.
     * <p>
     * Examples:
     * <p>
     * Given "abcabcbb", the answer is "abc", which the length is 3.
     * <p>
     * Given "bbbbb", the answer is "b", with the length of 1.
     * <p>
     * Given "pwwkew", the answer is "wke", with the length of 3.
     * Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
     * ================思路=================
     * 最简单的思路就是依次遍历每个字符开头的无重复子串，并且比较长度，返回最大的
     * 但是这种时间复杂度是O(n^2)
     * @param args
     */
    public static void main(String[] args) {

        int longestSubstring = lengthOfLongestSubstring2("jbpnbwwd");
        System.out.println("length:" + longestSubstring);
    }

    /**
     * 最简单的思路就是依次遍历每个字符开头的无重复子串，并且比较长度，返回最大的
     * 但是这种时间复杂度是O(n^2)
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstring(String s) {
        StringBuilder builder = new StringBuilder();
        int longest = 0;

        int length = s.length();
        for (int i = 0; i < length; i++) {
            for (int j = i; j < length; j++) {
                char c = s.charAt(j);
                if (builder.toString().indexOf(c) >= 0) {
                    if (builder.length() > longest) {
                        longest = builder.length();
                    }
                    builder.delete(0, builder.length());
                    break;
                } else {
                    builder.append(c);
                }
            }
        }

        if (builder.length() > longest) {//当有一个字符的时候，或者没有重复字符的时候
            longest = builder.length();
        }
        return longest;
    }


    /**
     * j b p n b w w d
     * j i
     * 一个指针i从1开始遍历数组，一个指针j，从split=0开始，依次遍历i之前的元素
     * 判断是否又和i相等的，如果没有，那么当前长度应该加1
     * 如果有，j这时指向和i相同的那个元素，那么split指向j+1，当前长度改为i-j
     * (这步是重置长度)
     * 这个思路就是加入新元素，遍历老元素，然后判断有没有和新元素相同的，没有则长度加一
     * 有就指向老元素中相同的那个后一个，重新开始计算
     * =========================
     * 但是这种方法的时间复杂度在最坏情况下是O(n^2)，就是没有重复元素的情况下
     * ==========================
     * 这种思路其实还是分析规律。
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstring1(String s) {
        char[] chars = s.toCharArray();//jbpnbwwd
        if(2 > chars.length){
            return chars.length;
        }
        int max = 0;
        int split_at = 0;
        int cur_len = 1;
        for(int i=1;i<chars.length;i++){
            int j = split_at;
            for(;j<i;j++){
                if(chars[i] == chars[j]){
                    break;
                }
            }
            if(j < i){
                split_at = j+1;
                cur_len = i-j;
            }else{
                cur_len++;
            }
            if(cur_len > max) max = cur_len;
        }
        return max;
    }


    /**
     * abcabcbb
     * 这个思路是一个窗口思路，left 和right指针都指向0，
     * 要将right的index都记录到数组中，然后right右移，再判断这个right
     * 然后取出这个right指向元素的index，如果不存在则长度是right-left+1
     * 如果存在，则取出的值是这个元素的index，如果index大于left，那么判断他在窗口中
     * 则left移动到窗口重复元素的右一位，就是index+1,然后重新扩大窗口
     * 有一个规律是原窗口中的元素一定不是重复的
     *
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstring2(String s) {
        char[] str = s.toCharArray();
        // #1-使用滑动窗口[l...r]记录当前的子数组
        int l = 0, r = 0, max = 0;//left ,right
        // #2-使用大小为256的数组记录每个元素最近一次遍历的下标
        int[] memo = new int[256];
        Arrays.fill(memo, -1);
        while (r < str.length) {
            int index = memo[str[r]];
            // #3-如果当前元素不是第一次遍历且存在于滑动窗口[l...r]中，则更新l
            // #4-否则将当前元素记录在滑动窗口中
            if (index >= l){
                l = index + 1;
            } else {
                max = max < r - l + 1 ? r - l + 1 : max;
            }
            // #5-记录最新下标，且滑动窗口右移一位
            memo[str[r]] = r;
            r++;
        }
        return max;
    }
}
