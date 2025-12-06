package com.example.GroupLink.CustomerNotification;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Service;

@Service
public class CustomerNotificationService {

    private CustomerNotificationRepository customerNotificationRepository;
    
    public CustomerNotificationService(CustomerNotificationRepository customerNotificationRepository) {
        this.customerNotificationRepository = customerNotificationRepository;
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
    
}
