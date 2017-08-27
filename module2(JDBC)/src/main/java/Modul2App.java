

import controller.Controller;
import dao.*;
import dao.jdbcdaoimpl.*;
import domain.Developer;
import domain.Skill;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 06.08.2017.
 */
public class Modul2App {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Controller controller = getController();

        Developer developer = new Developer();
        developer.setName("testDev");
        developer.setLastName("testDevLastName");
        developer.setEmail("testDev@mail.com");
        developer.setSalary(100500);
//        developer.setId(11);

        List skillList = new ArrayList();
        Skill skill = new Skill("C++");
        skill.setId(3);
        skillList.add(skill);

        developer.setSkillList(skillList);
//        controller.create(developer);
        System.out.println(controller.readAll(Skill.class));
//        controller.delete(developer);

    }

    private static Controller getController() {

        Controller controller = new Controller();
        CompanyDAO companyDAO = new CompanyDAOImpl();
        controller.setCompanyDAO(companyDAO);

        CustomerDAO customerDAO = new CustomerDAOImpl();
        controller.setCustomerDAO(customerDAO);

        SkillDAO skillDAO = new SkillDAOImpl();
        controller.setSkillDAO(skillDAO);

        DeveloperDAO developerDAO = new DeveloperDAOImpl();
        controller.setDeveloperDAO(developerDAO);

        ProjectDAO projectDAO = new ProjectDAOImpl();
        controller.setProjectDAO(projectDAO);

        return controller;
    }
}
