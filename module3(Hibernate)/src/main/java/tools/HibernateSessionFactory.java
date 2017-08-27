package tools;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.File;

public class HibernateSessionFactory {
    private static SessionFactory sessionFactory = new Configuration()
            .configure("hibernate.cfg.xml") //new File("javaenterprise/module3/hibernate.cfg.xml")
            .buildSessionFactory();

    private HibernateSessionFactory() {
    }

    public static Session getSession(){
        return sessionFactory.openSession();
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

}
