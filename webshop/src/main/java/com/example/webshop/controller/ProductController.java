package com.example.webshop.controller;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import com.example.webshop.db.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.webshop.db.ProductRepository;
import com.example.webshop.model.Product;
import com.example.webshop.service.ProductService;

import javax.annotation.security.RolesAllowed;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "products")
public class ProductController {

    private byte[] bytes;

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductService productService;

    @GetMapping("/get")
    @RolesAllowed({"User","Admin"})
    public ResponseEntity<List<Product>> getProducts() {
        return ResponseEntity.ok(productRepository.findAll());
    }




    @PostMapping("/add")
    @RolesAllowed("Admin")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) throws IOException, NoSuchAlgorithmException, InvalidKeyException {
        product.setPicByte(productService.getImage(product.getName()));
        return ResponseEntity.ok(productRepository.save(product));

    }

    @PutMapping("/update")
    @RolesAllowed("Admin")
    public ResponseEntity<Product> updateProduct(@RequestBody Product product) {
      return  ResponseEntity.ok(productRepository.save(product));
    }

    @DeleteMapping(path = { "/{id}" })
    @RolesAllowed("Admin")
    public ResponseEntity<Product> deleteProduct(@PathVariable("id") long id) {
        Product product = productRepository.getOne(id);
        productRepository.deleteById(id);
        return  ResponseEntity.ok(product);
    }
}
