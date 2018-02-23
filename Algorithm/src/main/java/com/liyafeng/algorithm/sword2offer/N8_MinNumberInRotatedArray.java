package com.liyafeng.algorithm.sword2offer;

public class N8_MinNumberInRotatedArray {

    /**
     * a1和a2的中点为（a1+a2）/2
     * <p>
     * 最后肯定走到 start = mid 然后后一个就是最小值了
     *
     * @param args
     */
    public static void main(String[] args) {

        int[] ints = {7, 10, 15, 1, 2, 3, 4, 5, 6};
//        int[] ints = {3, 4, 5, 6, 1, 2};

        int n = findMin2(ints, 0, ints.length - 1);
        System.out.println(n);
    }

    private static int findMin1(int[] ints, int start, int end) {


        int mid = (end + start) / 2;

        if (ints[mid] > ints[start]) {//当前在左边

            return findMin1(ints, mid, end);

        } else if (ints[mid] < ints[start]) {//在右边

            return findMin1(ints, start, mid);
        } else {//这里是start和mid指向同一个
            return ints[end];
        }
    }


    private static int findMin2(int[] ints, int start, int end) {

        int mid = (start + end) / 2;
        while (start < mid) {//这里要放后面是因为start或者end已经更新了，那么mid也要更新



            if (ints[mid] > ints[start]) {
                start = mid;
            } else if (ints[mid] < ints[end]) {
                end = mid;
            } else {
                return ints[end];
            }

            mid = (start + end) / 2;
        }

        return ints[end];
    }

}
