package no.bjornadal.pizzabakeren.rest.assembler;

import no.bjornadal.pizzabakeren.core.model.OrderDocument;
import no.bjornadal.pizzabakeren.model.OrderResource;
import no.bjornadal.pizzabakeren.rest.controller.OrderController;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

/**
 * Created by alfredw on 9/18/15.
 */
@Component
public class OrderResourceAssembler extends ResourceAssemblerSupport<OrderDocument, OrderResource> {

    public OrderResourceAssembler() {
        super(OrderController.class, OrderResource.class);
    }

    @Override
    public OrderResource toResource(OrderDocument orderDocument) {
        return new OrderResource(orderDocument.getPizzaNumber(),
                orderDocument.getSoda(),
                orderDocument.getGroupId(),
                orderDocument.getUsername(),
                orderDocument.getTotalPrice());
    }
}
