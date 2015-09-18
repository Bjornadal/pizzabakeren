package no.bjornadal.pizzabakeren.core.repository;

import no.bjornadal.pizzabakeren.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.Repository;

/**
 * Created by alfredw on 9/18/15.
 */
public interface OrderRepository extends Repository<Order, Long>{

    void save(Order order);
    Page<Order> findByGroupIdAndDate(String groupId, String date, PageRequest pageRequest);
}
