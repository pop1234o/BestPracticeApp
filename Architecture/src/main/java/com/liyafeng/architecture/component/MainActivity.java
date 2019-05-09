package com.liyafeng.architecture.component;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.Observer;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.lifecycle.ViewModelProviders;

import com.liyafeng.architecture.R;

import static androidx.lifecycle.Lifecycle.State.STARTED;

public class MainActivity extends AppCompatActivity {

    /**
     * ===========ViewModel概览================
     * https://developer.android.com/topic/libraries/architecture/viewmodel.html
     * The ViewModel class is designed to store and manage UI-related data in a lifecycle conscious way.
     * The ViewModel class allows data to survive configuration changes such as screen rotations.
     * <p>
     * 存储和管理ui相关的数据，通过有生命周期行为的方式，当 configuration改变（比如屏幕旋转）也不销毁数据
     * <p>
     * ===========为什么用ViewModel?==============
     * 两个痛点：
     * 1.如果系统 销毁然后重建了 UI controller（比如Activity转屏的时候）
     * 那么数据也会随之销毁，当然你可以在 onSaveInstanceState() 中将数据存储在bundle中
     * 在onCreate的时候获得到它，但是当数据量一大的时候或者是bitmap的时候
     * serialized then deserialized 就不行了
     * <p>
     * 2. UI controller 中进行大量的异步请求，当ui销毁的时候我们需要清除这些请求，以防潜在的内存泄漏
     * 而管理这些请求是需要不少逻辑的。而且在屏幕旋转后再次请求数据会造成资源的浪费
     * <p>
     * 所以ViewModel是更简单高效的解决方案
     *
     * ===========ViewModel特性==================
     * ViewModelProviders可以使得Activity销毁重建 而 ViewModel还是原来的对象
     *
     * ViewModel objects are designed to outlive specific instantiations of views or LifecycleOwners.
     * ViewModel被设计为和view和LifecycleOwners无关联的，所以viewmodel的生命周期比他们要长
     *
     * 如果你需要applicationContext，那么可以使用
     * AndroidViewModel
     *
     * ViewModel的生命周期见 res/drawable/viewmodel-lifecycle.png
     *
     * 总结：
     * 1.ViewModel生命周期比 LifecycleOwners 长
     * 2.通过配合LiveData 来实现解耦，因为他持有LiveData，所以没有callback，降低潜在的内存泄漏
     *
     * ================ViewModel在两个fragment之间可以共享=========
     * model = ViewModelProviders.of(getActivity()).get(SharedViewModel.class);
     * 这样就实现了两个fragment之间的通信，因为他们获取到的model都是一个对象
     *
     *
     *
     * ===========ViewModel使用注意=============
     *  A ViewModel must never reference a view, Lifecycle, or any class that may hold a reference to the activity context.
     *  ViewModel 类中不应该持有view对象或者 activity context
     *
     * ===========ViewModel在架构中的位置（代替loader）==========
     * 见viewmodel-loader.png 和 viewmodel-replace-loader.png
     *
     *
     */
    private void initData() {
        UserProfileViewModel viewModel = ViewModelProviders.of(this).get(UserProfileViewModel.class);

        viewModel.getUser(123L).observe(this, new Observer<User>() {
            @Override
            public void onChanged(@Nullable User user) {
                Log.i("test", "获取了" + user.toString());
            }
        });

    }


    /**
     * =========ui controller=========
     * UI controllers such as activities and fragments are primarily intended to display UI data,
     * react to user actions, or handle operating system communication, such as permission requests.
     * activities fragments 主要是展示ui数据，相应用户操作，处理系统会话，比如权限请求
     * 他们不应持有数据，和处理数据
     * <p>
     * ==========
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initLocationLifecycle();

    }

    private void initLocationLifecycle() {
        final MyLocationListener myLocationListener = new MyLocationListener(getLifecycle());


        getLifecycle().addObserver(myLocationListener);
        checkLocationState(new Runnable() {
            @Override
            public void run() {
                myLocationListener.enable();
            }
        });
    }

    /**
     * 初始化或者检查定位服务是否可用
     *
     * @param runnable
     */
    private void checkLocationState(Runnable runnable) {
        new Handler().postDelayed(runnable, 300);
    }

    /**
     * ============Lifecycle====================
     * https://developer.android.google.cn/topic/libraries/architecture/lifecycle
     * 有这样的场景，我们在activity或者fragment onStart的时候开启定位服务，onStop
     * 的时候需要停止。按照往常的写法需要在 onStart 中调用start，在 onStop 调用stop
     * 这样写看起来没什么问题，但是通常情况下我们有很多逻辑都需要在生命周期中写，这样会导致
     * onStart 和 onStop 中的代码变得臃肿，难以维护。
     * 还有一种情况，就是我们在 onStart 中可能需要先执行一些异步的检查操作，检查完毕后再调用
     * start开启定位服务，但是这个时候onStop可能在这之前就已经被调用了，这样这个服务就不需要被开启了
     * <p>
     * 总结：
     * 1.生命周期中代码臃肿，难以维护
     * 2.异步调用后已经onStop了，这时组件不需要被开启
     * <p>
     * 基于以上两个问题，我们可以用 Lifecycle组件来优雅的解决
     * <p>
     * <p>
     * LifecycleObserver 标记接口，实现它的类是观察生命周期通知的观察者
     * LifecycleOwner 生命周期的发出这
     * Fragments and Activities in Support Library 26.1.0 and later already implement the LifecycleOwner interface.
     * 在 Support Library 26.1.0 以上版本Activity 和 Fragment都已经实现了这个接口
     * 在AndroidX 中 ComponentActivity 继承了Activity 实现了  LifecycleOwner
     * androidx.appcompat.app.AppCompatActivity;是ComponentActivity的子类
     * <p>
     * 这样我们就通过观察者模式来接收生命周期的事件了，实现了代码解耦，内部也实现了获取当前
     * LifecycleOwner 状态的方法，我们只需要调用判断即可
     * <p>
     * <p>
     * =============实现原理 =============
     * LifecycleRegistry 实现了 Lifecycle
     * LifecycleRegistry 持有 LifecycleOwner 的弱引用
     * LifecycleOwner 的 getLifecycle()方法就是 返回的 LifecycleRegistry 对象
     * <p>
     * LifecycleRegistry是真正的被观察者，就是触发事件的对象，然后通知观察者调用方法即可
     * <p>
     * LifecycleOwner 就持有 LifecycleRegistry 对象，而且 LifecycleOwner 是有生命周期的类
     * LifecycleOwner中用 LifecycleRegistry对象触发事件
     * <p>
     * 整个结构用的就是观察者加弱引用来防止内存泄漏，代码解耦
     * <p>
     * ==========ProcessLifecycleOwner===========
     * https://developer.android.google.cn/reference/androidx/lifecycle/ProcessLifecycleOwner.html
     * 这个一般用于app 切换前后台的通知
     */
    public static class MyLocationListener implements LifecycleObserver {

        private boolean enabled = false;
        private Lifecycle lifecycle;

        public MyLocationListener(Lifecycle lifecycle) {
            this.lifecycle = lifecycle;
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_START)
        void start() {
            if (enabled) {
                // connect
                Log.i("test", "start: ");
            }
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
        void stop() {
            // disconnect if connected
            Log.i("test", "stop: ");
        }


        /**
         * 后台杀掉应用也会调用ON_DESTROY
         */
        @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
        void destroy() {
            Log.i("test", "destroy: ");
        }

        public void enable() {
            enabled = true;
            if (lifecycle.getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
                // connect if not connected
                Log.i("test", "start: ");
            }
        }
    }
}
