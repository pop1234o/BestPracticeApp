package com.liyafeng.architecture.component;

import android.arch.lifecycle.*;
import android.arch.lifecycle.MutableLiveData;

/**
 * Created by liyafeng on 2017/12/27.
 */

public class UserRepository {



    public LiveData<User> getUser(long userId) {

        final MutableLiveData<User> mutableLiveData = new MutableLiveData<User>();
        new android.os.Handler().post(new Runnable() {
            @Override
            public void run() {
                mutableLiveData.setValue(new User());
            }
        });
        return mutableLiveData;
    }
}
