package com.liyafeng.algorithm.sword2offer;

public class N30_GetLeastNumbers {


    /**
     * 找出数组中最小的k个数
     * ----------
     * 1.分治思想 O(n)
     * <p>
     *     用快速切分 ，找到索引为 n-1 的数字， 这样它的左边肯定都是比它小的
     *     []     [n-1]   []
     *     0,1,2   3
     *     --总共4个数字
     * <p>
     *  这种方法
     * ================
     * 2.辅助空间思想（适合海量数据输入，因为这个流式处理） O(nlogk)
     *
     * 每次入优先队列，直到辅助空间满，当再次取下一个数的时候
     * 和优先队列中的最大值比较，如果比最大值小，那么移除最大值，将这个数字入队列
     * 如果大，则取下一个数字
     *
     *
     *
     * @param args
     */
    public static void main(String[] args) {

        int[] ints = {8, 5, 6, 1, 2, 3, 4};

        int[] leasts = findLeastNumbers(ints, 4);


        for (int i = 0; i < leasts.length; i++) {
            System.out.print(leasts[i] + " ");
        }
    }

    private static int[] findLeastNumbers(int[] ints, int n) {

        int mid = partition(ints, 0, ints.length - 1);

        int start = 0;
        int end = ints.length - 1;

        while (mid != (n - 1)) {//反复的去找第n大的数字，缩小范围   这里是索引 要减1
            if (mid > n) {
                end = mid - 1;
                mid = partition(ints, start, mid - 1);
            } else {
                start = mid + 1;
                mid = partition(ints, mid + 1, end);
            }

        }

        int[] results = new int[n];
        for (int i = 0; i < n; i++) {
            results[i] = ints[i];
        }

        return results;
    }

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
