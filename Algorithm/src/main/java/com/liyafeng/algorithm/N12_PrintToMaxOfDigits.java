package com.liyafeng.algorithm;

public class N12_PrintToMaxOfDigits {

    /**
     * 大数问题
     * <p>
     * 打印 n位数的 1-max
     * 比如输入16，那么就打印1到99999999。。（16个9）
     *
     * @param args
     */
    public static void main(String[] args) {

        printMax(16);
    }

    /**
     * @param digit 位数
     */
    private static void printMax(int digit) {


        if (digit < 1) {
            return;
        }
        //用字符数组表示

        char[] chars = new char[digit];
        for (int i = 0; i < digit; i++) {
            chars[i] = '0';
        }

        //字符数组加1

        while (increament(chars)) {

        }
        print(chars);
        //打印

    }

    /**
     * 这个标记是标明还没有遇到非0的字符
     *
     * @param chars
     */
    private static void print(char[] chars) {//这里 如果第一个不为0的要标记

        boolean flag = true;
        for (int i = 0; i < chars.length; i++) {
            if (flag && chars[i] == '0') {
                continue;
            }

            flag = false;
            System.out.print(chars[i]);

        }
        System.out.println();
    }

    private static boolean increament(char[] chars) {

        for (int i = chars.length - 1; i >= 0; i--) {
            if (chars[i] == '9') {//加1
                if (i == 0) { //最后恢复加1的判断
                    for (int j = 0; j < chars.length; j++) {
                        chars[j] = '9';
                    }
                    return false;
                }
                chars[i] = '0';
            } else {
                chars[i] += 1;
                break;
            }
        }

        return true;
    }
}
