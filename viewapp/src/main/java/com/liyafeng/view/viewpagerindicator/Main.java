package com.liyafeng.view.viewpagerindicator;

/**
 * Created by liyafeng on 2018/1/4.
 */

public class Main {

    /**
     * 我们可以用j神的viewpagerIndicator
     *
     * 当然还可以用support-v4中的PagerTabStrip (这个效果太差了，title还会滑动)
     * https://developer.android.google.cn/reference/android/support/v4/view/PagerTabStrip.html
     *
     * 还有support-design中的TabLayout (这个用着还行，能实现tab连续滑动的效果，使用也简单)
     * https://developer.android.google.cn/reference/android/support/design/widget/TabLayout.html
     *  compile 'com.android.support:design:25.0.0'
     *
     *  https://www.jianshu.com/p/2b2bb6be83a8
     *
     * 这个只要重写adapter。getPageTitle即可
     * xmlns:app="http://schemas.android.com/apk/res-auto"
     *  app:tabSelectedTextColor="@color/mobile_yellow"
     *  app:tabTextColor="#fff"
     *  app:tabIndicatorColor="@color/mobile_yellow"
     *  app:tabIndicatorHeight="3dp"
     * ---------------------------------------
     *
     * 或者MagicIndicator，这个效果比较全
     *
     * @param args
     */
    public static void main(String[] args) {

    }
}
