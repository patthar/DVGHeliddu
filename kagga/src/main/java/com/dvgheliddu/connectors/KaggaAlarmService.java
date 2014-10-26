package com.dvgheliddu.connectors;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.dvgheliddu.data.Kagga;
import com.dvgheliddu.data.Utils.Constants;

import java.util.Calendar;

/**
 * Created by ppatthar on 13/10/14.
 */
public class KaggaAlarmService {

    public AlarmManager getAlarmMgr() {
        return alarmMgr;
    }

    public void setAlarmMgr() {
        this.alarmMgr = (AlarmManager)this.getmContext().getSystemService(Context.ALARM_SERVICE);
    }

    private AlarmManager alarmMgr;
    public static Integer alarmID = 1234567;


    public Context getmContext() {
        return mContext;
    }

    public void setmContext(Context mContext) {
        this.mContext = mContext;
    }

    private Context mContext = null;




    public KaggaAlarmService(Context ctx) {
        super();
        setmContext(ctx);
        setAlarmMgr();
    }

    public void createAlarmService() {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(System.currentTimeMillis());
        Toast toast = Toast.makeText(getmContext(), cal.getTime().toString(),Toast.LENGTH_SHORT);
        toast.show();
        Kagga kagga = Kagga.getInstance(getmContext());
        cal.set(Calendar.HOUR_OF_DAY, kagga.getKaggaAlarmTimeHour());
        cal.set(Calendar.MINUTE, kagga.getKaggaAlarmTimeMinute());
        Log.i("Alarm", "At Boot time, Alarm set to " +  kagga.getKaggaAlarmTimeHour() + " , " + kagga.getKaggaAlarmTimeMinute());
        try {
            Intent intent = new Intent(getmContext(), AlarmReceiver.class);

            alarmMgr.setRepeating(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), AlarmManager.INTERVAL_DAY,
                    PendingIntent.getBroadcast(getmContext(), this.alarmID, intent, 0));
            toast = Toast.makeText(getmContext(),"Next alarm at " + cal.getTime().toString(),Toast.LENGTH_LONG);
            toast.show();
        }
        catch (Exception e) {
            e.printStackTrace();
        }


    }

    public boolean checkAlarm() {
        Intent i = new Intent(getmContext(), AlarmReceiver.class);
        boolean alarmStatus = ( PendingIntent.getBroadcast(getmContext(), this.alarmID,i,PendingIntent.FLAG_NO_CREATE) != null );
        return alarmStatus;
    }
}
