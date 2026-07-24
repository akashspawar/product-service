package com.example.product_service.mapper;

import com.example.product_service.dto.ProductRequestDto;
import com.example.product_service.dto.ProductResponseDto;
import com.example.product_service.entity.Product;

public class ProductMapper {
    //Entity → Response DTO
    public static ProductResponseDto toResponseDto(Product product){

        ProductResponseDto dto = new ProductResponseDto();

        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setDescription(product.getDescription());
        dto.setPrice(product.getPrice());
        dto.setQuantity(product.getQuantity());

        return dto;
    }
//Request DTO → Entity
    public static Product toEntity(ProductRequestDto dto){

        Product product = new Product();

        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setQuantity(dto.getQuantity());

        return product;
    }
//Update Existing Entity
//because we're updating an existing object.
//This is my favorite method because it's used a lot in production.
    public static void updateEntity(
            Product product,
            ProductRequestDto dto){

        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setQuantity(dto.getQuantity());
    }
}
