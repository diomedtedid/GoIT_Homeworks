package model.dao.inmemorydaoimpl;

import model.dao.TaskDao;
import model.domain.Task;

import java.util.Optional;

/**
 * Created by User on 08.09.2017.
 */
public class TaskMemoryDaoImpl extends AbstractMemoryDaoImpl<Long, Task> implements TaskDao {
    private long id = 1;

    @Override
    public void create(Task entity) {
        entity.setId(id++);
        super.create(entity);
    }

    @Override
    public Task read(Long key) {

        return super.readAll()
                .stream()
                .filter(task -> task.getId() == key)
                .findFirst().get();
    }
}
