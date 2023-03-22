package com.estore.controller;

import com.estore.api.estoreapi.controller.ShoppingCartController;
import com.estore.api.estoreapi.model.ShoppingCart;
import com.estore.api.estoreapi.persistence.ShoppingCartDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@Tag("Controller-Tier")
public class ShoppingCartControllerTests {
    private ShoppingCartController shoppingCartController;
    private ShoppingCartDAO mockShoppingCartDAO;

    @BeforeEach
    public void setupShoppingCartController(){
        mockShoppingCartDAO = mock(ShoppingCartDAO.class);
        shoppingCartController = new ShoppingCartController(mockShoppingCartDAO);
    }

    @Test
    public void testCreateShoppingCart() throws IOException {
        String username = "Eli";

        when(mockShoppingCartDAO.createShoppingCart(username)).thenReturn(true);

        ResponseEntity<String> response = shoppingCartController.createShoppingCart(username);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testCreateShoppingCartConflict() throws IOException {
        String username = "Eli";

        when(mockShoppingCartDAO.createShoppingCart(username)).thenReturn(false);

        ResponseEntity<String> response = shoppingCartController.createShoppingCart(username);

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
    }

    @Test
    public void testCreateShoppingCartHandleException() throws IOException {
        String username = "Eli";

        doThrow(new IOException()).when(mockShoppingCartDAO).createShoppingCart(username);

        ResponseEntity<String> response = shoppingCartController.createShoppingCart(username);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    public void testGetShoppingCart() throws IOException {
        ShoppingCart cart = new ShoppingCart("Eli", new int[]{1,2});

        when(mockShoppingCartDAO.getShoppingCart(cart.getUsername())).thenReturn(cart);

        ResponseEntity<ShoppingCart> response = shoppingCartController.getCart(cart.getUsername());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(cart, response.getBody());
    }

    @Test
    public void testGetShoppingCartNotFound() throws IOException {
        ShoppingCart cart = new ShoppingCart("Eli", new int[]{1,2});

        when(mockShoppingCartDAO.getShoppingCart(cart.getUsername())).thenReturn(null);

        ResponseEntity<ShoppingCart> response = shoppingCartController.getCart(cart.getUsername());

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testGetShoppingCartHandleException() throws IOException {
        ShoppingCart cart = new ShoppingCart("Eli", new int[]{1,2});

        doThrow(new IOException()).when(mockShoppingCartDAO).getShoppingCart(cart.getUsername());

        ResponseEntity<ShoppingCart> response = shoppingCartController.getCart(cart.getUsername());

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }
}
