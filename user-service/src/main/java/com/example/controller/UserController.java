package com.example.controller;



import com.example.dto.UserDto;
import com.example.mapper.UserModelAssembler;
import com.example.service.api.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user-service")
public class UserController {

    private final UserService userService;
    private final UserModelAssembler assembler;

    @GetMapping("/get")
    @Operation(description = "Получение всех пользователей")
    public CollectionModel<EntityModel<UserDto>> getAllUsers() {
        List<EntityModel<UserDto>> users = userService.getAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(users,
                linkTo(methodOn(UserController.class).getAllUsers()).withSelfRel());
    }

    @GetMapping("/get/{id}")
    @Operation(description = "Получение пользователя по ID")
    public EntityModel<UserDto> getUser(@PathVariable Long id) {
        return assembler.toModel(userService.get(id), id);
    }

    @PostMapping("/create")
    @Operation(description = "Создание пользователя")
    public EntityModel<UserDto> createUser(@RequestBody UserDto user) {
        UserDto created = userService.save(user);
        return assembler.toModel(created, null);
    }

    @PutMapping("/update/{id}")
    @Operation(description = "Обновление пользователя")
    public EntityModel<UserDto> updateUser(@PathVariable Long id, @RequestParam String newName) {
        return assembler.toModel(userService.update(id, newName), id);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(description = "Удаление пользователя")
    public EntityModel<UserDto> deleteUser(@PathVariable Long id) {
        return assembler.toModel(userService.delete(id), id);
    }

}
