package com.liyafeng.view.newlayout;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.liyafeng.view.R;

public class CoordinatorLayoutActivity extends Activity {

    /**
     * As a top-level application decor or chrome layout
     * As a container for a specific interaction with one or more child views
     * <p>
     * 这个其实就是来协调滚动的
     * <p>
     * UI变换会更连贯
     * <p>
     * 每个sdk版本下自带的res中的Style也不一样，它里面自定义了一些基本的属性
     * 如果我们用低版本的sdk，这些属性就没有，可能会报错
     * <p>
     * ===============布局========
     * android.support.design.widget.CoordinatorLayout
     * 这个不参与滑动等动作，他只是将它布局内的控件关联起来，内部利用Behavior来协调
     * 比如将listview和toolbar关联起来，listview下滑的时候显示，上滑的时候隐藏
     * 而被动移动的布局需要指定behavior  app:layout_behavior="com.xx.xx_behavior"
     * 这样就根据了Behavior类中的 layoutDependsOn（）方法来判断被依赖的view是哪个
     * 当然好像也可以不依赖？？
     * <p>
     * <p>
     * android.support.design.widget.AppBarLayout
     * 就是个垂直的LinearLayout ，里面子控件要添加属性 app:layout_scrollFlags="scroll"
     * CoordinatorLayout中包括AppBarLayout和NestedScrollView或者 RecyclerView（因为他们
     * 都实现了 ScrollingView, NestedScrollingChild ），AppBarLayout中又有默认的Behavior
     * 所以很方便
     * <p>
     * <p>
     * <p>
     * android.support.design.widget.CollapsingToolbarLayout
     * android.support.v7.widget.Toolbar
     * android.support.v4.widget.NestedScrollView
     * <p>
     * <p>
     * ==================== CollapsingToolbarLayout 滑动卡顿问题==================
     * 这种滑动会不顺畅，https://blog.csdn.net/BIGGGFISH/article/details/53585783
     * //https://www.jianshu.com/p/d0dbbdfa5372
     * 27.0.2b版本已经解决这个问题
     *
     * ========AppBarLayout======
     *  AppBarLayout is a vertical {@link LinearLayout} which implements many of the features of material
     *  designs app bar concept, namely scrolling gestures.
     *
     * ================ CollapsingToolbarLayout ================
     *  CollapsingToolbarLayout is a wrapper for { Toolbar} which implements a collapsing app bar.
     *  It is designed to be used as a direct child of a { AppBarLayout}
     *  一个可折叠的toolbar ，他必须是 AppBarLayout 的子布局
     *
     *
     *
     * =========== Toolbar ============
     * A standard toolbar for use within application content.
     * 就是工具栏
     *
     * //
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_design_layout);


//        setContentView(R.layout.activity_design_layout);

//        final RecyclerView recycer = (RecyclerView) findViewById(R.id.recycer);
//        recycer.setLayoutManager(new LinearLayoutManager(this));
//        RecyclerView.Adapter adapter = new RecyclerView.Adapter<CoordinatorLayoutActivity.ListHolder>() {
//            @Override
//            public CoordinatorLayoutActivity.ListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//                View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.list_recycle, null);
//
//                view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
//
//                CoordinatorLayoutActivity.ListHolder listHolder = new CoordinatorLayoutActivity.ListHolder(view);
//
//                return listHolder;
//            }
//
//            @Override
//            public void onBindViewHolder(CoordinatorLayoutActivity.ListHolder holder, int position) {
//                holder.setData(position);
//            }
//
//            @Override
//            public int getItemCount() {
//                return 50;
//            }
//        };
//        recycer.setAdapter(adapter);
//
//        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Snackbar.make(recycer, "aaa", 100).show();
//            }
//        });
    }

    /**
     * com.google.android.material:material:1.2.0
     *
     * //title滑动吸顶的效果
     * <RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
     *     xmlns:app="http://schemas.android.com/apk/res-auto"
     *     xmlns:tools="http://schemas.android.com/tools"
     *     android:layout_width="match_parent"
     *     android:layout_height="match_parent">
     *
     *     <androidx.coordinatorlayout.widget.CoordinatorLayout xmlns:android="http://schemas.android.com/apk/res/android"
     *         xmlns:app="http://schemas.android.com/apk/res-auto"
     *         xmlns:tools="http://schemas.android.com/tools"
     *         android:layout_width="match_parent"
     *         android:layout_height="match_parent"
     *         android:background="#F5F7FB"
     *         tools:context=".ui.ContractActivity">
     *
     *
     *         <com.google.android.material.appbar.AppBarLayout
     *             android:id="@+id/app_bar_layout"
     *             android:layout_width="match_parent"
     *             android:layout_height="140dp">
     *
     *             <com.google.android.material.appbar.CollapsingToolbarLayout
     *                 android:id="@+id/collapsing_toolbar_layout"
     *                 android:layout_width="match_parent"
     *                 android:layout_height="match_parent"
     *                 app:collapsedTitleGravity="center"
     *                 app:contentInsetEnd="0dp"
     *                 app:contentInsetStart="0dp"
     *                 app:expandedTitleGravity="left|bottom"
     *                 app:expandedTitleMarginStart="20dp"
     *                 app:layout_scrollFlags="scroll|exitUntilCollapsed">
     *
     *                 <androidx.appcompat.widget.Toolbar
     *                     android:id="@+id/toolbar"
     *                     android:layout_width="match_parent"
     *                     android:layout_height="64dp"
     *                     app:contentInsetEnd="0dp"
     *                     app:contentInsetStart="0dp"
     *                     app:contentInsetStartWithNavigation="0dp"
     *                     app:layout_collapseMode="pin"
     *                     app:title="家长列表">
     *
     *
     *                 </androidx.appcompat.widget.Toolbar>
     *
     *
     *             </com.google.android.material.appbar.CollapsingToolbarLayout>
     *         </com.google.android.material.appbar.AppBarLayout>
     *
     *
     *         <androidx.recyclerview.widget.RecyclerView
     *             android:id="@+id/rvContract"
     *             android:layout_width="match_parent"
     *             android:layout_height="match_parent"
     *             app:layout_behavior="@string/appbar_scrolling_view_behavior" />
     *
     *
     *     </androidx.coordinatorlayout.widget.CoordinatorLayout>
     *
     *
     *     <ImageView
     *         android:layout_width="wrap_content"
     *         android:layout_height="wrap_content"
     *         android:layout_marginLeft="20dp"
     *         android:layout_marginTop="20dp"
     *         android:src="@drawable/rtc_back" />
     *
     * </RelativeLayout>
     */
    void a1(){}

    public class ListHolder extends RecyclerView.ViewHolder {

        private TextView itemView;

        public ListHolder(View itemView) {
            super(itemView);
            this.itemView = (TextView) itemView.findViewById(R.id.item);
        }

        public void setData(int position) {
            itemView.setText("" + position);
        }
    }

}
