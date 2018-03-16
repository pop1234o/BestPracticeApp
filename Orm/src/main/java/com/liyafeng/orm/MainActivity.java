package com.liyafeng.orm;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.liyafeng.orm.db.MyDBHelper;

import org.greenrobot.greendao.database.Database;

import java.util.Date;
import java.util.List;

public class MainActivity extends Activity {

    private DaoSession daoSession;
    private DaoMaster daoMaster;

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

        daoMaster = new DaoMaster(openHelper.getWritableDb());
        //一个DaoSession代表一次任务，如果修改了实体类，但是没有修改库，那么再次查询出来的
        //实体就是修改过的，而不是数据库中真正的，所以要newSession才行
        daoSession = daoMaster.newSession();
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doSome();
            }
        });
        findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doSome1();
            }
        });
        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doSome2();
            }
        });


    }

    private void doSome2() {
        UserDao userDao = daoSession.getUserDao();
        Database database = daoSession.getDatabase();
        database.beginTransaction();
        List<User> users = userDao.loadAll();
        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            if(i==0){
                user.setNumber(345);
                userDao.update(user);
            }
        }
//        database.setTransactionSuccessful();
        database.endTransaction();
    }

    private void doSome1() {
        DaoSession daoSession = daoMaster.newSession();
        UserDao userDao = daoSession.getUserDao();
        List<User> users = userDao.loadAll();
        for (User u : users) {
            Log.i("test", "onCreate: " + u.toString());
        }
    }

    private void doSome() {

        UserDao userDao = daoSession.getUserDao();


        User user = new User();
        user.setName("pop");
        user.setNumber(123);
        user.setDate(new Date(System.currentTimeMillis()));
//        userDao.insert(user);
        userDao.insertOrReplace(user);


    }
}
