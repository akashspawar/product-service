package com.example.product_service.dto;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class ResponseBuilder {

    public static <T> ApiResponse<T> success(
            String message,
            T data,
            HttpStatus status) {

        ApiResponse<T> response = new ApiResponse<>();

        response.setTimestamp(LocalDateTime.now());
        response.setStatus(status.value());
        response.setMessage(message);
        response.setData(data);

        return response;
    }

    public static <T> ApiResponse<T> error(String message, HttpStatus status) {

        ApiResponse<T> response = new ApiResponse<>();
      //  response.setSuccess(false);
        response.setMessage(message);
        response.setData(null);
        response.setStatus(status.value());

        return response;
    }
}
