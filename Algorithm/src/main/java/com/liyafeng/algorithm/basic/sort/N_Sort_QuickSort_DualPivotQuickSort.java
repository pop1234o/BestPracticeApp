package com.liyafeng.algorithm.basic.sort;

import java.util.Arrays;

public class N_Sort_QuickSort_DualPivotQuickSort {

    /**
     * 双轴快速排序（时间复杂度 O(n log n)）
     * ==============原理==================
     *
     *
     * @param args
     */
    public static void main(String[] args) {

        int[] ints = {6, 1, 2, 5, 4, 8};
        Arrays.sort(ints);
        for (int i = 0; i < ints.length; i++) {
            System.out.print(ints[i]+" ");
        }
    }

    public static void sort(int[] ints, int lo, int hi) {


    }



    public class QuickSortDualPivot {

        public void sort (int[] input){
            //input=shuffle(input);
            sort (input, 0, input.length-1);
        }

        private void sort(int[] input, int lowIndex, int highIndex) {

            if (highIndex<=lowIndex) {
                return;
            }

            int pivot1=input[lowIndex];
            int pivot2=input[highIndex];

            if (pivot1>pivot2){
                Util.exchange(input, lowIndex, highIndex);
                pivot1=input[lowIndex];
                pivot2=input[highIndex];
                //sort(input, lowIndex, highIndex);
            }
            else if (pivot1==pivot2){
                while (pivot1==pivot2 && lowIndex<highIndex){
                    lowIndex++;
                    pivot1=input[lowIndex];
                }
            }

            int i=lowIndex+1;
            int lt=lowIndex+1;
            int gt=highIndex-1;

            while (i<=gt){

                if (input[i]< pivot1){
                    Util.exchange(input, i++, lt++);
                }
                else if (pivot2<input[i]){
                    Util.exchange(input, i, gt--);
                }
                else{
                    i++;
                }

            }

            Util.exchange(input, lowIndex, --lt);
            Util.exchange(input, highIndex, ++gt);

            sort(input, lowIndex, lt-1);
            sort (input, lt+1, gt-1);
            sort(input, gt+1, highIndex);

        }

    }
}
