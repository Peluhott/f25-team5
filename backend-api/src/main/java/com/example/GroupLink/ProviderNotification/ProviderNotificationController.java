package com.example.GroupLink.ProviderNotification;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ProviderNotificationController {

    private final ProviderNotificationService providerNotificationService;

    public ProviderNotificationController(ProviderNotificationService providerNotificationService) {
        this.providerNotificationService = providerNotificationService;
    }

    @GetMapping("/provider/{providerId}/notifications")
    public String providerNotifications(@PathVariable Long providerId, Model model) {
        List<ProviderNotification> notifications = providerNotificationService.getNotificationsForProvider(providerId);
        model.addAttribute("notifications", notifications);
        model.addAttribute("providerId", providerId);
        return "provider/provider-notifications";
    }

    @PostMapping("/provider/{providerId}/notifications/{notificationId}/markRead")
    public String markNotificationRead(@PathVariable Long providerId,
            @PathVariable Long notificationId,
            RedirectAttributes redirectAttributes) {
        try {
            providerNotificationService.markAsRead(notificationId);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/provider/" + providerId + "/notifications";
    }

    @PostMapping("/provider/{providerId}/notifications/{notificationId}/markUnread")
    public String markNotificationUnread(@PathVariable Long providerId,
            @PathVariable Long notificationId,
            RedirectAttributes redirectAttributes) {
        try {
            providerNotificationService.markAsUnread(notificationId);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/provider/" + providerId + "/notifications";
    }

    @PostMapping("/provider/{providerId}/notifications/{notificationId}/delete")
    public String deleteNotification(@PathVariable Long providerId,
            @PathVariable Long notificationId,
            RedirectAttributes redirectAttributes) {
        try {
            providerNotificationService.deleteNotification(notificationId);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/provider/" + providerId + "/notifications";
    }
}
