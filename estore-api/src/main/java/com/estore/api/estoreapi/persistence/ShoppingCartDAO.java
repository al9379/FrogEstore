package com.estore.api.estoreapi.persistence;

import java.io.IOException;

import com.estore.api.estoreapi.model.ShoppingCart;

public interface ShoppingCartDAO {

    /**
     * Retrieves the shopping cart with the given username
     * @param username name of user
     * @return {@linkplain ShoppingCart shopping cart}, null if a shopping
     * cart already exists with that name
     * @throws IOException if an issue with storage
     */
    ShoppingCart getShoppingCart(String username) throws IOException;

    /**
     * Creates and saves a new {@linkplain ShoppingCart shopping cart}
     * @param username name of user
     * @return true if successful, false otherwise
     * @throws IOException if an issue with storage
     */
    boolean createShoppingCart(String username) throws IOException;

    /**
     * Updates and saves a {@linkplain ShoppingCart shoping cart}
     * @param shoppingCart cart to update
     * @return updated {@linkplain ShoppingCart shoping cart} if successfull, null otherwise
     * @throws IOException if an issue with storage
     */
    boolean updateShoppingCart(ShoppingCart shoppingCart) throws IOException;

    boolean deleteShoppingCart(String username) throws IOException;
}
