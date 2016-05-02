package com.upna.ibio16.epoc.alarm;

import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;

import com.upna.ibio16.epoc.R;

public class RingingAlarm extends AppCompatActivity implements View.OnClickListener {

    private final int NOTI_TAG = 0x12398;
    private Ringtone r;
    private ImageButton snooze, remove;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED);
        window.addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setNavigationBarColor(getResources().getColor(R.color.colorPrimaryDark));
            window.setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
        }

        Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
        r = RingtoneManager.getRingtone(getApplicationContext(), notification);
        r.play();

        // Ahora ya la parte grafica.
        setContentView(R.layout.activity_ringing_alarm);

        snooze = (ImageButton) findViewById(R.id.snooze_alarm);
        snooze.setOnClickListener(this);
        remove = (ImageButton) findViewById(R.id.remove_alarm);
        remove.setOnClickListener(this);
    }

    private void setNotificationReminder(){
        NotificationManager manager = (NotificationManager)
                getSystemService(NOTIFICATION_SERVICE);

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(android.R.drawable.ic_lock_idle_alarm)
                        .setContentTitle("Inhalador")
                        .setContentText("Tienes que tomarte la medicacion");

        manager.notify(NOTI_TAG, mBuilder.build());
    }

    @Override
    public void onBackPressed() {}

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.snooze_alarm:
                setNotificationReminder();
            default:
                if(r.isPlaying()) r.stop();
                finish();
        }
    }
}
