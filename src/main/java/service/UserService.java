package service;


import dao.UserDao;
import dto.CreateUserDto;
import entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import mapper.UserMapper;

import java.util.List;

@AllArgsConstructor
public class UserService {

    private final UserDao userDao;
    private final UserMapper userMapper;


    public void save(CreateUserDto dto) {
        userDao.save(userMapper.toEntity(dto));
    }

    public UserEntity get(Long id) {
        return userDao.get(id);
    }

    public List<UserEntity> getAll() {
        return userDao.getAll();
    }

    public void update(Long id, String newName) {
        UserEntity user = userDao.get(id);
        if (user != null) {
            user.setName(newName);
            userDao.update(user);
        }
    }

    public void delete(Long id) {
        userDao.delete(id);
    }
}
