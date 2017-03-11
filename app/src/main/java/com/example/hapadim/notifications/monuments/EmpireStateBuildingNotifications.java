package com.example.hapadim.notifications.monuments;

import android.app.NotificationManager;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;

import com.example.hapadim.R;

import static android.R.attr.id;

/**
 * Created by queenabergen on 3/3/17.
 */

public class EmpireStateBuildingNotifications extends AppCompatActivity {


    //acquired when user completes the empire state building
    public void empireStateofMindBadge() {
        NotificationCompat.Builder mBuilder =
                (NotificationCompat.Builder) new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.unlockicon)
                        .setContentTitle(getString(R.string.notiAchievementTitles))
                        .setContentText(getString(R.string.stateofmindbadge));

        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(id, mBuilder.build());
    }

}
