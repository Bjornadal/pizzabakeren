package no.bjornadal.pizzabakeren.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import no.nb.pizzabakeren.R;
import no.bjornadal.pizzabakeren.adapter.SodaArrayAdapter;
import no.bjornadal.pizzabakeren.model.Soda;

import java.util.ArrayList;
import java.util.List;

public class SelectSodaActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.soda_list);

        List<Soda> sodas = new ArrayList<Soda>();
        sodas.add(new Soda("Cola"));
        sodas.add(new Soda("Cola Zero"));
        sodas.add(new Soda("Fanta"));
        sodas.add(new Soda("Urge"));
        sodas.add(new Soda("Sprite"));

        final ListView listView = (ListView) findViewById(R.id.sodaList);
        SodaArrayAdapter adapter = new SodaArrayAdapter(this, R.layout.soda_item, sodas);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(parent.getContext(), ConfirmActivity.class);
                startActivity(intent);
            }
        });
    }
}
