package com.liyafeng.view.flutter;

public class Flutter {

    /**
     * 下载sdk
     * https://flutter.cn/docs/get-started/install/macos
     * 解压，配置成环境变量。
     * 配置两个镜像 https://flutter.cn/community/china
     * 运行flutter doctor查看
     *
     * Android studio 安装flutter插件
     * 即可创建flutter工程了
     *
     * 创建后编译，报错，超时，因为有些java库需要下载  https://mirrors.tuna.tsinghua.edu.cn/help/flutter/
     * 在编译android项目时，flutter还会从 https://storage.googleapis.com/download.flutter.io 下载Java程序库，您可以在 Android 项目目录下的 build.gradle 中添加下面一行下载源，从而使用TUNA镜像。
     * allprojects {
     *     repositories {
     *         google()
     *         jcenter()
     *         maven { url 'https://mirrors.tuna.tsinghua.edu.cn/flutter/download.flutter.io' }
     *     }
     * }
     * ======编译时间长===
     * 删除build重新编译
     *
     * ==========编译报错=======
     * Error: Cannot run with sound null safety, because the following dependencies
     * don't support null safety:
     *
     *  - package:flutter_spinkit
     *  - package:webview_flutter
     *  - package:scan
     *  - package:fluttertoast
     *
     * For solutions, see https://dart.dev/go/unsound-null-safety
     *
     * https://stackoverflow.com/questions/64917744/cannot-run-with-sound-null-safety-because-dependencies-dont-support-null-safety
     * Run --> Edit Configurations --> Add Additional Run args --> --no-sound-null-safety
     *
     *
     * @param args
     */
    public static void main(String[] args) {

    }

    /**
     * void main() {
     *   runApp(MyApp());
     * }
     * MyApp继承 Weight
     *
     * StatelessWidget  StatefulWidget
     * 万物皆weight，获取数据，通知改变
     *   setState(() {
     *       _counter++;
     *   });
     * 重新调用 build 返回最新 Weight
     *
     * ===== 常见布局
     * https://flutter.cn/docs/development/ui/widgets/layout
     *
     * =====匿名函数
     * (){xx;}   index=>{}
     *
     * =====页面跳转
     *  Navigator.of(context).push(
     *                       new MaterialPageRoute(builder: (BuildContext context) {
     *                         return XXPage();//也是一个weight
     *                       }));
     *
     *
     */
    public void a1(){}

    /**
     * ==========MethodChannel
     * https://flutter.cn/docs/development/platform-integration/platform-channels
     *
     */
    void a1_1(){}

    /**
     * ========核心widget======
     * https://flutter.cn/docs/development/ui/widgets/layout
     *
     *
     */
    void a1_2(){}

    /**
     * ====视频播放====
     * 原生
     * https://flutter.cn/docs/cookbook/plugins/play-video
     * Flutter 团队提供了 video_player 插件。你可以使用 video_player 插件播放存储在本地文件系统中的视频或者网络视频。
     * https://pub.flutter-io.cn/packages/video_player
     *
     * 在 iOS 上，video_player 使用 AVPlayer 进行播放控制。在 Android 上，使用的是 ExoPlayer。
     *
     *
     */
    void a2(){}


    /**
     * ====ppt
     * 展示demo
     * 普通播放，
     * 讲解video_player概述，代码实现
     * 实现原理
     *
     * 讲解示例代码流程，

     * 讲解视频加载原理，源码解读
     *
     *
     *
     */
    void a3(){}


    /**
     * ====PageView=====
     * https://api.flutter-io.cn/flutter/widgets/PageView-class.html
     * https://blog.csdn.net/mengks1987/article/details/104570500 Flutter Widgets 之 PageView
     *
     *
     */
    void a4(){}


}
