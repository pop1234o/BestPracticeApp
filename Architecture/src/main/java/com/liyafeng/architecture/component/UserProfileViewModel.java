package com.liyafeng.architecture.component;

//import android.arch.lifecycle.LiveData;
//import android.arch.lifecycle.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * Created by liyafeng on 2017/12/27.
 */

public class UserProfileViewModel extends ViewModel {


    LiveData<User> users;

    public UserProfileViewModel() {
    }

    public LiveData<User> getUser(long userId) {

        //这个判断，如果user已经set过数据，那么下次observe的时候还会返回那个数据
        if (users == null) {
            users = new MutableLiveData<User>();
            //模拟网络请求
            new android.os.Handler().post(new Runnable() {
                @Override
                public void run() {
                    users.setValue(new User());
                }
            });
        }

        return users;
    }


}
