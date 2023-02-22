package com.estore.api.estoreapi.persistence;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.estore.api.estoreapi.model.Product;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class ProductFileDAO implements ProductDAO {

    private static final Logger LOG = Logger.getLogger(ProductFileDAO.class.getName());
    Map<Integer, Product> products;
    private ObjectMapper objectMapper;
    private String filename;
    private static int nextId;

    public ProductFileDAO(@Value("${products.file}") String filename, ObjectMapper objectMapper) throws IOException{
        this.filename = filename;
        this.objectMapper = objectMapper;
        load();
    }

    private synchronized static int getNextId() {
        return nextId++;
    }

    private Product[] getProductsArray() {
        ArrayList<Product> productsArrList = new ArrayList<>();
        
        for (Product product : products.values()) {
            productsArrList.add(product);
        }
        Product[] proArray = new Product[productsArrList.size()];
        productsArrList.toArray(proArray);
        return proArray;
    }

    private boolean save() throws IOException {
        Product[] productArray = getProductsArray();
        objectMapper.writeValue(new File(filename), productArray);
        return true;
    }

    private boolean load() throws IOException{
        products = new TreeMap<>();
        nextId = 0;
        Product[] productArray = objectMapper.readValue(new File(filename),Product[].class);

        for (Product product: productArray) {
            products.put(product.getId(),product);
            if (product.getId() > nextId)
                nextId = product.getId();
        }
        ++nextId;
        return true;
    }

    @Override
    public Product[] getAll() throws IOException {
        synchronized(products) {
            return getProductsArray();
        }
    }

    @Override
    public Product[] searchFor(String name) throws IOException {
        ArrayList<Product> productsArrList = new ArrayList<>();
        
        for (Product product : products.values()) {
            if (name == null || product.getName().contains(name)) {
                productsArrList.add(product);
            }
        }
        Product[] proArray = new Product[productsArrList.size()];
        productsArrList.toArray(proArray);
        return proArray;
    }

    @Override
    public Product getProduct(int id) throws IOException {
        synchronized(products) {
            if (products.containsKey(id))
                return products.get(id);
            else return null;
        }
    }

    @Override
    public Product createProduct(Product product) throws IOException {
        synchronized(products) {
            Product newPro = new Product(
                getNextId(), 
                product.getStock(), 
                product.getPrice(), 
                product.getName(), 
                product.getDescription()
            );
            products.put(newPro.getId(), newPro);
            save();
            return newPro;
        }
    }

    @Override
    public Product updateProduct(Product product) throws IOException {
        synchronized(products) {
            if(!products.containsKey(product.getId())) return null;

            products.put(product.getId(), product);
            save();
            return product;
        }
    }

    @Override
    public boolean deleteProduct(int id) throws IOException {
        synchronized(products) {
            if (!products.containsKey(id)) return false;

            products.remove(id);
            return save();
        }
    }
    
}
