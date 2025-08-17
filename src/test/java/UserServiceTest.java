import dao.UserDao;
import dto.CreateUserDto;
import entity.UserEntity;
import mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import service.UserService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserDao userDao;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserService userService;

    @Test
    void save_shouldConvertDtoAndCallDao() {
        CreateUserDto dto = new CreateUserDto("Alice", "alice@e", 30);
        UserEntity entity = new UserEntity();
        entity.setName("Alice");
        entity.setEmail("alice@e");
        entity.setAge(30);

        when(userMapper.toEntity(dto)).thenReturn(entity);

        userService.save(dto);

        verify(userDao).save(entity);
    }

    @Test
    void get_shouldReturnUserEntityById() {
        Long userId = 1L;
        UserEntity expectedUser = new UserEntity();
        expectedUser.setId(userId);
        expectedUser.setName("Alice");

        when(userDao.get(userId)).thenReturn(expectedUser);

        UserEntity actualUser = userService.get(userId);

        assertEquals(expectedUser, actualUser);
        verify(userDao).get(userId);
    }

    @Test
    void update_shouldChangeNameAndCallUpdate_whenUserExists() {
        Long userId = 1L;
        String newName = "UpdatedName";
        UserEntity existingUser = new UserEntity();
        existingUser.setId(userId);
        existingUser.setName("OldName");

        when(userDao.get(userId)).thenReturn(existingUser);

        userService.update(userId, newName);

        assertEquals(newName, existingUser.getName());
        verify(userDao).update(existingUser);
    }

    @Test
    void update_shouldDoNothing_whenUserDoesNotExist() {
        Long userId = 2L;
        String newName = "NoUser";

        when(userDao.get(userId)).thenReturn(null);

        userService.update(userId, newName);

        verify(userDao, never()).update(any());
    }
    @Test
    void delete_shouldCallDaoDeleteWithCorrectId() {
        Long userId = 1L;

        userService.delete(userId);

        verify(userDao).delete(userId);
    }

}
