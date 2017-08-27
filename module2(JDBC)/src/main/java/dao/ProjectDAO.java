package dao;

import domain.Project;

/**
 * Created by User on 11.06.2017.
 */
public interface ProjectDAO extends GeneralDAO<Long, Project> {
    void setDeveloperDAO(DeveloperDAO developerDAO);
    DeveloperDAO getDeveloperDAO();
}
