package com.liyafeng.algorithm.sword2offer;

public class N10_NumberOf1InBinary {

    /**
     * int 32位，有2^32种可能，前1位表示符号，那么剩下的31位有2^31可能 正数有2^31个，负数也有2^31个，但是正数有0
     * 1111111111111   -1
     * 1000000000000    Integer.MIN_VALUE  -2147483648
     * 0000000000000    0
     * 0111111111111    Integer.MAX_VALUE  2147483647 因为这里0也是正数，所以加上2147483647 就是2147483648
     * <p>
     * 我们平时的 10 是2，其实前面的30位都是0，只不过是省略了
     * <p>
     * toBinaryString
     * <p>
     * <<左移，低位补0
     * >>右移，正，补0 ，负，补1
     * >>> 无符号右移 无论正负，高位都补0
     *
     *
     * ===============================
     * 1110100
     * 减一
     * 1110011
     *
     * &
     * 1110000 这样就去掉了一个1，一次去掉一个1，直到数字为0
     * 统计去掉1的次数，即可
     *
     *
     * ================================
     * ^ 异或 ，相同为假0，不同为真1
     *
     * @param args
     */
    public static void main(String[] args) {

        int n = 3;

//        System.out.println(Integer.toBinaryString(-10));
//        System.out.println(Integer.toBinaryString(-10 >> 1));
//        System.out.println(Integer.toBinaryString(-1 >>> 1));
//
//        System.out.println(Integer.MAX_VALUE);
//        System.out.println(Integer.MIN_VALUE);


        System.out.println(Integer.toBinaryString(11));
        int num = getNumOf1(11);
        System.out.println(num);
    }

    /**
     * 标志位，做& 不为0代表当前位是1，计数器加1，flag左移，循环如此
     * @param i
     * @return
     */
    private static int getNumOf1(int i) {

        if (i == 0) {
            return 0;
        }
        int flag = 1;
        int count = 0;
        while (flag != 0) {

            int i1 = i & flag;
            if (i1 != 0) {
                count++;
            }
            flag <<= 1;
        }

        return count;
    }
}
