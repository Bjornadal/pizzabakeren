package no.bjornadal.pizzabakeren.core.service;

import no.bjornadal.pizzabakeren.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * Created by alfredw on 9/18/15.
 */
public interface OrderService {
    void saveOrder(Order order);
    Page<Order> getOrders(String groupId, String date, PageRequest pageRequest);
}
