package com.example.product_service.controller;

import com.example.product_service.dto.ProductRequestDto;
import com.example.product_service.dto.ProductResponseDto;
import com.example.product_service.service.ProductService;
import com.example.product_service.entity.Product;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<ProductResponseDto> saveProduct(@Valid @RequestBody ProductRequestDto dto){
        ProductResponseDto response = service.addProduct(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAll(){

        List<Product> allProducts = service.getAllProducts();
        return ResponseEntity.status(HttpStatus.OK).body(allProducts);

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
    public String deleteProduct(@PathVariable Long id){
       service.deleteProduct(id);
       return "Deleted successfully.............";
    }
}
