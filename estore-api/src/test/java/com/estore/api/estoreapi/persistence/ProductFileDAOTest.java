
package com.estore.api.estoreapi.persistence;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.estore.api.estoreapi.model.Product;
import com.fasterxml.jackson.databind.ObjectMapper;

@Tag("Persistence-tier")
public class ProductFileDAOTest {
    ProductFileDAO productFileDAO;
    Product[] testProducts;
    ObjectMapper mockObjectMapper;

    @BeforeEach
    public void setupProductFileDAO() throws IOException {
        mockObjectMapper = mock(ObjectMapper.class);
        testProducts = new Product[3];
        testProducts[0] = new Product(99,2,3.99f,"Froggy 1","going bark");
        testProducts[1] = new Product(100,0, 9999.99f, "Take it", " left it");
        testProducts[2] = new Product(101,12,42.42f, "Fortune", "Cost has it");

        when(mockObjectMapper
            .readValue(new File("doesnt_matter.txt"),Product[].class))
                .thenReturn(testProducts);
        productFileDAO = new ProductFileDAO("doesnt_matter.txt",mockObjectMapper);
    }

    @Test
    public void testGetAll() throws IOException {
        Product[] products = productFileDAO.getAll();
        assertEquals(products.length,testProducts.length);
        for (int i = 0; i < testProducts.length;++i)
            assertEquals(products[i],testProducts[i]);
    }

    @Test
    public void testSearchFor() throws IOException {
        Product[] products = productFileDAO.searchFor(" ");

        assertEquals(products.length,2);
        assertEquals(products[0],testProducts[0]);
        assertEquals(products[1],testProducts[1]);
    }

    @Test
    public void testGetProduct() throws IOException {
        Product product = productFileDAO.getProduct(101);

        assertEquals(product, testProducts[2]);
    }

    @Test 
    public void testDeleteProduct() throws IOException {
        boolean result = assertDoesNotThrow(() -> productFileDAO.deleteProduct(100));

        assertEquals(result, true);

        assertEquals(testProducts.length-1, productFileDAO.products.size());
    }

    @Test
    public void testCreateProduct() throws IOException {
        Product product = new Product(105, 5, 0.99f, "New one", "Just showed up");

        Product result = assertDoesNotThrow(() -> productFileDAO.createProduct(product),
                                "Unexpected exception thrown");

        assertNotNull(result);
        Product actual = productFileDAO.getProduct(result.getId());
        assertEquals(actual.getName(),product.getName());
    }

    @Test 
    public void testUpdateProduct() throws IOException {
        Product product = new Product(101, 5, 0.99f, "New one", "Just showed up");

        Product result = assertDoesNotThrow(() -> productFileDAO.updateProduct(product), "Unexpected Exception thrown");

        assertNotNull(result);
        Product actual = productFileDAO.getProduct(product.getId());
        assertEquals(actual,product);
    }


    @Test
    public void testSaveException() throws IOException{
        doThrow(new IOException())
            .when(mockObjectMapper)
                .writeValue(any(File.class),any(Product[].class));

        Product product = new Product(99,2,3.99f,"Froggy 1","going bark");

        assertThrows(IOException.class,
                        () -> productFileDAO.createProduct(product),
                        "IOException not thrown");
    }

    @Test
    public void testGetProductNotFound() throws IOException {
        Product product = productFileDAO.getProduct(98);

        assertEquals(product,null);
    }

    @Test
    public void testDeleteProductNotFound() {
        boolean result = assertDoesNotThrow(() -> productFileDAO.deleteProduct(98),
                                                "Unexpected exception thrown");
        assertEquals(result,false);
        assertEquals(productFileDAO.products.size(),testProducts.length);
    }

    @Test
    public void testUpdateProductNotFound() {
        Product product = new Product(105, 5, 0.99f, "New one", "Just showed up");
        Product result = assertDoesNotThrow(() -> productFileDAO.updateProduct(product),
                                                "Unexpected exception thrown");

        assertNull(result);
    }

    @Test
    public void testConstructorException() throws IOException {
        ObjectMapper mockObjectMapper = mock(ObjectMapper.class);
        doThrow(new IOException())
            .when(mockObjectMapper)
                .readValue(new File("doesnt_matter.txt"),Product[].class);

        assertThrows(IOException.class,
                        () -> new ProductFileDAO("doesnt_matter.txt",mockObjectMapper),
                        "IOException not thrown");
    } 
}
