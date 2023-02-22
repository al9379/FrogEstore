package com.estore.api.estoreapi.persistence;

import java.io.IOException;
import java.util.Map;

import com.estore.api.estoreapi.model.Product;

public interface ProductDAO {
    /**
     * Retrieves all current products {@linkplain Product products}
     * @return Array of {@link Product product} objects
     * @throws IOException if an issue with storage
     */
    Product[] getAll() throws IOException;

    /**
     * Finds all {@linkplain Product products} whose name contains the given text
     * @param name name to match
     * @return Array of matching {@link Product products} objects
     * @throws IOException if an issue with storage
     */
    Product[] searchFor(String name) throws IOException;

    /**
     * Retrieves a specific {@linkplain Product product} with the given id
     * @param id id of the {@link Product product} to get
     * @return the matching {@link Product product}
     * @throws IOException if an issue with storage
     */
    Product getProduct(int id) throws IOException;

    /**
     * Creates and saves a new {@linkplain Product product}
     * @param product {@linkplain Product product} to create
     * @return new {@link Product product} if successful, null otherwise
     * @throws IOException if an issue with storage
     */
    Product createProduct(Product product) throws IOException;

    /**
     * Updates and saves a {@linkplain Product product} 
     * @param product  object to be updated
     * @return updated {@link Product product} if successful, null otherwise
     * @throws IOException if an issue with storage
     */
    Product updateProduct(Product product) throws IOException;

    /**
     * Deletes a {@linkplain Product product} with the given id
     * @param id id of the {@link Product product} to delete
     * @return true if successful
     * @throws IOException if an issue with storage
     */
    boolean deleteProduct(int id) throws IOException;
}