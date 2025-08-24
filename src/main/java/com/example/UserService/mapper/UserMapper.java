package com.example.UserService.mapper;

import com.example.UserService.dto.UserDto;
import com.example.UserService.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    UserEntity toEntity(UserDto dto);

    UserDto toDto(UserEntity entity);

    List<UserDto> toDtoList(List<UserEntity> entities);
}
