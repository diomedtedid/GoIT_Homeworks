package entity;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

/**
 * Created by User on 11.06.2017.
 */
@Entity
@Table (name = "companies")
public class CompanyEntity {

    @Id
    @Column (name = "company_id")
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long id;

    @Column (name = "company_name")
    private String name;

    @Column (name = "company_address")
    private String address;

    @ManyToMany (fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @Fetch(value = FetchMode.SUBSELECT)
//    @OrderColumn (name = "developers_id")
    @JoinTable (name = "companies_has_developers",
    joinColumns = @JoinColumn (name = "companies_id"),
    inverseJoinColumns = @JoinColumn (name = "developers_id"))
    private List<DeveloperEntity> developerEntityList;

    @ManyToMany (fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
//    @Fetch(value = FetchMode.SUBSELECT) //TODO: узнать, почему здесь эта строчка не нужна
    @JoinTable (name = "companies_has_projects",
            joinColumns = @JoinColumn (name = "companies_id"),
            inverseJoinColumns = @JoinColumn (name = "projects_id"))
    private List<ProjectEntity> projectEntityList;

    public CompanyEntity() {
    }

    public CompanyEntity(long id, String name, String address, List<DeveloperEntity> developerEntityList, List<ProjectEntity> projectEntityList) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.developerEntityList = developerEntityList;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<DeveloperEntity> getDeveloperEntityList() {
        return developerEntityList;
    }

    public void setDeveloperEntityList(List<DeveloperEntity> developerEntityList) {
        this.developerEntityList = developerEntityList;
    }

    public List<ProjectEntity> getProjectEntityList() {
        return projectEntityList;
    }

    public void setProjectEntityList(List<ProjectEntity> projectEntityList) {
        this.projectEntityList = projectEntityList;
    }

    @Override
    public String toString() {
        return "CompanyEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", developerEntityList=" + developerEntityList +
                ", projectEntityList=" + projectEntityList +
                '}';
    }
}
