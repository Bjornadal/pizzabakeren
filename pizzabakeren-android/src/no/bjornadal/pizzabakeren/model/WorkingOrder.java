package no.bjornadal.pizzabakeren.model;

/**
 * Created by andreasb on 18.09.15.
 */
public class WorkingOrder {

    private String groupId;
    private String username;
    private Pizza pizza;
    private Soda soda;

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

    public Pizza getPizza() {
        return pizza;
    }

    public void setPizza(Pizza pizza) {
        this.pizza = pizza;
    }

    public Soda getSoda() {
        return soda;
    }

    public void setSoda(Soda soda) {
        this.soda = soda;
    }
}
