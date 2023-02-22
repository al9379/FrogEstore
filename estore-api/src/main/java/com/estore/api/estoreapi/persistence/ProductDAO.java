package com.estore.api.estoreapi.persistence;

import java.io.IOException;
import java.util.Map;

import com.estore.api.estoreapi.model.Product;

public interface ProductDAO {
    Product[] getAll() throws IOException;
    Product[] searchFor(String name) throws IOException;
    Product getProduct(int id) throws IOException;
    Product createProduct(Product product) throws IOException;
    Product updateProduct(Product product) throws IOException;
    boolean deleteProduct(int id) throws IOException;
}