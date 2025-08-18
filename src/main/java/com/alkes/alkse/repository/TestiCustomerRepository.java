package com.alkes.alkse.repository;

import com.alkes.alkse.model.TestiCustomer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestiCustomerRepository extends JpaRepository<TestiCustomer, Long> {
}
