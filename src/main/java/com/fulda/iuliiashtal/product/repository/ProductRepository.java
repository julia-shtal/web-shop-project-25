package com.fulda.iuliiashtal.product.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fulda.iuliiashtal.product.entity.Product;
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
import java.util.Objects;

//Simulate DB
@Service
public class ProductRepository {

    Logger log = LogManager.getLogger(this.getClass().getName());

    public void writeItemToJson(Product product) {
        List<Product> products = readFromJson();
        Objects.requireNonNull(products).add(product);
        writeToJson(products);
    }

    public void writeToJson(List<Product> products) {
        JSONArray jsonArray = new JSONArray();
        for (Product product : products) {
            jsonArray.put(new JSONObject()
                    .put("id", product.getId())
                    .put("name", product.getName())
                    .put("price", product.getPrice())
                    .put("size", product.getSize())
                    .put("description", product.getDescription())
                    .put("category", product.getCategory())
                    .put("color", product.getColor()));
        }
        Path filePath = Paths.get("products.json");
        try {
            Files.write(filePath, jsonArray.toString(4).getBytes());  // 4 is for pretty-print indentation
            log.info("JSON array written to file successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Product> readFromJson() {
        File jsonFile = new File("products.json");
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(jsonFile, new TypeReference<ArrayList<Product>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
