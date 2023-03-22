package com.estore.api.estoreapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Product {
    @JsonProperty("id") private int id;
    @JsonProperty("stock") private int stock;
    @JsonProperty("price") private float price;
    @JsonProperty("name") private String name;
    @JsonProperty("description") private String description;

    /**
     * Create a product with the given attributes
     * @param id ID of the product
     * @param stock Amount of product in stock
     * @param price Price of product
     * @param name Name of product
     * @param description Description of product
     */
    public Product(
        @JsonProperty("id") int id, 
        @JsonProperty("stock") int stock,
        @JsonProperty("price") float price,
        @JsonProperty("name") String name,
        @JsonProperty("description") String description
    ) {
        this.id = id;
        this.stock = stock;
        this.price = price;
        this.name = name;
        this.description = description;
    }

    /**
     * Retrieves the id of the product
     * @return id of the product
     */
    public int getId() {return this.id;}

    /**
     * Sets the name of the product
     * @param name
     */
    public void setName(String name) {this.name = name;}

    /**
     * Retrieves the name of the product
     * @return the name of the product
     */
    public String getName() {return this.name;}
    
    /**
     * Retrieves the amount of product in stock
     * @return amout of product in stock
     */
    public int getStock() {return this.stock;}

    /**
     * Set the amount of product in stock
     * @param stock amount of product in stock
     */
    public void setStock(int stock) {this.stock = stock;}

    /**
     * Retrieves the price of the product
     * @return price of the product
     */
    public float getPrice() {return this.price;}

    /**
     * Sets the price of the product
     * @param price price of the product
     */
    public void setPrice(float price) {this.price = price;}

    /**
     * Retrieves the description of the product
     * @return description of the product
     */
    public String getDescription() {return this.description;}

    /**
     * Sets the description of the product
     * @param description description of the product
     */
    public void setDescription(String description) {this.description = description;}

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return String.format("%s : %f: %d ", this.name, this.price, this.id);
    }
}
