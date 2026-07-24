package com.example.product_service.service;

import com.example.product_service.dto.ProductRequestDto;
import com.example.product_service.dto.ProductResponseDto;
import com.example.product_service.entity.Product;
import com.example.product_service.exception.ProductNotFoundException;
import com.example.product_service.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private final ProductRepository repository;


    public ProductServiceImpl(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public ProductResponseDto addProduct(ProductRequestDto dto) {
        Product product = new Product();
         //Convert DTO → Entity
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setQuantity(dto.getQuantity());
//after saving
        Product save = repository.save(product);
//Convert Entity → Response DTO
        ProductResponseDto response = new ProductResponseDto();
        response.setId(save.getId());
        response.setName(save.getName());
        response.setDescription(save.getDescription());
        response.setPrice(save.getPrice());
        response.setQuantity(save.getQuantity());
        return response;
    }

    @Override
    public List<Product> getAllProducts() {
        return repository.findAll();
    }

    @Override
    public Product getProduct(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id : " + id));
    }

    @Override
    public void deleteProduct(Long id) {
        Product product = repository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id : " + id));
         repository.delete(product);
    }

    @Override
    public ProductResponseDto updateProduct(ProductRequestDto dto, Long id) {
// Step 1: Fetch existing product
        Product product = repository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id : " + id));
        // Step 2: Update fields
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setQuantity(dto.getQuantity());
        // Step 3: Save updated entity
        Product updatedProduct = repository.save(product);
        // Step 4: Convert Entity -> Response DTO
        ProductResponseDto response = new ProductResponseDto();
        response.setId(updatedProduct.getId());
        response.setName(updatedProduct.getName());
        response.setDescription(updatedProduct.getDescription());
        response.setPrice(updatedProduct.getPrice());
        response.setQuantity(updatedProduct.getQuantity());
        return response;
    }
}
