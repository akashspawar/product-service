package com.example.product_service.service;

import com.example.product_service.dto.ProductRequestDto;
import com.example.product_service.dto.ProductResponseDto;
import com.example.product_service.entity.Product;
import com.example.product_service.exception.ProductNotFoundException;
import com.example.product_service.repository.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;

    private static final Logger logs = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Override
    public ProductResponseDto addProduct(ProductRequestDto dto) {
        log.info("Saving new product: {}", dto.getName());
        Product product = new Product();
         //Convert DTO → Entity
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setQuantity(dto.getQuantity());
//after saving
        Product save = repository.save(product);
        log.info("Product saved successfully with id {}", save.getId());
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
        logs.info("Fetching product with id {}", id);
        Product product = repository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id : " + id));
         log.info("Product found successfully with id {}", id);
        return product;
    }

    @Override
    public void deleteProduct(Long id) {
        logs.info("getting deleted ID is{}",id);
        Product product = repository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id : " + id));
        logs.info("Deleted ID is: {}",id);
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
