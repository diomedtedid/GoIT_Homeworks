package entity;


import javax.persistence.*;

/**
 * Created by User on 11.06.2017.
 */

@Entity
@Table(name = "skills")
public class SkillEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "skill_id")
    private long id;

    @Column (name = "skill_skill")
    private String skill;

    public SkillEntity() {
    }

    public SkillEntity(String skill) {
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

        SkillEntity skillEntity1 = (SkillEntity) o;

        return skill.equalsIgnoreCase(skillEntity1.skill);
    }

    @Override
    public int hashCode() {
        return skill.hashCode();
    }

    @Override
    public String toString() {
        return "SkillEntity{" +
                "id=" + id +
                ", skill='" + skill + '\'' +
                '}';
    }
}
