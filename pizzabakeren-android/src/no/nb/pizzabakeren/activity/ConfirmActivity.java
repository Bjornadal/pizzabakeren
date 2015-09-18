package no.nb.pizzabakeren.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import no.nb.pizzabakeren.R;

/**
 * Created by andreasb on 11.09.15.
 */
public class ConfirmActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.confirm);
    }

    public void sendOrder(View view) {

    }
}
