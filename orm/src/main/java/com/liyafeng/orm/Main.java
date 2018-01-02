package com.liyafeng.orm;

/**
 * Created by liyafeng on 2018/1/2.
 */

public class Main {

    /**
     * @param args
     * 我们在gradle添加 编译插件
     * 然后添加version声明，这个会自动生成代码中体现
     * 然后定义一个Bean，然后加上@Entity注解
     * build-》make project，这样就在build-》generated-》source-》greendao中
     * 生成DaoMaster，DaoSession，XXXDao，这几个文件
     * 我们要自己定义 MyOpenHelper继承DaoMaster.OpenHelper ，复写onUpgrade()
     * 然后我们在Application中新建 MyOpenHelper对象，getWritableDb()
     * new DaoMaster(db).newSession();然后我们将DaoSession写个getter
     * 接下来就可以使用了
     * ----------------------
     * greendao的原理还是用的原生的数据库框架，只不过为你自动生成了代码
     *
     */
    public static void main(String[] args) {

    }
}
