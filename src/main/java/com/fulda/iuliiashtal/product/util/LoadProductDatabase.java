package com.fulda.iuliiashtal.product.util;

import com.fulda.iuliiashtal.product.model.entity.Product;
import com.fulda.iuliiashtal.product.model.enums.Categories;
import com.fulda.iuliiashtal.product.model.enums.Colors;
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
            log.info("Preloading " + repository.save(new Product("Hat", 12.99, "S", Colors.YELLOW.name(), "A casual yellow hat.", Categories.ACCESSORIES.name())));
            log.info("Preloading " + repository.save(new Product("T-Shirt", 9.99, "S", Colors.WHITE.name(), "A comfortable white T-shirt.", Categories.APPAREL.name())));
            log.info("Preloading " + repository.save(new Product("Gloves", 5.99, "M", Colors.BLUE.name(), "Stylish blue gloves.", Categories.ACCESSORIES.name())));
            log.info("Preloading " + repository.save(new Product("Jacket", 89.99, "L", Colors.BLACK.name(), "Warm black jacket.", Categories.OUTERWEAR.name())));
            log.info("Preloading " + repository.save(new Product("Sneakers", 69.99, "44", Colors.RED.name(), "Trendy red sneakers.", Categories.FOOTWEAR.name())));
            log.info("Preloading " + repository.save(new Product("Dress", 100.00, "S", Colors.BLACK.name(), "Cute dress.", Categories.DRESSES.name())));
            log.info("Preloading " + repository.save(new Product("Trousers", 20.00, "M", Colors.GREEN.name(), "Green trousers.", Categories.TROUSERS.name())));
            log.info("Preloading " + repository.save(new Product("Sweater", 60.00, "L", Colors.PINK.name(), "Nice sweater.", Categories.SWEATERS.name())));
            log.info("Preloading " + repository.save(new Product("Jeans", 20.00, "XS", Colors.BLUE.name(), "Smart blue jeans.", Categories.JEANS.name())));
            log.info("Preloading " + repository.save(new Product("Coat", 100.00, "S", Colors.BEIGE.name(), "Oversize coat.", Categories.OUTERWEAR.name())));

            log.info("Preloading " + repository.save(new Product("Scarf", 15.99, "One Size", Colors.RED.name(), "Cozy red scarf for winter.", Categories.ACCESSORIES.name())));
            log.info("Preloading " + repository.save(new Product("Cap", 10.49, "M", Colors.GRAY.name(), "Lightweight gray cap.", Categories.ACCESSORIES.name())));
            log.info("Preloading " + repository.save(new Product("Boots", 120.00, "42", Colors.BROWN.name(), "Durable brown leather boots.", Categories.FOOTWEAR.name())));
            log.info("Preloading " + repository.save(new Product("Socks", 4.99, "L", Colors.BLACK.name(), "Comfortable black socks.", Categories.FOOTWEAR.name())));
            log.info("Preloading " + repository.save(new Product("Blouse", 35.50, "M", Colors.WHITE.name(), "Elegant white blouse.", Categories.APPAREL.name())));
            log.info("Preloading " + repository.save(new Product("Shorts", 25.00, "L", Colors.BLUE.name(), "Stylish denim shorts.", Categories.APPAREL.name())));
            log.info("Preloading " + repository.save(new Product("Sandals", 45.00, "38", Colors.BEIGE.name(), "Comfortable summer sandals.", Categories.FOOTWEAR.name())));
            log.info("Preloading " + repository.save(new Product("Watch", 150.00, "One Size", Colors.SILVER.name(), "Luxury silver wristwatch.", Categories.ACCESSORIES.name())));
            log.info("Preloading " + repository.save(new Product("Belt", 19.99, "M", Colors.BROWN.name(), "Classic brown leather belt.", Categories.ACCESSORIES.name())));
            log.info("Preloading " + repository.save(new Product("Cardigan", 65.00, "M", Colors.PURPLE.name(), "Warm purple cardigan.", Categories.SWEATERS.name())));
        };
    }
}
