package no.bjornadal.pizzabakeren.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import no.nb.pizzabakeren.R;
import no.bjornadal.pizzabakeren.model.Soda;

import java.util.List;

/**
 * Created by andreasb on 04.09.15.
 */
public class SodaArrayAdapter extends ArrayAdapter<Soda> {

    private final Context context;
    private final List<Soda> sodas;

    public SodaArrayAdapter(Context context, int resource, List<Soda> objects) {
        super(context, resource, objects);
        this.context = context;
        this.sodas = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.soda_item, parent, false);
        TextView tvName =(TextView)rowView.findViewById(R.id.tvName);
        Soda soda = sodas.get(position);
        tvName.setText(soda.getName());

        return rowView;
    }
}
