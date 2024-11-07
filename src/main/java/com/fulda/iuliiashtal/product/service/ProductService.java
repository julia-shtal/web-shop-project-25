package com.fulda.iuliiashtal.product.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fulda.iuliiashtal.product.entity.Product;
import com.fulda.iuliiashtal.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repository;

    public boolean checkExists(Product product) {
        return getProducts().stream().anyMatch(p -> p.getName().equalsIgnoreCase(product.getName()));
    }

    public List<Product> getProducts() {
        return repository.readFromJson();
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
        return save(product);
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
                return save(existingProduct);
            }
        }
        return null;
    }

    public Product save(Product product) {
        repository.writeItemToJson(product);
        return product;
    }

    public boolean delete(UUID id) {
        List<Product> products = repository.readFromJson();
        Optional<Product> foundProduct = products.stream().filter(product -> product.getId().equals(id))
                .findFirst();
        if (foundProduct.isPresent()) {
            products.remove(foundProduct.get());
            repository.writeToJson(products);
            return true;
        }
        return false;
    }




}
