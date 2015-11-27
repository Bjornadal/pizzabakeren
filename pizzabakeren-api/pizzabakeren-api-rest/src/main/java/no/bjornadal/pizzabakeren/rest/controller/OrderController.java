package no.bjornadal.pizzabakeren.rest.controller;

import no.bjornadal.pizzabakeren.core.model.OrderDocument;
import no.bjornadal.pizzabakeren.core.service.OrderService;
import no.bjornadal.pizzabakeren.model.OrderResource;
import no.bjornadal.pizzabakeren.model.OrderWrapper;
import no.bjornadal.pizzabakeren.rest.assembler.OrderWrapperAssembler;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/orders")
public class OrderController {

    private OrderService orderService;
    private OrderWrapperAssembler orderWrapperAssembler = new OrderWrapperAssembler();

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
    public OrderWrapper listAllOrders(
            @PathVariable(value = "groupId") String groupId,
            @PathVariable(value = "date") String date) {

        List<OrderDocument> orders = orderService.getOrders(groupId, date);
        return orderWrapperAssembler.toResource(orders);
    }
}
