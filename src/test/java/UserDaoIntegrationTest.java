import congfig.HibernateUtil;
import dao.UserDao;
import dto.CreateUserDto;
import entity.UserEntity;
import mapper.UserMapper;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;


import java.util.List;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;


@Testcontainers
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserDaoIntegrationTest {

    @Container
    private static final PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:15")
            .withDatabaseName("test-db")
            .withUsername("test")
            .withPassword("test");


    @BeforeAll
    void setupHibernate() {
        Properties props = new Properties();
        props.setProperty("hibernate.connection.driver_class", "org.postgresql.Driver");
        props.setProperty("hibernate.connection.url", postgres.getJdbcUrl());
        props.setProperty("hibernate.connection.username", postgres.getUsername());
        props.setProperty("hibernate.connection.password", postgres.getPassword());
        props.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        props.setProperty("hibernate.hbm2ddl.auto", "create-drop");
        props.setProperty("hibernate.show_sql", "true");

        Configuration cfg = new Configuration();
        cfg.setProperties(props);
        cfg.addAnnotatedClass(UserEntity.class);

        HibernateUtil.setSessionFactory(cfg.buildSessionFactory());
    }


    private final UserDao userDao = new UserDao();
    private final UserMapper mapper = new UserMapper();


    @BeforeEach
    void cleanDatabase() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.createQuery("DELETE FROM UserEntity").executeUpdate();
            session.getTransaction().commit();
        }
    }

    @Test
    void shouldSaveAndRetrieveUser() {
        CreateUserDto user = new CreateUserDto("Alice", "alice@e", 30);
        UserEntity userEntity = mapper.toEntity(user);
        userDao.save(userEntity);

        List<UserEntity> all = userDao.getAll();
        assertEquals(1, all.size());
        assertEquals("Alice", all.get(0).getName());
    }

    @Test
    void shouldUpdateUser() {
        CreateUserDto user = new CreateUserDto("Alice", "alice@e", 30);
        UserEntity userEntity = mapper.toEntity(user);
        userDao.save(userEntity);

        user.setName("Alice");
        userDao.update(userEntity);

        UserEntity updated = userDao.get(userEntity.getId());
        assertEquals("Alice", updated.getName());
    }

    @Test
    void shouldDeleteUser() {
        CreateUserDto user = new CreateUserDto("Alice", "alice@e", 30);
        UserEntity userEntity = mapper.toEntity(user);
        userDao.save(userEntity);

        userDao.delete(userEntity.getId());

        UserEntity deleted = userDao.get(userEntity.getId());
        assertNull(deleted);
    }
}
