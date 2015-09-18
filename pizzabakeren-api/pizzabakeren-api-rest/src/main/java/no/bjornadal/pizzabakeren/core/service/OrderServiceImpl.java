package no.bjornadal.pizzabakeren.core.service;

import no.bjornadal.pizzabakeren.core.repository.OrderRepository;
import no.bjornadal.pizzabakeren.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * Created by alfredw on 9/18/15.
 */
public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public void saveOrder(Order order) {
        orderRepository.save(order);
    }

    @Override
    public Page<Order> getOrders(String groupId, String date, PageRequest pageRequest) {
        return orderRepository.findByGroupIdAndDate(groupId, date, pageRequest);
    }
}
