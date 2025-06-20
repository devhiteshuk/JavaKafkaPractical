/*
package com.dhl.virtusa;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.test.context.EmbeddedKafka;

@SpringBootTest
@EmbeddedKafka(partitions = 1, brokerProperties = {
    "listeners=PLAINTEXT://localhost:9092", 
    "port=9092"
})
public class KafkaRetryTest {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Test
    void testRetries() throws InterruptedException {
        // Send a message to a topic that will fail
        kafkaTemplate.send("test-topic", "key", "value")
            .addCallback(
                result -> System.out.println("Success!"),
                ex -> System.out.println("Failed: " + ex.getMessage())
            );

        Thread.sleep(5000); // Wait for retries
    }
}*/
