package no.bjornadal.pizzabakeren.model;

/**
 * Created by andreasb on 18.09.15.
 */
public class Order {
    private String groupId;
    private String username;
    private int pizzaNumber;
    private String soda;
    private int totalPrice;

    public Order() {
    }

    public Order(int pizzaNumber, String soda, String groupId, String username) {
        this.pizzaNumber = pizzaNumber;
        this.soda = soda;
        this.groupId = groupId;
        this.username = username;
    }

    public Order(String groupId, String username, int pizzaNumber, String soda, int totalPrice) {
        this.groupId = groupId;
        this.username = username;
        this.pizzaNumber = pizzaNumber;
        this.soda = soda;
        this.totalPrice = totalPrice;
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

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }


}
