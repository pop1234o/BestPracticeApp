package com.liyafeng.plugin

import com.android.build.gradle.AppExtension
import com.android.build.gradle.AppPlugin
import com.android.build.gradle.api.ApplicationVariant
import org.gradle.api.Action;
import org.gradle.api.Plugin;
import org.gradle.api.Project
import org.gradle.api.Task;

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



        System.out.println("=====*=======开始执行==============");
        //注册自定义的Extension
        //Extension with name 'custom_extension' does not exist. Currently registered extension names: [ext, defaultArtifacts, reporting, buildOutputs, android]
        //这个必须写在这里，要不build.gradle中不能使用custom_extension{}
        project.extensions.create("custom_extension", CustomExtension);

        project.getTasks().create("myTask", {
            System.out.println("======*======执行==============");
            def name = project.extensions.getByName("custom_extension").name;
            System.out.println("执行的" + name);
        }).doLast {
            println "执行完毕";
        };

        //我们可以用自定义插件生成代码.java文件
//        createJava(project);


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
        def file = project.getBuildDir();
        System.out.println("生成java文件" + file.getAbsolutePath());

        def out = new File(file, "CustomClass.java");
        if (!out.exists()) {
            out.createNewFile();
        }
        out.write(content);
    }


    public static class MyPlguinTestClass {
        def str = "default";
    }


    public static class CustomExtension {
        def name = "default name";
    }
}