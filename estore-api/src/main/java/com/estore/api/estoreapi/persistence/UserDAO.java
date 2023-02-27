package com.estore.api.estoreapi.persistence;

import java.io.IOException;

public interface UserDAO {
    public String[] getAll();
    public boolean addUser(String username) throws IOException;
    public boolean deleteUser(String username) throws IOException;
    public boolean userExists(String username);
}
