package com.liyafeng.architecture;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

/**
 * Created by liyafeng on 2017/12/27.
 */

public class UserProfileViewModel extends ViewModel {


    private final UserRepository userRepository;
    LiveData<User> userLiveData;

    public UserProfileViewModel() {
        userRepository = new UserRepository();
    }

    public LiveData<User> getUser(long userId) {
        return userRepository.getUser(userId);
    }



}
