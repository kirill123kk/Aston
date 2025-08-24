package com.example.UserService.service;


import com.example.UserService.dto.UserDto;
import com.example.UserService.entity.UserEntity;
import com.example.UserService.repository.UserRepository;
import com.example.UserService.service.api.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import com.example.UserService.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;


    public String save(UserDto dto) {
        userRepository.save(userMapper.toEntity(dto));
        return "User created " + dto.toString();
    }

    public UserDto get(Long id) {
        return userMapper.toDto(userRepository.findById(id).orElseThrow());
    }

    public List<UserDto> getAll() {
        return userMapper.toDtoList(userRepository.findAll());
    }

    public UserDto update(Long id, String newName) {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User %s not found".formatted(id)));
        user.setName(newName);
        userRepository.save(user);
        return userMapper.toDto(user);
    }

    public String delete(Long id) {
        userRepository.deleteById(id);
        return "User deleted";
    }
}
