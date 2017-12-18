package com.liyafeng.orm;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

import com.liyafeng.orm.db.MyDBHelper;

import java.util.Date;
import java.util.List;

public class MainActivity extends Activity {

    /**
     * Android自带的数据库框架，SqlLiteOpenHelper
     * 但是我们经常需要 将 object 和 表中的行 互转，这就是orm  object relational mapping
     * 所以就诞生的orm框架，他底层用的还是Android自带的数据库类，但是上层做了封装
     * 封装好的东西用起来就是爽，少了很多重复的操作
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        MyDBHelper dbHelper  = new MyDBHelper(this, "db_name", null, 1);
//        SQLiteDatabase writableDatabase = dbHelper.getWritableDatabase();
//        writableDatabase.execSQL("select * from user");
//
//

        DaoMaster.DevOpenHelper openHelper = new DaoMaster.DevOpenHelper(this, "db_user");

        DaoMaster daoMaster = new DaoMaster(openHelper.getWritableDb());
        DaoSession daoSession = daoMaster.newSession();
        UserDao userDao = daoSession.getUserDao();


        User user = new User();
        user.setName("pop");
        user.setId(0L);
        user.setDate(new Date(System.currentTimeMillis()));
//        userDao.insert(user);
        userDao.insertOrReplace(user);

        List<User> users = userDao.loadAll();
        for (User u : users) {
            Log.i("test", "onCreate: " + u.getId());
        }
    }
}
