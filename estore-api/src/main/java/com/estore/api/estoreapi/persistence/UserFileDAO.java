package com.estore.api.estoreapi.persistence;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Value;

@Component
public class UserFileDAO implements UserDAO {

    private static final Logger LOG = Logger.getLogger(UserFileDAO.class.getName());

    private ObjectMapper objectMapper;

    private List<String> users;
    private String filename;

    public UserFileDAO(@Value("${users.file}") String filename, ObjectMapper objectMapper) throws IOException {
        this.filename = filename;
        this.objectMapper = objectMapper;
        load();
    }

    private boolean save() throws IOException {
        objectMapper.writeValue(new File(filename), this.getAll());
        return true;
    }

    private boolean load() throws IOException {
        users = new ArrayList<>();
        String[] loadedUsers = objectMapper.readValue(new File(filename), String[].class);
        for (String user : loadedUsers) {
            this.users.add(user);
        }
        return true;
    }

    @Override
    public String[] getAll() {
        return users.toArray(new String[0]);
    }

    @Override
    public boolean addUser(String username) throws IOException {
        if (this.userExists(username)) return false;

        users.add(username);
        return save();
    }

    @Override
    public boolean deleteUser(String username) throws IOException{
        if (!this.userExists(username)) return false;
        users.remove(username);

        return save();
    }

    @Override
    public boolean userExists(String username) {
        return users.contains(username);
    }
    
}
