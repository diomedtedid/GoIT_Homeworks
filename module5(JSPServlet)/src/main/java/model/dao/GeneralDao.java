package model.dao;

import model.domain.User;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * Created by User on 12.06.2017.
 */
public interface GeneralDao<K extends Serializable, T> {
    void create(T entity);
    T read(K key);
    void update(T entity);
    void delete(T entity);
    Set<T> readAll();
}
