package com.fulda.iuliiashtal.user.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fulda.iuliiashtal.user.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

//Simulate DB
@Service
public class UserRepository {

    Logger log = LogManager.getLogger(this.getClass().getName());

    public void writeToJson(List<User> users) {
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
            log.info("JSON array written to file successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<User> readFromJson() {
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
