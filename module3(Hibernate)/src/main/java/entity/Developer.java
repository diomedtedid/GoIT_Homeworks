package entity;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

/**
 * Created by User on 11.06.2017.
 */

@Entity
@Table (name = "developers")
public class Developer {

    @Id
    @Column (name = "dev_id")
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long id;

    @Column (name = "dev_name")
    private String name;

    @Column (name = "dev_lastname")
    private String lastName;

    @Column (name = "dev_email")
    private String email;

    @Column (name = "salary")
    private double salary;

    @ManyToMany (fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @Fetch(value = FetchMode.SUBSELECT)
    @JoinTable (name = "developers_has_skills",
            joinColumns = @JoinColumn (name = "developers_id", referencedColumnName = "dev_id"),
    inverseJoinColumns = @JoinColumn (name = "skills_id", referencedColumnName = "skill_id"))
    private List<SkillEntity> skillEntityList;

    public Developer() {
    }

    public Developer(long id, String name, String lastName, String email, double salary, List<SkillEntity> skillEntityList) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.salary = salary;
        this.skillEntityList = skillEntityList;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public List<SkillEntity> getSkillEntityList() {
        return skillEntityList;
    }

    public void setSkillEntityList(List<SkillEntity> skillEntityList) {
        this.skillEntityList = skillEntityList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Developer developer = (Developer) o;

        if (!name.equals(developer.name)) return false;
        if (!lastName.equals(developer.lastName)) return false;
        return email.equals(developer.email);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + lastName.hashCode();
        result = 31 * result + email.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Developer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", salary=" + salary +
                ", skillEntityList=" + skillEntityList +
                "}\r\n";
    }
}
