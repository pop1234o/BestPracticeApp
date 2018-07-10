package com.liyafeng.performance;

import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity {

    /**
     * 1,千万不要在循环中使用+操作字符串，会产生大量String对象，要用StringBuilder
     * 2.不要在循环中使用HasMap来存储或者读取，key是int ，long 的类型，否则大量的装箱
     * 会产生大量的Integer， Long类型，占用内存，要用SparseArray代替
     * 3.不要在循环中创建大量对象，我们可以用缓冲池的形式来处理
     * 4.尽量避免轮询（自旋），可以用观察者模式来解决
     * <p>
     * <p>
     * =================monkey 压测=============
     * https://developer.android.google.cn/studio/test/monkey.html
     * <p>
     * money有参数， 主要`用来约束包，页面，控制事件数量
     * <p>
     * adb shell ps | awk '/com\.android\.commands\.monkey/ { system("adb shell kill " $2) }'
     * <p>
     * //次数
     * adb shell monkey -p your.package.name -v 500
     * <p>
     * adb shell monkey -p com.qusukj.baoguan  -v 250000 --pct-trackball 100%
     * <p>
     * <p>
     * 停止monkey
     * adb shell ps|grep monkey返回的第一个数字就是monkey的进程号
     * 2. adb shell kill [进程号]
     * <p>
     * ========================anr=================
     * <p>
     * http://duanqz.github.io/2015-10-12-ANR-Analysis
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        StringBuilder

    }

    /**
     * 使用Android Profiler 进行内存占用分析，cpu耗时分析，网络流量分析
     * 使用MAT查看内存泄漏，还可以查看内存中大对象
     *
     *
     * */
}
