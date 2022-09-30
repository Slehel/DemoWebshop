//package com.example.webshop.product;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import javax.transaction.Transactional;
//import java.util.List;
//import java.util.Objects;
//import java.util.Optional;
//
//@Service
//public class ProductService {
//    //Business logic for Product
//
//    private final ProductRepository productRepository;
//
//    @Autowired
//    public ProductService(ProductRepository productRepository) {
//        this.productRepository = productRepository;
//    }
//
//    public List<Product> getProducts() {
//        return productRepository.findAll();
//    }
//
//    public void addNewProduct(Product product) {
//        Optional<Product> productOptional = productRepository.findProductByName(product.getName());
//        if (productOptional.isPresent()){
//            throw new IllegalStateException("Product name already used");
//        }
//        productRepository.save(product);
//    }
//
//    public void deleteProduct(Long productId) {
//        boolean exists = productRepository.existsById(productId);
//        if (!exists){
//            throw new IllegalStateException("product with id " + productId +" does not exists!");
//        }
//        productRepository.deleteById(productId);
//    }
//    @Transactional
//    public void updateProduct(Long productId,
//                              String name,
//                              Integer price) {
//        Product product = productRepository.findById(productId).orElseThrow(() -> new IllegalStateException(
//                "product with id "+ productId +" does not exist "));
//
//        if (name != null && name.length() > 0 && !Objects.equals(product.getName(), name)) {
//            Optional<Product> productOptional = productRepository.findProductByName(name);
//            if(productOptional.isPresent()){
//                throw new IllegalStateException("name taken");
//            }
//            product.setName(name);
//        }
//
//        if (price != null  && !Objects.equals(product.getPrice(), price)) {
//            product.setPrice(price);
//        }
//    }
//}
