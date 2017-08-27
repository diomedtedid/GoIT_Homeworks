package dao;

import domain.Developer;

/**
 * Created by User on 11.06.2017.
 */
public interface DeveloperDAO extends GeneralDAO<Long, Developer> {
    public void setSkillDAO(SkillDAO skillDAO);
    public SkillDAO getSkillDAO();
}
