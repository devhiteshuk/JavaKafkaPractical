package com.dhl.virtual.utility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import java.util.Map;
import jakarta.annotation.PostConstruct;

@Component
public class KafkaConfigValidator {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @PostConstruct
    public void validateProducerConfig() {
        Map<String, Object> configs = kafkaTemplate.getProducerFactory().getConfigurationProperties();
        System.out.println("=== Kafka Producer Properties ===");
        System.out.println("retries: " + configs.get("retries"));
        System.out.println("acks: " + configs.get("acks"));
        System.out.println("retry.backoff.ms: " + configs.get("retry.backoff.ms"));
    }
}