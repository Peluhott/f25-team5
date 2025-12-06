package com.example.GroupLink.CustomerNotification;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerNotificationRepository extends JpaRepository<CustomerNotification, Long> {
    
    
}
