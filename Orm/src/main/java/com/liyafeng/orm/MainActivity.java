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
        //如果是修改，那么不同的session会有数据不同步的问题，所以我们session是全局单例的
        //总之newSession肯定能获取到最新的，但是两个session之间就不能读取最新修改的
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
                newSessionSearch();
            }
        });
        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update();
            }
        });


        findViewById(R.id.button3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                oldSessionSearch();
            }
        });
    }

    private void update() {
        UserDao userDao = daoSession.getUserDao();
//        UserDao userDao = daoMaster.newSession().getUserDao();
        List<User> users = userDao.loadAll();
        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            if(i==0){
                user.setNumber(345);
                userDao.update(user);
            }
        }
    }

    private void newSessionSearch() {
        DaoSession daoSession = daoMaster.newSession();
        UserDao userDao = daoSession.getUserDao();
        List<User> users = userDao.loadAll();
        for (User u : users) {
            Log.i("test", "onCreate: " + u.toString());
        }
    }
    private void oldSessionSearch() {
//        daoSession.clear();//如果要获取最新的要调用这个方法，这里有个hashmap<WeakReference>
        //里面缓存的数据，所以我们取出来是内存的
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
