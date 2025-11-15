package com.example.GroupLink.Customer;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CustomerService {

    CustomerRepository customerRepository;

    CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    private static final Path UPLOAD_DIR = Paths.get("src/main/resources/static/customer/images");

    public Customer createCustomer(Customer customer) {
        if(customerRepository.existsByEmail(customer.getEmail())) {
            throw new IllegalArgumentException("User already registed with this email");
        }
        return customerRepository.save(customer);
    }

    public Customer updateCustomer(Long id, Customer CustomerDetails){
        Customer customer = customerRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Customer not found"));

        customer.setName(CustomerDetails.getName());
        customer.setEmail(CustomerDetails.getEmail());
        customer.setPassword(CustomerDetails.getPassword());

        return customerRepository.save(customer);
    }

    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Customer not found"));
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public void deleteCustomer(Long id) {
        if (!customerRepository.existsById(id)) {
            throw new EntityNotFoundException("Customer not found");
        }
        customerRepository.deleteById(id);
    }
    
}
