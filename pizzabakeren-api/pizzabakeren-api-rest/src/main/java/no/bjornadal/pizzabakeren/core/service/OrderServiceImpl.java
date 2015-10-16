package no.bjornadal.pizzabakeren.core.service;

import no.bjornadal.pizzabakeren.core.model.OrderDocument;
import no.bjornadal.pizzabakeren.core.repository.OrderRepository;
import no.bjornadal.pizzabakeren.model.OrderResource;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alfredw on 9/18/15.
 */
@Service
public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public void saveOrder(OrderResource orderResource) {
        String date = DateTime.now().toString("yyyy-MM-dd");
        orderRepository.save(new OrderDocument(orderResource.getPizzaNumber(), orderResource.getSoda(), orderResource.getGroupId().toLowerCase(), orderResource.getUsername().toLowerCase(), date, orderResource.getTotalPrice()));
    }

    @Override
    public OrderDocument getOrder(String username, String groupId, String date) {
        return orderRepository.findByUsernameAndGroupIdAndDate(username.toLowerCase(), groupId.toLowerCase(), date);
    }

    @Override
    public List<OrderResource> getOrders(String groupId, String date) {
        List<OrderDocument> orderDocuments = orderRepository.findByGroupIdAndDate(groupId.toLowerCase(), date);
        List<OrderResource> orderResources = new ArrayList<>();
        for (OrderDocument orderDocument : orderDocuments) {
            OrderResource orderResource = new OrderResource(orderDocument.getPizzaNumber(), orderDocument.getSoda(),
                    orderDocument.getGroupId(), orderDocument.getUsername(), orderDocument.getTotalPrice());

            orderResources.add(orderResource);
        }
        return orderResources;
    }
}
