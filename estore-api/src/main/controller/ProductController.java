package com.estore.api.estoreapi;

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

@RestController
@RequestMapping("products")
public class ProductController {
    private static final Logger LOG = Logger.getLogger(ProductController.class.getName());
    private ProductDAO productDAO;


    public ProductController(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable int id) {

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
