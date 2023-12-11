package com.tal.geniedata.secret.view.card

import android.content.Context
import android.view.View
//import androidx.recyclerview.widget.LinearLayoutManager
//import androidx.recyclerview.widget.LinearSnapHelper
//import androidx.recyclerview.widget.RecyclerView
//import kotlinx.android.synthetic.main.layout_content.*

class CardScaleHelper(var context: Context) {
//
//
//    var mCurrentDx = 0
//    var rvPageList: RecyclerView? = null
//    var pageWidth: Int =
//        (App.context.resources.getDimension(R.dimen.page_width) + GalleryItemDecoration.mPageMargin * 2).toInt()
//
//
//    fun attachToRecyclerView(rvPageList: RecyclerView) {
//        this.rvPageList = rvPageList
//
//
//        val linearSnapHelper = LinearSnapHelper()
//        rvPageList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
//
//            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
//                super.onScrollStateChanged(recyclerView, newState)
//                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
//
//                    val linearLayoutManager1 = rvPageList.layoutManager ?: return
//                    val snapView: View = linearSnapHelper.findSnapView(linearLayoutManager1)
//                        ?: return  // nothing we can do
//                    val snapPosition: Int = linearLayoutManager1.getPosition(snapView)
//
//                    RLog.log("snapPosition::$snapPosition")
////                    if (snapPosition != mCurrentItem && getScrollState() == ViewPager2.SCROLL_STATE_IDLE) {
////                        mPageChangeEventDispatcher.onPageSelected(snapPosition)
////                    }
//                }
//            }
//
//            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
//                super.onScrolled(recyclerView, dx, dy)
//                mCurrentDx += dx
//                RLog.log("onScrolled::$mCurrentDx   dx$dx")
//
//                //每个dx，对应每个item的scale和透明度
////                GalleryItemDecoration.mLeftPageVisibleWidth
//
//
//                val linearLayoutManager1 =
//                    rvPageList.layoutManager as? LinearLayoutManager ?: return
//                val findFirstVisibleItemPosition =
//                    linearLayoutManager1.findFirstVisibleItemPosition()
//                val findLastVisibleItemPosition = linearLayoutManager1.findLastVisibleItemPosition()
//                //0 0最大   108  1最大     216 -2 最大
//
//                //循环
//
//
//                for (i in findFirstVisibleItemPosition..findLastVisibleItemPosition) {
//                    //pos dx  ->  缩放
//                    //当前距离和最大的距离之间的差距
//                    var per = Math.abs(mCurrentDx - i * pageWidth) / pageWidth.toFloat()
//                    //大于1那就是距离大于一个卡片，固定缩放
//                    //小于1是开始之间最大缩放，0是不缩放
//                    RLog.log("i  $i   scale $per")
//                    var scale = 0.86f
//                    if (per < 1) {
//                        scale = 0.86f + (0.14f * (1 - per))
//                    }
//                    var viewPage = linearLayoutManager1.findViewByPosition(i)
//                    viewPage?.scaleX = scale
//                    viewPage?.scaleY = scale
//
//
//                    var alpha = 0.50f
//                    if (per < 1) {
//                        alpha = 0.50f + (0.50f * (1 - per))
//                    }
//
//                    viewPage?.alpha = alpha
//                }
//
//
//            }
//        })
//        rvPageList.addItemDecoration(GalleryItemDecoration())
//        linearSnapHelper.attachToRecyclerView(rvPageList)
//    }
//
//    fun scrollToPosition(i: Int) {
//        rvPageList?.let {
//            mCurrentDx = (i * pageWidth).toInt()
//            if (i > 0) {
//                //滑动到屏幕中央
//                (rvPageList?.layoutManager as LinearLayoutManager).scrollToPositionWithOffset(
//                    i,
//                    (360.dp2px() - pageWidth) / 2
//                )
//            } else {
//                (rvPageList?.layoutManager as LinearLayoutManager).scrollToPosition(i)
//            }
//
//        }
//    }
//
//    fun smoothScroll(i: Int) {
//
//        rvPageList?.smoothScrollBy((i * pageWidth) - mCurrentDx, 0)
//    }
}