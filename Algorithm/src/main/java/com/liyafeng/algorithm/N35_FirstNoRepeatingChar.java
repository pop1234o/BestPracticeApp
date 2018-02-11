package com.liyafeng.algorithm;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by liyafeng on 2018/2/10.
 */

public class N35_FirstNoRepeatingChar {

    /**
     * 求一个字符串中第一个只出现一次的字符
     * =========================
     * 遍历字符串，依次计数，然后再遍历一遍找出第一个出现的字符
     *
     * @param args
     */
    public static void main(String[] args) {
        String s = "asdfaxsghgfhsdfv";
        char c = getFirstNoRepeatingChar(s);
        System.out.println("FirstNoRepeatingChar:  " + c);
    }

    private static char getFirstNoRepeatingChar(String s) {
        char[] chars = s.toCharArray();
        HashMap<Character, Counter> map = new HashMap<>();
        for (int i = 0; i < chars.length; i++) {
            Counter counter = map.get(chars[i]);
            if (counter == null) {
                counter = new Counter();
                map.put(chars[i], counter);
            }
            counter.count++;
        }

        Iterator<Map.Entry<Character, Counter>> iterator = map.entrySet().iterator();
        char c = ' ';
        int minNumber = -1;
        //找出map中最先创建的那个
        while (iterator.hasNext()) {
            Map.Entry<Character, Counter> entry = iterator.next();
            Counter counter = entry.getValue();
            if (counter.count == 1) {
                if (minNumber == -1) {
                    minNumber = counter.innerNumber;
                    c = entry.getKey();
                } else {
                    if (counter.innerNumber < minNumber) {
                        minNumber = counter.innerNumber;
                        c = entry.getKey();
                    }
                }
            }
        }
        return c;

    }

    static class Counter {
        int count;
        static int number = 0;

        int innerNumber;

        public Counter() {
            innerNumber = number++;
        }
    }
}
