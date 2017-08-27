package dao;

import tools.HibernateSessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.TransactionException;
import org.hibernate.criterion.Order;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;

public abstract class AbstractHibernateDao <K extends Serializable, T> implements GeneralDao <K, T> {
    private Class<T> entityType;

    @Override
    public void create(T entity){
        Transaction transaction = null;
        try (Session session = HibernateSessionFactory.getSession()){
            transaction = session.beginTransaction();
            session.save(entity);
            transaction.commit();
        } catch (RuntimeException e) {
            e.printStackTrace();
            if (transaction != null) {
                try {
                    transaction.rollback();  //throw TransactionException (extends HibernateException extends PersistenceException  extends RuntimeException) and IllegalStateException (extends RuntimeException)
                } catch (RuntimeException e1) {
                    e1.printStackTrace();
                    throw new TransactionException("Huston we have a problem");
                }
            }
            throw new TransactionException("Huston we have a problem");
        }


    }

    @Override
    public T read(K key) {

        try (Session session = HibernateSessionFactory.getSession()){

            return session.get(getEntityType(),  key);
        }

    }

    @Override
    public void update(T entity) {
        Transaction transaction = null;
        try (Session session = HibernateSessionFactory.getSession()){
            transaction = session.beginTransaction();
            session.update(entity);
            transaction.commit();
        } catch (RuntimeException e) {
            e.printStackTrace();
            if (transaction != null) {
                try {
                    transaction.rollback();  //throw TransactionException (extends HibernateException extends PersistenceException  extends RuntimeException) and IllegalStateException (extends RuntimeException)
                } catch (RuntimeException e1) {
                    e1.printStackTrace();
                    throw new TransactionException("Huston we have a problem");
                }
            }
            throw new TransactionException("Huston we have a problem");
        }
    }

    @Override
    public void delete(T entity) {
        Transaction transaction = null;
        try (Session session = HibernateSessionFactory.getSession()){
            transaction = session.beginTransaction();
            session.delete(entity);
            transaction.commit();
        } catch (RuntimeException e) {
            e.printStackTrace();
            if (transaction != null) {
                try {
                    transaction.rollback();  //throw TransactionException (extends HibernateException extends PersistenceException  extends RuntimeException) and IllegalStateException (extends RuntimeException)
                } catch (RuntimeException e1) {
                    e1.printStackTrace();
                    throw new TransactionException("Huston we have a problem");
                }
            }
            throw new TransactionException("Huston we have a problem");
        }
    }

    @Override
    public List<T> readAll() {

        try (Session session = HibernateSessionFactory.getSession()){

            return session.createCriteria(getEntityType()).list();
        }

    }

    private Class<T> getEntityType() {
        //TODO:Разобраться, как эта херня работает!!!!!!
        if (this.entityType == null) {
            this.entityType = ((Class) ((ParameterizedType) this.getClass()
                    .getGenericSuperclass()).getActualTypeArguments()[1]);
        }
        return this.entityType;
    }
}
