package com.estore.api.estoreapi.persistence;

import com.estore.api.estoreapi.model.ShoppingCart;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

@Component
public class ShoppingCartFileDAO implements ShoppingCartDAO{
    private Map<String, ShoppingCart> shoppingCartMap;
    private final ObjectMapper objectMapper;
    private final String filename;

    /**
     * Creates a ShoppingCart File DAO Object
     * @param filename file name to read and write to
     * @param objectMapper JSON object converter
     * @throws IOException if there's an issue with the file
     */
    public ShoppingCartFileDAO(@Value("${carts.file}")  String filename, ObjectMapper objectMapper) throws IOException {
        this.filename = filename;
        this.objectMapper = objectMapper;
        load();
    }

    /**
     * Loads the shopping carts from the json file
     * @throws IOException if there's an issue reading from the file
     */
    private void load() throws IOException {
        shoppingCartMap = new TreeMap<>();
        ShoppingCart[] cartArray = objectMapper.readValue(new File(filename), ShoppingCart[].class);

        for(ShoppingCart cart : cartArray) {
            shoppingCartMap.put(cart.getUsername(), cart);
        }
    }

    /**
     * Saves the shopping carts from the map to the json file
     * @throws IOException fi there's an issue writing to the file
     */
    private void save() throws IOException {
        ShoppingCart[] cartArray = shoppingCartMap.values().toArray(new ShoppingCart[0]);
        objectMapper.writeValue(new File(filename), cartArray);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ShoppingCart getShoppingCart(String username) throws IOException {
        return shoppingCartMap.get(username);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean createShoppingCart(String username) throws IOException {
        if(shoppingCartMap.containsKey(username)) return false;
        shoppingCartMap.put(username, new ShoppingCart(username));
        save();
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean updateShoppingCart(ShoppingCart shoppingCart) throws IOException {
        if(!shoppingCartMap.containsKey(shoppingCart.getUsername())) return false;
        shoppingCartMap.put(shoppingCart.getUsername(), shoppingCart);
        save();
        return true;
    }

    @Override
    public boolean deleteShoppingCart(String username) throws IOException {
        if (this.getShoppingCart(username) == null) return false;
        shoppingCartMap.remove(username);
        save();
        return true;
    }
}
