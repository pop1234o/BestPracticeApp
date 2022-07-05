package com.liyafeng.video

import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.view.*
import android.view.animation.AlphaAnimation
import android.widget.TextView

class ScaleController(var touchView: View, var controlView: View, var tvClickMsg: View) {
    var showTip = false

    var onClickListener: (() -> Unit)? = null

    fun handleScale(onClickListener: (() -> Unit)?) {
        val gestureDetector = GestureDetector(controlView.context, object :
            GestureDetector.SimpleOnGestureListener() {

            override fun onDown(e: MotionEvent?): Boolean {
//                RLog.log("GestureDetectorCompat::onDown")
//                RLog.log("onDown ${e?.action}")
                hide(tvClickMsg)
                return super.onDown(e)
            }

            override fun onScroll(
                e1: MotionEvent?,
                e2: MotionEvent?,
                distanceX: Float,
                distanceY: Float
            ): Boolean {
//                RLog.log("GestureDetectorCompat::onScroll $distanceX")
                if (controlView.scaleX <= 1f) {
                    return false
                }
                //x轴移动
                val fl = controlView.width * (controlView.scaleX - 1) / 2


                var moveFl = controlView.translationX - distanceX
//                RLog.log("GestureDetectorCompat::onScroll $distanceX  $moveFl")
                if (moveFl > fl && controlView.translationX < fl) {
                    moveFl = fl
                }

                if (moveFl < -fl && controlView.translationX > -fl) {
                    moveFl = -fl
                }

                if (moveFl <= fl && moveFl >= -fl) {
                    controlView.translationX = moveFl
                }


                return super.onScroll(e1, e2, distanceX, distanceY)
            }


            override fun onDoubleTap(e: MotionEvent?): Boolean {
//                RLog.log("GestureDetectorCompat::onDoubleTapEvent ${e?.action}")
                val scaleX = controlView.scaleX
                if (scaleX.toInt() != 2) {

                    val maxScaleX =
                        PropertyValuesHolder.ofFloat("scaleX", controlView.scaleX, 2f)
                    val maxScaleY =
                        PropertyValuesHolder.ofFloat("scaleY", controlView.scaleY, 2f)
                    val maxAnim =
                        ObjectAnimator.ofPropertyValuesHolder(
                            controlView,
                            maxScaleX,
                            maxScaleY
                        )
                    maxAnim.setDuration(100)

                    maxAnim.start()
                } else {
                    val minScaleX =
                        PropertyValuesHolder.ofFloat("scaleX", controlView.scaleX, 1f)
                    val minScaleY =
                        PropertyValuesHolder.ofFloat("scaleY", controlView.scaleY, 1f)
                    val transX =
                        PropertyValuesHolder.ofFloat("translationX", controlView.translationX, 0f)
                    val minAnim =
                        ObjectAnimator.ofPropertyValuesHolder(
                            controlView,
                            transX,
                            minScaleX,
                            minScaleY
                        )
                    minAnim.setDuration(100)
                    minAnim.start()
                }

                return false
            }

            override fun onSingleTapConfirmed(e: MotionEvent?): Boolean {
//                RLog.log("onSingleTapConfirmed")
                onClickListener?.invoke()
                return super.onSingleTapConfirmed(e)
            }
        })

        val scaleGestureDetector = ScaleGestureDetector(controlView.context, object :
            ScaleGestureDetector.SimpleOnScaleGestureListener() {

            override fun onScale(detector: ScaleGestureDetector?): Boolean {
                detector ?: return false
//                RLog.log("GestureDetectorCompat::onScale ${detector.scaleFactor}")
                val scaleFactor = detector.scaleFactor
                if (java.lang.Float.isNaN(scaleFactor) || java.lang.Float.isInfinite(scaleFactor)) return false


                var fl = controlView.scaleX * scaleFactor

                //放到最大
                if (fl > 2 && controlView.scaleX < 2) {
                    fl = 2f
                }
                if (fl < 1 && controlView.scaleX > 1) {
                    fl = 1f
                }
                //没到2就一直让放大、缩小
                if (fl <= 2 && fl >= 1) {
                    controlView.scaleX = fl
                    controlView.scaleY = fl

                    //校正trans
                    val fl = controlView.width * (controlView.scaleX - 1) / 2

                    val translationX = controlView.translationX
                    if (translationX > fl) {
                        controlView.translationX = fl
                    }

                    if (translationX < -fl) {
                        controlView.translationX = -fl
                    }
                }

                return true
            }

            override fun onScaleBegin(detector: ScaleGestureDetector?): Boolean {
                return super.onScaleBegin(detector)
            }

            override fun onScaleEnd(detector: ScaleGestureDetector?) {
                super.onScaleEnd(detector)

            }

        })
        touchView.setOnTouchListener { v, event ->
            val onTouchEvent = scaleGestureDetector.onTouchEvent(event)
            gestureDetector.onTouchEvent(event)
            return@setOnTouchListener true
        }
    }

    var alpha: ObjectAnimator? = null
//    fun showTip(tvClickMsg: View, onClickListener: (() -> Unit)?) {
//        if (showTip) {
//            return
//        }
//        showTip = true
//
//        handleScale(onClickListener)
////        RLog.log("showTip  ${controlView.bottom}")
//
//        //根据播放区域下移83
//        var bottom = (800.dp2px() - controlView.bottom) - 83.dp2px()
//        if (bottom < 100.dp2px()) {
//            bottom = 100.dp2px()
//        }
//        (tvClickMsg.layoutParams as? ViewGroup.MarginLayoutParams)?.bottomMargin =
//            bottom
//        alpha = ObjectAnimator.ofFloat(tvClickMsg, "alpha", 0.6f, 0.6f, 0.6f, 0.6f, 0f)
//        alpha?.setDuration(5000)
//        alpha?.start()
//    }

    fun hide(tvClickMsg: View) {
        if (tvClickMsg.alpha == 0f) {
            return
        }
        alpha?.cancel()
        tvClickMsg.alpha = 0f
    }

    fun onDestroy() {
//        alpha?.let {
//            if (it.isRunning) {
//                it.cancel()
//            }
//        }
    }

}