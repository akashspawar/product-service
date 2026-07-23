package com.example.product_service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProductRequestDto {

    @NotBlank(message = "Product name cannot be blank")
    private String name;

    private String description;

    @NotNull(message = "Price is required")
    private Double price;

    @NotNull(message = "Quantity is required")
    private Integer quantity;
}
