package no.bjornadal.pizzabakeren.rest.assembler;

import no.bjornadal.pizzabakeren.core.model.OrderDocument;
import no.bjornadal.pizzabakeren.model.OrderResource;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by alfredw on 9/25/15.
 */
public class OrderResourceAssemblerTest {

    @Test
    public void toResource() {
        OrderDocument orderDocument = new OrderDocument(28, "Sprite", "g1", "Ola Nordmann", "2015-09-25");
        OrderResourceAssembler orderResourceAssembler = new OrderResourceAssembler();

        OrderResource orderResource = orderResourceAssembler.toResource(orderDocument);

        assertEquals(28, orderResource.getPizzaNumber());
        assertEquals("Sprite", orderResource.getSoda());
        assertEquals("g1", orderResource.getGroupId());
        assertEquals("Ola Nordmann", orderResource.getUsername());
    }
}
