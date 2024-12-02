package com.fulda.iuliiashtal.product.facade;

import com.fulda.iuliiashtal.inventory.service.InventoryService;
import com.fulda.iuliiashtal.product.model.dto.ProductDetailDTO;
import com.fulda.iuliiashtal.product.model.entity.Product;
import com.fulda.iuliiashtal.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Facade class responsible for aggregating and providing detailed product information
 * by combining data from {@link ProductService} and {@link InventoryService}.
 */
@Service
@RequiredArgsConstructor
public class ProductDetailFacade {

    private final ProductService productService;
    private final InventoryService inventoryService;

    /**
     * Retrieves detailed information about a product, including its stock and sold-out status.
     *
     * @param productId the unique identifier of the product.
     * @return a {@link ProductDetailDTO} containing the product details and stock information.
     */
    public ProductDetailDTO getProductDetail(UUID productId) {
        // Fetch product information using ProductService
        Product product = productService.getProductById(productId);

        // Fetch stock information for the product using InventoryService
        int stock = inventoryService.getStockForProductId(productId);

        // Determine if the product is sold out based on the stock level
        boolean isSoldOut = stock <= 0;

        // Construct and return a ProductDetailDTO with the aggregated information
        return new ProductDetailDTO(
                productId,
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getColor(),
                product.getCategory(),
                product.getSize(),
                stock,
                isSoldOut
        );
    }
}
