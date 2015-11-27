package no.bjornadal.pizzabakeren.activity;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import no.bjornadal.pizzabakeren.R;
import no.bjornadal.pizzabakeren.alarm.AlarmReceiver;
import no.bjornadal.pizzabakeren.service.NotificationService;
import no.bjornadal.pizzabakeren.service.OrderService;

/**
 * Created by andreasb on 11.09.15.
 */
public class MainActivity extends Activity {

    public static final String PREFS_NAME = "MySettings";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        String fullname = settings.getString("fullname", "");
        String group = settings.getString("group", "");

        EditText etFullname = ((EditText)findViewById(R.id.etFullname));
        EditText etGroup = ((EditText)findViewById(R.id.etGroup));

        etFullname.setText(fullname);
        etGroup.setText(group);

        Intent i = new Intent("no.bjornadal.events.ALARM_START");
        sendBroadcast(i);
    }

    public void startOrder(View view) {
        String fullname = ((EditText)findViewById(R.id.etFullname)).getText().toString();
        String group = ((EditText)findViewById(R.id.etGroup)).getText().toString();

        if (fullname.isEmpty() || group.isEmpty()) {
            Toast toast = Toast.makeText(getApplicationContext(), "Du må skrive inn navn og gruppe", Toast.LENGTH_LONG);
            toast.show();
        }
        else {
            OrderService.getInstance().getCurrentOrder().setUsername(fullname);
            OrderService.getInstance().getCurrentOrder().setGroupId(group);

            SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
            SharedPreferences.Editor editor = settings.edit();
            editor.putString("fullname", fullname);
            editor.putString("group", group);
            editor.commit();

            Intent intent = new Intent(this, SelectPizzaActivity.class);
            startActivity(intent);
        }
    }

    public void viewCurrentOrders(View view) {
        String group = ((EditText)findViewById(R.id.etGroup)).getText().toString().trim().toLowerCase();

        if (group.isEmpty()) {
            Toast toast = Toast.makeText(getApplicationContext(), "Du må skrive inn gruppe", Toast.LENGTH_LONG);
            toast.show();
        }
        else {
            Intent intent = new Intent(this, ViewOrdersActivity.class);
            intent.putExtra("group", group);
            startActivity(intent);
        }
    }
}
