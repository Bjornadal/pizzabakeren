package no.bjornadal.pizzabakeren.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import no.bjornadal.pizzabakeren.model.Order;
import no.bjornadal.pizzabakeren.model.Pizza;
import no.bjornadal.pizzabakeren.R;

import java.util.List;

/**
 * Created by andreasb on 04.09.15.
 */
public class OrderArrayAdapter extends ArrayAdapter<Order> {

    private final Context context;
    private final List<Order> orders;

    public OrderArrayAdapter(Context context, int resource, List<Order> objects) {
        super(context, resource, objects);
        this.context = context;
        this.orders = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.order_item, parent, false);

        TextView tvOrderUsername =(TextView)rowView.findViewById(R.id.tvOrderUsername);
        TextView tvOrderPizzaNumber =(TextView)rowView.findViewById(R.id.tvOrderPizzaNumber);
        TextView tvOrderSoda =(TextView)rowView.findViewById(R.id.tvOrderSoda);

        Order order = orders.get(position);
        tvOrderUsername.setText(order.getUsername() + "");
        tvOrderPizzaNumber.setText(order.getPizzaNumber() + "");
        tvOrderSoda.setText(order.getSoda() + "");

        return rowView;
    }
}
