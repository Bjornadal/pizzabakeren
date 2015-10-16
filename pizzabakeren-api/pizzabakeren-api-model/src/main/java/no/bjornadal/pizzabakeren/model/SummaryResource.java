package no.bjornadal.pizzabakeren.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by alfredw on 10/16/15.
 */
public class SummaryResource {
    private final Object id;
    private final int numberOfResource;

    @JsonCreator
    public SummaryResource(@JsonProperty("id") Object id,
                           @JsonProperty("total") int numberOfResource) {
        this.id = id;
        this.numberOfResource = numberOfResource;
    }

    public Object getId() {
        return id;
    }

    public int getNumberOfResource() {
        return numberOfResource;
    }
}
