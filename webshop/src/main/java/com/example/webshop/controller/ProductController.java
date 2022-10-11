package com.example.webshop.controller;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import com.example.webshop.db.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @GetMapping("/get/{id}")
    public List<Product> getProduct(@PathVariable("id") long id) throws IOException, NoSuchAlgorithmException, InvalidKeyException {
        Product product = productRepository.getOne(id);
        //product.setPicByte(productService.getImage(product.getName()));
        List<Product> products = new ArrayList<>();
        products.add(product);
        return products;
    }

    @PostMapping("/upload")
    public void uploadImage(@RequestParam("imageFile") MultipartFile file) throws IOException {
        this.bytes = file.getBytes();
    }
    @PostMapping("/add")
    public void createProduct(@RequestBody Product product) throws IOException, NoSuchAlgorithmException, InvalidKeyException {
        product.setPicByte(productService.getImage(product.getName()));
        productRepository.save(product);
    }
//    @PostMapping("/cart")
//    public void createCart(@RequestBody ArrayList<Product> cart) throws IOException {
//
//    }

    @PutMapping("/update")
    public void updateProduct(@RequestBody Product product) {
        productRepository.save(product);
    }

    @DeleteMapping(path = { "/{id}" })
    public Product deleteProduct(@PathVariable("id") long id) {
        Product product = productRepository.getOne(id);
        productRepository.deleteById(id);
        return product;
    }
}
