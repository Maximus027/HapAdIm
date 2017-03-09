package com.example.hapadim;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.NotificationCompat;

import static android.content.Context.NOTIFICATION_SERVICE;

/**
 * Created by queenabergen on 3/4/17.
 */

public class NotificationBuilder {
    int icon;
    String contentTitle;
    String contentText;
    Intent intent;


    public NotificationBuilder(int icon, String contentTitle, String contentText) {
        this.icon = icon;
        this.contentTitle = contentTitle;
        this.contentText = contentText;
        this.intent = intent;
    }

    //add intent to parameters
    public void makeNotification(Context context) {
        NotificationCompat.Builder mBuilder =
                (NotificationCompat.Builder) new NotificationCompat.Builder(context)
                        .setSmallIcon(icon)
                        .setContentTitle(contentTitle)
                        .setContentText(contentText);
        NotificationManager mNotificationManager =
                (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
        mNotificationManager.notify(1, mBuilder.build());

    }


}
