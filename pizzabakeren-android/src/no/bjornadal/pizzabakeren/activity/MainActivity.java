package no.bjornadal.pizzabakeren.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import no.nb.pizzabakeren.R;

/**
 * Created by andreasb on 11.09.15.
 */
public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

    public void startOrder(View view) {
        Intent intent = new Intent(this, SelectPizzaActivity.class);
        startActivity(intent);
    }
}
