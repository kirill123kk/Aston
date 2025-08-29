package com.example.UserService.controller;

import com.example.UserService.dto.UserDto;

import com.example.UserService.service.api.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user-service")
public class UserController {

    private final UserService userService;

    @GetMapping("/get")
    @Operation(description = "Получение всех пользователей")
    public List<UserDto> getAllUsers() {
        return userService.getAll();
    }

    @GetMapping("/get/{id}")
    @Operation(description = "Получение пользователя по ID")
    public UserDto getUser(@PathVariable Long id) {
        return userService.get(id);
    }

    @PostMapping("/create")
    @Operation(description = "Создание пользователя")
    public String createUser(@RequestBody UserDto user) {
        return userService.save(user);
    }

    @PutMapping("/update/{id}")
    @Operation(description = "Обновление пользователя")
    public UserDto updateUser(@PathVariable Long id, @RequestParam String newName) {
        return userService.update(id, newName);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(description = "Удаление пользователя")
    public String deleteUser(@PathVariable Long id) {
        return userService.delete(id);
    }

}
