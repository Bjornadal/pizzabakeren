package no.bjornadal.pizzabakeren.model;

import java.util.List;
import java.util.Map;

/**
 * Created by andreasb on 21.10.15.
 */
public class OrderWrapper {
    private List<Order> orders;
    private Map<String, Integer> pizzaSummary;
    private Map<String, Integer> sodaSummary;
    private int numberOfOrders;

    public OrderWrapper() {
    }

    public OrderWrapper(List<Order> orders, Map<String, Integer> pizzaSummary, Map<String, Integer> sodaSummary) {
        this.orders = orders;
        this.pizzaSummary = pizzaSummary;
        this.sodaSummary = sodaSummary;
    }

    public OrderWrapper(List<Order> orders, Map<String, Integer> pizzaSummary, Map<String, Integer> sodaSummary, int numberOfOrders) {
        this.orders = orders;
        this.pizzaSummary = pizzaSummary;
        this.sodaSummary = sodaSummary;
        this.numberOfOrders = numberOfOrders;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public Map<String, Integer> getPizzaSummary() {
        return pizzaSummary;
    }

    public void setPizzaSummary(Map<String, Integer> pizzaSummary) {
        this.pizzaSummary = pizzaSummary;
    }

    public Map<String, Integer> getSodaSummary() {
        return sodaSummary;
    }

    public void setSodaSummary(Map<String, Integer> sodaSummary) {
        this.sodaSummary = sodaSummary;
    }

    public int getNumberOfOrders() {
        return numberOfOrders;
    }

    public void setNumberOfOrders(int numberOfOrders) {
        this.numberOfOrders = numberOfOrders;
    }
}
