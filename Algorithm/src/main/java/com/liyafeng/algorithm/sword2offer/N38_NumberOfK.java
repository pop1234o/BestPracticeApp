package com.liyafeng.algorithm.sword2offer;

/**
 * Created by liyafeng on 2018/2/23.
 */

public class N38_NumberOfK {

    /**
     * 在一个有序的数组中找出数字k出现的次数
     * =============思路============
     * 这是一个查找题，既然数组有序，那么最好的方法是二分法查找
     * 但是我们需要知道第一个k和最后一个k出现的位置，这就需要对二分查找进行修改
     * 找到前面不是k的那个k,找到后面不是k的那个k，两个k的index相减加一就是k的个数
     * ==========考点===========
     * 知识迁移能力，加强版的二分法查找
     * @param args
     */
    public static void main(String[] args) {

        int[] array = {1, 2, 3, 4, 4, 4, 4, 4, 4, 5, 5, 6, 6, 7, 7, 8, 9, 10};
        int count = findKCount(array, 4);
        System.out.println("k count :"+count);
    }

    private static int findKCount(int[] array, int k) {

        int start = binarySearch(array, k, new IEquals() {
            @Override
            public int isEquals(int[] array, int mid, int k) {
                if (array[mid - 1] == k) {
                    return -1;
                }
                return 0;
            }
        });
        int end = binarySearch(array, k, new IEquals() {
            @Override
            public int isEquals(int[] array, int mid, int k) {
                if (array[mid + 1] == k) {
                    return 1;
                }
                return 0;
            }
        });
        return end-start+1;
    }

    private static int binarySearch(int[] array, int k, IEquals iEquals) {
        int start = 0;
        int end = array.length - 1;
        while (start <= end) {
            int mid = (start + end) >>> 1;
            if (k < array[mid]) {
                end = mid - 1;
            } else if (k > array[mid]) {
                start = mid + 1;
            } else {
                int equals = iEquals.isEquals(array, mid, k);
                if (equals == 0) {
                    return mid;
                } else if (equals > 0) {
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            }

        }
        return ~start;
    }

    interface IEquals {
        int isEquals(int[] array, int mid, int k);
    }
}
