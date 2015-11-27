package no.bjornadal.pizzabakeren.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import no.bjornadal.pizzabakeren.R;
import no.bjornadal.pizzabakeren.model.Pizza;

import java.util.List;

/**
 * Created by andreasb on 04.09.15.
 */
public class PizzaArrayAdapter extends ArrayAdapter<Pizza> {

    private final Context context;
    private final List<Pizza> pizzas;

    public PizzaArrayAdapter(Context context, int resource, List<Pizza> objects) {
        super(context, resource, objects);
        this.context = context;
        this.pizzas = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.pizza_item, parent, false);
        TextView tvNumber =(TextView)rowView.findViewById(R.id.tvNumber);
        TextView tvName =(TextView)rowView.findViewById(R.id.tvName);
        TextView tvDescription =(TextView)rowView.findViewById(R.id.tvDescription);
        TextView tvPrice =(TextView)rowView.findViewById(R.id.tvPrice);
        Pizza pizza = pizzas.get(position);
        tvNumber.setText(pizza.getNumber() + "");
        tvName.setText(pizza.getName());
        tvPrice.setText(pizza.getPrice() + ",-");
        tvDescription.setText(pizza.getDescription());

        return rowView;
    }
}
