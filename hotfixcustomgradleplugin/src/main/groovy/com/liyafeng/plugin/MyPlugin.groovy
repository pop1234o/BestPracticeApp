package com.liyafeng.plugin;

import org.gradle.api.Plugin;
import org.gradle.api.Project;

public class MyPlugin implements Plugin<Project> {

    void apply(Project project) {
        System.out.println("========================");
        System.out.println("hello gradle plugin!");
        System.out.println("========================");
    }

    /**
     * 自定义gradle插件
     * 首先新建一个工程，里面只剩下 src/main 文件夹和build.gradle文件
     * 在build.gradle文件中使用两个插件，
     * apply plugin: 'groovy'这个插件帮我们生成自定义插件，打包成jar
     * apply plugin: 'maven' 这个插件帮我们将jar文件发布到我们定义的仓库
     *
     * 然后还要添加开发插件用的依赖
     * dependencies {
     * compile gradleApi()
     * compile localGroovy()
     * }
     * 接下来我们配置插件要上传的位置
     *
     * group='com.liyafeng.plugin' //配置名称
     *  version='1.0.0'

     *  uploadArchives {
     *      repositories {
     *          mavenDeployer {
     *              repository(url: uri('D:/repos'))
     *          }
     *      }
     *  }
     *
     * 然后再main下新建groovy文件夹，里面新建我们的文件夹（包名），com/liyafeng/plugin
     * 然后新建MyPlugin.groovy文件，创建一个MyPlugin类实现Plugin<Project>接口。
     * 编译的时候会执行apply方法，project对象代表整个Module，在这个方法中我们
     * 可以做自己定义的操作
     *
     * 然后我们在main下新建resources/META-INF/gradle-plugins文件夹。
     * 里面定义一个 [插件名称].properties 文件，插件名称我们自己随意取
     * 里面用implementation-class=com.liyafeng.plugin.MyPlugin 将自定义类
     * 加入到插件中
     *
     *
     *
     *
     * 然后我们点击Android studio 右侧的Gradle栏，找到我们的插件项目，展开
     * 有一个upload
     * 到此，我们自定义的gradle
     * ======================在其他
     * */
}