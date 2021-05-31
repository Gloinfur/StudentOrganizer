package com.jansmoneymachine.timetablestudents.Deadlines;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;


public class DeadlinesAlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        DeadlinesNotificationHelper notificationHelper = new DeadlinesNotificationHelper(context);
        NotificationCompat.Builder notificationBuilder = notificationHelper.getChannel1Notification("Student Organizer", "Deadline Reminder - Click here!");
        notificationHelper.getManager().notify(1, notificationBuilder.build());
    }
}

