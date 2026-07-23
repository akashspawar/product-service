package com.example.product_service.service;

import com.example.product_service.dto.ProductRequestDto;
import com.example.product_service.dto.ProductResponseDto;
import com.example.product_service.entity.Product;

import java.util.List;

public interface ProductService {

    ProductResponseDto addProduct(ProductRequestDto dto);

    List<Product> getAllProducts();

    Product getProduct(Long id);

    void deleteProduct(Long id);

    Product updateProduct(Product product, Long id);
}
