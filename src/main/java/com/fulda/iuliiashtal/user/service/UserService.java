package com.fulda.iuliiashtal.user.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fulda.iuliiashtal.user.entity.User;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService {

    public List<User> getUsers() {
        return readFromJson();
    }

    public Optional<User> getUserById(UUID id) {
        return getUsers().stream().filter(user -> user.getId().equals(id)).findFirst();
    }

    public List<User> getUsersByFullName(String firstName, String lastName) {
        return getUsers().stream()
                .filter(user -> user.getFirstName().equalsIgnoreCase(firstName)
                        && user.getLastName().equalsIgnoreCase(lastName))
                .collect(Collectors.toList());
    }

    public User create(String firstName, String lastName,
                       String email) {
        List<User> users = getUsers();
        User newUser = new User(UUID.randomUUID(), firstName, lastName, email, "defaultPassword");
        users.add(newUser);
        writeToJson(users);
        return newUser;
    }

    //Simulate DB
    private void writeToJson(List<User> users) {
        JSONArray jsonArray = new JSONArray();
        for (User user : users) {
            jsonArray.put(new JSONObject()
                    .put("id", user.getId())
                    .put("firstName", user.getFirstName())
                    .put("lastName", user.getLastName())
                    .put("email", user.getEmail())
                    .put("password", user.getPassword()));
        }
        Path filePath = Paths.get("users.json");
        try {
            Files.write(filePath, jsonArray.toString(4).getBytes());  // 4 is for pretty-print indentation
            System.out.println("JSON array written to file successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Simulate DB
    private List<User> readFromJson() {
        File jsonFile = new File("users.json");
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(jsonFile, new TypeReference<ArrayList<User>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
