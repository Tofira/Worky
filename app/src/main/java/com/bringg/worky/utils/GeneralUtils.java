package com.bringg.worky.utils;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import com.bringg.worky.main.MainActivity;
import com.bringg.worky.R;

/**
 * Created by Mickael on 3/6/2018.
 */

public class GeneralUtils {

    public static Notification getOngoingNotification(Context context) {
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context, Constants.NotificationsConstants.NOTIFICATION_CHANNEL_ID);

        notificationBuilder
                .setOngoing(true)
                .setDefaults(Notification.DEFAULT_ALL)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.ic_launcher)
                .setTicker(context.getString(R.string.notification_ticker_text))
                .setContentTitle(context.getString(R.string.notification_title_text))
                .setContentText(context.getString(R.string.notification_content_text));

        Intent notificationIntent = new Intent(context, MainActivity.class);
        notificationIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        notificationIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent contentIntent = PendingIntent.getActivity(context, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        notificationBuilder.setContentIntent(contentIntent);

        return (notificationBuilder.build());


    }

}
