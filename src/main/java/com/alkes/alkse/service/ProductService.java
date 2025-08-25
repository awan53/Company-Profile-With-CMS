package com.alkes.alkse.service;

import org.springframework.stereotype.Service;
import com.alkes.alkse.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.alkes.alkse.model.Product;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }
    public List<Product> getTop3Products() {
        return productRepository.findTop3ByOrderByIdDesc();
    }

    public Optional<Product> findProductById(Long id) {
        return productRepository.findById(id);
    }
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
