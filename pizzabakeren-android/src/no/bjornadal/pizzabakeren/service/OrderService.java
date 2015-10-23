package no.bjornadal.pizzabakeren.service;

import android.app.Activity;
import android.os.AsyncTask;
import android.widget.ListView;
import android.widget.Toast;
import no.bjornadal.pizzabakeren.adapter.OrderArrayAdapter;
import no.bjornadal.pizzabakeren.adapter.OrderSummaryArrayAdapter;
import no.bjornadal.pizzabakeren.model.Order;
import no.bjornadal.pizzabakeren.model.OrderSummary;
import no.bjornadal.pizzabakeren.model.OrderWrapper;
import no.bjornadal.pizzabakeren.model.WorkingOrder;
import no.bjornadal.pizzabakeren.R;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

/**
 * Created by andreasb on 18.09.15.
 */
public class OrderService {

    private Activity viewOrdersActivity;
    private Activity confirmActivity;
    private WorkingOrder currentOrder = new WorkingOrder();
    private final String url = "http://bjornadal.no/pizzabakeren/orders";

    public static OrderService orderService;


    public void viewOrders(String group, String date, Activity activity) {
        viewOrdersActivity = activity;
        new GetOrdersTask().execute(group, date);
    }

    public void saveCurrentOrder(Activity activity) {
        confirmActivity = activity;

        Order order = new Order();
        order.setPizzaNumber(currentOrder.getPizza().getNumber());
        order.setSoda(currentOrder.getSoda().getName());
        order.setGroupId(currentOrder.getGroupId().trim().toLowerCase());
        order.setUsername(currentOrder.getUsername().trim());
        order.setTotalPrice(currentOrder.getPizza().getPrice());

        new OrderSaveTask().execute(order);

        resetInstance();
    }

    public WorkingOrder getCurrentOrder() {
        return currentOrder;
    }

    public void setCurrentOrder(WorkingOrder currentOrder) {
        this.currentOrder = currentOrder;
    }

    public static OrderService getInstance() {
        if (orderService == null) {
            orderService = new OrderService();
        }

        return orderService;
    }

    public static void resetInstance() {
        orderService = null;
    }

    private void refreshOrders(ResponseEntity<OrderWrapper> orders) {
        if (orders == null) {
            return;
        }

        List<Order> ordersList = orders.getBody().getOrders();

        final ListView listView = (ListView) viewOrdersActivity.findViewById(R.id.orderList);
        OrderArrayAdapter adapter = new OrderArrayAdapter(viewOrdersActivity, R.layout.pizza_item, ordersList);
        listView.setAdapter(adapter);

        final ListView listViewPizzaSummmary = (ListView) viewOrdersActivity.findViewById(R.id.pizzaSummaryList);
        OrderSummaryArrayAdapter summaryArrayAdapter = new OrderSummaryArrayAdapter(viewOrdersActivity, R.layout.order_summary_item,
                OrderSummary.transform(orders.getBody().getPizzaSummary()));
        listViewPizzaSummmary.setAdapter(summaryArrayAdapter);

        final ListView listViewSodaSummmary = (ListView) viewOrdersActivity.findViewById(R.id.sodaSummaryList);
        OrderSummaryArrayAdapter summarySodaArrayAdapter = new OrderSummaryArrayAdapter(viewOrdersActivity, R.layout.order_summary_item,
                OrderSummary.transform(orders.getBody().getSodaSummary()));
        listViewSodaSummmary.setAdapter(summarySodaArrayAdapter);
    }


    private void showSaveResponse(ResponseEntity<String> response) {
        Toast toast;

        if (response.getStatusCode().equals(HttpStatus.OK)) {
            toast = Toast.makeText(confirmActivity.getApplicationContext(), "Din ordre er sendt!", Toast.LENGTH_LONG);
        }
        else if (response.getStatusCode().equals(HttpStatus.ALREADY_REPORTED)) {
            toast = Toast.makeText(confirmActivity.getApplicationContext(), "Du har allerede lagt inn ordre i dag...", Toast.LENGTH_LONG);
        }
        else {
            toast = Toast.makeText(confirmActivity.getApplicationContext(), "Det skjedde en intern feil ved bestilling...", Toast.LENGTH_LONG);
        }

        toast.show();
    }

    private class OrderSaveTask extends AsyncTask<Order, Void, ResponseEntity<String>> {
        @Override
        protected ResponseEntity<String> doInBackground(Order... orders) {
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            restTemplate.getMessageConverters().add(new StringHttpMessageConverter());

            Order order = orders[0];
            ResponseEntity<String> response = restTemplate.postForEntity(url, order, String.class);

            return response;
        }

        @Override
        public void onPostExecute(ResponseEntity<String> response) {
            showSaveResponse(response);
        }
    }

    private class GetOrdersTask extends AsyncTask<String, Void, ResponseEntity<OrderWrapper>> {
        @Override
        protected ResponseEntity<OrderWrapper> doInBackground(String... params) {
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            restTemplate.getMessageConverters().add(new StringHttpMessageConverter());

            ResponseEntity<OrderWrapper> response = restTemplate.getForEntity(url + "/{groupId}/{date}", OrderWrapper.class, params[0], params[1]);

            return response;
        }

        @Override
        public void onPostExecute(ResponseEntity<OrderWrapper> orders) {
            refreshOrders(orders);
        }
    }


}
