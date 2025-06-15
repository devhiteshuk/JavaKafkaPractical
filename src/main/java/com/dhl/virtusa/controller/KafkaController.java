package com.dhl.virtusa.controller;

import com.dhl.virtusa.services.KafkaMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class KafkaController {

    @Autowired
    KafkaMessageService kafkaMessageService;

    @GetMapping("/kafka")
    public List<String> kafkaPage() {
        return kafkaMessageService.getMessages();
    }

    @PostMapping("/kafka/send")
    public String sendMessage(@RequestParam String message) {
        kafkaMessageService.sendMessage(message);
        return "Message sent: " + message;
    }
}