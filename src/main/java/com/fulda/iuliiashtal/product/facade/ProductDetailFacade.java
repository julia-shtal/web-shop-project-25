package com.fulda.iuliiashtal.product.facade;

import com.fulda.iuliiashtal.inventory.service.InventoryService;
import com.fulda.iuliiashtal.product.model.dto.ProductDetailDTO;
import com.fulda.iuliiashtal.product.model.entity.Product;
import com.fulda.iuliiashtal.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductDetailFacade {

    private final ProductService productService;
    private final InventoryService inventoryService;

    public ProductDetailDTO getProductDetail(UUID productId) {
        Product product = productService.getProductById(productId);
        int stock = inventoryService.getStockForProductId(productId);
        boolean isSoldOut = stock <= 0;

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
