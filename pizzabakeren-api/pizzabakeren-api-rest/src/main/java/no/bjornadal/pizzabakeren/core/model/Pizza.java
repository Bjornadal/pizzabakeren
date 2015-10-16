package no.bjornadal.pizzabakeren.core.model;

/**
 * Created by alfredw on 10/16/15.
 */
public class Pizza implements Comparable<Pizza> {

    private final int number;

    public Pizza(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public int compareTo(Pizza otherPizza) {
        return this.number - otherPizza.getNumber();
    }

    @Override
    public boolean equals(Object otherObject) {
        if (this == otherObject) return true;
        if (otherObject == null || getClass() != otherObject.getClass()) return false;

        Pizza pizza = (Pizza) otherObject;

        return number == pizza.number;

    }

    @Override
    public int hashCode() {
        return number;
    }
}
