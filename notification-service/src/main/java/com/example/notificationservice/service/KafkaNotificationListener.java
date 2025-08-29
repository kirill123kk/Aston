package com.example.notificationservice.service;

import com.example.notificationservice.dto.UserEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaNotificationListener {

    private final EmailService emailService;

    @KafkaListener(topics = "user-events", groupId = "notification-group")
    public void listen(String messageJson) {
        ObjectMapper mapper = new ObjectMapper();
            UserEvent event = mapper.readValue(messageJson, UserEvent.class);
            String body = switch (event.getOperation()) {
                case "CREATE" -> "Здравствуйте! Ваш аккаунт на сайте был успешно создан.";
                case "DELETE" -> "Здравствуйте! Ваш аккаунт был удалён.";
                default -> "Неизвестная операция.";
            };
            emailService.sendEmail(event.getEmail(), "Уведомление", body);

    }
}
