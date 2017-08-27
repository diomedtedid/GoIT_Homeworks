package dao;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by User on 12.06.2017.
 */
public interface GeneralDao<K extends Serializable, T> {
    void create(T entity);
    T read(K key);
    void update(T entity);
    void delete(T entity);
    List<T> readAll();
}
