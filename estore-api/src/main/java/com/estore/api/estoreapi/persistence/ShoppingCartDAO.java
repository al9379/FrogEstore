package com.estore.api.estoreapi.persistence;

import java.io.IOException;

import com.estore.api.estoreapi.model.ShoppingCart;

public interface ShoppingCartDAO {

    /**
     * Retrieves the shopping cart with the given username
     * @param username
     * @return {@linkplain ShoppingCart shopping cart}
     * @throws IOException if an issue with storage
     */
    ShoppingCart getShoppingCart(String username) throws IOException;

    /**
     * Creates and saves a new {@linkplain ShoppingCart shoping cart}
     * @param username
     * @return new {@linkplain ShoppingCart shoping cart} if successfull, null otherwise
     * @throws IOException if an issue with storage
     */
    boolean createShoppingCart(String username) throws IOException;

    /**
     * Updates and saves a {@linkplain ShoppingCart shoping cart}
     * @param shoppingCart
     * @return updated {@linkplain ShoppingCart shoping cart} if successfull, null otherwise
     * @throws IOException if an issue with storage
     */
    ShoppingCart updateShoppingCart(ShoppingCart shoppingCart) throws IOException;
}
