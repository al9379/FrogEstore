package com.estore.api.estoreapi.controller;

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
import org.springframework.web.bind.annotation.RestController;

import com.estore.api.estoreapi.model.ShoppingCart;
import com.estore.api.estoreapi.persistence.ShoppingCartDAO;

import java.util.logging.Logger;

/**
 * Handles the REST API requests for the Product resource
 */

@RestController
@RequestMapping("carts")
public class ShoppingCartController {
    private static final Logger LOG = Logger.getLogger(ShoppingCartController.class.getName());
    private ShoppingCartDAO shoppingCartDAO;

    public ShoppingCartController(ShoppingCartDAO shoppingCartDAO) {
        this.shoppingCartDAO = shoppingCartDAO;
    }

   @GetMapping("/{username}")
    public ResponseEntity<ShoppingCart> getCart(@PathVariable String username) {
        LOG.info("GET /carts/" + username);
        try {
            ShoppingCart cart = this.shoppingCartDAO.getShoppingCart(username);
            if (username != null)
                return new ResponseEntity<ShoppingCart>(cart, HttpStatus.OK);
            else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (IOException e) {
            LOG.log(Level.SEVERE, e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

   @PostMapping("/{username}")
    public ResponseEntity<String> createShoppingCart(@PathVariable String username) {
        LOG.info("POST /carts" + username);
        try {
            boolean created_cart = shoppingCartDAO.createShoppingCart(username);
            if (!created_cart) {
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            } else {
                return new ResponseEntity<>(HttpStatus.OK);
            }

        } catch (IOException e) {
            LOG.log(Level.SEVERE, e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

   @PutMapping("/{username}")
    public ResponseEntity<String> updateCart(@RequestBody ShoppingCart shoppingCart) {
        LOG.info("PUT /carts" + shoppingCart);
        try {
            boolean updated_cart = shoppingCartDAO.updateShoppingCart(shoppingCart);
            if (!updated_cart) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(HttpStatus.OK);
            }

        } catch (IOException e) {
            LOG.log(Level.SEVERE, e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<String> deleteProduct(@PathVariable String username) {
        LOG.info("DELETE /carts/" + username);
        try {
            boolean result = shoppingCartDAO.deleteShoppingCart(username);
            if (result == true) {
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

        } catch (IOException e) {
            LOG.log(Level.SEVERE, e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}