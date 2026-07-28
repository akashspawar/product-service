package com.example.product_service.service;

import com.example.product_service.dto.ProductRequestDto;
import com.example.product_service.dto.ProductResponseDto;
import com.example.product_service.dto.RegisterRequestDto;
import com.example.product_service.entity.Product;
import com.example.product_service.entity.User;
import com.example.product_service.exception.ProductNotFoundException;
import com.example.product_service.mapper.ProductMapper;
import com.example.product_service.repository.ProductRepository;
import com.example.product_service.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;

    private final UserRepository userRepository;



    private static final Logger logs = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Override
    public ProductResponseDto addProduct(ProductRequestDto dto) {
        Product product = ProductMapper.toEntity(dto);
        Product saved = repository.save(product);
        return ProductMapper.toResponseDto(saved);
    }

    @Override
    public Page<ProductResponseDto> getAllProducts(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("name"));
        Page<Product> pageProduct = repository.findAll(pageable);
                return pageProduct.map(ProductMapper::toResponseDto);
    }

    @Override
    public ProductResponseDto getProduct(Long id) {
        Product product = repository.findById(id)
                .orElseThrow(() ->
                        new ProductNotFoundException("Product not found"));
        return ProductMapper.toResponseDto(product);
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
        Product product = repository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id : " + id));
        ProductMapper.updateEntity(product, dto);
        Product updated = repository.save(product);
        return ProductMapper.toResponseDto(updated);
    }

    @Override
    public List<ProductResponseDto> findByPriceGreaterThan(Double price) {
        return repository.findByPriceGreaterThan(price)
                .stream()
                .map(ProductMapper::toResponseDto)
                .toList();
    }
}
