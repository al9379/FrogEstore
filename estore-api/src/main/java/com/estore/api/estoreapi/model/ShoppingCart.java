package com.estore.api.estoreapi.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ShoppingCart {
    @JsonProperty("username") private String username;
    @JsonProperty("products") private List<Product> products;


    /**
     * Create a shopping cart with the given username
     * @param username user's username
     */
    public ShoppingCart(
        @JsonProperty("username") String username
    ){
        this.username = username;
        this.products = new ArrayList<>();
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
    public List<Product> getProducts() {return this.products;}

    /**
     * Adds a products to the shopping cart
     * @param product
     */
    public void addProduct(Product product) {this.products.add(product);}

    /**
     * Removes a product from the shopping cart
     * @param product
     */
    public void removeProduct(Product product) {this.products.remove(product);}
}
