package com.example.product_service.controller;

import com.example.product_service.dto.ApiResponse;
import com.example.product_service.dto.ProductRequestDto;
import com.example.product_service.dto.ProductResponseDto;
import com.example.product_service.dto.ResponseBuilder;
import com.example.product_service.service.ProductService;
import com.example.product_service.entity.Product;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<ProductResponseDto>> saveProduct(@Valid @RequestBody ProductRequestDto dto){
        ProductResponseDto productDto = service.addProduct(dto);
       /* ApiResponse<ProductResponseDto> response= new ApiResponse<>();
        response.setTimestamp(LocalDateTime.now());
        response.setStatus(HttpStatus.OK.value());
        response.setMessage("Product fetched successfully");
        response.setData(productDto);

        return ResponseEntity.ok(response);*/

        //new using builder
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
    public ResponseEntity<List<Product>> getAll(){

        List<Product> allProducts = service.getAllProducts();
       // return ResponseEntity.status(HttpStatus.OK).body(allProducts);
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
    public ResponseEntity<Product> getProduct(@PathVariable Long id){

        Product product = service.getProduct(id);
        return ResponseEntity.status(HttpStatus.OK).body(product);

    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@RequestBody Product product, @PathVariable Long id){

        Product product1 = service.updateProduct(product, id);
        return ResponseEntity.ok(product1);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductResponseDto>> deleteProduct(@PathVariable Long id) {

        ApiResponse<ProductResponseDto> response = new ApiResponse<>();

        response.setTimestamp(LocalDateTime.now());
        response.setStatus(HttpStatus.OK.value());
        response.setMessage("Product deleted successfully");
        service.deleteProduct(id);
        return ResponseEntity.ok(response);
    }
}
