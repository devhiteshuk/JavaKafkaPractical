package com.dhl.virtual.error_handle;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.ByteArraySerializer;
import org.apache.kafka.common.serialization.Deserializer;

import java.time.Instant;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public class ErrorHandlingDeserializer<T> implements Deserializer<T> {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final Deserializer<T> innerDeserializer;
    private KafkaProducer<byte[], byte[]> deadLetterProducer;
    private String deadLetterTopic;

    public ErrorHandlingDeserializer(Deserializer<T> innerDeserializer) {
        this.innerDeserializer = innerDeserializer;
    }

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        innerDeserializer.configure(configs, isKey);
        
        // Configure dead letter producer
        deadLetterTopic = (String) configs.get("dead.letter.topic");
        if (deadLetterTopic != null) {
            deadLetterProducer = new KafkaProducer<>(
                new HashMap<>(configs),
                new ByteArraySerializer(),
                new ByteArraySerializer()
            );
        }
    }

    @Override
    public T deserialize(String topic, byte[] data) {
        try {
            return innerDeserializer.deserialize(topic, data);
        } catch (Exception e) {
            if (deadLetterProducer != null) {
                sendToDeadLetter(topic, data, e);
            }
            throw new SerializationException("Failed to deserialize", e);
        }
    }

    private void sendToDeadLetter(String originalTopic, byte[] data, Exception error) {
        try {
            // Create error metadata
            Map<String, Object> errorInfo = new HashMap<>();
            errorInfo.put("originalTopic", originalTopic);
            errorInfo.put("timestamp", Instant.now().toString());
            errorInfo.put("error", error.getMessage());
            errorInfo.put("stackTrace", Arrays.toString(error.getStackTrace()));
            errorInfo.put("rawData", data != null ? Base64.getEncoder().encodeToString(data) : null);

            // Send to dead letter topic
            deadLetterProducer.send(new ProducerRecord<>(
                deadLetterTopic,
                objectMapper.writeValueAsBytes(errorInfo)
            ));
        } catch (Exception dlqError) {
            dlqError.printStackTrace();
        }
    }

    @Override
    public void close() {
        innerDeserializer.close();
        if (deadLetterProducer != null) {
            deadLetterProducer.close();
        }
    }
}