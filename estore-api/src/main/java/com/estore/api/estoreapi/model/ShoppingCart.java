package com.estore.api.estoreapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ShoppingCart {
    @JsonProperty("username") private String username;
    @JsonProperty("products") private Product[] products;

    /**
     * Create a shopping cart with the given username
     * @param username user's username
     */
    public ShoppingCart(
        @JsonProperty("username") String username
    ){
        this.username = username;
        this.products = new Product[]{};
    }

    /**
     * Retrieves the username associated with the shopping car
     * @return username
     */
    public String getUsername() {return this.username;}

    /**
     * Retrieves the list of products in the shopping cart
     * @return list of products
     */
    public Product[] getProducts() {return this.products;}

    /**
     * Sets the array of products
     * @param products the new array of products
     */
    public void setProducts(Product[] products) {
        this.products = products;
    }
}
