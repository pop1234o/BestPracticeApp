package com.liyafeng.algorithm.sword2offer;

public class N29_FindHalfNumInArray {

    /**
     * 找出在数组中出现次数在一半以上的数字（假设数组中有这个数字）
     * <p>
     * 分治思想
     * <p>
     * 出现一半以上的数字一定在排序后的数组中间
     * <p>
     * 所以我们问题就变成找出数组中第n/2 大的数字
     * <p>
     * ==========================================
     * 第二种O(n)的方法
     * 遍历数组，第一个数，出现次数为1
     * 取第二个数，如果和第一个相等，次数加1
     * 不想等 减 1
     * 如果次数为0 ，那么取下一个数，次数重置为 1
     * 如此反复
     * <p>
     * 那个多于一半的数肯定是最后一个 将次数重置为1的数
     * //用辅助空间来解决问题
     *
     * @param args
     */
    public static void main(String[] args) {

        int[] ints = {1, 2, 3, 2, 2, 5, 5, 2, 5, 5, 2};

        int half = checkMoreThanHalf(ints, 0, ints.length - 1);

        System.out.println("中位数为" + half);
    }

    public static int checkMoreThanHalf(int[] ints, int lo, int hi) {


        int mid = partition(ints, lo, hi);


        while (mid != ints.length / 2) {

            if (mid > ints.length / 2) {//中位数在左边
                hi = mid - 1;//将范围变小
                mid = partition(ints, lo, mid - 1);
            } else {//中位数在右边
                lo = mid + 1;
                mid = partition(ints, mid + 1, hi);
            }
        }

        return ints[mid];

    }

    /**
     * 切分
     *
     * @param ints
     * @param lo
     * @param hi
     * @return
     */
    public static int partition(int[] ints, int lo, int hi) {
        int k = lo;
        int start = lo + 1;
        int end = hi;

        while (true) {
            while (ints[start] <= ints[k]) {
                start++;
                if (start == hi) {
                    break;
                }
            }
            while (ints[end] >= ints[k]) {
                end--;
                if (end == lo) {
                    break;
                }
            }

            if (start >= end) {
                break;
            }
            exchange(ints, start, end);
        }

        exchange(ints, k, end);
        return end;

    }

    public static void exchange(int[] ints, int start, int end) {
        int n = ints[start];
        ints[start] = ints[end];
        ints[end] = n;
    }

}
