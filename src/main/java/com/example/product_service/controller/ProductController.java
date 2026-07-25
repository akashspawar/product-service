package com.example.product_service.controller;

import com.example.product_service.dto.ApiResponse;
import com.example.product_service.dto.ProductRequestDto;
import com.example.product_service.dto.ProductResponseDto;
import com.example.product_service.dto.ResponseBuilder;
import com.example.product_service.service.ProductService;
import com.example.product_service.entity.Product;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Product API",
        description = "Product Management APIs")
public class ProductController {


    private final ProductService service;
    private static final Logger logs = LoggerFactory.getLogger(ProductController.class);

    @PostMapping
    @Operation(
            summary = "Create Product",
            description = "Creates a new product")
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
    public ResponseEntity<Page<ProductResponseDto>> getAll(@RequestParam(defaultValue = "0")int page,
                                                           @RequestParam(defaultValue = "5")int size) {
        Page<ProductResponseDto> allProducts = service.getAllProducts(page,size);
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
    @Operation(
            summary = "Get Product",
            description = "Fetch product by id")
    public ResponseEntity<ApiResponse<ProductResponseDto>> getProduct(@PathVariable Long id) {
        ProductResponseDto response = service.getProduct(id);
        return ResponseEntity.ok(
                ResponseBuilder.success(
                        "get particular product",
                        response,
                        HttpStatus.OK
                )
        );
       // return ResponseEntity.status(HttpStatus.OK).body(response);
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
    @Operation(
            summary = "Delete Product")
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

    @GetMapping("/GreaterThanPrice")
    public ResponseEntity<ApiResponse<List<ProductResponseDto>>> priceGreaterThan(@RequestParam Double price){
        List<ProductResponseDto> byPriceLessThan = service.findByPriceLessThan(price);
        return ResponseEntity.ok(
                ResponseBuilder.success(
                        "getting conditional result",
                        byPriceLessThan,
                        HttpStatus.OK
                )
        );
    }

}
