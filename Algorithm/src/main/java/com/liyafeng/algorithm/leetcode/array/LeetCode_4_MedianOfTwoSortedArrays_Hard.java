package com.liyafeng.algorithm.leetcode.array;

/**
 * Created by liyafeng on 2018/3/2.
 */

public class LeetCode_4_MedianOfTwoSortedArrays_Hard {

    /**
     * 两个排序数组的中位数
     * There are two sorted arrays nums1 and nums2 of size m and n respectively.
     * <p>
     * Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
     * <p>
     * Example 1:
     * nums1 = [1, 3]
     * nums2 = [2]
     * <p>
     * The median is 2.0
     * Example 2:
     * nums1 = [1, 2]
     * nums2 = [3, 4]
     * <p>
     * The median is (2 + 3)/2 = 2.5
     *
     * @param args
     */
    public static void main(String[] args) {

//        int[] num1 = {1, 3};
//        int[] num2 = {2};

        int[] num1 = {1};
        int[] num2 = {3};
        double medianSortedArrays = findMedianSortedArrays(num1, num2);
        System.out.println("medianSortedArrays:" + medianSortedArrays);
    }

    /**
     * 这个思路就是归并排序，当排序好的数组大小是总大小的一半的时候
     * 根据奇偶区分取中位的元素。
     * 时间复杂度是O(m+n)
     * 这个算法的缺点是需要在while中进行判断，所以还不如全都排序完再取值
     * @param nums1
     * @param nums2
     * @return
     */
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int length_1 = nums1.length;
        int length_2 = nums2.length;
        int length = length_1 + length_2;
        boolean isEven = length % 2 == 0;
        int findIndex = length / 2;

        int[] ints = new int[length];
        int index1 = 0, index2 = 0, n = 0;
        while (index1 < length_1 || index2 < length_2) {

            if (index1 >= length_1) {
                ints[n++] = nums2[index2++];
            } else if (index2 >= length_2) {
                ints[n++] = nums1[index1++];
            } else {
                if (nums1[index1] <= nums2[index2]) {
                    ints[n++] = nums1[index1++];
                } else {
                    ints[n++] = nums2[index2++];
                }
            }


            if (isEven) {
                if (n == findIndex + 1) {
                    return (ints[n - 1] + ints[n - 2]) / 2.0f;
                }
            } else {
                if (n == findIndex + 1) {//已经归并的数组大小
                    return ints[n - 1];
                }
            }

        }
        return 0;
    }


    /**
     *  这个思路是归并排序，然后取中值即可
     *
     * 知识点：数组，归并排序
     */
    class Solution {
        /**

         * @param nums1
         * @param nums2
         * @return
         */
        public double findMedianSortedArrays(int[] nums1, int[] nums2) {
            if (nums1 == null || nums1.length == 0) {
                return findMedianSortedArrays(nums2);
            }
            if (nums2 == null || nums2.length == 0) {
                return findMedianSortedArrays(nums1);
            }
            int l1 = nums1.length, l2 = nums2.length;
            int l = l1 + l2;//length1 ,length2
            int[] arr = new int[l];
            int p = l1 - 1, q = l2 - 1, i = l - 1;
            while (p >= 0 && q >= 0) {
                if (nums1[p] > nums2[q]) {
                    arr[i--] = nums1[p--];
                } else {
                    arr[i--] = nums2[q--];
                }
            }
            while (p >= 0) {
                arr[i--] = nums1[p--];
            }
            while (q >= 0) {
                arr[i--] = nums2[q--];
            }
            if (l % 2 != 0) {
                return arr[l / 2];
            } else {
                return (arr[l / 2 - 1] + arr[l / 2]) / 2.0;
            }

        }

        public double findMedianSortedArrays(int[] arr) {
            int l = arr.length;
            if (l % 2 != 0) {
                return arr[l / 2];
            } else {
                return (arr[l / 2 - 1] + arr[l / 2]) / 2.0;
            }
        }
    }
}
