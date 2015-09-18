package no.bjornadal.pizzabakeren.service;

import no.bjornadal.pizzabakeren.model.Order;
import no.bjornadal.pizzabakeren.model.WorkingOrder;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

/**
 * Created by andreasb on 18.09.15.
 */
public class OrderService {

    private WorkingOrder currentOrder = new WorkingOrder();
    private String url = "http://localhost/pizzabakeren";

    public static OrderService orderService;

    public void saveCurrentOrder() {
        Order order = new Order();
        order.setPizzaNumber(currentOrder.getPizza().getNumber());
        order.setSoda(currentOrder.getSoda().getName());
        order.setGroupId(currentOrder.getGroupId());
        order.setUsername(currentOrder.getUsername());

//        RestTemplate restTemplate = new RestTemplate();
//        restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
//        restTemplate.postForObject(url, order, Order.class);

        resetInstance();
    }

    public WorkingOrder getCurrentOrder() {
        return currentOrder;
    }

    public void setCurrentOrder(WorkingOrder currentOrder) {
        this.currentOrder = currentOrder;
    }

    public static OrderService getInstance() {
        if (orderService == null) {
            orderService = new OrderService();
        }

        return orderService;
    }

    public static void resetInstance() {
        orderService = null;
    }
}
