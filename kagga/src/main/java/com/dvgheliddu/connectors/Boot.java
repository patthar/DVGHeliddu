package com.dvgheliddu.connectors;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.dvgheliddu.data.Utils.Constants;

import java.util.Calendar;

/**
 * Created by ppatthar on 13/10/14.
 */
public class Boot {

    public AlarmManager getAlarmMgr() {
        return alarmMgr;
    }

    public void setAlarmMgr() {
        this.alarmMgr = (AlarmManager)this.getmContext().getSystemService(Context.ALARM_SERVICE);
    }

    private AlarmManager alarmMgr;
    private PendingIntent alarmIntent;

    public Context getmContext() {
        return mContext;
    }

    public void setmContext(Context mContext) {
        this.mContext = mContext;
    }

    private Context mContext = null;

    public PendingIntent getAlarmIntent() {
        return alarmIntent;
    }

    public void setAlarmIntent()  {

        Intent intent = new Intent(getmContext(), AlarmReceiver.class);
        this.alarmIntent = PendingIntent.getBroadcast(getmContext(),0,intent,0);
    }

    public Boot(Context ctx) {
        super();
        setmContext(ctx);
        setAlarmMgr();
        setAlarmIntent();
    }

    public void createAlarmService() {
        Calendar cal = Calendar.getInstance();
        long time = System.currentTimeMillis();
        cal.setTimeInMillis(System.currentTimeMillis());
        Toast toast = Toast.makeText(getmContext(), cal.getTime().toString(),Toast.LENGTH_SHORT);
        toast.show();
        cal.set(Calendar.HOUR_OF_DAY, Constants.TIME_TO_DELIVER_NOTIFICATION_HOUR);
        cal.set(Calendar.MINUTE, Constants.TIME_TO_DELIVER_NOTIFICATION_MINUTE);

        try {
            alarmMgr.setInexactRepeating(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), AlarmManager.INTERVAL_DAY,getAlarmIntent());
        }
        catch (Exception e) {
            e.printStackTrace();
        }


    }
}
