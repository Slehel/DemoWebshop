package com.example.webshop.service;


import com.example.webshop.db.ProductRepository;
import io.minio.GetObjectArgs;
import io.minio.GetPresignedObjectUrlArgs;
import io.minio.MinioClient;
import io.minio.errors.MinioException;
import java.io.IOException;
import java.io.InputStream;

import io.minio.http.Method;
import org.apache.tomcat.jni.Global;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.nio.charset.StandardCharsets;

@Service
public class ProductService {

    public String keplink;
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    public String getImage(String productname) throws IOException, NoSuchAlgorithmException, InvalidKeyException {
        try {
            /* play.min.io for test and development. */
            MinioClient minioClient =
                    MinioClient.builder()
                            .endpoint("http://localhost:9000")
                            .credentials("minioadmin", "minioadmin")
                            .build();
            String url =
                    minioClient.getPresignedObjectUrl(
                            GetPresignedObjectUrlArgs.builder()
                                    .method(Method.GET)
                                    .bucket("webbucket")
                                    .object(productname+".jpg")
                                    .expiry(60 * 60 * 24 * 7)
                                    .build());
            System.out.println(url);
            keplink=url;
            return url;
        } catch (MinioException e) {
            System.out.println("Error occurred: " + e);
        }
        return keplink;
    }





    }
