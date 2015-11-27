package no.bjornadal.pizzabakeren.alarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by andreasb on 02.10.15.
 */
public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "AlarmBootReceiver.onReceive()", Toast.LENGTH_LONG).show();
    }
}
