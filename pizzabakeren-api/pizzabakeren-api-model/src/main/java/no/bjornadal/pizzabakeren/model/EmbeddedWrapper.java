package no.bjornadal.pizzabakeren.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alfredw on 9/18/15.
 */
public class EmbeddedWrapper {
    private List<OrderResource> orders = new ArrayList<>();

    public List<OrderResource> getOrders() {
        return orders;
    }
}
