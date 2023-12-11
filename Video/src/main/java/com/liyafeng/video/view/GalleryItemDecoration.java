package com.liyafeng.video.view;

import android.graphics.Rect;
import android.view.View;


public class GalleryItemDecoration{

}
//public class GalleryItemDecoration extends RecyclerView.ItemDecoration {

//    public static int mPageMargin = dpToPx(6);          // 每一个页面默认页边距
//    public static int mLeftPageVisibleWidth = dpToPx(136); // 中间页面左右两边的页面可见部分宽度
//
//
//
//    @Override
//    public void getItemOffsets(Rect outRect, final View itemView, final RecyclerView parent, RecyclerView.State state) {
//        super.getItemOffsets(outRect, itemView, parent, state);
//        // ...
//        outRect.left = mPageMargin;
//        outRect.right = mPageMargin;
//        int position = ((RecyclerView.LayoutParams) itemView.getLayoutParams()).getBindingAdapterPosition();
//        if (position == 0) {
//            outRect.left = mLeftPageVisibleWidth;
//        }
//        int itemCount = parent.getAdapter().getItemCount();
//        if (position == itemCount - 1) {
//            outRect.right = mLeftPageVisibleWidth;
//        }
//
//    }
//
//    private static int dpToPx(int i) {
//        return DensityUtil.dip2px(App.context, i);
//    }
//}
