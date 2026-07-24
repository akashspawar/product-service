package com.example.product_service.controller;

import com.example.product_service.dto.ApiResponse;
import com.example.product_service.dto.ProductRequestDto;
import com.example.product_service.dto.ProductResponseDto;
import com.example.product_service.dto.ResponseBuilder;
import com.example.product_service.service.ProductService;
import com.example.product_service.entity.Product;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
@Slf4j
public class ProductController {


    private final ProductService service;
    private static final Logger logs = LoggerFactory.getLogger(ProductController.class);

    @PostMapping
    public ResponseEntity<ApiResponse<ProductResponseDto>> saveProduct(@Valid @RequestBody ProductRequestDto dto) {
        ProductResponseDto productDto = service.addProduct(dto);
        logs.info("updated product details: {}", productDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(
                        ResponseBuilder.success(
                                "Product saved successfully",
                                productDto,
                                HttpStatus.CREATED
                        )
                );

    }

    @GetMapping
    public ResponseEntity<List<Product>> getAll() {
        List<Product> allProducts = service.getAllProducts();
        return ResponseEntity.status(HttpStatus.OK)
                .body(
                        ResponseBuilder.success(
                                "Fetch data successfully",
                                allProducts,
                                HttpStatus.OK
                        ).getData()
                );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable Long id) {
        Product product = service.getProduct(id);
        return ResponseEntity.status(HttpStatus.OK).body(product);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductResponseDto>> updateProduct(@Valid @RequestBody ProductRequestDto dto, @PathVariable Long id) {
        ProductResponseDto responseDto = service.updateProduct(dto, id);
        return ResponseEntity.ok(
                ResponseBuilder.success(
                        "Data Updated successfully",
                        responseDto,
                        HttpStatus.OK
                )
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductResponseDto>> deleteProduct(@PathVariable Long id) {
        service.deleteProduct(id);
        return ResponseEntity.ok(
                ResponseBuilder.success(
                        "Deleted Successfully...........",
                        null,
                        HttpStatus.OK
                )
        );
    }
}
