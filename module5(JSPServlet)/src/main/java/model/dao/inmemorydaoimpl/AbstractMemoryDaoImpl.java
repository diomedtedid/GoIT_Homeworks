package model.dao.inmemorydaoimpl;

import model.dao.GeneralDao;
import model.domain.User;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by User on 07.09.2017.
 */
public abstract class AbstractMemoryDaoImpl<K extends Serializable, T> implements GeneralDao <K, T> {
    private Set<T> entitySet = new HashSet<>();

    @Override
    public void create(T entity) {
        entitySet.add(entity);
    }

    @Override
    public abstract T read(K key);

    @Override
    public void update(T entity) {
        entitySet.remove(entity);
        entitySet.add(entity);
    }

    @Override
    public void delete(T entity) {
        entitySet.remove(entity);
    }

    @Override
    public Set<T> readAll() {
        return entitySet;
    }
}
