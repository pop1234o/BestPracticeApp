package com.liyafeng.practice;

import android.content.Context;

/**
 * Created by liyafeng on 16/11/2017.
 */

public class ComputerBasic {


    //region 计算机编码

    /**
     * 讲一下常见编码方式？
     * http://www.ruanyifeng.com/blog/2007/10/ascii_unicode_and_utf-8.html
     * https://www.zhihu.com/question/23374078/answer/24385963
     */
    public void a1(Context context) {
        /*
         * 8位一个字节(byte)，那么8位可以表示2^8=256种可能
         * ==========================================
         * ASCII 码
         * 最早由美国创造，只用了前7位，第一位为0，所以ascii能表示128种字符
         *
         * Unicode是字符集，Utf-8是编码规则，两者是不同的东西
         * Unicode字符集
         * http://www.chi2ko.com/tool/CJK.htm
         * ===================================
         * Utf-8编码  Utf-16编码
         * 编码规则见下图
         * 为什么要有编码规则？是因为你不知道 32位是表示一个字符，还是四个字符，
         * 因为32位都表示一个字符，那么英文的存储就很浪费空间
         * ==================================
         * gb2312 和gbk
         *
         */
        context.getResources().getDrawable(R.drawable.unicode);
    }
    //endregion

}
