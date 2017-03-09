package com.example.hapadim.MonumentsNotifications;

import android.app.NotificationManager;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;

import com.example.hapadim.R;

import static android.R.attr.id;

/**
 * Created by queenabergen on 3/4/17.
 */

public class EffielTowerNotifications extends AppCompatActivity {

    //acquired when user completes the empire state building
    public void empireStateofMindBadge() {
        NotificationCompat.Builder mBuilder =
                (NotificationCompat.Builder) new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.unlockicon)
                        .setContentTitle(getString(R.string.notiAchievementTitles))
                        .setContentText(getString(R.string.stateofmindbadge));
/*
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(THE_NEW_ACTIIVTY.class);
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent =
                stackBuilder.getPendingIntent(
                        0,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );
        mBuilder.setContentIntent(resultPendingIntent);
        */
        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(id, mBuilder.build());
    }

}
