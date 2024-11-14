package com.fulda.iuliiashtal.product.controller;

import com.fulda.iuliiashtal.product.entity.Product;
import com.fulda.iuliiashtal.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/catalog")
    public String viewCatalog(Model model, @RequestParam(required = false, defaultValue = "true") boolean edit) {
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);
        model.addAttribute("editMode", edit);
        return "catalog";
    }

    @GetMapping("/product/{id}")
    public String viewProductDetail(@PathVariable UUID id, Model model) {
        Product product = productService.getProductById(id);
        model.addAttribute("product", product);
        return "productDetail";
    }

    @GetMapping("/add-product")
    public String showAddProductForm(Model model) {
        model.addAttribute("product", new Product());
        return "add-product";
    }

    @PostMapping("/add-product")
    public String addProduct(@ModelAttribute Product product) {
        productService.createProduct(product);
        return "redirect:/product/" + product.getId();
    }

    @GetMapping("/product-delete/{id}")
    public String deleteProduct(@PathVariable UUID id) {
        productService.deleteProduct(id);
        return "redirect:/catalog?edit=true";
    }

    /*Deprecated*/
    @GetMapping("api/products")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("api/products/{id}")
    public Product getProductDetail(@PathVariable UUID id) {
        return productService.getProductById(id);
    }

    @GetMapping("api/products/color")
    public List<Product> getProductsByColor(@RequestParam String color) {
        return productService.getProductsByColor(color);
    }

    @GetMapping("api/products/category")
    public List<Product> getProductsByCategory(@RequestParam String category) {
        return productService.getProductsByCategory(category);
    }

    @PostMapping("api/products")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        if (productService.checkProductExists(product)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.createProduct(product));
    }

    @PutMapping("api/products")
    public ResponseEntity<Product> updateProduct(@RequestBody Product product) {
        Product updatedProduct = productService.updateProduct(product);
        if (updatedProduct != null) {
            return ResponseEntity.ok(updatedProduct);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("api/products/{id}")
    public ResponseEntity<List<Product>> delete(@PathVariable UUID id) {
        boolean isDeleted = productService.deleteProduct(id);
        if (isDeleted) {
            List<Product> remainingProducts = productService.getAllProducts();
            return ResponseEntity.ok(remainingProducts);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(null);
        }
    }
}
