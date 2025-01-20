package com.fulda.iuliiashtal.product.controller;

import com.fulda.iuliiashtal.product.model.entity.Product;
import com.fulda.iuliiashtal.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class SaaSCatalogController {

    private final ProductService productService;

    @GetMapping("saas/catalog")
    public List<Product> viewCatalog() {
        return productService.getAllProducts();
    }
}
