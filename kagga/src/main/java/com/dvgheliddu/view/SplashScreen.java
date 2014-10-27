package com.dvgheliddu.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.dvgheliddu.connectors.KaggaAlarmService;
import com.dvgheliddu.connectors.KaggaAlarmService;
import com.dvgheliddu.data.Utils.Constants;
import com.dvgheliddu.kagga.R;

/**
 * Created by ppatthar on 14/10/14.
 */
public class SplashScreen extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //Bootstrap the app here
                KaggaAlarmService alarm = new KaggaAlarmService(getApplicationContext());
                if(! alarm.checkAlarm()) {
                    alarm.createAlarmService();
                }
                else {
                    Log.i("Alarm", "Alarm already set");
                }
                Intent i = new Intent(SplashScreen.this, KaggaHistoryListActivity.class);
                startActivity(i);
                // close this activity
                finish();
            }
        }, Constants.SPLASH_TIME_OUT);
    }
}
