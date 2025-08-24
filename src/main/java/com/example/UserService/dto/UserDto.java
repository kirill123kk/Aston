package com.example.UserService.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "DTO для работы с пользователем")
public class UserDto {

    @Schema(description = "Имя пользователя", example = "Игорь")
    private String name;

    @Schema(description = "Майл пользователя", example = "1984@ader.ru")
    private String email;

    @Min(1) @Max(120)
    @Schema(description = "Возраст пользователя", example = "19")
    private int age;
}
