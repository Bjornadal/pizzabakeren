package no.bjornadal.pizzabakeren.model;

/**
 * Created by alfredw on 9/18/15.
 */
public class Order {
    private int pizzaNumber;
    private String soda;
    private String groupId;
    private String username;
    private String date;

    public Order() {
    }

    public Order(int pizzaNumber, String soda, String groupId, String username, String date) {
        this.pizzaNumber = pizzaNumber;
        this.soda = soda;
        this.groupId = groupId;
        this.username = username;
        this.date = date;
    }

    public int getPizzaNumber() {
        return pizzaNumber;
    }

    public String getSoda() {
        return soda;
    }

    public String getGroupId() {
        return groupId;
    }

    public String getUsername() {
        return username;
    }

    public String getDate() {
        return date;
    }
}
