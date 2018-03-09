package com.liyafeng.algorithm.basic.sort;

public class N_Sort_QuickSort {

    /**
     * 快速排序（时间复杂度 O(n log n)）
     * ==============原理==================
     * 取第一位 low，
     * 定义start，end两个指针指向 low+1 和 high
     * k  *i          *j
     * 6, 1, 2, 5, 4, 8
     *
     * i向右扫描，直到遇到第一个比它大的数，并指向它
     * j向左扫描，遇到第一个比它小的数
     *
     * 交换i和j ,直到 i>=j ，这个时候 j 一定会指向比 k 小的数，所以 j和 k 交换，
     * 这样就变成 [比k小的数][k][比k大的数]
     *
     * 交换i和j,反复如此，直到 i>=j  (j有可能比i前一位，如果遇到这个情况，就是 [k][小于的数][大于的数]这种情况)
     * 所以k要和 j 交换
     *
     * i==j的情况是 [k][小于它的数] 这样 i 指向最后一个数，j则默认指向最后一个数。
     *
     * 这个过程叫一次切分（partition）
     *
     * start > end 情况就是 [k][小于k][大于k]
     *                            *  *
     *                          end  start
     * 这个时候end 和 k交换 ，完成一次切分
     *
     * 如果     [k][小于k]
     *                **
     *              end start 都指向最后一个
     * 这个时候 k 和 end 交换  完成 [小于k][k]
     *
     * 如果    [k][大于k]
     *         *   *
     *         end  start  不交换
     * 然后 就是 k 和 end 交换， 他们都是一样，所以不进行交换
     *
     * 所以 start >= end 的时候就退出，代表完成这次切分
     * ================优缺点========================
     * 缺点：
     *  1.对于小数组，快速排序比插入排序慢，所以当切分到小数组的时候，改为插入排序
     *  2.对于特定的数组表现不好，比如有大量重复元素的数组，切分不能对半分的数组，
     *  这样就需要很多次切分
     *  3.对于递归实现的快速排序，不适合大量数据，会造成栈溢出，需要改为非递归实现
     *
     * 优点：
     *  1.代码简洁
     *  2.一般比归并排序和希尔排序快，因为他们在内循环中移动数据
     *  3.比较次数少
     *
     *
     * @param args
     */
    public static void main(String[] args) {

        int[] ints = {6, 1, 2, 5, 4, 8};
        sort(ints, 0, ints.length - 1);

        for (int i = 0; i < ints.length; i++) {
            System.out.print(ints[i]+" ");
        }
    }

    public static void sort(int[] ints, int lo, int hi) {

        if (hi <= lo) {
            return;
        }
        int mid = partition(ints, lo, hi);
        sort(ints, lo, mid - 1);
        sort(ints, mid + 1, hi);

    }

    /**
     * 切分！！选中的那个元素叫做pivot（轴）
     * 经典的快速排序是单个pivot
     * @param ints
     * @param lo
     * @param hi
     * @return
     */
    public static int partition(int[] ints, int lo, int hi) {

        int k = lo;
        int i = lo + 1;
        int j = hi;

        while (true) {
            while (ints[i] <= ints[k]) {//注意这里要有等于，否则遇到相同的数指针就不动了
                i++;
                if (i == hi) {
                    break;
                }
            }
            while (ints[j] >= ints[k]) {
                j--;
                if (j == lo) {
                    break;
                }
            }

            if (i >= j) {
                break;
            }
            exchange(ints, i, j);
        }
        exchange(ints, k, j);

        return j;

    }

    private static void exchange(int[] ints, int i, int j) {
        int n = ints[i];
        ints[i] = ints[j];
        ints[j] = n;
    }


    /**
     * 三向切分的快速排序
     * =====================
     *
     * ========优点==============
     * 能处理含有大量重复元素的数组
     */
    class Quick3Way{

    }
}
