package com.example.UserService.service;

import com.example.UserService.dto.UserEvent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {
    private final KafkaTemplate<String, UserEvent> kafkaTemplate;

    @Value("${kafka.topic.user-events}")
    private String topic;

    public KafkaProducerService(KafkaTemplate<String, UserEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendUserEvent(UserEvent event) {
        kafkaTemplate.send(topic, event);
    }
}
