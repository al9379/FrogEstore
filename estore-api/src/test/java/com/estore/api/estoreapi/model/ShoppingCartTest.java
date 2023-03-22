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
        int[] expectedProducts = new int[]{};

        ShoppingCart cart = new ShoppingCart(expectedUsername, new int[]{});

        assertEquals(expectedUsername, cart.getUsername());
        assertArrayEquals(expectedProducts, cart.getProducts());
    }

    @Test
    public void setProducts() {
        String expectedUsername = "eli";
        int[] expectedProducts = new int[]{1};

        ShoppingCart cart = new ShoppingCart(expectedUsername, new int[]{});

        cart.setProducts(expectedProducts);

        assertArrayEquals(expectedProducts, cart.getProducts());
    }
}