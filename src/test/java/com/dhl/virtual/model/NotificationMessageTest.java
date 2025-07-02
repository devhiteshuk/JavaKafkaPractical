package com.dhl.virtual.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NotificationMessageTest {

    @Test
    void testGettersAndSetters() {
        NotificationMessage msg = new NotificationMessage();
        msg.setUserId("user123");
        msg.setType("INFO");
        msg.setMessage("Test message");
        msg.setTimestamp("2024-06-10T12:00:00Z");

        assertEquals("user123", msg.getUserId());
        assertEquals("INFO", msg.getType());
        assertEquals("Test message", msg.getMessage());
        assertEquals("2024-06-10T12:00:00Z", msg.getTimestamp());
    }

    @Test
    void testToString() {
        NotificationMessage msg = new NotificationMessage();
        msg.setUserId("user123");
        msg.setType("INFO");
        msg.setMessage("Test message");
        msg.setTimestamp("2024-06-10T12:00:00Z");

        String str = msg.toString();
        assertTrue(str.contains("userId='user123'"));
        assertTrue(str.contains("type='INFO'"));
        assertTrue(str.contains("message='Test message'"));
        assertTrue(str.contains("timestamp='2024-06-10T12:00:00Z'"));
    }
}