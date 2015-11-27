package no.bjornadal.pizzabakeren.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by andreasb on 23.10.15.
 */
public class OrderSummary {
    private String label;
    private int value;

    public OrderSummary() {
    }

    public OrderSummary(String label, int value) {
        this.label = label;
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public static List<OrderSummary> transform(Map<String, Integer> map) {
        List<OrderSummary> summaries = new ArrayList<OrderSummary>();
        for (Map.Entry<String, Integer> entry : map.entrySet())
        {
            summaries.add(new OrderSummary(entry.getKey(), entry.getValue()));
        }

        return summaries;
    }
}
