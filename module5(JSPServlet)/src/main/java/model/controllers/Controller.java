package model.controllers;

import model.dao.TaskDao;
import model.dao.UserDao;
import model.domain.Task;
import model.domain.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by User on 08.09.2017.
 */
public class Controller {
    private UserDao userDao;
    private TaskDao taskDao;
    private static Controller controller;

    private Controller() {
    }

    public static Controller getController() {

        if (controller == null) {
            controller = new Controller();
        }

        return controller;
    }

    public List<Task> getTaskListByUser (User user) {
        List<Task> taskList = new ArrayList<>();

        if (userDao.readAll().contains(user)) {
            taskList.addAll(taskDao.readAll()
                    .stream()
                    .filter(task -> task.getUser().equals(user))
                    .collect(Collectors.toList())
            );
        } else {
            userDao.create(user);
        }

        return taskList;
    }

    public void saveUser (User user) {
        userDao.create(user);
    }

    public User getUser (long id) {
        return userDao.read(id);
    }

    public User getUserByName (String name) {
        User user = null;

        try {
            user = userDao.readAll()
            .stream()
            .filter(uzer -> uzer.getUserName().equalsIgnoreCase(name))
            .findFirst()
            .get();
        } catch (Exception e) {
        }

        return user;
    }

    public Task getTask (long id) {
        return  taskDao.read(id);
    }

    public void saveTask (Task task) {
        if (task.getUser() == null || task.getDateOfStart() == null || task.getDateOfEnd() == null) {
            throw  new IllegalArgumentException("New task contains null fields");
        }

        this.taskDao.create(task);
    }

    public void updateTask(Task task) {
        taskDao.update(task);
    }

    public void deleteTask(Task task) {
        taskDao.delete(task);
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public TaskDao getTaskDao() {
        return taskDao;
    }

    public void setTaskDao(TaskDao taskDao) {
        this.taskDao = taskDao;
    }
}
