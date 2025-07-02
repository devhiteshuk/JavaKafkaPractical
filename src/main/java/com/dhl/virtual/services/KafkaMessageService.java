package com.dhl.virtual.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
public class KafkaMessageService {

    private final List<String> messages = new CopyOnWriteArrayList<>();

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @KafkaListener(topics = "test-topic")
    public void listen(String message) {
        messages.add(message);
    }

    public List<String> getMessages() {
        return messages;
    }

    @KafkaHandler
    public void sendMessage(String message) {
        kafkaTemplate.send("test-topic", message);
        System.out.println("Message sent: " + message);
    }
}