package com.liyafeng.architecture.component;

//import android.arch.lifecycle.*;
//import android.arch.lifecycle.MutableLiveData;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

/**
 * Created by liyafeng on 2017/12/27.
 * 负责网络请求和数据库
 */

public class UserRepository {


    public LiveData<User> getUser(long userId) {
        //模拟网络请求和数据库请求
        final MutableLiveData<com.liyafeng.architecture.component.User> userLiveData = new MutableLiveData<>();
        new android.os.Handler().post(new Runnable() {
            @Override
            public void run() {
                userLiveData.setValue(new User());
            }
        });
        return userLiveData;

    }

    public interface DataCallback<T> {
        void onSuccess(T data);

        void onFailure(String msg);
    }
}
