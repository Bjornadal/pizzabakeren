package no.bjornadal.pizzabakeren.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import no.bjornadal.pizzabakeren.service.OrderService;
import no.bjornadal.pizzabakeren.R;
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

        final List<Pizza> pizzas = new ArrayList<Pizza>();
        pizzas.add(new Pizza(1, "DEN ENKLE", "Ost, tomatsaus – og bare det!", 38));
        pizzas.add(new Pizza(2, "Kvess", "Ost, tomatsaus, skinke og sjampinjong", 51));
        pizzas.add(new Pizza(3, "DRØMMEN" , "Ost, tomatsaus, kjøttdeig og paprika", 51));
        pizzas.add(new Pizza(4, "PIZZABAKEREN SPESIAL" , "Ost, tomatsaus, kjøttdeig, løk og bacon", 51));
        pizzas.add(new Pizza(5, "SNADDER" , "Ost, tomatsaus, kjøttdeig, løk og ananas", 51));
        pizzas.add(new Pizza(6, "MIX" , "Ost, tomatsaus, pepperoni, løk og paprika", 51));
        pizzas.add(new Pizza(7, "MEKSIKANEREN" , "Ost, tomatsaus, marinert kylling, marinert biff, nachoschips, hvitløk, mais og chili", 65));
        pizzas.add(new Pizza(8, "BIFFEN" , "Ost, tomatsaus, marinert biff i strimler, bacon og hvitløk", 65));
        pizzas.add(new Pizza(9, "DEN MARINERTE" , "Ost, tomatsaus, marinert biff i strimler, sjampinjong og løk", 60));
        pizzas.add(new Pizza(10, "PEPPERSVENNEN" , "Ost, tomatsaus, pepperbiff i strimler, sjampinjong, løk og paprika ", 60));
        pizzas.add(new Pizza(11, "FLAMMEN" , "Ost, tacosaus, kjøttdeig, nachoschips og jalapeños", 60));
        pizzas.add(new Pizza(12, "TACOKYLLINGEN" , "Ost, tacosaus, marinert kylling, nachoschips og jalapeños", 65));
        pizzas.add(new Pizza(13, "KOKKENS KYLLING" , "Ost, tomatsaus, marinert kylling, løk, mais og sjampinjong", 60));
        pizzas.add(new Pizza(14, "KOKKENS FAVORITT" , "Ost, tomatsaus, kjøttdeig, luksusbacon og ananas", 60));
        pizzas.add(new Pizza(15, "GNISTEN" , "Ost, tomatsaus, kjøttdeig, løk, hvitløk og jalapeños", 60));
        pizzas.add(new Pizza(16, "LUKSUSKYLLING" , "Ost, tomatsaus, marinert kylling, luksusbacon, løk og paprika", 65));
        pizzas.add(new Pizza(17, "KYLLINGFARMEN" , "Ost, tomatsaus, marinert kylling, chili, pesto og tomater i skiver", 60));
        pizzas.add(new Pizza(18, "VEGETARIANEREN" , "Ost, tomatsaus, ananas, sjampinjong, løk, mais og paprika", 46));
        pizzas.add(new Pizza(19, "KEBABEN" , "Ost, tacosaus, kebabkjøtt, mais, rødløk og jalapeño. Toppes med kebab hvitløksdressing", 65));
        pizzas.add(new Pizza(20, "DREGEN" , "Ost, tomatsaus, skinke og bacon", 51));
        pizzas.add(new Pizza(21, "MR.X" , "Ost, tomatsaus, kjøttdeig, pepperoni, løk og ananas ", 60));
        pizzas.add(new Pizza(22, "CHORIZO SPESIAL" , "Ost, tomatsaus, chorizo, marinert biff og løk", 60));
        pizzas.add(new Pizza(23, "DOBBELDEKKER" , "Ost, tomatsaus, skinke, kjøttdeig og oregano", 60));
        pizzas.add(new Pizza(24, "HEIT KYLLING" , "Ost, tomatsaus, sterk og marinert kylling, bacon og ananas", 60));
        pizzas.add(new Pizza(25, "CHORIZOEN" , "Ost, tomatsaus, chorizo, pesto, oregano og tomat", 60));
        pizzas.add(new Pizza(28, "HOTTENTOTTEN" , "Ost, tomatsaus, pepperbiff, pepperoni og paprika", 60));

        final ListView listView = (ListView) findViewById(R.id.pizzaList);
        PizzaArrayAdapter adapter = new PizzaArrayAdapter(this, R.layout.pizza_item, pizzas);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                OrderService.getInstance().getCurrentOrder().setPizza(pizzas.get(position));
                Intent intent = new Intent(parent.getContext(), SelectSodaActivity.class);
                startActivity(intent);
            }
        });
    }
}
