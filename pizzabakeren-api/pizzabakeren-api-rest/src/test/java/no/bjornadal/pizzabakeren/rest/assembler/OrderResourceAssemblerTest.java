package no.bjornadal.pizzabakeren.rest.assembler;

import no.bjornadal.pizzabakeren.core.model.OrderDocument;
import no.bjornadal.pizzabakeren.model.OrderResource;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Alfred on 17.10.2015.
 */
public class OrderResourceAssemblerTest {

    @Test
    public void orderDocumentToOrderResource() {
        OrderDocument orderDocument = new OrderDocument(28, "sprite", "pizza group", "ola nordmann", "2015-10-15", 69);
        OrderResourceAssembler orderResourceAssembler = new OrderResourceAssembler();

        OrderResource orderResource = orderResourceAssembler.toResource(orderDocument);

        assertEquals(orderDocument.getPizzaNumber(), orderResource.getPizzaNumber());
        assertEquals(orderDocument.getSoda(), orderResource.getSoda());
        assertEquals(orderDocument.getGroupId(), orderResource.getGroupId());
        assertEquals(orderDocument.getUsername(), orderResource.getUsername());
        assertEquals(orderDocument.getTotalPrice(), orderResource.getTotalPrice());
    }
}
