package no.nb.pizzabakeren.model;

/**
 * Created by andreasb on 04.09.15.
 */
public class Pizza {
    private int number;
    private String name;
    private String description;
    private int price;

    public Pizza(int number, String name, String description, int price) {
        this.number = number;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
