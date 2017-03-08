package com.example.hapadim.MountainsNotifications;

import android.app.NotificationManager;
import android.content.Context;
import android.support.v7.app.NotificationCompat;

import com.example.hapadim.R;

import static android.content.Context.NOTIFICATION_SERVICE;

/**
 * Created by queenabergen on 3/4/17.
 */

public class MountEverestNotification {

    //acquired when user completes mt.everest building
    public void everestCompletitionBadge(Context context) {
        NotificationCompat.Builder mBuilder =
                (NotificationCompat.Builder) new NotificationCompat.Builder(context)
                        .setSmallIcon(R.drawable.unlockicon)
                        .setContentTitle(context.getString(R.string.notiAchievementTitles))
                        .setContentText(context.getString(R.string.everest_Completion));
        NotificationManager mNotificationManager =
                (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
        mNotificationManager.notify(1, mBuilder.build());
    }


}
