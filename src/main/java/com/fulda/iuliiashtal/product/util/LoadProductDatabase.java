package com.fulda.iuliiashtal.product.util;

import com.fulda.iuliiashtal.product.model.entity.Product;
import com.fulda.iuliiashtal.product.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadProductDatabase {
    private static final Logger log = LoggerFactory.getLogger(LoadProductDatabase.class);

    @Bean
    CommandLineRunner initDatabase(ProductRepository repository) {
        return args -> {
            log.info("Preloading " + repository.save(new Product("Hat", 12.99, "S", "Yellow", "A casual yellow hat.", "Accessories")));
            log.info("Preloading " + repository.save(new Product("T-Shirt", 9.99, "S", "White", "A comfortable white T-shirt.", "Apparel")));
            log.info("Preloading " + repository.save(new Product("Gloves", 5.99, "M", "Blue", "Stylish blue gloves.", "Accessories")));
            log.info("Preloading " + repository.save(new Product("Jacket", 89.99, "L", "Black", "Warm black jacket.", "Outerwear")));
            log.info("Preloading " + repository.save(new Product("Sneakers", 69.99, "44", "Red", "Trendy red sneakers.", "Footwear")));
            log.info("Preloading " + repository.save(new Product("Dress", 100.00, "S", "Black", "Cute dress", "Dresses")));
            log.info("Preloading " + repository.save(new Product("Trousers", 20.00, "M", "Green", "-", "Trousers")));
            log.info("Preloading " + repository.save(new Product("Sweater", 60.00, "L", "Pink", "Nice sweater", "Sweaters")));
            log.info("Preloading " + repository.save(new Product("Jeans", 20.00, "XS", "Blue", "Smart blue jeans", "Jeans")));
            log.info("Preloading " + repository.save(new Product("Coat", 100.00, "S", "Beige", "Oversize coat.", "Outerwear")));
        };
    }
}
