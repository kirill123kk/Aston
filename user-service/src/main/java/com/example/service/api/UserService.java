package com.example.service.api;



import com.example.dto.UserDto;

import java.util.List;

public interface UserService {
    List<UserDto> getAll();
    UserDto get(Long id);
    UserDto save(UserDto dto);
    UserDto update(Long id, String newName);
    UserDto delete(Long id);
}
