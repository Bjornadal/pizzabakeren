package no.bjornadal.pizzabakeren.core;

import no.bjornadal.pizzabakeren.core.model.Pizza;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by alfredw on 10/16/15.
 */
public class PizzaTest {

    @Test
    public void pizzaIsEqualToAnotherPizza() {
        Pizza pizza1 = new Pizza(28);
        Pizza pizza2 = new Pizza(28);

        assertTrue(pizza1.equals(pizza2));
    }

    @Test
    public void sortListOfPizzaAfterNumber() {
        List<Pizza> pizzas = createPizzas();

        Collections.sort(pizzas);

        assertEquals(4, pizzas.get(0).getNumber());
        assertEquals(17, pizzas.get(1).getNumber());
        assertEquals(17, pizzas.get(2).getNumber());
        assertEquals(28, pizzas.get(3).getNumber());
    }

    @Test
    public void listAlreadyContainsPizza() {
        List<Pizza> pizzas = createPizzas();

        assertTrue(pizzas.contains(new Pizza(28)));
    }

    @Test
    public void listDoesntContainPizza() {
        List<Pizza> pizzas = createPizzas();

        assertFalse(pizzas.contains(new Pizza(19)));
    }

    private List<Pizza> createPizzas() {
        List<Pizza> pizzas = new ArrayList<>();
        pizzas.add(new Pizza(28));
        pizzas.add(new Pizza(17));
        pizzas.add(new Pizza(4));
        pizzas.add(new Pizza(17));
        return pizzas;
    }
}
