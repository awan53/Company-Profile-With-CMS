package com.alkes.alkse.repository;

import com.alkes.alkse.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    // Additional query methods can be defined here if needed
    List<Product> findTop3ByOrderByIdDesc();
}
