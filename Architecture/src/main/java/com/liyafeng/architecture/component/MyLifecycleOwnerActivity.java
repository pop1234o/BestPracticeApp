package com.liyafeng.architecture.component;

import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;

/**
 * 自定义 LifecycleOwner ，向前兼容
 *
 *
 *
 */
public class MyLifecycleOwnerActivity implements LifecycleOwner {


    private LifecycleRegistry lifecycleRegistry;

    public void onCreate() {
        lifecycleRegistry = new LifecycleRegistry(this);
        lifecycleRegistry.markState(Lifecycle.State.CREATED);
    }

    public void onStart() {
        lifecycleRegistry.markState(Lifecycle.State.STARTED);
    }

    @NonNull
    @Override
    public Lifecycle getLifecycle() {
        return lifecycleRegistry;
    }
}
