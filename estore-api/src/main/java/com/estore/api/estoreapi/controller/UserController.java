package com.estore.api.estoreapi.controller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.estore.api.estoreapi.model.Product;
import com.estore.api.estoreapi.persistence.ProductDAO;
import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.UserDataHandler;

import com.estore.api.estoreapi.persistence.UserDAO;

/**
 * Handles the REST API requests for the User resource
 */
@RestController
@RequestMapping("users")
public class UserController {
    private static final Logger LOG = Logger.getLogger(UserController.class.getName());
    private UserDAO userDAO;


    /**
     * Creates a REST API controller which responds to requests
     *
     * @param userDAO The {@link UserDAO User Data Access Object} to
     *                   perform CRUD operations
     */
    public UserController(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    /**
     * Responds to the GET request for all users
     *
     * @return ResponseEntity with array of username (may be empty) and
     *         HTTP status of OK<br>
     *         ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
     */
    @GetMapping("")
    public ResponseEntity<String[]> getUsers() {
        LOG.info("GET /users");
        try {
            String[] users = userDAO.getAll();
            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (IOException e) {
            LOG.log(Level.SEVERE, e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Responds to the GET request for a user for the given username
     *
     * @param username The username used to locate the user
     * @return ResponseEntity with the username and HTTP status of OK if
     *         found<br>
     *         ResponseEntity with HTTP status of NOT_FOUND if not found<br>
     */
    @GetMapping("/{username}")
    public ResponseEntity<String> getUser(@PathVariable String username) {
        LOG.info("GET /users");
        if (userDAO.userExists(username)) {
            return new ResponseEntity<String>(username, HttpStatus.OK);
        }  else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Creates a user with the provided username
     *
     * @param username - The username of user to create
     *
     * @return ResponseEntity with username of the created user object and HTTP status
     *         of CREATED<br>
     *         ResponseEntity with HTTP status of CONFLICT if a user object with that
     *         username already exists<br>
     *         ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
     */
    @PostMapping("")
    public ResponseEntity<String> createUser(@RequestBody String username) {
        LOG.info("POST /users " + username);
        try {
            boolean result = userDAO.addUser(username);
            if (result) return new ResponseEntity<String>(username, HttpStatus.OK);
            else  return new ResponseEntity<>(HttpStatus.CONFLICT);
        } catch (IOException e) {
            LOG.log(Level.SEVERE, e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Deletes a user with the given username
     *
     * @param username The username of the user to be deleted
     *
     * @return ResponseEntity HTTP status of OK if deleted<br>
     *         ResponseEntity with HTTP status of NOT_FOUND if not found<br>
     *         ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
     */
    @DeleteMapping("/{username}")
    public ResponseEntity<String> deleteUser(@PathVariable String username) {
        LOG.info("DELETE /user/" + username);
        try {
            boolean result = userDAO.deleteUser(username);
            if (result) {
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
