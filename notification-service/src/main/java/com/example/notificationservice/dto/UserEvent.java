package com.example.notificationservice.dto;

import lombok.Data;

@Data
public class UserEvent {
    private String operation;
    private String email;
}
