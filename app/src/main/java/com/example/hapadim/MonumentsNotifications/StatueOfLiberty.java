package com.example.hapadim.MonumentsNotifications;

import android.support.v7.app.AppCompatActivity;

/**
 * Created by queenabergen on 3/3/17.
 */

public class StatueOfLiberty extends AppCompatActivity {

//    Places places = new Places();
//
//    public Places getPlaces() {
//        places.getBadges().get(0);
//        return places;
//    }



/*
    //acquired when user completes Staue of Liberty
    public void victoryStatueBadge() {
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.victoryStatueBadge)
                        .setContentTitle(getString(R.string.notiAchievementTitles))
                        .setContentText(getString(R.string.victoryStatue));
        Intent resultIntent = new Intent(THE_NEW_ACTIIVTY.class);

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(THE_NEW_ACTIIVTY.class);
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent =
                stackBuilder.getPendingIntent(
                        0,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );
        mBuilder.setContentIntent(resultPendingIntent);
        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(id, mBuilder.build());
    }
    */


}
