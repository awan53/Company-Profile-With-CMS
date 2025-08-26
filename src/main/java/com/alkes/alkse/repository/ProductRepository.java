package com.alkes.alkse.repository;

import com.alkes.alkse.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    // Method to find all products with pagination
    Page<Product> findAll(Pageable pageable);
    // Additional query methods can be defined here if needed
    List<Product> findTop3ByOrderByIdDesc();

    Page<Product> findByNameContainingIgnoreCaseOrCategory_NameContainingIgnoreCase(String name, String category, Pageable pageable);

    List<Product> findByNameContainingIgnoreCaseOrCategory_NameContainingIgnoreCase(String name, String categoryName);


}
