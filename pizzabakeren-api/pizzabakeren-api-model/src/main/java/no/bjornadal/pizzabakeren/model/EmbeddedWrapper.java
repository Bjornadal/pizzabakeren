package no.bjornadal.pizzabakeren.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by alfredw on 9/18/15.
 */
public class EmbeddedWrapper {
    private List<OrderResource> orders = new ArrayList<>();
    private Map<Integer, Integer> pizzaSummary;
    private Map<String, Integer> sodaSummary;
    private int numberOfPizzas;

    @JsonCreator
    public EmbeddedWrapper(@JsonProperty("orders") List<OrderResource> orders,
                           @JsonProperty("pizzaSummary") Map<Integer, Integer> pizzaSummary,
                           @JsonProperty("sodaSummary") Map<String, Integer> sodaSummary) {
        this.orders = orders;
        this.pizzaSummary = pizzaSummary;
        this.sodaSummary = sodaSummary;
    }

    public List<OrderResource> getOrders() {
        return orders;
    }

    public Map<Integer, Integer> getPizzaSummary() {
        return pizzaSummary;
    }

    public Map<String, Integer> getSodaSummary() {
        return sodaSummary;
    }

    public int getNumberOfPizzas() {
        numberOfPizzas = 0;
        for (Integer integer : pizzaSummary.keySet()) {
            numberOfPizzas += pizzaSummary.get(integer);
        }
        return numberOfPizzas;
    }
}
