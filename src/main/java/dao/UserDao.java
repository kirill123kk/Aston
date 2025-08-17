package dao;

import congfig.HibernateUtil;
import entity.UserEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class UserDao {
    private static final Logger logger = LoggerFactory.getLogger(UserDao.class);

    public void save(UserEntity user) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.persist(user);
            tx.commit();
        } catch (Exception e) {
            logger.error("the user is not saved error: ", e.getMessage());
        }
    }

    public  UserEntity get(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(UserEntity.class, id);
        } catch (Exception e) {
            logger.error("the user is not given error: ", e.getMessage());
        }
        return null;
    }

    public List<UserEntity> getAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from UserEntity", UserEntity.class).list();
        } catch (Exception e) {
            logger.error("the users is not given error: ", e.getMessage());
        }
        return null;
    }

    public void update(UserEntity user) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.merge(user);
            tx.commit();
        } catch (Exception e) {
            logger.error("he user has not been updated error: ", e.getMessage());
        }
    }

    public void delete(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            UserEntity user = session.get(UserEntity.class, id);
            if (user != null) session.remove(user);
            tx.commit();
        } catch (Exception e) {
            logger.error("he user has not been deleted error: ", e.getMessage());
        }
    }
}
