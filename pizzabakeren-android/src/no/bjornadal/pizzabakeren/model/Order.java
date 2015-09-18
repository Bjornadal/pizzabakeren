package no.bjornadal.pizzabakeren.model;

/**
 * Created by andreasb on 18.09.15.
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

    public void setPizzaNumber(int pizzaNumber) {
        this.pizzaNumber = pizzaNumber;
    }

    public String getSoda() {
        return soda;
    }

    public void setSoda(String soda) {
        this.soda = soda;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
