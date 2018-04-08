package com.liyafeng.hotfix;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.lang.reflect.Array;
import java.lang.reflect.Field;

import dalvik.system.DexClassLoader;
import dalvik.system.PathClassLoader;

/**
 * Created by liyafeng on 2018/4/8.
 */

public class DexInsertHotFix {

    /**
     * 首先我们将修复后的BugClass.java ，进行编译成class文件，
     * javac -cp或者-classpath <android.jar目录> <xxx.java目录>
     *  编译完成后
     *  我们可以查看class文件，用javap -c <xxx.class目录> 里面是反汇编的字节码
     *
     *
     * @param args
     */
    public static void main(String[] args) {

    }

    public static void loadFix(Context context) throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException {
        File directory = Environment.getExternalStorageDirectory();
        File dexFile = new File(directory, "classes.dex");

        if(!dexFile.exists()){
            Log.i("test", "补丁不存在");
            return;
        }
        File optimizedDirectory = new File(context.getFilesDir(), "optimizedDirectory");
        if(!optimizedDirectory.exists()){
            optimizedDirectory.mkdirs();
        }
        //系统的类加载器
        PathClassLoader pathClassLoader = (PathClassLoader) context.getClassLoader();
        //创建我们补丁的类加载器
        DexClassLoader dexClassLoader = new DexClassLoader(dexFile.getAbsolutePath(), optimizedDirectory.getAbsolutePath(), null, pathClassLoader);

        Field pathList = Class.forName("dalvik.system.BaseDexClassLoader").getDeclaredField("pathList");
        pathList.setAccessible(true);

        //获取两个classLoader的pathList的成员变量(DexPathList.java)
        Object pathPathList = pathList.get(pathClassLoader);
        Object dexPathList = pathList.get(dexClassLoader);


        Field dexElements = Class.forName("dalvik.system.DexPathList").getDeclaredField("dexElements");
        dexElements.setAccessible(true);

        //(DexPathList.java中的Element[]数组，这个是个内部类)
        Object leftDexElements = dexElements.get(dexPathList);
        Object rightDexElements = dexElements.get(pathPathList);


        Class<?> componentType = leftDexElements.getClass().getComponentType();
        int i = Array.getLength(leftDexElements);// 得到左数组长度（补丁数组）
        int j = Array.getLength(rightDexElements);// 得到原dex数组长度
        int k = i + j;// 得到总数组长度（补丁数组+原dex数组）
        Object result = Array.newInstance(componentType, k);// 创建一个类型为componentType，长度为k的新数组
        System.arraycopy(leftDexElements, 0, result, 0, i);
        System.arraycopy(rightDexElements, 0, result, i, j);


        //将新的 Element[] 赋值到 PathDexClassLoader 对象中的pathList,中的 Element[] dexElements;

        Object pathPathList_1 = pathList.get(pathClassLoader);//获取 (DexPathList)对象

        //将新的Element[] 赋值到 PathDexClassLoader->DexPathList->Element[]中
        dexElements.set(pathPathList_1,result);
        Log.i("test", "合并完成");
    }
}
