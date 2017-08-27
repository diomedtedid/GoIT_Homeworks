package dao;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by User on 12.06.2017.
 */
public interface GeneralDAO<K extends Serializable, T> {
    void create(T entity) throws SQLException;
    T read(K key) throws SQLException;
    void update(T entity)throws SQLException;
    void delete(T entity)throws SQLException;
    List<T> readAll() throws SQLException;
}
