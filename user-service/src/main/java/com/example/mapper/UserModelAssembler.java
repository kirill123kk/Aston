package com.example.mapper;

import com.example.controller.UserController;
import com.example.dto.UserDto;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class UserModelAssembler implements RepresentationModelAssembler<UserDto, EntityModel<UserDto>> {

    public EntityModel<UserDto> toModel(UserDto user, Long id) {
        return EntityModel.of(user,
                linkTo(methodOn(UserController.class).getUser(id)).withSelfRel(),
                linkTo(methodOn(UserController.class).updateUser(id, user.getName())).withRel("update"),
                linkTo(methodOn(UserController.class).deleteUser(id)).withRel("delete"),
                linkTo(methodOn(UserController.class).getAllUsers()).withRel("all-users")
        );
    }

    @Override
    public EntityModel<UserDto> toModel(UserDto entity) {
        return null;
    }
}
