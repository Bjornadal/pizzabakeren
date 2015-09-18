package no.bjornadal.pizzabakeren.service;

import no.bjornadal.pizzabakeren.model.Order;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

/**
 * Created by andreasb on 18.09.15.
 */
public class OrderService {

    String url = "http://localhost/pizzabakeren";

    public void saveOrder(Order order) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
        restTemplate.postForObject(url, order, Order.class);
    }
}
