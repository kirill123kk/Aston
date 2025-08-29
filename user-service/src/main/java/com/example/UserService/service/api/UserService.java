package com.example.UserService.service.api;

import com.example.UserService.dto.UserDto;

import java.util.List;

public interface UserService {
    List<UserDto> getAll();
    UserDto get(Long id);
    String save(UserDto dto);
    UserDto update(Long id, String newName);
    String delete(Long id);
}
