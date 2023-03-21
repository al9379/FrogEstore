package com.estore.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;

import com.estore.api.estoreapi.persistence.ProductDAO;

import net.bytebuddy.agent.VirtualMachine.ForHotSpot.Connection.Response;

import com.estore.api.estoreapi.controller.ProductController;
import com.estore.api.estoreapi.model.Product;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


@Tag("Controller-Tier")
public class ProductControllerTests {
    private ProductController productController;
    private ProductDAO mockProductDAO;

    @BeforeEach
    public void setupProductController(){
        mockProductDAO = mock(ProductDAO.class);
        productController = new ProductController(mockProductDAO);
    }

    @Test
    public void testGetProduct() throws IOException{
        Product product = new Product(1,10,15,"tree","poison");

        when(mockProductDAO.getProduct(product.getId())).thenReturn(product);

        ResponseEntity<Product> response = productController.getProduct(product.getId());

        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(product,response.getBody());
    }

    @Test
    public void testGetProductsHandleException() throws Exception{
        int productId = 1;
        doThrow(new IOException()).when(mockProductDAO).getProduct(productId);

        ResponseEntity<Product> response = productController.getProduct(productId);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,response.getStatusCode());
    }

    @Test
    public void testGetProductNotFound() throws Exception{
        int productId = 1;

        when(mockProductDAO.getProduct(productId)).thenReturn(null);

        ResponseEntity<Product> response = productController.getProduct(productId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testCreateProduct() throws IOException{
        Product product = new Product(1,10,15,"tree","poison");

        when(mockProductDAO.createProduct(product)).thenReturn(product);

        ResponseEntity<Product> response = productController.createProduct(product);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(product, response.getBody());
    }

    @Test
    public void testCreateProductFailed() throws IOException{
        Product product = new Product(1,10,15,"tree","poison");

        when(mockProductDAO.createProduct(product)).thenReturn(null);

        ResponseEntity<Product> response = productController.createProduct(product);

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
    }

    @Test
    public void testUpdateProduct() throws IOException{
        Product product = new Product(1,10,15,"tree","poison");

        when(mockProductDAO.updateProduct(product)).thenReturn(product);

        ResponseEntity<Product> response = productController.updateProduct(product);
        product.setName("bush");

        response = productController.updateProduct(product);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(product, response.getBody());
    }

    @Test
    public void testUpdateProductFailed() throws IOException{
        Product product = new Product(1,10,15,"tree","poison");

        when(mockProductDAO.updateProduct(product)).thenReturn(null);

        ResponseEntity<Product> response = productController.updateProduct(product);

        assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());
    }

    @Test
    public void testUpdateProductHandleException() throws IOException{

        Product product = new Product(1,10,15,"tree","poison");

        doThrow(new IOException()).when(mockProductDAO).updateProduct(product);

        ResponseEntity<Product> response = productController.updateProduct(product);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,response.getStatusCode());
    }

    @Test
    public void testGetAll() throws IOException { // getHeroes may throw IOException
        // Setup
        Product[] products = new Product[2];
        products[0] = new Product(1,10,15,"tree","poison");
        products[1] = new Product(2,8,10,"bull","burp");
        
        when(mockProductDAO.getAll()).thenReturn(products);

        // Invoke
        ResponseEntity<Product[]> response = productController.getProducts();

        // Analyze
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(products,response.getBody());
    }

    @Test
    public void testGetAllHandleException() throws IOException {
        // Setup
        
        doThrow(new IOException()).when(mockProductDAO).getAll();

        // Invoke
        ResponseEntity<Product[]> response = productController.getProducts();

        // Analyze
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,response.getStatusCode());
    }

    @Test
    public void testSearchFor() throws IOException { 
        // Setup
        String searchString = "re";
        Product[] products = new Product[2];
        products[0] = new Product(1,10,15,"tree","poison");
        products[1] = new Product(2,8,10,"bull","burp");
       
        when(mockProductDAO.searchFor(searchString)).thenReturn(products);

        // Invoke
        ResponseEntity<Product[]> response = productController.searchProducts(searchString);

        // Analyze
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(products,response.getBody());
    }

    @Test
    public void testSearchForHandleException() throws IOException { 
        // Setup
        String searchString = "an";
        
        doThrow(new IOException()).when(mockProductDAO).searchFor(searchString);

        // Invoke
        ResponseEntity<Product[]> response = productController.searchProducts(searchString);

        // Analyze
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,response.getStatusCode());
    }

    @Test
    public void testDeleteProduct() throws IOException { 
        // Setup
        int productId = 1;
        
        when(mockProductDAO.deleteProduct(productId)).thenReturn(true);

        ResponseEntity<Product> response = productController.deleteProduct(productId);

        assertEquals(HttpStatus.OK,response.getStatusCode());
    }

    @Test
    public void testDeleteProductNotFound() throws IOException { 
        // Setup
        int productId = 1;
       
        when(mockProductDAO.deleteProduct(productId)).thenReturn(false);

        // Invoke
        ResponseEntity<Product> response = productController.deleteProduct(productId);

        // Analyze
        assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());
    }

    @Test
    public void testDeleteProductHandleException() throws IOException { 
        // Setup
        int productId = 1;
        
        doThrow(new IOException()).when(mockProductDAO).deleteProduct(productId);

        // Invoke
        ResponseEntity<Product> response = productController.deleteProduct(productId);

        // Analyze
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,response.getStatusCode());
    }
}
