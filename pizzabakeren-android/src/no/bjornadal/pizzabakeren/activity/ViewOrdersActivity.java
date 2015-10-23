package no.bjornadal.pizzabakeren.activity;

import android.app.Activity;
import android.os.Bundle;
import no.bjornadal.pizzabakeren.service.OrderService;
import no.bjornadal.pizzabakeren.R;
import org.joda.time.DateTime;

/**
 * Created by andreasb on 09.10.15.
 */
public class ViewOrdersActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_orders);

        String date = DateTime.now().toString("yyyy-MM-dd");
        String group;
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                group = null;
            } else {
                group = extras.getString("group");
            }
        } else {
            group = (String) savedInstanceState.getSerializable("group");
        }


        OrderService orderService = OrderService.getInstance();
        orderService.viewOrders(group, date, this);
    }
}
