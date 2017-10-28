package com.liyafeng.modularization;

import android.app.Activity;
import android.os.Bundle;

/**
 * 模块化开发（或者叫组件化）
 * 当然有的人将分包叫模块化
 *
 * 多个module叫组件化
 *
 * 为什么要组件化？
 * 编译快，
 * 耦合低，项目大了容易形成耦合地狱，这样你动一个模块的时候就要改动很多东西
 * 多人员开发冲突少
 * ==================
 * 需要解决的问题？
 * 如何debug时作为app，而打包时作为library？
 *
 * 组件之间如何通信？
 *
 * 组件之间如何跳转页面？
 *
 * 共用资源如何放置？
 *
 * library的R不是final（怕和主app冲突）
 * =================
 *   app
 *   业务组件
 *   功能组件（包括 日志，common）
 *   第三方依赖
 *
 *
 *
 */
public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
