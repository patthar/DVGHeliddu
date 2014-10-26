package com.dvgheliddu.connectors;

import android.animation.AnimatorSet;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

import com.dvgheliddu.data.Kagga;
import com.dvgheliddu.data.KaggaDeserializer;
import com.dvgheliddu.kagga.R;
import com.dvgheliddu.view.KaggaDetail;
import com.dvgheliddu.view.ResultActivity;

/**
 * Created by ppatthar on 13/10/14.
 */
public class AlarmReceiver extends BroadcastReceiver{

    int mNotificationId = 001;

    @Override
    public void onReceive(Context ctx, Intent intent) {

        Toast toast = Toast.makeText(ctx, "Managed to get the wake up call",Toast.LENGTH_SHORT);

        //build notification
        BuildKaggaForTheDay todaysKagga = new BuildKaggaForTheDay(ctx);
        NotificationCompat.Builder mBuilder = todaysKagga.getmBuilder();

        Intent kaggaIntent = new Intent(ctx, KaggaDetail.class);
        kaggaIntent.putExtra("koftd", todaysKagga.getmKagga().getId());
        Integer newnum = (Integer) kaggaIntent.getExtras().get("koftd");

        PendingIntent notifIntent = PendingIntent.getActivity(ctx, 0, kaggaIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        mBuilder.setContentIntent(notifIntent);
        mBuilder.setDefaults(Notification.DEFAULT_SOUND);

        NotificationManager notifyMgr = (NotificationManager) ctx.getSystemService(Context.NOTIFICATION_SERVICE);
        notifyMgr.notify(mNotificationId, mBuilder.build());
    }

    private class BuildKaggaForTheDay {
        public NotificationCompat.Builder getmBuilder() {
            return mBuilder;
        }

        public void setmBuilder(NotificationCompat.Builder mBuilder) {
            this.mBuilder = mBuilder;
        }

        NotificationCompat.Builder mBuilder = null;
        Context mContext = null;

        public KaggaDeserializer getmKagga() {
            return mKagga;
        }

        public void setmKagga(KaggaDeserializer mKagga) {
            this.mKagga = mKagga;
        }

        KaggaDeserializer mKagga = null;

        public Context getmContext() {
            return mContext;
        }

        public void setmContext(Context mContext) {
            this.mContext = mContext;
        }

        private BuildKaggaForTheDay(Context ctx) {
            setmContext(ctx);

            //roll dice and get kagga number
            Kagga k = Kagga.getInstance(ctx);
            KaggaDeserializer thisKagga = k.readKagga(k.rollDice());
            setmKagga(thisKagga);

            setmBuilder(
                    new NotificationCompat.Builder(ctx)
                            .setSmallIcon(R.drawable.dvg)
                            .setContentTitle(getmKagga().getId() + " : " + getmKagga().getTitle())
                            .setContentText(getmKagga().getKagga())

            );
        }
    }

}
