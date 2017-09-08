package model.dao.inmemorydaoimpl;

import model.dao.UserDao;
import model.domain.User;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by User on 08.09.2017.
 */
public class UserMemoryDaoImplTest {
    private UserDao userDao = null;

    @Before
    public void setUp() throws Exception {
        userDao = new UserMemoryDaoImpl();
        User user1 = new User("user1");
        User user2 = new User("user2");
        User user3 = new User("user3");

        userDao.create(user1);
        userDao.create(user2);
        userDao.create(user3);

    }

    @Test
    public void create() throws Exception {
        assertEquals(3, userDao.readAll().size());
        userDao.create(new User("user4"));
        assertEquals(4, userDao.readAll().size());
    }

    @Test
    public void read() throws Exception {
        assertEquals("user1", userDao.read(1L).getUserName());
        assertEquals("user2", userDao.read(2L).getUserName());
        assertEquals("user3", userDao.read(3L).getUserName());
    }


    @Test
    public void update() throws Exception {
        assertEquals("user1", userDao.read(1L).getUserName());
        User user = userDao.read(1L);
        user.setUserName("updateUser");
        userDao.update(user);
        assertEquals("updateUser", userDao.read(1L).getUserName());
    }

    @Test
    public void delete() throws Exception {
        assertEquals(3, userDao.readAll().size());
        User user = new User("user1");
        userDao.delete(user);
        assertEquals(2, userDao.readAll().size());
    }


}