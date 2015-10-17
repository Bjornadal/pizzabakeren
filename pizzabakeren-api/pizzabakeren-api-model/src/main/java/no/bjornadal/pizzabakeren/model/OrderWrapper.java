package no.bjornadal.pizzabakeren.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.hateoas.ResourceSupport;

import java.util.List;
import java.util.Map;

/**
 * Created by Alfred on 16.10.2015.
 */
public class OrderWrapper extends ResourceSupport {

    private final List<OrderResource> orders;
    private final Map<Integer, Integer> pizzaSummary;
    private final Map<String, Integer> sodaSummary;

    @JsonCreator
    public OrderWrapper(@JsonProperty("orders") List<OrderResource> orders,
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

    public int getNumberOfOrders() {
        return orders.size();
    }
}
