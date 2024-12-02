package com.fulda.iuliiashtal.product.controller;

import com.fulda.iuliiashtal.product.facade.ProductDetailFacade;
import com.fulda.iuliiashtal.product.model.dto.ProductDetailDTO;
import com.fulda.iuliiashtal.product.model.entity.Product;
import com.fulda.iuliiashtal.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * Controller responsible for handling product-related operations such as
 * viewing, searching, filtering, adding, updating, and deleting products.
 */
@Controller
@RequiredArgsConstructor
public class ProductController {

    private final ProductDetailFacade productDetailFacade;
    private final ProductService productService;

    /**
     * Displays the product catalog with all products.
     *
     * @param model the {@link Model} object to add attributes to the view.
     * @param edit  a flag indicating whether the edit mode is enabled.
     * @return the name of the catalog view.
     */
    @GetMapping("/catalog")
    public String viewCatalog(Model model, @RequestParam(required = false, defaultValue = "true") boolean edit) {
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);
        model.addAttribute("editMode", edit);
        return "catalog";
    }

    /**
     * Displays the paginated product catalog.
     *
     * @param model    the {@link Model} object to add attributes to the view.
     * @param pageable the {@link Pageable} object to handle pagination details.
     * @return the name of the catalog view.
     */
    @GetMapping("/catalog-paginated")
    public String viewCatalogPaginated(Model model, @PageableDefault(size = 3) Pageable pageable) {
        Page<Product> page = productService.getAllPagedProducts(pageable);

        model.addAttribute("products", page.getContent());
        model.addAttribute("currentPage", page.getNumber() != 0 ? page.getNumber() : 0);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("hasNext", page.hasNext());
        model.addAttribute("hasPrevious", page.hasPrevious());

        return "catalog";
    }

    /**
     * Filters products by color and displays them in the catalog view.
     *
     * @param color the color to filter products by.
     * @param model the {@link Model} object to add attributes to the view.
     * @return the name of the catalog view.
     */
    @GetMapping("/product/color")
    public String getProductsByColor(@RequestParam String color, Model model) {
        List<Product> filteredProducts = productService.getProductsByColor(color);
        model.addAttribute("products", filteredProducts);
        return "catalog";
    }

    /**
     * Filters products by category and displays them in the catalog view.
     *
     * @param category the category to filter products by.
     * @param model    the {@link Model} object to add attributes to the view.
     * @return the name of the catalog view.
     */
    @GetMapping("/product/category")
    public String getProductsByCategory(@RequestParam String category, Model model) {
        List<Product> filteredProducts = productService.getProductsByCategory(category);
        model.addAttribute("products", filteredProducts);
        return "catalog";
    }

    /**
     * Searches for products by name and displays them in the catalog view.
     *
     * @param query the search query string.
     * @param model the {@link Model} object to add attributes to the view.
     * @return the name of the catalog view.
     */
    @GetMapping("product/search")
    public String searchProducts(@RequestParam String query, Model model) {
        List<Product> matchingProducts = productService.getProductsByName(query);
        model.addAttribute("products", matchingProducts);
        return "catalog";
    }

    /**
     * Displays the details of a specific product.
     *
     * @param id    the unique identifier of the product.
     * @param model the {@link Model} object to add attributes to the view.
     * @return the name of the product detail view.
     */
    @GetMapping("/product/{id}")
    public String viewProductDetail(@PathVariable UUID id, Model model) {
        ProductDetailDTO productDetail = productDetailFacade.getProductDetail(id);
        model.addAttribute("productDetail", productDetail);
        return "productDetail";
    }

    /**
     * Displays the form to add a new product.
     *
     * @param model the {@link Model} object to add attributes to the view.
     * @return the name of the add-product view.
     */
    @GetMapping("/add-product")
    public String showAddProductForm(Model model) {
        model.addAttribute("product", new Product());
        return "add-product";
    }

    /**
     * Handles the submission of a new product.
     *
     * @param product the {@link Product} object to be created.
     * @return a redirect to the newly created product's detail page.
     */
    @PostMapping("/add-product")
    public String addProduct(@ModelAttribute Product product) {
        productService.createProduct(product);
        return "redirect:/product/" + product.getId();
    }

    /**
     * Deletes a specific product and redirects to the catalog view.
     *
     * @param id the unique identifier of the product to be deleted.
     * @return a redirect to the catalog view with edit mode enabled.
     */
    @GetMapping("/product-delete/{id}")
    public String deleteProduct(@PathVariable UUID id) {
        productService.deleteProduct(id);
        return "redirect:/catalog?edit=true";
    }

    /**
     * Returns a list of all products (API endpoint).
     *
     * @return a list of all products.
     */
    @GetMapping("api/products")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    /**
     * Returns the details of a specific product (API endpoint).
     *
     * @param id the unique identifier of the product.
     * @return the {@link Product} object containing product details.
     */
    @GetMapping("api/products/{id}")
    public Product getProductDetail(@PathVariable UUID id) {
        return productService.getProductById(id);
    }

    /**
     * Returns a list of products filtered by category (API endpoint).
     *
     * @param category the category to filter products by.
     * @return a list of filtered products.
     */
    @GetMapping("api/products/category")
    public List<Product> getProductsByCategory(@RequestParam String category) {
        return productService.getProductsByCategory(category);
    }

    /**
     * Updates an existing product (API endpoint).
     *
     * @param product the {@link Product} object containing updated details.
     * @return a {@link ResponseEntity} containing the updated product or a 404 status if not found.
     */
    @PutMapping("api/products")
    public ResponseEntity<Product> updateProduct(@RequestBody Product product) {
        Product updatedProduct = productService.updateProduct(product);
        if (updatedProduct != null) {
            return ResponseEntity.ok(updatedProduct);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    /**
     * Deletes a specific product and returns the remaining products (API endpoint).
     *
     * @param id the unique identifier of the product to be deleted.
     * @return a {@link ResponseEntity} containing the remaining products or a 404 status if not found.
     */
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