package no.bjornadal.pizzabakeren.rest.assembler;

import no.bjornadal.pizzabakeren.core.model.OrderDocument;
import no.bjornadal.pizzabakeren.model.OrderResource;
import no.bjornadal.pizzabakeren.model.OrderWrapper;
import org.springframework.hateoas.ResourceAssembler;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * Created by Alfred on 17.10.2015.
 */
public class OrderWrapperAssembler implements ResourceAssembler<List<OrderDocument>, OrderWrapper> {

    private OrderResourceAssembler orderResourceAssembler = new OrderResourceAssembler();

    @Override
    public OrderWrapper toResource(List<OrderDocument> orderDocuments) {
        List<OrderResource> orders = getOrderResources(orderDocuments);

        Map<Integer, Integer> pizzaSummary = getPizzaSummary(orders);
        Map<String, Integer> sodaSummary = getSodaSummary(orders);
        return new OrderWrapper(orders, pizzaSummary, sodaSummary);
    }

    private List<OrderResource> getOrderResources(List<OrderDocument> orderDocuments) {
        return orderDocuments.stream().map(orderResourceAssembler::toResource).collect(Collectors.toList());
    }

    private Map<Integer, Integer> getPizzaSummary(List<OrderResource> orders) {
        Map<Integer, Integer> summary = new TreeMap<>();
        for (OrderResource order : orders) {
            int pizzaNr = order.getPizzaNumber();
            if(summary.containsKey(pizzaNr)) {
                int number = summary.get(pizzaNr);
                summary.put(pizzaNr, ++number);
            } else {
                summary.put(pizzaNr, 1);
            }
        }
        return summary;
    }

    private Map<String, Integer> getSodaSummary(List<OrderResource> orders) {
        Map<String, Integer> summary = new TreeMap<>();
        for (OrderResource order : orders) {
            String soda = order.getSoda().toLowerCase();
            if(summary.containsKey(soda)) {
                int number = summary.get(soda);
                summary.put(soda, ++number);
            } else {
                summary.put(soda, 1);
            }
        }
        return summary;
    }
}
