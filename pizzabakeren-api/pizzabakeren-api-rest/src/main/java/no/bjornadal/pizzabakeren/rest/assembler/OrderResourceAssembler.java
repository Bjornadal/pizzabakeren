package no.bjornadal.pizzabakeren.rest.assembler;

import no.bjornadal.pizzabakeren.core.model.OrderDocument;
import no.bjornadal.pizzabakeren.model.OrderResource;
import org.springframework.hateoas.ResourceAssembler;

/**
 * Created by Alfred on 17.10.2015.
 */
public class OrderResourceAssembler implements ResourceAssembler<OrderDocument, OrderResource> {

    @Override
    public OrderResource toResource(OrderDocument orderDocument) {
        return new OrderResource(orderDocument.getPizzaNumber(),
                orderDocument.getSoda(),
                orderDocument.getGroupId(),
                orderDocument.getUsername(),
                orderDocument.getTotalPrice());
    }
}
