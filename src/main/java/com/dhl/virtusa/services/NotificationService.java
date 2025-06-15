package com.dhl.virtusa.services;

import com.dhl.virtusa.model.NotificationMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class NotificationService {

    @Autowired
    private KafkaTemplate<String, NotificationMessage> kafkaTemplate;

    private final List<NotificationMessage> notifications = new ArrayList<NotificationMessage>();
    public void sendNotification(NotificationMessage notification) {
        kafkaTemplate.send("notifications", notification);
    }

    @KafkaListener(topics = "notifications", groupId = "virtusa-group")
    public void listen(NotificationMessage notification) {
        notifications.add(notification);
        System.out.println("Received notification: " + notification.toString());
    }

    public List<NotificationMessage> getNotifications() {
        // This method should return a list of notifications.
        return Collections.unmodifiableList(notifications);
    }
}