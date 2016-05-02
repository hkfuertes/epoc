package com.upna.ibio16.epoc.alarm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.commonsware.cwac.wakeful.WakefulIntentService;

import java.util.Calendar;

/**
 * Created by hkfuertes on 02/04/16.
 */
public class AlarmListener implements WakefulIntentService.AlarmListener {
    @Override
    public void scheduleAlarms(AlarmManager alarmManager, PendingIntent pendingIntent, Context context) {

        // every day at 9 am
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        //Repeat every hour
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(), AlarmManager.INTERVAL_HOUR, pendingIntent);
    }

    @Override
    public void sendWakefulWork(Context context) {

    }

    @Override
    public long getMaxAge() {
        return 0;
    }
}
