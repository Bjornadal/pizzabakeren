package no.bjornadal.pizzabakeren.rest.controller;

import no.bjornadal.pizzabakeren.core.model.OrderDocument;
import no.bjornadal.pizzabakeren.core.service.OrderService;
import no.bjornadal.pizzabakeren.model.OrderResource;
import no.bjornadal.pizzabakeren.model.SearchResource;
import no.bjornadal.pizzabakeren.rest.assembler.SearchResourceAssembler;
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
    private SearchResourceAssembler searchResourceAssembler = new SearchResourceAssembler();

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "Hello World from Pizzabakeren!";
    }

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public void saveOrder(@RequestBody OrderResource orderResource) {
        orderService.saveOrder(orderResource);
    }

    @RequestMapping(value = "/{groupId}/{date}", method = RequestMethod.GET)
    public SearchResource listAllOrders(
            @PathVariable(value = "groupId") String groupId,
            @PathVariable(value = "date") String date,
            @RequestParam(value = "page", required = false, defaultValue = "0") int page,
            @RequestParam(value = "size", required = false, defaultValue = "10") int size) {

        Page<OrderDocument> orders = orderService.getOrders(groupId, date, new PageRequest(page, size));
        return searchResourceAssembler.toResource(orders);
    }
}
