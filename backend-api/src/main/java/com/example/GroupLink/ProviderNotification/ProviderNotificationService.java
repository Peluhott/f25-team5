package com.example.GroupLink.ProviderNotification;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.GroupLink.ProviderNotification.ProviderNotification;
import com.example.GroupLink.ProviderNotification.ProviderNotificationRepository;

@Service
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

    // new: fetch notifications for a provider
    public List<ProviderNotification> getNotificationsForProvider(Long providerId) {
        if (providerId == null)
            return List.of();
        return providerNotificationRepository.findByProviderId(providerId);
    }

    public void markAsRead(Long notificationId) {
        Optional<ProviderNotification> opt = providerNotificationRepository.findById(notificationId);
        if (opt.isPresent()) {
            ProviderNotification n = opt.get();
            n.setRead(true);
            providerNotificationRepository.save(n);
        } else {
            throw new IllegalArgumentException("Notification not found: " + notificationId);
        }
    }

    public void deleteNotification(Long notificationId) {
        if (!providerNotificationRepository.existsById(notificationId)) {
            throw new IllegalArgumentException("Notification not found: " + notificationId);
        }
        providerNotificationRepository.deleteById(notificationId);
    }

    // mark a notification as unread
    public void markAsUnread(Long notificationId) {
        Optional<ProviderNotification> opt = providerNotificationRepository.findById(notificationId);
        if (opt.isPresent()) {
            ProviderNotification n = opt.get();
            n.setRead(false);
            providerNotificationRepository.save(n);
        } else {
            throw new IllegalArgumentException("Notification not found: " + notificationId);
        }
    }
}
