package com.estore.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;

import com.estore.api.estoreapi.persistence.UserDAO;

import net.bytebuddy.agent.VirtualMachine.ForHotSpot.Connection.Response;

import com.estore.api.estoreapi.controller.UserController;
import com.estore.api.estoreapi.model.ShoppingCart;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Tag("Controller-Tier")
public class UserControllerTests {
    private UserController userController;
    private UserDAO mockUserDAO;

    @Test
    public void testCreateUser() throws IOException{
        String user = "Tim";

        when(mockUserDAO.addUser(user)).thenReturn(true);

        ResponseEntity<String> response = userController.createUser(user);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(user, response.getBody());
    }

    @Test
    public void testCreateProductFailed() throws IOException{
        String user = "Tim";

        when(mockUserDAO.addUser(user)).thenReturn(null);

        ResponseEntity<String> response = userController.createUser(user);

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
    }

    @Test
    public void testGetAll() throws IOException {
        // Setup
        String[] users = new String[2];
        users[0] = new String("Tim");
        users[1] = new String("Drake");

        when(mockUserDAO.getAll()).thenReturn(users);

        // Invoke
        ResponseEntity<String[]> response = userController.getUsers();

        // Analyze
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(users,response.getBody());
    }

    @Test
    public void testGetAllHandleException() throws IOException { 
        // Setup
       
        doThrow(new IOException()).when(mockUserDAO).getAll();

        // Invoke
        ResponseEntity<String[]> response = userController.getUsers();

        // Analyze
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,response.getStatusCode());
    }

    @Test
    public void testDeleteUser() throws IOException { 
        // Setup
        String user = "Tim";
        
        when(mockUserDAO.deleteUser(user)).thenReturn(true);

        ResponseEntity<String> response = userController.deleteUser(user);

        assertEquals(HttpStatus.OK,response.getStatusCode());
    }

    @Test
    public void testDeleteHeroNotFound() throws IOException { 
        // Setup
        String user = "Tim";
        
        when(mockUserDAO.deleteUser(user)).thenReturn(false);

        // Invoke
        ResponseEntity<String> response = userController.deleteUser(user);

        // Analyze
        assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());
    }

    @Test
    public void testDeleteHeroHandleException() throws IOException { 
        // Setup
        String user = "Tim";
        
        doThrow(new IOException()).when(mockUserDAO).deleteUser(user);

        // Invoke
        ResponseEntity<String> response = userController.deleteUser(user);

        // Analyze
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,response.getStatusCode());
    }
}
