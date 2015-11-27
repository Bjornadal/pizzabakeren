package no.bjornadal.pizzabakeren.core.repository;

import no.bjornadal.pizzabakeren.core.model.OrderDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Created by alfredw on 9/18/15.
 */
public interface OrderRepository extends MongoRepository<OrderDocument, String> {

    List<OrderDocument> findByGroupIdAndDate(String groupId, String date);

    OrderDocument findByUsernameAndGroupIdAndDate(String username, String groupId, String date);
}
