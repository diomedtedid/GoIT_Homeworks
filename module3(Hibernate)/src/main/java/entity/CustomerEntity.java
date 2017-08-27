package entity;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

/**
 * Created by User on 11.06.2017.
 */
@Entity
@Table (name = "customers")
public class CustomerEntity {

    @Id
    @Column (name = "cust_id")
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long id;

    @Column (name = "cust_name")
    private String name;

    @Column (name = "cust_lastname")
    private String lastName;

    @ManyToMany (fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @Fetch(value = FetchMode.SUBSELECT)
    @JoinTable (name = "customers_has_projects",
    joinColumns = @JoinColumn (name = "customers_id"),
    inverseJoinColumns = @JoinColumn (name = "projects_id"))
    //TODO: В чем разница в различных видах указания присоединенных таблиц

    private List<ProjectEntity> projectEntityList;

    public CustomerEntity() {
    }

    public CustomerEntity(long id, String name, String lastName, List<ProjectEntity> projectEntityList) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.projectEntityList = projectEntityList;
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

    public List<ProjectEntity> getProjectEntityList() {
        return projectEntityList;
    }

    public void setProjectEntityList(List<ProjectEntity> projectEntityList) {
        this.projectEntityList = projectEntityList;
    }

    @Override
    public String toString() {
        return "CustomerEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", projectEntityList=" + projectEntityList +
                '}';
    }
}
