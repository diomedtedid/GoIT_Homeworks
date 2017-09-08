package model.dao.inmemorydaoimpl;

import model.dao.GeneralDao;
import model.domain.User;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 07.09.2017.
 */
public abstract class AbstractDaoImpl <K extends Serializable, T> implements GeneralDao <K, T> {
    private List<T> entityList = new ArrayList<>();

    @Override
    public void create(T entity) {

    }

    @Override
    public T read(K key) {
        return null;
    }

    @Override
    public void update(T entity) {

    }

    @Override
    public void delete(T entity) {

    }

    @Override
    public List<T> readAllByUser(User user) {
        return null;
    }
}
