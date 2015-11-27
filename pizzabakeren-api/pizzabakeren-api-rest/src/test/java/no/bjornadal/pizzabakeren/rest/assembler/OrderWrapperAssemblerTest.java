package no.bjornadal.pizzabakeren.rest.assembler;

import no.bjornadal.pizzabakeren.core.model.OrderDocument;
import no.bjornadal.pizzabakeren.model.OrderWrapper;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

/**
 * Created by Alfred on 17.10.2015.
 */
public class OrderWrapperAssemblerTest {

    private OrderWrapperAssembler orderWrapperAssembler;

    @Before
    public void setup() {
        orderWrapperAssembler = new OrderWrapperAssembler();
    }

    @Test
    public void orderDocumentsToOrderWrapperWithThreeOrders() {
        List<OrderDocument> orderDocuments = createOrderDocuments();

        OrderWrapper orderWrapper = orderWrapperAssembler.toResource(orderDocuments);

        assertEquals(3, orderWrapper.getNumberOfOrders());
    }

    @Test
    public void orderDocumentsToOrderWrapperWithPizzaSummary() {
        List<OrderDocument> orderDocuments = createOrderDocuments();

        OrderWrapper orderWrapper = orderWrapperAssembler.toResource(orderDocuments);

        assertThat(orderWrapper.getPizzaSummary().keySet(), hasSize(3));
    }

    @Test
    public void orderDocumentsToOrderWrapperWithSodaSummary() {
        List<OrderDocument> orderDocuments = createOrderDocuments();

        OrderWrapper orderWrapper = orderWrapperAssembler.toResource(orderDocuments);

        assertThat(orderWrapper.getSodaSummary().keySet(), hasSize(1));
    }

    private List<OrderDocument> createOrderDocuments() {
        List<OrderDocument> orderDocuments = new ArrayList<>();
        orderDocuments.add(new OrderDocument(1, "fanta", "pizza group", "ola nordmann", "2015-10-15", 59));
        orderDocuments.add(new OrderDocument(2, "fanta", "pizza group", "per nordmann", "2015-10-15", 59));
        orderDocuments.add(new OrderDocument(3, "fanta", "pizza group", "pål nordmann", "2015-10-15", 59));
        return orderDocuments;
    }
}
