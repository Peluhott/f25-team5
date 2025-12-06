package com.example.GroupLink.Customer;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.GroupLink.CustomerNotification.CustomerNotification;
import com.example.GroupLink.Group.Group;
import com.example.GroupLink.GroupMembership.GroupMembership;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CustomerService {

    CustomerRepository customerRepository;

    CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    private static final String UPLOAD_DIR = "backend-api/src/main/resources/static/customer/images/";

    public Customer createCustomer(Customer customer, MultipartFile profilePicture) {
        Customer newCustomer = customerRepository.save(customer);

        String originalFileName = profilePicture.getOriginalFilename();

    try {
      if (originalFileName != null && originalFileName.contains(".")) {
        String fileExtension = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
        String fileName = String.valueOf(newCustomer.getId()) + "." + fileExtension;
        Path filePath = Paths.get(UPLOAD_DIR + fileName);

        InputStream inputStream = profilePicture.getInputStream();

        Files.createDirectories(Paths.get(UPLOAD_DIR));// Ensure directory exists
        Files.copy(inputStream, filePath,
            StandardCopyOption.REPLACE_EXISTING);// Save picture file
        newCustomer.setProfilePicturePath(fileName);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

        return customerRepository.save(newCustomer);

    }

    public Customer updateCustomer(Long id, Customer CustomerDetails){
        Customer customer = customerRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Customer not found"));

        customer.setUsername(CustomerDetails.getUsername());
        customer.setEmail(CustomerDetails.getEmail());
        customer.setPassword(CustomerDetails.getPassword());

        return customerRepository.save(customer);
    }

    public Customer updateCustomerPFP(Long id, Customer customer, MultipartFile profilePicture){
        String originalFileName = profilePicture.getOriginalFilename();

    try {
      if (originalFileName != null && originalFileName.contains(".")) {
        String fileExtension = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
        String fileName = String.valueOf(id) + "." + fileExtension;
        Path filePath = Paths.get(UPLOAD_DIR + fileName);

        InputStream inputStream = profilePicture.getInputStream();
        Files.deleteIfExists(filePath);
        Files.copy(inputStream, filePath,
            StandardCopyOption.REPLACE_EXISTING);// Save picture file
        customer.setProfilePicturePath(fileName);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return customerRepository.save(customer);
    }

    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Customer not found"));
    }

    public Customer getCustomerByUsername(String username) {
        return customerRepository.findByUsername(username);
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public boolean authenticate(String username, String password) {
        Optional<Customer> customerOptional = customerRepository.findByUsername_(username);
        if (customerOptional.isPresent()) {
            Customer customer = customerOptional.get();
            return customer.getPassword().equals(password);
        }
        return false;
    }

    public boolean isEmailTaken(String email) {
        return customerRepository.existsByEmail(email);
    }

    public boolean isUsernameTaken(String username) {
        return customerRepository.existsByUsername(username);
    }

    public List<CustomerNotification> getCustomerNotifications(Long customerId) {
        Customer customer = getCustomerById(customerId);
        return customerRepository.findAllNotificationsByIdDesc(customer.getId());
    }

    public List<GroupMembership> getCustomerActiveGroupMemberships(Long customerId) {
        Customer customer = getCustomerById(customerId);
        return customerRepository.findAcceptedActiveGroupMembershipsByIdDesc(customer.getId());
    }

    public List<GroupMembership> getCustomerInactiveGroupMemberships(Long customerId) {
        Customer customer = getCustomerById(customerId);
        return customerRepository.findAcceptedInactiveGroupMembershipsByIdDesc(customer.getId());
    }

    public List<GroupMembership> getCustomerPendingGroupMemberships(Long customerId) {
        Customer customer = getCustomerById(customerId);
        return customerRepository.findPendingGroupMembershipsByIdDesc(customer.getId());
    }

    public void deleteCustomer(Long id) {
        if (!customerRepository.existsById(id)) {
            throw new EntityNotFoundException("Customer not found");
        }
        customerRepository.deleteById(id);
    }
    
}
