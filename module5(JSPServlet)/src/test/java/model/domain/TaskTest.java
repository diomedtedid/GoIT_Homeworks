package model.domain;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by User on 08.09.2017.
 */
public class TaskTest {
    @Test
    public void creationTest() {
        Task task = new Task(null, null, null);
        assertEquals(Priority.REGULAR, task.getPriority());
        assertEquals(Status.OPEN, task.getStatus());
    }

}