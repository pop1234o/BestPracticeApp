package com.liyafeng.plugin

import com.android.build.gradle.AppExtension
import com.android.build.gradle.AppPlugin
import com.android.build.gradle.api.ApplicationVariant;
import org.gradle.api.Plugin;
import org.gradle.api.Project;

public class MyPlugin implements Plugin<Project> {

    void apply(Project project) {
        System.out.println("========================");
        System.out.println("hello gradle plugin!");
        System.out.println("========================");

        //注册一个Transform，我们自定义的Transform，这样在编译的时候就
        //可以执行里面的代码了

        //这里的Extension代表一个闭包，就是build.gradle中的 {}
        //这里的AppExtension代表 android{}
        def android = project.extensions.getByType(AppExtension)
        def classTransform = new MyClassTransform(project);
        android.registerTransform(classTransform);

        //我们可以用自定义插件生成代码.java文件

        createJava(project);


    }

    void createJava(Project project) {
        System.out.println("========开始生成java文件========");

        def android = project.extensions.getByType(AppExtension)

        project.extensions.create("myCustomExtension", MyPlguinTestClass);

        //AppPlugin是android插件里的一个插件
//        if(project.plugins.hasPlugin(AppPlugin)){
//
//        }

        def content = """
               package com.liyafeng;
               class CustomClass{
                    String str ="这个是生成的类";
               }
            """;
        def file = project.getBuildFile();
        def out = new File(file, "CustomClass.java");
        if (!out.exists()) {
            out.createNewFile();
        }
        out.write(content);
    }


    public static class MyPlguinTestClass {
        def str = "默认值";
    }

}