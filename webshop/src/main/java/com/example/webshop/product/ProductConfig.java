package com.example.webshop.product;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ProductConfig {

    @Bean
    CommandLineRunner commandLineRunner(ProductRepository repository) {
        return args -> {
            Product phone = new Product(
                    "Phone",
                    "this is a phone",
                    5,
                    0,
                    "kep.jpg"
            );

            Product laptop = new Product(
                    "Laptop",
                    "this is a laptop",
                    10,
                    0,
                    "kep2.jpg"
            );

            repository.saveAll(
                    List.of(phone, laptop)
            );
        };
    }
}
