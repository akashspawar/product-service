package com.example.product_service.service;

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
    public Product addProduct(Product product) {
        return repository.save(product);
    }

    @Override
    public List<Product> getAllProducts() {
        return repository.findAll();
    }

    @Override
    public Product getProduct(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new ProductNotFoundException(
                                "Product not found with id : " + id));

    }

    @Override
    public void deleteProduct(Long id) {
        Product product = repository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id : " + id));
         repository.delete(product);
    }

    @Override
    public Product updateProduct(Product product, Long id) {

        System.out.println("Update API called with id = " + id);

        Product product1 = repository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id : " + id));


            product1.setName(product.getName());
            product1.setDescription(product.getDescription());
            product1.setPrice(product.getPrice());
            product1.setQuantity(product.getQuantity());

        return  repository.save(product1);
    }
}
