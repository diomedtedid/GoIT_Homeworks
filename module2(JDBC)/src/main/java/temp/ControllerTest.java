package temp;

import controller.Controller;
import dao.*;
import dao.jdbcdaoimpl.*;
import domain.*;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by User on 04.08.2017.
 */
public class ControllerTest {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
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

//        System.out.println(read(controller, 1, Developer.class));
//        System.out.println(read(controller, 1, Developer.class));

//        Company company = new Company();
//        company.setName("Herovaya Corp");
//        company.setAddress("adresssssss");
//        create(controller, company);

//        create(controller, new Skill("test_skill1"));
//        Skill skill = new Skill("test_skill2");
//        skill.setId(12);
//        delete(controller, skill);

        System.out.println(readAll(controller, Skill.class));

    }



    private static Object read (Controller controller, long id, Class clazz) throws SQLException, ClassNotFoundException {
        long begine = System.currentTimeMillis();
        Object obj = controller.read(id, clazz);
        System.out.println("READ USED TIME :" + (System.currentTimeMillis() - begine));
        return obj;
    }

    private static void create (Controller controller, Object obj) throws SQLException, ClassNotFoundException {
        controller.create(obj);
    }

    private static void update (Controller controller, Object object) throws SQLException, ClassNotFoundException {
        controller.update(object);
    }

    private static void delete (Controller controller, Object object) throws SQLException, ClassNotFoundException {
        controller.delete(object);
    }

    private static List readAll (Controller controller, Class clazz) throws SQLException, ClassNotFoundException {
        return controller.readAll(clazz);
    }
}
