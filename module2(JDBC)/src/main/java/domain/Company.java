package domain;

import java.util.List;

/**
 * Created by User on 11.06.2017.
 */
public class Company {
    private long id;
    private String name;
    private String address;
    private List<Developer> developerList;
    private List<Project> projectList;

    public Company() {
    }

    public Company(long id, String name, String address, List<Developer> developerList, List<Project> projectList) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.developerList = developerList;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Developer> getDeveloperList() {
        return developerList;
    }

    public void setDeveloperList(List<Developer> developerList) {
        this.developerList = developerList;
    }

    public List<Project> getProjectList() {
        return projectList;
    }

    public void setProjectList(List<Project> projectList) {
        this.projectList = projectList;
    }

    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", developerList=" + developerList +
                ", projectList=" + projectList +
                '}';
    }
}
