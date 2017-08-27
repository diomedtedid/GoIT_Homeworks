package entity;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

/**
 * Created by User on 11.06.2017.
 */

@Entity
@Table (name = "projects")
public class Project {

    @Id
    @Column (name = "proj_id")
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private  long id;

    @Column (name = "proj_name")
    private String projectName;

    @Column (name = "cost")
    private double cost;

    @ManyToMany (fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    //TODO: спросить за это!!!!!! org.hibernate.loader.MultipleBagFetchException: cannot simultaneously fetch multiple bags
    @Fetch(value = FetchMode.SUBSELECT)
    @JoinTable (name = "projects_has_developers",
            joinColumns = @JoinColumn (name = "projects_id", referencedColumnName = "proj_id"),
            inverseJoinColumns = @JoinColumn (name = "developers_id", referencedColumnName = "dev_id"))
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
