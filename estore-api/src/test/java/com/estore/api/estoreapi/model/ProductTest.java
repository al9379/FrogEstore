package com.estore.api.estoreapi.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

/**
 * The unit tests for the Product class
 */
@Tag("model-tier")
public class ProductTest {

   @Test
   public void testConstructor(){
        int expectedId = 99;
        int expectedStock = 200;
        float expectedPrice = 19.99f;
        String expectedName = "Freddy Frog";
        String expectedDescription = "A very famous frog";
        Product product = new Product(expectedId,expectedStock,expectedPrice,expectedName,expectedDescription);

        assertEquals(expectedId,product.getId());
        assertEquals(expectedStock,product.getStock());
        assertEquals(expectedPrice,product.getPrice());
        assertEquals(expectedName,product.getName());
        assertEquals(expectedDescription,product.getDescription());
   }

   @Test
   public void testSetStock(){
        int id = 99;
        int stock = 200;
        float price = 19.99f;
        String name = "Freddy Frog";
        String description = "A very famous frog";
        Product product = new Product(id,stock,price,name,description);

        int expectedStock = 10;
        
        product.setStock(expectedStock);

        assertEquals(expectedStock,product.getStock());
   }

   @Test
   public void testSetPrice(){
        int id = 99;
        int stock = 200;
        float price = 19.99f;
        String name = "Freddy Frog";
        String description = "A very famous frog";
        Product product = new Product(id,stock,price,name,description);

        float expectedPrice = 300.00f;
        
        product.setPrice(expectedPrice);

        assertEquals(expectedPrice,product.getPrice());
   }

   
   @Test
   public void testSetName(){
        int id = 99;
        int stock = 200;
        float price = 19.99f;
        String name = "Freddy Frog";
        String description = "A very famous frog";
        Product product = new Product(id,stock,price,name,description);

        String expectedName = "Henry Hop";
        
        product.setName(expectedName);

        assertEquals(expectedName,product.getName());
   }

   @Test
   public void testSetDescription(){
        int id = 99;
        int stock = 200;
        float price = 19.99f;
        String name = "Freddy Frog";
        String description = "A very famous frog";
        Product product = new Product(id,stock,price,name,description);

        String expectedDescription = "A happy frog";
        
        product.setDescription(expectedDescription);

        assertEquals(expectedDescription,product.getDescription());
   }

   @Test
   public void testToString(){
        int id = 99;
        int stock = 200;
        float price = 19.99f;
        String name = "Freddy Frog";
        String description = "A very famous frog";

        String expectedString = String.format("%s : %f: %d ", name, price, id);

        Product product = new Product(id,stock,price,name,description);

        String actualString = product.toString();

        assertEquals(expectedString,actualString);
   }
}
