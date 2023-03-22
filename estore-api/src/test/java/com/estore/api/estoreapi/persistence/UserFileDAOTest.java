package com.estore.api.estoreapi.persistence;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;


/**
 * The unit tests for the UserFileDAO class
 */
@Tag("Persistence-tier")
public class UserFileDAOTest {
    UserFileDAO userFileDAO;
    String[] testUsers;
    ObjectMapper mockObjectMapper;

    @BeforeEach
    public void setupUserFileDAO() throws IOException {
        mockObjectMapper = mock(ObjectMapper.class);
        testUsers = new String[]{"Micheal B Jordan", "Shaggy", "Goku"};

        when(mockObjectMapper
            .readValue(new File("doesnt_matter.txt"),String[].class))
                .thenReturn(testUsers);
        userFileDAO = new UserFileDAO("doesnt_matter.txt",mockObjectMapper);
    }

    @Test
    public void testGetUsers () throws IOException {
        String[] users = userFileDAO.getAll();
        assertEquals(users.length,testUsers.length);
        for (int i = 0; i < testUsers.length;++i)
            assertEquals(testUsers[i],testUsers[i]);
    }

    @Test 
    public void testDeleteUser () throws IOException {
        boolean result = assertDoesNotThrow(() -> userFileDAO.deleteUser("Goku"));

        assertEquals(result, true);
        assertEquals(userFileDAO.getAll().length, testUsers.length-1);
    }

    @Test
    public void testCreateUser () throws IOException {
        boolean result = userFileDAO.addUser("Luigi");
        assertEquals(result, true);
    }

    @Test
    public void testSaveException() throws IOException{
        doThrow(new IOException())
            .when(mockObjectMapper)
                .writeValue(any(File.class),any(String[].class));


        assertThrows(IOException.class,
                        () -> userFileDAO.addUser("Luigi"),
                        "IOException not thrown");
    }

    @Test
    public void testDeleteUserNotFound() {
        boolean result = assertDoesNotThrow(() -> userFileDAO.deleteUser("Mario"),
                                                "Unexpected exception thrown");

        assertEquals(result,false);
        assertEquals(userFileDAO.getAll().length,testUsers.length);
    }


    @Test
    public void testConstructorException() throws IOException {
        ObjectMapper mockObjectMapper = mock(ObjectMapper.class);
        doThrow(new IOException())
            .when(mockObjectMapper)
                .readValue(new File("doesnt_matter.txt"),String[].class);
        assertThrows(IOException.class,
                        () -> new UserFileDAO("doesnt_matter.txt",mockObjectMapper),
                        "IOException not thrown");
    }
}
