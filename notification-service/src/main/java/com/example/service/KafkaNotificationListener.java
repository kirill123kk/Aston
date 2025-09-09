package com.example.service;

import com.example.dto.UserEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.logging.Level;
import java.util.logging.Logger;


@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaNotificationListener {
    private final EmailService emailService;

    @KafkaListener(topics = "user-events", groupId = "notification-group")
    public void listen(String messageJson) {
        ObjectMapper mapper = new ObjectMapper();
        Logger.getGlobal().log(Level.INFO,  messageJson);
        try {
            UserEvent event = mapper.readValue(messageJson, UserEvent.class);
            String body = switch (event.getOperation()) {
                case "CREATE" -> "Здравствуйте! Ваш аккаунт на сайте был успешно создан.";
                case "DELETE" -> "Здравствуйте! Ваш аккаунт был удалён.";
                default -> "Неизвестная операция.";
            };
            Logger.getGlobal().log(Level.INFO, body);
            emailService.sendEmail(event.getEmail(), "Уведомление", body);
        } catch (JsonProcessingException ignored) {
            Logger.getGlobal().log(Level.INFO, "EXCEPTION");
        }
    }
}
