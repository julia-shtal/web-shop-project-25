package com.fulda.iuliiashtal.product.service;

import com.fulda.iuliiashtal.product.entity.Product;
import com.fulda.iuliiashtal.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repository;

    public boolean checkProductExists(Product product) {
        return getAllProducts().stream().anyMatch(p -> p.getName().equalsIgnoreCase(product.getName()));
    }

    public List<Product> getAllProducts() {
        return repository.readFromJson();
    }

    public Product getProductById(UUID id) {
        return getAllProducts().stream().filter(product -> product.getId().equals(id)).findFirst().orElseThrow();
    }

    public List<Product> getProductsByColor(String color) {
        return getAllProducts().stream()
                .filter(product -> product.getColor().equalsIgnoreCase(color))
                .collect(Collectors.toList());
    }

    public List<Product> getProductsByCategory(String category) {
        return getAllProducts().stream()
                .filter(product -> product.getCategory().equalsIgnoreCase(category))
                .collect(Collectors.toList());
    }

    public Product createProduct(Product product) {
        product.setId(product.getId() == null ? UUID.randomUUID() : product.getId());
        return save(product);
    }

    public Product updateProduct(Product product) {
        Optional<Product> optional = getAllProducts().stream()
                .filter(product1 -> product1.getId().equals(product.getId()))
                .findFirst();
        if (optional.isEmpty()) {
            return null;
        }
        Product existingProduct = optional.get();
        existingProduct.setName(product.getName());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setSize(product.getSize());
        existingProduct.setColor(product.getColor());
        existingProduct.setCategory(product.getCategory());
        return save(existingProduct);
    }

    public Product save(Product product) {
        repository.writeItemToJson(product);
        return product;
    }

    public boolean deleteProduct(UUID id) {
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
