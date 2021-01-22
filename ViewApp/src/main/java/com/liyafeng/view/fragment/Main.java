package com.liyafeng.view.fragment;

import android.content.Context;

/**
 * Created by liyafeng on 2017/12/29.
 */

public class Main {

    /**
     * @param args
     * https://developer.android.google.cn/guide/components/fragments.html
     * 我们要用support-v4包下的FragmentManager 和Fragment
     * 然后 开启事务
     * add ,是将一个 片段 加入到布局中
     * 而replace是将布局中存在的一个Fragment替换为这个Fragment，如果没有，则无效果
     *
     * Fragment也可以后退，像Activity一样也有回退栈
     * -----------------------
     * Fragment本身就是一个View，只不过用FragmentActivity，和FragmentManager来赋予它生命周期
     * 这样就是包装了一层，使得我们可以复用代码
     *
     * ------------add replace attach--------------------
     * add后，只是将这个Fragment  addView到布局中，会盖在下面的Fragment上
     * detach相当于removeView，但是没有销毁，下次可以attach
     * replace方法相当于把现在container中的Fragment全都remove，然后将新的Fragment add进去
     *
     * 显示：add() replace() show() attach()
     * 隐藏：remove() hide() detach()
     *
     * replace() ,如果布局中已经有了fragment，那么走销毁方法 onPause-onDestroy ,而且会被回收
     * 新加入的fragment会走onCreate-onResume
     *
     *
     * --------------------------
     * 一个app主体可以用Fragment+viewpager+RadioButton，但是viewpager要加懒加载
     * 也可以Fragment+radioButton，这样适合没有滑动效果的主页框架
     *
     *
     * ==============fragment重叠问题=============='
     * https://www.jianshu.com/p/78ec81b42f92
     * 就是app在后台被销毁，然后销毁前保存了状态，等下次进来恢复，
     * 我们设置了hide，但是保存状态没有保存这个值，所有恢复的时候所有fragment都显示出来了
     * 解决方法就是在oncreate中将所有fragment都隐藏一次，而且要用tag来判断
     *
     *
     * ================FragmentPagerAdapter 和FragmentStatePagerAdapter区别 =============
     * https://www.jianshu.com/p/9687209cfd5f
     *
     * 而FragmentStatePagerAdapter 是用一个 ArrayList<Fragment>来存储所有的Fragment，从这里也可以看出这两者的区别，
     * FragmentPagerAdapter 适用于固定少量的Fragment。而FragmentStatePagerAdapter 较多Fragment的场景。
     *
     * 作者：johnnycmj
     * 链接：https://www.jianshu.com/p/9687209cfd5f
     * 来源：简书
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * ==================Fragment中嵌套Fragment 页面回收 后 恢复白屏问题=============
     * 子Fragment需要用 Fragment中 getChildFragmentManager()  来管理
     *
     */
    public static void main(String[] args) {
    }
}
