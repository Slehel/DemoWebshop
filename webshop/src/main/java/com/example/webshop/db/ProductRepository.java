package com.example.webshop.db;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.webshop.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}


