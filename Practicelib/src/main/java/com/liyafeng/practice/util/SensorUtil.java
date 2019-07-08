package com.tal.lib_common.utils;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;

import com.tal.lib_common.app.BaseApplication;

import java.util.Calendar;

public class SensorUtil implements SensorEventListener {

    public static final String TAG = "SensorControler";
    private SensorManager mSensorManager;
    private Sensor mSensor;

    private float mX, mY, mZ;
    private long lastStaticStamp = 0;
//    Calendar mCalendar;

    boolean isFocusing = false;
    boolean canFocus = false;

    public static final int DELEY_DURATION = 200;

    public static final int STATUS_NONE = 0;
    public static final int STATUS_STATIC = 1;
    public static final int STATUS_MOVE = 2;
    private int STATUE = STATUS_NONE;

    private CameraFocusListener mCameraFocusListener;

    private static SensorUtil mInstance;

    //1 表示没有被锁定 0表示被锁定
    private int foucsing = 1;

    private SensorUtil() {
        mSensorManager = (SensorManager) BaseApplication.getIns().getSystemService(Activity.SENSOR_SERVICE);
        // TYPE_GRAVITY
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    }

    public static synchronized SensorUtil getInstance() {
        if (mInstance == null) {
            mInstance = new SensorUtil();
        }
        return mInstance;
    }

    public void setCameraFocusListener(CameraFocusListener mCameraFocusListener) {
        this.mCameraFocusListener = mCameraFocusListener;
    }

    public void onStart() {
        restParams();
        canFocus = true;
        mSensorManager.registerListener(this, mSensor,
                SensorManager.SENSOR_DELAY_UI);
    }

    public void onStop() {
        mSensorManager.unregisterListener(this, mSensor);
        canFocus = false;
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }


    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor == null) {
            return;
        }

        if (isFocusing) {
            restParams();
            return;
        }
//        Log.i(TAG,"检测手机"+event.sensor.getType());

        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            float x =  event.values[0];
            float y =  event.values[1];
            float z =  event.values[2];
            long stamp = System.currentTimeMillis();


            if (STATUE != STATUS_NONE) {
                float px = Math.abs(mX - x);
                float py = Math.abs(mY - y);
                float pz = Math.abs(mZ - z);
//                Log.d(TAG, "pX:" + px + "  pY:" + py + "  pZ:" + pz + "    stamp:"
//                        + stamp + "  second:" + second);
                double value = Math.sqrt(px * px + py * py + pz * pz);
                if (value > 0.6) {
//                    textviewF.setText("检测手机在移动..");
                    Log.i(TAG, "检测手机在移动" + value);
                    STATUE = STATUS_MOVE;

                    mX = x;
                    mY = y;
                    mZ = z;
                } else {
//                    Log.i(TAG, "检测静止" + value + " " + STATUE);
//                    textviewF.setText("检测手机静止..");
//                    Log.i(TAG,"mobile static");
                    //上一次状态是move，记录静态时间点

                    if (STATUE == STATUS_MOVE) {
//                        if (stamp - lastStaticStamp > DELEY_DURATION) {
                            lastStaticStamp = stamp;
                            //移动后静止一段时间，可以发生对焦行为
                            if (!isFocusing) {
                                Log.i(TAG, "检测手机静止========");
                                if (mCameraFocusListener != null) {
                                    mCameraFocusListener.onFocus();
                                }
                            }
//                        }
                        STATUE = STATUS_STATIC;
                    }

                }
            } else {
                STATUE = STATUS_STATIC;
            }

        }
    }

    private void restParams() {
        STATUE = STATUS_NONE;
        mX = 0;
        mY = 0;
        mZ = 0;
    }

    /**
     * 对焦是否被锁定
     *
     * @return
     */
    public boolean isFocusLocked() {
        if (canFocus) {
            return foucsing <= 0;
        }
        return false;
    }

    /**
     * 锁定对焦
     */
    public void lockFocus() {
        isFocusing = true;
        foucsing--;
//        Log.i(TAG, "lockFocus");
    }

    /**
     * 解锁对焦
     */
    public void unlockFocus() {
        isFocusing = false;
        foucsing++;
//        Log.i(TAG, "unlockFocus");
    }

    public void restFoucs() {
        foucsing = 1;
    }

    public interface CameraFocusListener {
        void onFocus();
    }
}
