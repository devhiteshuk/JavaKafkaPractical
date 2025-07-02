package com.dhl.virtual.controller;

import com.dhl.virtual.model.NotificationMessage;
import com.dhl.virtual.services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RestController
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @PostMapping("/notify")
    public String notifyUser(@RequestParam String userId) {
        NotificationMessage notification = new NotificationMessage();
        notification.setUserId(userId);
        notification.setType("REGISTRATION");
        notification.setMessage("User registered successfully");
        notification.setTimestamp(Instant.now().toString());
        notificationService.sendNotification(notification);
        return "Notification sent for user: " + userId;
    }

    @GetMapping("/notifications")
    public List<NotificationMessage> getNotifications() {
        return notificationService.getNotifications();
    }
}