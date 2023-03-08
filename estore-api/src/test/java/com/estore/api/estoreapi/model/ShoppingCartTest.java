package com.estore.api.estoreapi.model;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The unit tests for the ShoppingCart class
 */
@Tag("Model-tier")
class ShoppingCartTest {

    @Test
    public void getters() {
        String expectedUsername = "eli";
        Product[] expectedProducts = new Product[]{};

        ShoppingCart cart = new ShoppingCart(expectedUsername);

        assertEquals(expectedUsername, cart.getUsername());
        assertArrayEquals(expectedProducts, cart.getProducts());
    }

    @Test
    public void setProducts() {
        String expectedUsername = "eli";
        Product[] expectedProducts = new Product[]{new Product(1, 2, 3, "testProduct", "testDescription")};

        ShoppingCart cart = new ShoppingCart(expectedUsername);

        cart.setProducts(expectedProducts);

        assertArrayEquals(expectedProducts, cart.getProducts());
    }
}