package com.fulda.iuliiashtal.inventory.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

//Simulate DB
@Service
public class InventoryRepositoryLocal {

    private final Logger log = LoggerFactory.getLogger(this.getClass().getName());

    public Map<UUID, Integer> readFromJson() {
        File jsonFile = new File("inventory.json");
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            // Read the JSON file and convert it to a Map<UUID, Integer>
            Map<String, Integer> stringKeyMap = objectMapper.readValue(jsonFile, new TypeReference<>() {
            });
            // Convert String keys to UUID keys
            Map<UUID, Integer> uuidKeyMap = new HashMap<>();
            for (Map.Entry<String, Integer> entry : stringKeyMap.entrySet()) {
                uuidKeyMap.put(UUID.fromString(entry.getKey()), entry.getValue());
            }
            return uuidKeyMap;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void writeToJson(Map<UUID, Integer> inventory) {
        File jsonFile = new File("inventory.json");
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            // Convert UUID keys to String keys
            Map<String, Integer> stringKeyMap = new HashMap<>();
            for (Map.Entry<UUID, Integer> entry : inventory.entrySet()) {
                stringKeyMap.put(entry.getKey().toString(), entry.getValue());
            }
            // Write the map to the JSON file
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(jsonFile, stringKeyMap);
            log.info("Inventory successfully written to inventory.json");
        } catch (IOException e) {
            e.printStackTrace();
            log.error("Failed to write inventory to inventory.json");
        }
    }

    public void writeEmptyToJson() {
        File jsonFile = new File("inventory.json");
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            // Convert UUID keys to String keys
            Map<String, Integer> stringKeyMap = new HashMap<>();
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(jsonFile, stringKeyMap);
            log.info("Inventory successfully written to inventory.json");
        } catch (IOException e) {
            e.printStackTrace();
            log.error("Failed to write inventory to inventory.json");
        }
    }
}
