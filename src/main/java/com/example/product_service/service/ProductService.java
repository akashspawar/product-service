package com.example.product_service.service;

import com.example.product_service.dto.ProductRequestDto;
import com.example.product_service.dto.ProductResponseDto;
import com.example.product_service.dto.RegisterRequestDto;
import com.example.product_service.entity.Product;
import com.example.product_service.entity.User;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {

    ProductResponseDto addProduct(ProductRequestDto dto);

    Page<ProductResponseDto> getAllProducts(int page,int size);

    ProductResponseDto getProduct(Long id);

    void deleteProduct(Long id);

    ProductResponseDto updateProduct(ProductRequestDto dto, Long id);

    List<ProductResponseDto> findByPriceGreaterThan(Double price);
}
