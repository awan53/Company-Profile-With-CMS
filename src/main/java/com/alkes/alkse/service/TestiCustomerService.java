package com.alkes.alkse.service;

import com.alkes.alkse.model.TestiCustomer;
import com.alkes.alkse.repository.TestiCustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TestiCustomerService {

    private TestiCustomerRepository testiCustomerRepository;

    @Autowired
    public TestiCustomerService(TestiCustomerRepository testiCustomerRepository) {
        this.testiCustomerRepository = testiCustomerRepository;
    }

    public List<TestiCustomer> findTestiCustomersAll() {
        return testiCustomerRepository.findAll();
    }

    public Optional<TestiCustomer> findTestiCustomerById(Long id) {
        return testiCustomerRepository.findById(id);
    }

    public TestiCustomer saveTestiCustomer(TestiCustomer testiCustomer) {
        return testiCustomerRepository.save(testiCustomer);
    }

    public void deleteTestiCustomer(Long id) {
        testiCustomerRepository.deleteById(id);
    }
}
