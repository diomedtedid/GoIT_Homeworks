package model.dao.inmemorydaoimpl;

import model.dao.TaskDao;
import model.domain.Task;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * Created by User on 08.09.2017.
 */
public class TaskDaoImplTest {
    TaskDao taskDao = null;

    @Before
    public void setUp() throws Exception {
        taskDao = new TaskMemoryDaoImpl();

        Task task1 = new Task(null, null, null);
        Task task2 = new Task(null, null, null);
        Task task3 = new Task(null, null, null);

        taskDao.create(task1);
        taskDao.create(task2);
        taskDao.create(task3);
    }

    @Test
    public void read() throws Exception {
        assertEquals(1, taskDao.read(1L).getId());
        assertEquals(2, taskDao.read(2L).getId());
        assertEquals(3, taskDao.read(3L).getId());
    }


    @Test
    public void update() throws Exception {
        assertNull(taskDao.read(1L).getTitle());
        Task task = taskDao.read(1L);
        task.setTitle("test");
        taskDao.update(task);
        assertEquals(task.getTitle(), taskDao.read(1L).getTitle());
    }

    @Test
    public void delete() throws Exception {
        Set<Task> taskSet = taskDao.readAll();
        assertEquals(taskSet.size(), 3);
        Task task = taskSet.stream().filter(task1 -> task1.getId()==1).findFirst().get();
        taskDao.delete(task);
        assertEquals(taskSet.size(), 2);
    }

    @Test
    public void readAll() throws Exception {
        Set<Task> taskSet = taskDao.readAll();
        assertEquals(3, taskSet.size());

    }

}