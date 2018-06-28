package com.liyafeng.view.other.notify;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.support.v4.app.NotificationCompat;

import com.liyafeng.view.R;

public class Main {

    /**
     * 通知显示
     * https://developer.android.com/guide/topics/ui/notifiers/notifications?hl=zh-cn
     *
     *
     *
     *
     * @param args
     */
    public static void main(String[] args) {
        Context context=null;
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(context)
                        .setPriority(NotificationCompat.PRIORITY_MAX)
                        .setWhen(System.currentTimeMillis())
                        .setVibrate(new long[]{0,300,50,300})
                        .setDefaults(Notification.DEFAULT_SOUND)
                        .setAutoCancel(true)
//                            .setVibrate()
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("notify_title")
                        .setContentText("notify_content");


//        Intent intent = getIntent(context, obj);

//        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
//        mBuilder.setContentIntent(pendingIntent);

        NotificationManager mNotificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        // mId allows you to update the notification later on.
        if (mNotificationManager != null) {
            int id = (int) (System.currentTimeMillis() % 10000);
            mNotificationManager.notify(id, mBuilder.build());
        }


//        数组第一个参数表示延迟震动时间

//                第二个参数表示震动持续时间
//        第三个参数表示震动后的休眠时间
//                第四个参数又表示震动持续时间
//        第五个参数也表示正到休眠时间
//                以此类推
    }
}
