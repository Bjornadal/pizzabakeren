package no.bjornadal.pizzabakeren.core.repository;

import no.bjornadal.pizzabakeren.core.model.OrderDocument;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by alfredw on 9/18/15.
 */
public interface OrderRepository extends MongoRepository<OrderDocument, String> {

    Page<OrderDocument> findByGroupIdAndDate(String groupId, String date, Pageable pageRequest);
}
