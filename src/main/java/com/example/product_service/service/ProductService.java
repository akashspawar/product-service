package com.example.product_service.service;

import com.example.product_service.entity.Product;

import java.util.List;

public interface ProductService {

    Product addProduct(Product product);

    List<Product> getAllProducts();

    Product getProduct(Long id);

    Product deleteProduct(Long id);

    Product updateProduct(Product product);
}
