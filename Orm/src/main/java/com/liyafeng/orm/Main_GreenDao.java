package com.liyafeng.orm;

/**
 * Created by liyafeng on 2018/1/2.
 */

public class Main_GreenDao {

    /**
     * @param args 我们在gradle添加 编译插件
     *             然后添加version声明，这个会自动生成代码中体现
     *             然后定义一个Bean，然后加上@Entity注解
     *             build-》make project，这样就在build--》generated-》source-》greendao中
     *             生成DaoMaster，DaoSession，XXXDao，这几个文件
     *             我们要自己定义 MyOpenHelper继承DaoMaster.OpenHelper ，复写onUpgrade()
     *             然后我们在Application中新建 MyOpenHelper对象，getWritableDb()
     *             new DaoMaster(db).newSession();然后我们将DaoSession写个getter
     *             接下来就可以使用了
     *             ----------------------
     *             greendao的原理还是用的原生的数据库框架，只不过为你自动生成了代码
     *             <p>
     *             -----------------------------
     *             官方文档
     *             http://greenrobot.org/greendao
     *             http://greenrobot.org/greendao/documentation/   //文档
     *             http://greenrobot.org/greendao/documentation/modelling-entities/
     *             https://github.com/greenrobot/greenDAO
     *             <p>
     *             <p>
     *             ==============加密=============
     *             https://blog.csdn.net/qq_33689414/article/details/52304819
     *             compile 'net.zetetic:android-database-sqlcipher:3.5.1'
     *             <p>
     *             ===============kotlin中的greendao=====
     *             <p>
     *             我们需要在app的gradle中配置,否则会报找不到DaoSession的错误
     *             android{
     *             ...
     *             sourceSets {
     *             main.java.srcDirs += 'build/generated/source/greendao'
     *             }
     *             }
     */
    public static void main(String[] args) {

        /*
        * //混淆
        * #greendao
-keepclassmembers class * extends org.greenrobot.greendao.AbstractDao {
public static java.lang.String TABLENAME;
}
-keep class **$Properties

# If you do not use SQLCipher:
-dontwarn org.greenrobot.greendao.database.**
# If you do not use Rx:
-dontwarn rx.**
        *
        * */
    }
}
