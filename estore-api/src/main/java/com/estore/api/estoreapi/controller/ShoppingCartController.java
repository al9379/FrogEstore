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
 * Handles the REST API requests for the Shopping Cart resource
 */

@RestController
@RequestMapping("carts")
public class ShoppingCartController {
    private static final Logger LOG = Logger.getLogger(ShoppingCartController.class.getName());
    private ShoppingCartDAO shoppingCartDAO;

    /**
     * Creates a REST API controller which responds to requests
     * @param shoppingCartDAO The {@link ShoppingCartDAO Shopping Cart Data Access Object} to
     *                        perform CRUD operations
     */
    public ShoppingCartController(ShoppingCartDAO shoppingCartDAO) {
        this.shoppingCartDAO = shoppingCartDAO;
    }

    /**
     * Responds to the GET request for a shopping cart for the given username
     * @param username The username used to locate the shopping cart
     * @return ResponseEntity with the shopping cart object and HTTP status of OK if
     *         found<br>
     *         ResponseEntity with HTTP status of NOT_FOUND if not found<br>
     *         ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
     */
   @GetMapping("/{username}")
    public ResponseEntity<ShoppingCart> getCart(@PathVariable String username) {
        LOG.info("GET /carts/" + username);
        try {
            ShoppingCart cart = this.shoppingCartDAO.getShoppingCart(username);
            if (cart != null)
                return new ResponseEntity<ShoppingCart>(cart, HttpStatus.OK);
            else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (IOException e) {
            LOG.log(Level.SEVERE, e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Creates a shopping cart with the provided username
     *
     * @param username - The username of the shopping cart to create
     *
     * @return ResponseEntity with given username and HTTP status
     *         of CREATED<br>
     *         ResponseEntity with HTTP status of CONFLICT if a shopping cart
     *         object with that username already exists<br>
     *         ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
     */
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

    /**
     * Updates the shopping cart with the provided shopping cart object, if it exists
     *
     * @param shoppingCart The shopping cart to update
     *
     * @return ResponseEntity with the username of the updated shopping cart object and HTTP status
     *         of OK if updated<br>
     *         ResponseEntity with HTTP status of NOT_FOUND if not found<br>
     *         ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
     */
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

    /**
     * Deletes a {@linkplain ShoppingCart shopping cart} with the given username
     *
     * @param username The username of the {@link ShoppingCart shopping cart} to deleted
     *
     * @return ResponseEntity HTTP status of OK if deleted<br>
     *         ResponseEntity with HTTP status of NOT_FOUND if not found<br>
     *         ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
     */
    @DeleteMapping("/{username}")
    public ResponseEntity<String> deleteShoppingCart(@PathVariable String username) {
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