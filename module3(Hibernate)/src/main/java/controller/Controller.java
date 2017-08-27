package controller;

import dao.*;

import java.util.List;

/**
 * Created by User on 18.08.2017.
 */
public class Controller {
    CompanyDao companyDao;
    CustomerDao customerDao;
    DeveloperDao developerDao;
    ProjectDao projectDao;
    SkillDao skillDao;

    public Controller() {
    }

    public Object read (long id, Class clazz) {
        GeneralDao dao = daoDispatcher(clazz);
        return dao.read(id);
    }

    public void create (Object object) {
        Class clazz = object.getClass();
        GeneralDao dao = daoDispatcher(clazz);
        dao.create(object);
    }

    public void update (Object object) {
        GeneralDao dao = daoDispatcher(object.getClass());
        dao.update(object);
    }

    public void delete (Object object) {
        GeneralDao dao = daoDispatcher(object.getClass());
        dao.delete(object);
    }

    public List readAll (Class clazz) {
        GeneralDao dao = daoDispatcher(clazz);
        List list = dao.readAll();
        return list;
    }

    private GeneralDao daoDispatcher (Class clazz) {
        String className = clazz.getSimpleName();
        switch (className) {
            case "Company":
                return this.companyDao;

            case "Developer":
                return this.developerDao;

            case "Skill":
                return this.skillDao;

            case "Project":
                return this.projectDao;

            case "Customer":
                return this.customerDao;

            default:
                throw new RuntimeException("Class " + className + " not found");
        }
    }

    public CompanyDao getCompanyDao() {
        return companyDao;
    }

    public void setCompanyDao(CompanyDao companyDao) {
        this.companyDao = companyDao;
    }

    public CustomerDao getCustomerDao() {
        return customerDao;
    }

    public void setCustomerDao(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    public DeveloperDao getDeveloperDao() {
        return developerDao;
    }

    public void setDeveloperDao(DeveloperDao developerDao) {
        this.developerDao = developerDao;
    }

    public ProjectDao getProjectDao() {
        return projectDao;
    }

    public void setProjectDao(ProjectDao projectDao) {
        this.projectDao = projectDao;
    }

    public SkillDao getSkillDao() {
        return skillDao;
    }

    public void setSkillDao(SkillDao skillDao) {
        this.skillDao = skillDao;
    }
}
