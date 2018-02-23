package com.liyafeng.algorithm.sword2offer;

public class N4_ReplaceString {

    /**
     * String替换过程
     * 先找到这个字符的位置，然后将位置前的字符加入StringBuiler，然后将新的字符加入
     * 循环如此.
     *
     * 可以考虑从后向前
     * @param args
     */
    public static void main(String[] args) {

//        String s = "we are faimliy";
//
//        s.replace("a","A");

        char[] chars = new char[10];
        for (int i = 0; i < chars.length; i++) {
            if (i % 3 == 0) {
                chars[i] = ' ';
            } else {
                chars[i] = 'a';
            }
            System.out.print(chars[i]);
        }

        System.out.println();

        char[] replaceBlank = replaceBlank(chars);

        for (int i = 0; i < replaceBlank.length; i++) {
            System.out.print(replaceBlank[i]);
        }
    }


    public static char[] replaceBlank(char[] chars){

        int count =0;
        for (int i = 0; i < chars.length; i++) {
            char aChar = chars[i];
            if(aChar==' '){
                count++;
            }
        }

        char[] newchars = new char[chars.length + count*3];

        for (int i = 0 ,j =0; i < chars.length; i++,j++) {
            char aChar = chars[i];
            if (aChar == ' ') {
                newchars[j++] = '%';
                newchars[j++] = '2';
                newchars[j] = '0';

            } else {
                newchars[j] = chars[i];
            }
        }

        return newchars;

    }
}
