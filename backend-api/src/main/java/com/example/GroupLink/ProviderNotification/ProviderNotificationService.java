package com.example.GroupLink.ProviderNotification;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.example.GroupLink.ProviderNotification.ProviderNotification;
import com.example.GroupLink.ProviderNotification.ProviderNotificationRepository;

public class ProviderNotificationService {
    private ProviderNotificationRepository providerNotificationRepository;

    public ProviderNotificationService(ProviderNotificationRepository providerNotificationRepository) {
        this.providerNotificationRepository = providerNotificationRepository;
    }

    public String formatTimestamp(LocalDateTime timestamp) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM-d");
        return timestamp.format(formatter);
    }

    public ProviderNotification createNotification(ProviderNotification notification) {
        LocalDateTime timestamp = LocalDateTime.now();
        notification.setTimestamp(formatTimestamp(timestamp));
        return providerNotificationRepository.save(notification);
    }
}
