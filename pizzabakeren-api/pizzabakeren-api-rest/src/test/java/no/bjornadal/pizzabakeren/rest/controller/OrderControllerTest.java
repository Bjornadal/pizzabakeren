package no.bjornadal.pizzabakeren.rest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import no.bjornadal.pizzabakeren.core.model.OrderDocument;
import no.bjornadal.pizzabakeren.core.service.OrderService;
import no.bjornadal.pizzabakeren.model.OrderResource;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class OrderControllerTest {

    private MockMvc mockMvc;

    private OrderController orderController;

    @Mock
    private OrderService mockedOrderService;

    @Before
    public void setup() {
        orderController = new OrderController(mockedOrderService);
        mockMvc = MockMvcBuilders.standaloneSetup(orderController).build();
    }

    @Test
    public void saveOrder() throws Exception {
        OrderResource orderResource = new OrderResource(28, "Sprite", "g1", "Ola Nordmann", "2015-09-25");
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(orderResource);

        mockMvc.perform(post("/orders").contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isOk());
    }

    @Test
    public void listAllOrders() throws Exception{
        List<OrderDocument> content = Arrays.asList(new OrderDocument(28, "Sprite", "g1", "Ola Nordmann", "2015-09-25"));
        Page<OrderDocument> orders = new PageImpl<>(content);

        when(mockedOrderService.getOrders("g1", "2015-09-25", new PageRequest(0, 10))).thenReturn(orders);
        mockMvc.perform(get("/orders/g1/2015-09-25"))
                .andExpect(status().isOk());
    }
}
