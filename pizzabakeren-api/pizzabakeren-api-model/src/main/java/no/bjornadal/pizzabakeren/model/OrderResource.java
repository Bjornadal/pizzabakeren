package no.bjornadal.pizzabakeren.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.hateoas.ResourceSupport;

/**
 * Created by alfredw on 9/18/15.
 */
public class OrderResource extends ResourceSupport {
    private final int pizzaNumber;
    private final String soda;
    private final String groupId;
    private final String username;
    private final String date;

    @JsonCreator
    public OrderResource(@JsonProperty("pizzaNumber") int pizzaNumber,
                         @JsonProperty("soda") String soda,
                         @JsonProperty("groupId") String groupId,
                         @JsonProperty("username") String username,
                         @JsonProperty("date") String date) {
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

    public String getDate() { return date; }

}