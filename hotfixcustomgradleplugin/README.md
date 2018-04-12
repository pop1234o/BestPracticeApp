
#自定义gradle插件 
首先新建一个工程，里面只剩下 src/main 文件夹和build.gradle文件
在build.gradle文件中使用两个插件，
```
apply plugin: 'groovy'//这个插件帮我们生成自定义插件，打包成jar
apply plugin: 'maven' //这个插件帮我们将jar文件发布到我们定义的仓库

//然后还要添加开发插件用的依赖
dependencies {
    compile gradleApi() 
    compile localGroovy()
}
//接下来我们配置插件要上传的位置

group='com.liyafeng.plugin' //配置名称
version='1.0.0'
    
 uploadArchives {
     repositories {
         mavenDeployer {
             //这里我们上传到本地D盘
             repository(url: uri('D:/repos'))
         }
     }
 }
```
然后再main下新建groovy文件夹，里面新建我们的文件夹（包名），com/liyafeng/plugin
然后新建MyPlugin.groovy文件，创建一个MyPlugin类实现Plugin<Project>接口。
编译的时候会执行apply方法，project对象代表整个Module，在这个方法中我们
可以做自己定义的操作

然后我们在main下新建resources/META-INF/gradle-plugins文件夹。
里面定义一个 [插件名称].properties 文件，插件名称我们自己随意取
里面用implementation-class=com.liyafeng.plugin.MyPlugin 将自定义类
加入到插件中


然后我们点击Android studio 右侧的Gradle栏，找到我们的插件项目，展开
有一个upload->uploadArchives，双击一下，就可以将打包好的jar上传到D盘了

我们到D盘中repos目录下，com/liyafeng/plugin/[ModuleName]/version目录下就能看到jar包了

到此，我们自定义的gradle的插件就在D盘这个仓库中了。

#在其他项目中引用我们自定义的插件

只要在其他项目的build.gradle配置我们要引用的仓库和插件即可
将下面代码加入到Module的build.gradle的最前面或者最后面都行。
```
buildscript {
    repositories {
        maven {//本地Maven仓库地址
            url uri('D:/repos')
        }
    }
    dependencies {
        //格式为-->group:module:version
        classpath 'com.liyafeng.plugin:hotfixcustomgradleplugin:1.0.0'
    }
}
//com.liyafeng.gradle为resources/META-INF/gradle-plugins下的properties文件名称
apply plugin: 'com.liyafeng.customplugin'

```
接下来我clean project，然后make module，
在Android Studio最下面的Gradle Console框中就能看到执行了我们的MyPlugin中的代码了
   