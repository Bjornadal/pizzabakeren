package no.bjornadal.pizzabakeren.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import no.bjornadal.pizzabakeren.service.OrderService;
import no.bjornadal.pizzabakeren.R;

/**
 * Created by andreasb on 11.09.15.
 */
public class ConfirmActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.confirm);

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        OrderService orderService = OrderService.getInstance();
        TextView tvChosenPizza = (TextView) findViewById(R.id.tvChosenPizza);
        tvChosenPizza.setText(orderService.getCurrentOrder().getPizza().getName());

        TextView tvChosenSoda = (TextView) findViewById(R.id.tvChosenSoda);
        tvChosenSoda.setText(orderService.getCurrentOrder().getSoda().getName());
    }

    public void saveOrder(View view) {
        OrderService.getInstance().saveCurrentOrder(this);

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
