package no.bjornadal.pizzabakeren.rest.controller;

import no.bjornadal.pizzabakeren.model.OrderResource;

/**
 * Created by alfredw on 10/16/15.
 */
public class OrderResourceBuilder {

    private int pizzaNumber;
    private String soda;
    private String groupId;
    private String username;
    private int price;

    public OrderResourceBuilder() {
        pizzaNumber = 28;
        soda = "Sprite";
        groupId = "pizzaGroup";
        username = "Ola Nordmann";
        price = 69;
    };

    public OrderResourceBuilder withPizzaNumber(int pizzaNumber) {
        this.pizzaNumber = pizzaNumber;
        return this;
    }

    public OrderResourceBuilder withSoda(String soda) {
        this.soda = soda;
        return this;
    }

    public OrderResourceBuilder withGroupId(String groupId) {
        this.groupId = groupId;
        return this;
    }

    public OrderResourceBuilder withUsername(String username) {
        this.username = username;
        return this;
    }

    public OrderResourceBuilder withPrice(int price) {
        this.price = price;
        return this;
    }

    public OrderResource build() {
        return new OrderResource(pizzaNumber, soda, groupId, username, price);
    }
}
