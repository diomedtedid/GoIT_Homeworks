package dao;

import domain.Company;

/**
 * Created by User on 11.06.2017.
 */
public interface CompanyDAO extends GeneralDAO<Long, Company> {
    void setDeveloperDAO(DeveloperDAO developerDAO);
    DeveloperDAO getDeveloperDAO();
    void setProjectDAO(ProjectDAO projectDAO);
    ProjectDAO getProjectDAO();
}
