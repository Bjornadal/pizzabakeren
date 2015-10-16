package no.bjornadal.pizzabakeren.core.service;

import no.bjornadal.pizzabakeren.core.model.OrderDocument;
import no.bjornadal.pizzabakeren.model.OrderResource;

import java.util.List;

/**
 * Created by alfredw on 9/18/15.
 */
public interface OrderService {

    void saveOrder(OrderResource orderResource);

    OrderDocument getOrder(String username, String groupId, String date);

    List<OrderResource> getOrders(String groupId, String date);
}
