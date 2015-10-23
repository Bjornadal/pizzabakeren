package no.bjornadal.pizzabakeren.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import no.bjornadal.pizzabakeren.activity.MainActivity;
import no.bjornadal.pizzabakeren.R;

/**
 * Created by andreasb on 02.10.15.
 */
public class NotificationService extends Service {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Intent resultIntent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, resultIntent, 0);

        Notification notificationBuilder = new Notification.Builder(this)
                .setContentTitle("Pizza bestilling")
                .setContentText("Husk å legge inn din pizza bestilling")
                .setSmallIcon(R.drawable.pizza)
                .setContentIntent(pendingIntent)
                .build();

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationBuilder.flags |= Notification.FLAG_AUTO_CANCEL;
        notificationBuilder.defaults |= Notification.DEFAULT_LIGHTS;
        notificationManager.notify(1, notificationBuilder);

        return START_NOT_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
