package no.bjornadal.pizzabakeren.core.service;

import no.bjornadal.pizzabakeren.core.model.OrderDocument;
import no.bjornadal.pizzabakeren.model.OrderResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * Created by alfredw on 9/18/15.
 */
public interface OrderService {

    void saveOrder(OrderResource orderResource);

    Page<OrderDocument> getOrders(String groupId, String date, PageRequest pageRequest);
}
