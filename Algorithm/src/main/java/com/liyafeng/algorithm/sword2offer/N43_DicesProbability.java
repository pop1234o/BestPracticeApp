package com.liyafeng.algorithm.sword2offer;

import java.util.HashMap;

/**
 * Created by liyafeng on 2018/2/26.
 */

public class N43_DicesProbability {

    /**
     * 将n个骰子抛出，和为s，求出所有s出现的概率
     * ===========思路================
     * 遍历出所有可能，每种可能出现次数/总次数 就是概率，但时间复杂度高
     * 所以这种肯定是找规律的
     *
     * ===========考点=================
     * 抽象建模的过程
     *
     * @param args
     */
    public static void main(String[] args) {

       HashMap<Integer,Integer> map =  getProbability(3);
    }

    private static HashMap<Integer, Integer> getProbability(int n) {
        return null;

    }
}
