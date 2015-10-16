package no.bjornadal.pizzabakeren.rest.controller;

import no.bjornadal.pizzabakeren.core.service.OrderService;
import no.bjornadal.pizzabakeren.model.OrderResource;
import no.bjornadal.pizzabakeren.model.SearchResource;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OrderControllerTest {

    @Mock
    private OrderService mockedOrderService;

    private OrderController orderController;
    private static final String PIZZA_GROUP = "pizzaGroup";

    @Before
    public void setup() {
        orderController = new OrderController(mockedOrderService);
    }

    @Test
    public void saveOrder() throws Exception {
        OrderResource orderResource = new OrderResourceBuilder().build();

        orderController.saveOrder(orderResource);

        verify(mockedOrderService).saveOrder(orderResource);
    }

    @Test
    public void listAllOrders() {
        List<OrderResource> orders = Arrays.asList(new OrderResourceBuilder().build());
        when(mockedOrderService.getOrders(PIZZA_GROUP, "2015-09-25")).thenReturn(orders);

        SearchResource searchResource = orderController.listAllOrders(PIZZA_GROUP, "2015-09-25");

        assertNotNull(searchResource);
    }

    @Test
    public void ordersWithPizzas_4_27_28_28_ShouldHavePizzaSummary() {
        List<OrderResource> orders = createOrders();
        when(mockedOrderService.getOrders(PIZZA_GROUP, "2015-09-25")).thenReturn(orders);

        SearchResource searchResource = orderController.listAllOrders(PIZZA_GROUP, "2015-09-25");

        Map<Integer, Integer> pizzaSummary = searchResource.getEmbeddedWrapper().getPizzaSummary();
        assertEquals(new Integer(1), pizzaSummary.get(4));
        assertEquals(new Integer(1), pizzaSummary.get(27));
        assertEquals(new Integer(2), pizzaSummary.get(28));
    }

    @Test
    public void ordersWithSodaSpriteSpriteColaFantaShouldHaveSodaSummary() throws Exception{
        List<OrderResource> orders = createOrders();
        when(mockedOrderService.getOrders(PIZZA_GROUP, "2015-09-25")).thenReturn(orders);

        SearchResource searchResource = orderController.listAllOrders(PIZZA_GROUP, "2015-09-25");

        Map<String, Integer> sodaSummary = searchResource.getEmbeddedWrapper().getSodaSummary();
        assertEquals(new Integer(1), sodaSummary.get("Cola"));
        assertEquals(new Integer(1), sodaSummary.get("Fanta"));
        assertEquals(new Integer(2), sodaSummary.get("Sprite"));
    }

    private List<OrderResource> createOrders() {
        List<OrderResource> orders = new ArrayList<>();
        orders.add(new OrderResourceBuilder().withUsername("Ola").build());
        orders.add(new OrderResourceBuilder().withUsername("Per").withPizzaNumber(4).build());
        orders.add(new OrderResourceBuilder().withUsername("Karl").withPizzaNumber(27).withSoda("Cola").build());
        orders.add(new OrderResourceBuilder().withUsername("Morten").withPizzaNumber(28).withSoda("Fanta").build());
        return orders;
    }
}
