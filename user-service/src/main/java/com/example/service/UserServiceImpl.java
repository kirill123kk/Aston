package com.example.service;






import com.example.dto.UserDto;
import com.example.dto.UserEvent;
import com.example.entity.UserEntity;
import com.example.mapper.UserMapper;
import com.example.repository.UserRepository;
import com.example.service.api.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.logging.LogLevel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final KafkaProducerService kafkaProducerService;


    public UserDto save(UserDto dto) {
        userRepository.save(userMapper.toEntity(dto));
        Logger.getGlobal().log(Level.INFO, "kafka go new massage");
        kafkaProducerService.sendUserEvent(new UserEvent("CREATE", dto.getEmail()));
        Logger.getGlobal().log(Level.INFO, "kafka create new massage");
        return dto;

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

    public UserDto delete(Long id) {
        UserEntity userEntity = userRepository.findById(id).orElseThrow();
        userRepository.deleteById(id);
        kafkaProducerService.sendUserEvent(new UserEvent("DELETE", userEntity.getEmail()));

        return userMapper.toDto(userEntity);
    }
}
