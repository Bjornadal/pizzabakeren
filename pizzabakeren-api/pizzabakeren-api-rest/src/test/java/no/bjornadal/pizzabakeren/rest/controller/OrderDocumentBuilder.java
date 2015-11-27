package no.bjornadal.pizzabakeren.rest.controller;

import no.bjornadal.pizzabakeren.core.model.OrderDocument;

/**
 * Created by Alfred on 17.10.2015.
 */
public class OrderDocumentBuilder {
    private int pizzaNumber;
    private String soda;
    private String groupId;
    private String username;
    private String date;
    private int price;

    public OrderDocumentBuilder() {
        pizzaNumber = 28;
        soda = "Sprite";
        groupId = "pizzaGroup";
        username = "Ola Nordmann";
        date = "2015-10-15";
        price = 69;
    };

    public OrderDocumentBuilder withPizzaNumber(int pizzaNumber) {
        this.pizzaNumber = pizzaNumber;
        return this;
    }

    public OrderDocumentBuilder withSoda(String soda) {
        this.soda = soda;
        return this;
    }

    public OrderDocumentBuilder withGroupId(String groupId) {
        this.groupId = groupId;
        return this;
    }

    public OrderDocumentBuilder withUsername(String username) {
        this.username = username;
        return this;
    }

    public OrderDocumentBuilder withDate(String date) {
        this.date = date;
        return this;
    }

    public OrderDocumentBuilder withPrice(int price) {
        this.price = price;
        return this;
    }

    public OrderDocument build() {
        return new OrderDocument(pizzaNumber, soda, groupId, username, date, price);
    }
}
