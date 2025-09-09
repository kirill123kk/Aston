package com.example.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

@Data
@AllArgsConstructor
@Schema(description = "DTO для работы с Notification-service")
public class UserEvent extends RepresentationModel<UserEvent> {
    private String operation;
    private String email;
}
