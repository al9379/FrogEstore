package com.estore.api.estoreapi;

import java.io.IOError;
import java.io.IOException;
import java.util.logging.Level;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.estore.api.estoreapi.persistence.ProductDAO;

@RestController
@RequestMapping("products")
public class ProductController {
    private static final Logger LOG = Logger.getLogger(ProductController.class.getName());
    private ProductDAO productDAO;


    public ProductController(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }


    /**
     * Responds to the GET request for a product for the given id
     * 
     * @param id    The id used to locate the product
     * @return      ResponseEntity with the product object and HTTP status of OK if found
     * ResponseEntity with HTTP status of NOT_FOUND if not found
     * ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
     */    
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable int id) {
        LOG.info("GET /products/" + id);
        try {
            Product product = productDAO.getProduct(id);
            if(product != null)
                return new ResponseEntity<Product>(product, HttpStatus.OK);
            else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch(IOException e) {
            LOG.log(Level.SEVERE, e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("")
    public ResponseEntity<Product[]> getProducts() {

    }

    @GetMapping("/")
    public ResponseEntity<Product[]> searchProducts(@RequestParam String name) {

    }

    @PostMapping("")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {

    }

    @PutMapping("")
    public ResponseEntity<Product> updateProduct(@RequestBody Product product) {

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable int id) {

    }
    
}
