package com.example.UserService.controller;


import com.example.UserService.dto.UserDto;
import com.example.UserService.service.api.UserService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;


import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;



    @Test
    void getAllUsers_shouldReturnList() {
        List<UserDto> expected = List.of(
                new UserDto("Fedcka","adsf@ads",19),
                new UserDto("Vanka","adsf@ads",19)
        );
        when(userService.getAll()).thenReturn(expected);

        List<UserDto> result = userController.getAllUsers();

        assertEquals(expected, result);
        verify(userService, times(1)).getAll();
    }

    @Test
    void getUser_shouldReturnUser() {
        UserDto expected = new UserDto("Fedcka","adsf@ads",19);
        when(userService.get(1L)).thenReturn(expected);

        UserDto result = userController.getUser(1L);

        assertEquals(expected, result);
        verify(userService, times(1)).get(1L);
    }

    @Test
    void createUser_shouldReturnMessage() {
        UserDto newUser = new UserDto("Fedcka","adsf@ads",19);
        when(userService.save(newUser)).thenReturn("User created");

        String result = userController.createUser(newUser);

        assertEquals("User created", result);
        verify(userService, times(1)).save(newUser);
    }

    @Test
    void updateUser_shouldReturnUpdatedUser() {
        UserDto updated = new UserDto("Fedcka","adsf@ads",19);
        when(userService.update(1L, "UpdatedName")).thenReturn(updated);

        UserDto result = userController.updateUser(1L, "UpdatedName");

        assertEquals(updated, result);
        verify(userService, times(1)).update(1L, "UpdatedName");
    }

    @Test
    void deleteUser_shouldReturnMessage() {
        when(userService.delete(1L)).thenReturn("User deleted");

        String result = userController.deleteUser(1L);

        assertEquals("User deleted", result);
        verify(userService, times(1)).delete(1L);
    }
}
