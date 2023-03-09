package com.estore.api.estoreapi.persistence;

import com.estore.api.estoreapi.model.ShoppingCart;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.webservices.client.WebServiceClientTest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

/**
 *  Test the Shopping Cart File DAO class
 */
@Tag("Persistence-tier")
class ShoppingCartFileDAOTest {
    ShoppingCartFileDAO shoppingCartFileDAO;
    ShoppingCart[] testCarts;
    ObjectMapper mockObjectMapper;

    @BeforeEach
    public void setupShoppingCartFileDAO() throws IOException {
        mockObjectMapper = mock(ObjectMapper.class);
        testCarts = new ShoppingCart[3];
        testCarts[0] = new ShoppingCart("eli");
        testCarts[1] = new ShoppingCart("dylan");
        testCarts[2] = new ShoppingCart("alex");

        when(mockObjectMapper
                .readValue(new File("test_file.txt"), ShoppingCart[].class))
                .thenReturn(testCarts);
        shoppingCartFileDAO = new ShoppingCartFileDAO("test_file.txt", mockObjectMapper);
    }

    @Test
    public void testGetShoppingCart() {
        ShoppingCart cart = assertDoesNotThrow(() -> shoppingCartFileDAO.getShoppingCart("eli"));

        assertEquals(cart, testCarts[0]);
    }

    @Test
    public void testCreateShoppingCart() {
        String testUsername = "another_username";

        boolean result = assertDoesNotThrow(() -> shoppingCartFileDAO.createShoppingCart(testUsername),
                "Unexpected exception thrown");

        assertTrue(result);
        ShoppingCart actual = assertDoesNotThrow(() -> shoppingCartFileDAO.getShoppingCart(testUsername));
        assertEquals(testUsername, actual.getUsername());
    }

    @Test
    public void testUpdateShoppingCart() {
        ShoppingCart cart = new ShoppingCart("dylan");

        boolean result = assertDoesNotThrow(() -> shoppingCartFileDAO.updateShoppingCart(cart),
                "Unexpected exception thrown");

        assertTrue(result);
        ShoppingCart actual = assertDoesNotThrow(() -> shoppingCartFileDAO.getShoppingCart(cart.getUsername()));
        assertEquals(cart, actual);
    }

    @Test
    public void testSaveException() throws IOException {
        doThrow(new IOException())
                .when(mockObjectMapper)
                .writeValue(any(File.class), any(ShoppingCart[].class));

        String testUsername = "Eli";

        assertThrows(IOException.class,
                () -> shoppingCartFileDAO.createShoppingCart(testUsername),
                "IOException not thrown");
    }

    @Test
    public void testGetShoppingCartNotFound() {
        ShoppingCart cart = assertDoesNotThrow(() -> shoppingCartFileDAO.getShoppingCart("andrew"),
                "Unexpected exception thrown");

        assertNull(cart);
    }

    @Test
    public void testUpdateShoppingCartNotFound() {
        ShoppingCart cart = new ShoppingCart("mason");

        boolean result = assertDoesNotThrow(() -> shoppingCartFileDAO.updateShoppingCart(cart),
                "Unexpected exception thrown");
    }

    @Test
    public void testConstructorException() throws IOException {
        ObjectMapper mockObjectMapper = mock(ObjectMapper.class);

        doThrow(new IOException())
                .when(mockObjectMapper)
                .readValue(new File("test_file.txt"), ShoppingCart[].class);

        assertThrows(IOException.class,
                () -> new ShoppingCartFileDAO("test_file.txt", mockObjectMapper),
                "Unexpected exception thrown");
    }
}