package com.estore.api.estoreapi.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Product {
    @JsonProperty("id") private int id;
    @JsonProperty("stock") private int stock;
    @JsonProperty("price") private float price;
    @JsonProperty("name") private String name;
    @JsonProperty("description") private String description;

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

    public int getId() {return this.id;}

    public void setName(String name) {this.name = name;}

    public String getName() {return this.name;}
    
    public int getStock() {return this.stock;}

    public void setStock(int stock) {this.stock = stock;}

    public float getPrice() {return this.price;}

    public void setPrice(float price) {this.price = price;}

    public String getDescription() {return this.description;}

    public void setDescription(String description) {this.description = description;}

    @Override
    public String toString() {
        return String.format("%s : %d: %d ", this.name, this.price, this.id);
    }
}
