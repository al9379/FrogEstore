package com.estore.api.estoreapi.persistence;

import com.estore.api.estoreapi.model.ShoppingCart;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.util.Map;

public class ShoppingCartFileDAO implements ShoppingCartDAO{
    private Map<String, ShoppingCart> shoppingCartMap;
    private final ObjectMapper objectMapper;
    private final String filename;
    private static int nextId;

    /**
     * Creates a ShoppingCart File DAO Object
     * @param filename file name to read and write to
     * @param objectMapper JSON object converter
     * @throws IOException if there's an issue with the file
     */
    public ShoppingCartFileDAO(@Value("data/shoppingCarts.json") String filename, ObjectMapper objectMapper) throws IOException {
        this.filename = filename;
        this.objectMapper = objectMapper;
    }

    @Override
    public ShoppingCart getShoppingCart(String username) throws IOException {
        return null;
    }

    @Override
    public ShoppingCart createShoppingCart(String username) throws IOException {
        return null;
    }

    @Override
    public ShoppingCart updateShoppingCart(ShoppingCart shoppingCart) throws IOException {
        return null;
    }
}
