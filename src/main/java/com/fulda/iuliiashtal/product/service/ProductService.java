package com.fulda.iuliiashtal.product.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fulda.iuliiashtal.product.entity.Product;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductService {

    public boolean checkExists(Product product) {
        return getProducts().stream().anyMatch(p -> p.getName().equalsIgnoreCase(product.getName()));
    }

    public List<Product> getProducts() {
        return readFromJson();
    }

    public Optional<Product> getProductById(UUID id) {
        return getProducts().stream().filter(product -> product.getId().equals(id)).findFirst();
    }

    public List<Product> getProductsByColor(String color) {
        return getProducts().stream()
                .filter(product -> product.getColor().equalsIgnoreCase(color))
                .collect(Collectors.toList());
    }

    public List<Product> getProductsByCategory(String category) {
        return getProducts().stream()
                .filter(product -> product.getCategory().equalsIgnoreCase(category))
                .collect(Collectors.toList());
    }

    public Product create(Product product) {
        product.setId(product.getId() == null ? UUID.randomUUID() : product.getId());
        writeItemToJson(product);
        return product;
    }

    public Product updateProduct(Product product) {
        for (Product existingProduct : getProducts()) {
            if (existingProduct.getId().equals(product.getId())) {
                existingProduct.setName(product.getName());
                existingProduct.setDescription(product.getDescription());
                existingProduct.setPrice(product.getPrice());
                existingProduct.setSize(product.getSize());
                existingProduct.setColor(product.getColor());
                existingProduct.setCategory(product.getCategory());
                return create(existingProduct);
            }
        }
        return null;
    }

    public boolean delete(UUID id) {
        List<Product> products = readFromJson();
        Optional<Product> foundProduct = products.stream().filter(product -> product.getId().equals(id))
                .findFirst();
        if (foundProduct.isPresent()) {
            products.remove(foundProduct.get());
            writeToJson(products);
            return true;
        }
        return false;
    }

    //Simulate DB
    private void writeItemToJson(Product product) {
        List<Product> products = readFromJson();
        Objects.requireNonNull(products).add(product);
        writeToJson(products);
    }

    //Simulate DB
    private void writeToJson(List<Product> products) {
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
            System.out.println("JSON array written to file successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Simulate DB
    private List<Product> readFromJson() {
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
