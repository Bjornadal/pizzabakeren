package no.bjornadal.pizzabakeren.rest.controller;

import no.bjornadal.pizzabakeren.core.service.OrderService;
import no.bjornadal.pizzabakeren.model.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RestController
public class OrderController {

    private static final Logger LOG = LoggerFactory.getLogger(OrderController.class);

    private OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @RequestMapping(value = "/{groupId}/{date}", method = RequestMethod.GET)
    public String listAllOrders(@PathVariable(value = "groupId") String groupId,
                                @PathVariable(value = "date") String date,
                                @RequestParam(value = "page", required = false, defaultValue = "0") int page,
                                @RequestParam(value = "size", required = false, defaultValue = "10") int size) {
        PageRequest pageRequest = new PageRequest(page, size);
        Page<Order> orders = orderService.getOrders(groupId, date, pageRequest);
        orders.forEach(order -> {
            System.out.println(order.getGroupId() + "\t" + order.getDate());
        });
        return "yo!";
    }
}
