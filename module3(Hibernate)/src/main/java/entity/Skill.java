package entity;


import javax.persistence.*;

/**
 * Created by User on 11.06.2017.
 */

@Entity
@Table(name = "skills")
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "skill_id")
    private long id;

    @Column (name = "skill_skill")
    private String skill;

    public Skill() {
    }

    public Skill(String skill) {
        this.skill = skill;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Skill skill1 = (Skill) o;

        return skill.equalsIgnoreCase(skill1.skill);
    }

    @Override
    public int hashCode() {
        return skill.hashCode();
    }

    @Override
    public String toString() {
        return "Skill{" +
                "id=" + id +
                ", skill='" + skill + '\'' +
                '}';
    }
}
