package com.fulda.iuliiashtal.product.controller;

import com.fulda.iuliiashtal.product.facade.ProductDetailFacade;
import com.fulda.iuliiashtal.product.model.dto.ProductDetailDTO;
import com.fulda.iuliiashtal.product.model.entity.Product;
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

    private final ProductDetailFacade productDetailFacade;
    private final ProductService productService;

    @GetMapping("/catalog")
    public String viewCatalog(Model model, @RequestParam(required = false, defaultValue = "true") boolean edit) {
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);
        model.addAttribute("editMode", edit);
        return "catalog";
    }

    @GetMapping("/product/color")
    public String getProductsByColor(@RequestParam String color, Model model) {
        List<Product> filteredProducts = productService.getProductsByColor(color);
        model.addAttribute("products", filteredProducts);
        return "catalog";
    }

    @GetMapping("/product/category")
    public String getProductsByCategory(@RequestParam String category, Model model) {
        List<Product> filteredProducts = productService.getProductsByCategory(category);
        model.addAttribute("products", filteredProducts);
        return "catalog";
    }

    @GetMapping("product/search")
    public String searchProducts(@RequestParam String query, Model model) {
        List<Product> matchingProducts = productService.getProductsByName(query);
        model.addAttribute("products", matchingProducts);
        return "catalog";
    }

    @GetMapping("/product/{id}")
    public String viewProductDetail(@PathVariable UUID id, Model model) {
        ProductDetailDTO productDetail = productDetailFacade.getProductDetail(id);
        model.addAttribute("productDetail", productDetail);
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



    @GetMapping("api/products/category")
    public List<Product> getProductsByCategory(@RequestParam String category) {
        return productService.getProductsByCategory(category);
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
