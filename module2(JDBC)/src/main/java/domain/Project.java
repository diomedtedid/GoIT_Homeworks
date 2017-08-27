package domain;

import java.util.List;

/**
 * Created by User on 11.06.2017.
 */
public class Project {
    private  long id;
    private String projectName;
    private double cost;
    private List<Developer> developerList;

    public Project() {
    }

    public Project(long id, String projectName, double cost, List<Developer> developerList) {
        this.id = id;
        this.projectName = projectName;
        this.cost = cost;
        this.developerList = developerList;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public List<Developer> getDeveloperList() {
        return developerList;
    }

    public void setDeveloperList(List<Developer> developerList) {
        this.developerList = developerList;
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", projectName='" + projectName + '\'' +
                ", cost=" + cost +
                ", developerList=\r\n" + developerList +
                "}\r\n";
    }
}
