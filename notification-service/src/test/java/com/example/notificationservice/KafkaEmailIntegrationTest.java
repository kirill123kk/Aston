package com.example.notificationservice;

import com.example.notificationservice.dto.UserEvent;
import com.example.notificationservice.service.EmailService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@RequiredArgsConstructor
public class KafkaEmailIntegrationTest {

    private final KafkaTemplate<String, String> kafkaTemplate;


    private final EmailService emailService;

    @Test
    public void testKafkaTriggersEmail() throws Exception {
        UserEvent event = new UserEvent();
        event.setOperation("CREATE");
        event.setEmail("test@example.com");

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(event);

        kafkaTemplate.send("user-events", json);


    }
}
