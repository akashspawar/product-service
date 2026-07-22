package com.example.product_service.controller;

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
    public ResponseEntity<Product> saveProduct(@Valid @RequestBody Product product){
        Product product1 = service.addProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(product1);
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAll(){

        List<Product> allProducts = service.getAllProducts();
        return ResponseEntity.status(HttpStatus.OK).body(allProducts);

    }

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable Long id){

        return service.getProduct(id);

    }

    @PutMapping
    public Product updateProduct(@RequestBody Product product){

        return service.updateProduct(product);

    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id){

        service.deleteProduct(id);

    }
}
