package no.bjornadal.pizzabakeren.core.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by alfredw on 9/18/15.
 */
@Document(collection = "order")
public class OrderDocument {

    @Id
    private String id;
    private int pizzaNumber;
    private String soda;
    private String groupId;
    private String username;
    private String date;
    private int totalPrice;

    @PersistenceConstructor
    public OrderDocument(int pizzaNumber, String soda, String groupId, String username, String date, int totalPrice) {
        this.pizzaNumber = pizzaNumber;
        this.soda = soda;
        this.groupId = groupId;
        this.username = username;
        this.date = date;
        this.totalPrice = totalPrice;
    }

    public String getId() {
        return id;
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

    public int getTotalPrice() {
        return totalPrice;
    }

    @Override
    public String toString() {
        return "OrderDocument{" +
                "id='" + id + '\'' +
                ", pizzaNumber=" + pizzaNumber +
                ", soda='" + soda + '\'' +
                ", groupId='" + groupId + '\'' +
                ", username='" + username + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
