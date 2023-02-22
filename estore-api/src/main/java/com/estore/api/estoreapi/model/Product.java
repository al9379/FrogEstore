package com.estore.api.estoreapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Product {
    @JsonProperty("id") private int id;
    @JsonProperty("stock") private int stock;
    @JsonProperty("name") private String name;

    public Product(
        @JsonProperty("id") int id, 
        @JsonProperty("stock") int stock,
        @JsonProperty("name") String name
    ) {
        this.id = id;
        this.stock = stock;
        this.name = name;
    }

    public int getId() {return this.id;}

    public void setName(String name) {this.name = name;}

    public String getName() {return this.name;}

    @Override
    public String toString() {
        return String.format("%s : %d: %d ", this.name, this.stock, this.id);
    }
}
