package domain;

import java.util.List;

/**
 * Created by User on 11.06.2017.
 */
public class Developer {
    private long id;
    private String name;
    private String lastName;
    private String email;
    private double salary;
    private List<Skill> skillList;

    public Developer() {
    }

    public Developer(long id, String name, String lastName, String email, double salary, List<Skill> skillList) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.salary = salary;
        this.skillList = skillList;
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

    public List<Skill> getSkillList() {
        return skillList;
    }

    public void setSkillList(List<Skill> skillList) {
        this.skillList = skillList;
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
                ", skillList=" + skillList +
                "}\r\n";
    }
}
