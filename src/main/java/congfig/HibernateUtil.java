package congfig;

import entity.UserEntity;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Properties;

public class HibernateUtil {
    private static SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            Properties props = new Properties();
            props.load(HibernateUtil.class.getClassLoader().getResourceAsStream("hibernate.properties"));
            Configuration configuration = new Configuration();
            configuration.setProperties(props);
            configuration.addAnnotatedClass(UserEntity.class);
            return configuration.buildSessionFactory();
        } catch (Exception ex) {
            throw new ExceptionInInitializerError("SessionFactory creation failed: " + ex);
        }
    }
    public static void setSessionFactory(SessionFactory factory) {
        sessionFactory = factory;
    }
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}