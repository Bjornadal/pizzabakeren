package no.bjornadal.pizzabakeren.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import no.nb.pizzabakeren.R;
import no.bjornadal.pizzabakeren.adapter.PizzaArrayAdapter;
import no.bjornadal.pizzabakeren.model.Pizza;

import java.util.ArrayList;
import java.util.List;

public class SelectPizzaActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pizza_list);

        List<Pizza> pizzas = new ArrayList<Pizza>();
        pizzas.add(new Pizza(1, "DEN ENKLE", "Ost, tomatsaus – og bare det!", 38));
        pizzas.add(new Pizza(2, "Kvess", "Ost, tomatsaus, skinke og sjampinjong", 51));
        pizzas.add(new Pizza(3, "DRØMMEN" , "Ost, tomatsaus, kjøttdeig og paprika", 51));
        pizzas.add(new Pizza(4, "PIZZABAKEREN SPESIAL" , "Ost, tomatsaus, kjøttdeig, løk og bacon", 51));
        pizzas.add(new Pizza(5, "SNADDER" , "Ost, tomatsaus, kjøttdeig, løk og ananas", 51));
        pizzas.add(new Pizza(6, "MIX" , "Ost, tomatsaus, pepperoni, løk og paprika", 51));
        pizzas.add(new Pizza(7, "MEKSIKANEREN" , "Ost, tomatsaus, marinert kylling, marinert biff, nachoschips, hvitløk, mais og chili", 65));
        pizzas.add(new Pizza(8, "BIFFEN" , "Ost, tomatsaus, marinert biff i strimler, bacon og hvitløk", 65));
        pizzas.add(new Pizza(9, "DEN MARINERTE\n" , "Ost, tomatsaus, marinert biff i strimler, sjampinjong og løk", 60));
        pizzas.add(new Pizza(10, "PEPPERSVENNEN" , "Ost, tomatsaus, pepperbiff i strimler, sjampinjong, løk og paprika ", 60));

        final ListView listView = (ListView) findViewById(R.id.pizzaList);
        PizzaArrayAdapter adapter = new PizzaArrayAdapter(this, R.layout.pizza_item, pizzas);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(parent.getContext(), SelectSodaActivity.class);
                startActivity(intent);
            }
        });
    }
}
