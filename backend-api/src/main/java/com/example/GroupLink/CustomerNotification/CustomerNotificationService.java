package com.example.GroupLink.CustomerNotification;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Service;

import com.example.GroupLink.Customer.Customer;
import com.example.GroupLink.Customer.CustomerService;

@Service
public class CustomerNotificationService {

    private CustomerNotificationRepository customerNotificationRepository;
    private CustomerService customerService;

    public CustomerNotificationService(CustomerNotificationRepository customerNotificationRepository,
            CustomerService customerService) {
        this.customerNotificationRepository = customerNotificationRepository;
        this.customerService = customerService;
    }

    public String formatTimestamp(LocalDateTime timestamp) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM-d");
        return timestamp.format(formatter);
    }

    public CustomerNotification createNotification(CustomerNotification notification) {
        LocalDateTime timestamp = LocalDateTime.now();
        notification.setTimestamp(formatTimestamp(timestamp));
        return customerNotificationRepository.save(notification);
    }

    public void createCustomerNotification(Long customerId, String type, String message) {
        Customer customer = customerService.getCustomerById(customerId);
        LocalDateTime timestamp = LocalDateTime.now();
        String time = formatTimestamp(timestamp);
        CustomerNotification notification = new CustomerNotification(customer, type, message, time);
        customerNotificationRepository.save(notification);
    }

}
