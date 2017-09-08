package model.dao.inmemorydaoimpl;

import model.dao.UserDao;
import model.domain.User;

/**
 * Created by User on 08.09.2017.
 */
public class UserMemoryDaoImpl extends AbstractMemoryDaoImpl<Long, User> implements UserDao {
    private long id = 1;

    @Override
    public void create(User entity) {
        entity.setId(id++);
        super.create(entity);
    }

    @Override
    public User read(Long key) {
        return super.readAll().stream()
                .filter(user -> user.getId() == key)
                .findFirst()
                .get();
    }
}
