package com.example.GroupLink.CustomerNotification;

import org.springframework.stereotype.Controller;

@Controller
public class CustomerNotificationController {

    private CustomerNotificationService customerNotificationService;

    public CustomerNotificationController(CustomerNotificationService customerNotificationService) {
        this.customerNotificationService = customerNotificationService;
    }
    
}
