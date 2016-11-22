package jp.ac.asojuku.st.batterywarning;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by RMS0897 on 2016/11/11.
 */
public class AlarmReceiver extends BroadcastReceiver{

    @Override
    public void onReceive(Context context, Intent receivedintent) {

        int notificationId = receivedintent.getIntExtra("notificationId", 0);
        NotificationManager myNotification =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        Intent bootIntent = new Intent(context, MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(context, 0, bootIntent, 0);
        Notification.Builder builder = new Notification.Builder(context);
        int scale = receivedintent.getIntExtra("scale", 0);

        if(scale == 60) {
            //もしバッテリーが60%なら・・・
            builder.setSmallIcon(android.R.drawable.ic_dialog_info)
                    .setContentTitle("現在のバッテリ残量は" + scale + "です。")
                    .setContentText(receivedintent.getCharSequenceExtra("todo"))
                    .setWhen(System.currentTimeMillis())
                    .setPriority(Notification.PRIORITY_DEFAULT)
                    .setAutoCancel(true)
                    .setDefaults(Notification.DEFAULT_SOUND)
                    .setContentIntent(contentIntent);

            myNotification.notify(notificationId, builder.build());
        }
    }
}
