package com.estore.api.estoreapi.persistence;

import java.io.IOException;

public interface UserDAO {
    /**
     * Retrieves all current users
     * @return Array of username
     * @throws IOException if an issue with storage
     */
    String[] getAll() throws IOException;

    /**
     * Creates and saves a new user with the given username
     * @param username username of the new user
     * @return true if successful, false otherwise
     * @throws IOException if an issue with storage
     */
    boolean addUser(String username) throws IOException;

    /**
     * Deletes a user with the given username
     * @param username username of the user to delete
     * @return true if successful, false otherwise
     * @throws IOException if an issue with storage
     */
    boolean deleteUser(String username) throws IOException;

    /**
     * Checks if a user with the given username exists
     * @param username the username of the user
     * @return true if the user exists, false otherwise
     */
    boolean userExists(String username);
}
