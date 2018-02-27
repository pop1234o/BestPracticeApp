package com.liyafeng.algorithm.sword2offer;

import com.liyafeng.algorithm.basic.sort.Util;

import java.util.HashMap;

/**
 * Created by liyafeng on 2018/2/26.
 */

public class N44_ContinousCards {

    /**
     * 从一个扑克牌中抽出5张牌，判断是不是顺子。大小王是0，代表任意
     * ===========思路================
     * 其实就是判断0-13中抽取5个数，0可以出现两次，判断是不是连续的
     * 我们可以统计出0的个数，然后再统计出非0中间空隙的大小，
     * 0的个数要>=空隙的个数说明的对子
     * 如果不是这样，那么我们要判断很多条件，比如下一个数是差1，是连续的
     * 差2可以用0补，差3必须用两个00补，否则不是顺子，大于3肯定不是顺子
     * 所以还是判断空隙大小简单
     * ===========考点=================
     * 抽象建模的过程，把扑克转化为整型数组
     *
     * @param args
     */
    public static void main(String[] args) {

        int[] n = {0, 1, 3, 4, 5};
        boolean b = isContinue(n);
        System.out.println("is continue:" + b);
    }

    private static boolean isContinue(int[] n) {
        sort(n);
        Util.print(n);

        int countOfZore = 0;
        for (int i = 0; i < n.length; i++) {
            if (n[i] == 0) {
                countOfZore++;
            } else {
                break;
            }
        }

        int gap = 0;
        for (int i = countOfZore; i < n.length - 1; i++) {
            gap += (n[i + 1] - n[i] - 1);
        }


        return countOfZore >= gap;
    }

    private static void sort(int[] n) {
        for (int i = 0; i < n.length - 1; i++) {
            for (int j = i + 1; j > 0 && Util.less(n, j, j - 1); j--) {
                Util.exchange(n, j, j - 1);
            }
        }
    }

}
