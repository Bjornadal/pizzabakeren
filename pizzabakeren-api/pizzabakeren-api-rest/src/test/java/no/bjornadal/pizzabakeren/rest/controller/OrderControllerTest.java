package no.bjornadal.pizzabakeren.rest.controller;

import no.bjornadal.pizzabakeren.core.model.OrderDocument;
import no.bjornadal.pizzabakeren.core.service.OrderService;
import no.bjornadal.pizzabakeren.model.OrderResource;
import no.bjornadal.pizzabakeren.model.OrderWrapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OrderControllerTest {

    private static final String PIZZA_GROUP = "pizzaGroup";
    private static final String DATE = "2015-09-25";

    @Mock
    private OrderService mockedOrderService;

    @InjectMocks
    private OrderController orderController;

    @Test
    public void saveOrder() throws Exception {
        OrderResource orderResource = new OrderResourceBuilder().build();

        orderController.saveOrder(orderResource);

        verify(mockedOrderService).saveOrder(orderResource);
    }

    @Test
    public void listAllOrders() {
        List<OrderDocument> orders = createOrders();
        when(mockedOrderService.getOrders(PIZZA_GROUP, DATE)).thenReturn(orders);

        OrderWrapper orderWrapper = orderController.listAllOrders(PIZZA_GROUP, DATE);

        assertNotNull(orderWrapper);
        assertThat(orderWrapper.getOrders(), hasSize(4));
        assertEquals(4, orderWrapper.getNumberOfOrders());
    }

    @Test
    public void ordersWithPizzas_4_27_28_28_ShouldHavePizzaSummary() {
        List<OrderDocument> orders = createOrders();
        when(mockedOrderService.getOrders(PIZZA_GROUP, DATE)).thenReturn(orders);

        OrderWrapper orderWrapper = orderController.listAllOrders(PIZZA_GROUP, DATE);

        Map<Integer, Integer> pizzaSummary = orderWrapper.getPizzaSummary();
        assertThat(pizzaSummary.keySet(), contains(4, 27, 28));
        assertThat(pizzaSummary.values(), contains(1, 1, 2));
    }

    @Test
    public void ordersWithSodaSpriteSpriteColaFantaShouldHaveSodaSummary() throws Exception{
        List<OrderDocument> orders = createOrders();
        when(mockedOrderService.getOrders(PIZZA_GROUP, DATE)).thenReturn(orders);

        OrderWrapper orderWrapper = orderController.listAllOrders(PIZZA_GROUP, DATE);

        Map<String, Integer> sodaSummary = orderWrapper.getSodaSummary();
        assertThat(sodaSummary.keySet(), contains("cola", "fanta", "sprite"));
        assertThat(sodaSummary.values(), contains(1, 1, 2));
    }

    private List<OrderDocument> createOrders() {
        List<OrderDocument> orders = new ArrayList<>();
        orders.add(new OrderDocumentBuilder().withUsername("Ola").withPizzaNumber(28).withSoda("Sprite").build());
        orders.add(new OrderDocumentBuilder().withUsername("Per").withPizzaNumber(4).withSoda("Sprite").build());
        orders.add(new OrderDocumentBuilder().withUsername("Karl").withPizzaNumber(27).withSoda("Cola").build());
        orders.add(new OrderDocumentBuilder().withUsername("Morten").withPizzaNumber(28).withSoda("Fanta").build());
        return orders;
    }
}
