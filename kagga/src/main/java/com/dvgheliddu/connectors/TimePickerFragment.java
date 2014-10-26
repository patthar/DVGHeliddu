package com.dvgheliddu.connectors;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TimePicker;

import java.text.DateFormat;
import java.util.Calendar;

import  android.text.format.DateFormat.*;
import android.widget.Toast;

import com.dvgheliddu.data.Kagga;

/**
 * Created by ppatthar on 23/10/14.
 */
public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        final Calendar cal = Calendar.getInstance();
        int hour    = cal.get(Calendar.HOUR_OF_DAY);
        int minutes = cal.get(Calendar.MINUTE);

        return new TimePickerDialog(getActivity(), this, hour, minutes, android.text.format.DateFormat.is24HourFormat(getActivity()));

    }

    @Override
    public void onTimeSet(TimePicker timePicker, int i, int i2) {
        Kagga kagga = Kagga.getInstance(getActivity().getBaseContext());
        kagga.setKaggaAlarmTimeHour(i);
        kagga.setKaggaAlarmTimeMinute(i2);

        //cancel the existing alarm and create new one
        KaggaAlarmService alarm = new KaggaAlarmService(getActivity().getBaseContext());
        Intent intent = new Intent(getActivity().getBaseContext(), AlarmReceiver.class);
        PendingIntent pIntent = PendingIntent.getBroadcast(getActivity().getBaseContext(), alarm.alarmID, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        alarm.getAlarmMgr().cancel(pIntent);
        alarm.createAlarmService();

        Integer hourDiff = Calendar.getInstance().get(Calendar.HOUR_OF_DAY) - i;
        Integer minDiff = Calendar.getInstance().get(Calendar.MINUTE) - i2;
        Toast.makeText(getActivity().getBaseContext(), String.format("Next alarm in %d hours, %d minutes", hourDiff, minDiff),Toast.LENGTH_SHORT).show();


    }
}
