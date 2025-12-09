package com.example.GroupLink.ProviderNotification;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProviderNotificationRepository extends JpaRepository<ProviderNotification, Long> {

    // return all notifications for a given provider
    List<ProviderNotification> findByProviderId(Long providerId);
}
