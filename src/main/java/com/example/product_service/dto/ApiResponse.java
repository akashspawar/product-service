package com.example.product_service.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ApiResponse<T> {

    private LocalDateTime timestamp;
    private Integer status;
    private String message;
    private T data;
}