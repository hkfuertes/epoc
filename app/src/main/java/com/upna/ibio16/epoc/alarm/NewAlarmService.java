package com.upna.ibio16.epoc.alarm;

import android.content.Intent;

import com.commonsware.cwac.wakeful.WakefulIntentService;

/**
 * Created by hkfuertes on 02/04/16.
 */
public class NewAlarmService extends WakefulIntentService {
    public NewAlarmService() {
        super("NewAlarmService");
    }

    @Override
    protected void doWakefulWork(Intent intent) {

    }
}
