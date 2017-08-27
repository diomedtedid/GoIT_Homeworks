package domain;

import java.util.List;

/**
 * Created by User on 11.06.2017.
 */
public class Customer {
    private long id;
    private String name;
    private String lastName;
    private List<Project> projectList;

    public Customer() {
    }

    public Customer(long id, String name, String lastName, List<Project> projectList) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.projectList = projectList;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Project> getProjectList() {
        return projectList;
    }

    public void setProjectList(List<Project> projectList) {
        this.projectList = projectList;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", projectList=" + projectList +
                '}';
    }
}
