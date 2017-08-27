package controller;

import dao.*;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by User on 04.08.2017.
 */
public class Controller {
    private SkillDAO skillDAO;
    private DeveloperDAO developerDAO;
    private CompanyDAO companyDAO;
    private ProjectDAO projectDAO;
    private CustomerDAO customerDAO;



    public Object read (long id, Class clazz) throws SQLException, ClassNotFoundException {
        GeneralDAO dao = daoDispatcher(clazz);
        return dao.read(id);
    }

    public void create (Object object) throws ClassNotFoundException, SQLException {
        Class clazz = object.getClass();
        GeneralDAO dao = daoDispatcher(clazz);
        dao.create(object);
    }

    public void update (Object object) throws ClassNotFoundException, SQLException {
        GeneralDAO dao = daoDispatcher(object.getClass());
        dao.update(object);
    }

    public void delete (Object object) throws ClassNotFoundException, SQLException {
        GeneralDAO dao = daoDispatcher(object.getClass());
        dao.delete(object);
    }

    public List readAll (Class clazz) throws ClassNotFoundException, SQLException {
        GeneralDAO dao = daoDispatcher(clazz);
        List list = dao.readAll();
        return list;
    }

    private GeneralDAO daoDispatcher (Class clazz) throws ClassNotFoundException {
        String className = clazz.getSimpleName();
        switch (className) {
            case "Company":
                return this.companyDAO;

            case "Developer":
                return this.developerDAO;

            case "Skill":
                return this.skillDAO;

            case "Project":
                return this.projectDAO;

            case "Customer":
                return this.customerDAO;

            default:
                throw new ClassNotFoundException();
        }
    }

    public SkillDAO getSkillDAO() {
        return skillDAO;
    }

    public void setSkillDAO(SkillDAO skillDAO) {
        this.skillDAO = skillDAO;
    }

    public DeveloperDAO getDeveloperDAO() {
        return developerDAO;
    }

    public void setDeveloperDAO(DeveloperDAO developerDAO) {
        this.developerDAO = developerDAO;
    }

    public CompanyDAO getCompanyDAO() {
        return companyDAO;
    }

    public void setCompanyDAO(CompanyDAO companyDAO) {
        this.companyDAO = companyDAO;
    }

    public ProjectDAO getProjectDAO() {
        return projectDAO;
    }

    public void setProjectDAO(ProjectDAO projectDAO) {
        this.projectDAO = projectDAO;
    }

    public CustomerDAO getCustomerDAO() {
        return customerDAO;
    }

    public void setCustomerDAO(CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }
}
