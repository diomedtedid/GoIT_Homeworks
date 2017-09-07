package model.domain;

import java.util.Date;

/**
 * Created by User on 07.09.2017.
 */
public class Task {
    private long id;
    private Date dateOfStart;
    private Date dateOfEnd;
    private String description;
    private Priority priority;
    private Status status;
    private User user;

    public Task(Date dateOfStart, Date dateOfEnd, User user) {
        this.dateOfStart = dateOfStart;
        this.dateOfEnd = dateOfEnd;
        this.description = "";
        this.priority = Priority.REGULAR;
        this.status = Status.OPEN;
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDateOfStart() {
        return dateOfStart;
    }

    public void setDateOfStart(Date dateOfStart) {
        this.dateOfStart = dateOfStart;
    }

    public Date getDateOfEnd() {
        return dateOfEnd;
    }

    public void setDateOfEnd(Date dateOfEnd) {
        this.dateOfEnd = dateOfEnd;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", dateOfStart=" + dateOfStart +
                ", dateOfEnd=" + dateOfEnd +
                ", description='" + description + '\'' +
                ", priority=" + priority +
                ", status=" + status +
                ", user=" + user +
                '}';
    }
}
