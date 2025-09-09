package com.example.mapper;



import com.example.dto.UserDto;
import com.example.entity.UserEntity;
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
