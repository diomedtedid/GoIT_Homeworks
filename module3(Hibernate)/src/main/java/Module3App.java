import controller.Controller;
import dao.*;
import dao.hibernatedaoimpl.*;
import entity.SkillEntity;
import tools.HibernateSessionFactory;

/**
 * Created by User on 18.08.2017.
 */
public class Module3App {
    public static void main(String[] args) {
        SkillDao skillDao = new SkillHibernateDaoImpl();
        ProjectDao projectDao = new ProjectHibernateDaoImpl();
        DeveloperDao developerDao = new DeveloperHibernateDaoImpl();
        CustomerDao customerDao = new CustomerHibernateDaoImpl();
        CompanyDao companyDao = new CompanyHibernateDaoImpl();

        Controller controller = new Controller();
        controller.setSkillDao(skillDao);
        controller.setProjectDao(projectDao);
        controller.setDeveloperDao(developerDao);
        controller.setCustomerDao(customerDao);
        controller.setCompanyDao(companyDao);

        System.out.println(controller.readAll(SkillEntity.class));

        HibernateSessionFactory.getSessionFactory().close();
    }
}
