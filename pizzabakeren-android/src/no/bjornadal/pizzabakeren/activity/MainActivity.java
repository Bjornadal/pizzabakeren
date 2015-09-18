package no.bjornadal.pizzabakeren.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import no.bjornadal.pizzabakeren.service.OrderService;
import no.nb.pizzabakeren.R;
import org.springframework.util.StringUtils;

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
}
