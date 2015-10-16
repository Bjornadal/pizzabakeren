package no.bjornadal.pizzabakeren.rest.controller;

import no.bjornadal.pizzabakeren.core.model.Pizza;
import no.bjornadal.pizzabakeren.core.service.OrderService;
import no.bjornadal.pizzabakeren.model.EmbeddedWrapper;
import no.bjornadal.pizzabakeren.model.OrderResource;
import no.bjornadal.pizzabakeren.model.SearchResource;
import no.bjornadal.pizzabakeren.model.SummaryResource;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(value = "/orders")
public class OrderController {

    private OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity saveOrder(@RequestBody OrderResource orderResource) {
        if (orderService.getOrder(orderResource.getUsername(), orderResource.getGroupId(), DateTime.now().toString("yyyy-MM-dd")) != null) {
            return new ResponseEntity(HttpStatus.ALREADY_REPORTED);
        }
        orderService.saveOrder(orderResource);

        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/{groupId}/{date}", method = RequestMethod.GET)
    public SearchResource listAllOrders(
            @PathVariable(value = "groupId") String groupId,
            @PathVariable(value = "date") String date) {

        List<OrderResource> orders = orderService.getOrders(groupId, date);
        Map<Integer, Integer> pizzaSummary = getPizzaSummary(orders);
        Map<String, Integer> sodaSummary = getSodaSummary(orders);
        EmbeddedWrapper embeddedWrapper = new EmbeddedWrapper(orders, pizzaSummary, sodaSummary);
        return new SearchResource(embeddedWrapper);
    }

    private Map<Integer, Integer> getPizzaSummary(List<OrderResource> orders) {
        Map<Integer, Integer> summary = new TreeMap<>();
        for (OrderResource order : orders) {
            int pizzaNr = order.getPizzaNumber();
            if(summary.containsKey(pizzaNr)) {
                int number = summary.get(pizzaNr);
                summary.put(pizzaNr, ++number);
            } else {
                summary.put(pizzaNr, 1);
            }
        }
        return summary;
    }

    private Map<String, Integer> getSodaSummary(List<OrderResource> orders) {
        Map<String, Integer> summary = new TreeMap<>();
        for (OrderResource order : orders) {
            String soda = order.getSoda().toLowerCase();
            if(summary.containsKey(soda)) {
                int number = summary.get(soda);
                summary.put(soda, ++number);
            } else {
                summary.put(soda, 1);
            }
        }
        return summary;
    }
}
