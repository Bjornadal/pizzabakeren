package no.bjornadal.pizzabakeren.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import no.bjornadal.pizzabakeren.R;
import no.bjornadal.pizzabakeren.model.Order;
import no.bjornadal.pizzabakeren.model.OrderSummary;

import java.util.List;

/**
 * Created by andreasb on 04.09.15.
 */
public class OrderSummaryArrayAdapter extends ArrayAdapter<OrderSummary> {

    private final Context context;
    private final List<OrderSummary> orders;

    public OrderSummaryArrayAdapter(Context context, int resource, List<OrderSummary> objects) {
        super(context, resource, objects);
        this.context = context;
        this.orders = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.order_summary_item, parent, false);

        TextView tvLabel =(TextView)rowView.findViewById(R.id.tvSummaryLabel);
        TextView tvValue =(TextView)rowView.findViewById(R.id.tvSummaryValue);

        OrderSummary order = orders.get(position);
        tvLabel.setText(order.getLabel() + "");
        tvValue.setText(order.getValue() + "");

        return rowView;
    }
}
